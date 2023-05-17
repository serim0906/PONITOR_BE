package pebite.Ponitor_BE.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import pebite.Ponitor_BE.dto.UsersLoginResDto;
import pebite.Ponitor_BE.exception.AuthenticationException;
import pebite.Ponitor_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@CrossOrigin(origins = "http://43.200.29.57:8080",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/users/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestParam(name = "username") @NotNull @NotEmpty @NotBlank String username,
                                @RequestParam(name = "password") @NotNull @NotEmpty @NotBlank String password,
                                HttpServletRequest request) {

        ResponseEntity responseEntity = null;

        try {

            UsersLoginResDto usersLoginResDto = userService.login(username, password);

            responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usersLoginResDto);

        }catch (AuthenticationException e){

            responseEntity = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getResponseMessage());
        }

        return responseEntity;

    }
}
