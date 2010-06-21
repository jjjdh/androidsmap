package com.sds.android.smap.map;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class PhotoItemizedOverlay extends SMapItemizedOverlay{

	
	public PhotoItemizedOverlay(Context context,Drawable marker) {
		super(context,boundCenterBottom(marker));
		
		//overlay 생성후 넣는 작업
		GeoPoint point = new GeoPoint(18197000, -60124000);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		addOverlay(overlayitem);

	}

	@Override
	protected void onIconTap(int index) {
		// TODO Auto-generated method stub
		
	}

}
