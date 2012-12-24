package enjoyCafe.controller.cafemap.apiPostCafe;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.apphosting.api.DeadlineExceededException;
import com.google.apphosting.api.ApiProxy.CapabilityDisabledException;

import enjoyCafe.service.FeedCafeService;
import enjoyCafe.service.ApiPostCafeService;
import enjoyCafe.util.AreaHandle;
import enjoyCafe.util.CafeUtil;
import enjoyCafe.util.DateTimeUtil;
import enjoyCafe.util.EscapeUtil;

public class IndexController extends Controller {

    private ApiPostCafeService service = new ApiPostCafeService();
    private FeedCafeService feedService = new FeedCafeService();
    
    @Override
    public Navigation run() throws Exception {
        
        //Map<String, Object> input = new HashMap<String, Object>();
        Map<String, Object> input = null;
        
        //投稿ボタンを押した場合
        if(request.getParameter("dataPost") != null){
            
            input = new RequestMap(request);
            
            //店舗名称をセット
            
            
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
            //時間を取得
            String nowTime = DateTimeUtil.getDateTimeString();
            if(request.getParameter("storeAddress") != null && request.getParameter("storeAddress") != ""){
                //エリア系データを取得して、DBにいるか問い合わせ
                String[] areaAry = AreaHandle.feedGeoHash(request.getParameter("storeAddress"));
                if(areaAry != null){
                    if(areaAry.length == 7){
                        geohashAll = areaAry[0];
                        geohash4 = areaAry[6];
                        geohash5 = areaAry[5];
                        geohash6 = areaAry[1];
                        geohash8 = areaAry[2];
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
                input.put("deleteFlag", "0");
                input.put("updateDateTime", nowTime);
            }
            
            
            //更新の場合
            if(request.getParameter("key") != null && request.getParameter("key") != ""){
                
                
                //input.put("iine", "0");
                input.put("key",request.getParameter("key"));
                //DBを更新
                service.updateData(input);
            }else{
              //フォームのパラメータを取得
                //String StoreName = (String )createStoreName(request.getParameter("storeName"),request.getParameter("storeFlag"));
                //input.put("storeName", StoreName);
                input.put("updateCount", "1");
                
                //イイネ
                /*
                if(request.getParameter("iine") != null && request.getParameter("iine") != ""){
                    input.put("iine", 1);
                }
                */
                
                //DBに投入
                service.postData(input);
                
            }
        }
        
        //削除の場合
        if(request.getParameter("delete") != null && request.getParameter("delete") != ""){
            input = new RequestMap(request);
            input.put("deleteFlag", "1");
            input.put("key",request.getParameter("key"));
            //DBを更新
            service.deleteData(input);
        }
        
        requestScope("reqParam", input);
        return forward("index.vm");
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
            case 99:
                storeName = string;
            break;
        
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
    
}
