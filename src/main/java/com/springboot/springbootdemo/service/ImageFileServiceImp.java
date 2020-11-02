package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.util.ReturnCodeAndMsgEnum;
import com.springboot.springbootdemo.util.ReturnValue;

import com.springboot.springbootdemo.util.WaterMarkUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;
@Service
public class ImageFileServiceImp implements ImageFileService {

    private static final Logger logger = LoggerFactory.getLogger(ImageFileServiceImp.class);

    @Override
    public ReturnValue uploadImageFileTest(MultipartFile imgFile) {
        //文件保存路径
        String targetFilePath = "C:\\Users\\Jiang\\Desktop\\测试";
        //重命名保存新文件的文件名
        String fileName = UUID.randomUUID().toString().replace("-", "");
        File targetFile = new File(targetFilePath + File.separator + fileName);

        try{
            String originalFilename = imgFile.getOriginalFilename();
            String typeName = originalFilename.substring(originalFilename.indexOf(".")+1 ,originalFilename.length());
            InputStream inputStream = imgFile.getInputStream();
            OutputStream outputStream = new FileOutputStream(targetFile) ;
            //调用添加水印的方法
            WaterMarkUtil.ImagemarkWater("1024笔记", inputStream, outputStream, 0, typeName);
            outputStream.close();
            inputStream.close();
            logger.info("------图片上传、添加水印成功------");
        }catch(IOException e){
        }

        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success, null);
    }
}