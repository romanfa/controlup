package weather;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import metric.Infrastructure;

public class WeatherPage {
	
	
	
	public String provideSearchIndexAndReturnTemperature(String sendKeys) {
		
		WebElement inputField = (new WebDriverWait(Infrastructure.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='LocationSearch_input']")));
		inputField.click();
		inputField.sendKeys("20852");
		WebElement dropDown = (new WebDriverWait(Infrastructure.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Rockville, MD']")));
		dropDown.click();
		WebElement answerField = (new WebDriverWait(Infrastructure.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid='TemperatureValue']")));
		return answerField.getText();
	}
		
}
