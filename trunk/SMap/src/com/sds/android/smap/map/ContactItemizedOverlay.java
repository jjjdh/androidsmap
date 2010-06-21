package com.sds.android.smap.map;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class ContactItemizedOverlay extends ItemizedOverlay<OverlayItem>{

	private static Drawable defaultMarker = null;
	
	public ContactItemizedOverlay() {
		super(defaultMarker);
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
