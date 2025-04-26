
package com.example.My_Converter_project;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CurrencyConverterController {

    @Autowired
    private ConversionHistoryRepository conversionHistoryRepository;
    @Autowired
    private CurrencyCodeRepository currencyCodeRepository;

    private static final String URL_RATES = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=67c80066d7a041758b0e47d5b11df019";

    @GetMapping("/converter")

    //method for getting currencies
    public Map<String, Object> getCurrencyCodes() {
        List<CurrencyCode> currencyList = currencyCodeRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("currencyCodes", currencyList.stream().map(CurrencyCode::getCurrencyIsoCode).collect(Collectors.toList()));
        return response;
    }

    //method for conversion currency by fetch
    @PostMapping("/converter")

    public Map<String, Object> convertCurrency(@RequestBody Map<String, Object> requestData, HttpSession session) throws Exception {
        double amount = Double.parseDouble(requestData.get("amount").toString());
        String fromCurrency = (String) requestData.get("from_currency");
        String toCurrency = (String) requestData.get("to_currency");

        RestTemplate restTemplate = new RestTemplate();
        String exchangeRates;
        try {
            exchangeRates = restTemplate.getForObject(URL_RATES, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed API request" + e.getMessage());
        }

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(exchangeRates);
        } catch (Exception e) {
            throw new RuntimeException("Failed API request" + e.getMessage());
        }

        if (!jsonObject.has("rates")) {
            throw new JSONException("Invalid API response");
        }
        JSONObject rates = jsonObject.getJSONObject("rates");

        if (!rates.has(fromCurrency) ||
                !rates.has(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code");
        }
        double fromRate = rates.getDouble(fromCurrency);
        double toRate = rates.getDouble(toCurrency);
        double result = (amount / fromRate) * toRate;

        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new Exception("no logged user");
        }
        //save to history
        ConversionHistory history = new ConversionHistory();
        history.setUser(user);
        history.setSourceCurrency(fromCurrency);
        history.setTargetCurrency(toCurrency);
        history.setSourceAmount(amount);
        history.setTargetAmount(result);
        history.setOperationDate(new Date());
        conversionHistoryRepository.save(history);

        Map<String, Object> response = new HashMap<>();
        response.put("result", result);

        return response;
    }

    @PostMapping("/public_converter")

    public Map<String, Object> convertCurrencyPublic(@RequestBody Map<String, Object> requestData) throws Exception {
        double amount = Double.parseDouble(requestData.get("amount").toString());
        String fromCurrency = (String) requestData.get("from_currency");
        String toCurrency = (String) requestData.get("to_currency");

        RestTemplate restTemplate = new RestTemplate();
        String exchangeRates;
        try {
            exchangeRates = restTemplate.getForObject(URL_RATES, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed API request" + e.getMessage());
        }

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(exchangeRates);
        } catch (Exception e) {
            throw new RuntimeException("Failed API request" + e.getMessage());
        }

        if (!jsonObject.has("rates")) {
            throw new JSONException("Invalid API response");
        }
        JSONObject rates = jsonObject.getJSONObject("rates");

        if (!rates.has(fromCurrency) ||
                !rates.has(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code");
        }
        double fromRate = rates.getDouble(fromCurrency);
        double toRate = rates.getDouble(toCurrency);
        double result = (amount / fromRate) * toRate;

        Map<String, Object> response = new HashMap<>();
        response.put("result", result);

        return response;
    }
}