package metric;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;


public class Infrastructure {

	private static Logger log = Logger.getLogger(Infrastructure.class);

	public static  WebDriver driver;

	public  Infrastructure() {
		Path resourceDirectory= Paths.get("src","main","resources");
		String absolutePath=resourceDirectory.toFile().getAbsolutePath();
		ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(new File(absolutePath+"\\chromedriver.exe")).usingAnyFreePort().withSilent(true).withVerbose(false).build();

		try {
			chromeDriverService.start();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("test-type");
		chromeOptions.addArguments("--disable-extensions");
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("--disable-notifications");

		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.addArguments("--start-maximized");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", prefs);

		driver= new ChromeDriver(chromeDriverService, chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
	}


	public void openPage(String url) {
		driver.get(url);
	}

	public void closePage() {
		driver.close();
	}

}