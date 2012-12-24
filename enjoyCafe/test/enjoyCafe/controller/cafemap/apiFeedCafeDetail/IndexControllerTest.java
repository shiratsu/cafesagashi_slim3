package enjoyCafe.controller.cafemap.apiFeedCafeDetail;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import enjoyCafe.controller.cafemap.apiFeedCafeDetail.IndexController;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/cafemap/apiFeedCafe/");
        IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
