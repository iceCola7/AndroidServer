package com.cxz.androidserver;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chen.
 */
public class StreamToolKit {

    public static final String readLine(InputStream is) throws IOException {

        StringBuilder sb = new StringBuilder();
        int c1 = 0;
        int c2 = 0;
        while(c2 != -1&& !(c1 == '\r'&& c2 == '\n')){
            c1 = c2;
            c2 = is.read();
            sb.append((char)c2);
        }
        if(sb.length() == 0){
            return null;
        }
        return sb.toString();
    }

}
