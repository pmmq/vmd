package vm.com.vmdigital.applications.modules;

import javax.inject.Singleton;

import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.VMManager;
import dagger.Module;
import dagger.Provides;

import org.greenrobot.eventbus.EventBus;

@Module
public class ApplicationModule {

    VMApplication mVMApplication;

    EventBus mEventBus;

    public ApplicationModule(VMApplication vmApplication) {
        mVMApplication = vmApplication;
    }

    @Provides
    @Singleton
    VMManager providesVMManager(EventBus pEventBus) {
        return new VMManager(mVMApplication, pEventBus);
    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        if (mEventBus == null) {
            mEventBus = EventBus.builder().build();
        }
        return mEventBus;
    }
}
