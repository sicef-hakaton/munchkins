package com.example.hakatonapp.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hakatonapp.R;
import com.example.hakatonapp.StudyApplication;
import com.example.hakatonapp.data.GlobalBank;
import com.example.hakatonapp.model.BasicFeedItem;
import com.example.hakatonapp.model.Comment;
import com.example.hakatonapp.util.Config;
import com.example.hakatonapp.util.Parser;

public class LoginActivity extends Activity {
	View button;
	TextView txt;
	EditText username;
	EditText password;;
	private RotateAnimation rotate = null;
	ImageView loadImage;
	View holder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		button = findViewById(R.id.login);
		txt = (TextView) findViewById(R.id.login_text);
		username = (EditText) findViewById(R.id.login_email);
		password = (EditText) findViewById(R.id.login_pass);
		loadImage = (ImageView) findViewById(R.id.login_loading);
		holder = findViewById(R.id.login_form_holder);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// CIMAJ SERVER
				String user = username.getText().toString();
				String pass = password.getText().toString();

				// ZA sad samo skoci na sledeci aktiviti
				button.setBackgroundColor(Color.parseColor("#ffffff"));
				txt.setTextColor(Color.parseColor("#000000"));
				//

				new LoginAsyncTask().execute();

				rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				rotate.setDuration(700);
				rotate.setRepeatMode(Animation.INFINITE);
				rotate.setRepeatCount(Animation.INFINITE);

				holder.setVisibility(View.GONE);
				loadImage.startAnimation(rotate);

			}
		});
	}

	public class LoginAsyncTask extends AsyncTask<Void, Void, Boolean> {
		String serverUrl = "";

		@Override
		protected Boolean doInBackground(Void... params) {
			InputStream inputStream = null;
			String result = "";
			try {

				// create HttpClient
				HttpClient httpclient = new DefaultHttpClient();

				// make GET request to the given URL
				HttpResponse httpResponse = httpclient.execute(new HttpGet(Config.TEACH_URL + "mita"));

				// receive response as inputStream
				inputStream = httpResponse.getEntity().getContent();

				// convert inputstream to string
				if (inputStream != null)
					result = convertInputStreamToString(inputStream);
				else
					result = "Did not work!";

			} catch (Exception e) {
				Log.d("InputStream", e.getLocalizedMessage());
			}

			JSONObject jObject = null;
			if (result != null && result != "")
				try {
					jObject = new JSONObject(result);

					JSONArray jArray = jObject.getJSONArray("results");
					for (int i = 0; i < jArray.length(); i++) {

						JSONObject oneObject = jArray.getJSONObject(i);
						BasicFeedItem item = Parser.parseBasicFeedItem(oneObject.toString());
						StudyApplication.globalBank.getFeed().items.add(item);
					}
				} catch (Exception e) {
					return false;
				}

			for (int i = 0; i < StudyApplication.globalBank.getFeed().size(); ++i) {
				try {

					// create HttpClient
					HttpClient httpclient = new DefaultHttpClient();

					// make GET request to the given URL
					String id = StudyApplication.globalBank.getFeed().getFeedItem(i).getRoomId();
					String addressa = Config.COMMENT_URL + id;
					HttpResponse httpResponse = httpclient.execute(new HttpGet(addressa));

					// receive response as inputStream
					inputStream = httpResponse.getEntity().getContent();

					// convert inputstream to string
					if (inputStream != null)
						result = convertInputStreamToString(inputStream);
					else
						result = "Did not work!";

				} catch (Exception e) {
					Log.d("InputStream", e.getLocalizedMessage());
				}

				jObject = null;
				if (result != null && result != "") {
					StudyApplication.globalBank.getFeed().getFeedItem(i).comments = Parser
							.parseCommentArray(result);
				}

			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			startActivity(new Intent(LoginActivity.this, MainActivity.class));

			// skloni ovaj Activity sa back stacka
			finish();
		}

	}

	// convert inputstream to String
	public static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	// Reads an InputStream and converts it to a String.
	public static String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {

		InputStream in = stream;
		InputStreamReader is = new InputStreamReader(in);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(is);
		String read = br.readLine();

		while (read != null) {
			sb.append(read);
			read = br.readLine();
		}

		return sb.toString();
	}
}
