package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.bean.FileReturn;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jiangxia
 * @date 2021年12月28日 20:14
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public FileReturn uploadFile(MultipartFile multipartFile) {
//        文件保存路径
        String filePath = "F:\\filepath";
//        文件名
        String fileName = String.valueOf(System.currentTimeMillis());
        File file = new File(filePath +File.separator + fileName);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(multipartFile.getInputStream(),fileOutputStream);
            System.out.println("===========file upload success=======");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
//                关闭
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件关闭错误",e);
            }
        }

        return new FileReturn<>(1,"文件上传成功",file);
    }
}
