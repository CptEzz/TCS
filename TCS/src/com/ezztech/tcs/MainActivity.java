package com.ezztech.tcs;

import net.simonvt.menudrawer.MenuDrawer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	private MenuDrawer mDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer = MenuDrawer.attach(this);
		mDrawer.setContentView(R.layout.activity_main);
		mDrawer.setMenuView(R.layout.activity_main_menu);
	}
	
	public void timeTableClick(View v){
		Intent intent = new Intent(this, TimeTable.class);
		startActivity(intent);
		mDrawer.closeMenu();
	}
}
