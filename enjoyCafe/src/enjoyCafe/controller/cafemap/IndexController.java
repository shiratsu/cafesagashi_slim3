package enjoyCafe.controller.cafemap;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String message = "Controller����̃��b�Z�[�W�ł�";
        requestScope("message", message);
        return forward("index.vm");
    }
    
    
}
