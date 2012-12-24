package enjoyCafe.util;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;



import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class AreaHandle {

    /**
     * エリア関連のパラメータ変更
     * @param address
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
	public static String[] feedGeoHash(String address){
		String[] returnAry = null;
		String geoHash = null;
		String geohash4 = null;
		String geohash5 = null;
		String geohash6 = null;
		String geohash8 = null;
		double lat = 0;
		double lng = 0;
		geoCodeHandle gch = new geoCodeHandle();
		//Hashtable latlngAry = gch.getLatLon(address);
		//HashMap<String,String> latlngAry =gch.feedLatLon(address);
		HashMap<String,String> latlngAry = gch.feedLatLon2(address);
		
		if(latlngAry != null){
    		if(latlngAry.get("lat") != null){
    			lat = Double.valueOf((String )latlngAry.get("lat"));
    		}
    		if(latlngAry.get("lng") != null){
    			lng = Double.valueOf((String )latlngAry.get("lng"));
    		}
		}
		//geoHashを取得(geohashAll)
		geoHash = Geohash.encode(lat, lng);
//System.out.println(geoHash);
		if(geoHash != null){
		    //６桁と８桁のものを取得
		    String[] geoAry = geoHash.split("");
		    geohash4 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4];
		    geohash5 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5];
		    geohash6 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6];
		    geohash8 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6]+geoAry[7]+geoAry[8];
		}
		
		if(geoHash != null && lat != 0 && lng != 0){
			returnAry = new String[]{geoHash,geohash6,geohash8,String.valueOf(lat),String.valueOf(lng),geohash5,geohash4};
		}
		return returnAry;
	}

	/**
	 * 緯度経度から、エリア関連情報を取得
	 * @param latStr
	 * @param lonStr
	 * @return
	 */
    public static String[] feedGeoHashForLatLon(String latStr, String lonStr) {
        String[] returnAry = null;
        String geoHash = null;
        String geohash4 = null;
        String geohash5 = null;
        String geohash6 = null;
        String geohash8 = null;
        double lat = 0;
        double lng = 0;
        geoCodeHandle gch = new geoCodeHandle();
        HashMap<String,String> latlngAry = new HashMap<String,String>();
        
        if(latlngAry != null){
            if(latStr != null){
                lat = Double.valueOf(latStr);
            }
            if(lonStr != null){
                lng = Double.valueOf(lonStr);
            }
        }
        //geoHashを取得(geohashAll)
        geoHash = Geohash.encode(lat, lng);
//System.out.println(geoHash);
        if(geoHash != null){
            //６桁と８桁のものを取得
            String[] geoAry = geoHash.split("");
            geohash4 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4];
            geohash5 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5];
            geohash6 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6];
            geohash8 = geoAry[1]+geoAry[2]+geoAry[3]+geoAry[4]+geoAry[5]+geoAry[6]+geoAry[7]+geoAry[8];
        }
        
        if(geoHash != null && lat != 0 && lng != 0){
            returnAry = new String[]{geoHash,geohash6,geohash8,String.valueOf(lat),String.valueOf(lng),geohash5,geohash4};
        }
        return returnAry;
    
    }

	
}
