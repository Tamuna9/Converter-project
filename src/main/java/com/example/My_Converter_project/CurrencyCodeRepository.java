package com.example.My_Converter_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CurrencyCodeRepository extends JpaRepository<CurrencyCode, String> {

    Optional<CurrencyCode> findByCurrencyIsoCode(String currencyIsoCode);

    List<CurrencyCode> findByCurrencyName(String currencyName);


}
;