package AutomationDemo_BDD.Core;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
	
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	private static WebDriver driver = null;
	
	public static WebDriver getWebDriverForBrowser(String browser)
	{
		switch(browser.toLowerCase())
		{
		case "chrome":
			driver = new ChromeDriver();
			logger.info("Chrome Browser invoked");
			break;
		case "firefox":
			driver = new FirefoxDriver();
			logger.info("Firefox browser invoked");
			break;
		case "headless":
			ChromeOptions option = new ChromeOptions();
			option.addArguments("headless");
			driver = new ChromeDriver(option);
			logger.info("Headless browser invoked");
		default:
			logger.fatal("No Such Browser is implemented, browser name is:" +browser);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("Browser window maximized and set timeout is 20secs");
		return driver;
	}
	
	public static String getBrowserName()
	{
		String defaultBrowser = "firefox";
		String browserSentFromCmd = System.getProperty("browser");
		if(browserSentFromCmd == null)
		{
			return defaultBrowser;
		}
		else
		{
			return browserSentFromCmd;
		}
	}
	
	public static void navigateToURL(String url)
	{
		driver.get(url);
		logger.info("Navigate to " +url);
	}
	public static void quitBrowser()
	{
		driver.quit();
		logger.info("Quit the browser");
	}
	
	public static void switchToNewWindow()
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		for(String handle:allWindowHandles)
		{
			driver.switchTo().window(handle);
			logger.info("Switch to new window, ID is: " +handle);
		}
	}
	

}
