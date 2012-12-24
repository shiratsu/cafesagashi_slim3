package enjoyCafe.controller.cafemap.postCafe;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import enjoyCafe.model.cafeMaster;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.param("storeName", "渋谷店");
        tester.param("storeCaption", "test");
        tester.param("storeAddress", "東京都渋谷区宇田川町２１−６");
        tester.param("zipCode", "12345678");
        tester.param("phoneNumber", "08099999999");
        tester.param("lat", "35.65993");
        tester.param("lon", "139.700318");
        tester.param("geohash6", "xcvbnm");
        tester.param("geohash8", "xcvbnmkl");
        tester.param("geohashAll", "xcvbnmkliii");
        tester.param("storeFlag", "1");
        tester.param("deleteFlag", "0");
        tester.param("tabako", "1");
        tester.param("kinen", "1");
        tester.param("koshitsu", "1");
        tester.param("wifi", "1");
        tester.param("pc", "1");
        tester.param("shinya", "1");
        tester.param("terace", "1");
        tester.param("pet", "1");
        tester.param("only", "1");
        tester.param("only1", "1");
        tester.param("only2", "0");
        tester.param("only3", "0");
        tester.param("only4", "1");
        tester.param("only5", "0");
        
        tester.param("dateMuki", "1");
        tester.param("dateMuki1", "1");
        tester.param("dateMuki2", "0");
        tester.param("dateMuki3", "0");
        tester.param("dateMuki4", "1");
        tester.param("dateMuki5", "0");
        
        tester.param("friendMattari", "1");
        tester.param("friendMattari1", "1");
        tester.param("friendMattari2", "0");
        tester.param("friendMattari3", "0");
        tester.param("friendMattari4", "1");
        tester.param("friendMattari5", "0");
        
        tester.param("manyPerson", "1");
        tester.param("manyPerson1", "1");
        tester.param("manyPerson2", "0");
        tester.param("manyPerson3", "0");
        tester.param("manyPerson4", "1");
        tester.param("manyPerson5", "0");
        
        tester.param("suiteru0to1", "1");
        tester.param("suiteru1to2", "1");
        tester.param("suiteru2to3", "1");
        tester.param("suiteru3to4", "1");
        tester.param("suiteru4to5", "1");
        tester.param("suiteru5to6", "1");
        tester.param("suiteru6to7", "1");
        tester.param("suiteru7to8", "1");
        tester.param("suiteru8to9", "1");
        tester.param("suiteru9to10", "1");
        tester.param("suiteru10to11", "1");
        tester.param("suiteru11to12", "1");
        tester.param("suiteru12to13", "1");
        tester.param("suiteru13to14", "1");
        tester.param("suiteru14to15", "1");
        tester.param("suiteru15to16", "1");
        tester.param("suiteru16to17", "1");
        tester.param("suiteru17to18", "1");
        tester.param("suiteru18to19", "1");
        tester.param("suiteru19to20", "1");
        tester.param("suiteru20to21", "1");
        tester.param("suiteru21to22", "1");
        tester.param("suiteru22to23", "1");
        tester.param("suiteru23to24", "1");
        tester.param("updateDateTime", "20100806000000");
        tester.param("dataPost", "1");
        tester.start("/cafemap/postCafe/");
        String postFlag = "1";
        IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        if(postFlag != null){
            assertThat(tester.isRedirect(), is(true));
        }else{
            assertThat(tester.isRedirect(), is(false));
        }
        assertThat(tester.getDestinationPath(), is("/cafemap/postCafe/index.vm"));
        cafeMaster stored = Datastore.query(cafeMaster.class).asSingle();
        assertThat(stored, is(notNullValue()));
        assertThat(stored.getStoreName(), is("渋谷"));
        
        
        
    }
}
