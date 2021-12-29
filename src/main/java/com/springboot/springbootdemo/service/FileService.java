package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.bean.FileReturn;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jiangxia
 * @date 2021/12/28 20:04
 * @param: No such property: code for class: Script1
 * @return No such property: code for class: Script1
 * @description 文件上传服务接口
 */
public interface FileService {
    public FileReturn uploadFile(MultipartFile multipartFile);
}
