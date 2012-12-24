package enjoyCafe.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 時間を取得
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTime() throws ParseException {
	
		Calendar cal=Calendar.getInstance();

		MessageFormat mf = new MessageFormat("{0,date,yyyy/MM/dd HH:mm:ss}");
		
		Object[] objs = {Calendar.getInstance().getTime()};

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		
		String result = mf.format(objs);
		Date date = df.parse(result);
		
		return date;
	}

	/**
	 * 時間を取得
	 * @return
	 * @throws ParseException
	 */
	public static String getDateTimeString() throws ParseException {
	
		Date date1 = new Date();  //(1)Dateオブジェクトを生成
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf1.format(date1);
	}
}
