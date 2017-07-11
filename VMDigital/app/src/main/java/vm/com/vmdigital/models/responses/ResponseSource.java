package vm.com.vmdigital.models.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import vm.com.vmdigital.applications.bases.BaseResponse;
import vm.com.vmdigital.models.Source;

public class ResponseSource extends BaseResponse {
	@SerializedName("sources")
	List<Source> mSourceList;
	
	public List<Source> getSourceList() {
		return mSourceList;
	}
	
	public enum Status{
		@SerializedName("ok")
		OK(0),
		@SerializedName("error")
		ERROR(1);
		private final int value;
		
		public int getValue() {
			return value;
		}
		private Status (int value){
			this.value = value;
		}
	}
}
