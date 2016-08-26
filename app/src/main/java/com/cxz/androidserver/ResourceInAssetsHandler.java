package com.cxz.androidserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by chen.
 */
public class ResourceInAssetsHandler implements IResourceUriHandler {

    private String acceptPrefix = "/static/";

    @Override
    public boolean accept(String uri) {
        return uri.startsWith(acceptPrefix);
    }

    @Override
    public void handle(String uri, HttpContext httpContext) throws IOException {
        OutputStream os = httpContext.getUnderlySocket().getOutputStream();
        PrintWriter writer = new PrintWriter(os);
        writer.println("HTTP/1.1 200 OK");
        writer.println();
        writer.println("from resource in assets handler");
        writer.flush();
    }
}
