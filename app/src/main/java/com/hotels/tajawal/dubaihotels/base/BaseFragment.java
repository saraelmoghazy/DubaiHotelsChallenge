package com.hotels.tajawal.dubaihotels.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.hotels.tajawal.dubaihotels.R;
import com.hotels.tajawal.dubaihotels.common_views.TajawalErrorView;
import com.hotels.tajawal.dubaihotels.remote.RetrofitException;
import com.hotels.tajawal.dubaihotels.util.ViewAnimationUtilities;

/**
 * Created by Sara Elmoghazy.
 */
public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel>
        extends Fragment {

    private AppCompatActivity mcontext;
    private V viewModel;
    private T viewDataBinding;
    private View rootView;
    private NestedScrollView scrollView;
    private ViewGroup oldView = null;
    private Dialog loadingIndicator = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();

    }

    private void observeError() {

        viewModel.getHasError().observe(getmContext(),
                hasError -> {
                    if (hasError != null) handleError(hasError);
                });

    }

    private void observeLoading() {
        viewModel.getIsLoading().observe(getmContext(),
                isLoading -> {
                    if (isLoading) {
                        showLoadingIndicator();
                    } else {
                        hideLoadingIndicator();
                    }
                });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        rootView = viewDataBinding.getRoot();
        onViewInflated(savedInstanceState, rootView);
        scrollView = getScrollView();
        if (scrollView != null) {
            oldView = (ViewGroup) scrollView.getChildAt(0);
        }
        if (getmContext() != null) {
            observeLoading();
            observeError();
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mcontext = null;
    }

    protected abstract void onViewInflated(Bundle savedInstanceState, View rootView);

    protected void switchTheView() {
        if (oldView != null) {
            toggleErrorContainerView(oldView);
        }
    }

    private void tryAgain() {
        switchTheView();
        restart();
    }

    public abstract NestedScrollView getScrollView();

    public T getViewDataBinding() {
        return viewDataBinding;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding.setVariable(getBindingVariable(), viewModel);
        viewDataBinding.executePendingBindings();
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    public void restart() {
        viewModel.restart();
    }

    public void handleError(RetrofitException error) {
        TajawalErrorView.Builder builder = new TajawalErrorView.Builder()
                .retryClickListener(view -> tryAgain()).msg(error.getMessage());
        if (getmContext() != null)
            addErrorView(builder.build(getmContext()));
    }

    protected void toggleErrorContainerView(View view) {
        scrollView = getScrollView();
        if (scrollView != null) {
            scrollView.removeAllViews();
            scrollView.addView(view, 0);
            ViewAnimationUtilities.expandView(view);
        }
    }

    public void addErrorView(TajawalErrorView view) {
        if (view != null) {
            toggleErrorContainerView(view);
        }
    }

    @Nullable
    public AppCompatActivity getmContext() {
        return mcontext;
    }


    public void showLoadingIndicator() {
        if (loadingIndicator == null && isAdded()) {
            loadingIndicator = new Dialog(getContext());
            loadingIndicator.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingIndicator.setContentView(R.layout.partial_blocking_loading_indicator);
            loadingIndicator.setCancelable(false);
            loadingIndicator.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loadingIndicator.show();
        }
    }

    /**
     * Hide previously displayed progress indicator to indicates that the process was finished.
     */
    public void hideLoadingIndicator() {
        if (loadingIndicator != null) {
            loadingIndicator.dismiss();
            loadingIndicator = null;
        }
    }
}
