package vm.com.vmdigital.applications.bases;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public abstract class VMFragment extends Fragment {

	protected void attachFragment(Fragment fragment, int layout) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.addToBackStack(fragment.getClass().getName());
		transaction.replace(layout, fragment , fragment.getClass().getName());
		transaction.commit();
	}
}
