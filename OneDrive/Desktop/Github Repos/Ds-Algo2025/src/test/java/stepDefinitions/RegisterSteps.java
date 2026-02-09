package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverFactory.DriverFactory;
import dsUtilities.ExcelReader;
import dsUtilities.LogHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.DataStructuresPF;
import pageObject.RegisterPF;

public class RegisterSteps {
	WebDriver driver=DriverFactory.getDriver();
	RegisterPF rp=new RegisterPF(driver);
	ExcelReader reader=new ExcelReader("src/test/resources/TestData/PythonCode.xlsx");
	@Given("user is on the homepage")
	public void user_is_on_the_homepage() {
		 LogHandler.info("user is on: " +driver.getTitle() +"page");
	}

	@When("user clicks on register link")
	public void user_clicks_on_register_link() {
		rp.clickOnRegisterLink();
		
	}

	@Then("user should land on the register page")
	public void user_should_land_on_the_register_page() {
		String actualTitle=rp.getTitleOfPage();
		String expectedTitle="Registration";
		Assert.assertEquals(actualTitle, expectedTitle);
		 LogHandler.info("if test is passed then user is on register page");
	}


	@Given("user is on rgister page")
	public void user_is_on_rgister_page() {
		 LogHandler.info("user is on: " +driver.getTitle() +"page");
	}

	@Then("user can see NumpyNinja logo,datastructures dropdown,siginin and register links")
	public void user_can_see_numpy_ninja_logo_datastructures_dropdown_siginin_and_register_links() {
		Assert.assertTrue(rp.headerElementsValidation());
	}
	@When("user did not enter data to username and password and confirmPwd fields and click on register button")
	public void user_did_not_enter_data_to_username_and_password_and_confirm_pwd_fields_and_click_on_register_button() {
		rp.clickOnregisterBtn();
			
	}

	@Then("user should see error message in alert window {string}")
	public void user_should_see_error_message_in_alert_window(String expectedMsg) {
		
		String actualMsg=rp.alertMessageForFirstInvalidField();
		
		Assert.assertEquals(actualMsg, expectedMsg);
		
		//logger.info("alert text: "+actualMsg);
		
	}
	@When("user gets data from {string} and scenario type {int} and clicks on register")
	public void user_gets_data_from_and_scenario_type_and_clicks_on_register(String sheetname, Integer rownumber) {
		List<Map<String, String>> allRowsData1= reader.readAllRows(sheetname);
		String uname=allRowsData1.get(rownumber).get("username");
		String pass=allRowsData1.get(rownumber).get("password");
		String confirmpass=allRowsData1.get(rownumber).get("confirmpassword");
		rp.sendUserDetails(uname, pass, confirmpass);
		rp.clickOnregisterBtn();
		
		
		
	}

	@Then("user should see eroor {string} message")
	public void user_should_see_eroor_message(String expectedMsg) {
		String actualMsg=rp.getErrorMsg();
		Assert.assertEquals(actualMsg, expectedMsg);
	}

	@When("user gets data from {string} and scenario type {string} and clicks on register")
	public void user_gets_data_from_and_scenario_type_and_clicks_on_register(String sheetname, String scenarioName) {
		List<Map<String, String>> allRowsData1= reader.readAllRows(sheetname);
		for(Map<String,String> data:allRowsData1) {
			if (data.get("scenarioType").equalsIgnoreCase(scenarioName)) {
				String uname=data.get("username");
				String password=data.get("password");
				String confirmpassword=data.get("confirmpassword");
				rp.sendUserDetails(uname, password, confirmpassword);
				rp.clickOnregisterBtn();
		
			}
				}
		
			}
	

	@Then("user should land on home page with title {string} and see the message {string}")
	public void user_should_land_on_home_page_with_title_and_see_the_message(String expectedTitle, String expectedMsg) {
		String actualTitle=rp.getPageTitle();
		String actualMsg=rp.getErrorMsg();
		Assert.assertTrue(actualMsg.contains(expectedMsg));
		 LogHandler.info("user registered sucessfully" + actualTitle);
		
	}
	
	@When("user clicks on login link")
	public void user_clicks_on_login_link() {
		
		rp.clickOnLogin();
	}

	@Then("user should land on the {string} page")
	public void user_should_land_on_the_page(String expectedTitle) {
		String actualTitle=rp.getTitleOfPage();
		Assert.assertEquals(actualTitle, expectedTitle);
		 LogHandler.info("user is on : " +actualTitle);
	}



}
