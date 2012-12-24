package enjoyCafe.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import enjoyCafe.meta.areaDataMeta;
import enjoyCafe.model.areaData;
import enjoyCafe.util.AreaHandle;
import enjoyCafe.util.CafeUtil;
import enjoyCafe.util.DateTimeUtil;
import enjoyCafe.util.EscapeUtil;


public class PostAreaService {

    private areaDataMeta adm = new areaDataMeta();
    /**
     * データ投稿、更新
     * @param input
     * @return
     */
    public areaData postData(Map<String, Object> input) {
        //データをbeanにコピーして投稿する
        areaData ad = new areaData();
        BeanUtil.copy(input, ad);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(ad);
        tx.commit();
        return ad;
    }

    /**
     * geo系のデータを更新する
     * @throws ParseException 
     */
    public void putGeoData() throws ParseException {
        // TODO Auto-generated method stub
        
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();       
        Query query = new Query(adm.getKind());
        query.addFilter("geohash4", Query.FilterOperator.EQUAL, "");
        //query.addFilter("geohashAround", Query.FilterOperator.EQUAL, null);
        List<Entity> list = ds.prepare(query).asList(FetchOptions.Builder.withOffset(0)); 
        for (Entity entity : list) {
            Transaction tx = Datastore.beginTransaction();
            String latStr = EscapeUtil.toHtmlStringForObject(entity.getProperty("lat"));
            String lonStr = EscapeUtil.toHtmlStringForObject(entity.getProperty("lon"));
            
          //エリア系のデータを取得
            String geohash4 = "";
            String geohash5 = "";
            String geohash6 = "";
            String geohash8 = "";
            String geohashAll = "";
            String geohashAround = "";
            
            //住所から緯度経度を取得
            double lat = 0;
            double lng = 0;
            //時間を取得
            String nowTime = DateTimeUtil.getDateTimeString();
            //エリア系データを取得して、DBにいるか問い合わせ
            String[] areaAry = AreaHandle.feedGeoHashForLatLon(latStr,lonStr);
            if(areaAry != null){
                if(areaAry.length == 7){
                    geohashAll = areaAry[0];
                    geohash6 = areaAry[1];
                    geohash5 = areaAry[5];
                    geohash4 = areaAry[6];
                    geohash8 = areaAry[2];
                    lat = Double.valueOf(areaAry[3]);
                    lng = Double.valueOf(areaAry[4]);
                }
            }
            geohashAround = CafeUtil.geohashAround(geohash6);
            entity.setProperty("lat", lat);
            entity.setProperty("lon", lng);
            entity.setProperty("geohash4", geohash4);
            entity.setProperty("geohash5", geohash5);
            entity.setProperty("geohash6", geohash6);
            entity.setProperty("geohash8", geohash8);
            entity.setProperty("geohashAll", geohashAll);
            entity.setProperty("geohashAround", geohashAround);
            entity.setProperty("updateDate", nowTime);
            ds.put(entity);
            tx.commit();
        }
        
        return;
    }
}
