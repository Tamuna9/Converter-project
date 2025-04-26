package com.example.My_Converter_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
//handles user registration functionality
@RestController
@RequestMapping("/register")
//process all requests with /register
public class RegisterController {
    //handle registration process
    @Autowired
    private UserService userService; //injects UserService
    @PostMapping
    public ResponseEntity<?> register(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String email, HttpSession session){
        Map<String, String> response = new HashMap<>();

        //Validate input parameters
        if ( username == null ||username.isEmpty()|| password == null  || password.isEmpty()) {
            response.put("message", "Invalid request parameters");
            response.put("redirect", "./register");
            return ResponseEntity.badRequest().body(response);
        }
        // check if user already exist
            if (userService.isUserExist(username, email)){
                response.put("message", "User already exist");
                response.put("redirect", "./register");
                return ResponseEntity.badRequest().body(response);

            }
            //create and save new user
            User newUser = new User(username,  password, email);
            userService.saveUser(newUser);

            //retrive saved user to get Id
        User registeredUser = userService.authenticate(username,password);
        session.setAttribute("username",registeredUser.getUsername());
        session.setAttribute("userId",registeredUser.getId());
        session.setAttribute("user",registeredUser);
        //response
        response.put("message", "Register successful");
        response.put("redirect", "./convert");//redirect to the convert page
        return ResponseEntity.ok(response);

    }
}
