package com.example.My_Converter_project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service //get and save exchange rates from external API
public class ExchangeRateService {

    //repository for save exchange rates to db
    private CurrencyCodeRepository currencyCodeRepository;

    //for HTTP-requests to API
    RestTemplate restTemplate = new RestTemplate();

    // get url and key from application.properties
    @Value("${currency.api.key}")
    private String apiKey;
    @Value("${currency.api.url}")
    private String apiUrl;

    //constructor with dependencies
    public ExchangeRateService(CurrencyCodeRepository currencyCodeRepository) {
        this.currencyCodeRepository = currencyCodeRepository;
    }
    public void fetchAndSaveRates(){
        // make request URL
        String requestURL = apiUrl + "?apikey=" + apiKey;

        try{ // send get-request and get data like json-object
            CurrencyFreaksResponse response = restTemplate.getForObject(requestURL,CurrencyFreaksResponse.class);
            if (response != null && response.rates !=null) {
                //check all currencies and save rate to db
                for (Map.Entry<String,String>entry : response.rates.entrySet()) {
                    String isoCode = entry.getKey();
                    String rateString = entry.getValue();
//                    CurrencyCode rate = new CurrencyCode();
//                    rate.setCurrencyIsoCode(entry.getKey());
                   try{
                       // make it double
                       BigDecimal rateValue = new BigDecimal(rateString);
                    CurrencyCode currencyCode = currencyCodeRepository
                            .findById(isoCode)
                            .orElse(new CurrencyCode(isoCode,isoCode,rateValue));
                    currencyCode.setRate(rateValue);
                       //save to db
                       currencyCodeRepository.save(currencyCode);
                   } catch (Exception e) {
                       System.out.println("Failed" + entry.getKey() + ":" + e.getMessage());

                   }
                }
                System.out.println("currencies loaded successfully");

            }else {
                System.out.println("Failed, API empty");

            }
        } catch (RuntimeException e) {
            System.out.println("Can not loaded currencies" + e.getMessage());

        }

    }

    public  static class CurrencyFreaksResponse {
        String date;
        String base;
        public Map<String,String>rates;
    }
}
