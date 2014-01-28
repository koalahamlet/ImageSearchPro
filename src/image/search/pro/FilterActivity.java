package image.search.pro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

public class FilterActivity extends Activity {

	Button bSubmit;
	EditText etWebsite;
	Spinner sprColor;
	Spinner sprType;
	Spinner sprSize;
	CheckBox cbColor;
	CheckBox cbType;
	CheckBox cbSize;
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
		setupCheckboxListeners();
				
		bSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (cbColor.isChecked()) {
					sColor=sprColor.getSelectedItem().toString();
					sfIsTheBest.setColor(sColor);	
				}else{
					sfIsTheBest.setColor("any");
				}
				
				if (cbType.isChecked()) {
					sType=sprType.getSelectedItem().toString();
					sfIsTheBest.setType(sType);	
				}else{
					sfIsTheBest.setType("any");
				}
				
				if(cbSize.isChecked()){
					sSize=sprSize.getSelectedItem().toString();
					sfIsTheBest.setSize(sSize);
				}else{
					sfIsTheBest.setSize("any");
				}
				
				
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
		cbColor = (CheckBox) findViewById(R.id.cbColor);
		cbType = (CheckBox) findViewById(R.id.cbType);
		cbSize = (CheckBox) findViewById(R.id.cbSize);
		sprSize.setEnabled(false);
		sprColor.setEnabled(false);
		sprType.setEnabled(false);
		
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etWebsite.getWindowToken(), 0);
		 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	private void setupCheckboxListeners() {
		sprSize.setEnabled(true);
		
		cbColor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					sprColor.setEnabled(true);
				}else{
					sprColor.setEnabled(false);
				}
				
			}
		});
		
		cbType.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					sprType.setEnabled(true);
				}else{
					sprType.setEnabled(false);
				}
				
			}
		});
		
		cbSize.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					sprSize.setEnabled(true);
				}else{
					sprSize.setEnabled(false);
				}
				
			}
		});

		
	}

}
