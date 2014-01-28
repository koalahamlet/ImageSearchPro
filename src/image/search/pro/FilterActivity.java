package image.search.pro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FilterActivity extends Activity {

	Button bSubmit;
	EditText etWebsite;
	Spinner sprColor;
	Spinner sprType;
	Spinner sprSize;
	String sColor;
	String sType;
	String sSize;
	String sWebsite;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		
		initializeViews();
		
		
		final SearchFilter sfIsTheBest = new SearchFilter();
		
		bSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sfIsTheBest.setColor(sColor);
				sfIsTheBest.setSize(sSize);
				sfIsTheBest.setType(sType);
				if(!(etWebsite.getText().toString()).isEmpty()){
					sfIsTheBest.setSite(sWebsite);
				}
			}
		});
	}

	
	
	private void initializeViews() {
		
		bSubmit = (Button) findViewById(R.id.bSubmit);
		etWebsite = (EditText) findViewById(R.id.etSiteFilter);
		sprColor = (Spinner) findViewById(R.id.sprColorFilter);
		sprType = (Spinner) findViewById(R.id.sprImageType);
		sprSize = (Spinner) findViewById(R.id.sprImageSize);
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etWebsite.getWindowToken(), 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}

}
