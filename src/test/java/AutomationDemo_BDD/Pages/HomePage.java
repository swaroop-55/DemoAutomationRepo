package AutomationDemo_BDD.Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class HomePage {

	private static final Logger logger = LogManager.getLogger(MyAccountPage.class);
	WebDriver driver;
	Scenario scn;
	WebDriverWait wait;

	private By productCategory = By.xpath("//div[@id='woocommerce_product_categories-2']//li");
	private By myAccLink = By.linkText("My Account");
	private By logoImage = By.xpath("//img[@title='Automation Practice Site']");

	private By popup = By.xpath("//div[@id='dismiss-button']");
	private By iframe = By.xpath("//iframe[@id='aswift_6']");
	private By iframe1 = By.xpath("//iframe[@id='ad_iframe' and @title='Advertisement'] ");
	private By iframeMain = By.xpath("//iframe[@id='aswift_0']");

	public HomePage(WebDriver driver, Scenario scn) 
	{
		this.driver = driver;
		this.scn = scn;
	}
	
	public void clickOnMyAccLink()
	{
		WebElement myAcc = driver.findElement(myAccLink);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(myAccLink));
		myAcc.click();
		
//		driver.switchTo().alert().dismiss();
		
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeMain));
		WebElement singleFrame = driver.findElement(iframeMain);
		driver.switchTo().frame(singleFrame);
		
		WebElement insideFrame = driver.findElement(iframe1);
		driver.switchTo().frame(insideFrame);
		
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(popup));
		WebElement popuphandle = driver.findElement(popup);
		popuphandle.click();
		
		driver.switchTo().defaultContent();
		
		logger.info("Clicked on MyAccount Link ");
		scn.log("Clicked on My Account Link");
	}
	
	public void setProductCategory()
	{
		List<WebElement> prodCategoryList = driver.findElements(productCategory);
		Assert.assertEquals(false, prodCategoryList.isEmpty());
		for(WebElement list:prodCategoryList)
		{
			System.out.println(list.getText());
		}
		logger.info("Displayed the product category list, size of the list is : " +prodCategoryList.size());
		scn.log("Displayed the product category list with size : " +prodCategoryList.size());
	}
	
	public void validateProductCategory(List<String> prodList)
	{
		List<WebElement> prodCategoryList = driver.findElements(productCategory);
		for(int i=0; i<prodCategoryList.size(); i++)
		{
			if(prodCategoryList.get(i).getText().equals(prodList.get(i).toString()))
			{
				Assert.assertTrue(true);
				logger.info(prodCategoryList.get(i).getText()+ " is matched with expected " + prodList.get(i).toString()+ " header name in Datatable");
			}
			else
			{
				Assert.fail();
				logger.info(prodCategoryList.get(i).getText()+ "is NOT matched with expected " + prodList.get(i).toString()+ " header name in Datatable");
			}
		}
		logger.info("Validate the product category with expected data table");
	}
	
	public void sizeOfProductCategory(int prodCount)
	{
		List<WebElement> prodCategoryList = driver.findElements(productCategory);
		Assert.assertEquals(prodCount, prodCategoryList.size());
		logger.info("Validate the size of product category , size is:" +prodCategoryList.size());
		scn.log("Validate the size of product category with size : " +prodCategoryList.size());
	}

	public void logoDisplay()
	{
		WebElement logoImg = driver.findElement(logoImage);
		Assert.assertEquals(true, logoImg.isDisplayed());
		logger.info("Logo image Displayed");
		scn.log("Displayed logo on landing page");
	}
	public void fetchLogoHeightAndWidth()
	{
		WebElement logoImg = driver.findElement(logoImage);
		String logoHeight = logoImg.getAttribute("height");
		logger.info("Height of logo is: " +logoHeight);
		
		String logoWidth = logoImg.getAttribute("width");
		logger.info("Width of logo is: " +logoWidth);
	}
	public void validateHeightAndWidthOfLogo(String dim)
	{
		WebElement logoImg = driver.findElement(logoImage);
		Assert.assertEquals(dim, logoImg.getAttribute("height"));
		logger.info("Validate height of logo is: " +dim);
		
		Assert.assertEquals(dim, logoImg.getAttribute("width"));
		logger.info("Validate width of logo is: " +dim);
		
	}
}
