package edu.cmu.pocketsphinx.demo;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.database.Cursor;
import android.widget.SimpleCursorAdapter;


import edu.cmu.pocketsphinx.demo.NotesDbAdapter;


public class PunchClass {

	static String punchTime;
	static int punchType;
	public static NotesDbAdapter mDbHelper;
	
	public static String getPunchTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
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

	public static void setPunch(String hyp) {
		
        
		if(hyp.equalsIgnoreCase("PUNCH IN")){
			punchType = 1;
		}
		
		switch (punchType){
		case 1:
			punchTime = getPunchTime();
			//fill db?
			String aPunch = punchTime;
	        mDbHelper.createNote(aPunch, "");
	        fillData();
		}
	}
   
    
    private static void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor c = mDbHelper.fetchAllNotes();
        //startManagingCursor(c);

        String[] from = new String[] { NotesDbAdapter.KEY_TITLE };
        int[] to = new int[] { R.id.EditText02 };
        
        // Now create an array adapter and set it to display using our row
        //SimpleCursorAdapter notes =
           // new SimpleCursorAdapter(this, R.layout.notes_row, c, from, to);
       // setListAdapter(notes);
    }

}
