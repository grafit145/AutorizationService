package com.example.demo.controller;

import com.example.demo.exeption.InvalidCredentials;
import com.example.demo.exeption.UnauthorizedUser;
import com.example.demo.service.Authorities;
import com.example.demo.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class AuthorizationController {
    AuthorizationService service;
    @Autowired
    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    ResponseEntity<String> InvalidCredentialsEx(InvalidCredentials e) {
        System.out.println("Error 400: " + e.getMessage());
        return new ResponseEntity<>("Error 400:" + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    ResponseEntity<String> UnauthorizedUserEx(InvalidCredentials e) {
        System.out.println("Error 401: " + e.getMessage());
        return new ResponseEntity<>("Error 401: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
