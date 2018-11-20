package com.hotels.tajawal.dubaihotels.common_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hotels.tajawal.dubaihotels.R;

/**
 * Error view for retry failed network calls.
 * Created by Sara Elmoghazy.
 */
public class TajawalErrorView extends ConstraintLayout {

    TextView txtErrorMessage;
    Button btnRetry;

    private Builder builder;

    public TajawalErrorView(Context context, Builder builder) {
        super(context);

        this.builder = builder;
        inflateErrorView();
        invalidateViews();
    }

    public TajawalErrorView(Context context) {
        super(context);

        inflateErrorView();
    }

    public TajawalErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflateErrorView();
    }

    public TajawalErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflateErrorView();
    }

    private void inflateErrorView() {

        View rootView = inflate(getContext(), R.layout.partial_no_connection, this);
        btnRetry = rootView.findViewById(R.id.btn_retry);
        txtErrorMessage = rootView.findViewById(R.id.txt_error);
    }

    public void invalidateViews() {
        setMsg(builder.msg);
        setRetryBtnListener();
    }


    private void setMsg(String errorMessage) {
        txtErrorMessage.setText(errorMessage);
    }

    private void setRetryBtnListener() {
        btnRetry.setOnClickListener(builder.retryClickListener);
    }

    public static class Builder {

        private String msg;
        private OnClickListener retryClickListener;

        public Builder msg(String msg) {
            this.msg = msg;

            return this;
        }

        public Builder retryClickListener(OnClickListener listener) {
            this.retryClickListener = listener;

            return this;
        }

        public TajawalErrorView build(@NonNull Context context) {
            return new TajawalErrorView(context, this);
        }
    }
}
