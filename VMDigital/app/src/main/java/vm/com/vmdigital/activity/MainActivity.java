package vm.com.vmdigital.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import vm.com.vmdigital.R;
import vm.com.vmdigital.adapters.SourceAdapter;
import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.VMManager;
import vm.com.vmdigital.applications.bases.VMActivity;
import vm.com.vmdigital.applications.events.SelectSourceEvent;
import vm.com.vmdigital.applications.interfaces.AdapterContract;
import vm.com.vmdigital.databinding.ActivityMainBinding;
import vm.com.vmdigital.fragments.FragmentMainContainer;
import vm.com.vmdigital.models.Source;

public class MainActivity extends VMActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    VMManager mManager;

    @Inject
    EventBus mEvent;

    ActivityMainBinding mBinding;
    SourceAdapter mSourceAdapter;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public boolean setupView(Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, mBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
        mBinding.rvMenu.setLayoutManager(new LinearLayoutManager(this));
        return mBinding == null ? false : true;
    }

    @Override
    public boolean setupData(Bundle savedInstanceState) {
        ((VMApplication) getApplication()).getApplictionComponent().inject(this);
        // create menu adapter
        if(mManager.getSources() != null){
            mSourceAdapter = new SourceAdapter(mManager.getSources());
            mSourceAdapter.setAdapterContract(new AdapterContract<Source>() {
                @Override
                public void onItemSelect(Source object) {
                    mBinding.drawerLayout.closeDrawers();
                    mEvent.post(new SelectSourceEvent(object));
                }
            });
        }
        return true;
    }

    @Override
    public void update() {
        mBinding.rvMenu.setAdapter(mSourceAdapter);
        attachFragment(FragmentMainContainer.newInstance(), mBinding.contentContainer.container.getId());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectSource(SelectSourceEvent event){
        mBinding.toolbar.setTitle(event.getSource().getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEvent.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEvent.unregister(this);
    }
}
