package com.hjdudu.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IWebViewService {

    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar,String name);

    Fragment getWebViewFragment(String url, boolean canNativeRefresh);

    Fragment getWebViewFragmentWithJavascriptInterface(String url, boolean canNativeRefresh);

    void startDemoHtml(Context context,String title,String fileName,String name);
}
