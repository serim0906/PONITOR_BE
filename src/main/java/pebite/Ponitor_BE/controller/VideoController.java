package pebite.Ponitor_BE.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import pebite.Ponitor_BE.dto.VideosResDto;
import pebite.Ponitor_BE.dto.VideosUploadResDto;
import pebite.Ponitor_BE.response.Message;
import pebite.Ponitor_BE.response.ResponseMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import pebite.Ponitor_BE.service.VideoService;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VideoController {

    @Autowired
    VideoService videoService;

    // 영상 업로드
    @PostMapping(value = "/{customerId}/videos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadVideosAmazonS3(
            @PathVariable("customerId") Long customerId,
            @RequestPart("file") MultipartFile file, HttpServletRequest request) {

        ResponseEntity responseEntity = null;

        try{

            videoService.uploadVideosAmazonS3(customerId, file);

            responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(VideosUploadResDto.builder().customerId(customerId).build());

        }catch (MultipartException e){
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(Message.ERR_4010));
        }catch (NoSuchAlgorithmException e){
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(Message.ERR_4020));
        }catch (IOException e){
            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(Message.ERR_4030));
        }

        return responseEntity;
    }


    // 영상 조회
    @GetMapping(value = "/users/{username}/lists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllVideos(
            @PathVariable("username") String username,
            HttpServletRequest request) {

        List<VideosResDto> videosResDtos = videoService.getAllVideos(username);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(videosResDtos);
    }
}
