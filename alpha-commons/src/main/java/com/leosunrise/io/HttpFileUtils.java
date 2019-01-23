package com.leosunrise.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Auther: Leo Dominic Xu
 * @Date: 2019/1/24 02:45
 * @Description:
 */
public class HttpFileUtils {
    protected static Logger logger = LoggerFactory.getLogger(HttpFileUtils.class);

    public static byte[] getByteFromURL(String url){
        URL uri = null;
        InputStream inputStream = null;
        URLConnection conn = null;
        try {
            uri = new URL(url);
            conn = uri.openConnection();
            inputStream = conn.getInputStream();
            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);
            return byt;
        } catch (Exception ex) {
            logger.warn("get cloud file content error:{}",ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
