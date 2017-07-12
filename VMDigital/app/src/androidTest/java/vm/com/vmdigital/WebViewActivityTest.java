package vm.com.vmdigital;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vm.com.vmdigital.activity.WebActivity;
import vm.com.vmdigital.uitls.ClassUtils;

@RunWith(AndroidJUnit4.class)
public class WebViewActivityTest {
    @Rule
    public ActivityTestRule<WebActivity> rule = new ActivityTestRule<WebActivity>(WebActivity.class,true,false);

    WebActivity mWebActivity;

    @Test
    public void testUrlCorrect(){
        Context context = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(WebActivity.URLKEY,"url");
        mWebActivity = rule.launchActivity(intent);
        String url =  (String) ClassUtils.getPrivateVariable("mUrl",mWebActivity);
        Assert.assertEquals("url",url);
    }
}



