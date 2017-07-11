package vm.com.vmdigital.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import vm.com.vmdigital.R;
import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.interfaces.AdapterContract;
import vm.com.vmdigital.databinding.ItemArticlesBinding;
import vm.com.vmdigital.holders.ArticleHolder;
import vm.com.vmdigital.models.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {

    List<Article> mArticleList;
    AdapterContract<Article> mAdapterContract;

    public ArticleAdapter(List<Article> articleList) {
        mArticleList = articleList;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemArticlesBinding articleBinding = ItemArticlesBinding.inflate(layoutInflater, parent, false);
        ArticleHolder holder = new ArticleHolder(articleBinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        holder.bind(this, mArticleList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    public void setAdapterContract(AdapterContract<Article> contract) {
        this.mAdapterContract = contract;
    }

    public void onSelect(View view, Article article) {
        if (mAdapterContract != null) {
            mAdapterContract.onItemSelect(article);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(AppCompatImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    public int getColor(int position){
        if(position%2 == 0){
            return VMApplication.getApplication().getResources().getColor(R.color.transparent_black_percent_5);
        }else{
            return VMApplication.getApplication().getResources().getColor(R.color.transparent_black_percent_15);
        }
    }
}
