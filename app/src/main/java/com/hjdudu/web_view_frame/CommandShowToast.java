package com.hjdudu.web_view_frame;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.hjdudu.base.BaseApplication;
import com.hjdudu.webview.ICallBack;
import com.hjdudu.webview.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map<String, String> parametes, ICallBack iCallBack) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.sApplication, parametes.get("message").toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
