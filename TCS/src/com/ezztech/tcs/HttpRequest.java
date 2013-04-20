package com.ezztech.tcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
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
			client = this.sslClient(client);
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
	
    private HttpClient sslClient(HttpClient client) {
	    try {
	        X509TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
	            }
 
	            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
	            }
 
	            public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	        };
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        ctx.init(null, new TrustManager[]{tm}, null);
	        SSLSocketFactory ssf = new MySSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = client.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        return new DefaultHttpClient(ccm, client.getParams());
	    } catch (Exception ex) {
	        return null;
	    }
	}
}
