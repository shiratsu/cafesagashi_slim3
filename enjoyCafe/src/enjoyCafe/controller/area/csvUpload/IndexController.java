package enjoyCafe.controller.area.csvUpload;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import enjoyCafe.service.PostAreaService;
import enjoyCafe.util.DateTimeUtil;
import enjoyCafe.util.Geohash;

public class IndexController extends Controller {

    private PostAreaService service = new PostAreaService();
    
    @Override
    public Navigation run() throws Exception {
        
        UserService userService = UserServiceFactory.getUserService();

        String thisURL = request.getRequestURI();
        //認証前の場合
        if (request.getUserPrincipal() == null) {
            return redirect(userService.createLoginURL(thisURL));
        }
        
        boolean uploadFlag = false;
        
        //アップロードボタンが押されたら
        if(requestScope("data") != null){
            //ファイルの中身を取得
            final FileItem formFile = requestScope("data");
            //String fileName = formFile.getFileName();
            InputStream is = new ByteArrayInputStream(formFile.getData());
            
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;
            
            int i = 0;
            
            //一行ずつ読みこんでアップロードする
            while ((line = buffer.readLine()) != null) {
                //System.out.println(line);
                //String lineData = new String(line.getBytes(), "UTF-8");
                String[] split = line.split(",");
                //System.out.println(lineData);
                if (split != null && split.length == 14 && i != 0) {
                    Map<String, Object> input = new HashMap<String, Object>();
                    //駅名に「(」がある場合があるのでそれでスプリット
                    //System.out.println(line);
                    input.put("rcCode", split[0].trim());
                    input.put("lineCode", split[1].trim());
                    input.put("stationCode", split[2].trim());
                    input.put("lineSort", split[3].trim());
                    input.put("stationSort", split[4].trim());
                    input.put("stationGroup", split[5].trim());
                    input.put("rcType", split[6].trim());
                    input.put("rcName", split[7].trim());
                    input.put("lineName", split[8].trim());
                    input.put("stationName", split[9].trim());
                    input.put("prefCode", split[10].trim());
                    input.put("lon", split[11].trim());
                    input.put("lat", split[12].trim());
                    /*
                    //ジオコードを取得
                    String geoHash = Geohash.encode(Double.valueOf(split[12].trim()), 
                                                    Double.valueOf(split[11].trim()));
                    String geohash6 = null;
                    String geohash8 = null;
                    if(geoHash != null){
                        //６桁と８桁のものを取得
                        String[] geoAry = geoHash.split("");
                        geohash6 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6];
                        geohash8 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6]+geoAry[7]+geoAry[8];
                    }
                    */
                    input.put("geohash4", "");
                    input.put("geohash5", "");
                    input.put("geohash6", "");
                    input.put("geohash8", "");
                    input.put("geohashAll", "");
                    input.put("geohashAround", "");
                    
                    //更新時間を取得
                    String nowTime = DateTimeUtil.getDateTimeString();
                    input.put("updateDate", nowTime);
                    
                    //DBに投入
                    service.postData(input);
                }
                i++;
                System.out.println(i);
            }
            uploadFlag = true;
        }
        requestScope("uploadFlag", uploadFlag);
        return forward("upload.vm");
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
