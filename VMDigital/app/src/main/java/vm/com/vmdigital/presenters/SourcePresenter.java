package vm.com.vmdigital.presenters;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.elvishew.xlog.XLog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.VMService;
import vm.com.vmdigital.models.responses.ResponseSource;
import vm.com.vmdigital.presenters.contracts.SourceContract;

public class SourcePresenter implements SourceContract.Presenter{
	
	SourceContract.View mView;
	
	@Inject VMService mService;
	
	public SourcePresenter(final SourceContract.View pView) {
		mView = pView;
		((VMApplication)VMApplication.getApplication()).getApplictionComponent().inject(this);
	}
	
	@Override
	public void fatchSource() {
		mView.onLoading(true);
		Call<ResponseSource> call = mService.getSource("","","");
		call.enqueue(new Callback<ResponseSource>() {
			@Override
			public void onResponse(final Call<ResponseSource> call, final Response<ResponseSource> response) {
				if(mView!=null){
					mView.onLoading(false);
					if(response.body().getStatus() == ResponseSource.Status.OK){
						mView.onSourceFetched(response.body().getSourceList());
					}else if(response.body().getStatus() == ResponseSource.Status.ERROR){
						XLog.d(response.body().getMessage());
						mView.onSourceFetched(null);
					}
				}
			}
			
			@Override
			public void onFailure(final Call<ResponseSource> call, final Throwable t) {
				if(mView!=null){
					mView.onLoading(false);
					mView.onSourceFetched(null);
				}
			}
		});
	}
	
	@Override
	public void onDetach() {
		mView = null;
	}
}
