package com.sds.android.smap.menu;

import android.app.Activity;

import com.sds.android.smap.map.OverlayItemsManager;

public class CalendarMenuListener extends MenuListener {

	public CalendarMenuListener(Activity caller,int id,OverlayItemsManager manager ) {
		super(caller ,id,manager);
	}

	@Override
	void onMenuClick() {
		setOverlay();
	}

}
