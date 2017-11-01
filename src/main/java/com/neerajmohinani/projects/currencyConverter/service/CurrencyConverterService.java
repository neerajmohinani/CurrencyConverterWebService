package com.neerajmohinani.projects.currencyConverter.service;



import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neerajmohinani.projects.currencyConverter.models.CurrencyCode;
import com.neerajmohinani.projects.currencyConverter.models.CurrencyQuote;
import com.neerajmohinani.projects.currencyConverter.models.CurrencyQuoteDao;
import com.neerajmohinani.projects.service.AbstractService;

@Service
public class CurrencyConverterService extends AbstractService<CurrencyQuote>{

	@Autowired
	CurrencyQuoteDao currencyQuoteDao;
	//Enter your currency layer access key here.
	private String access_key;
	
	@PostConstruct
	private void init(){
		super.init(currencyQuoteDao);
	}
	private static final String url ="http://apilayer.net/api/live?access_key="+access_key;
	public double transformAmount(String from, String to, double amount ) throws Exception {
		from=CurrencyQuote.SOURCE_CODE+from;
		to=CurrencyQuote.SOURCE_CODE+to;
		
		CurrencyQuoteDao cqDao = (CurrencyQuoteDao)tDao;
		CurrencyQuote currencyQuote = cqDao.getLatestCurrencyQuote();
		
		if(currencyQuote == null || currencyQuote.getLastUpdatedOn()+3600_000<System.currentTimeMillis()){
			
			RestTemplate restTemplate = new RestTemplate();
			CurrencyQuote currencyQuotetemp = restTemplate.getForObject(url, CurrencyQuote.class);
			currencyQuotetemp.setLastUpdatedOn(System.currentTimeMillis());
			cqDao.save(currencyQuotetemp);
			currencyQuote = currencyQuotetemp;
		}
	
		BigDecimal fromValue = BigDecimal.valueOf(+currencyQuote.getCurrencyMap().get(CurrencyCode.valueOf(from)));
		BigDecimal toValue = BigDecimal.valueOf(currencyQuote.getCurrencyMap().get(CurrencyCode.valueOf(to)));
		BigDecimal amnt = BigDecimal.valueOf(amount);
		BigDecimal convertedAmount = (toValue.divide(fromValue,3,RoundingMode.HALF_EVEN)).multiply(amnt);
		return convertedAmount.doubleValue();
	}
	public List<String> getAllCurrencyCodes() throws JsonProcessingException {
		CurrencyCode[] currencyCodes = CurrencyCode.values();
		List<String> cc = new ArrayList<>();
		for(CurrencyCode cCode: currencyCodes){
			ObjectMapper mapper = new ObjectMapper();
			cc.add(mapper.writeValueAsString(cCode));
			
			//System.out.println(mapper.writeValueAsString(cCode));
		}
		return cc;
	}
	
	
}
