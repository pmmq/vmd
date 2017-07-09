package vm.com.vmdigital.applications;

import android.support.annotation.MainThread;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import vm.com.vmdigital.applications.events.SourceEvent;
import vm.com.vmdigital.models.Source;

public class VMManager {
	
	private static VMManager mManager;
	
	private VMApplication mApplication;
	private EventBus mEventBus;
	private List<Source> mSources;
	
	public static VMManager getInstance(VMApplication pVMApplication, EventBus pEventBus){
		if(mManager == null){
			mManager = new VMManager(pVMApplication,pEventBus);
		}
		return mManager;
	}
	
	public VMManager(final VMApplication pApplication, final EventBus pEventBus) {
		mApplication = pApplication;
		mEventBus = pEventBus;
		mEventBus.register(this);
	}
	
	
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onSourceChange(SourceEvent pEvent){
		mSources = pEvent.mSources;
	}
	
	public List<Source> getSources() {
		if(mSources == null){
			mSources = new ArrayList();
		}
		return mSources;
	}
}
