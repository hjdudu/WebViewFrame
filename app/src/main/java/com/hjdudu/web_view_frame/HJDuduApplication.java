package com.hjdudu.web_view_frame;

import com.hjdudu.base.BaseApplication;
import com.hjdudu.base.loadsir.CustomCallback;
import com.hjdudu.base.loadsir.EmptyCallback;
import com.hjdudu.base.loadsir.ErrorCallback;
import com.hjdudu.base.loadsir.LoadingCallback;
import com.hjdudu.base.loadsir.LottieEmptyCallback;
import com.hjdudu.base.loadsir.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

public class HJDuduApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .addCallback(new LottieEmptyCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
