package pebite.Ponitor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pebite.Ponitor_BE.dto.ResultSaveRequestDto;
import pebite.Ponitor_BE.service.ResultService;

import javax.persistence.Column;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://43.200.29.57:8080",allowedHeaders = "*")
@Controller
public class ResultApiController {

    private final ResultService resultService;

    @ResponseBody
    @PostMapping("/result")
    public Long save(@RequestBody ResultSaveRequestDto requestDto){
        return resultService.save(requestDto);
    }
}
