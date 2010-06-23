package com.sds.android.smap;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author kshgizmo
 *
 */
public class SettingActivity extends PreferenceActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.settings);
	}

}
