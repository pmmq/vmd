package vm.com.vmdigital.applications.bases;

import com.google.gson.annotations.SerializedName;

import vm.com.vmdigital.models.responses.ResponseSource;

/**
 * Created by Pub on 11/07/2017.
 */

public class BaseResponse {
    @SerializedName("status")
    ResponseSource.Status status;
    @SerializedName("code")
    String code;
    @SerializedName("message")
    String message;

    public ResponseSource.Status getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
