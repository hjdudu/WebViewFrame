package com.hjdudu.usercenter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.hjdudu.common.eventbus.LoginEvent;
import com.hjdudu.usercenter.databinding.ActivityLoginBinding;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btnLogin.setOnClickListener(l ->{
            EventBus.getDefault().post(new LoginEvent("inputEmail"));
            finish();
        });
    }
}
