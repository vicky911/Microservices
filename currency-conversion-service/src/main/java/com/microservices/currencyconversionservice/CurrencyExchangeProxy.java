package com.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//URL we want to call is http://localhost:8000/currency-exchange/from/{from}/to/{to}
//So localhost:8000 goes as url in FeignClient and Rest part goes as a method part
//name is the application name of Rest Api from which you want data
//
@FeignClient(name="currency-exchange")
//@FeignClient(name="currency-exchange",url="localhost:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
