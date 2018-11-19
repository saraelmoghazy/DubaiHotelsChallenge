package com.hotels.tajawal.dubaihotels.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.hotels.tajawal.dubaihotels.BR;

/**
 * Created by Sara Elmoghazy on 12,September,2018
 */
public class BaseViewHolder<T, V extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final V binding;


    public BaseViewHolder(V binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(T item) {
        getBinding().setVariable(BR.obj, item);
        getBinding().executePendingBindings();
    }

    public V getBinding() {
        return binding;
    }
}
