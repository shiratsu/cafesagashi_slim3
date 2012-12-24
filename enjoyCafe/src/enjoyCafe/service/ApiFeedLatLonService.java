package enjoyCafe.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import enjoyCafe.util.AreaHandle;

public class ApiFeedLatLonService {

    public Map<String, Object> position;

    MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
    
    public void feedLatLon(Map<String, Object> input) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        position = new HashMap<String, Object>();
        
        String cacheKey = createCacheKey(input);
        //キーに値が入ってたら
        if(memcache.contains(cacheKey)){
            //System.out.println("cache");
            //System.out.println(cacheKey);
            input = (Map<String, Object>) memcache.get(cacheKey);
            if(input.containsKey("lat")){
                return;
            }    
        }
        
        createGeoPosition(input);
        return;
    }

    private String createCacheKey(Map<String, Object> input) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        String cacheKey = null;
        if(input.get("pointName") != null){
            String pointName = (String) input.get("pointName");
            cacheKey = "point="+URLEncoder.encode(pointName,"utf-8");
        }
        return cacheKey;
    }

    /**
     * 緯度経度から位置情報を取得
     * @param input
     */
    private void createGeoPosition(Map<String, Object> input) {
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
        return;
    }

}
