package vm.com.vmdigital.applications;

import android.app.Application;

import vm.com.vmdigital.applications.components.ApplicationComponent;
import vm.com.vmdigital.applications.components.DaggerApplicationComponent;
import vm.com.vmdigital.BuildConfig;
import vm.com.vmdigital.applications.modules.ApplicationModule;
import vm.com.vmdigital.applications.modules.ServiceModule;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

public class VMApplication extends Application {
	
	private static Application mApplication;
	private ApplicationComponent mApplictionComponent;
	
	@Override
	public void onCreate() {
		super.onCreate();
		XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);
		mApplication = this;
		mApplictionComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
				.serviceModule(new ServiceModule()).build();
	}
	
	public static Application getApplication() {
		return mApplication;
	}
	
	public ApplicationComponent getApplictionComponent() {
		return mApplictionComponent;
	}
}
