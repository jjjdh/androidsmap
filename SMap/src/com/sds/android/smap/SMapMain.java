package com.sds.android.smap;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.maps.MapActivity;

public class SMapMain extends MapActivity {
	private final int MENU_CALENDAR = Menu.FIRST;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, MENU_CALENDAR, 0, getString(R.string.calendar));
		return super.onCreateOptionsMenu(menu);
	}
}