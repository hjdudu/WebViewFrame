package com.hjdudu.usercenter;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.hjdudu.base.BaseApplication;
import com.hjdudu.common.autoservice.IUserCenterService;

@AutoService({IUserCenterService.class})
public class UserCenterServiceImp implements IUserCenterService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.sApplication,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
