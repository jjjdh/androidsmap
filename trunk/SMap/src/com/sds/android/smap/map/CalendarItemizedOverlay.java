package com.sds.android.smap.map;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class CalendarItemizedOverlay extends SMapItemizedOverlay{
	
	
	public CalendarItemizedOverlay(Drawable marker){
		super(boundCenterBottom(marker));
		
		//overlay 생성후 넣는 작업
		GeoPoint point = new GeoPoint(19242000, -99121000);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		addOverlay(overlayitem);
	}

	@Override
	protected void onIconTap(int index) {
		// TODO Auto-generated method stub
		
	}
	

}
