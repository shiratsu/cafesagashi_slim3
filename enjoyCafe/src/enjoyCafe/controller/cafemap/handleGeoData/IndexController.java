package enjoyCafe.controller.cafemap.handleGeoData;

import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.apphosting.api.DeadlineExceededException;
import com.google.apphosting.api.ApiProxy.CapabilityDisabledException;

import enjoyCafe.service.FeedCafeService;
import enjoyCafe.service.PostCafeService;
import enjoyCafe.util.UserUtil;

public class IndexController extends Controller {

    private PostCafeService service = new PostCafeService();
    
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
        
        boolean uploadFlag = false;
        //投稿ボタンを押した場合
        if(request.getParameter("update") != null && request.getParameter("update") != ""){
            
            //DBを更新(geohash等のデータで入ってないものをセット)
            service.putGeoData();
            uploadFlag = true;
        }
        requestScope("uploadFlag", uploadFlag);
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
