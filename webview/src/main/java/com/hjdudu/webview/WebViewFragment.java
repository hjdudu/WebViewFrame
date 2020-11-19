package com.hjdudu.webview;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.hjdudu.base.loadsir.ErrorCallback;
import com.hjdudu.base.loadsir.LoadingCallback;
import com.hjdudu.webview.databinding.FragmentWebviewBinding;
import com.hjdudu.webview.utils.Constants;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {
    private static String name;
    private String mUrl;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    //下拉刷新
    private boolean mCanNativeRefresh;

    private boolean mIsError = false;

    private static final String TAG = WebViewFragment.class.getSimpleName();

    public static WebViewFragment newInstance(String url, boolean canNativeRefresh, String name) {
        WebViewFragment.name = name;
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh);
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.URL);
            mCanNativeRefresh = bundle.getBoolean(Constants.CAN_NATIVE_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        if (!TextUtils.isEmpty(name)) {
            mBinding.webview.addJavascriptInterface(name);
        }
        mBinding.webview.registerWebViewCallBack(this);

//        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(mUrl);


        mLoadService = LoadSir.getDefault().register(mBinding.smartRefreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });
        mBinding.smartRefreshLayout.setEnableAutoLoadMore(false);
        mBinding.smartRefreshLayout.setEnableRefresh(mCanNativeRefresh);
        mBinding.smartRefreshLayout.setOnRefreshListener(this);

//        mBinding.webview.setWebViewClient(new HJDuduWebViewClient(this));
//        mBinding.webview.setWebChromeClient(new HJDuduChromeClient(this));
//        WebViewDefaultSettings.getInstance().setSettings(mBinding.webview);
        return mLoadService.getLoadLayout();
    }

    @Override
    public void pageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        mBinding.smartRefreshLayout.finishRefresh();
        if (mIsError) {
            mBinding.smartRefreshLayout.setEnableRefresh(true);
            mLoadService.showCallback(ErrorCallback.class);
        } else {
            mBinding.smartRefreshLayout.setEnableRefresh(mCanNativeRefresh);
            mLoadService.showSuccess();
        }
        Log.d(TAG, "pageFinished");
        mIsError = false;
    }

    @Override
    public void onError() {
        Log.e(TAG, "onError");
        mIsError = true;
        mBinding.smartRefreshLayout.setEnableRefresh(true);
        mBinding.smartRefreshLayout.finishRefresh();
        mLoadService.showCallback(ErrorCallback.class);
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }

    /**
     * 注入到window中
     *
     * @param name 名称
     */
    public void addJavascriptInterface(String name) {
//        mBinding.webview.addJavascriptInterface(name);
    }

    public boolean webViewCanGoBack() {
        return mBinding.webview.canGoBack();
    }

    public void webViewGoBack() {
        mBinding.webview.goBack();
    }
}
