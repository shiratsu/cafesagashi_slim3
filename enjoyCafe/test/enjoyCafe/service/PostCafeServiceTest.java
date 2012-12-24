package enjoyCafe.service;

import java.util.HashMap;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import enjoyCafe.model.cafeMaster;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PostCafeServiceTest extends AppEngineTestCase {

    private PostCafeService service = new PostCafeService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
    
    @Test
    public void postCafe() throws Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("storeName", "渋谷店");
        input.put("storeCaption", "test");
        input.put("storeAddress", "東京都渋谷区宇田川町２１−６");
        input.put("zipCode", "12345678");
        input.put("phoneNumber", "08099999999");
        input.put("lat", "35.65993");
        input.put("lon", "139.700318");
        input.put("geohash6", "xcvbnm");
        input.put("geohash8", "xcvbnmkl");
        input.put("geohashAll", "xcvbnmkliii");
        input.put("storeFlag", "1");
        input.put("deleteFlag", "0");
        input.put("tabako", "1");
        input.put("kinen", "1");
        input.put("koshitsu", "1");
        input.put("wifi", "1");
        input.put("pc", "1");
        input.put("shinya", "1");
        input.put("terace", "1");
        input.put("pet", "1");
        input.put("only", "1");
        input.put("only1", "1");
        input.put("only2", "0");
        input.put("only3", "0");
        input.put("only4", "1");
        input.put("only5", "0");
        
        input.put("dateMuki", "1");
        input.put("dateMuki1", "1");
        input.put("dateMuki2", "0");
        input.put("dateMuki3", "0");
        input.put("dateMuki4", "1");
        input.put("dateMuki5", "0");
        
        input.put("friendMattari", "1");
        input.put("friendMattari1", "1");
        input.put("friendMattari2", "0");
        input.put("friendMattari3", "0");
        input.put("friendMattari4", "1");
        input.put("friendMattari5", "0");
        
        input.put("manyPerson", "1");
        input.put("manyPerson1", "1");
        input.put("manyPerson2", "0");
        input.put("manyPerson3", "0");
        input.put("manyPerson4", "1");
        input.put("manyPerson5", "0");
        
        input.put("suiteru0to1", "1");
        input.put("suiteru1to2", "1");
        input.put("suiteru2to3", "1");
        input.put("suiteru3to4", "1");
        input.put("suiteru4to5", "1");
        input.put("suiteru5to6", "1");
        input.put("suiteru6to7", "1");
        input.put("suiteru7to8", "1");
        input.put("suiteru8to9", "1");
        input.put("suiteru9to10", "1");
        input.put("suiteru10to11", "1");
        input.put("suiteru11to12", "1");
        input.put("suiteru12to13", "1");
        input.put("suiteru13to14", "1");
        input.put("suiteru14to15", "1");
        input.put("suiteru15to16", "1");
        input.put("suiteru16to17", "1");
        input.put("suiteru17to18", "1");
        input.put("suiteru18to19", "1");
        input.put("suiteru19to20", "1");
        input.put("suiteru20to21", "1");
        input.put("suiteru21to22", "1");
        input.put("suiteru22to23", "1");
        input.put("suiteru23to24", "1");
        input.put("updateDateTime", "20100806000000");
        
        cafeMaster cm = service.postData(input);
        assertThat(cm, is(notNullValue()));
        cafeMaster stored = Datastore.get(cafeMaster.class, cm.getKey());
        assertThat(stored.getStoreName(), is("渋谷店"));
    }
}
