package com.lin.ego.base;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestFtp {
    static String baseUrl = "/home/ftpuser/ego/images";

    public static void main(String[] args) {
        FTPClient client = new FTPClient();
        try {
            client.connect("192.168.171.10",21);
            client.login("ftpuser","ftpuser" );
            File file = new File("C:\\Users\\LINx_\\Desktop\\1.jpg");
            InputStream inputStream = new FileInputStream(file);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.changeWorkingDirectory("/home/ftpuser/ego/images");
            client.enterLocalPassiveMode();
            boolean b = client.storeFile("test.jpg", inputStream);
            if(b){
                System.out.println("成功");
            }else{
                System.out.println("失败");
            }
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
