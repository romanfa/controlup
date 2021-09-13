package metric;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MetricConversionPage {

	public MetricConversionPage clickOnRightMenu(String buttonNameFromText) {
		WebElement element = Infrastructure.driver.findElement(By.xpath("//a[text()='"+buttonNameFromText+"']"));
		element.click();
		return this;
	}

	public void provideText(String sendKeys) {
		WebElement inputField = Infrastructure.driver.findElement(By.xpath("//input[@name='argumentConv']"));
		inputField.clear();
		inputField.sendKeys(sendKeys);
	}
	
	
	public String getAnswerFromPage() {
		WebElement inputField = Infrastructure.driver.findElement(By.xpath("//p[@id='answer']"));
		return inputField.getText();
	}
}
