package com.ezztech.tcs;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		      StrictMode.setThreadPolicy(policy);
		    }
		
		String data = "https://bennynet.dyndns.org:8080/jsontest";
		
		
		HttpRequest request = new HttpRequest();
		request.execute(10);
	}
	
	public void timeTableClick(View v){
		Intent intent = new Intent(this, TimeTable.class);
		startActivity(intent);
		//Close the menu and start the new activity
	}
}