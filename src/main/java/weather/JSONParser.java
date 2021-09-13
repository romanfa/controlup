package weather;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser {


	public String returnTemperatureFromJSON(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();


		JsonFactory factory = objectMapper.getFactory();
		JsonParser parser = factory.createParser(json);
		JsonNode actualObj = objectMapper.readTree(parser);
		
		return actualObj.get("main").get("temp").toString();
	}

}
