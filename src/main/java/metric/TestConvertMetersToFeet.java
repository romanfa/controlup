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
public class TestConvertMetersToFeet {
	private static Infrastructure infra;
	private static MetricConversionPage metricConversionPage;
	private static String input;
	private static String result;
	
	public TestConvertMetersToFeet(String input, String result) {
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
	public void convertMetersToFeet() {
		
		metricConversionPage.clickOnRightMenu("Length").clickOnRightMenu("Meters").clickOnRightMenu("Meters to Feet");
		
		metricConversionPage.provideText(input);
		
		String answer= metricConversionPage.getAnswerFromPage();
		answer=answer.substring(answer.indexOf("=")+2,answer.indexOf("f"));
		
		assertTrue(result.equals(answer));
	}
	
}
