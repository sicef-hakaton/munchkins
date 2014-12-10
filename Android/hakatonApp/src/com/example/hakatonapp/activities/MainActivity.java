package com.example.hakatonapp.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hakatonapp.R;
import com.example.hakatonapp.StudyApplication;
import com.example.hakatonapp.fragments.FeedFragment;
import com.example.hakatonapp.fragments.NavigationDrawerFragment;
import com.example.hakatonapp.fragments.TeacherFragment;
import com.example.hakatonapp.model.BasicFeedItem;
import com.example.hakatonapp.model.Feed;
import com.example.hakatonapp.util.Config;
import com.example.hakatonapp.util.Parser;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	int curFragment;

	entryAdd entryAddListener;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(
				R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		Thread t;
		Runnable r = new Runnable() {

			Feed feed = new Feed();

			@Override
			public void run() {
				while (true) {
					Log.e("BG CALL", "BG CALL");
					feed.items.clear();
					InputStream inputStream = null;
					String result = "";
					try {

						// create HttpClient
						HttpClient httpclient = new DefaultHttpClient();

						// make GET request to the given URL
						HttpResponse httpResponse = httpclient
								.execute(new HttpGet(Config.TEACH_URL + "mita"));

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
								feed.items.add(item);
							}
						} catch (Exception e) {
							int iasd = 0;
						}

					for (int i = 0; i < feed.size(); ++i) {
						try {

							// create HttpClient
							HttpClient httpclient = new DefaultHttpClient();

							// make GET request to the given URL
							String id = feed.getFeedItem(i).getRoomId();
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
							feed.getFeedItem(i).comments = Parser.parseCommentArray(result);

						}

						if (feed.size() > StudyApplication.globalBank.getFeed().size()) {
							// skloni ovaj Activity sa back stacka
							finish();
							Intent intent = getIntent();
							finish();
							startActivity(intent);

						} else {
							// niKsta
						}
					}
				}
			}

		};
		t = new Thread(r);
		t.start();

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

	@Override
	public void onNavigationDrawerItemSelected(int position) {

		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = null;
		switch (position) {
		case 1:
			fragment = FeedFragment.newInstance(position);
			entryAddListener = (entryAdd) fragment;
			break;
		case 2:
			fragment = TeacherFragment.newInstance(position);
			entryAddListener = (entryAdd) fragment;
			break;
		case 3:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;

		}

		fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
	}

	public void onSectionAttached(int number) {
		curFragment = number;
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void addNewEntry() {
		entryAddListener.addNewEntry();
	}

	public interface entryAdd {
		public void addNewEntry();
	}
}
