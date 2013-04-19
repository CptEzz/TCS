package com.ezztech.tcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

public class HttpRequest extends AsyncTask<String, Integer, JSONObject> {
	private JSONObject json;
	private MainActivity activity;
	private Context context;
	
	protected void onPostExecute(JSONObject json){
		//Log.v("TEST", "REQUEST FINISHED!");
		activity.updateResults(json);
	}
	
	public HttpRequest(MainActivity a, Context c){
		this.activity = a;
		this.context = c;
	}
	
	@Override
	protected JSONObject doInBackground(String... datain) {
		HttpResponse response = null;
		InputStream in = null;
		
		String thing = datain[0];
		
		try{
			HttpClient client = new MyHttpClient(context);
			HttpGet request = new HttpGet();
			request.setURI(new URI(datain[0]));
			response = client.execute(request);
			in = response.getEntity().getContent();
			
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8")); 
		    StringBuilder responseStrBuilder = new StringBuilder();

		    String inputStr;
		    while ((inputStr = streamReader.readLine()) != null){
		        responseStrBuilder.append(inputStr);
		    }
		    json = new JSONObject(responseStrBuilder.toString());
		    
			in.close();
			
			
		} catch (URISyntaxException e){
			e.printStackTrace();
		} catch (ClientProtocolException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
