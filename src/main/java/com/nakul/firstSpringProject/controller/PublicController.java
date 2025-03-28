package com.nakul.firstSpringProject.controller;

import com.nakul.firstSpringProject.entity.User;
import com.nakul.firstSpringProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/health_check")
    public String healthCheck(){
        return "OK";
    }
}
