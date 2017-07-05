package assignment.vm.com.vmdigital.applications.components;

import javax.inject.Singleton;

import assignment.vm.com.vmdigital.activity.MainActivity;
import assignment.vm.com.vmdigital.applications.VMApplication;
import assignment.vm.com.vmdigital.applications.modules.ApplicationModule;
import assignment.vm.com.vmdigital.applications.modules.ServiceModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class})
public interface ApplicationComponent {
	
	void inject (MainActivity mainActivity);
	void inject (VMApplication vmapplication);
	
}
