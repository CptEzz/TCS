package com.ezztech.tcs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;
import android.util.Log;

public class HttpRequest extends AsyncTask<URL, Integer, Long> {

	@Override
	protected Long doInBackground(URL... arg0) {
		Log.v("TEST", "Running");
		HttpResponse response = null;
		InputStream in = null;
		try{
			Log.v("TEST", "Trying");
			HttpClient client = this.getNewHttpClient();
			//Not recieving anything
			HttpGet request = new HttpGet();
			request.setURI(new URI("https://bennynet.dyndns.org:8080/jsontest"));
			response = client.execute(request);
			in = response.getEntity().getContent();
			Log.v("TEST", "Done");
		
		} catch (URISyntaxException e){
			e.printStackTrace();
			Log.v("TEST", "URI Syntax Exception");
		} catch (ClientProtocolException e){
			e.printStackTrace();
			Log.v("TEST", "Client Protocol Exception");
		} catch (IOException e){
			e.printStackTrace();
			Log.v("TEST", "IO Exception");
		}
		if (response != null){
			Log.v("TEST","DATA");
		}else{
			Log.v("TEST","NO DATA");
		}
		return null;
	}
	
	public HttpClient getNewHttpClient() {
	    try {
	        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);

	        SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        HttpParams params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        registry.register(new Scheme("https", sf, 443));

	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

	        return new DefaultHttpClient(ccm, params);
	    } catch (Exception e) {
	        return new DefaultHttpClient();
	    }
	}

}
