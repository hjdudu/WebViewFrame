package com.hjdudu.webview.mainprocess;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.hjdudu.webview.ICallBack;
import com.hjdudu.webview.IWebviewProcessToMainProcessInterface;
import com.hjdudu.webview.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 主进程命令管理器
 */
public class MainProcessCommandManager extends IWebviewProcessToMainProcessInterface.Stub {

    public static final String TAG = MainProcessCommandManager.class.getSimpleName();
    private static MainProcessCommandManager sInstance;

    HashMap<String, Command> hm = new HashMap<>();

    public static IBinder getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandManager.class) {
                sInstance = new MainProcessCommandManager();
            }
        }
        return sInstance;
    }

    private MainProcessCommandManager() {
        ServiceLoader<Command> load = ServiceLoader.load(Command.class);
        for (Command command :load) {
            if (!hm.containsKey(command.name())) {
                hm.put(command.name(), command);
            }
        }
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallBack callback) throws RemoteException {
        Log.i("test", "handleWebCommand :" + hm.get(commandName));
        executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class), callback);
    }

    private void executeCommand(String commandName, Map params, ICallBack callback) {
        if (params != null) {
            hm.get(commandName).execute(params, callback);
        }
    }
}
