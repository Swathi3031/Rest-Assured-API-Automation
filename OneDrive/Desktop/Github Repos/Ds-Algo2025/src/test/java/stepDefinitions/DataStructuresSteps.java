package stepDefinitions;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.DataStructuresPF;
import pageObject.LoginPF;
import dsUtilities.*;

public class DataStructuresSteps {

	
	WebDriver driver=DriverFactory.getDriver();
	DataStructuresPF ds=new DataStructuresPF(driver);
	LoginPF lp= new LoginPF(driver);
	ExcelUtilityHelper e=new ExcelUtilityHelper();
	
	@Given("user is sigin to app")
	public void user_is_sigin_to_app() {
		
		lp.loginBackgroundForPages();
		
	}

	@When("user cliks on DtastructureInro GetStarted button")
	public void user_cliks_on_dtastructure_inro_get_started_button() {
		ds.getStartbtnclick();
		 LogHandler.info("user is on data structure page:" +ds.pageTitle());
		
	}

	@Then("user lands on data-structures-introduction page")
	public void user_lands_on_data_structures_introduction_page() {
		String actualTitle=ds.pageTitle();
		String expectedTitle="Data Structures-Introduction";
		Assert.assertEquals(actualTitle, expectedTitle);
	}


	@Given("user is on data-structures-introduction page")
	public void user_is_on_data_structures_introduction_page() {
		 LogHandler.info(ds.pageTitle());
	}

	@Then("user  able to see NumpyNinja,Data structures dropdown,username and signout links")
	public void user_able_to_see_numpy_ninja_data_structures_dropdown_username_and_signout_links() {
		Assert.assertTrue(ds.isNumpyNinjaVisible());
	    Assert.assertTrue(ds.isDropdownVisible());
		Assert.assertTrue(ds.isUsernameVisible());
		Assert.assertTrue(ds.isSignOutVisible());
	}


@When("user ckicks on Time complexity link")
public void user_ckicks_on_time_complexity_link() {
	
	ds.timeComplexity();
}

@Then("user lands on {string} page")
public void user_lands_on_page(String expectedTitle) {
	Assert.assertEquals(ds.pageTitle(), expectedTitle);
	 LogHandler.info(ds.pageTitle());
	
	
}
	
	
@Given("user is on Time complexity page")
public void user_is_on_time_complexity_page() {
	ds.timeComplexity();
	 LogHandler.info(ds.pageTitle());
	
	
}

@When("user clicks on Practice Questions link")
public void user_clicks_on_practice_questions_link() {
	
	ds.clickOnPracticeQuest();
}

@Then("user should land on {string} page")
public void user_should_land_on_page(String expectedText) {
	
	Assert.assertTrue(ds.getPageURL().contains(expectedText));
	 LogHandler.info(ds.pageTitle());
}

@When("user clicks on try here link")
public void user_clicks_on_try_here_link() {
	ds.clickOnTryHere();
	
}

@Then("user should land on {string} page with  Run button")
public void user_should_land_on_page_with_run_button(String expectedText) {
	Assert.assertTrue(ds.getPageURL().contains(expectedText));
	 LogHandler.info(ds.pageTitle());
	Assert.assertTrue(ds.runButtonDisplayed());
	
}
@Given("user is on try Editor page")
public void user_is_on_try_editor_page() {
	ds.timeComplexity();
	ds.clickOnTryHere();
	 LogHandler.info(ds.pageTitle());
}

@When("user clicks on run button without code")
public void user_clicks_on_run_button_without_code() {
	
	ds.clickOnRunBtn();
}

@Then("user should see the error message in alert window")
public void user_should_see_the_error_message_in_alert_window() {
	
	Assert.assertTrue(ds.isAlertIsPresent());
}
@When("user clicks on run button with incorrect code from {string} and {int}")
public void user_clicks_on_run_button_with_incorrect_code_from_and(String sheetname, Integer rownumber)throws InterruptedException, Throwable, IOException {
	
	String codeToEnter=e.getPythonCodeFromExcel(sheetname, rownumber);
	ds.editor(codeToEnter);
    ds.clickOnRunBtn();

}
@Then("user should see the error message in alert window and get the alert text")
public void user_should_see_the_error_message_in_alert_window_and_get_the_alert_text() {
	
	Alert alert = driver.switchTo().alert();
	String text = alert.getText();
	 LogHandler.info(text);
	alert.accept();
}
@When("user types incorrect code from {string} and {int}")
public void user_types_incorrect_code_from_and(String sheetname, Integer rownumber) throws Throwable, IOException {
	String codeToEnter=e.getPythonCodeFromExcel(sheetname, rownumber);
	ds.editor(codeToEnter);
	ds.clickOnRunBtn();
	  
}

@Then("user should see alert window and can not click on run button")
public void user_should_see_alert_window_and_can_not_click_on_run_button() {
	WebDriverWait wait=new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	try {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertNotNull(alert);
        ds.clickOnRunBtn();
        Assert.assertNotNull(driver.switchTo().alert());
        alert.accept();
	}
	catch(Exception e){
		Assert.fail("alert not present");
		
	}
	
}


@When("user writes Python code from {string} and {int}")
public void user_writes_python_code_from_and(String sheetname, Integer rownumber) throws InterruptedException, Throwable, IOException {
	
	String codeToEnter=e.getPythonCodeFromExcel(sheetname, rownumber);
	 ds.editor(codeToEnter);
	  ds.clickOnRunBtn();

}

@Then("output should match with expected result {string}")
public void output_should_match_with_expected_result(String expectedResult) {
	String actualResult=ds.getOutputData();
	Assert.assertEquals(actualResult, expectedResult);
	 LogHandler.info("console result:" +actualResult);
	
	
}

@When("user clicks on browser back button")
public void user_clicks_on_browser_back_button() {
	String title=ds.navigateBack();
	 LogHandler.info(title);
}

@Then("user lands on  the {string} page")
public void user_lands_on_the_page(String expectedText) throws InterruptedException {
	String actualTitle=ds.laningPageValidation();
	Assert.assertEquals(actualTitle, expectedText);
	
	
	
}
@When("user clicks on Signout button")
public void user_clicks_on_signout_button() {
	
	ds.signOut();
}

@Then("user should land on Home page and can view {string} message")
public void user_should_land_on_home_page_and_can_view_message(String ExpectedMsg) {
	String actualMsg=ds.logOutMessage();
	Assert.assertEquals(actualMsg, ExpectedMsg);
	
}

}


