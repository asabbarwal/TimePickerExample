package com.abhisheksabbarwal.timepickerexample;

import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;

public class MainActivity extends Activity {

	static final int TIME_DIALOG_ID = 999;

	private TextView timeDispText;
	private TextView actualTime;
	private TimePicker timePicker;
	private Button changeTime;

	private int hour;
	private int min;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setCurrentTime();
		
		changeTime = (Button) findViewById(R.id.btnChangeTime);
		
		changeTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);	
			}
		});
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, 
                                        timePickerListener, hour, min,false);
 
		}
		return null;
	}
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			min = selectedMinute;
 
			// set current time into textview
			actualTime.setText(new StringBuilder().append(pad(hour)).append(":")
					.append(pad(min)));
 
			// set current time into timepicker
			timePicker.setCurrentHour(hour);
			timePicker.setCurrentMinute(min);
 
		}
	};

	public void setCurrentTime() {

		actualTime = (TextView) findViewById(R.id.actualTime);
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		

		final Calendar c = Calendar.getInstance();

		hour = c.get(Calendar.HOUR_OF_DAY);
		min = c.get(Calendar.MINUTE);

		// Display current time in textview

		actualTime.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(min)));

		//Set time in TimePicker
		
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(min);
		
	}
	
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
}
