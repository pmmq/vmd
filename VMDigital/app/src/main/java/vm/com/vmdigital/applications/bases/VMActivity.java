package vm.com.vmdigital.applications.bases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.elvishew.xlog.XLog;

public abstract class VMActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(setupView()){
			setupData();
		}else{
			XLog.d("something went wrong with view");
			return;
		}
	}
	
	public abstract boolean setupView();
	public abstract boolean setupData();
	
}
