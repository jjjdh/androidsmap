package com.sds.android.smap;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.google.android.maps.MapActivity;
import com.sds.android.menu.MenuListener;
import com.sds.android.smap.map.OverlayItemsManager;

public class SMapMain extends MapActivity {
	
	private final int MENU_CALENDAR = Menu.FIRST;
	private final int MENU_SETTING = Menu.FIRST+1;
	 OverlayItemsManager manager;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        manager = new OverlayItemsManager(this);
        
       setMenuListnener(R.id.calendar);
       setMenuListnener(R.id.contact);
       setMenuListnener(R.id.food);
       setMenuListnener(R.id.photo);
      
    }
    
    private void setMenuListnener(int id){
		ImageView view = (ImageView) findViewById(id);
		view.setOnClickListener(new MenuListener(manager,id));
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_CALENDAR, 0, getString(R.string.calendar));
		menu.add(0, MENU_SETTING, 0, getString(R.string.calendar));
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
}