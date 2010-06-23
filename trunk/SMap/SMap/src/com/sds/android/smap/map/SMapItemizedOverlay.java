package com.sds.android.smap.map;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author kshgizmo
 *
 */
public abstract class SMapItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	protected Context mContext;
	protected Activity caller;
	
	public SMapItemizedOverlay(Activity caller,Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		this.caller = caller;
		this.mContext = caller.getApplicationContext();
	}

	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	@Override
	protected boolean onTap(int index) {
		Log.d("kshgizmo", "name : "+getClass().getSimpleName()+" onTap : "+index);
		onIconTap(index);
		return super.onTap(index);
	}

	abstract protected void onIconTap(int index);

}
