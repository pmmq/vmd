package vm.com.vmdigital.models.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import vm.com.vmdigital.models.Source;

public class ResponseSource {
	@SerializedName("status")
	Status status;
	@SerializedName("code")
	String code;
	@SerializedName("message")
	String message;
	@SerializedName("sources")
	List<Source> mSourceList;
	
	public Status getStatus() {
		return status;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
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
