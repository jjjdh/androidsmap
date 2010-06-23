package com.sds.android.smap.menu;

import android.app.Activity;

import com.sds.android.smap.R;
import com.sds.android.smap.map.OverlayItemsManager;

public class MenuFactory {
public static MenuListener create(Activity caller,int id,OverlayItemsManager manager){
		switch(id){
		case R.id.calendar:
			return new CalendarMenuListener(caller,id,manager);
		case R.id.contact :
			return new ContactMenuListener(caller,id,manager);
		case R.id.food :
			return new FoodMenuListener(caller,id,manager);
		case R.id.photo :
			return new PhotoMenuListener(caller,id,manager);
		}
		return null;
	}
}
