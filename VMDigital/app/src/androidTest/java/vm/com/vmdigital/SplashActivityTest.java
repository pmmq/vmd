package vm.com.vmdigital;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vm.com.vmdigital.activity.SplashActivity;
import vm.com.vmdigital.databinding.ActivitySplashBinding;
import vm.com.vmdigital.presenters.SourcePresenter;
import vm.com.vmdigital.presenters.contracts.SourceContract;
import vm.com.vmdigital.uitls.ClassUtils;

@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> rule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    private SplashActivity mSplashActivity;

    @Before
    public void init(){
        mSplashActivity = rule.getActivity();
    }

    @Test
    public void testCorrectPresenter(){
        SourcePresenter presenter = (SourcePresenter) ClassUtils.getPrivateVariable("mSourcePresenter",mSplashActivity);
        Assert.assertNotNull(presenter);

        SourceContract.View view = (SourceContract.View) ClassUtils.getPrivateVariable("mView",presenter);
        Assert.assertNotNull(view);

        presenter.onDetach();
        SourceContract.View viewAfterFinish = (SourceContract.View) ClassUtils.getPrivateVariable("mView",presenter);
        Assert.assertNull(viewAfterFinish);
    }

    @Test
    public void testCorrectDataBinding(){
        ActivitySplashBinding mbinding = (ActivitySplashBinding) ClassUtils.getPrivateVariable("mBinding",mSplashActivity);
        Assert.assertNotNull(mbinding);
    }

}
