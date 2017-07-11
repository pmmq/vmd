package vm.com.vmdigital.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import vm.com.vmdigital.R;
import vm.com.vmdigital.applications.bases.VMActivity;
import vm.com.vmdigital.databinding.ActivityWebBinding;

public class ArticleLinkActivity extends VMActivity {

    public static final String URLKEY = "URLKEY";

    private ActivityWebBinding mBinding;
    private String mUrl;

    public ObservableBoolean isLoading = new ObservableBoolean(false);

    @Override
    public boolean setupView(Bundle saveInstanceState) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        mBinding.wv.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                final Uri uri = Uri.parse(url);
                return handleUri(uri);
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                final Uri uri = request.getUrl();
                return handleUri(uri);
            }

            private boolean handleUri(final Uri uri) {
                Log.i("", "Uri =" + uri);
                final String host = uri.getHost();
                final String scheme = uri.getScheme();
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                isLoading.set(true);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                isLoading.set(false);
                super.onPageFinished(view, url);
            }
        });

        return true;
    }

    @Override
    public boolean setupData(Bundle saveInstanceState) {
        mBinding.setActivity(this);
        Intent intent = getIntent();
        mUrl = intent.getExtras().getString(URLKEY, "https://www.google.co.th/");
        return true;
    }

    @Override
    public void update() {
        mBinding.wv.loadUrl(mUrl);
    }
}
