package com.sds.android.smap.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.R;

public class ItemizedOverlayFactory {
	public static ItemizedOverlay<OverlayItem> create(Activity caller,int id,Drawable marker){
		
		switch(id){
		case R.id.calendar :
			return new CalendarItemizedOverlay(caller,marker);
		case R.id.contact :
			return new ContactItemizedOverlay(caller,marker);
		case R.id.food :
			return new FoodItemizedOverlay(caller,marker);
		case R.id.photo :
			return new PhotoItemizedOverlay(caller,marker);
		default :
			return null;
		}
	}
}
