package com.example.atb.Service.Impl;

import com.example.atb.Entities.FileEntity;
import com.example.atb.Entities.User;
import com.example.atb.Exceptions.FileStorageException;
import com.example.atb.Repository.FileRepository;
import com.example.atb.Service.FileService;
import com.example.atb.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository dbFileRepository;
    private final UserService userService;

    @Override
    public FileEntity storeFile(MultipartFile file, long userId) {

        Optional<FileEntity> fileUser = dbFileRepository.findByUserId(userId);
        fileUser.ifPresent(dbFileRepository::delete);
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileEntity dbFile = new FileEntity();
            User user = userService.getUserById(userId);
            dbFile.setUser(user);
            dbFile.setFileName(fileName);
            dbFile.setFileType(file.getContentType());
            dbFile.setData(file.getBytes());
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public FileEntity getFile(long userId) {

        return dbFileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Oops! We couldn't find your profile picture, but we're working to bring it back soon! Stay tuned!",
                        new FileNotFoundException("File not found with id " + userId)));
    }

}
