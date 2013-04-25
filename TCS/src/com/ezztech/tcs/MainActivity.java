package com.ezztech.tcs;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity{
	JSONObject results;
	private static final String PREFS_NAME = "tcsprefs";
	private static final String AUTH_KEY = "authkey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String authkey = null;
		String data = "https://bennynet.dyndns.org:8080/jsontest";
		
		
		HttpRequest request = new HttpRequest(this, getApplicationContext());
		request.execute(data);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		authkey = settings.getString(AUTH_KEY, null);
		
		if (authkey == null){
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
		}
	}
	
	public void timeTableClick(View v){
		Intent intent = new Intent(this, TimeTable.class);
		startActivity(intent);
		//Close the menu and start the new activity
	}
	
	public void updateResults(JSONObject _data){
		this.results = _data;
		Log.v("TEST", "This is data: " + this.results); 
	}
}