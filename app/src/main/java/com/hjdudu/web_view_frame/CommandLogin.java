package com.hjdudu.web_view_frame;

import android.os.RemoteException;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.hjdudu.base.autoservice.HJDuduServiceLoader;
import com.hjdudu.common.autoservice.IUserCenterService;
import com.hjdudu.common.eventbus.LoginEvent;
import com.hjdudu.webview.ICallBack;
import com.hjdudu.webview.command.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

@AutoService({Command.class})
public class CommandLogin implements Command {

    private final IUserCenterService services= HJDuduServiceLoader.load(IUserCenterService.class);;
    private ICallBack iCallBack;
    private String callbackNameFromNativeJS;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map<String, String> parametes, ICallBack iCallBack) {
        this.iCallBack = iCallBack;
        if (services != null && !services.isLogin()) {
            services.login();
            callbackNameFromNativeJS = parametes.get("callbackname");
        }
    }


    @Subscribe
    public void onMessageEvent(LoginEvent event) throws RemoteException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accountName", event.getName());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("javascript:hjdudujs.callback('");
        stringBuffer.append(callbackNameFromNativeJS);
        stringBuffer.append("',");
        stringBuffer.append(new Gson().toJson(hashMap));
        stringBuffer.append(")");
        iCallBack.onResult(stringBuffer.toString(),new Gson().toJson(hashMap));
//        EventBus.getDefault().unregister(this);
    }
}
