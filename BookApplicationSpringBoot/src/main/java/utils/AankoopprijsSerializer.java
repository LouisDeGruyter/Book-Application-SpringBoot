package utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import static utils.InitFormatter.*;

import java.io.IOException;
public class AankoopprijsSerializer extends JsonSerializer<Double> {
	 @Override
	    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	        String formattedValue = FORMATTER.format(value);
	        gen.writeString(formattedValue);
	    }

	
}
