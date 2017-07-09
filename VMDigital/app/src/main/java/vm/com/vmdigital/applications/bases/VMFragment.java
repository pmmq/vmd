package vm.com.vmdigital.applications.bases;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class VMFragment extends Fragment {
	
	@Override
	public void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}
	
}
