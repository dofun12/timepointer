package com.example.workingtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.com.uacari.util.UacariReader;

import com.google.gson.Gson;

public class PostGetHelper{

	public static String postJSON(String json, String url) {
		try {
			long timeStart = System.currentTimeMillis();
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			if (url != null && !url.equals("")) {
				HttpPost post = new HttpPost(url);
				Gson gson = new Gson();
				StringEntity se = new StringEntity(json);
				post.setHeader("Content-type", "application/json");
				post.setHeader("Content-Encoding", "gzip");
				post.setEntity(se);
				HttpResponse response = httpClient.execute(post, localContext);
				HttpEntity entity = response.getEntity();
				response.getStatusLine();

				String responseString = Converter.getASCIIContentFromEntity(entity);
				System.out.println("\nEnviado: " + json);
				System.out.println("\nRecebido: " + responseString);
				System.out.println("Tempo: " + (System.currentTimeMillis() - timeStart));
				return responseString;
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getJSON(String url) {
		try {
			//return getUrlData(getLinkVerdadeiro(url));
			long timeStart = System.currentTimeMillis();
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			if (url != null && !url.equals("")) {
				HttpGet get = new HttpGet(url);
				get.setHeader("Content-Encoding", "gzip");

				HttpResponse response = httpClient.execute(get, localContext);
				HttpEntity entity = response.getEntity();
				//if (response.getStatusLine().getStatusCode() == 200) {
					String recebido = Converter
							.getASCIIContentFromEntity(entity);
					System.out.println("\nRecebido: " + recebido);
					System.out.println("Tempo: "+ (System.currentTimeMillis() - timeStart));
					return recebido;
				//				}
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String getUrlData(String uri){
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            //connection.setDoOutput(true);
            //OutputStream out = connection.getOutputStream();
            //out.write(data.getBytes());
            byte bytes[] = UacariReader.getBytes(connection.getInputStream());
            connection.disconnect();

            String resposta = new String(bytes);
            return resposta;
        } catch (Exception ex) {
        	ex.printStackTrace();
            //Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
	public static String getUrlDataV2(String myurl) throws IOException{
		InputStream is = null;
		int len = 500;
		try {
		    // Only display the first 500 characters of the retrieved
		    // web page content.
	        URL url = new URL(myurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        // Starts the query
	        conn.connect();
	        int response = conn.getResponseCode();
	        Log.d("DebugTag", "The response is: " + response);
	        is = conn.getInputStream();

	        // Convert the InputStream into a string
	        String contentAsString = Converter.readIt(is, len);
	        return contentAsString;
	        
	    // Makes sure that the InputStream is closed after the app is
	    // finished using it.
		}catch(Exception e){
			e.printStackTrace();
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
		return myurl;
	}
	//

}
