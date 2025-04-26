package com.example.My_Converter_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DatabaseController {
    @Autowired//constructor for dependencies
    public DatabaseController(DatabaseService databaseService, ResourceLoader resourceLoader) {
        this.databaseService = databaseService;
        this.resourceLoader = resourceLoader;
    }
    private final DatabaseService databaseService;
   private  final ResourceLoader resourceLoader;

    @PostMapping("/exportCurrencyToCSV")
    public ResponseEntity<Map<String,String>> exportCurrency(){
        try{
           databaseService.exportCurrencyDataToCSV();
            return ResponseEntity.ok(Collections.singletonMap("message", "Currency data exported successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/exportUsersToCSV")
    public ResponseEntity<Map<String,String>> exportUsers(){
        try{
            databaseService.exportUserDataToCSV();
            return ResponseEntity.ok(Collections.singletonMap("message", "User data exported successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    @PostMapping("/exportConversionHistoryToCSV")
    public ResponseEntity<Map<String,String>> exportConversionHistory(){
        try{
            databaseService.exportConversionHistoryToCSV();
            return ResponseEntity.ok(Collections.singletonMap("message", "History exported successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    //method for load db_manager page
    @PostMapping("/createDBTables")
    public ResponseEntity<Map<String,String>> createDB(@RequestBody(required = false)Map<String,String> request){
        try{
//            databaseService.createDBIfNotExist();
      return ResponseEntity.ok(Collections.singletonMap("message", "Tables created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
        }

    }




