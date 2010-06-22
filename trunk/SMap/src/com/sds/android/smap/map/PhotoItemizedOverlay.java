package com.sds.android.smap.map;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.R;

public class PhotoItemizedOverlay extends SMapItemizedOverlay {

	public PhotoItemizedOverlay(Activity caller, Drawable marker) {
		super(caller, boundCenterBottom(marker));

		// 이미지를 받아 오는 부분
		String[] proj = { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DATA,
				MediaStore.Images.Media.DISPLAY_NAME,
				MediaStore.Images.Media.SIZE };
		Cursor imageCursor = caller.managedQuery(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, null, null,
				null);
		Log.d("kshgizmo", "image cursor : "+imageCursor);
		if (imageCursor != null && imageCursor.moveToFirst()) {
			String title;
			String thumbsID;
			String thumbsImageID;
			String thumbsData;
			String data;
			String imgSize;
			int thumbsIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
			int thumbsDataCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
			int thumbsImageIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
			int thumbsSizeCol = imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE);
			int num = 0;
			do {
				thumbsID = imageCursor.getString(thumbsIDCol);
				thumbsData = imageCursor.getString(thumbsDataCol);
				thumbsImageID = imageCursor.getString(thumbsImageIDCol);
				imgSize = imageCursor.getString(thumbsSizeCol);
				Log.d("kshgizmo", thumbsID +" / "+thumbsData+"/"+thumbsImageID+"/"+imgSize);
				num++;
				if (thumbsImageID != null) {
					BitmapFactory.Options bo = new BitmapFactory.Options();
					 bo.inSampleSize = 8;
					 Bitmap bmp = BitmapFactory.decodeFile(thumbsData, bo);
					 Bitmap resized = Bitmap.createScaledBitmap(bmp, 45, 45, true);
					 BitmapDrawable icon = new BitmapDrawable(resized);
					 GeoPoint point = new GeoPoint(18197000, -60124000);
					 OverlayItem overlayitem = new OverlayItem(point, "", "");
					 overlayitem.setMarker(icon);
					 addOverlay(overlayitem);
					 
				}
			} while (imageCursor.moveToNext());
//			 imageView.setImageBitmap(resized);

		}
		// overlay 생성후 넣는 작업
		
		// overlayitem.setMarker(marker);
		

	}

	@Override
	protected void onIconTap(int index) {
		// TODO Auto-generated method stub

	}

}
