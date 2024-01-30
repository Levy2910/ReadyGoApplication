package com.example.readygo.controller;

//API KEY
//AC30345A00814669A9B01176665CF22D

import com.example.readygo.model.User;
import com.example.readygo.service.IUserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.*;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService iUserService;
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try{
            String message = iUserService.addUser(user);
            if ( !message.equals("successfully")){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
            }
            return ResponseEntity.ok("successfully created your account");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> checkUser(@RequestParam String email, String password){
        try{
            return ResponseEntity.ok(iUserService.checkUser(email, password));
        }
        catch( Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable Long user_id, @RequestBody User user){
        try{
            if (iUserService.updateUser(user_id, user)){
                return ResponseEntity.ok(user);
            }else{
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        }
        catch (Exception e){
            System.out.printf(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
