package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.bean.FileReturn;
import com.springboot.springbootdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jiangxia
 * @date 2021年12月28日 20:36
 */
@RestController
@RequestMapping(value="/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public FileReturn uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile){
        return fileService.uploadFile(multipartFile);
    }
}
