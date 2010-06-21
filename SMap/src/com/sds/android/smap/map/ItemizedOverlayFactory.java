package com.sds.android.smap.map;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.R;

public class ItemizedOverlayFactory {
	public static ItemizedOverlay<OverlayItem> create(int id,Drawable marker){
		
		switch(id){
		case R.id.calendar :
			return new CalendarItemizedOverlay(marker);
		case R.id.contact :
			return new ContactItemizedOverlay(marker);
		case R.id.food :
			return new FoodItemizedOverlay(marker);
		case R.id.photo :
			return new PhotoItemizedOverlay(marker);
		default :
			return null;
		}
	}
}
