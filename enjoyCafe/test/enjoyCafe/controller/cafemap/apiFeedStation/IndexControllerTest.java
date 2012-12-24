package enjoyCafe.controller.cafemap.apiFeedStation;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import enjoyCafe.controller.cafemap.apiFeedStation.IndexController;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/cafemap/feedStation/");
        IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
