package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.util.ReturnValue;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFileService {
    public ReturnValue uploadImageFileTest(MultipartFile imageFile);
}
