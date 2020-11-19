package com.hjdudu.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.hjdudu.base.BaseApplication;
import com.hjdudu.webview.ICallBack;
import com.hjdudu.webview.IWebviewProcessToMainProcessInterface;
import com.hjdudu.webview.command.Command;
import com.hjdudu.webview.mainprocess.MainProcessCommandService;

import java.util.HashMap;


/**
 * 命令分发器
 */

public class WebViewProcessCommandDispatcher implements ServiceConnection {


    private static WebViewProcessCommandDispatcher sInstance;

    HashMap<String, Command> hm = new HashMap<>();

    public static WebViewProcessCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                sInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }

    private IWebviewProcessToMainProcessInterface iWebviewProcessToMainProcessInterface;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebviewProcessToMainProcessInterface = IWebviewProcessToMainProcessInterface.Stub.asInterface(service);

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onNullBinding(ComponentName name) {

    }


    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }


    public void executeCommand(String commandName, String params, BaseWebView baseWebView) throws RemoteException {
        if (iWebviewProcessToMainProcessInterface != null) {
            iWebviewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallBack.Stub() {
                @Override
                public void onResult(String callbackname, String response) throws RemoteException {
                    baseWebView.handleCallBack(callbackname, response);
                }
            });
        }
    }
}
