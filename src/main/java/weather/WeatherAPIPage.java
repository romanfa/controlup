package weather;

import java.io.IOException;
import java.net.SocketTimeoutException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherAPIPage  extends InfrastructureRESTAPI  {

	private static final String apiKey="da7bd93aa41d009a0652d919648d86d2";



	public static String[] getTemperature(String zip) throws SocketTimeoutException, IOException {

		String request ="http://api.openweathermap.org/data/2.5/weather?zip="+zip+",us&appid="+apiKey;

		return getWithResponse(request);

	}

	public static String returnTemperatureFromJSON(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();


		JsonFactory factory = objectMapper.getFactory();
		JsonParser parser = factory.createParser(json);
		JsonNode actualObj = objectMapper.readTree(parser);
		System.out.println(actualObj.get("main").get("temp").toString());
		return kelvinToPfahrenheit(actualObj.get("main").get("temp").toString());
	}


	
	public static String kelvinToPfahrenheit(String kelvin) {
		
		return String.valueOf(((Double.valueOf(kelvin)-273.15)*9/5+32));
		
		
	}

}
