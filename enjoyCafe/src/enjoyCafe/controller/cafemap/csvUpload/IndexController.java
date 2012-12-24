package enjoyCafe.controller.cafemap.csvUpload;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.util.HashMap;
import java.util.Map;


import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.apphosting.api.DeadlineExceededException;
import com.google.apphosting.api.ApiProxy.CapabilityDisabledException;
import java.io.Reader;

import enjoyCafe.service.PostAreaService;
import enjoyCafe.service.PostCafeService;
import enjoyCafe.service.UploadService;
import enjoyCafe.util.AreaHandle;
import enjoyCafe.util.DateTimeUtil;
import enjoyCafe.util.UserUtil;

public class IndexController extends Controller {

    private PostCafeService service = new PostCafeService();
    static final String STATUS = "status";
    static final String ERRCODE = "errorCode";
    static final String ERRMESSAGE = "errorMessage";
    static final String CANRETRY = "canRetry";
    static final String STATUS_OK = "OK";
    static final String STATUS_NG = "NG";
    
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
        boolean uploadFlag = false;
        
        //アップロードボタンが押されたら
        if(requestScope("data") != null){
            //ファイルの中身を取得
            
            
            final FileItem formFile = requestScope("data");
            String fileName = formFile.getFileName();
            InputStream is = new ByteArrayInputStream(formFile.getData());
            
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;
            
            int i = 0;
            
            //一行ずつ読みこんでアップロードする
            while ((line = buffer.readLine()) != null) {
                line = line.replaceAll("\"", "");
                //System.out.println(line);
                //String lineData = new String(line.getBytes(), "UTF-8");
                //String lineData = new String(line.getBytes("Shift_JIS"), "UTF-8");
                System.out.println(line);
                String[] split = StringUtil.splitData(line,",",20);
                
                if (split != null && i != 0) {
                    Map<String, Object> input = new HashMap<String, Object>();
                    input.put("storeName", createInputData(split[0]));
                    input.put("storeCaption", createInputData(split[1]));
                    input.put("storeAddress", createInputData(split[2]));
                    input.put("phoneNumber", createInputData(split[3]));
                    input.put("zipCode", createInputData(split[4]));
                    input.put("storeFlag", createInputData(split[7]));
                    input.put("tabako", createInputData(split[8]));
                    input.put("kinen", createInputData(split[9]));
                    input.put("koshitsu", createInputData(split[10]));
                    input.put("wifi", createInputData(split[11]));
                    input.put("pc", createInputData(split[12]));
                    input.put("shinya", createInputData(split[13]));
                    input.put("terace", createInputData(split[14]));
                    input.put("pet", createInputData(split[15]));
                    input.put("only", createInputData(split[16]));
                    input.put("dateMuki", createInputData(split[17]));
                    input.put("friendMattari", createInputData(split[18]));
                    input.put("manyPerson", createInputData(split[19]));
                    
                    /*
                    //エリア系のデータを取得
                    String geohash6 = null;
                    String geohash8 = null;
                    String geohashAll = null;
                    
                    //住所から緯度経度を取得
                    double lat = 0;
                    double lng = 0;
                    
                    //エリア系データを取得して、DBにいるか問い合わせ
                    String[] areaAry = AreaHandle.feedGeoHash(split[2]);
                    if(areaAry != null){
                        if(areaAry.length == 5){
                            geohashAll = areaAry[0];
                            geohash6 = areaAry[1];
                            geohash8 = areaAry[2];
                            lat = Double.valueOf(areaAry[3]);
                            lng = Double.valueOf(areaAry[4]);
                        }
                    }
                    */
                    //時間を取得
                    String nowTime = DateTimeUtil.getDateTimeString();
                    input.put("lat", "");
                    input.put("lon", "");
                    input.put("geohash4", "");
                    input.put("geohash5", "");
                    input.put("geohash6", "");
                    input.put("geohash8", "");
                    input.put("geohashAll", "");
                    input.put("geohashAround", "");
                    input.put("updateCount", "1");
                    input.put("deleteFlag", "0");
                    input.put("iine", "0");
                    input.put("updateDateTime", nowTime);
                    //DBに投入
                    service.postData(input);
                    
                }
                i++;
            }
            
            //FileItem formFile2 = requestScope("data");
            //us.upload(formFile2);
            //return redirect(basePath);
            
            uploadFlag = true;
        }
        
    
        requestScope("uploadFlag", uploadFlag);
        return forward("upload.vm");
        
    }
    
    /**
     * 電話番号からハイフンを削除
     * @param string
     * @return
     */
    private Object changePhoneNumber(String string) {
        //改行とは削除
        if(string != null){
            return string.trim().replace("-", "");
        }
        return null;
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
     * DBにインサートする用のデータを作成
     * @param string
     * @return
     */
    private Object createInputData(String string) {
        //改行とは削除
        if(string != null){
            return string.trim();
        }
        return null;
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
