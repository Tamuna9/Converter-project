package com.example.My_Converter_project;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class CurrencyDataService {
    private static final String URL_RATES = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=67c80066d7a041758b0e47d5b11df019";

    @Autowired
    private CurrencyCodeRepository currencyCodeRepository;
    @PostConstruct //called once, when starts
    public void loadCurrencyData() {
    RestTemplate restTemplate = new RestTemplate();

    try{
        String response  = restTemplate.getForObject(URL_RATES, String.class);
        JSONObject jsonObject  = new JSONObject(response);

         // check if json has key "rates"
        if (jsonObject.has("rates")) {
            JSONObject rates  = jsonObject.getJSONObject("rates");
            Iterator<String> keys = rates.keys();

            while (keys.hasNext()){
                String isoCode = keys.next();
                BigDecimal rate = new BigDecimal(rates.getString(isoCode));

           // save currency, if not in db
                if (!currencyCodeRepository.existsById(isoCode)) {
                    currencyCodeRepository.save(new CurrencyCode(isoCode,isoCode,rate));
                }
            }
            System.out.println("currencies loaded");

        }else {
            System.out.println("Failed");
        }

    } catch (Exception e) {
        System.out.println("Failed to load" +e.getMessage());
    }
    }
}



