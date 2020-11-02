package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.service.ImageFileService;
import com.springboot.springbootdemo.util.ReturnValue;
import com.springboot.springbootdemo.util.WaterMarkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/image")
public class ImageFileController {
    private static final Logger logger = LoggerFactory.getLogger(ImageFileController.class);

    @Autowired
    private ImageFileService imageFileService;

    /**
     * 文件上传测试接口
     * @return
     */
    @RequestMapping("/upload")
        public ReturnValue uploadFileTest(@RequestParam("uploadFile") MultipartFile imgFile) {
            return imageFileService.uploadImageFileTest(imgFile);
    }
}
