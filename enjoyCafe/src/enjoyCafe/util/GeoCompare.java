package enjoyCafe.util;

import java.util.Comparator;
import java.util.HashMap;

import com.google.appengine.api.datastore.Entity;

import enjoyCafe.model.cafeMaster;

public class GeoCompare implements Comparator<HashMap>  {

    // デフォルトは降順  
    private boolean order = true;  
     
    public void setOrder(boolean order) {  
        this.order = order;  
    }

    public int compare(HashMap m0, HashMap m1) {  
        if (this.order) {  
         // 昇順  
         return EscapeUtil.toHtmlStringForObject(m0.get("distance")).compareTo(EscapeUtil.toHtmlStringForObject(m1.get("distance")));  
        } else {  
         // 降順  
         return EscapeUtil.toHtmlStringForObject(m1.get("distance")).compareTo(EscapeUtil.toHtmlStringForObject(m0.get("distance")));
        }  
       }  

}
