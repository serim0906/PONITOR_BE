package pebite.Ponitor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import pebite.Ponitor_BE.dto.CustomerSaveRequestDto;
import pebite.Ponitor_BE.dto.CustomerUpdateRequestDto;
import pebite.Ponitor_BE.service.CustomerService;


@RequiredArgsConstructor
@CrossOrigin(origins = "http://43.200.29.57:8080",allowedHeaders = "*")
@RestController
public class CustomerApiController {

    private final CustomerService customerService;

    @ResponseBody // Long 타입을 리턴하고 싶은 경우 붙여야 함 (Long - 객체)
    @PostMapping("/customer" ) //insert
    public Long save(@RequestBody CustomerSaveRequestDto requestDto){
        return customerService.save(requestDto);
    }


    @PutMapping("/customer/{customerId}")//update
    public Long update(@PathVariable Long customerId, @RequestBody CustomerUpdateRequestDto requestDto){
        return customerService.update(customerId, requestDto);
    }

}
