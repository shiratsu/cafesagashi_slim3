package enjoyCafe.controller.cafemap.csvUpload;

import java.util.ArrayList;

public class StringUtil {

    /**
     * データをスプリット
     * @param lineData
     * @param string
     * @param i 
     * @return
     */
    public static String[] splitData(String lineData, String string, int i) {
        // TODO Auto-generated method stub
        String[] returnAry = null;
        String[] split = lineData.split(",");
        if(split != null){
            if(split.length == 20){
                returnAry = split;
                
            }else{
                int sabun = 20-split.length;
                
                ArrayList<String> tmpList = new ArrayList<String>();
                for(int x=0;x<split.length;x++){
                    tmpList.add(split[x]);
                }
                for(int y = split.length;y<i;y++){
                    tmpList.add(null);
                }
                returnAry = convAry(tmpList);
            }
        }        
        return returnAry;
    }

    /**
     * ArrayList to String[]
     * @param tmpList
     * @return
     */
    private static String[] convAry(ArrayList<String> tmpList) {
        // TODO Auto-generated method stub
        String[] returnAry = new String[tmpList.size()];
        for(int i=0;i<tmpList.size();i++){
            returnAry[i] = tmpList.get(i);
        }
        return returnAry;
    }

}
