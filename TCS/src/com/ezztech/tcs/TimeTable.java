package com.ezztech.tcs;

import net.simonvt.menudrawer.MenuDrawer;
import android.app.Activity;
import android.os.Bundle;

public class TimeTable extends Activity {
	
	private MenuDrawer mDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer = MenuDrawer.attach(this);
		mDrawer.setContentView(R.layout.activity_time_table);
		mDrawer.setMenuView(R.layout.activity_main_menu);
	}

}
