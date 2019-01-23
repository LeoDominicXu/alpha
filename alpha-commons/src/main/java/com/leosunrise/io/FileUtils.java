package com.leosunrise.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Auther: Leo Dominic Xu
 * @Date: 2019/1/24 02:45
 * @Description:
 */
public class FileUtils {
    protected static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static byte[] getByteFromURL(String url){
        InputStream inputStream = null;
        URLConnection conn = null;
        try {
            inputStream = new FileInputStream(url);
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
