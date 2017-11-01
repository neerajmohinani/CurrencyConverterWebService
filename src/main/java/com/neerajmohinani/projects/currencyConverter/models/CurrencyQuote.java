package com.neerajmohinani.projects.currencyConverter.models;

import java.time.LocalDateTime;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name="CurrencyQuote")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrencyQuote {

	@Id
	private long timestamp;
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	@JsonProperty("quotes")
	private Map<CurrencyCode,Double> currencyMap;
	
	@Column
	private String source;
	public static final String SOURCE_CODE = "USD";
	
	@Column
	private long lastUpdatedOn;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public Map<CurrencyCode, Double> getCurrencyMap() {
		return currencyMap;
	}
	public void setCurrencyMap(Map<CurrencyCode, Double> currencyMap) {
		this.currencyMap = currencyMap;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public long getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(long lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
}
