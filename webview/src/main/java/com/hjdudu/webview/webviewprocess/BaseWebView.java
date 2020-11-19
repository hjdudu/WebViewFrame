package com.hjdudu.webview.webviewprocess;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.hjdudu.webview.WebViewCallBack;

import com.hjdudu.webview.bean.JsParam;
import com.hjdudu.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.hjdudu.webview.webviewprocess.webchromeclient.HJDuduChromeClient;
import com.hjdudu.webview.webviewprocess.webchromeclient.HJDuduChromeClient;
import com.hjdudu.webview.webviewprocess.webviewclient.HJDuduWebViewClient;

public class BaseWebView extends WebView {

    private final static String TAG = BaseWebView.class.getSimpleName();

    public BaseWebView(@NonNull Context context) {
        this(context, null);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        //初始化建立连接
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
        WebViewDefaultSettings.getInstance().setSettings(this);
//        // 注入到window中
//        addJavascriptInterface(this, "hjduduwebview");
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        setWebViewClient(new HJDuduWebViewClient(webViewCallBack));
        setWebChromeClient(new HJDuduChromeClient(webViewCallBack));
    }


    @JavascriptInterface
    public void takeNativeAction(final String jsParam) throws RemoteException {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param), this);
            }

        }
    }

    /**
     * 注入到window中
     *
     * @param name 名称
     */
    public void addJavascriptInterface(String name) {
        addJavascriptInterface(this, name);
    }

    public void handleCallBack(String callbackname, String response) {
        if (callbackname != null && response != null) {
            post(new Runnable() {
                @Override
                public void run() {
                    evaluateJavascript(callbackname, null);
                }
            });
        }
    }
}
