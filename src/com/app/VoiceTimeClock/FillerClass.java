package com.app.VoiceTimeClock;
import com.app.VoiceTimeClock.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class FillerClass extends Activity {

	TextView comingUp;
	
	public void onCreate(Bundle savedInstanceState) {
		CharSequence title = "Carnegie Mellon PocketSphinx";
		this.setTitle(title);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comingup);
		comingUp = (TextView) findViewById(R.id.ComingUP);
		
	}
	
	
}