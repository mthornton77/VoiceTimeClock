package edu.cmu.pocketsphinx.demo;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PunchClass {

	static String punchTime;
	static int punchType;

	public static String getPunchTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		// get current date time with Date()
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int min = calendar.get(Calendar.MINUTE);
		
		 int mod=min%30;
		    if(mod>30/2){
		     min=30-mod;
		    }else{
		     min=0-mod;
		    }
		    calendar.add(Calendar.MINUTE, min);
		punchTime = (dateFormat.format(calendar.getTime()));
		return punchTime;
	}

	public static void setPunch(String hyp) {
		if(hyp.equalsIgnoreCase("PUNCH IN")){
			punchType = 1;
		}
		
		switch (punchType){
		case 1:
			punchTime = getPunchTime();

		}
	}
}
