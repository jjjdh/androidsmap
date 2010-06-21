package com.sds.android.smap.map;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.R;

public class ItemizedOverlayFactory {
	public static ItemizedOverlay<OverlayItem> create(int id){
		
		switch(id){
		case R.id.calendar :
			return new CalendarItemizedOverlay();
		case R.id.contact :
			return new ContactItemizedOverlay();
		case R.id.food :
			return new FoodItemizedOverlay();
		case R.id.photo :
			return new PhotoItemizedOverlay();
		default :
			return null;
		}
	}
}
