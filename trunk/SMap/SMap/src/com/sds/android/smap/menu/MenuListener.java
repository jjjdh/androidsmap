package com.sds.android.smap.menu;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.map.OverlayItemsManager;

/**
 * @author kshgizmo
 *
 */
public abstract class MenuListener implements View.OnClickListener{

	OverlayItemsManager manager;
	int id;
	Activity caller;
	
	
	public MenuListener(Activity caller, int id,OverlayItemsManager manager) {
		super();
		this.manager = manager;
		this.id = id;
		this.caller = caller;
	}

	@Override
	public void onClick(View v) {
		Log.d("kshgizmo", getClass().getSimpleName()+" click");
		onMenuClick();
	}
	abstract void onMenuClick();
	
	public void setOverlay(){
		manager.setCurrentOverlay(id);
		ItemizedOverlay<OverlayItem> overlay = manager.getCurrentOverlay();
		Log.d("kshgizmo","size : "+overlay.size() + " name "+ overlay.getClass().getName());
	}

}
