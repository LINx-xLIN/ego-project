package com.lin.ego.base.utils;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

public class FtpUtils {
    /**
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param basePath
     * @param filePath
     * @param remoteFileName
     * @param in
     * @return
     */
    public static boolean upload(String hostname, int port, String username, String password, String basePath, String filePath, String remoteFileName, InputStream in){

        FTPClient client = new FTPClient();
        try {
            client.connect(hostname,port);
            boolean login = client.login(username, password);
            if(login){
                client.setFileType(FTPClient.BINARY_FILE_TYPE);
                client.enterLocalPassiveMode();
                String path = basePath + filePath;
                boolean directoryFlag = client.changeWorkingDirectory(path);
                if(!directoryFlag){
                    String tempPath = basePath;
                    String[] split = filePath.split("/");
                    for (int i = 0; i < split.length; i++) {
                        if (StringUtils.isNotEmpty(split[i])){
                            tempPath = tempPath + "/" + split[i];
                            boolean tempPathFlag = client.changeWorkingDirectory(tempPath);
                            if(!tempPathFlag){
                                client.makeDirectory(tempPath);
                            }
                        }
                    }
                   client.changeWorkingDirectory(path);
                }
                client.storeFile(remoteFileName,in);
                return true;
            }


            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
}
