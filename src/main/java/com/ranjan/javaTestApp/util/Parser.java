package com.ranjan.javaTestApp.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Parser {

	public static Double toDouble(Object obj) {

		Double result = 0.0;
		if (obj != null) {
			result = Double.parseDouble(obj.toString());
		}
		return result;
	}
	public static Integer toInt(Object obj) {

		Integer result = 0;
		if (obj != null) {
			result = Integer.parseInt(obj.toString());
		}
		return result;
	}
	public static BigDecimal toBigDecimal(Object obj) {
		BigDecimal result = new BigDecimal("0");
		if (obj != null) {
			result = new BigDecimal(obj.toString());
		}
		return result;
	}
  
	  public static String getDate(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String yyyy = sdf.format(date);
			return yyyy;
		}
	  
	  public static String getDateForId(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			String yyyy = sdf.format(date);
			return yyyy;
		}
	 
}
