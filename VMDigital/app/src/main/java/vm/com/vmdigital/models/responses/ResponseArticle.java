package vm.com.vmdigital.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vm.com.vmdigital.applications.bases.BaseResponse;
import vm.com.vmdigital.models.Article;
import vm.com.vmdigital.models.Source;

public class ResponseArticle extends BaseResponse{
    @SerializedName("articles")
    List<Article> mArticles;
    @SerializedName("source")
    String source;
    @SerializedName("sortBy")
    Source.SortType mSortType;

    public List<Article> getArticles() {
        return mArticles;
    }

    public String getSource() {
        return source;
    }

    public Source.SortType getSortType() {
        return mSortType;
    }
}
