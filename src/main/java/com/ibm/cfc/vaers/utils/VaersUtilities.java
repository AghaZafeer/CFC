package com.ibm.cfc.vaers.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class VaersUtilities {

	public static Date getDateFromString(String dateAsString) {  
	    Date dateFromString = null;
		if(dateAsString != null) {
		    try {
				dateFromString = new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	    return dateFromString;
	}  
	
	public static boolean getBooleanFromString(String booleanAsString) {
		if(booleanAsString != null) {
			if(booleanAsString.equalsIgnoreCase("TRUE")) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public static Timestamp getTimestampFromString(String strDate) {
	    if(strDate != null) {
			try {
		      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		       // you can change format of date
		      Date date = formatter.parse(strDate);
		      Timestamp timeStampDate = new Timestamp(date.getTime());
	
		      return timeStampDate;
		    } catch (ParseException e) {
		      e.printStackTrace();
		      return null;
		    }
	    }else {
	    	return null;
	    }
	}
	
	public static Date getCurrentDate() {
		Date date=java.util.Calendar.getInstance().getTime();  
		return date; 
	}	
}
