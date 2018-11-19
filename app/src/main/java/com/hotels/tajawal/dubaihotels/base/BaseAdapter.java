package com.hotels.tajawal.dubaihotels.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public abstract class BaseAdapter<T, V extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseViewHolder<T, V>> {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        V binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), getLayout(), parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        final T item = getItemForPosition(position);
        holder.bind(item);
    }

    protected abstract T getItemForPosition(int position);

    protected abstract int getLayout();

}

