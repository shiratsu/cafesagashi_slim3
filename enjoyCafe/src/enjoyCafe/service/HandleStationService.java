package enjoyCafe.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

import enjoyCafe.meta.areaDataMeta;
import enjoyCafe.util.EscapeUtil;




public class HandleStationService {

    private areaDataMeta adm = new areaDataMeta();
    /**
     * 駅一覧を取得
     * @param input
     * @return
     */
    public List<HashMap> feedStationList(Map<String, Object> input) {
        // TODO Auto-generated method stub
        
        List<Entity> listStation = null;
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        
        //クエリを初期化
        Query query = new Query(adm.getKind());
        
        //where句を作成
        //場所のクエリを検索
        if(input.get("stationName") != "" && input.containsKey("stationName")){
            query = createGeoPosition(query,input);
        }
        
        int offset = 0;
        int limit = 100;
        //鉄道会社コードでソート
        query.addSort("stationName");
        listStation = ds.prepare(query).asList(FetchOptions.Builder.withOffset(offset).limit(limit));
        
        //返却用の配列を定義
        List<HashMap> list = new ArrayList<HashMap>();
        
        list = createListData(listStation,list);
        
        return list;
    }
    
    /**
     * 返却用のデータを作成
     * @param listStation
     * @param list
     * @return
     */
    private List<HashMap> createListData(List<Entity> listStation,
            List<HashMap> list) {
        // TODO Auto-generated method stub
        String tmpCode = "";
        
        //鉄道会社が違うものだけを返却用として定義
        for(Entity ad : listStation){
            if(!tmpCode.equals(EscapeUtil.toHtmlStringForObject(ad.getProperty("prefCode")))){
                HashMap<String,String> areaData = new HashMap<String,String>();
                areaData.put("key", EscapeUtil.toHtmlString(String.valueOf(ad.getKey().getId())));
                areaData.put("rcCode", EscapeUtil.toHtmlStringForObject(ad.getProperty("rcCode")));
                areaData.put("lineCode", EscapeUtil.toHtmlStringForObject(ad.getProperty("lineCode")));
                areaData.put("stationCode", EscapeUtil.toHtmlStringForObject(ad.getProperty("stationCode")));
                areaData.put("lineSort", EscapeUtil.toHtmlStringForObject(ad.getProperty("lineSort")));
                areaData.put("stationSort", EscapeUtil.toHtmlStringForObject(ad.getProperty("stationSort")));
                areaData.put("stationGroup", EscapeUtil.toHtmlStringForObject(ad.getProperty("stationGroup")));
                areaData.put("rcType", EscapeUtil.toHtmlStringForObject(ad.getProperty("rcType")));
                areaData.put("rcName", EscapeUtil.toHtmlStringForObject(ad.getProperty("rcName")));
                areaData.put("lineName", EscapeUtil.toHtmlStringForObject(ad.getProperty("lineName")));
                areaData.put("stationName", EscapeUtil.toHtmlStringForObject(ad.getProperty("stationName")));
                areaData.put("searchStationName", EscapeUtil.toHtmlStringForObject(ad.getProperty("searchStationName")));
                areaData.put("prefCode", EscapeUtil.toHtmlStringForObject(ad.getProperty("prefCode")));
                areaData.put("lat", EscapeUtil.toHtmlStringForObject(ad.getProperty("lat")));
                areaData.put("lon", EscapeUtil.toHtmlStringForObject(ad.getProperty("lon")));
                areaData.put("geohash4", EscapeUtil.toHtmlStringForObject(ad.getProperty("geohash4")));
                areaData.put("geohash5", EscapeUtil.toHtmlStringForObject(ad.getProperty("geohash5")));
                areaData.put("geohash6", EscapeUtil.toHtmlStringForObject(ad.getProperty("geohash6")));
                areaData.put("geohash8", EscapeUtil.toHtmlStringForObject(ad.getProperty("geohash8")));
                areaData.put("geohashAll", EscapeUtil.toHtmlStringForObject(ad.getProperty("geohashAll")));
                areaData.put("geohashAround", EscapeUtil.toHtmlStringForObject(ad.getProperty("geohashAround")));
                areaData.put("updateDate", EscapeUtil.toHtmlStringForObject(ad.getProperty("updateDate")));
                list.add(areaData);
                tmpCode = EscapeUtil.toHtmlStringForObject(ad.getProperty("prefCode"));
            }
        }
        return list;
    }

    /**
     * 駅系を取得
     * @param query
     * @param input
     * @return
     */
    private Query createGeoPosition(Query query, Map<String, Object> input) {
        // TODO Auto-generated method stub
        query.addFilter("stationName", Query.FilterOperator.GREATER_THAN_OR_EQUAL, (String) input.get("stationName"));
        query.addFilter("stationName",Query.FilterOperator.LESS_THAN, (String) input.get("stationName")+ "\ufffd"); // "\ufffd"はUNICODEの最大値
        
        return query;
    }
    
    /**
     *  1000件削除を実行(1000件しか取得できないため)
     *  全件削除は、データストアの仕様上不可能
     */
    public void deleteAllData() {
        // TODO Auto-generated method stub
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(adm.getKind());
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

}
