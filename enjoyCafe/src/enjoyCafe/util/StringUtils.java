package enjoyCafe.util;

import java.util.List;

public class StringUtils {
    /**
     * 文字列配列を指定した文字列で区切る<br/>
     * StringUtils.join(new String[]{"Java","Python","Ruby"},"|") => "Java|Python|Ruby"
     * @param strs 文字列配列
     * @param joinStr 区切り文字
     * @return 区切り文字で区切られた文字列
     * @see String#split(String)
     */
    public static String join(List<String> strs, String joinStr) {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < strs.size(); i ++) {
            if (strs.get(i) == null) {
                continue;
            }
            if (0 < buffer.length()) {
                buffer.append(joinStr);
            }
            buffer.append(strs.get(i));
        }
        return String.valueOf(buffer);
    }
    /**
     * 単純な文字列置換<br/>
     * String#replaceAllの正規表現によるマッチングではなくrawテキストでの文字列置換を行なう．
     * @param src 置換前テキスト
     * @param target 置換前の文字列
     * @param replace 置換後の文字列
     * @return 置換後テキスト
     * @see String#replaceAll(String, String)
     */
    public static String replaceAll(final String src, final String target, final String replace) {
        final StringBuffer buffer
            = new StringBuffer((int)(src.length() * 1.5));
        if (src == null) {
            return null;
        }
        int index;
        int start = 0;
        int targetLength = target.length();
        while((index = src.indexOf(target, start)) != -1) {
            buffer.append(src.substring(start, index));
            buffer.append(replace);
            start = index + targetLength;
        }
        buffer.append(src.substring(start));
        return buffer.toString();
    }
}

