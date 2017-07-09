package vm.com.vmdigital.applications.bases;

import vm.com.vmdigital.applications.components.ApplicationComponent;

public interface VMView {
	ApplicationComponent getAppComponent();
	void onLoading(boolean isLoading);
}
