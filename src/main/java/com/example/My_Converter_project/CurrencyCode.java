package com.example.My_Converter_project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "currency_codes")
public class CurrencyCode {
    @Id
    @Column(name="currency_iso_code", length=100)
    private  String currencyIsoCode;
    @Column(name="currency_name", length=100, nullable = false)
    private  String currencyName;
    @Column(name="rate")
  private BigDecimal rate;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public CurrencyCode() {
    }


    public CurrencyCode(String currencyIsoCode, String currencyName,BigDecimal rate) {
        this.currencyIsoCode = currencyIsoCode;
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public String getCurrencyIsoCode() {
        return currencyIsoCode;
    }

    public void setCurrencyIsoCode(String currencyIsoCode) {
        this.currencyIsoCode = currencyIsoCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
