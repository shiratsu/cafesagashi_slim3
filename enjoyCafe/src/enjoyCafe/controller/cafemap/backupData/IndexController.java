package enjoyCafe.controller.cafemap.backupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class IndexController extends Controller {

private FeedCafeService service = new FeedCafeService();
    
    @Override
    public Navigation run() throws Exception {
        
        //検索ボタンが押された後、喫茶店一覧のリストを取得
        Map<String, Object> input = null;
        List<HashMap> cafeList = new ArrayList<HashMap>();
        boolean searchFlag = false;
        int searchCount = 0;
        
        //投稿ボタンを押した場合
        if(request.getParameter("search") != null){
            //フォームのパラメータを取得
            input = new RequestMap(request);
            //検索フラグの作成
            input = handleSearchStoreFlag(input);
            searchFlag = true;
            cafeList = service.feedCafeList(input);
            searchCount = cafeList.size();
        }
        requestScope("searchFlag", searchFlag);
        requestScope("searchCount", searchCount);
        requestScope("reqParam", input);
        requestScope("cafeList", cafeList);
        return forward("index.vm");
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


    /**
     * 店舗フラグの処理
     */
    private Map<String, Object> handleSearchStoreFlag(Map<String, Object> input) {
        // TODO Auto-generated method stub
        ArrayList<String> tmpAry = new ArrayList<String>();
        String ok = "";
        //店舗フラグが１なら
        if("1".equals(input.get("storeFlag1"))){
            tmpAry.add("1");
            ok += "1";
        }
        //店舗フラグが2なら
        if("1".equals(input.get("storeFlag2"))){
            tmpAry.add("2");
            ok += "2";
        }
        //店舗フラグが3なら
        if("1".equals(input.get("storeFlag3"))){
            tmpAry.add("3");
            ok += "3";
        }
        //店舗フラグが3なら
        if("1".equals(input.get("storeFlag4"))){
            tmpAry.add("4");
            ok += "4";
        }
        //店舗フラグが5なら
        if("1".equals(input.get("storeFlag5"))){
            tmpAry.add("5");
            ok += "5";
        }
        //店舗フラグが6なら
        if("1".equals(input.get("storeFlag6"))){
            tmpAry.add("6");
            ok += "6";
        }
        //店舗フラグが7なら
        if("1".equals(input.get("storeFlag7"))){
            tmpAry.add("7");
            ok += "7";
        }
        //店舗フラグが8なら
        if("1".equals(input.get("storeFlag8"))){
            tmpAry.add("8");
            ok += "8";
        }
        //店舗フラグが9なら
        if("1".equals(input.get("storeFlag9"))){
            tmpAry.add("9");
            ok += "9";
        }
        //店舗フラグが99なら
        if("1".equals(input.get("storeFlagZ"))){
            tmpAry.add("Z");
            ok += "Z";
        }
        
        //サイズが２なら、fromとToをセットして終わり
        if(tmpAry.size() == 2){
            input.put("storeFlagFrom", tmpAry.get(0));
            input.put("storeFlagTo", tmpAry.get(1));
            
        }else if(tmpAry.size() > 2){
            //それ以外
            if(tmpAry.size() > 0){
                input.put("storeFlagFrom", tmpAry.get(0));
                //サイズを取得
                int size = tmpAry.size();
                if(size > 2){
                    input.put("storeFlagTo", tmpAry.get(size-1));
                    input.put("storeFlagOk", ok);
                }
                
            }
        }
        
        return input;
    }
}
