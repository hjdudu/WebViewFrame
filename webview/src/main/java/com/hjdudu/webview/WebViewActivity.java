package com.hjdudu.webview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hjdudu.webview.databinding.ActivityWebviewBinding;
import com.hjdudu.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebviewBinding mBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getIntent().getStringExtra(Constants.TITLE);
        String url = getIntent().getStringExtra(Constants.URL);
        String name = getIntent().getStringExtra(Constants.JAVASCRIPT_INTERFACE_NAME);
        boolean isShowActionBar = getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);

        mBinding.actionBar.setVisibility(isShowActionBar ? View.VISIBLE : View.GONE);
        mBinding.title.setText(title);

        mBinding.back.setOnClickListener(v -> {
            finish();
        });


        //添加fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        WebViewFragment webViewFragment = WebViewFragment.newInstance(url, true, name);
        ft.replace(R.id.web_view_fragment, webViewFragment).commit();
    }

    /**
     * 设置返回值按钮
     *
     * @param bitmap
     */
    public void setBackImage(Bitmap bitmap) {
        mBinding.back.setImageBitmap(bitmap);
    }

    /**
     * 设置返回值按钮
     *
     * @param drawable
     */
    public void setBackImage(Drawable drawable) {
        mBinding.back.setImageDrawable(drawable);
    }


    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }
}
