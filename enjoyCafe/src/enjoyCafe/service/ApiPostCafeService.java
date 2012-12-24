package enjoyCafe.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import enjoyCafe.meta.cafeMasterMeta;
import enjoyCafe.model.cafeMaster;
import enjoyCafe.util.AreaHandle;
import enjoyCafe.util.CafeUtil;
import enjoyCafe.util.DateTimeUtil;
import enjoyCafe.util.EscapeUtil;


public class ApiPostCafeService {

    private cafeMasterMeta cmm = new cafeMasterMeta();
    /**
     * データ投稿、更新
     * @param input
     * @return
     */
    public cafeMaster postData(Map<String, Object> input) {
        //データをbeanにコピーして投稿する
        cafeMaster cm = new cafeMaster();
        //BeanUtil.copy(input, cm);
        //パラメータをセット
        if(input.get("storeName") != null && !"".equals(input.get("storeName")) && !"(null)".equals(input.get("storeName"))){
            cm.setStoreName(EscapeUtil.toHtmlStringForObject(input.get("storeName")));
        }
        if(input.get("storeFullName") != null && !"".equals(input.get("storeFullName")) && !"(null)".equals(input.get("storeFullName"))){
            cm.setStoreFullName(EscapeUtil.toHtmlStringForObject(input.get("storeFullName")));
        }
        if(input.get("storeCaption") != null && !"".equals(input.get("storeCaption")) && !"(null)".equals(input.get("storeCaption"))){
            cm.setStoreCaption(EscapeUtil.toHtmlStringForObject(input.get("storeCaption")));
        }
        if(input.get("storeAddress") != null && !"".equals(input.get("storeAddress")) && !"(null)".equals(input.get("storeAddress"))){
            cm.setStoreAddress(EscapeUtil.toHtmlStringForObject(input.get("storeAddress")));
        }
        if(input.get("phoneNumber") != null && !"".equals(input.get("phoneNumber")) && !"(null)".equals(input.get("phoneNumber"))){
            cm.setPhoneNumber(EscapeUtil.toHtmlStringForObject(input.get("phoneNumber")));
        }
        if(input.get("geohash4") != null && !"".equals(input.get("geohash4")) && !"(null)".equals(input.get("geohash4"))){
            cm.setGeohash4(EscapeUtil.toHtmlStringForObject(input.get("geohash4")));
        }
        if(input.get("geohash5") != null && !"".equals(input.get("geohash5")) && !"(null)".equals(input.get("geohash5"))){
            cm.setGeohash5(EscapeUtil.toHtmlStringForObject(input.get("geohash5")));
        }
        if(input.get("geohash6") != null && !"".equals(input.get("geohash6")) && !"(null)".equals(input.get("geohash6"))){
            cm.setGeohash6(EscapeUtil.toHtmlStringForObject(input.get("geohash6")));
        }
        if(input.get("geohash8") != null && !"".equals(input.get("geohash8")) && !"(null)".equals(input.get("geohash8"))){
            cm.setGeohash8(EscapeUtil.toHtmlStringForObject(input.get("geohash8")));
        }
        if(input.get("geohashAll") != null && !"".equals(input.get("geohashAll")) && !"(null)".equals(input.get("geohashAll"))){
            cm.setGeohashAll(EscapeUtil.toHtmlStringForObject(input.get("geohashAll")));
        }
        if(input.get("geohashAround") != null && !"".equals(input.get("geohashAround")) && !"(null)".equals(input.get("geohashAround"))){
            cm.setGeohashAround(EscapeUtil.toHtmlStringForObject(input.get("geohashAround")));
        }
        if(input.get("lat") != null && !"".equals(input.get("lat")) && !"(null)".equals(input.get("lat"))){
            cm.setLat(EscapeUtil.toHtmlStringForObject(input.get("lat")));
        }
        if(input.get("lon") != null && !"".equals(input.get("lon")) && !"(null)".equals(input.get("lon"))){
            cm.setLon(EscapeUtil.toHtmlStringForObject(input.get("lon")));
        }
        if(input.get("zipCode") != null && !"".equals(input.get("zipCode")) && !"(null)".equals(input.get("zipCode"))){
            cm.setZipCode(EscapeUtil.toHtmlStringForObject(input.get("zipCode")));
        }
        if(input.get("deviceId") != null && !"".equals(input.get("deviceId")) && !"(null)".equals(input.get("deviceId"))){
            cm.setDeviceId(EscapeUtil.toHtmlStringForObject(input.get("deviceId")));
        }
        if(input.get("kinen") != null && !"".equals(input.get("kinen")) && !"(null)".equals(input.get("kinen"))){
            cm.setKinen(EscapeUtil.toHtmlStringForObject(input.get("kinen")));
        }
        if(input.get("koshitsu") != null && !"".equals(input.get("koshitsu")) && !"(null)".equals(input.get("koshitsu"))){

            cm.setKoshitsu(EscapeUtil.toHtmlStringForObject(input.get("koshitsu")));
        }
        if(input.get("storeFlag") != null && !"".equals(input.get("storeFlag")) && !"(null)".equals(input.get("storeFlag"))){
            
            cm.setStoreFlag(EscapeUtil.toHtmlStringForObject(input.get("storeFlag")));
        }
        if(input.get("tabako") != null && !"".equals(input.get("tabako")) && !"(null)".equals(input.get("tabako"))){
            cm.setTabako(EscapeUtil.toHtmlStringForObject(input.get("tabako")));
        }
        if(input.get("koshitsu") != null && !"".equals(input.get("koshitsu")) && !"(null)".equals(input.get("koshitsu"))){
            cm.setKoshitsu(EscapeUtil.toHtmlStringForObject(input.get("koshitsu")));
        }
        if(input.get("pc") != null && !"".equals(input.get("pc")) && !"(null)".equals(input.get("pc"))){
            cm.setPc(EscapeUtil.toHtmlStringForObject(input.get("pc")));
        }
        if(input.get("wifi") != null && !"".equals(input.get("wifi")) && !"(null)".equals(input.get("wifi"))){
            
            cm.setWifi(EscapeUtil.toHtmlStringForObject(input.get("wifi")));
            
        }
        if(input.get("shinya") != null && !"".equals(input.get("shinya")) && !"(null)".equals(input.get("shinya"))){
            cm.setShinya(EscapeUtil.toHtmlStringForObject(input.get("shinya")));
        }
        if(input.get("terace") != null && !"".equals(input.get("terace")) && !"(null)".equals(input.get("terace"))){
            cm.setTerace(EscapeUtil.toHtmlStringForObject(input.get("terace")));
        }
        if(input.get("pet") != null && !"".equals(input.get("pet")) && !"(null)".equals(input.get("pet"))){
            cm.setPet(EscapeUtil.toHtmlStringForObject(input.get("pet")));
        }
        
        cm.setStoreFlag(EscapeUtil.toHtmlStringForObject(input.get("storeFlag")));
        cm.setUserSendFlag(EscapeUtil.toHtmlStringForObject(input.get("userSendFlag")));
        cm.setDeleteFlag("0");
        cm.setIine("0");
        cm.setUpdateTime(new Date());
        
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(cm);
        tx.commit();
        return cm;
    }


    /**
     * データ更新
     * @param input
     * @throws EntityNotFoundException 
     */
    public void updateData(Map<String, Object> input) throws EntityNotFoundException {
        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        Key cafekey = KeyFactory.createKey("cafeMaster", Long.valueOf((String) input.get("key")));
        Entity cafeEntity = service.get(cafekey);
        
        //イイネは特殊処理
        if(input.containsKey("iine") == true){
            String iineMoto = EscapeUtil.toHtmlStringForObject(cafeEntity.getProperty("iine"));
            if(iineMoto == ""){
                iineMoto = "0";
            }
            int tmpIine = Integer.valueOf(iineMoto);
            tmpIine++;
            
            cafeEntity.setProperty("iine",String.valueOf(tmpIine));
        }
        
        if(input.get("storeName") != null && !"".equals(input.get("storeName")) && !"(null)".equals(input.get("storeName"))){
            cafeEntity.setProperty("storeName",input.get("storeName"));
        }
        if(input.get("storeFullName") != null && !"".equals(input.get("storeFullName")) && !"(null)".equals(input.get("storeFullName"))){
            cafeEntity.setProperty("storeFullName",input.get("storeFullName"));
        }
        if(input.get("storeCaption") != null && !"".equals(input.get("storeCaption")) && !"(null)".equals(input.get("storeCaption"))){
            cafeEntity.setProperty("storeCaption",input.get("storeCaption"));
        }
        if(input.get("storeAddress") != null && !"".equals(input.get("storeAddress")) && !"(null)".equals(input.get("storeAddress"))){
            cafeEntity.setProperty("storeAddress",input.get("storeAddress"));
        }
        if(input.get("phoneNumber") != null && !"".equals(input.get("phoneNumber")) && !"(null)".equals(input.get("phoneNumber"))){
            cafeEntity.setProperty("phoneNumber",input.get("phoneNumber"));
        }
        if(input.get("geohash4") != null && !"".equals(input.get("geohash4")) && !"(null)".equals(input.get("geohash4"))){
            cafeEntity.setProperty("geohash4",input.get("geohash4"));
        }
        if(input.get("geohash5") != null && !"".equals(input.get("geohash5")) && !"(null)".equals(input.get("geohash5"))){
            cafeEntity.setProperty("geohash5",input.get("geohash5"));
        }
        if(input.get("geohash6") != null && !"".equals(input.get("geohash6")) && !"(null)".equals(input.get("geohash6"))){
            cafeEntity.setProperty("geohash6",input.get("geohash6"));
        }
        if(input.get("geohash8") != null && !"".equals(input.get("geohash8")) && !"(null)".equals(input.get("geohash8"))){
            cafeEntity.setProperty("geohash8",input.get("geohash8"));
        }
        if(input.get("geohashAll") != null && !"".equals(input.get("geohashAll")) && !"(null)".equals(input.get("geohashAll"))){
            cafeEntity.setProperty("geohashAll",input.get("geohashAll"));
        }
        if(input.get("geohashAround") != null && !"".equals(input.get("geohashAround")) && !"(null)".equals(input.get("geohashAround"))){
            cafeEntity.setProperty("geohashAround",input.get("geohashAround"));
        }
        if(input.get("lat") != null && !"".equals(input.get("lat")) && !"(null)".equals(input.get("lat"))){
            cafeEntity.setProperty("lat",input.get("lat"));
        }
        if(input.get("lon") != null && !"".equals(input.get("lon")) && !"(null)".equals(input.get("lon"))){
            cafeEntity.setProperty("lon",input.get("lon"));
        }
        if(input.get("zipCode") != null && !"".equals(input.get("zipCode")) && !"(null)".equals(input.get("zipCode"))){
            cafeEntity.setProperty("zipCode",input.get("zipCode"));
        }
        
        
        if(input.containsKey("kinen")){
            if("1".equals(input.get("kinen"))){
                cafeEntity.setProperty("kinen","1");
            }else{
                cafeEntity.setProperty("kinen","0");
            }
        }
        
        if(input.get("storeFlag") != null && !"".equals(input.get("storeFlag")) && !"(null)".equals(input.get("storeFlag"))){
            cafeEntity.setProperty("storeFlag",input.get("storeFlag"));
        }
        if(input.containsKey("tabako")){
            if("1".equals(input.get("tabako"))){
                cafeEntity.setProperty("tabako","1");
            }else{
                cafeEntity.setProperty("tabako","0");
            }
        }
        if(input.containsKey("koshitsu")){
            if("1".equals(input.get("koshitsu"))){
                cafeEntity.setProperty("koshitsu","1");
            }else{
                cafeEntity.setProperty("koshitsu","0");
            }
        }
        if(input.containsKey("pc")){
            if("1".equals(input.get("pc"))){
                cafeEntity.setProperty("pc","1");
            }else{
                cafeEntity.setProperty("pc","0");
            }
        }
        if(input.containsKey("wifi")){
            if("1".equals(input.get("wifi"))){
                cafeEntity.setProperty("wifi","1");
            }else{
                cafeEntity.setProperty("wifi","0");
            }
        }
        if(input.containsKey("shinya")){
            if("1".equals(input.get("shinya"))){
                cafeEntity.setProperty("shinya","1");
            }else{
                cafeEntity.setProperty("shinya","0");
            }
        }
        if(input.containsKey("terace")){
            if("1".equals(input.get("terace"))){
                cafeEntity.setProperty("terace","1");
            }else{
                cafeEntity.setProperty("terace","0");
            }
        }
        if(input.containsKey("pet")){
            if("1".equals(input.get("pet"))){
                cafeEntity.setProperty("pet","1");
            }else{
                cafeEntity.setProperty("pet","0");
            }
        }
        if(input.get("deviceId") != null && !"".equals(input.get("deviceId")) && !"(null)".equals(input.get("deviceId"))){
            cafeEntity.setProperty("deviceId",input.get("deviceId"));
        }
        
        if(input.get("suiteruTime") != null && !"".equals(input.get("suiteruTime")) && !"(null)".equals(input.get("suiteruTime"))){
            cafeEntity.setProperty("suiteruTime",input.get("suiteruTime"));
        }
        if(input.get("userSendFlag") != null && !"".equals(input.get("userSendFlag")) && !"(null)".equals(input.get("userSendFlag"))){
            cafeEntity.setProperty("userSendFlag",input.get("userSendFlag"));
        }
        cafeEntity.setProperty("nickName",input.get("nickName"));
        cafeEntity.setProperty("updateTime", new Date());
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Transaction tx = Datastore.beginTransaction();
        ds.put(cafeEntity);
        tx.commit();
        return;
        
    }


    /**
     * データ削除
     * @param input
     * @throws EntityNotFoundException 
     */
    public void deleteData(Map<String, Object> input) throws EntityNotFoundException {
        // TODO Auto-generated method stub
        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        Key cafekey = KeyFactory.createKey("cafeMaster", Long.valueOf((String) input.get("key")));
        Entity cafeEntity = service.get(cafekey);
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Transaction tx = Datastore.beginTransaction();
        
        //フラグをたてるだけ
        cafeEntity.setProperty("deleteFlag","1");
        cafeEntity.setProperty("updateTime", new Date());
        ds.put(cafeEntity);
        tx.commit();
        return;
        
    }

    /**
     *  1000件削除を実行(1000件しか取得できないため)
     *  全件削除は、データストアの仕様上不可能
     */
    public void deleteAllData() {
        // TODO Auto-generated method stub
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(cmm.getKind());
        query.setKeysOnly();
        List<Entity> list = ds.prepare(query).asList(FetchOptions.Builder.withOffset(0)); // ※DatastoreTimeoutException の可能性がありますが省略しています。
        List<Key> keys = new ArrayList<Key>(1000);
        for (Entity entity : list) {
            keys.add(entity.getKey());
            ds.delete(keys); // batch delete
            keys.clear();
            
        }
        if (keys.size() > 0){
            ds.delete(keys); // batch delete ※DatastoreTimeoutException の可能性がありますが省略しています。
        }
        return;
    }


    /**
     * 削除したデータを復活させる
     * @param input
     * @throws EntityNotFoundException 
     */
    public void hukkatsuData(Map<String, Object> input) throws EntityNotFoundException {
        // TODO Auto-generated method stub
        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        Key cafekey = KeyFactory.createKey("cafeMaster", Long.valueOf((String) input.get("key")));
        Entity cafeEntity = service.get(cafekey);
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Transaction tx = Datastore.beginTransaction();
        
        //フラグをたてるだけ
        cafeEntity.setProperty("deleteFlag","0");
        ds.put(cafeEntity);
        tx.commit();
        return;
        
    }


    /**
     * geoHashのセットされてない人たちを更新
     * @throws ParseException 
     */
    public void putGeoData() throws ParseException {
        // TODO Auto-generated method stub
        
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        
        Query query = new Query(cmm.getKind());
        //query.addFilter("geohash4", Query.FilterOperator.EQUAL, "");
        query.addFilter("geohashAround", Query.FilterOperator.EQUAL, null);
        List<Entity> list = ds.prepare(query).asList(FetchOptions.Builder.withOffset(0)); 
        //System.out.println(list.size());
        for (Entity entity : list) {
            Transaction tx = Datastore.beginTransaction();
            String address = EscapeUtil.toHtmlStringForObject(entity.getProperty("storeAddress"));
            
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
            String[] areaAry = AreaHandle.feedGeoHash(address);
            if(areaAry != null){
                if(areaAry.length == 7){
                    geohashAll = areaAry[0];
                    geohash5 = areaAry[5];
                    geohash4 = areaAry[6];
                    geohash6 = areaAry[1];
                    geohash8 = areaAry[2];
                    lat = Double.valueOf(areaAry[3]);
                    lng = Double.valueOf(areaAry[4]);
                }
            }
            System.out.println(lat);
          //geohashの周りの値を取得
            geohashAround = CafeUtil.geohashAround(geohash6);
            entity.setProperty("lat", lat);
            entity.setProperty("lon", lng);
            entity.setProperty("geohash4", geohash4);
            entity.setProperty("geohash5", geohash5);
            entity.setProperty("geohash6", geohash6);
            entity.setProperty("geohash8", geohash8);
            entity.setProperty("geohashAll", geohashAll);
            entity.setProperty("geohashAround", geohashAround);
            ds.put(entity);
            tx.commit();
        }
        
        return;
    }
    

}
