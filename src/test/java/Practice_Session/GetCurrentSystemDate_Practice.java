package Practice_Session;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetCurrentSystemDate_Practice {

	public static void main(String[] args) {
		
		Date dateObj = new Date();
		//System.out.println(dateObj.toString());
		
		//simple date format class in java used to get date in required format
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String actDate = sim.format(dateObj);
		System.out.println(actDate);
		
		//calendar class in java - capture entire calendar format
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		
		String dataRequired = sim.format(cal.getTime());
		System.out.println(dataRequired);
	}

}
