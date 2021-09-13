package weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class InfrastructureRESTAPI {
	
	public static String[] getWithResponse(String urlRequest) throws IOException, SocketTimeoutException {
		String[] returnValues= {null,null,null};
		StringBuilder strBuilder = new StringBuilder();
		URL url;
		HttpURLConnection conn=null;
		try {
			url = new URL(urlRequest);
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(1000);
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				strBuilder.append(line); 
			} 

			rd.close();
			conn.disconnect();
			returnValues[0] = conn.getHeaderFields().toString();
			returnValues[1] =String.valueOf(conn.getResponseCode());
			returnValues[2]=strBuilder.toString();

		} catch (Exception e) {
			returnValues[1]=String.valueOf(conn.getResponseCode());
		}
		return returnValues;

	}
	
}
