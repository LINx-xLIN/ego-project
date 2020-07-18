package com.lin.ego.manager.service.impl;

import com.lin.ego.base.utils.FtpUtils;
import com.lin.ego.base.utils.IDUtils;
import com.lin.ego.base.vo.UploadResult;
import com.lin.ego.manager.service.UploadService;
import com.sun.corba.se.impl.presentation.rmi.IDLTypesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {
    @Value("${ftp.hostname}")
    private String hostname;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.basePath}")
    private String basePath;
    @Value("${pic.url}")
    private String picBaseUrl;


    @Override
    public UploadResult upload(MultipartFile file) {
        UploadResult result = new UploadResult();

        try {
            Date date = new Date();
            String year = new SimpleDateFormat("yyyy").format(date);
            String month = new SimpleDateFormat("MM").format(date);
            String day = new SimpleDateFormat("dd").format(date);


            String filePath="/ego/images/"+year+"/"+month+"/"+day;

            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));

            String fileName = IDUtils.genImageName() + extName;

            InputStream in = file.getInputStream();



            boolean upload = FtpUtils.upload(hostname, port, username, password, basePath, filePath, fileName, in);
            if(upload){
                result.setError(0);
                String url = picBaseUrl + "/" + year + "/" + month + "/" + day + "/" + fileName;
                result.setUrl(url);
            }else{
                result.setError(1);
                result.setMessage("上传失败");

            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("上传失败");
        }

        return result;
    }
}
