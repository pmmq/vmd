package vm.com.vmdigital.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import vm.com.vmdigital.adapters.ArticleAdapter;
import vm.com.vmdigital.databinding.ItemArticlesBinding;
import vm.com.vmdigital.models.Article;

/**
 * Created by Pub on 11/07/2017.
 */

public class ArticleHolder extends RecyclerView.ViewHolder {

    ItemArticlesBinding mBinding;

    public ArticleHolder(ItemArticlesBinding itemArticlesBinding) {
        super(itemArticlesBinding.getRoot());
        mBinding = itemArticlesBinding;
    }

    public void bind(ArticleAdapter adapter, Article article,int position){
        mBinding.setArticle(article);
        mBinding.setAdapter(adapter);
        mBinding.setPosition(position);
        mBinding.executePendingBindings();
    }
}
