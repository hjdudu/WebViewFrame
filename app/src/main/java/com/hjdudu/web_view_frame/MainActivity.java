package com.hjdudu.web_view_frame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hjdudu.base.autoservice.HJDuduServiceLoader;
import com.hjdudu.common.autoservice.IWebViewService;


public class MainActivity extends AppCompatActivity {

    private Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IWebViewService iWebViewService = HJDuduServiceLoader.load(IWebViewService.class);
        btnOpen = (Button) findViewById(R.id.btn_open);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iWebViewService != null) {
                    iWebViewService.startWebViewActivity(MainActivity.this, "HTTPS://www.baidu.com", "百度",true);
                }
            }
        });

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if (iWebViewService!=null){

            transaction.replace(R.id.web_view_fragment,iWebViewService.getWebViewFragment("HTTPS://www.baidu.com",true)).commit();
        }

    }
}