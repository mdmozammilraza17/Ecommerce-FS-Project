package com.banner.service;

import com.banner.DTO.CloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    CloudinaryResponse uploadImage(MultipartFile multipartFile);
}
