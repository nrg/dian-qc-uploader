package org.nrg.dian.qc.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd")
	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss")
	
	static Date secondsToDate(Long seconds){
		def milliseconds = seconds * 1000
		return new Date(milliseconds)
	}
	
	static String dateFormat(Date date){
		return DATE_FORMAT.format(date)
	}
	
	static String timeFormat(Date date){
		return TIME_FORMAT.format(date)
	}
}