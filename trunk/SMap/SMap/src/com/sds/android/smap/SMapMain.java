package com.sds.android.smap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.sds.android.smap.geo.RandomGeoPoint;
import com.sds.android.smap.map.OverlayItemsManager;
import com.sds.android.smap.menu.MenuFactory;
import com.sds.android.smap.menu.MenuListener;

public class SMapMain extends MapActivity {
	
	private final int MENU_CALENDAR = Menu.FIRST;
	private final int MENU_SETTINGS = Menu.FIRST+1;
	private final int MENU_ADDPOI = Menu.FIRST+2;
	private OverlayItemsManager manager;
	private boolean isDisplayWeather = false;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(this);
		boolean weather = prefer.getBoolean("weather_preference", false);
		if(weather && !isDisplayWeather){
			addWeather();
		}else if(isDisplayWeather && !weather){
			removeWeather();
		}
		isDisplayWeather = weather;
		super.onResume();
	}
	private void addWeather(){
		Log.d("kshgizmo", "addWeather");
	}
	private void removeWeather(){
		Log.d("kshgizmo", "removeWeather");
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        manager = new OverlayItemsManager(this);
        
       setMenuListnener(R.id.calendar);
       setMenuListnener(R.id.contact);
       setMenuListnener(R.id.food);
       setMenuListnener(R.id.photo);
     
       setMapOption();
       
    }
    
    private void setMapOption() {
    	MapController controller ;
    	MapView mapView = (MapView)findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		controller = mapView.getController();
		controller.setZoom(15);
		LocationManager manager =  (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location arg0 = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(arg0==null){
			arg0=manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		int lat = (int) (arg0.getLatitude()*1000000);
		int lng = (int) (arg0.getLongitude()*1000000);
		Log.d("kshgizmo", lat+" / "+lng);
		GeoPoint currentPoint = new GeoPoint(lat,lng);
		RandomGeoPoint.setCurrentPoint(currentPoint);
		controller.setCenter(currentPoint);
		
	}
	private void setMenuListnener(int id){
		ImageView view = (ImageView) findViewById(id);
		view.setOnClickListener(MenuFactory.create(this, id, manager));
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_CALENDAR, 0, getString(R.string.calendar)).setIcon(R.drawable.menu_calendar);
		menu.add(0, MENU_SETTINGS, 0, "Settings").setIcon(R.drawable.menu_settings);
		menu.add(0, MENU_ADDPOI, 0, "My Location").setIcon(R.drawable.menu_mylocation);
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case MENU_CALENDAR :
			break;
		case MENU_SETTINGS :
			startActivity(new Intent(this,SettingActivity.class));
			break;
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
}