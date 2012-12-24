package enjoyCafe.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

class HandleGoogleApi extends DefaultHandler{
    private int tab=0;

    public String latLonStr = null;
    public HashMap areaAry = new HashMap();
    public ArrayList<HashMap> list = new ArrayList<HashMap>();
    public HashMap getAreaAry() {
        return areaAry;
    }

    public void setAreaAry(HashMap areaAry) {
        this.areaAry = areaAry;
    }

    public ArrayList<HashMap> getList() {
        return list;
    }

    public void setList(ArrayList<HashMap> list) {
        this.list = list;
    }

    public String getLatLonStr() {
        return latLonStr;
    }

    public void setLatLonStr(String latLonStr) {
        this.latLonStr = latLonStr;
    }

    private String currentTag;

    /* XML文書の開始時の処理*/
    public void startDocument(){
        //System.out.println("Here is start of XML document.");
    }

    /* 要素の開始時の処理*/
    public void startElement(String namespaceURI,String localName,String qName,Attributes attrs){
        
        if("Placemark".equals(qName)){
            areaAry = new HashMap();
        }
        
        /*タグ名を表示*/
        if(qName!=""){
            //tabbing();
            //System.out.println("qName= "+qName);
            currentTag=qName;
        }
       
        
    }


    

    /*要素の終了時の処理*/
    public void endElement(String namespaceURI,String localName,String qName){
        
        if("Placemark".equals(qName)){
            list.add(areaAry);
        }
        
        currentTag="";
    }

    /* XML文書の終了時の処理*/
    public void endDocument(){
        //System.out.println("Here is end of XML document.");
    }


    /* 文字列の処理*/
    public void characters(char[] ch,int start,int length){
        if(currentTag.equals("coordinates")){
            String str=new String(ch,start,length);
            if(str.length()!=0){
                
                //緯度経度をセット
                latLonStr=str;
                areaAry.put("coordinates", latLonStr);
            }
        }else if(currentTag.equals("address")){
            String str=new String(ch,start,length);
            if(str.length()!=0){
                
                //住所をセット
                areaAry.put("address", str);
            }
        }
    }

    /* 処理命令を受けた時の処理 要調査*/
    public void processingInstruction(String target,String data){}

    /* プレフィックスによる名前空間の範囲の開始時 要調査*/
    public void startPrefixMapping(String prefix,String uri){}

    /* プレフィックスによる名前空間の範囲の終了時 要調査*/
    public void endPrefixMapping(String prefix){}

    /* インデント用.これはこのクラス独自のメソッド*/
}
