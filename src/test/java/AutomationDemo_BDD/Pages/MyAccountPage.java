package AutomationDemo_BDD.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;


public class MyAccountPage {

	private static final Logger logger = LogManager.getLogger(MyAccountPage.class);
	WebDriver driver;
	Scenario scn;
	WebDriverWait wait;

	private By usernameField = By.id("username");
	private By passwordField = By.id("password");
	private By loginButton = By.name("login");
	private By AuthFailMessage = By.xpath("//ul[@class='woocommerce-error']/li");

	public MyAccountPage(WebDriver driver,Scenario scn)
	{
		this.driver = driver;
		this.scn = scn;
	}

	public void validateMyAccountTitle(String myAccPageTitle)
	{
		/*
		 * wait = new WebDriverWait(driver, 20); boolean xyz =
		 * wait.until(ExpectedConditions.titleIs(myAccPageTitle));
		 * Assert.assertEquals(true, xyz);
		 * logger.info("Validation of MyAccount Page title : " +myAccPageTitle);
		 * scn.log("Validation of MyAccount Page title : " +myAccPageTitle);
		 */	
		
		String actualPageTitle = driver.getTitle();
		Assert.assertEquals(myAccPageTitle, actualPageTitle);
		logger.info("Validation of MyAccount Page title : " +myAccPageTitle);
		scn.log("Validation of MyAccount Page title : " +myAccPageTitle);
		
	}
	
	public void myAccountLogin(String username, String password)
	{
		WebElement unameField = driver.findElement(usernameField);
		unameField.sendKeys(username);
		WebElement pwdField = driver.findElement(passwordField);
		pwdField.sendKeys(password);
		
		logger.info("User entered emailid and password");
		scn.log("User entered emailid and password");
		
		WebElement loginBtn = driver.findElement(loginButton);
		loginBtn.click();
		
		logger.info("User clicked on Login Button");
		scn.log("User clicked on Login Button");
	}
	
	public void validateAuthenticationFail(String authFailMsg) 
	{
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(AuthFailMessage));
		
		WebElement failedLoginMessage = driver.findElement(AuthFailMessage);
		Assert.assertEquals(authFailMsg,failedLoginMessage.getText());
		
		logger.info("Validating failed Login Message " +failedLoginMessage.getText());
		scn.log("Validating failed Login Message " +failedLoginMessage.getText());
	}

	
	
	
	
	
	
	
		

}
