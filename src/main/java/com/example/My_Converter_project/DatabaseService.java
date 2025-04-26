package com.example.My_Converter_project;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class DatabaseService {
    @Autowired
    private CurrencyCodeRepository currencyCodeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConversionHistoryRepository conversionHistoryRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    //constructor for dependencies
    public DatabaseService(CurrencyCodeRepository currencyCodeRepository, UserRepository userRepository, ResourceLoader resourceLoader) {
        this.currencyCodeRepository = currencyCodeRepository;
        this.userRepository = userRepository;
        this.resourceLoader = resourceLoader;
    }
    @PostConstruct
            public void initOnStartUp() {
        try{
            exportCurrencyDataToCSV();
            exportUserDataToCSV();
            exportConversionHistoryToCSV();
            System.out.println("Successful" );

        } catch (IOException e) {
            System.out.println("Failed" +e.getMessage());
        }
    }
     String basePath =  System.getProperty("user.dir") + "/CSV";
     String currencyFilePath = basePath + "currency_rates.csv";
     String userFilePath = basePath + "users.csv";
    String historyFilePath =basePath + "history.csv";


    public void exportCurrencyDataToCSV() throws IOException{
        List<CurrencyCode> currencyCodes = currencyCodeRepository.findAll();
        File file = new File(currencyFilePath);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
           writer.write("currencyIsoCode,currencyName,rate");
           writer.newLine();
           for (CurrencyCode currencyCode:currencyCodes){
               writer.write(currencyCode.getCurrencyIsoCode() + "," + currencyCode.getCurrencyName() + "," + currencyCode.getRate());
               writer.newLine();
           }
        }
    }
    public void exportUserDataToCSV() throws IOException{
        List<User> users = userRepository.findAll();
        File file = new File(userFilePath);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("username,password,email");
            writer.newLine();
            for (User user:users){
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail());
                writer.newLine();
            }
        }
    }

    public void exportConversionHistoryToCSV() throws IOException{
        List<ConversionHistory> historyList = conversionHistoryRepository.findAll();
       File file = new File(historyFilePath);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("userId,ConversionId,SourceCurrency,TargetCurrency,SourceAmount,TargetAmount,OperationDate");
            writer.newLine();
            for (ConversionHistory record:historyList){
                writer.write(record.getUser().getId() + "," + record.getConversionId() + ","
                        + record.getSourceCurrency() + "," + record.getTargetCurrency() + ","
                        + record.getSourceAmount() + "," + record.getTargetAmount() + "," + record.getOperationDate());
                writer.newLine();
            }
        }
    }
}
