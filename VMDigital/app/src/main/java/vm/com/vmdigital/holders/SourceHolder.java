package vm.com.vmdigital.holders;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import vm.com.vmdigital.adapters.SourceAdapter;
import vm.com.vmdigital.databinding.ItemSourceBinding;
import vm.com.vmdigital.models.Source;

public class SourceHolder extends RecyclerView.ViewHolder {

    public TextViewCompat tvTitle;
    public ItemSourceBinding mBinding;

    public SourceHolder(ItemSourceBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(Source source, SourceAdapter adapter) {
        mBinding.setSource(source);
        mBinding.setAdapter(adapter);
        mBinding.executePendingBindings();
    }

}
