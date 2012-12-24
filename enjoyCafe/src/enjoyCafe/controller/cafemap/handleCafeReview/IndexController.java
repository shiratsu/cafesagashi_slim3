package enjoyCafe.controller.cafemap.handleCafeReview;

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

import enjoyCafe.service.HandleReviewService;
import enjoyCafe.util.DateTimeUtil;

/**
 * レビュー管理
 * @author shiratsu
 *
 */
public class IndexController extends Controller {

    private HandleReviewService service = new HandleReviewService();
    
    @Override
    public Navigation run() throws Exception {
        
        List<HashMap> reviewList = new ArrayList<HashMap>();
        Map<String, Object> input = new HashMap<String, Object>();
        input = new RequestMap(request);
        //投稿ボタンを押した場合
        if(request.getParameter("postReview") != null){
            //時間を取得
            String nowTime = DateTimeUtil.getDateTimeString();
            input.put("updateDate", nowTime);
            //DBに投入
            service.postData(input);
        }
        //レビュー一覧を取得
        if(request.getParameter("cafeId") != null){
            reviewList = service.feedReviewList(input);
        }
        requestScope("reqParam", input);
        requestScope("reviewList", reviewList);
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
