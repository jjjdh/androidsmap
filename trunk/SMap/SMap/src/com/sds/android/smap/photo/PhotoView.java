package com.sds.android.smap.photo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.sds.android.smap.R;

public class PhotoView extends Activity {
	Bitmap bmp;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.photoview);
	    ImageView image =  (ImageView) findViewById(R.id.photo);
	    String dataUri = getIntent().getExtras().getString("imgUri");
	    
	    BitmapFactory.Options option = new BitmapFactory.Options();
		option.inSampleSize = 1;

//		int size = OverlayItemsManager.OVERLAY_SIZE;
		bmp = BitmapFactory.decodeFile(dataUri, option);
//		Bitmap resized = Bitmap.createScaledBitmap(bmp, size, size,true);
	    
		image.setImageBitmap(bmp);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		bmp.recycle();
		super.onDestroy();
	}

}
