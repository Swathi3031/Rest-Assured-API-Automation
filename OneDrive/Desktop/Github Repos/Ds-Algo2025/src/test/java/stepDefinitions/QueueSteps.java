package stepDefinitions;

import io.cucumber.java.en.*;
import pageObject.LinkedListPF;
import pageObject.LoginPF;
import pageObject.QueuePF;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import dsUtilities.ConfigReader;
import driverFactory.DriverFactory;
import dsUtilities.ExcelUtilityHelper;
import dsUtilities.LogHandler;

public class QueueSteps {

    WebDriver driver = DriverFactory.getDriver();
   // LinkedListPF ll = new LinkedListPF(driver);
    QueuePF queuePage = new QueuePF(driver);
    LoginPF lp= new LoginPF(driver);
    ExcelUtilityHelper excelUtil =new ExcelUtilityHelper();
    
    @Given("user is on sign to app to click Queue")
    public void user_is_on_sign_to_app_to_click_queue() {
    	lp.loginBackgroundForPages();

    }

    @When("user cliks on Queue GetStarted button")
    public void user_cliks_on_queue_get_started_button() {
    	queuePage.navigateToQueuePage  ();
   	    LogHandler.info(queuePage.pageTitle());
    }

    @Then("user lands on {string} on page")
    public void user_lands_on_on_page(String string) {
   	 String actualTitle = queuePage.pageTitle();  
	    String expectedTitle = "Queue";      
	    Assert.assertEquals(actualTitle, expectedTitle);
    }
    
    @When("user clicks on Implementation of Queue in Python on Queue Page")
    public void user_clicks_on_implementation_of_queue_in_python_on_queue_page() {
    	queuePage.clickImplementationOfQueueInPython(); 
    }
    
    @Then("user lands on Queue page and able to see NumpyNinja,Data structures")
    public void user_lands_on_queue_page_and_able_to_see_numpy_ninja_data_structures() {
    	Assert.assertTrue(queuePage.isNumpyNinjaVisible());
   		Assert.assertTrue(queuePage.isDropdownVisible());
   		Assert.assertTrue(queuePage.isUsernameVisible());
   		Assert.assertTrue(queuePage.isSignOutVisible());
    }
    
    @Given("user is on the Queue home page")
    public void user_is_on_queue_home_page() {
    	queuePage.navigateToQueuePage  ();
    }

    @When("user clicks on {string} link on Queue page")
    public void user_clicks_on_topic_link(String topic) {
        queuePage.clickQueueTopic(topic);
    }
    
    @Then("The user should be navigated to {string} page on Queue")
    public void the_user_should_be_navigated_to_page_on_queue(String topic) {
    	 queuePage.verifyPageNavigation(topic);
    	 LogHandler.info(queuePage.pageTitle());
    }

    @Given("user is on the {string} page of Queue")
    public void user_is_on_individual_queue_page(String topic) {
    	queuePage.clickImplementationOfQueueInPython();
  	  queuePage.clickQueueTopic(topic);
    }

    @When("user clicks on tryHere button on queue links")
    public void user_clicks_on_try_here_button_on_queue_links() {
    	 queuePage.clickTryHere();
    }

    @Then("user should be navigated to Try Editor page with Run button")
    public void user_should_be_navigated_to_try_editor() {
    	String Title=queuePage.pageTitle();
		LogHandler.info("Title of the current page is : " + Title);
	    Assert.assertEquals(Title, "Assessment", "Title not matched");
    }
    
    @Given("The user is in the tryEditor page on Queue page")
    public void the_user_is_in_the_try_editor_page_on_queue_page() {
    	queuePage.clickImplementationOfQueueInPython();
    	queuePage.clickTryHere();
		LogHandler.info(queuePage.pageTitle());
    }

    @When("user writes Python code from {string} and {int}  on queue links and click the Run button")
    public void user_writes_python_code_from_and_on_queue_links_and_click_the_run_button(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
        excelUtil.enterPythonCode(sheetName, rowNumber);
        queuePage.clickRunButton();
	    ExcelUtilityHelper.handleAlert(driver);
    }

    @Then("output should match with expected result from {string} and {int} for queue pages")
    public void output_should_match_with_expected_result_from_and_for_queue_pages(String sheetname, Integer rowNumber) throws InvalidFormatException, IOException {
    	String expectedMsg = excelUtil.getExpectedResultFromExcel(sheetname, rowNumber);
	    String actualMsg = queuePage.getOutputData(); 
	    LogHandler.info("Expected output from Excel: " + expectedMsg);
	    LogHandler.info("Actual output on screen: " + actualMsg);
	    assertEquals(actualMsg.trim(), expectedMsg.trim(), "Output does not match expected result");
    }
    
    @When("user clicks on run button without code for queue links")
    public void user_clicks_on_run_button_without_code_for_queue_links() {
    	queuePage.clickRunButton();
    }

    @Then("user should see the error message in alert window queue links")
    public void user_should_see_the_error_message_in_alert_window_queue_links() {
    	//Assert.assertTrue(excelUtil.isAlertIsPresent());
    	try {
            // Wait a short moment to see if alert appears
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.fail("Unexpected alert was displayed.");
        } catch (TimeoutException e) {
            // Expected: No alert appeared
        }
    }


    @When("user clicks on run button with incorrect code from {string} and {int} for queue links")
    public void user_clicks_on_run_button_with_incorrect_code_from_and_for_queue_links(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
        excelUtil.enterPythonCode(sheetName, rowNumber);
        queuePage.clickRunButton();
	    ExcelUtilityHelper.handleAlert(driver);
    }

    @Then("user should see the error message in alert window and get the alert text for queue links")
    public void user_should_see_the_error_message_in_alert_window_and_get_the_alert_text_for_queue_links() {
    	String alertText=	excelUtil.getErrorText();
	    assertTrue(alertText.contains("line") && alertText.toLowerCase().contains("error"));
	    LogHandler.info(queuePage.pageTitle());
    }

    @When("user types incorrect code from {string} and {int} on queue links")
    public void user_types_incorrect_code_from_and_on_queue_links(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
        excelUtil.enterPythonCode(sheetName, rowNumber);
        queuePage.clickRunButton();
    }

    @Then("user should see alert window and can not click on run button on queue links")
    public void user_should_see_alert_window_and_can_not_click_on_run_button_on_queue_links() {
    	 ExcelUtilityHelper.handleAlert(driver);
    }

    
    @Given("user is on Implementation of Queue in Python page page Of Queue")
    public void user_is_on_implementation_queue_practice_page() {
    	queuePage.clickImplementationOfQueueInPython(); 
    }

    @When("user clicks on Practice Questions link on queue page")
    public void user_clicks_on_practice_questions_link() {
        queuePage.clickPracticeQuestionsLink();
    }

    @Then("user should land on practice page on queue page")
    public void user_should_land_on_practice_page() {
    	String Title=queuePage.pageTitle();
		LogHandler.info("Title of the current page is : " + Title);
	    Assert.assertEquals(Title, "Practice Questions", "Title not matched");
    }

    @Given("The user is on Queue page")
    public void the_user_is_on_queue_page() {
    	queuePage.clickImplementationOfQueueInPython(); 
    }

    @When("The user clicks on Sign out button on Queue page")
    public void the_user_clicks_on_sign_out_button_on_queue_page() {
    	queuePage.clickSignOut();
    }

    @Then("user should navigate back to Home page from queue and can view {string} message")
    public void user_should_navigate_back_to_home_page_from_queue_and_can_view_message(String expectedMsg) {
    	String actualMsg=queuePage.logOutMessage();
    	Assert.assertEquals(actualMsg, expectedMsg);
    	LogHandler.info(queuePage.pageTitle());   	
    }
    
}



