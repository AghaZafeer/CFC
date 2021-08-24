package com.ibm.cfc.vaers.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
	
	public static String getStringFromDate(Date stringAsDate) {  
	    String stringFromDate = null;
		if(stringAsDate != null) {
		    stringFromDate = new SimpleDateFormat("yyyy-MM-dd").format(stringAsDate); 
		}
	    return stringFromDate;
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
	
	public static String getStringFromBoolean(boolean booleanAsString) {
		if(booleanAsString) {
			return "TRUE";
		}else {
			return "FALSE";
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
	
	public static String generateCaseID() {
		String caseID = "";
		int n = 6;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
		Date date = new Date(System.currentTimeMillis());
        caseID = formatter.format(date) + "-" + getAlphaNumericString(n);
		return caseID;
	}
	
	public static String getAlphaNumericString(int n){
		String alphaNumericString = "0123456789";
  
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(alphaNumericString.length()* Math.random());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }
	
	public static String generateOTPValue() {
		String OTPValue = "";
		int n = 4;
        OTPValue = getAlphaNumericString(n);
		return OTPValue;
	}
	
	public static String replaceOTP(String orgString, String strToFind, String strToReplace) {
		return orgString.replace(strToFind, strToReplace);
	}
	
	public static long getDateDifferenceInDays(LocalDate startDate, LocalDate endDate) {
		long difference = 0l;
		difference = ChronoUnit.DAYS.between(endDate,startDate);
		return difference;
	}
	
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		java.util.Date safeDate = new Date(dateToConvert.getTime());
		
		return safeDate.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
}
