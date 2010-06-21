package com.sds.android.smap.map;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class PhotoItemizedOverlay extends SMapItemizedOverlay{

	
	public PhotoItemizedOverlay(Drawable marker){
		super(boundCenterBottom(marker));
		
		//overlay ������ �ִ� �۾�
		GeoPoint point = new GeoPoint(18197000, -60124000);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		addOverlay(overlayitem);

	}

	@Override
	protected void onIconTap(int index) {
		// TODO Auto-generated method stub
		
	}

}
