package image.search.pro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

import com.loopj.android.image.SmartImageView;

public class ImageDisplayActivity extends Activity {

	SmartImageView ivImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		ImageResult image = (ImageResult) getIntent().getSerializableExtra("result");
		ivImage = (SmartImageView) findViewById(R.id.ivResult);
		ivImage.setImageUrl(image.getFullUrl());
		String strEmail = "mike@grio.com";
		
		
		
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
		emailIntent.setType("application/image");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, strEmail); 
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Test Subject"); 
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From My App"); 
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/Myimage.jpeg"));
		startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

}
