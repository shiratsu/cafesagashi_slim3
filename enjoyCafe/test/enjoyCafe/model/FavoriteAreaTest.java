package enjoyCafe.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FavoriteAreaTest extends AppEngineTestCase {

    private FavoriteArea model = new FavoriteArea();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
