package com.sds.android.smap.map;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.geo.RandomGeoPoint;
import com.sds.android.smap.photo.PhotoView;

public class PhotoItemizedOverlay extends SMapItemizedOverlay {
	static int oldnum=0;

	public PhotoItemizedOverlay(Activity caller, Drawable marker) {
		super(caller, boundCenterBottom(marker));

		// 이미지를 받아 오는 부분
		String[] proj = { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DATA,
				MediaStore.Images.Media.DISPLAY_NAME,
				MediaStore.Images.Media.LATITUDE,
				MediaStore.Images.Media.LONGITUDE,
				MediaStore.Images.Media.DATE_ADDED};
		String selection = null; //여기에 날자나 좌표로 사진을 고를수 있는 조건을 넣을수 있다.
		
		Cursor imageCursor = caller.managedQuery(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, selection, null,
				null);
		
		if (imageCursor != null && imageCursor.moveToFirst()) {
			
			String id;
			String displayName;
			String dataUri;
			String lat = null;
			String lng;
			String date;
			
			int idCol = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
			int dataUriCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
			int displayNameCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
			int latCol = imageCursor.getColumnIndex(MediaStore.Images.Media.LATITUDE);
			int lngCol = imageCursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE);
			int dateCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);
			int num=0;
			for(int i=0;i<oldnum;i++){
				imageCursor.moveToNext();
			}
			do {
				num++;
				oldnum++;
				id = imageCursor.getString(idCol);
				dataUri = imageCursor.getString(dataUriCol);
				displayName = imageCursor.getString(displayNameCol);
				lat = imageCursor.getString(latCol);
				lng = imageCursor.getString(lngCol);
				date = imageCursor.getString(dateCol);
//				Date dateText = new Date(Integer.parseInt(date));
				Log.d("kshgizmo", getClass().getSimpleName() + " : Load Photos : thumbsID=" + id + " / data="
						+ dataUri + "/ imageID=" + displayName+"/ date="+date);
				
				if (displayName != null) {
					BitmapFactory.Options option = new BitmapFactory.Options();
					option.inSampleSize = 128;

					int size = OverlayItemsManager.OVERLAY_SIZE;
					Bitmap bmp = BitmapFactory.decodeFile(dataUri, option);
					Bitmap resized = Bitmap.createScaledBitmap(bmp, size, size,true);
					BitmapDrawable icon = new BitmapDrawable(caller.getResources(), resized);
					GeoPoint point;
					if(lat==null || lng == null || lat.equals("") || lng.equals("")){
						Log.d("kshgizmo", getClass().getSimpleName() + " : Generate Random GeoPoint");
						point = RandomGeoPoint.getGeoPoint();
					}
					else{
						point = new GeoPoint(Integer.parseInt(lat),Integer.parseInt(lng));
					}
					OverlayItem overlayitem = new OverlayItem(point,dataUri,id);
					overlayitem.setMarker(boundCenterBottom(icon));
					
					addOverlay(overlayitem);
				}
			} while (imageCursor.moveToNext() && num<20);
			Log.d("kshgizmo", "total size="+num);
		}
	}

	@Override
	protected void onIconTap(int index) {
		
		OverlayItem overlayItem = createItem(index);
		Intent i ;
		if( android.os.Build.MODEL.startsWith("HTC") ) {
            // TODO: do ALL HTC devices use the same camera classes?
			i = new Intent(caller.getApplicationContext(),PhotoView.class);
			i.putExtra("imgUri", overlayItem.getTitle());
        } else {
        	i = new Intent();
    		i.setClassName("com.android.gallery","com.android.camera.ViewImage");
    		Uri uri = Uri.parse("content://media/external/images/media/"+overlayItem.getSnippet());
    		i.setData(uri);
        }
		caller.startActivity(i);
		

	}

}
