package pebite.Ponitor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import pebite.Ponitor_BE.dto.CustomerImagesSaveRequestDto;
import pebite.Ponitor_BE.service.CustomerImagesService;


import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@CrossOrigin(origins = "http://43.200.29.57:8080",allowedHeaders = "*")
@RestController
public class CustomerImagesApiController {

    private final CustomerImagesService customerImagesService;

    //  HttpServletRequest request을 사용해주면 알아서 요청으로 들어온값들이 requestDto 안으로 들어감
    @PostMapping(value = "/customerimages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //insert
    public Long saveImage(HttpServletRequest request, @RequestParam(value = "image") MultipartFile imageFile, CustomerImagesSaveRequestDto requestDto) {
        Long successId = customerImagesService.saveImage(imageFile, requestDto);
        return successId;
    }

}