package enjoyCafe.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CafeFacoriteTest extends AppEngineTestCase {

    private CafeFacorite model = new CafeFacorite();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
