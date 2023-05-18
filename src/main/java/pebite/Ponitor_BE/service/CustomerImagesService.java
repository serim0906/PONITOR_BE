package pebite.Ponitor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pebite.Ponitor_BE.dto.CustomerImagesSaveRequestDto;
import pebite.Ponitor_BE.repository.CustomerImagesRepository;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class CustomerImagesService {
    private final CustomerImagesRepository customerImagesRepository;

    @Autowired
    private S3Uploader s3Uploader;

    @Transactional
    public Long saveImage(MultipartFile imageFile, CustomerImagesSaveRequestDto requestDto){
        if (!imageFile.isEmpty()) {
            try {
                String imageUrl = s3Uploader.uploadFiles(imageFile, "images");
                requestDto.setImageUrl(imageUrl);
            } catch (IOException e) {
                System.out.println(e.getMessage()); }
        }
        return  customerImagesRepository.save(requestDto.toEntity()).getCustomerId();
    }




}

