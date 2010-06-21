package com.sds.android.smap.map;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class FoodItemizedOverlay extends SMapItemizedOverlay{

	
	public FoodItemizedOverlay(Drawable marker) {
		super(boundCenterBottom(marker));
		
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
