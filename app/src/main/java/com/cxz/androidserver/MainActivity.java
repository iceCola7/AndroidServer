package com.cxz.androidserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SimpleHttpServer shs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebConfiguration webConfig = new WebConfiguration();
        webConfig.setPort(8088);
        webConfig.setMaxParallels(50);
        shs = new SimpleHttpServer(webConfig);
        shs.registerResourceHandler(new ResourceInAssetsHandler());
        shs.registerResourceHandler(new UploadImageHandler());
        shs.startAsync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            shs.stopServer();
        } catch (IOException e) {
            Log.e("cxz",e.toString());
        }
    }
}
