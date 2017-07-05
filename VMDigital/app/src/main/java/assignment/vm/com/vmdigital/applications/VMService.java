package assignment.vm.com.vmdigital.applications;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VMService {
	
	@GET(VMUrl.GET_SOURCE) Call<List<Response>> getSource(@Query("category") String category , @Query("language") String language , @Query("country") String country) ;
	@GET(VMUrl.GET_articles) Call<List<Response>> getArticle(@Query("source") String source, @Query("sortBy") String sort);
	
}
