package enjoyCafe.util;

import java.math.BigDecimal;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public class CafeUtil {

    
    /**
     * 雰囲気の平均値を計算
     * @param parameter
     * @param parameter2
     * @param string 
     * @return
     */
    public static String calchunikiAverage(String parameter, String parameter2, String string) {
        // TODO Auto-generated method stub
        String result = null;
        
        if(parameter == null || parameter == ""){
            parameter = "0";
        }
        
        if(parameter2 == null || parameter2 == ""){
            parameter2 = "0";
        }
        //計算を実行
        if(parameter2 != null && 
           string != null &&  
           parameter2 != "" && 
           string != ""
           ){
            int motoCount = Integer.valueOf(parameter);
            int calcCount = Integer.valueOf(parameter2);
            int counter = Integer.valueOf(string);
            int resultCount = ((motoCount*counter)+calcCount)/(counter+1);
            result = String.valueOf(resultCount);           
        }
        return result;
    }

    /**
     * geohash6桁の周りの値を取得
     * @param geohash6
     * @return
     */
    public static String geohashAround(String geohash) {
        // TODO Auto-generated method stub
        String geohashAround = "";
        String geohash1 = "";
        String geohash2 = "";
        String geohash3 = "";
        String geohash4 = "";
        String geohash5 = "";
        String geohash6 = "";
        String geohash7 = "";
        String geohash8 = "";
        //右からスタート
        geohash1 = Geohash.calculateAdjacent(geohash, 0);
        geohash2 = Geohash.calculateAdjacent(geohash1, 2);
        geohash3 = Geohash.calculateAdjacent(geohash2, 1);
        geohash4 = Geohash.calculateAdjacent(geohash3, 1);
        geohash5 = Geohash.calculateAdjacent(geohash4, 3);
        geohash6 = Geohash.calculateAdjacent(geohash5, 3);
        geohash7 = Geohash.calculateAdjacent(geohash6, 0);
        geohash8 = Geohash.calculateAdjacent(geohash7, 0);
        geohashAround = geohash+geohash1+geohash2+geohash3+geohash4+geohash5+geohash6+geohash7+geohash8;
        
        return geohashAround;
    }

    /**
     * 緯度経度の地点からの距離を計算
     * @param cm
     * @param input
     * @return
     */
    public static String calcDistance(Entity cm, Map<String, Object> input) {
        // TODO Auto-generated method stub
        String strDistance = "";
        double pLat = Double.valueOf(EscapeUtil.toHtmlStringForObject(input.get("lat")));
        double pLon = Double.valueOf(EscapeUtil.toHtmlStringForObject(input.get("lon")));
        
        double tmpLat = Double.valueOf(EscapeUtil.toHtmlStringForObject(cm.getProperty("lat")));
        double tmpLon = Double.valueOf(EscapeUtil.toHtmlStringForObject(cm.getProperty("lon")));
        
        double xDistance = pLat-tmpLat;
        double yDistance = pLon-tmpLon;
        double fxabs = Math.abs(xDistance);
        double fyabs = Math.abs(yDistance);
        double distance = Math.sqrt((fxabs * fxabs) + (fyabs * fyabs))*111;
        
        //小数点第２位
        BigDecimal bi = new BigDecimal(String.valueOf(distance));
        //小数第二位で四捨五入
        double k1 = bi.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        strDistance = String.valueOf(k1);
        return strDistance;
    }
    /**
     * 店舗名を返す
     * @param string
     * @param string2
     * @return
     */
    public static Object createStoreName(String string, String string2) {
        // TODO Auto-generated method stub
        String storeName = "";
        
        switch(Integer.valueOf(string2)){
            case 1:
                storeName = "スターバックス "+string;
            break;
            case 2:
                storeName = "ドトール "+string;
            break;
            case 3:
                storeName = "タリーズ "+string;
            break;
            case 4:
                storeName = "サンマルクカフェ "+string;
            break;
            case 5:
                storeName = "エクセルシオール "+string;
            break;
            case 6:
                storeName = "シアトルズベスト "+string;
            break;
            case 7:
                storeName = "ベローチェ "+string;
            break;
            case 8:
                storeName = "カフェドクリエ "+string;
            break;
            case 9:
                storeName = "コメダ珈琲 "+string;
            break;
            case 99:
                storeName = string;
            break;
        
        }
        return (Object )storeName;
    }

    public static String createStoreMainName(String storeFlag) {
        // TODO Auto-generated method stub
     // TODO Auto-generated method stub
        String storeName = "";
        if(!"Z".equals(storeFlag) && !"".equals(storeFlag) && storeFlag != null){
            switch(Integer.valueOf(storeFlag)){
                case 1:
                    storeName = "スターバックス";
                break;
                case 2:
                    storeName = "ドトール";
                break;
                case 3:
                    storeName = "タリーズ";
                break;
                case 4:
                    storeName = "サンマルクカフェ";
                break;
                case 5:
                    storeName = "エクセルシオール";
                break;
                case 6:
                    storeName = "シアトルズベスト";
                break;
                case 7:
                    storeName = "ベローチェ";
                break;
                case 8:
                    storeName = "カフェドクリエ";
                break;
                case 9:
                    storeName = "コメダ珈琲";
                break;
                
            
            }
        }
        return storeName;
    }
    
}
