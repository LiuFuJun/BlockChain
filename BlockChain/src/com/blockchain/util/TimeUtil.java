package com.blockchain.util;

import java.text.SimpleDateFormat;

public class TimeUtil {

	private static final SimpleDateFormat FullFormatStandardTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

	public static final String getFullFormatStandardTime() {
		return FullFormatStandardTime.format(System.currentTimeMillis());
	}

}
