package com.ibooking.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getCurrentDateStr() {
		Date date = new Date();
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		str = df.format(date);
		return str;
	}

	public static Date parsePlainDate(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		return sdf.parse(source, new ParsePosition(0));
	}
}
