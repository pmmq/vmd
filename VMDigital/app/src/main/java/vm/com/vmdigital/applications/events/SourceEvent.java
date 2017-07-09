package vm.com.vmdigital.applications.events;

import java.util.List;

import vm.com.vmdigital.models.Source;

public class SourceEvent {
	
	public List<Source> mSources;
	
	public SourceEvent(final List<Source> pSources) {
		mSources = pSources;
	}
}
