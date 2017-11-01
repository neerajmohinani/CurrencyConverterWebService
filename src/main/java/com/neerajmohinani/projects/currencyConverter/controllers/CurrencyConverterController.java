package com.neerajmohinani.projects.currencyConverter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neerajmohinani.projects.currencyConverter.models.CurrencyCode;
import com.neerajmohinani.projects.currencyConverter.service.CurrencyConverterService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="CurrencyConverter")
public class CurrencyConverterController {

	@RequestMapping(value="/currency/{from}/{to}/{amount}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public double convertCurrency(@PathVariable("amount") double amount,
				@PathVariable("from")String from,@PathVariable("to")String to) throws Exception{
		
		double tranformedAmount = currencyConverterService.transformAmount(from,to,amount);
		return tranformedAmount;
	}
	
	@RequestMapping(value="/currency/all")
	public String getAllCurrencies() throws Exception{
		System.out.println(currencyConverterService.getAllCurrencyCodes());
		return currencyConverterService.getAllCurrencyCodes().toString();
		
	}
	
	@Autowired
	CurrencyConverterService currencyConverterService;
}
