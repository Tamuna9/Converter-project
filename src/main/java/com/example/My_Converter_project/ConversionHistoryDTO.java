package com.example.My_Converter_project;

import java.time.LocalDateTime;
import java.util.Date;

public class ConversionHistoryDTO {
    private Long conversionId;
    private String sourceCurrency;
    private String targetCurrency;

    private Double sourceAmount;

    private Double targetAmount;

    private Date operationDate;


    public ConversionHistoryDTO(Long conversionId, String sourceCurrency, String targetCurrency, Double sourceAmount, Double targetAmount, Date operationDate) {
       this.conversionId = conversionId;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.operationDate = operationDate;
    }
    public Long getConversionId() {
        return conversionId;
    }

    public void setConversionId(Long conversionId) {
        this.conversionId = conversionId;
    }
    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}
