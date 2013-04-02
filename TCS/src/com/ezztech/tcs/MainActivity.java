package com.ezztech.tcs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public void timeTableClick(View v){
		Intent intent = new Intent(this, TimeTable.class);
		startActivity(intent);
		//Close the menu and start the new activity
	}
}
