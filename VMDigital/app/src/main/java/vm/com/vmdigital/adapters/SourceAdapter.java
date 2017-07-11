package vm.com.vmdigital.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elvishew.xlog.XLog;

import java.util.List;

import vm.com.vmdigital.applications.interfaces.AdapterContract;
import vm.com.vmdigital.databinding.ItemSourceBinding;
import vm.com.vmdigital.holders.SourceHolder;
import vm.com.vmdigital.models.Source;

public class SourceAdapter extends RecyclerView.Adapter<SourceHolder> {

    List<Source> mSourceList;
    AdapterContract<Source> mAdapterContract;

    public SourceAdapter(List<Source> sourceList) {
        mSourceList = sourceList;
    }

    @Override
    public SourceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSourceBinding sourceBinding = ItemSourceBinding.inflate(layoutInflater,parent,false);
        SourceHolder holder = new SourceHolder(sourceBinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(SourceHolder holder, int position) {
        holder.bind(mSourceList.get(position),this);
    }

    @Override
    public int getItemCount() {
        return mSourceList.size();
    }

    public void setAdapterContract(AdapterContract<Source> contract){
        this.mAdapterContract = contract;
    }

    public void onSelect(View view,Source source){
        if(mAdapterContract != null){
            mAdapterContract.onItemSelect(source);
        }
    }

}
