package weather;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import metric.Infrastructure;

public class TestWeather {

	private static Infrastructure infra;
	private static WeatherPage weatherPage;
	private static String zip="20852";
	private static int TemperatureFromWebPageInt;
	private static int TemperatureFromRestAPIInt;

	@BeforeClass
	public static void setup() {
		weatherPage = new WeatherPage();
		infra = new Infrastructure();
		infra.openPage("https://weather.com");
	}

	@AfterClass
	public static void end() {
		infra.closePage();
	}

	@Test
	public void compareTemperaturesFromWebAndRESTAPI() throws SocketTimeoutException, IOException{ 

		//Get Temperature from web page
		String TemperatureFromWebPage = weatherPage.provideSearchIndexAndReturnTemperature(zip);
		//Cut the last character
		TemperatureFromWebPage=TemperatureFromWebPage.substring(0, TemperatureFromWebPage.length()-1);
		TemperatureFromWebPageInt=Integer.valueOf(TemperatureFromWebPage);
		//return Temperature from REST API 
		String temperatureFromRESTAPI=WeatherAPIPage.returnTemperatureFromJSON(WeatherAPIPage.getTemperature(zip)[2]);
		TemperatureFromRestAPIInt=(int) Math.round(Double.valueOf(temperatureFromRESTAPI));

		System.out.println(TemperatureFromRestAPIInt);
		System.out.println(TemperatureFromWebPageInt);
		assertTrue(isLessThanTenPercentDiff(TemperatureFromRestAPIInt,TemperatureFromWebPageInt));
	}


	public boolean isLessThanTenPercentDiff(int val1, int val2) {
		int temp=0;
		if(val1<=val2) {
			temp = (val1-val2)/(val1+val2)/2*100;
		}else {
			temp=(val2-val1)/(val2+val1)/2*100;
		}
		if(temp>10) {
			return false;
		}else {
			return true;
		}
	}
}
