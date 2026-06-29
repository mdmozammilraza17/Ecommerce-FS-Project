package com.banner.service;

import com.banner.DTO.CloudinaryResponse;
import com.banner.exception.ImageUploadException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService{

    private final Cloudinary cloudinary;

    @Override
    public CloudinaryResponse uploadImage(MultipartFile file) {
        try
        {
            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap());

            return CloudinaryResponse.builder()
                    .imageUrl(result.get("secure_url").toString())
                    .publicId(result.get("public_id").toString()).build();

        } catch (IOException e) {
            throw new ImageUploadException("Failed to upload the image", e);
        }
    }
}
