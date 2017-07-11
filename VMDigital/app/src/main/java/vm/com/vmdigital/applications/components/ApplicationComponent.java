package vm.com.vmdigital.applications.components;

import android.app.FragmentContainer;

import javax.inject.Singleton;

import vm.com.vmdigital.activity.MainActivity;
import vm.com.vmdigital.activity.SplashActivity;
import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.modules.ApplicationModule;
import vm.com.vmdigital.applications.modules.ServiceModule;
import dagger.Component;
import vm.com.vmdigital.fragments.FragmentArticles;
import vm.com.vmdigital.fragments.FragmentMainContainer;
import vm.com.vmdigital.presenters.ArticlePresenter;
import vm.com.vmdigital.presenters.SourcePresenter;

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class})
public interface ApplicationComponent {
	void inject (VMApplication vmapplication);
	void inject (MainActivity mainActivity);
	void inject (SplashActivity pSplashActivity);
	void inject (FragmentMainContainer fragmentContainer);
	void inject (FragmentArticles fragmentArticles);
	void inject (SourcePresenter pSourcePresenter);
	void inject (ArticlePresenter articlePresenter);
}
