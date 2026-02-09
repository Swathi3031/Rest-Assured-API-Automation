package dsalgoHooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import driverFactory.DriverFactory;
import dsUtilities.ConfigReader;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import java.util.HashSet;
import java.util.Set;

public class Hooks {
	public static WebDriver driver;

	@Before

	public void setUp() {
		
		driver = DriverFactory.initDriver();
		String url = ConfigReader.getProperty("url");

		if (url == null || url.isEmpty()) {
			throw new RuntimeException("URL not specified in config.properties");
		}
		driver.get(url);
	}
	
	@After(order = 1)
	public void takeScreenShotOnFailure(Scenario scenario) {
		if(driver != null && scenario.isFailed()) {
			TakesScreenshot ts=(TakesScreenshot) driver;
			byte[]src=ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", "screenshot");
		}
		
	}
	
	

	@After(order=0)
	public void tearDown() {
		DriverFactory.quitDriver(); // Close browser
	}
	
}
