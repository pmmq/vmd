package assignment.vm.com.vmdigital.applications;

import android.app.Application;

import assignment.vm.com.vmdigital.BuildConfig;
import assignment.vm.com.vmdigital.applications.components.ApplicationComponent;
import assignment.vm.com.vmdigital.applications.components.DaggerApplicationComponent;
import assignment.vm.com.vmdigital.applications.modules.ApplicationModule;
import assignment.vm.com.vmdigital.applications.modules.ServiceModule;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

public class VMApplication extends Application {
	
	private static Application mApplication;
	private ApplicationComponent mApplictionComponent;
	
	@Override
	public void onCreate() {
		super.onCreate();
		XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);
		mApplictionComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
				.serviceModule(new ServiceModule()).build();
	}
	
	public static Application getApplication() {
		return mApplication;
	}
}
