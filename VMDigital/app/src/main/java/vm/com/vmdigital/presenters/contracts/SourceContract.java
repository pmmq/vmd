package vm.com.vmdigital.presenters.contracts;

import java.util.List;

import vm.com.vmdigital.applications.bases.VMPresenter;
import vm.com.vmdigital.applications.bases.VMView;
import vm.com.vmdigital.models.Source;

public interface SourceContract {
	
	public interface View extends VMView{
		void onSourceFetched(List<Source> sources);
	}
	
	public interface Presenter extends VMPresenter{
		void fetchSource();
	}
	
}
