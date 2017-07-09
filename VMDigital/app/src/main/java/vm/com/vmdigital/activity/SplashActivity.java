package vm.com.vmdigital.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import vm.com.vmdigital.R;
import vm.com.vmdigital.applications.components.ApplicationComponent;
import org.greenrobot.eventbus.EventBus;
import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.bases.VMActivity;
import vm.com.vmdigital.applications.events.SourceEvent;
import vm.com.vmdigital.databinding.ActivitySplashBinding;
import vm.com.vmdigital.models.Source;
import vm.com.vmdigital.presenters.SourcePresenter;
import vm.com.vmdigital.presenters.contracts.SourceContract;

public class SplashActivity extends VMActivity implements SourceContract.View{
	
	@Inject EventBus mEventBus;
	ActivitySplashBinding mBinding;
	SourcePresenter mSourcePresenter;
	public ObservableBoolean isLoading = new ObservableBoolean(false);
	
	
	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public boolean setupView() {
		mBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash);
		mBinding.setSplash(this);
		return true;
	}
	
	@Override
	public boolean setupData() {
		getAppComponent().inject(this);
		mSourcePresenter = new SourcePresenter(this);
		mSourcePresenter.fatchSource();
		return true;
	}
	
	@Override
	public ApplicationComponent getAppComponent() {
		return ((VMApplication)getApplication()).getApplictionComponent();
	}
	 
	@Override
	public void onLoading(boolean isLoading) {
		this.isLoading.set(isLoading);
	}
	
	@Override
	public void onSourceFetched(final List<Source> sources) {
		if(sources != null){
			mEventBus.post(new SourceEvent(sources));
			// Intent newIntent = new Intent(this,MainActivity.class);
			// startActivity(newIntent);
		}
	}
}
