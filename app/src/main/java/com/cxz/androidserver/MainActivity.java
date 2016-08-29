package com.cxz.androidserver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

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
        shs.registerResourceHandler(new ResourceInAssetsHandler(this));
        shs.registerResourceHandler(new UploadImageHandler(){
            @Override
            protected void onImageLoaded(String path){
                showImage(path);
            }
        });
        shs.startAsync();
    }

    private void showImage(final String path) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView iv = (ImageView) findViewById(R.id.imageView);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                iv.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this,"image load and show",Toast.LENGTH_LONG).show();

            }
        });
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
