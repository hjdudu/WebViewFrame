package com.hjdudu.web_view_frame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hjdudu.base.autoservice.HJDuduServiceLoader;
import com.hjdudu.common.autoservice.IWebViewService;

import java.util.ServiceLoader;


public class MainActivity extends AppCompatActivity {

    private Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = (Button) findViewById(R.id.btn_open);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWebViewService iWebViewService = HJDuduServiceLoader.load(IWebViewService.class);
                if (iWebViewService != null) {
                    iWebViewService.startWebViewActivity(MainActivity.this, "HTTPS://www.baidu.com", "百度",true);
                }
            }
        });

    }
}