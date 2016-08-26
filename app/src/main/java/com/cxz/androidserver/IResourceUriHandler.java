package com.cxz.androidserver;

import java.io.IOException;

/**
 * Created by chen.
 */
public interface IResourceUriHandler {

    boolean accept(String uri);

    void handle(String uri, HttpContext httpContext) throws IOException;

}
