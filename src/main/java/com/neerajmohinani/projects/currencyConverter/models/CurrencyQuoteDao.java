package com.neerajmohinani.projects.currencyConverter.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyQuoteDao extends CrudRepository<CurrencyQuote,Long>{
	
	@Query(value="Select * from Currency_Quote where timestamp IN (select max(timestamp) from Currency_Quote)",nativeQuery=true)
	abstract CurrencyQuote getLatestCurrencyQuote();
}
