package com.practice.others.util.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * java.util.Date
 *  - Use this type if you need to store a date value
 * 
 * java.util.Calendar
 *  - Perform date calculations like adding days & months in another date.
 *  - Check the day is a weekday. 
 *  - Convert the dates & times b/w time zones.
 */
public class DateSample {

	void dateAPIs(){
		//Most of the API's in Date is Deprecated
		System.out.println("\nDATE API'S - START");
		Date ob = new Date();
		System.out.println("DATE in system is : " + ob);
		System.out.println("Date: " +ob.getDate());
		System.out.println("Day: " +ob.getDay());
		System.out.println("Month: " + ob.getMonth());
		System.out.println("Year: " + ob.getYear());
		System.out.println("Hours: " +ob.getHours());
		System.out.println("Mins: " +ob.getMinutes());
		System.out.println("Sec's: " +ob.getSeconds());
		System.out.println("Time: " + ob.getTime());
		System.out.println("TimeZoneOffset: " + ob.getTimezoneOffset());
		System.out.println("toString: " + ob.toString());
		System.out.println("DATE API'S - END");
	}
	
	void calendarAPIs(){
		System.out.println("\nCALENDAR API'S - START");
		Date ob = new Date();
		System.out.println("DATE in system is : " + ob);
		Calendar cal = Calendar.getInstance();
		System.out.println("Year:"+cal.get(Calendar.YEAR));
		System.out.println("Month:"+cal.get(Calendar.MONTH));
		System.out.println("Day of Month:"+cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("Day of Week:"+cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("Hour:"+cal.get(Calendar.HOUR_OF_DAY));
		System.out.println("Min:"+cal.get(Calendar.MINUTE));
		System.out.println("Sec:"+cal.get(Calendar.SECOND));
		System.out.println("MiliSec:"+cal.get(Calendar.MILLISECOND));
		System.out.println("Sys date by using Cal Object:"+cal.getTime());
		System.out.println("Total Seconds:"+cal.getTimeInMillis());
		System.out.println("Time Zone:"+cal.getTimeZone());
		System.out.println("CALENDAR API'S - END");
	}

	void dateManipulations(){
		System.out.println("\nDATE MANIPULATION'S - START");
		Date d1 = new Date("1/12/2015");
		//Date in year,month(0-11),date format
		//115 is equivalent to 2015 in Date class-> get from date.getyear();
		Date d2 = new Date(115,1,2);
		System.out.println("d1:"+d1+"\t d2:"+d2);
		if(d1.before(d2)) System.out.println("d1 is before d2");
		if(d2.after(d1)) System.out.println("d2 is after d1");
		System.out.println("(d1<d2)-d1.compareTo(d2):"+d1.compareTo(d2));
		System.out.println("(d2>d1)-d2.compareTo(d1):"+d2.compareTo(d1));
		System.out.println("DATE MANIPULATION'S - END");
	}
	void calendarManipulations(){
		System.out.println("\nCALENDAR MANIPULATION'S - START");
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		cal1.set(Calendar.HOUR_OF_DAY, 7);
		Calendar cal2 = Calendar.getInstance();
		Date d2 = new Date("2/12/2015");
		cal2.setTime(d2);
		cal2.set(Calendar.HOUR_OF_DAY, 12);
		System.out.println("cal1:"+cal1.getTime());
		System.out.println("cal2:"+cal2.getTime());
		System.out.println("CALENDAR MANIPULATION'S - END");
	}
	/**
	 * DateFormat: It is predefined format. It allows you to format
	 * dates & times with predefined styles.
	 */
	void dateFormatAPIs(){
		System.out.println("\nDATEFORMAT API'S - START");
		Date date = new Date();
		DateFormat df;
		//There are lot of inbuilt Locale's in DateFormat
		System.out.println("US Locale Format's with differnt style:");
		df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
		String formattedDate = df.format(date);
		System.out.println("Date format in (style)DEFAULT:"+formattedDate);
		df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
		System.out.println("Date format in (style)SHORT  :"+df.format(date));
		df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		System.out.println("Date format in (style)MEDIUM :"+df.format(date));
		df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
		System.out.println("Date format in (style)LONG   :"+df.format(date));
		df = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);
		System.out.println("Date format in (style)FULL   :"+df.format(date));
		System.out.println("DATEFORMAT API'S - END");
	}
	/**
	 * SimpleDateFormat: It is customized Date formats. It allows you 
	 * to format dates & times with customized styles.
	 */
	void simpleDateFormatAPIs(){
		System.out.println("\nSIMPLEDATEFORMAT API'S - START");
		Date date = new Date();
		SimpleDateFormat sdf;
		System.out.println("Sample customized format's:");
		sdf =new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String formattedDate = sdf.format(date);
		System.out.println("dd-MM-yyyy hh:mm:ss -> "+formattedDate);
		sdf =new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		System.out.println("yyyy.MM.dd G 'at' HH:mm:ss z -> "+sdf.format(date));
		sdf =new SimpleDateFormat("EEE, MMM d, yy");
		System.out.println("EEE, MMM d, yy -> "+sdf.format(date));
		sdf =new SimpleDateFormat("h:mm a");
		System.out.println("h:mm a -> "+sdf.format(date));
		sdf =new SimpleDateFormat("yyyy.MMMMMM.dd GGGG hh:mmm aaa");
		System.out.println("yyyy.MMMMMM.dd GGGG hh:mmm aaa -> "+sdf.format(date));
		
		/*sdf =new SimpleDateFormat("");
		System.out.println("dd-MM-yyyy hh:mm:ss -> "+sdf.format(date));
		sdf =new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		System.out.println("dd-MM-yyyy hh:mm:ss -> "+sdf.format(date));*/
		System.out.println("SIMPLE DATE FORMAT API'S - END");
	}
	public static void main(String[] args) {
		
		DateSample obj = new DateSample();
		obj.dateAPIs();
		obj.calendarAPIs();
		obj.dateManipulations();
		obj.calendarManipulations();
		obj.dateFormatAPIs();
		obj.simpleDateFormatAPIs();
		
	}
}