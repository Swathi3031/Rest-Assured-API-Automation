package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.LinkedListPF;
import pageObject.LoginPF;
import dsUtilities.ConfigReader;
import driverFactory.DriverFactory;
import dsUtilities.ExcelUtilityHelper;
import dsUtilities.LogHandler;

public class LinkedListSteps {
	
	
	WebDriver driver = DriverFactory.getDriver();
    LinkedListPF llPage = new LinkedListPF(driver);
    LoginPF lp= new LoginPF(driver);
    ExcelUtilityHelper excelUtil =new ExcelUtilityHelper();
    

    @Given("user is on sign to app to click Linked List")
	public void user_is_on_sign_to_app_to_click_linked_list() {
    	lp.loginBackgroundForPages();
	}

	@When("user cliks on Linked List GetStarted button")
	public void user_cliks_on_linked_list_get_started_button() {
		llPage.navigateToLLPage();
   	    LogHandler.info(llPage.pageTitle());
	}
	
	@Then("user lands on Linked List page")
	public void user_lands_on_linked_list_page() {
		String actualTitle = llPage.pageTitle();  
	    String expectedTitle = "Linked List";      
	    Assert.assertEquals(actualTitle, expectedTitle);
	}
    
	@When("user clicks on Introduction on LinkedList Page")
	public void user_clicks_on_introduction_on_linked_list_page() {
		llPage.clickIntroductionLink();
	}
		
	@Then("user lands on LinkedList page and able to see NumpyNinja,Data structures dropdown,username and signout links")
	public void user_lands_on_linked_list_page_and_able_to_see_numpy_ninja_data_structures_dropdown_username_and_signout_links() {
		Assert.assertTrue(llPage.isNumpyNinjaVisible());
   		Assert.assertTrue(llPage.isDropdownVisible());
   		Assert.assertTrue(llPage.isUsernameVisible());
   		Assert.assertTrue(llPage.isSignOutVisible());
	}

	@Given("user is on linkedlist home page")
	public void user_is_on_linkedlist_home_page() {
		llPage.clickIntroductionLink();
	}

	@When("user clicks on {string} link on linkedlist page")
	public void user_clicks_on_link_on_linkedlist_page(String topic) {
	    llPage.clickLinkedlistTopic(topic);
	}

	@Then("user should be navigated to {string} page of linkedlist")
	public void user_should_be_navigated_to_page_of_linkedlist(String topic) {
	   llPage.verifyPageNavigation(topic);
	   LogHandler.info(llPage.pageTitle());
	}
	
	@Given("user is on the {string} page of Linkedlist")
	public void user_is_on_the_page_of_linkedlist(String topic) {
		llPage.clickIntroductionLink();
		llPage.clickLinkedlistTopic(topic);
	}

	@When("user clicks on tryHere button of LinkedList page")
	public void user_clicks_on_try_here_button_of_linked_list_page() {
		llPage.clickTryHere();
	}

	@Then("user should be navigated to Try Editor page with Run button on linkedlist")
	public void user_should_be_navigated_to_try_editor_page_with_run_button_on_linkedlist() {
		LogHandler.info(llPage.pageTitle());
	}

	@Given("The user is in the tryEditor page on Linkedlist page")
	public void the_user_is_in_the_try_editor_page_on_linkedlist_page() {
	    llPage.clickIntroductionLink();
		llPage.clickTryHere();
		LogHandler.info(llPage.pageTitle());
	}

	@When("user writes Python code from {string} and {int}  on Linkedlist links and click the Run button")
	public void user_writes_python_code_from_and_on_linkedlist_links_and_click_the_run_button(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {  
        excelUtil.enterPythonCode(sheetName, rowNumber);
        llPage.clickRunButton();
	    ExcelUtilityHelper.handleAlert(driver);
	}

	@Then("output should match with expected result from {string} and {int} for Linkedlist pages")
	public void output_should_match_with_expected_result_from_and_for_linkedlist_pages(String sheetname, Integer rowNumber) throws InvalidFormatException, IOException {
		String expectedMsg = excelUtil.getExpectedResultFromExcel(sheetname, rowNumber);
	    String actualMsg = llPage.getOutputData(); 
	    LogHandler.info("Expected output from Excel: " + expectedMsg);
	    LogHandler.info("Actual output on screen: " + actualMsg);
	    assertEquals(actualMsg.trim(), expectedMsg.trim(), "Output does not match expected result");
	}

	@Given("user is on try Editor page on Linkedlist")
	public void user_is_on_try_editor_page_on_linkedlist() {
		llPage.clickIntroductionLink();
		llPage.clickTryHere();
		LogHandler.info(llPage.pageTitle());
	}

	@When("user clicks on run button without code for Linkedlist links")
	public void user_clicks_on_run_button_without_code_for_linkedlist_links() {
	    llPage.clickRunButton();
	}

	@Then("user should see the error message in alert window Linkedlist links")
	public void user_should_see_the_error_message_in_alert_window_linkedlist_links() {
    	try {
            // Wait a short moment to see if alert appears
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.fail("Unexpected alert was displayed.");
        } catch (TimeoutException e) {
            // Expected: No alert appeared
        }
	}

	@When("user clicks on run button with incorrect code from {string} and {int} for Linkedlist links")
	public void user_clicks_on_run_button_with_incorrect_code_from_and_for_linkedlist_links(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
        excelUtil.enterPythonCode(sheetName, rowNumber);
        llPage.clickRunButton();
	    ExcelUtilityHelper.handleAlert(driver);
	}

	@Then("user should see the error message in alert window and get the alert text for Linkedlist links")
	public void user_should_see_the_error_message_in_alert_window_and_get_the_alert_text_for_linkedlist_links() {
		String alertText=	excelUtil.getErrorText();
	    assertTrue(alertText.contains("line") && alertText.toLowerCase().contains("error"));
	}

	@When("user types incorrect code from {string} and {int} on Linkedlist links")
	public void user_types_incorrect_code_from_and_on_linkedlist_links(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
        excelUtil.enterPythonCode(sheetName, rowNumber);
        llPage.clickRunButton();
	}

	@Then("user should see alert window and can not click on run button on Linkedlist links")
	public void user_should_see_alert_window_and_can_not_click_on_run_button_on_linkedlist_links() {
		 ExcelUtilityHelper.handleAlert(driver);
	}

	@Given("user is on Introduction page Of LinkedList")
	public void user_is_on_introduction_page_of_linked_list() {
		llPage.clickIntroductionLink();
	}

	@When("user clicks on Practice Questions link on Linkedlist")
	public void user_clicks_on_practice_questions_link_on_linkedlist() {
		llPage.clickPracticeQuestionsLink();
	}

	@Then("user should land on practice page on Linkedlist")
	public void user_should_land_on_practice_page_on_linkedlist() {
		String Title=llPage.pageTitle();
		LogHandler.info("Title of the current page is : " + Title);
	    Assert.assertEquals(Title, "Practice Questions", "Title not matched");
	}

	@Given("The user is on LinkedList page")
	public void the_user_is_on_linked_list_page() {
		llPage.clickIntroductionLink();
	}

	@When("The user clicks on Sign out button on Linkedlist page")
	public void the_user_clicks_on_sign_out_button_on_linkedlist_page() {
		llPage.clickSignOut();
	}

	@Then("user should navigate back to Home page from linkedlist and can view {string} message")
	public void user_should_navigate_back_to_home_page_from_linkedlist_and_can_view_message(String expectedMsg) {
		String actualMsg=llPage.logOutMessage();
    	Assert.assertEquals(actualMsg, expectedMsg); 
    	LogHandler.info(llPage.pageTitle());
	}	
}

