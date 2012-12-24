package enjoyCafe.controller.area.handleAreaData;

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

import enjoyCafe.service.HandleStationService;


/**
 * 駅一覧を表示
 * @author shiratsu
 *
 */
public class IndexController extends Controller {

    
    private HandleStationService feedStationService = new HandleStationService();
    
    @Override
    public Navigation run() throws Exception {
        
      //検索ボタンが押された後、喫茶店一覧のリストを取得
        Map<String, Object> input = null;
        List<HashMap> stationList = new ArrayList<HashMap>();
        boolean searchFlag = false;
        int searchCount = 0;
        
        
        //検索ボタンを押した場合
        if(request.getParameter("search") != null){
            //フォームのパラメータを取得
            input = new RequestMap(request);
            searchFlag = true;
            stationList = feedStationService.feedStationList(input);
            searchCount = stationList.size();
        }
        requestScope("searchFlag", searchFlag);
        requestScope("searchCount", searchCount);
        requestScope("reqParam", input);
        requestScope("stationList", stationList);
        
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
   
}
