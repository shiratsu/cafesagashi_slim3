package enjoyCafe.service;

import java.util.HashMap;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import enjoyCafe.model.areaData;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PostAreaServiceTest extends AppEngineTestCase {

    private PostAreaService service = new PostAreaService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
    
    @Test
    public void postArea() throws Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("rcCode", "1");
        input.put("lineCode", "1");
        input.put("stationCode", "1");
        input.put("lineSort", "1");
        input.put("stationSort", "1");
        input.put("stationGroup", "1");
        input.put("rcType", "1");
        input.put("rcName", "1");
        input.put("lineName", "1");
        input.put("stationName", "渋谷");
        input.put("searchStationName", "1");
        input.put("prefCode", "13");
        input.put("stationType", "1");
        input.put("lon", "1");
        input.put("lat", "1");
        input.put("geohash6", "xcvbnm");
        input.put("geohash8", "xcvbnmkl");
        input.put("geohashAll", "xcvbnmkl987");
        areaData ad = service.postData(input);
        assertThat(ad, is(notNullValue()));
        areaData stored = Datastore.get(areaData.class, ad.getKey());
        assertThat(stored.getStationName(), is("渋谷"));
    }    
}
