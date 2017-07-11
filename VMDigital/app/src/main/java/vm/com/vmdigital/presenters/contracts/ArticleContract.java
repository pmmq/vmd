package vm.com.vmdigital.presenters.contracts;

import java.util.List;
import vm.com.vmdigital.applications.bases.VMPresenter;
import vm.com.vmdigital.applications.bases.VMView;
import vm.com.vmdigital.models.Article;
import vm.com.vmdigital.models.Source;

public interface ArticleContract {

    public interface View extends VMView {
        void onArticleFetched(List<Article> article);
    }

    public interface Presenter extends VMPresenter {
        void fetchArticle(String articleId, Source.SortType type);
    }
}
