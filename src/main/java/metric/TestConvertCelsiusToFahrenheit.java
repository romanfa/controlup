package metric;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestConvertCelsiusToFahrenheit {
	private static Infrastructure infra;
	private static MetricConversionPage metricConversionPage;
	private static String input;
	private static String result;
	
	public TestConvertCelsiusToFahrenheit(String input, String result) {
		this.input=input;
		this.result=result;
	}
	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {{"20","68"},{"10","50"}});
		
	}
	
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
		
		metricConversionPage.clickOnRightMenu("Temperature").clickOnRightMenu("Celsius").clickOnRightMenu("Celsius to Fahrenheit");
		
		metricConversionPage.provideText(input);
		
		String answer= metricConversionPage.getAnswerFromPage();
		
		answer=answer.substring(answer.indexOf("=")+2,answer.indexOf("."));

		assertTrue(result.equals(answer));
	}
	
}
