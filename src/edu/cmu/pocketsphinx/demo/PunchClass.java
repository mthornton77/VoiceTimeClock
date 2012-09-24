package edu.cmu.pocketsphinx.demo;

import java.util.Calendar;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class PunchClass extends Activity {
	
	static String punchTime;
	static int punchType;
	private static String DB_PATH = "/data/data/edu.cmu.pocketsphinx.demo/databases/";
	 private static String DB_NAME = "data.sqlite";   

	  
	  @Override
      public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
}
	
	public static String getPunchTime() {
		DateFormat dateFormat = new SimpleDateFormat("HHmm");
		Calendar calendar = Calendar.getInstance();
		// get minute of "now"
		calendar.setTime(new Date());
		int min = calendar.get(Calendar.MINUTE);
		// round to nearest 30min
		int mod = min % 30;
		if (mod > 30 / 2) {
			min = 30 - mod;
		} 
		else {
			min = 0 - mod;
		}
		calendar.add(Calendar.MINUTE, min);
		punchTime = (dateFormat.format(calendar.getTime()));

		return punchTime;
	}

	public void setPunch(String hyp, SQLiteDatabase db1, Context context) {
		
		if(hyp.equalsIgnoreCase("PUNCH IN")){
			//Context context= getBaseContext();
			punchType = 1;
			punchTime = getPunchTime();		
			try {
				copyDataBase(context);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db1.execSQL("INSERT INTO notes VALUES ("+punchTime+", 6, 7)");
	
		} 	
	}
	
	 public void copyDataBase(Context context) throws IOException{

	            //Open your local db as the input stream
		 InputStream myInput = context.getAssets().open(DB_NAME);

	            // Path to the just created empty db
	            String outFileName = DB_PATH + DB_NAME;

	            //Open the empty db as the output stream
	            OutputStream myOutput = new FileOutputStream(outFileName);

	            //transfer bytes from the inputfile to the outputfile
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = myInput.read(buffer))>0){
	                myOutput.write(buffer, 0, length);
	            }

	            //Close the streams
	            myOutput.flush();
	            myOutput.close();
	            myInput.close();

	        }//end of copyDataBase() method
}
	