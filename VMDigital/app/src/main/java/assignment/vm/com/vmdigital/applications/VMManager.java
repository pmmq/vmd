package assignment.vm.com.vmdigital.applications;

public class VMManager {
	
	private static VMManager mManager;
	
	public static VMManager getInstance(){
		if(mManager == null){
			mManager = new VMManager();
		}
		return mManager;
	}
	
}
