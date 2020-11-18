package com.hjdudu.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.hjdudu.webview.WebViewCallBack;

public class HJDuduChromeClient extends WebChromeClient {

    private WebViewCallBack mWebViewCallBack;
    private static final String TAG = HJDuduChromeClient.class.getSimpleName();

    public HJDuduChromeClient(WebViewCallBack webViewCallBack) {
        mWebViewCallBack = webViewCallBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);

        if (mWebViewCallBack != null) {
            mWebViewCallBack.updateTitle(title);
        } else {
            Log.e(TAG, "WebViewCallBack is null !!!");
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(TAG,consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
