package com.sds.android.smap.map;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class FoodItemizedOverlay extends SMapItemizedOverlay{

	
	public FoodItemizedOverlay(Activity caller,Drawable marker) {
		super(caller,boundCenterBottom(marker));
		
		//overlay ������ �ִ� �۾�
		GeoPoint point = new GeoPoint(21242000, -89121000);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		addOverlay(overlayitem);
	}

	@Override
	protected void onIconTap(int index) {
		// TODO Auto-generated method stub
		
	}


}
