package com.ezztech.tcs;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {
	private static final String PREFS_NAME = "tcsprefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		
		
	}
	
	public void onSubmitClick(View v){
		EditText usernameView = (EditText) findViewById(R.id.username);
		EditText passwordView = (EditText) findViewById(R.id.password);
		String username = usernameView.getText().toString();
		String password = passwordView.getText().toString();
		
		String input = "https://bennynet.dyndns.org:8080/dummyauth/" + username + "/" + password;
		HttpRequest request = new HttpRequest(this, getApplicationContext());
		request.execute(input);
	}
	
	public void updateRequests(JSONObject result) throws JSONException{
		Log.v("TEST", result + "");
		String authcode = (String) result.get("authcode");
	}
}
