package com.example.My_Converter_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
//handles user login functionality
@RestController
@RequestMapping("/login") //process all requests with /login
public class LoginController {

    @Autowired
    private UserService userService; //injects UserService
//handle login process
    @PostMapping
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Map<String, String> response = new HashMap<>();

        //Validate input parameters
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.put("message", "Invalid username or password");
            response.put("redirect", "./login");
            return ResponseEntity.badRequest().body(response);
        }
        try {
            //authenticate the user
            User user = userService.authenticate(username, password);
            if (user != null) {
                //store username in session, if successful
                session.setAttribute("username", user.getUsername());
                session.setAttribute("userId", user.getId());
                session.setAttribute("user", user);
                response.put("message", "Login successful");
                response.put("redirect", "./convert");//redirect to the convert page
                return ResponseEntity.ok(response);
            } else
            {response.put("message", "Invalid request parameters");
            response.put("redirect", "./login");
            return ResponseEntity.status(401).body(response);
        }
    }catch (DataAccessException e) {
            //handle database asses issues
            return ResponseEntity.status(503).body(response);
        }

    }
}
