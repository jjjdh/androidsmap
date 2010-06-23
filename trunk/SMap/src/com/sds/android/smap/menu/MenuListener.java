package com.sds.android.menu;

import android.util.Log;
import android.view.View;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.map.OverlayItemsManager;

/**
 * @author kshgizmo
 *
 */
public class MenuListener implements View.OnClickListener{

	OverlayItemsManager manager;
	int id;
	
	
	public MenuListener(OverlayItemsManager manager, int id) {
		super();
		this.manager = manager;
		this.id = id;
	}

	@Override
	public void onClick(View v) {
		Log.d("kshgizmo", id+" click");
		manager.setCurrentOverlay(id);
		
		ItemizedOverlay<OverlayItem> overlay = manager.getCurrentOverlay();
		Log.d("kshgizmo","size : "+overlay.size() + " name "+ overlay.getClass().getName());
		
	}

}
