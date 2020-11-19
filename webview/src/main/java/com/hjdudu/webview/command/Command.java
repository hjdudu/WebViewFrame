package com.hjdudu.webview.command;

import com.hjdudu.webview.ICallBack;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map<String, String> parametes, ICallBack iCallBack);
}
