package enjoyCafe.controller.cafemap.postCafe;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.apphosting.api.DeadlineExceededException;
import com.google.apphosting.api.ApiProxy.CapabilityDisabledException;

import enjoyCafe.util.AreaHandle;
import enjoyCafe.util.CafeUtil;
import enjoyCafe.util.DateTimeUtil;
import enjoyCafe.util.EscapeUtil;
import enjoyCafe.util.Geohash;
import enjoyCafe.util.UserUtil;

import enjoyCafe.service.FeedCafeService;
import enjoyCafe.service.PostCafeService;

public class IndexController extends Controller {

    private PostCafeService service = new PostCafeService();
    private FeedCafeService feedService = new FeedCafeService();
    
    @Override
    public Navigation run() throws Exception {
        
        UserService userService = UserServiceFactory.getUserService();

        String thisURL = request.getRequestURI();
        //認証前の場合
        if (request.getUserPrincipal() == null) {
            return redirect(userService.createLoginURL(thisURL));
        }
        if(!UserUtil.checkUser(userService)){
            return redirect("/cafemap/ngUser/");
        }
        
        //Map<String, Object> input = new HashMap<String, Object>();
        Map<String, Object> input = null;
        
        
        //投稿ボタンを押した場合
        if(request.getParameter("dataPost") != null && request.getParameter("dataPost") != ""){
            
            input = new RequestMap(request);
            
            //エリア系のデータを取得
            String geohash4 = null;
            String geohash5 = null;
            String geohash6 = null;
            String geohash8 = null;
            String geohashAll = null;
            String geohashAround = null;
            
            String storeFullName = null;
            
            if(input.get("storeName") != null && 
               !"".equals(input.get("storeName")) && 
               !"(null)".equals(input.get("storeName")) &&
               input.get("storeFlag") != null && 
               !"".equals(input.get("storeFlag")) && 
               !"(null)".equals(input.get("storeFlag"))
            ){
                String storeMainName = CafeUtil.createStoreMainName(EscapeUtil.toHtmlStringForObject(input.get("storeFlag")).trim());
                String storeSubName = EscapeUtil.toHtmlStringForObject(input.get("storeName")).trim();
                storeFullName = storeMainName+" "+storeSubName;
                input.put("storeFullName", storeFullName);
            }
            
            //住所から緯度経度を取得
            double lat = 0;
            double lng = 0;
            String[] areaAry = null;

            if(input.get("lat") != null && !"".equals(input.get("lat"))){
                //geoHashを取得(geohashAll)
                
                boolean checkLatLon = latlonCheck(input);
                
                if(checkLatLon == true){
                    String latStr = (String) input.get("lat");
                    String lonStr = (String) input.get("lon");
                    lat = Double.valueOf(latStr);
                    lng = Double.valueOf(lonStr);
                    String geoHash = Geohash.encode(lat, lng);
                    //System.out.println(geoHash);
                    if(geoHash != null){
                        //６桁と８桁のものを取得
                        String[] geoAry = geoHash.split("");
                        geohash4 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4];
                        geohash5 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5];
                        geohash6 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6];
                        geohash8 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6]+geoAry[7]+geoAry[8];
                    }
                    input.put("lat", String.valueOf(lat));
                    input.put("lon", String.valueOf(lng));
                    input.put("geohash4", geohash4);
                    input.put("geohash5", geohash5);
                    input.put("geohash6", geohash6);
                    input.put("geohash8", geohash8);
                    input.put("geohashAround", geohashAround);
                    input.put("geohashAll", geohashAll);
                }   
            }else{
              //エリア系データを取得して、DBにいるか問い合わせ
                areaAry = AreaHandle.feedGeoHash(request.getParameter("storeAddress"));
                if(areaAry != null){
                    if(areaAry.length == 7){
                        geohashAll = areaAry[0];
                        geohash4 = areaAry[6];
                        geohash6 = areaAry[1];
                        geohash8 = areaAry[2];
                        geohash5 = areaAry[5];
                        lat = Double.valueOf(areaAry[3]);
                        lng = Double.valueOf(areaAry[4]);
                    }
                }
                //geohashの周りの値を取得
                geohashAround = CafeUtil.geohashAround(geohash6);
                
                input.put("lat", String.valueOf(lat));
                input.put("lon", String.valueOf(lng));
                input.put("geohash4", geohash4);
                input.put("geohash5", geohash5);
                input.put("geohash6", geohash6);
                input.put("geohash8", geohash8);
                input.put("geohashAround", geohashAround);
                input.put("geohashAll", geohashAll);
             
            }
            //時間を取得
            String nowTime = DateTimeUtil.getDateTimeString();
            
            input.put("deleteFlag", "0");
            input.put("updateDateTime", nowTime);
            
            //新規投稿の場合
            if(request.getParameter("key") == null || request.getParameter("key") == ""){
                //フォームのパラメータを取得
                String StoreName = (String )request.getParameter("storeName");
                
                //イイネがセットされてなかったら
                if(!input.containsKey("iine") || input.get("iine") == ""){
                    input.put("iine", 0);
                }
                
                input.put("storeName", StoreName);
                input.put("updateCount", "1");
                
                //DBに投入
                service.postData(input);
                return redirect("/cafemap/postCafe/");
            }else{
                int updateCount = 0;
                //更新回数
                if(request.getParameter("updateCount") != ""){
                    updateCount = Integer.valueOf(request.getParameter("updateCount"));
                    updateCount++;
                }
                //イイネ
                if(request.getParameter("iine") != null && request.getParameter("iine") != ""){
                    input.put("iine", 1);
                }
                
                input.put("updateCount", String.valueOf(updateCount));
                input.put("key",request.getParameter("key"));
                //DBを更新
                service.updateData(input);
                return redirect("/top/");
            }
        }
        
        //削除の場合
        if(request.getParameter("delete") != null && request.getParameter("delete") != ""){
            input = new RequestMap(request);
            input.put("deleteFlag", "1");
            input.put("key",request.getParameter("key"));
            //DBを更新
            service.deleteData(input);
            return redirect("/top/");
        }
        
        //復活の場合
        if(request.getParameter("hukkatsu") != null && request.getParameter("hukkatsu") != ""){
            input = new RequestMap(request);
            input.put("deleteFlag", "0");
            input.put("key",request.getParameter("key"));
            //DBを更新
            service.hukkatsuData(input);
            return redirect("/top/");
        }
        
        
        //IDをもって遷移してきたら更新なので、データを取得する
        if(request.getParameter("key") != null && request.getParameter("dataPost") == null){
            input = feedService.feedOneData(request.getParameter("key"));
        }
        
        requestScope("reqParam", input);
        return forward("index.vm");
    }
    
    /**
     * 緯度経度型をチェック
     * @param input
     * @return
     */
    private boolean latlonCheck(Map<String, Object> input) {
        // TODO Auto-generated method stub
        String latStr = (String) input.get("lat");
        String lonStr = (String) input.get("lon");
        double lat = Double.valueOf(latStr);
        double lng = Double.valueOf(lonStr);
        //まず、緯度を確認
        Pattern p = Pattern.compile("[¥¥d.]");
        Matcher m = p.matcher(latStr);
        if(!m.find()){
            return false;
        }
        //次は経度
        m = p.matcher(lonStr);
        if(!m.find()){
            return false;
        }
        return true;
    }

    /**
     * 店舗名を返す
     * @param string
     * @param string2
     * @return
     */
    private Object createStoreName(String string, String string2) {
        // TODO Auto-generated method stub
        String storeName = "";
        if(!"Z".equals(string2)){
            switch(Integer.valueOf(string2)){
                case 1:
                    storeName = "スターバックス "+string;
                break;
                case 2:
                    storeName = "ドトール "+string;
                break;
                case 3:
                    storeName = "タリーズ "+string;
                break;
                case 4:
                    storeName = "サンマルクカフェ "+string;
                break;
                case 5:
                    storeName = "エクセルシオール "+string;
                break;
                case 6:
                    storeName = "シアトルズベスト "+string;
                break;
                case 7:
                    storeName = "ベローチェ "+string;
                break;
                case 8:
                    storeName = "カフェドクリエ "+string;
                break;
                case 9:
                    storeName = "コメダ珈琲 "+string;
                break;
                default:
                    storeName = string;
                break;
            
            }
        }
        return (Object )storeName;
    }
    
    /**
     * 現在時間を取得
     * @return
     * @throws ParseException
     */
    private Date getDateTime() throws ParseException {
    
        Calendar cal=Calendar.getInstance();

        MessageFormat mf = new MessageFormat("{0,date,yyyy/MM/dd HH:mm:ss}");
        
        Object[] objs = {Calendar.getInstance().getTime()};

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String result = mf.format(objs);
        Date date = df.parse(result);
        return date;
    }
    
/*   
    @Override
    protected Navigation handleError(Throwable error) throws Throwable {
        Map<String, Object> map = Maps.newHashMap();
        String errorCode;
        String errorMessage;
        boolean canRetry = false;
        if (error instanceof CapabilityDisabledException) {
            errorCode = "READONLY";
            errorMessage = "AppEngineのサービスが読み取り専用です";
        } else if (error instanceof DatastoreTimeoutException) {
            errorCode = "DSTIMEOUT";
            errorMessage = "データストアがタイムアウトしました。";
            canRetry = true;
        } else if (error instanceof DatastoreFailureException) {
            errorCode = "DSFAILURE";
            errorMessage = "データストアのアクセスに失敗しました。";
        } else if (error instanceof DeadlineExceededException) {
            errorCode = "DEE";
            errorMessage = "30秒を超えても処理が終了しませんでした。";
            canRetry = true;
        } else {
            errorCode = "UNKNOWN";
            errorMessage = "予期せぬエラーが発生しました。" + error.toString();
        }
        requestScope("errorCode", errorCode);
        requestScope("errorMessage", errorMessage);
        return forward("error.vm");
    }
*/   
}
