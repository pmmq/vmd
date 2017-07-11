package vm.com.vmdigital.adapters;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vm.com.vmdigital.fragments.FragmentArticles;
import vm.com.vmdigital.models.Source;


public class ContentFragmnetAdapter extends FragmentPagerAdapter {

    BottomNavigationView mBottomNavigationView;

    public ContentFragmnetAdapter(FragmentManager fm ) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = FragmentArticles.newInstance(Source.SortType.TOP);
                break;
            case 1:
                fragment = FragmentArticles.newInstance(Source.SortType.LATEST);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setBottomNavigationItemView(BottomNavigationView bottomNavigationItemView) {
        mBottomNavigationView = bottomNavigationItemView;
    }
}
