package vm.com.vmdigital.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import vm.com.vmdigital.R;
import vm.com.vmdigital.applications.bases.VMActivity;
import vm.com.vmdigital.databinding.ActivityMainBinding;

public class MainActivity extends VMActivity implements NavigationView.OnNavigationItemSelectedListener {
	
	ActionBarDrawerToggle toggle;
	ActivityMainBinding mainBinding;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public boolean setupView() {
		mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		setSupportActionBar(mainBinding.toolbar);
		toggle = new ActionBarDrawerToggle(this, mainBinding.drawerLayout, mainBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mainBinding.drawerLayout.setDrawerListener(toggle);
		toggle.syncState();
		mainBinding.navView.setNavigationItemSelectedListener(this);
		return mainBinding == null ? false : true;
	}
	
	@Override
	public boolean setupData() {
		mainBinding.contentContainer.tvMain.setText("HI");
		return false;
	}
	
	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();
		mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}
}
