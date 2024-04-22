package com.example.atb.Service;

import com.example.atb.Entities.FileEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileEntity storeFile(MultipartFile file, long userId);
    FileEntity getFile(long userId);
}
