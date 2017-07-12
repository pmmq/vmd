package vm.com.vmdigital;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.greenrobot.eventbus.EventBus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import vm.com.vmdigital.activity.MainActivity;
import vm.com.vmdigital.adapters.SourceAdapter;
import vm.com.vmdigital.applications.VMManager;
import vm.com.vmdigital.databinding.ActivityMainBinding;
import vm.com.vmdigital.uitls.ClassUtils;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);

    MainActivity activity;
    @Before
    public void init(){
        activity = rule.getActivity();
    }

    @Test
    public void testAdapterCorrectDefine(){
        SourceAdapter adapter =  (SourceAdapter) ClassUtils.getPrivateVariable("mSourceAdapter",activity);
        Assert.assertNotNull(adapter);
        Assert.assertEquals(adapter.getItemCount(),0);
    }

    @Test
    public void testCorrectDataBinding(){
        ActivityMainBinding mbinding = (ActivityMainBinding) ClassUtils.getPrivateVariable("mBinding",activity);
        Assert.assertNotNull(mbinding);
    }

    @Test
    public void testEventBusNotNull(){
        EventBus event = (EventBus) ClassUtils.getPrivateVariable("mEvent",activity);
        Assert.assertNotNull(event);
    }

    @Test
    public void testManagerNotNull(){
        VMManager manager = (VMManager) ClassUtils.getPrivateVariable("mManager",activity);
        Assert.assertNotNull(manager);
    }
}
