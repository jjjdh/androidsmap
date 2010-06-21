package com.sds.android.smap.map;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.R;

public class ItemizedOverlayFactory {
	public static ItemizedOverlay<OverlayItem> create(Context context,int id,Drawable marker){
		
		switch(id){
		case R.id.calendar :
			return new CalendarItemizedOverlay(context,marker);
		case R.id.contact :
			return new ContactItemizedOverlay(context,marker);
		case R.id.food :
			return new FoodItemizedOverlay(context,marker);
		case R.id.photo :
			return new PhotoItemizedOverlay(context,marker);
		default :
			return null;
		}
	}
}
