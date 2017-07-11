package vm.com.vmdigital.applications.bases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.elvishew.xlog.XLog;

public abstract class VMActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(setupView(savedInstanceState)){
			if(setupData(savedInstanceState)){
				update();
			}
		}else{
			XLog.d("something went wrong with view");
			return;
		}
	}

	protected void attachFragment(Fragment fragment, int layout) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//		transaction.addToBackStack(fragment.getClass().getName());
		transaction.replace(layout, fragment , fragment.getClass().getName());
		transaction.commitAllowingStateLoss();
	}
	
	public abstract boolean setupView(Bundle saveInstanceState);
	public abstract boolean setupData(Bundle saveInstanceState);
	public abstract void update();
	
}
