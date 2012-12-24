package enjoyCafe.controller.cafemap.apiPostCafe3;


import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.apphosting.api.DeadlineExceededException;
import com.google.apphosting.api.ApiProxy.CapabilityDisabledException;

import enjoyCafe.service.ApiPostCafeServiceUpdate2;
import enjoyCafe.util.AreaHandle;
import enjoyCafe.util.CafeUtil;
import enjoyCafe.util.DateTimeUtil;
import enjoyCafe.util.EscapeUtil;

public class IndexController extends Controller {

    private ApiPostCafeServiceUpdate2 service = new ApiPostCafeServiceUpdate2();
    
    @Override
    public Navigation run() throws Exception {
        
        Logger logger = Logger.getLogger(IndexController.class.getName());
        //Map<String, Object> input = new HashMap<String, Object>();
        Map<String, Object> input = null;
        Key cafeKey=null;
        String returnKey = null;
        
        //投稿ボタンを押した場合
        if(request.getParameter("dataPost") != null || request.getParameter("datapost") != null){
            
            input = new RequestMap(request);
            logger.info("info log1");
            System.out.println("info log1");
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
                //String tmpName = URLDecoder.decode((String) input.get("storeName"),"utf-8");
                String tmpName = request.getParameter("storeName");
                String storeSubName = EscapeUtil.toHtmlStringForObject(tmpName).trim();
                storeFullName = storeMainName+" "+storeSubName;
                input.put("storeFullName", storeFullName);
            }
            logger.info("info log2");
            System.out.println("info log2");
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
            
            logger.info( "info log3");
            System.out.println("info log4");
            //更新の場合
            if(request.getParameter("key") != null && request.getParameter("key") != ""){
                
                //input.put("iine", "0");
                input.put("key",request.getParameter("key"));
                //DBを更新
                cafeKey = service.updateData(input);
            }else{
              //フォームのパラメータを取得
                //String StoreName = (String )createStoreName(request.getParameter("storeName"),request.getParameter("storeFlag"));
                //input.put("storeName", StoreName);
                
                //イイネ
                /*
                if(request.getParameter("iine") != null && request.getParameter("iine") != ""){
                    input.put("iine", 1);
                }
                */
                
                //DBに投入
                cafeKey = service.postData(input);
                
            }
            logger.info( "info log4");
            System.out.println("info log5");
        }
        
        //削除の場合
        if(request.getParameter("delete") != null && request.getParameter("delete") != ""){
            input = new RequestMap(request);
            input.put("deleteFlag", "1");
            input.put("key",request.getParameter("key"));
            //DBを更新
            cafeKey = service.deleteData(input);
        }
        if(cafeKey != null){
            returnKey = String.valueOf(cafeKey.getId());
        }
        
        
        requestScope("returnKey", returnKey);
        //nullがセットされないため対策
        input = notNullinput(input);
        requestScope("reqParam", input);
        return forward("index.vm");
    }
    
    /**
     * nullがセットされる可能性があるデータには、空文字or0
     * @param input
     * @return
     */
    private Map<String, Object> notNullinput(Map<String, Object> input) {
        // TODO Auto-generated method stub
        String storeMainName=null;
        String storeSubName=null;
        if(!input.containsKey("storeAddress")){
            input.put("storeAddress", "");
        }
        if(!input.containsKey("storeSubName")){
            if(input.get("storeName") != null){
                //HTML特殊文字変換
                storeSubName = EscapeUtil.toHtmlStringForObject(input.get("storeName"));
                

                input.put("storeSubName", storeSubName);
                input.remove("storeName");
                input.put("storeName", storeSubName);
            }else{
                input.put("storeSubName", "");
            }
            
        }
        
        if(!input.containsKey("storeMainName")){
            if(input.get("storeFlag") != null){
                String storeFlag = (String) input.get("storeFlag");
                storeMainName = CafeUtil.createStoreMainName(EscapeUtil.toHtmlStringForObject(storeFlag).trim());
                input.put("storeMainName", storeMainName);
            }else{
                input.put("storeMainName", "");
            }
        }
        if(!input.containsKey("storeName")){
            if(storeMainName != null && storeSubName != null && !"".equals(storeMainName) && !"".equals(storeSubName)){
                input.put("storeName", storeMainName+" "+storeSubName);
            }else{
                input.put("storeName", "");
            }
        }else{
            if(storeMainName != null && storeSubName != null && !"".equals(storeMainName) && !"".equals(storeSubName)){
                input.put("storeName", storeMainName+" "+storeSubName);
            }
        }
        if(!input.containsKey("storeCaption")){
            input.put("storeCaption", "");
        }
        if(!input.containsKey("zipCode")){
            input.put("zipCode", "");
        }
        if(!input.containsKey("phoneNumber")){
            input.put("phoneNumber", "");
        }
        if(!input.containsKey("storeFlag")){
            input.put("storeFlag", "");
        }
        if(!input.containsKey("lat")){
            input.put("lat", "");
        }
        if(!input.containsKey("lon")){
            input.put("lon", "");
        }
        if(!input.containsKey("tabako")){
            input.put("tabako", "");
        }
        if(!input.containsKey("kinen")){
            input.put("kinen", "");
        }
        if(!input.containsKey("koshitsu")){
            input.put("koshitsu", "");
        }
        if(!input.containsKey("wifi")){
            input.put("wifi", "");
        }
        if(!input.containsKey("shinya")){
            input.put("shinya", "");
        }
        if(!input.containsKey("pet")){
            input.put("pet", "");
        }
        return input;
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
            errorMessage = "予期せぬエラーが発生しました。" + errorCode.toString();
        }
        requestScope("errorCode", errorCode);
        requestScope("errorMessage", errorMessage);
        return forward("error.vm");
    }
    
}

