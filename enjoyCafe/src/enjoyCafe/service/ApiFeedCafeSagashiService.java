package enjoyCafe.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

import enjoyCafe.meta.cafeMasterMeta;
import enjoyCafe.util.AreaHandle;
import enjoyCafe.util.CafeCompare;
import enjoyCafe.util.CafeUtil;
import enjoyCafe.util.EscapeUtil;
import enjoyCafe.util.GeoCompare;
import enjoyCafe.util.NiceCompare;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class ApiFeedCafeSagashiService {

    private cafeMasterMeta cmm = new cafeMasterMeta();
    public Map<String,Object> position = null;
    
    MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
    
    public Map<String, Object> getPosition() {
        return position;
    }

    public void setPosition(Map<String, Object> position) {
        this.position = position;
    }

    public List<Entity> feedApiCafeList(Map<String, Object> input)  throws ParserConfigurationException, SAXException, IOException{
      //array define
        List<Entity> list = new ArrayList<Entity>();
        position = input;
        Query query = new Query(cmm.getKind());
        // filter
        
        //error
        if(input.containsKey("positionFlag") != true && input.containsKey("nowPointFlag") != true && input.containsKey("geohashFlag") != true){
            return list;
        }
        
        //where
        //position query
        if("1".equals(input.get("positionFlag")) && input.get("pointName") != ""){
            query = createGeoPosition(query,input);
        }
        
        //now search
        if("1".equals(input.get("nowPointFlag")) && input.get("lat") != "" && input.get("lon") != ""){
            query = createNowPosition(query,input);
        }
 
        //kodawari
        query = createKodawariWhere(query,input);
        //suiteru
        query = createSuiteruWhere(query,input);
        
        //query = createHuniki(query,input);
        
        
        
        if(!"1".equals(input.get("storeFlag1")) && 
           !"1".equals(input.get("storeFlag2")) &&
           !"1".equals(input.get("storeFlag3")) &&
           !"1".equals(input.get("storeFlag4")) &&
           !"1".equals(input.get("storeFlag5")) && 
           !"1".equals(input.get("storeFlag6")) &&
           !"1".equals(input.get("storeFlag7")) &&
           !"1".equals(input.get("storeFlag8")) &&
           !"1".equals(input.get("storeFlag9")) &&
           !"1".equals(input.get("storeFlagZ")) && 
           input.containsKey("storeFlagFrom") != true && 
           input.containsKey("storeFlagTo") != true &&
           input.containsKey("storeFlagOk") != true
        ){
            //all
            list = feedCafeListAll(query,list,input);
        }else{
            //fromTo not
            if(input.containsKey("storeFlagFrom") != true && input.containsKey("storeFlagTo") != true){
                //storeFlag = 1
                if("1".equals(input.get("storeFlag1"))){
                    list = feedCafeListOnly(query,input,list,"1");
                }
                //storeFlag = 2
                if("1".equals(input.get("storeFlag2"))){
                    
                    list = feedCafeListOnly(query,input,list,"2");
                }
                //storeFlag = 3
                if("1".equals(input.get("storeFlag3"))){
                    list = feedCafeListOnly(query,input,list,"3");
                }
                //storeFlag = 4
                if("1".equals(input.get("storeFlag4"))){
                    list = feedCafeListOnly(query,input,list,"4");
                }
                //storeFlag = 5
                if("1".equals(input.get("storeFlag5"))){
        
                    list = feedCafeListOnly(query,input,list,"5");
                }
                //storeFlag = 6
                if("1".equals(input.get("storeFlag6"))){
                    list = feedCafeListOnly(query,input,list,"6");
                }
                //storeFlag = 7
                if("1".equals(input.get("storeFlag7"))){
                    list = feedCafeListOnly(query,input,list,"7");
                }
                //storeFlag = 8
                if("1".equals(input.get("storeFlag8"))){
                    list = feedCafeListOnly(query,input,list,"8");
                }
                //storeFlag = 9
                if("1".equals(input.get("storeFlag9"))){
                    list = feedCafeListOnly(query,input,list,"9");
                }
                //店舗フラグが99なら
                if("1".equals(input.get("storeFlagZ"))){
                    list = feedCafeListOnly(query,input,list,"Z");
                }
            }else{
                //storeFlag from to
                if(input.get("storeFlagFrom") != "" || input.get("storeFlagTo") != ""){
                    list = feedCafeFromTo(query,list,input);
                }
            }
        }
        
        return list;
    }
    /**
     * where句を作成
     * @param query
     * @param input
     * @return
     */
    private Query createKodawariWhere(Query query, Map<String, Object> input) {
        //たばこ
        //タバコ
        if("1".equals(input.get("tabako"))){
            query.addFilter("tabako",Query.FilterOperator.EQUAL, "1");
        }
        //禁煙
        if("1".equals(input.get("kinen"))){
            query.addFilter("kinen",Query.FilterOperator.EQUAL, "1");
        }
        //wifiあり
        if("1".equals(input.get("wifi"))){
            query.addFilter("wifi",Query.FilterOperator.EQUAL, "1");
        }
        //PC
        if("1".equals(input.get("pc"))){
            query.addFilter("pc",Query.FilterOperator.EQUAL, "1");
        }
        //深夜営業あり
        if("1".equals(input.get("shinya"))){
            query.addFilter("shinya",Query.FilterOperator.EQUAL, "1");
        }
        //テラス
        if("1".equals(input.get("terace"))){
            query.addFilter("terace",Query.FilterOperator.EQUAL, "1");
        }
        //ペット
        if("1".equals(input.get("pet"))){
            query.addFilter("pet",Query.FilterOperator.EQUAL, "1");
        }
        return query;
    }

    /**
     * 雰囲気のwhere句を作成
     * @param query
     * @param input
     * @return
     */    
    private Query createHuniki(Query query, Map<String, Object> input) {
        // TODO Auto-generated method stub
        //デート向き
        if(input.get("dateMuki") != null && input.get("dateMuki") != "" && "1".equals(input.get("dateMuki"))){
            query.addFilter("dateMuki", Query.FilterOperator.EQUAL, input.get("dateMuki"));
        }
        //友人とまったり
        if(input.get("friendMattari") != null && input.get("friendMattari") != "" && "1".equals(input.get("friendMattari"))){
            query.addFilter("friendMattari", Query.FilterOperator.EQUAL, input.get("friendMattari"));
        }
        //多人数オッケー
        if(input.get("manyPerson") != null && input.get("manyPerson") != "" && "1".equals(input.get("manyPerson"))){
            query.addFilter("manyPerson", Query.FilterOperator.EQUAL, input.get("manyPerson"));
            
        }
        
        //ひとりでまったり
        if(input.get("only") != null && input.get("only") != "" && "1".equals(input.get("only"))){
            query.addFilter("only", Query.FilterOperator.EQUAL, input.get("only"));
        }
        
        return query;
    }

    /**
     * すいてる時間検索
     * @param query
     * @param input
     * @return
     */
    private Query createSuiteruWhere(Query query, Map<String, Object> input) {
      //時間帯検索
        //すいてる0to1
        if("1".equals(input.get("suiteru0to1"))){
            query.addFilter("suiteru0to1",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる1to2
        if("1".equals(input.get("suiteru1to2"))){
            query.addFilter("suiteru1to2",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる2to3
        if("1".equals(input.get("suiteru2to3"))){
            query.addFilter("suiteru2to3",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる3to4
        if("1".equals(input.get("suiteru3to4"))){
            query.addFilter("suiteru3to4",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる4to5
        if("1".equals(input.get("suiteru4to5"))){
            query.addFilter("suiteru4to5",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる5to6
        if("1".equals(input.get("suiteru5to6"))){
            query.addFilter("suiteru5to6",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる6to7
        if("1".equals(input.get("suiteru6to7"))){
            query.addFilter("suiteru6to7",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる7to8
        if("1".equals(input.get("suiteru7to8"))){
            query.addFilter("suiteru7to8",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる8to9
        if("1".equals(input.get("suiteru8to9"))){
            query.addFilter("suiteru8to9",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる9to10
        if("1".equals(input.get("suiteru9to10"))){
            query.addFilter("suiteru9to10",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる10to11
        if("1".equals(input.get("suiteru10to11"))){
            query.addFilter("suiteru10to11",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる11to12
        if("1".equals(input.get("suiteru11to12"))){
            query.addFilter("suiteru11to12",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる12to13
        if("1".equals(input.get("suiteru12to13"))){
            query.addFilter("suiteru12to13",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる13to14
        if("1".equals(input.get("suiteru13to14"))){
            query.addFilter("suiteru13to14",Query.FilterOperator.EQUAL, "1");
        }
        
        //すいてる14to15
        if("1".equals(input.get("suiteru14to15"))){
            query.addFilter("suiteru14to15",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる15to16
        if("1".equals(input.get("suiteru15to16"))){
            query.addFilter("suiteru15to16",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる16to17
        if("1".equals(input.get("suiteru16to17"))){
            query.addFilter("suiteru16to17",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる17to18
        if("1".equals(input.get("suiteru17to18"))){
            query.addFilter("suiteru17to18",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる18to19
        if("1".equals(input.get("suiteru18to19"))){
            query.addFilter("suiteru18to19",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる19to20
        if("1".equals(input.get("suiteru19to20"))){
            query.addFilter("suiteru19to20",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる20to21
        if("1".equals(input.get("suiteru20to21"))){
            query.addFilter("suiteru20to21",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる21to22
        if("1".equals(input.get("suiteru21to22"))){
            query.addFilter("suiteru21to22",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる22to23
        if("1".equals(input.get("suiteru22to23"))){
            query.addFilter("suiteru22to23",Query.FilterOperator.EQUAL, "1");
        }
        //すいてる23to24
        if("1".equals(input.get("suiteru23to24"))){
            query.addFilter("suiteru23to24",Query.FilterOperator.EQUAL, "1");
        }
        return query;
    }


    /**
     * storeFlag gentei
     * @param query
     * @param list
     * @param input
     * @return
     */
    private List<Entity> feedCafeFromTo(Query query, List<Entity> list,
            Map<String, Object> input) {
        // TODO Auto-generated method stub
        
        List<Entity> listFromTo = new ArrayList<Entity>();
        
        query.addFilter("storeFlag", Query.FilterOperator.GREATER_THAN_OR_EQUAL, (String) input.get("storeFlagFrom"));
        query.addFilter("storeFlag",Query.FilterOperator.LESS_THAN_OR_EQUAL, (String) input.get("storeFlagTo"));
        
        if("1".equals(input.get("deleteFlag"))){
            query.addFilter("deleteFlag", Query.FilterOperator.EQUAL, "1");
        }else{
            //default delete = 0
            query.addFilter("deleteFlag", Query.FilterOperator.EQUAL, "0");
        }
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        
        
        listFromTo = ds.prepare(query).asList(FetchOptions.Builder.withOffset(0).limit(1000));
        //for iPhone list
        if(input.containsKey("listFlag")){
            list = loopListData(listFromTo,list,input);
        }else{
            list = loopData(listFromTo,list,input);
        }
        return list;
    }
    
    
    /**
     * for detail
     * @param query 
     * @param input
     * @param list
     * @param string
     * @return
     */
    private List<Entity> feedCafeListOnly(Query query, Map<String, Object> input,
            List<Entity> list, String string) {
        // TODO Auto-generated method stub
        List<Entity> listOnly = new ArrayList<Entity>();
        query.addFilter("storeFlag", Query.FilterOperator.EQUAL, string);
        
        //delete set
        if("1".equals(input.get("deleteFlag"))){
            query.addFilter("deleteFlag", Query.FilterOperator.EQUAL, "1");
        }else{
            //default:deleteFlag = 0
            query.addFilter("deleteFlag", Query.FilterOperator.EQUAL, "0");
        }
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        /*
        int offset = 0;
        int limit = 0;
        if(input.get("offset") != null && input.get("offset") != ""){
            offset=Integer.valueOf((String) input.get("offset"));
        }
        
        if(input.get("limit") != null && input.get("limit") != ""){
            limit=Integer.valueOf((String) input.get("limit"));
        }
        */
        //sort
        //query = createSort(query,input);
        
        listOnly = ds.prepare(query).asList(FetchOptions.Builder.withOffset(0).limit(1000));
        //GeoCompare comparator = new GeoCompare();
        
        //GeohashCompare comparator = new GeohashCompare();
        //Collections.sort(listOnly, comparator );
        
        //data loop
        //iPhone for list base info
        if(input.containsKey("listFlag")){
            list = loopListData(listOnly,list,input);
        }else{
            list = loopData(listOnly,list,input);
        }
        
        return list;
    }
    
    
    /**
     * iPhone for list
     * @param listOnly
     * @param list
     * @param input 
     * @return
     */
    private List<Entity> loopListData(List<Entity> listData, List<Entity> list, Map<String, Object> input) {
        // TODO Auto-generated method stub
        String checkParam = "";
        String checkgeohash6 = "";
        checkgeohash6 = EscapeUtil.toHtmlStringForObject(input.get("geohash6"));
        if(input.containsKey("storeFlagOk") && input.get("storeFlagOk") != ""){
            checkParam = EscapeUtil.toHtmlStringForObject(input.get("storeFlagOk"));
        }
        if(checkParam == ""){
            for(Entity cm : listData){
                boolean suiteruOk = true;
                String check2 = EscapeUtil.toHtmlStringForObject(cm.getProperty("geohashAround"));
                if(check2.indexOf(checkgeohash6) != -1){
                    //now suiteru
                    if(input.get("suiteruFrom") != null && input.get("suiteruFrom") != "" && input.get("suiteruTo") != null && input.get("suiteruTo") != ""){
                        String suiteruFromStr = (String) input.get("suiteruFrom");
                        int suiteruFrom = Integer.valueOf(suiteruFromStr);
                        String suiteruToStr = (String) input.get("suiteruTo");
                        int suiteruTo = Integer.valueOf(suiteruToStr);
                        int suiteruCheck = 0;
                        String suiteruCheckStr = (String) cm.getProperty("suiteruTime");
                        if(suiteruCheckStr != null){
                            suiteruCheck = Integer.valueOf(suiteruCheckStr);
                        }else{
                            suiteruCheck = 0;
                        }
                        if(suiteruFrom > suiteruCheck || suiteruTo < suiteruCheck){
                            suiteruOk = false;
                        }   
                    }
                    if(suiteruOk == true){
                        list.add(cm);
                    }
                }
            }
        }else{
            for(Entity cm : listData){
                boolean suiteruOk = true;
                String check = EscapeUtil.toHtmlStringForObject(cm.getProperty("storeFlag"));
                if(checkParam.indexOf(check) != -1){
                    String check2 = EscapeUtil.toHtmlStringForObject(cm.getProperty("geohashAround"));
                    if(check2.indexOf(checkgeohash6) != -1){
                        
                        //now suiteru
                        if(input.get("suiteruFrom") != null && input.get("suiteruFrom") != "" && input.get("suiteruTo") != null && input.get("suiteruTo") != ""){
                            String suiteruFromStr = (String) input.get("suiteruFrom");
                            int suiteruFrom = Integer.valueOf(suiteruFromStr);
                            String suiteruToStr = (String) input.get("suiteruTo");
                            int suiteruTo = Integer.valueOf(suiteruToStr);
                            int suiteruCheck = 0;
                            String suiteruCheckStr = (String) cm.getProperty("suiteruTime");
                            if(suiteruCheckStr != null){
                                suiteruCheck = Integer.valueOf(suiteruCheckStr);
                            }else{
                                suiteruCheck = 0;
                            }
                            if(suiteruFrom > suiteruCheck || suiteruTo < suiteruCheck){
                                suiteruOk = false;
                            }   
                        }
                        if(suiteruOk == true){
                            list.add(cm);
                        }
                    }
                }
            }
        }
        return list;
    }
    
    
    /**
     * now Position query
     * @param query
     * @param input
     * @return
     */
    private Query createNowPosition(Query query, Map<String, Object> input) {
        
        //feed area data
        String geohash4 = "";
        String geohash6 = "";
        String geohash5 = "";
        String geohash8 = "";
        String geohashAll = "";
        
        String latStr = EscapeUtil.toHtmlStringForObject(input.get("lat"));
        String lonStr = EscapeUtil.toHtmlStringForObject(input.get("lon"));
        
        //latlon from address
        double lat = 0;
        double lng = 0;
        
        //area data from db
        if(latStr != "" && lonStr != ""){
            String[] areaAry = AreaHandle.feedGeoHashForLatLon(latStr,lonStr);
            if(areaAry.length == 7){
                geohashAll = areaAry[0];
                geohash6 = areaAry[1];
                geohash5 = areaAry[5];
                geohash4 = areaAry[6];
                geohash8 = areaAry[2];
                lat = Double.valueOf(areaAry[3]);
                lng = Double.valueOf(areaAry[4]);
                input.put("geohash6", areaAry[1]);
                query.addFilter("geohash4", Query.FilterOperator.EQUAL, geohash4);
            }
        }
        position.put("lat", latStr);
        position.put("lon", lonStr);
        position.put("geohash4", geohash4);
        position.put("geohash5", geohash5);
        position.put("geohash6", geohash6);
        position.put("geohash8", geohash8);
        position.put("geohashAll", geohashAll);
        return query;
    }

    
    /**
     * geoCode6 
     * @param query
     * @param input
     * @return
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     */
    private Query createGeoPosition(Query query, Map<String, Object> input) throws ParserConfigurationException, SAXException, IOException {
        // TODO Auto-generated method stub
        
        //get areadata
        String geohash4 = null;
        String geohash6 = null;
        String geohash8 = null;
        String geohash5 = null;
        String geohashAll = null;
        
        //lat lon from address
        double lat = 0;
        double lng = 0;
        
        //area data
        if(input.get("pointName") != null){
            String[] areaAry = AreaHandle.feedGeoHash((String) input.get("pointName"));
            if(areaAry != null){
                if(areaAry.length == 7){
                    geohashAll = areaAry[0];
                    geohash6 = areaAry[1];
                    geohash5 = areaAry[5];
                    geohash4 = areaAry[6];
                    geohash8 = areaAry[2];
                    lat = Double.valueOf(areaAry[3]);
                    lng = Double.valueOf(areaAry[4]);
                    input.put("geohash6", areaAry[1]);
                    input.put("lat", lat);
                    input.put("lon", lng);
                    query.addFilter("geohash4", Query.FilterOperator.EQUAL, geohash4);
                }
            }
        }
        position.put("lat", lat);
        position.put("lon", lng);
        position.put("geohash4", geohash4);
        position.put("geohash5", geohash5);
        position.put("geohash6", geohash6);
        position.put("geohash8", geohash8);
        position.put("geohashAll", geohashAll);
        return query;
    }

    /**
     * 返却用配列を作成
     * @param listData
     * @param list
     * @param input 
     * @return
     */
    private List<Entity> loopData(List<Entity> listData, List<Entity> list, Map<String, Object> input) {
        
        
        String checkParam = "";
        String checkgeohash6 = "";
        
        checkgeohash6 = EscapeUtil.toHtmlStringForObject(input.get("geohash6"));
        if(input.containsKey("storeFlagOk") && input.get("storeFlagOk") != ""){
            checkParam = EscapeUtil.toHtmlStringForObject(input.get("storeFlagOk"));
        }
        
        // TODO Auto-generated method stub
        if(checkParam == ""){
  
            for(Entity cm : listData){
                boolean suiteruOk = true;
                String check2 = EscapeUtil.toHtmlStringForObject(cm.getProperty("geohashAround"));
                if(check2.indexOf(checkgeohash6) != -1){
                    
                    
                    //今すいてる
                    if(input.get("suiteruFrom") != null && input.get("suiteruFrom") != "" && input.get("suiteruTo") != null && input.get("suiteruTo") != ""){
                        String suiteruFromStr = (String) input.get("suiteruFrom");
                        int suiteruFrom = Integer.valueOf(suiteruFromStr);
                        String suiteruToStr = (String) input.get("suiteruTo");
                        int suiteruTo = Integer.valueOf(suiteruToStr);
                        
                        int suiteruCheck = 0;
                        String suiteruCheckStr = (String) cm.getProperty("suiteruTime");
                        if(suiteruCheckStr != null){
                            suiteruCheck = Integer.valueOf(suiteruCheckStr);
                        }else{
                            suiteruCheck = 0;
                        }
                        if(suiteruFrom > suiteruCheck || suiteruTo < suiteruCheck){
                            suiteruOk = false;
                        }   
                    }
                    if(suiteruOk == true){
                        list.add(cm);
                    }
                }
            }
        }else{
            for(Entity cm : listData){
                boolean suiteruOk = true;
                String check = EscapeUtil.toHtmlStringForObject(cm.getProperty("storeFlag"));
                if(checkParam.indexOf(check) != -1){
                    String check2 = EscapeUtil.toHtmlStringForObject(cm.getProperty("geohashAround"));
                    if(check2.indexOf(checkgeohash6) != -1){
                        
                        //今すいてる
                        if(input.get("suiteruFrom") != null && input.get("suiteruFrom") != "" && input.get("suiteruTo") != null && input.get("suiteruTo") != ""){
                            String suiteruFromStr = (String) input.get("suiteruFrom");
                            int suiteruFrom = Integer.valueOf(suiteruFromStr);
                            String suiteruToStr = (String) input.get("suiteruTo");
                            int suiteruTo = Integer.valueOf(suiteruToStr);
                            
                            int suiteruCheck = 0;
                            String suiteruCheckStr = (String) cm.getProperty("suiteruTime");
                            if(suiteruCheckStr != null){
                                suiteruCheck = Integer.valueOf(suiteruCheckStr);
                            }else{
                                suiteruCheck = 0;
                            }
                            if(suiteruFrom > suiteruCheck || suiteruTo < suiteruCheck){
                                suiteruOk = false;
                            }   
                        }
                        if(suiteruOk == true){
                            list.add(cm);
                        }    
                    }
                }
            }
        }
        return list;
    }
    
    
    /**
     * カフェ一覧すべてを取得
     * @param query 
     * @param list
     * @param input 
     * @return
     */
    private List<Entity> feedCafeListAll(Query query, List<Entity> list, Map<String, Object> input) {
        
        List<Entity> listAll = new ArrayList<Entity>();
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        
        //削除フラグがセットされていた場合
        if("1".equals(input.get("deleteFlag"))){
            query.addFilter("deleteFlag", Query.FilterOperator.EQUAL, "1");
        }else{
            //デフォルトは、削除フラグ１以外一覧
            query.addFilter("deleteFlag", Query.FilterOperator.EQUAL, "0");
        }
        
        if("1".equals(input.get("geohashNotFlag"))){
            query.addFilter("geohash6", Query.FilterOperator.EQUAL, "");
        }

        
        return listAll;
    }
    
    /**
     * メムキャッシュのキーを作成
     * @param input
     * @return
     */
    private String createCacheKey(Map<String, Object> input) {
        // TODO Auto-generated method stub
        String geohash6 = (String) input.get("geohash6");
        String tabako = null;
        String kinen = null;
        String koshitsu = null;
        String wifi = null;
        String pc = null;
        String shinya = null;
        String terace = null;
        String pet = null;
        String storeFlag1 = null;
        String storeFlag2 = null;
        String storeFlag3 = null;
        String storeFlag4 = null;
        String storeFlag5 = null;
        String storeFlag6 = null;
        String storeFlag7 = null;
        String storeFlag8 = null;
        String storeFlag9 = null;
        String storeFlagZ = null;
        String nice = null;
        String suiteruNow = null;
        String cacheKey = null;
        if("1".equals(input.get("tabako"))){
            tabako = "1";
        }
        if("1".equals(input.get("kinen"))){
            kinen = "1";
        }
        if("1".equals(input.get("koshitsu"))){
            koshitsu = "1";
        }
        if("1".equals(input.get("wifi"))){
            wifi = "1";
        }
        if("1".equals(input.get("pc"))){
            pc = "1";
        }
        if("1".equals(input.get("shinya"))){
            shinya = "1";
        }
        if("1".equals(input.get("terace"))){
            terace = "1";
        }
        if("1".equals(input.get("pet"))){
            pet = "1";
        }
        if("1".equals(input.get("storeFlag1"))){
            storeFlag1 = "1";
        }
        if("1".equals(input.get("storeFlag2"))){
            storeFlag2 = "1";
        }
        if("1".equals(input.get("storeFlag3"))){
            storeFlag3 = "1";
        }
        if("1".equals(input.get("storeFlag4"))){
            storeFlag4 = "1";
        }
        if("1".equals(input.get("storeFlag5"))){
            storeFlag5 = "1";
        }
        if("1".equals(input.get("storeFlag6"))){
            storeFlag6 = "1";
        }
        if("1".equals(input.get("storeFlag7"))){
            storeFlag7 = "1";
        }
        if("1".equals(input.get("storeFlag8"))){
            storeFlag8 = "1";
        }
        if("1".equals(input.get("storeFlag9"))){
            storeFlag9 = "1";
        }
        if("1".equals(input.get("storeFlagZ"))){
            storeFlagZ = "1";
        }
        if("1".equals(input.get("nice"))){
            nice = "1";
        }
        if("1".equals(input.get("suiteruNow"))){
            suiteruNow = "1";
        }
        cacheKey = "geohash6="+geohash6+"&tabako="+tabako+"&kinen="+kinen+"&koshitsu="+koshitsu+"&wifi="+wifi+
                    "&pc="+pc+"&shinya="+shinya+"&terace="+terace+"&pet="+pet+"&storeFlag1="+storeFlag1+
                    "&storeFlag2="+storeFlag2+"&storeFlag3="+storeFlag3+"&storeFlag4="+storeFlag4+
                    "&storeFlag5="+storeFlag5+"&storeFlag6="+storeFlag6+"&storeFlag7="+storeFlag7+
                    "&storeFlag8="+storeFlag8+"&storeFlag9="+storeFlag9+"&storeFlagZ="+storeFlagZ+
                    "&nice="+nice+"&suiteruNow="+suiteruNow;
        
        return cacheKey;
    }
    
}
