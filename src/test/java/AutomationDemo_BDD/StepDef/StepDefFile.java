package AutomationDemo_BDD.StepDef;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import AutomationDemo_BDD.Core.WebDriverFactory;
import AutomationDemo_BDD.Pages.HomePage;
import AutomationDemo_BDD.Pages.MyAccountPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefFile {
	
	private static final Logger logger = LogManager.getLogger(StepDefFile.class);
	WebDriver driver;
	Scenario scn;
	String base_url = "https://practice.automationtesting.in/shop/";
	
	HomePage homePageObj;
	MyAccountPage myAccPageObj;
	
	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		scn.log("Browser got invoked");
		
		homePageObj = new HomePage(driver, scn);
		myAccPageObj = new MyAccountPage(driver, scn);
		
		
	}
	@After(order = 2)
	public void captureScreenShot(Scenario scn) {
		if(scn.isFailed()) {
			TakesScreenshot scrnshot = (TakesScreenshot) driver;
			byte[] data = scrnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of Failed Step :" +scn.getName());
			scn.log("Attached a screenshot as step got FAILED");
		}else {
			scn.log("Test Case Passed, No Screenshot Captured");
		}
	}
	
	@After(order = 1)
	public void tearDown() {
		WebDriverFactory.quitBrowser();
		scn.log("Browser got Quit");
	}
	
	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		WebDriverFactory.navigateToURL(base_url);
		scn.log("User is on HomePage");
	    
	}


	@Given("User click on My Account page from homepage")
	public void user_click_on_my_account_page_from_homepage() {
		
		homePageObj.clickOnMyAccLink();
		scn.log("User clicked on My Account link");
		
	
	}
	@When("User redirected to the Account page of the application where title is {string}")
	public void user_redirected_to_the_account_page_of_the_application_where_title_is(String myAccPageTitle) {
		
		myAccPageObj.validateMyAccountTitle(myAccPageTitle);
		scn.log("My Account Page title validated");
	    
	}
	@When("User enters {string} and {string} and click on login button")
	public void user_enters_and_and_click_on_login_button(String username, String password) {
		
		myAccPageObj.myAccountLogin(username, password);
		scn.log("User entered emailId and password");
	  
	}
	@Then("User is unable to login with an error message {string}")
	public void user_is_unable_to_login_with_an_error_message(String authFailMsg) {
	    
		myAccPageObj.validateAuthenticationFail(authFailMsg);
		scn.log("Failed Login Authentication Message Displayed");
	}
	
	@When("User see the product category")
	public void user_see_the_product_category() {
		homePageObj.setProductCategory();
		scn.log("user see product category lists");
	   
	}

	@Then("Validate product category as per expected product category listed below")
	public void validate_product_category_as_per_expected_product_category_listed_below(List<String> prodList) {
		homePageObj.validateProductCategory(prodList);
		scn.log("validated product category list with expected lists " +prodList.toString());
	   
	}
	@Then("Size of product category shoud be {int}")
	public void size_of_product_category_shoud_be(Integer prodCount) {
		homePageObj.sizeOfProductCategory(prodCount);
	    
	}

	@Given("User see the logo of the application")
	public void user_see_the_logo_of_the_application() {
	   homePageObj.logoDisplay();
	}

	@When("User fetch the height and width of logo")
	public void user_fetch_the_height_and_width_of_logo() {
		homePageObj.fetchLogoHeightAndWidth();
	   
	}
	@Then("Height and width of logo should be {string}")
	public void height_and_width_of_logo_should_be(String dim) {
		homePageObj.validateHeightAndWidthOfLogo(dim);
		scn.log("Height and width of logo after validation: " +dim);
	    
	}


	
	

}
