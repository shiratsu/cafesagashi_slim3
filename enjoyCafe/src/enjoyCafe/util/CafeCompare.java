package enjoyCafe.util;

import java.util.Comparator;
import java.util.HashMap;

import com.google.appengine.api.datastore.Entity;

import enjoyCafe.model.cafeMaster;

public class CafeCompare implements Comparator<HashMap>  {
    // デフォルトは降順  
    private boolean order = true;  
     
    public void setOrder(boolean order) {  
        this.order = order;  
    }


    public int compare(HashMap m0, HashMap m1) {
        String[] sortKeys = {"iine", "geoHashAll"};
        for (String key : sortKeys) {
            int c = 0;
            if("iine".equals(key)){
                Integer c1;
                Integer c2;
                if(!"".equals(EscapeUtil.toHtmlStringForObject(m1.get(key))) && EscapeUtil.toHtmlStringForObject(m1.get(key)) != null){
                    c1 = new Integer(EscapeUtil.toHtmlStringForObject(m1.get(key)));
                }else{
                    c1 = new Integer(0);
                }
                if(!"".equals(EscapeUtil.toHtmlStringForObject(m0.get(key))) && EscapeUtil.toHtmlStringForObject(m0.get(key)) != null){
                    c2 = new Integer(EscapeUtil.toHtmlStringForObject(m0.get(key)));
                }else{
                    c2 = new Integer(0);
                }    
                c = c1.compareTo(c2);
            }else{
                c = EscapeUtil.toHtmlStringForObject(m0.get(key)).compareTo(EscapeUtil.toHtmlStringForObject(m1.get(key)));
            }
            if (c != 0) {
                return c;
            }
        }
        return 0;
        
    }  

}
