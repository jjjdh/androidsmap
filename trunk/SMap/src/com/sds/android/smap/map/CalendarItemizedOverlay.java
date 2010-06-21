package com.sds.android.smap.map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.sds.android.calendar.CalendarUtils;

public class CalendarItemizedOverlay extends SMapItemizedOverlay{
	
	
	public CalendarItemizedOverlay(Context context,Drawable marker){
		super(context,boundCenterBottom(marker));
		
		
		CalendarUtils util = new CalendarUtils();
		Log.d("kshgizmo", "log : "+util.getCalendarInfo("", ""));
		
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
