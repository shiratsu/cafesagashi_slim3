package enjoyCafe.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class areaDataTest extends AppEngineTestCase {

    private areaData model = new areaData();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
