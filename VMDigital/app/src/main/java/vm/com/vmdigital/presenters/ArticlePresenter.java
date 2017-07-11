package vm.com.vmdigital.presenters;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vm.com.vmdigital.BuildConfig;
import vm.com.vmdigital.applications.VMService;
import vm.com.vmdigital.models.Source;
import vm.com.vmdigital.models.responses.ResponseArticle;
import vm.com.vmdigital.models.responses.ResponseSource;
import vm.com.vmdigital.presenters.contracts.ArticleContract;

public class ArticlePresenter implements ArticleContract.Presenter {

    ArticleContract.View mView;

    @Inject
    VMService mService;

    public ArticlePresenter(ArticleContract.View view) {
        mView = view;
        mView.getAppComponent().inject(this);
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void fetchArticle(String sourceId, Source.SortType type) {
        if(mView!=null){
            mView.onLoading(true);
        }
        Call<ResponseArticle> call = mService.getArticle(sourceId,type);
        call.enqueue(new Callback<ResponseArticle>() {
            @Override
            public void onResponse(Call<ResponseArticle> call, Response<ResponseArticle> response) {
                if(mView!=null){
                    mView.onLoading(false);
                    if(response.code() == 400){
                        mView.onArticleFetched(null);
                    }else if(response.code() == 200){
                        mView.onArticleFetched(response.body().getArticles());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseArticle> call, Throwable t) {
                if(mView!=null){
                    mView.onLoading(false);
                    mView.onArticleFetched(null);
                }
            }
        });
    }
}
