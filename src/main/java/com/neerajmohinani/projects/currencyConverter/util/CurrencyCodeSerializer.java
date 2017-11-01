package com.neerajmohinani.projects.currencyConverter.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.neerajmohinani.projects.currencyConverter.models.CurrencyCode;

public class CurrencyCodeSerializer extends JsonSerializer<CurrencyCode> {

	@Override
	  public void serialize(CurrencyCode cc, JsonGenerator generator,
	            SerializerProvider provider) throws IOException,
	            JsonProcessingException {

	    generator.writeStartObject();
	    generator.writeFieldName("currency");
	    generator.writeString(cc.getCurrency());
	    generator.writeFieldName("currencyCode");
	    generator.writeString(cc.getCurrencyCode());
	    generator.writeEndObject();
	  }
}
