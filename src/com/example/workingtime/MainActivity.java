package com.example.workingtime;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private class LongRunningGetIO extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			String text = null;
			JSONObject obj = new JSONObject();
			try {
				obj.put("ra", "654002");

				obj.put("data1", "07/07/2014 00:00");
				obj.put("data2", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
				return PostGetHelper.postJSON(obj.toString(),"http://perseu1.eniac.com.br/androidHandler/android/jersey/meutempo");
			} catch (Exception e) {
				return e.getLocalizedMessage();
			}
		}
		
		@Override
		protected void onPostExecute(String results) {
			if (results != null) {
				try {
					JSONObject obj = new JSONObject(results);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
