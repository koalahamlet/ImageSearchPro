package image.search.pro;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {

	private static final int SETTINGS = 1;
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	String query;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	SearchFilter filter = new SearchFilter();
	ImageResultArrayAdapter imageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// Append more data into the adapter
//
				searchTehNetz(totalItemsCount);

			}

		});
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent,
					int position, long arg3) {
				Intent i = new Intent(getApplicationContext(),
						ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}

		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		 Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
//		MenuItem mi = (MenuItem) findViewById(R.id.action_settings);
		
//
//	    menu.add(0, SETTINGS, 0, "Settings")
//	        .setIcon(R.drawable.ic_settings)
//	        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		return true;
		  
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
		 switch (item.getItemId()) {
		  case R.id.action_settings:
			  Intent i = new Intent(this, FilterActivity.class);
				 startActivity(i);
		     break;
		  default:
		break; }
		  return true;

	}
	

	private void setupViews() {
		etQuery = (EditText) findViewById(R.id.etSearch);
		gvResults = (GridView) findViewById(R.id.gvImages);
		btnSearch = (Button) findViewById(R.id.bSearch);

	}

	public void onImageSearch(View v) {
		//This part dismisses the keyboard
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etQuery.getWindowToken(), 0);
		//
		imageResults.clear();
		query = etQuery.getText().toString();
		searchTehNetz(0);
	}

	

	private void searchTehNetz(int page) {

		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
		// this talks to the Internet.
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(
				"https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
						+ "start=" + page + "&v=1.0&q=" + Uri.encode(query),
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						JSONArray imageJsonResults = null;
						try {
							imageJsonResults = response.getJSONObject(
									"responseData").getJSONArray("results");
							imageAdapter.addAll(ImageResult
									.fromJSONArray(imageJsonResults));
							Log.d("DEBUG", imageResults.toString());
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}

		);

	}

	
}
