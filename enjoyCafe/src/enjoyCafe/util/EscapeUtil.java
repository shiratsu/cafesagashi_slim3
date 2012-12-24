package enjoyCafe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字列にエスケープ処理を行うクラス
 *
 * @author g
 *
 */
public class EscapeUtil {

    /**
     * 文字列に含まれるHTML特殊文字をエスケープ変換するメソッド
     *
     * @param p 変換元文字列
     * @return 変換後文字列
     */
    static public String toHtmlString(String p) {
        String str = "";
        if(p != null){
            for (int i = 0; i < p.length(); i++) {
                Character c = new Character(p.charAt(i));
                switch (c.charValue()) {
                    case '&' :
                        str = str.concat("&amp;");
                        break;
                    case '<' :
                        str = str.concat("&lt;");
                        break;
                    case '>' :
                        str = str.concat("&gt;");
                        break;
                    case '"' :
                        str = str.concat("&quot;");
                        break;
                    case '\'' :
                        str = str.concat("&apos;");
                        break;
                    default :
                        str = str.concat(c.toString());
                        break;
                }
            }
        }
        return str;
    }
    /**
     * オブジェクトを文字列に変換するメソッド
     *
     * @param object s 変換元文字列
     * @return 変換後文字列
     */
    static public String toStringForObject(Object s) {
        String str = "";
        String p = "";
        if(s instanceof Object){
            if(s != null){
                p = s.toString();
            }
                
        }
        return p;
    }
    
    /**
     * 文字列に含まれるHTML特殊文字をエスケープ変換するメソッド
     *
     * @param object s 変換元文字列
     * @return 変換後文字列
     */
    static public String toHtmlStringForObject(Object s) {
        String str = "";
        String p = "";
        if(s instanceof Object){
            if(s != null){
                p = s.toString();
            }
                
        }
        if(p != null){
            for (int i = 0; i < p.length(); i++) {
                Character c = new Character(p.charAt(i));
                switch (c.charValue()) {
                    case '&' :
                        str = str.concat("&amp;");
                        break;
                    case '<' :
                        str = str.concat("&lt;");
                        break;
                    case '>' :
                        str = str.concat("&gt;");
                        break;
                    case '"' :
                        str = str.concat("&quot;");
                        break;
                    case '\'' :
                        str = str.concat("&apos;");
                        break;
                    default :
                        str = str.concat(c.toString());
                        break;
                }
            }
        }
        
        return str;
    }
    
    /**
     * 文字列に含まれるSQL特殊文字をエスケープ処理するメソッド
     * 
     * @param p 変換元文字列
     * @return 変換後文字列
     */
    static public String toSqlString(String p) {
        String str = "";
        for (int i = 0; i < p.length(); i++) {
            Character c = new Character(p.charAt(i));
            switch (c.charValue()) {
                case '\'' :
                    str = str.concat("''");
                    break;
                case '\\' :
                    str = str.concat("\\\\");
                    break;
                default :
                    str = str.concat(c.toString());
                    break;
            }
        }
        return str;
    }
}