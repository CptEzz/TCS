package com.ezztech.tcs;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity{
	JSONObject results;
	private static final String PREFS_NAME = "tcsprefs";
	private static final String AUTH_KEY = "authkey";
	private static final String USERNAME = "username";
	String username = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String authkey = null;
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		authkey = settings.getString(AUTH_KEY, null);
		username = settings.getString(USERNAME, null);
		
		if (authkey == null){
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
			this.finish();
		}else{
			checkLogin(authkey);
		}
	}
	
	public void timeTableClick(View v){
		Intent intent = new Intent(this, TimeTable.class);
		startActivity(intent);
		this.finish();
		//Close the menu and start the new activity
	}
	
	public void updateResults(JSONObject _data) throws JSONException{
		boolean authOk = false;
		
		authOk = _data.getBoolean("auth");
		
		if(authOk == false){
			Toast toast = Toast.makeText(this.getApplicationContext(), _data.getString("errorm"), Toast.LENGTH_LONG);
			toast.show();
			
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			
			editor.remove(AUTH_KEY);
			editor.remove(USERNAME);
			editor.commit();
			
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
			this.finish();
		}else{
			
		}
		
	}
	
	private void checkLogin(String _authkey){
		String input = "https://bennynet.dyndns.org:8080/dummyauthcheck/" + username + "/" + _authkey;
		HttpRequest request = new HttpRequest(this, getApplicationContext());
		request.execute(input);
	}
}