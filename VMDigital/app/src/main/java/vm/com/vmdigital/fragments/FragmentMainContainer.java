package vm.com.vmdigital.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import vm.com.vmdigital.R;
import vm.com.vmdigital.adapters.ContentFragmnetAdapter;
import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.VMManager;
import vm.com.vmdigital.applications.bases.VMFragment;
import vm.com.vmdigital.applications.events.SelectSourceEvent;
import vm.com.vmdigital.databinding.FragmentContainerBinding;
import vm.com.vmdigital.models.Source;

public class FragmentMainContainer extends VMFragment implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    @Inject
    VMManager mManager;
    @Inject
    EventBus mEventBus;

    FragmentContainerBinding mBinding;

    private final int TOP_MENU_ID = 0;
    private final int LASTEST_MENU_ID = 1;

    ContentFragmnetAdapter mFragmnetAdapter;

    public static FragmentMainContainer newInstance() {
        Bundle args = new Bundle();
        FragmentMainContainer fragment = new FragmentMainContainer();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((VMApplication) getActivity().getApplication()).getApplictionComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentContainerBinding.inflate(inflater, container, false);
        initialize();
        return mBinding.getRoot();
    }

    private void initialize() {
        mFragmnetAdapter = new ContentFragmnetAdapter(getChildFragmentManager());
        mBinding.pagerContainer.setAdapter(mFragmnetAdapter);
        mBinding.navMain.setOnNavigationItemSelectedListener(this);
        mBinding.pagerContainer.addOnPageChangeListener(this);
        mBinding.pagerContainer.setOffscreenPageLimit(1);
    }

    // communicate button nav and pager
    @Override
    public void onPageSelected(int position) {
        mBinding.navMain.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_top:
                mBinding.pagerContainer.setCurrentItem(0);
                break;
            case R.id.action_lastest:
                mBinding.pagerContainer.setCurrentItem(1);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
