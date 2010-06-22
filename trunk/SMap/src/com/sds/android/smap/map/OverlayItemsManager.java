package com.sds.android.smap.map;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.R;

/**
 * @author kshgizmo
 *
 */
public class OverlayItemsManager {
	ItemizedOverlay<OverlayItem> currentOverlay = null;
	int currentId ;
	HashMap<String,ItemizedOverlay<OverlayItem>> cacheMap = new HashMap<String,ItemizedOverlay<OverlayItem>>();
	Context caller;
	MapView mapView;
	List<Overlay> mapOverlays;
	HashMap<String, Drawable> drawableMap;
	

	public OverlayItemsManager() {

	}

	public OverlayItemsManager(Activity caller) {
		// TODO Auto-generated constructor stub
		this.caller = caller;
		
		initDrawable();
		
		mapView = (MapView) caller.findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		mapOverlays = mapView.getOverlays();

	}
	
	private void initDrawable() {
		drawableMap = new HashMap<String, Drawable>();
		drawableMap.put(String.valueOf(R.id.calendar), caller.getResources().getDrawable(R.drawable.calendar));
		drawableMap.put(String.valueOf(R.id.contact), caller.getResources().getDrawable(R.drawable.contact));
		drawableMap.put(String.valueOf(R.id.food), caller.getResources().getDrawable(R.drawable.food));
		drawableMap.put(String.valueOf(R.id.photo), caller.getResources().getDrawable(R.drawable.photo));
	}
	private Drawable getDrawable(int id){
		return drawableMap.get(String.valueOf(id));
	}

	public void setCurrentOverlay(int id) {
		
		
		if(currentOverlay!=null){
			Log.d("kshgizmo","remove "+currentOverlay.getClass().getSimpleName());
			cacheMap.put(String.valueOf(currentId), currentOverlay);
			mapOverlays.remove(currentOverlay);
		}
		
		if(cacheMap.containsKey(String.valueOf(id)) && !isChaged()){
			Log.d("kshgizmo","get "+currentOverlay.getClass().getSimpleName());
			currentOverlay = cacheMap.get(String.valueOf(id));
		}else{
			Drawable drawable = getDrawable(id);
			currentOverlay =  ItemizedOverlayFactory.create(caller.getApplicationContext(),id,drawable);
			Log.d("kshgizmo","create "+currentOverlay.getClass().getSimpleName());
		}
		currentId = id;
		mapOverlays.add(currentOverlay);
		mapView.invalidate();
	}
	
	private boolean isChaged() {
		//OverLay 가 변경된경우 true 를 리턴한다.
		return false;
	}

	public ItemizedOverlay<OverlayItem> getCurrentOverlay(){
		return currentOverlay;
	}
	

}
