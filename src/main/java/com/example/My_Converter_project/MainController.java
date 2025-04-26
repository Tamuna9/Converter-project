package com.example.My_Converter_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//handing navigation between different pages
@Controller //marks this as a Spring MVC controller
public class MainController {
  private static final Logger logger = LoggerFactory.getLogger(MainController.class); //logger for debugging

  //handles the ("/") request and returns the home page
  @GetMapping("/")
    public String home(){
      return "homePage";
  }
  
  //handles the "/login" request and returns the login page
  @GetMapping("/login")
  public String login(){
    logger.info("login page");
  return "login";
  }

  //handles the "/register" request and returns the register page
  @GetMapping("/register")
  public String register(){
    return "register";
  }

  //handles the "/db_manager" request and returns the db_manager page
  @GetMapping("/db_manager")
  public String db(){
    logger.info("db page");
    return "db_manager";
  }

  //handles the "/converter" request and returns the converter page
  @GetMapping("/convert")
  public String converter(){
    logger.info("converter page");
    return "converter";
  }
  @GetMapping("/view_history")
  public String history(){
    logger.info("history page");
    return "history_page";
  }
}
