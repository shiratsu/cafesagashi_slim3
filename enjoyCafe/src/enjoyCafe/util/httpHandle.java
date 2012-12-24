package enjoyCafe.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class httpHandle {
	
	public String getHttpData(String Url){
		
		try {

		      // HTTP通信系
		      URL u = new URL(Url);

		      // 読む
		      BufferedReader i = new BufferedReader(
		                                new InputStreamReader(
		                                u.openStream()));

		      // 
		      String line;
		      
		      StringBuilder sb = new StringBuilder();
		      while ((line = i.readLine()) != null) {
		        //
		        sb.append(line);
		      }
		    
		      // 
		      i.close();
		      
		      String returnData = new String(sb);
		      return returnData;

		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		return Url;
		
	}
	
	

}
