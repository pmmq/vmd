package vm.com.vmdigital;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vm.com.vmdigital.activity.MainActivity;
import vm.com.vmdigital.adapters.ContentFragmnetAdapter;
import vm.com.vmdigital.databinding.ActivityMainBinding;
import vm.com.vmdigital.databinding.FragmentContainerBinding;
import vm.com.vmdigital.fragments.FragmentMainContainer;
import vm.com.vmdigital.uitls.ClassUtils;

@RunWith(AndroidJUnit4.class)
public class ContainerFragmentTest {

    FragmentMainContainer fragment;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init() {
        fragment = new FragmentMainContainer();
        MainActivity mainActivity = rule.getActivity();
        ActivityMainBinding mbinding = (ActivityMainBinding) ClassUtils.getPrivateVariable("mBinding",mainActivity);
        mainActivity.getSupportFragmentManager().beginTransaction().add(mbinding.contentContainer.container.getId(), fragment, null).commit();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    @Test
    public void testCorrectDataBinding(){
        FragmentContainerBinding mBinding = (FragmentContainerBinding) ClassUtils.getPrivateVariable("mBinding",fragment);
        Assert.assertTrue(mBinding != null);
    }


}
