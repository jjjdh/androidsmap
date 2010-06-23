package com.sds.android.smap.menu;

import android.app.Activity;

import com.sds.android.smap.map.OverlayItemsManager;

public class ContactMenuListener extends MenuListener{

	public ContactMenuListener(Activity caller,int id,OverlayItemsManager manager ) {
		super(caller ,id,manager);
	}

	@Override
	void onMenuClick() {
		// TODO Auto-generated method stub
		setOverlay();
	}

}
