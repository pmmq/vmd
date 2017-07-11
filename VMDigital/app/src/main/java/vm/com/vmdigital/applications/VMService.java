package vm.com.vmdigital.applications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vm.com.vmdigital.models.Source;
import vm.com.vmdigital.models.responses.ResponseArticle;
import vm.com.vmdigital.models.responses.ResponseSource;

public interface VMService {
	
	@GET(VMUrl.GET_SOURCE) Call<ResponseSource> getSource(@Query("category") String category , @Query("language") String language , @Query("country") String country) ;
	@GET(VMUrl.GET_articles) Call<ResponseArticle> getArticle(@Query("source") String source, @Query("sortBy") Source.SortType sort);
	
}
