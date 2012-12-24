package enjoyCafe.controller.cafemap.uploadCheck;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import enjoyCafe.service.UploadService;

public class IndexController extends Controller {

    private UploadService service = new UploadService();
    @Override
    public Navigation run() throws Exception {
        requestScope("dataList", service.getDataList());
        return forward("index.jsp");
    }
}
