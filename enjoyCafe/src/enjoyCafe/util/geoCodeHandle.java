package enjoyCafe.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import sun.net.www.http.HttpClient;
import org.apache.commons.digester.*;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.sun.tools.javac.util.List;

import enjoyCafe.digester.Placemark;
import enjoyCafe.digester.Point;
import enjoyCafe.digester.Response;
import enjoyCafe.digester.Status;
import enjoyCafe.digester.KmlBean;

public class geoCodeHandle {
	
	private String baseUrl = "http://www.geocoding.jp/api/?v=1.1";
	private String gMapApiUrl = "http://maps.google.com/maps/geo";
	private httpHandle httpConn;
	private String apiKey = "ABQIAAAAHcKss5O7vJKoSmQ6vy_IQxTkbcdT7TAc-_C5QDwehsHhfaHFdxQjDJhp5kPdiW3XTKIDymoWWwgtIg";
	//private String apiKey = "API_KEY";
    
	
	public String lat;
	public String lng;
	
	public geoCodeHandle(){	
		
	}

	/**
	 * 緯度経度を取得
	 * @param query
	 * @return
	 */
	public HashMap feedLatLon2(String query){
	    HttpURLConnection http = null;
	    HashMap latlngAry = new HashMap();
	    
	    InputStream in = null;
        URL url = null;       
        String encodeQuery;
        try {
            encodeQuery = URLEncoder.encode(query, "UTF-8");

            String location = gMapApiUrl+"?key="+apiKey+"&output=xml&ie=UTF8&q="+encodeQuery;
//System.out.println(location);            
            // 指定した URL の作成           
            url = new URL(location);
            String latlon = null;
            ArrayList<HashMap> resultlist = new ArrayList<HashMap>();
            int i=0;
            while(i < 5){
                
                BufferedInputStream input = new BufferedInputStream(url.openStream());
                InputSource inputSource = new InputSource(input);
             
                /*パーサのFactoryを作成
                名前からしてFactoryパターンで,Singletonだと思う.*/
                SAXParserFactory spf=SAXParserFactory.newInstance();
                /*パーサを取得*/
                SAXParser sp=spf.newSAXParser();
                  
                /*イベントハンドラを作成*/
                HandleGoogleApi sh=new HandleGoogleApi();
        
                /*イベントハンドラに入力データとイベントハンドラを渡す*/
                sp.parse(inputSource,sh);
                resultlist = sh.list;
                if(resultlist.size() > 0){
                    HashMap tmpAry = resultlist.get(0);
                    latlon = EscapeUtil.toHtmlStringForObject(tmpAry.get("coordinates"));
                }
                //latlon = sh.latLonStr;
                if(latlon != null){
                    break;
                }
                i++;
            }
            if(latlon != null){
                String[] latlonAry = latlon.split(",");          
                latlngAry.put("lat",latlonAry[1]);
                latlngAry.put("lng",latlonAry[0]);
            }
            
	    } catch (UnsupportedEncodingException e1) {    
            
	    } catch (NullPointerException e2) {
            e2.printStackTrace();    
	        
        } catch (Exception e) {
            e.printStackTrace();
       
            
        } finally{
            // HTTP 通信の後始末
            try{
                in.close();
            } catch (Exception e) {}
            
            try {
                http.disconnect();
            } catch (Exception e) {}
            
        }
        return latlngAry;
	    
	}
	
	public Hashtable getLatLon(String query){
        
        Hashtable latlngAry = new Hashtable();
        try {
            String encodeQuery;
        
            encodeQuery = URLEncoder.encode(query, "UTF-8");
            String url = baseUrl+"&q="+encodeQuery;
            
            // ドキュメントビルダーファクトリを生成
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            // ドキュメントビルダーを生成
            DocumentBuilder builder = dbfactory.newDocumentBuilder();
            // パースを実行してDocumentオブジェクトを取得
            Document doc = builder.parse(url);
//System.out.println(url);            
            // ルート要素を取得（タグ名：site）
            Element root = doc.getDocumentElement();
            System.out.println("ルート要素のタグ名：" + root.getTagName());
        
            System.out.println("***** ページリスト *****");
            // page要素のリストを取得
            NodeList list = root.getElementsByTagName("coordinate");    
            
            // page要素の数だけループ
            for (int i=0; i < list.getLength() ; i++) {
                // page要素を取得
                Element element = (Element)list.item(i);
                 // lat要素のリストを取得
                NodeList latList = element.getElementsByTagName("lat");
                // lat要素を取得
                Element latElement = (Element)latList.item(0);
                // lat要素の最初の子ノード（テキストノード）の値を取得
                lat = latElement.getFirstChild().getNodeValue();
                
                // lng要素のリストを取得
                NodeList lngList = element.getElementsByTagName("lng");
                // lng要素を取得
                Element lngElement = (Element)lngList.item(0);
                // lng要素の最初の子ノード（テキストノード）の値を取得
                lng = lngElement.getFirstChild().getNodeValue();
    
    
                latlngAry.put("lat",lat);
                latlngAry.put("lng",lng);
            }
            return latlngAry;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }   
        
        
    }
	
	/**
	 * 緯度経度を取得
	 * @param query
	 * @return
	 */
	public HashMap feedLatLon(String query){
        
	    HashMap<String,String> latlngAry = new HashMap<String,String>();
        try {
            String encodeQuery;
            encodeQuery = URLEncoder.encode(query, "UTF-8");

            //GoogleMapのURLを指定
            String url = gMapApiUrl+"?key="+apiKey+"&output=xml&ie=UTF8&&q="+encodeQuery;
            //String url = baseUrl+"&q="+encodeQuery;
            
            // ドキュメントビルダーファクトリを生成
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            // ドキュメントビルダーを生成
            DocumentBuilder builder = dbfactory.newDocumentBuilder();
            // パースを実行してDocumentオブジェクトを取得
            Document doc = builder.parse(url);
            // ルート要素を取得（タグ名：kml）
            Element root = doc.getDocumentElement();
            //System.out.println("ルート要素のタグ名：" + root.getTagName());
        
            //System.out.println("***** ページリスト *****");
            // page要素のリストを取得
            NodeList list = root.getElementsByTagName("Response");    
            
            // page要素の数だけループ
            //要素にNULLが合った場合は、NULLポインタイクセプションをはいて、メソッドは終了
            for (int i=0; i < list.getLength() ; i++) {
                // page要素を取得
                Element element = (Element)list.item(i);
                 // lat要素のリストを取得
                NodeList PlacemarkList = element.getElementsByTagName("Placemark");
                // lat要素を取得
                Element PlacemarkElement = (Element)PlacemarkList.item(0);
                
                NodeList PointList = PlacemarkElement.getElementsByTagName("Point");
                Element PointElement = (Element)PointList.item(0);
                NodeList codeList = PlacemarkElement.getElementsByTagName("coordinates");
                Element codeElement = (Element)codeList.item(0);
                String latlon = codeElement.getFirstChild().getNodeValue();
                String[] latlonAry = latlon.split(",");
                //System.out.println(latlon);
                if(latlonAry != null){
                    if(latlonAry.length == 3){
                        latlngAry.put("lat",latlonAry[1]);
                        latlngAry.put("lng",latlonAry[0]);
                    }
                }    
                
            }
            return latlngAry;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }   
        
        
    }
}
