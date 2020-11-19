package com.hjdudu.web_view_frame;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.hjdudu.base.BaseApplication;
import com.hjdudu.webview.ICallBack;
import com.hjdudu.webview.command.Command;
import com.hjdudu.webview.mainprocess.MainProcessCommandManager;

import java.util.Map;

@AutoService({Command.class})
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map<String, String> parametes, ICallBack iCallBack) {
        String target_class = parametes.get("target_class");
        if (!TextUtils.isEmpty(target_class)){
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication,target_class));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Log.d(MainProcessCommandManager.TAG, "executeCommand target:$target");
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
