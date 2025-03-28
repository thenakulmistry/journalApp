package com.nakul.firstSpringProject.controller;

import com.nakul.firstSpringProject.entity.User;
import com.nakul.firstSpringProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if(!all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        try {
            userService.saveNewAdmin(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
