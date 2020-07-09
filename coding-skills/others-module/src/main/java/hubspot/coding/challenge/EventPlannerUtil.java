package hubspot.coding.challenge;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventPlannerUtil {
	
	public static boolean isNextDay(String dateString1, String dateString2) throws Exception {
		boolean result = false;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = simpleDateFormat.parse(dateString1);
		Date date2 = simpleDateFormat.parse(dateString2);
		
		int diff = (int)( (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
		
		if(diff == 1)
			result = true;
		else 
			result = false;
		
		return result;
	}

}
