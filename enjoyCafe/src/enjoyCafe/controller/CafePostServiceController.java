package enjoyCafe.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CafePostServiceController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("CafePostService.jsp");
    }
}
