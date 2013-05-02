package com.ezztech.tcs;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	private static final String PREFS_NAME = "tcsprefs";
	int duration = Toast.LENGTH_LONG;
	String username = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
	}
	
	public void onSubmitClick(View v){
		EditText usernameView = (EditText) findViewById(R.id.username);
		EditText passwordView = (EditText) findViewById(R.id.password);
		username = usernameView.getText().toString();
		String password = passwordView.getText().toString();
		
		String input = "https://bennynet.dyndns.org:8080/dummyauth/" + username + "/" + password;
		HttpRequest request = new HttpRequest(this, getApplicationContext());
		request.execute(input);
	}
	
	public void updateRequests(JSONObject result) throws JSONException{
		
		if (result.get("authcode") != null){
			String authcode = (String) result.get("authcode");
			
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			
			editor.putString("authkey", authcode);
			editor.putString("username", username);
			editor.commit();
			logincomplete();
		}else if (result.get("errorc") != null){
			String error = result.getString("errorm");
			Toast toast = Toast.makeText(this.getApplicationContext(), error, duration);
			toast.show();
		}else{
			Toast toast = Toast.makeText(this.getApplicationContext(), "Something is wrong with the logins! Please let the developers know", duration);
			toast.show();
		}
		
	}
	
	private void logincomplete(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
}
