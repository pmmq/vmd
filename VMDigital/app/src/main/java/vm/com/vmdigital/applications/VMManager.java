package vm.com.vmdigital.applications;

import com.android.internal.util.Predicate;
import com.elvishew.xlog.XLog;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import vm.com.vmdigital.applications.events.SourceEvent;
import vm.com.vmdigital.models.Source;

public class VMManager {

	private VMApplication mApplication;
	private EventBus mEventBus;
	private List<Source> mSources;

	
	public VMManager(final VMApplication pApplication, final EventBus pEventBus) {
		mApplication = pApplication;
		mEventBus = pEventBus;
		mEventBus.register(this);
	}
	
	public List<Source> getSources() {
		if(mSources == null){
			mSources = new ArrayList();
		}
		return mSources;
	}

	public Source getTechcrunchSource(){
		for (Source source:mSources) {
			if(source.getId().equals("techcrunch")){
				return source;
			}
		}
		return null;
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onSourceChange(SourceEvent pEvent){
		mSources = pEvent.mSources;
	}
}
