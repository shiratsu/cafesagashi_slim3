package enjoyCafe.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class AreaPostServiceController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("AreaPostService.jsp");
    }
}
