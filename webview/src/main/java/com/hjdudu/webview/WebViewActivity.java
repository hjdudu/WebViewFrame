package com.hjdudu.webview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hjdudu.webview.databinding.ActivityWebviewBinding;
import com.hjdudu.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebviewBinding mBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getIntent().getStringExtra(Constants.TITLE);
        String url = getIntent().getStringExtra(Constants.URL);
        boolean isShowActionBar = getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(url);

        mBinding.actionBar.setVisibility(isShowActionBar ? View.VISIBLE : View.GONE);
        mBinding.title.setText(title);

        mBinding.back.setOnClickListener(v -> {
            finish();
        });
    }


    public void setBackImage(Bitmap bitmap){
        mBinding.back.setImageBitmap(bitmap);
    }
    public void setBackImage(Drawable drawable){
        mBinding.back.setImageDrawable(drawable);
    }
}
