package com.lin.ego.manager.controller;

import com.lin.ego.base.vo.UploadResult;
import com.lin.ego.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/pic/upload")
    public UploadResult upload(MultipartFile uploadFile){

        return uploadService.upload(uploadFile);
    }

}
