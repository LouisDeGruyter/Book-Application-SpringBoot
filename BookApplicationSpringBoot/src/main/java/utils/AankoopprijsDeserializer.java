package utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import static utils.InitFormatter.*;
public class AankoopprijsDeserializer extends JsonDeserializer<Double> {

	 @Override
	    public Double deserialize(JsonParser parser, DeserializationContext context) throws IOException {
	        String value = parser.getValueAsString();
	        if (value == null || value.isEmpty()) {
	            return null;
	        }
	        try {
	            return FORMATTER.parse(value).doubleValue();
	        } catch (Exception e) {
	            throw new IllegalArgumentException("Invalid value for quality field: " + value, e);
	        }
	    }

}
