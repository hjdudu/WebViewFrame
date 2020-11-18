package com.hjdudu.webview.webviewprocess.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hjdudu.webview.WebViewCallBack;

public class HJDuduWebViewClient extends WebViewClient {
    private WebViewCallBack webViewCallBack;

    private static final String TAG = HJDuduWebViewClient.class.getSimpleName();

    public HJDuduWebViewClient(WebViewCallBack webViewCallBack) {
        this.webViewCallBack = webViewCallBack;
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        if (webViewCallBack != null) {
            webViewCallBack.pageFinished(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null !!!");
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (webViewCallBack != null) {
            webViewCallBack.pageStarted(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null !!!");
        }
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (webViewCallBack != null) {
            webViewCallBack.onError();
        } else {
            Log.e(TAG, "WebViewCallBack is null !!!");
        }
    }
}
