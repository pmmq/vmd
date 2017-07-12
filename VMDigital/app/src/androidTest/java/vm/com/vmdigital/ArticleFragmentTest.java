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
import vm.com.vmdigital.adapters.ArticleAdapter;
import vm.com.vmdigital.adapters.ContentFragmnetAdapter;
import vm.com.vmdigital.databinding.ActivityMainBinding;
import vm.com.vmdigital.databinding.FragmentArticlesBinding;
import vm.com.vmdigital.databinding.FragmentContainerBinding;
import vm.com.vmdigital.fragments.FragmentArticles;
import vm.com.vmdigital.fragments.FragmentMainContainer;
import vm.com.vmdigital.presenters.ArticlePresenter;
import vm.com.vmdigital.presenters.contracts.ArticleContract;
import vm.com.vmdigital.presenters.contracts.SourceContract;
import vm.com.vmdigital.uitls.ClassUtils;

@RunWith(AndroidJUnit4.class)
public class ArticleFragmentTest {

    FragmentArticles fragment;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);
    MainActivity mainActivity;
    @Before
    public void init() {
        fragment = new FragmentArticles();
        mainActivity = rule.getActivity();
        ActivityMainBinding mbinding = (ActivityMainBinding) ClassUtils.getPrivateVariable("mBinding",mainActivity);
        mainActivity.getSupportFragmentManager().beginTransaction().add(mbinding.contentContainer.container.getId(), fragment, null).commit();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    @Test
    public void testCorrectDataBinding(){
        FragmentArticlesBinding mBinding = (FragmentArticlesBinding) ClassUtils.getPrivateVariable("mBinding",fragment);
        Assert.assertTrue(mBinding != null);
    }

    @Test
    public void testArticleAdapter(){
        ArticleAdapter adapter = (ArticleAdapter) ClassUtils.getPrivateVariable("mAdapter",fragment);
        Assert.assertEquals(0,adapter.getItemCount());
        Assert.assertNotNull(adapter);
    }

    @Test
    public void testArticleAdapterGetColorEvenOdd(){
        ArticleAdapter adapter = (ArticleAdapter) ClassUtils.getPrivateVariable("mAdapter",fragment);
        Assert.assertEquals(mainActivity.getApplication().getResources().getColor(R.color.transparent_black_percent_5),adapter.getColor(0));
        Assert.assertEquals(mainActivity.getApplication().getResources().getColor(R.color.transparent_black_percent_15),adapter.getColor(1));
    }

    @Test
    public void testPresenter(){
        ArticlePresenter presenter = (ArticlePresenter) ClassUtils.getPrivateVariable("mPresenter",fragment);
        Assert.assertNotNull(presenter);

        ArticleContract.View view = (ArticleContract.View) ClassUtils.getPrivateVariable("mView",presenter);
        Assert.assertNotNull(view);

        presenter.onDetach();
        ArticleContract.View viewAfterFinish = (ArticleContract.View) ClassUtils.getPrivateVariable("mView",presenter);
        Assert.assertNull(viewAfterFinish);
    }
    
}
