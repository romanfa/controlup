package metric;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestConverting {
	private static Infrastructure infra;
	private static MetricConversionPage metricConversionPage;
	
	@BeforeClass
	public static void setup() {
		infra = new Infrastructure();
		metricConversionPage = new MetricConversionPage();
	}

	@Before
	public void before() {
		infra.openPage("https://www.metric-conversions.org");
	}
	
	@AfterClass
	public static void end() {
		infra.closePage();
	}

	@Test
	public void convertCelsiusToFahrenheit() {
		String referenceAnswer = "20°C= 68.00000°F";
		
		metricConversionPage.clickOnRightMenu("Temperature").clickOnRightMenu("Celsius").clickOnRightMenu("Celsius to Fahrenheit");
		
		metricConversionPage.provideText("20");
		
		String answer= metricConversionPage.getAnswerFromPage();

		assertTrue(referenceAnswer.equals(answer));
	}
	
	@Test
	public void convertMetersToFeet() {
		String referenceAnswer = "10m= 32ft 9.700788in";
		
		metricConversionPage.clickOnRightMenu("Length").clickOnRightMenu("Meters").clickOnRightMenu("Meters to Feet");
		
		metricConversionPage.provideText("10");
		
		String answer= metricConversionPage.getAnswerFromPage();
		
		assertTrue(referenceAnswer.equals(answer));
	}
	
	
	@Test
	public void convertOncesToGrams() {
		String referenceAnswer = "125oz= 3543.690g";
		
		metricConversionPage.clickOnRightMenu("Weight").clickOnRightMenu("Ounces").clickOnRightMenu("Ounces to Grams");
		
		metricConversionPage.provideText("125");
		
		String answer= metricConversionPage.getAnswerFromPage();
		
		assertTrue(referenceAnswer.equals(answer));
	}
	
}
