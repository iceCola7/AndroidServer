package com.cxz.androidserver;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by chen.
 */
public class ResourceInAssetsHandler implements IResourceUriHandler {

    private String acceptPrefix = "/static/";
    private Context context;

    public ResourceInAssetsHandler(Context context){
        this.context = context;
    }

    @Override
    public boolean accept(String uri) {
        return uri.startsWith(acceptPrefix);
    }

    @Override
    public void handle(String uri, HttpContext httpContext) throws IOException {
//        OutputStream os = httpContext.getUnderlySocket().getOutputStream();
//        PrintWriter writer = new PrintWriter(os);
//        writer.println("HTTP/1.1 200 OK");
//        writer.println();
//        writer.println("from resource in assets handler");
//        writer.flush();

        int startIndex = acceptPrefix.length();
        String assetsPath = uri.substring(startIndex);
        InputStream fis = context.getAssets().open(assetsPath);
        byte[] raw = StreamToolKit.readRawFromStream(fis);
        fis.close();
        OutputStream os = httpContext.getUnderlySocket().getOutputStream();
        PrintStream printer = new PrintStream(os);
        printer.println("HTTP/1.1 200 OK");
        printer.println();
        printer.println("Content-Length:" + raw.length);
        if(assetsPath.endsWith(".html")){
            printer.println("Content-Type:text/html");
        }else if(assetsPath.endsWith(".js")){
            printer.println("Content-Type:text/js");
        }else if(assetsPath.endsWith(".css")){
            printer.println("Content-Type:text/css");
        }else if(assetsPath.endsWith(".jpg")){
            printer.println("Content-Type:text/jpg");
        }else if(assetsPath.endsWith(".png")){
            printer.println("Content-Type:text/png");
        }
        printer.println();
        printer.println(raw);
    }
}
