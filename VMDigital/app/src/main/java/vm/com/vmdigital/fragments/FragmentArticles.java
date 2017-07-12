package vm.com.vmdigital.fragments;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import vm.com.vmdigital.activity.WebActivity;
import vm.com.vmdigital.adapters.ArticleAdapter;
import vm.com.vmdigital.applications.VMApplication;
import vm.com.vmdigital.applications.VMManager;
import vm.com.vmdigital.applications.bases.VMFragment;
import vm.com.vmdigital.applications.components.ApplicationComponent;
import vm.com.vmdigital.applications.events.SelectSourceEvent;
import vm.com.vmdigital.applications.interfaces.AdapterContract;
import vm.com.vmdigital.databinding.FragmentArticlesBinding;
import vm.com.vmdigital.models.Article;
import vm.com.vmdigital.models.Source;
import vm.com.vmdigital.presenters.ArticlePresenter;
import vm.com.vmdigital.presenters.contracts.ArticleContract;

public class FragmentArticles extends VMFragment implements ArticleContract.View , AdapterContract<Article>{

    private static final String SORT_TYPE_KEY = "SORT_TYPE_KEY";
    private static final String ARTICLE_KEY = "ARTICLE_KEY";

    @Inject
    EventBus mEvent;

    @Inject
    VMManager mManager;

    private FragmentArticlesBinding mBinding;
    private ArticlePresenter mPresenter;
    private Source.SortType type;

    public ObservableArrayList<Article> mArticles = new ObservableArrayList<>();
    public ObservableBoolean isLoading = new ObservableBoolean(false);

    ArticleAdapter mAdapter;

    public static FragmentArticles newInstance(Source.SortType type) {
        Bundle args = new Bundle();
        args.putSerializable(SORT_TYPE_KEY,type);
        FragmentArticles fragment = new FragmentArticles();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
        mPresenter = new ArticlePresenter(this);
        mAdapter = new ArticleAdapter(mArticles);
        mAdapter.setAdapterContract(this);
        initArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentArticlesBinding.inflate(inflater,container,false);
        mBinding.setFragmentArticles(this);
        mBinding.rvArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvArticles.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    private void initArguments(){
        Bundle arg = getArguments();
        if(arg!=null){
            type = (Source.SortType) arg.get(SORT_TYPE_KEY);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // fetch techcrunch
        Source techcrunch = mManager.getTechcrunchSource();
        if (techcrunch != null) {
            onSelectSource(new SelectSourceEvent(techcrunch));
        }
    }

    @Override
    public void onLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    @Override
    public void onArticleFetched(List<Article> article) {
        mArticles.clear();
        if(article!=null){
            mArticles.addAll(article);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public ApplicationComponent getAppComponent() {
        return ((VMApplication)getActivity().getApplication()).getApplictionComponent();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectSource(SelectSourceEvent event){
        mPresenter.fetchArticle(event.getSource().getId(),type);
    }

    @Override
    public void onStart() {
        super.onStart();
        mEvent.register(this);
    }

    @Override
    public void onStop() {
        mEvent.unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onItemSelect(Article object) {
        Intent detailIntent = new Intent(getActivity(), WebActivity.class);
        detailIntent.putExtra(WebActivity.URLKEY,object.getUrl());
        startActivity(detailIntent);
    }

    // handle variable state
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            mArticles.clear();
            List<Article> currentArticle = savedInstanceState.getParcelableArrayList(ARTICLE_KEY);
            mArticles.addAll(currentArticle);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARTICLE_KEY,mArticles);
    }


}
