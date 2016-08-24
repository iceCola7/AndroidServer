package com.cxz.androidserver;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by chen.
 */
public class HttpContext {
    private final HashMap<String, String> requestHeaders;
    private Socket underlySocket;

    public HttpContext(){
        requestHeaders = new HashMap<String,String>();
    }

    public void setUnderlySocket(Socket socket) {
        this.underlySocket = socket;
    }

    public Socket getUnderlySocket(){
        return this.underlySocket;
    }

    public void addRequestHeader(String headerName, String headerValue) {
        requestHeaders.put(headerName,headerValue);
    }

    public String getRequestHeaderValue(String headerName){
        return requestHeaders.get(headerName);
    }
}
