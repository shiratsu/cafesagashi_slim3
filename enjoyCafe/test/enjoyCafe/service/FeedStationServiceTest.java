package enjoyCafe.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FeedStationServiceTest extends AppEngineTestCase {

    private HandleStationService service = new HandleStationService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
