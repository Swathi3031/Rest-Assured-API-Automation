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
import pageObject.GraphPF;
import pageObject.LinkedListPF;
import pageObject.LoginPF;
import dsUtilities.ConfigReader;
import driverFactory.DriverFactory;
import dsUtilities.ExcelUtilityHelper;
import dsUtilities.LogHandler;

public class GraphSteps {
	
	    WebDriver driver = DriverFactory.getDriver();
	    GraphPF graphPage = new GraphPF(driver);
	    LoginPF lp= new LoginPF(driver);
	    ExcelUtilityHelper excelUtil =new ExcelUtilityHelper();
	   

	    @Given("user is on sign to app to click graph")
		public void user_is_on_sign_to_app_to_click_graph() {
	    	lp.loginBackgroundForPages();
	    	
		}

		@When("user cliks on Graph GetStarted button")
		public void user_cliks_on_graph_get_started_button() {
			graphPage.getStartbtnclick();
			LogHandler.info("user is on data structure page:" +graphPage.pageTitle());
		}
		
		@Then("user lands on Graph page")
		public void user_lands_on_graph_page() {
			 String actualTitle = graphPage.pageTitle();  
			    String expectedTitle = "Graph";      
			    Assert.assertEquals(actualTitle, expectedTitle);
		}
		
		@Given("user is on graph page")
		public void user_is_on_graph_page() {
			graphPage.clickGraph();
		}

	@Then("user lands on Graph page and able to see NumpyNinja,Data structures dropdown,username and signout links")
	public void user_lands_on_graph_page_and_able_to_see_numpy_ninja_data_structures_dropdown_username_and_signout_links() {
		Assert.assertTrue(graphPage.isNumpyNinjaVisible());
   		Assert.assertTrue(graphPage.isDropdownVisible());
   		Assert.assertTrue(graphPage.isUsernameVisible());
   		Assert.assertTrue(graphPage.isSignOutVisible());
	}

	@When("user clicks on graph of graph Page")
	public void user_clicks_on_graph_of_graph_page() {
		graphPage.clickGraph();
		LogHandler.info(graphPage.pageTitle());
	}

	@Then("user lands on {string} page of graph")
	public void user_lands_on_page_of_graph(String string) {
		String Title=graphPage.pageTitle();
		LogHandler.info("Title of the current page is : " + Title);
	    Assert.assertEquals(Title, "Graph", "Title not matched");
	}

	@Given("user is on Graph of Graph page")
	public void user_is_on_graph_of_graph_page() {
		graphPage.clickGraph();
	}
	
	@When("user clicks on Tryhere link on Graph Page")
	public void user_clicks_on_tryhere_link_on_graph_page() {
		graphPage.clickTryHere();
	}

	@Then("user should be directed to {string} page with Run button")
	public void user_should_be_directed_to_page_with_run_button(String string) {
		String Title=graphPage.pageTitle();
	LogHandler.info("Title of the current page is : " + Title);
	Assert.assertEquals(Title, "Assessment", "Title not matched");
	}

	@Given("The user is in the tryEditor page on Graph page")
	public void the_user_is_in_the_try_editor_page_on_graph_page() {
		graphPage.clickGraph();
		graphPage.clickTryHere();
		LogHandler.info(graphPage.pageTitle());
	}

	@When("user writes Python code from {string} and {int} and click the Run button")
	public void user_writes_python_code_from_and_and_click_the_run_button(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {  
        excelUtil.enterPythonCode(sheetName, rowNumber);
        graphPage.clickRunButton();
        ExcelUtilityHelper.handleAlert(driver);
	}

	@Then("output should match with expected result from {string} and {int}")
	public void output_should_match_with_expected_result_from_and(String sheetname, Integer rowNumber) throws InvalidFormatException, IOException {
		String expectedMsg = excelUtil.getExpectedResultFromExcel(sheetname, rowNumber);
	    String actualMsg = graphPage.getOutputData(); 
	    LogHandler.info("Expected output from Excel: " + expectedMsg);
	    LogHandler.info("Actual output on screen: " + actualMsg);
	    assertEquals(actualMsg.trim(), expectedMsg.trim(), "Output does not match expected result");
	}

	@When("user clicks on run button without code on Graph page")
	public void user_clicks_on_run_button_without_code_on_graph_page() {
		graphPage.clickRunButton();
	}

	@Then("user cannot able click on run button on Graph page")
	public void user_cannot_able_click_on_run_button_on_graph_page() {
		try {
            // Wait a short moment to see if alert appears
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.fail("Unexpected alert was displayed.");
        } catch (TimeoutException e) {
            // Expected: No alert appeared
        }
	}

	@Given("The user is in the tryEditor page of Graph Representations page")
	public void the_user_is_in_the_try_editor_page_of_graph_representations_page() {
		graphPage.clickGraph();
		graphPage.clickTryHere();
		LogHandler.info(graphPage.pageTitle());
	}
	
	@When("user clicks on run button with incorrect code from {string} and {int} on Graph page")
	public void user_clicks_on_run_button_with_incorrect_code_from_and_on_graph_page(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
        excelUtil.enterPythonCode(sheetName, rowNumber);
        graphPage.clickRunButton();
	    ExcelUtilityHelper.handleAlert(driver);
	}

	@Then("user should see the error message in alert window and get the alert text on Graph page")
	public void user_should_see_the_error_message_in_alert_window_and_get_the_alert_text_on_graph_page() {
		String alertText=	excelUtil.getErrorText();
	    assertTrue(alertText.contains("line") && alertText.toLowerCase().contains("error"));
	    LogHandler.info(graphPage.pageTitle());
	}

	@When("user types incorrect code from {string} and {int} on Graph page")
	public void user_types_incorrect_code_from_and_on_graph_page(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {  
        excelUtil.enterPythonCode(sheetName, rowNumber);
        graphPage.clickRunButton();
	    
	}

	@Then("user should see alert window and can not click on run button on Graph page")
	public void user_should_see_alert_window_and_can_not_click_on_run_button_on_graph_page() {
		 ExcelUtilityHelper.handleAlert(driver);
	}

	@When("user clicks on GraphReprasentions of grapg Page")
	public void user_clicks_on_graph_reprasentions_of_grapg_page() {
		graphPage.clickPracticeQuestionsLink();
	}

	@Then("user lands on {string} of graph Page")
	public void user_lands_on_of_graph_page(String string) {
		String Title=graphPage.pageTitle();
		LogHandler.info("Title of the current page is : " + Title);
	    Assert.assertEquals(Title, "Practice Questions", "Title not matched");
	}

	@Given("user is on GraphReprasentions of Graph page")
	public void user_is_on_graph_reprasentions_of_graph_page() {
		graphPage.clickGraphRepresentations();
	}

	@When("user clicks on Tryhere link on GraphReprasentions Page")
	public void user_clicks_on_tryhere_link_on_graph_reprasentions_page() {
		graphPage.clickTryHere();
	}

	@Then("user should be directed to {string} page with Run button on GraphReprasentions page")
	public void user_should_be_directed_to_page_with_run_button_on_graph_reprasentions_page(String string) {
		String Title=graphPage.pageTitle();
		LogHandler.info("Title of the current page is : " + Title);
		Assert.assertEquals(Title, "Assessment", "Title not matched");
	}

	@Given("The user is in the tryEditor page on GraphReprasentions page")
	public void the_user_is_in_the_try_editor_page_on_graph_reprasentions_page() {
		graphPage.clickGraphRepresentations();
		graphPage.clickTryHere();
	}

	@When("user clicks on run button with incorrect code from {string} and {int} on GraphReprasentions page")
	public void user_clicks_on_run_button_with_incorrect_code_from_and_on_graph_reprasentions_page(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {  
        excelUtil.enterPythonCode(sheetName, rowNumber);
        graphPage.clickRunButton();
	    ExcelUtilityHelper.handleAlert(driver);
	}

	@Then("user should see the error message in alert window and get the alert text on GraphReprasentions page")
	public void user_should_see_the_error_message_in_alert_window_and_get_the_alert_text_on_graph_reprasentions_page() {
		String alertText=	excelUtil.getErrorText();
	    assertTrue(alertText.contains("line") && alertText.toLowerCase().contains("error"));
	}

	@When("user types incorrect code from {string} and {int} on GraphReprasentions page")
	public void user_types_incorrect_code_from_and_on_graph_reprasentions_page(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
        excelUtil.enterPythonCode(sheetName, rowNumber);
        graphPage.clickRunButton();
	}

	@Then("user should see alert window and can not click on run button on GraphReprasentions page")
	public void user_should_see_alert_window_and_can_not_click_on_run_button_on_graph_reprasentions_page() {
		ExcelUtilityHelper.handleAlert(driver);
	}

	@Given("user is in tryEditor page and navigates to GraphReprasentions page")
	public void user_is_in_try_editor_page_and_navigates_to_graph_reprasentions_page() {
		graphPage.clickGraphRepresentations();
	}

	@When("user clicks on Practice Questions in graph representations page")
	public void user_clicks_on_practice_questions_in_graph_representations_page() {
	   graphPage.clickPracticeQuestionsLink();
	}

	@Then("user should land on graph {string} page")
	public void user_should_land_on_graph_page(String string) {
		String Title=graphPage.pageTitle();
		LogHandler.info("Title of the current page is : " + Title);
		Assert.assertEquals(Title, "Practice Questions", "Title not matched");
	}

	@Given("The user is on Graph page")
	public void the_user_is_on_graph_page() {
		graphPage.clickGraph();
	}

	@When("The user clicks on Sign out button on Graph Page")
	public void the_user_clicks_on_sign_out_button_on_graph_page() {
		graphPage.clickSignOut();
	}

	@Then("user should navigate back to Home page from Graph and can view {string} message")
	public void user_should_navigate_back_to_home_page_from_graph_and_can_view_message(String expectedMsg) {
		String actualMsg=graphPage.logOutMessage();
    	Assert.assertEquals(actualMsg, expectedMsg);
    	LogHandler.info(graphPage.pageTitle());
	}
	
}
