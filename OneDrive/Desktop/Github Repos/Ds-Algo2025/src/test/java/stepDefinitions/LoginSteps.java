package stepDefinitions;




import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import pageObject.LoginPF;
import dsalgoHooks.Hooks;
import dsUtilities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class LoginSteps {


	WebDriver driver=DriverFactory.getDriver();
	LoginPF loginobj = new LoginPF(driver);
	ExcelReader reader;
	String username = null;
	String password = null;
	List<Map<String, String>> allRowsData;
		
	@Given("User is on home page")
	public void user_is_on_home_page() {
	     LogHandler.info(driver.getTitle());
	}

	@When("User clicks on sign in button")
	public void user_clicks_on_sign_in_button() {
		loginobj.click_homesignin();
	}

	@Then("User should land on {string} login page")
	public void user_should_land_on_login_page(String expected_Title) {
		loginobj.page_title();
	}
	@Given("The user is on the login Page")
	public void the_user_is_on_the_login_page() {
	    LogHandler.info("User is on login page");
	   }

	@Then("The user should see all header elements")
	public void the_user_should_see_all_header_elements() {
		Assert.assertTrue(loginobj.isNumpyNinjaVisible());
		Assert.assertTrue(loginobj.isDropdownVisible());
		Assert.assertTrue(loginobj.isRegisterVisible());
		Assert.assertTrue(loginobj.isSignin());
	}
	
	@When("The user leave the username field empty and click login")
	public void the_user_leave_the_username_field_empty_and_click_login() {
		loginobj.clickLoginwithoutusername();
		
	}

	@Then("The User should see {string}")
	public void the_user_should_see(String expected_message) {
		
		//Assert.assertTrue("Expected the username field to be empty", invalid);
		String actual_message=loginobj.isUsernameFieldEmpty();
		Assert.assertEquals(actual_message, expected_message);
		 LogHandler.info(actual_message);
		
	}
	@When("The user leave the password field empty and click login")
	public void the_user_leave_the_password_field_empty_and_click_login() {
		loginobj.clickLoginwithoutpassword();
	}


	@Then("The user must see {string}")
	public void the_user_must_see(String expected_message1)
	{
		String actual_message1=loginobj.isPasswordFieldEmpty();
		
		Assert.assertEquals(actual_message1, expected_message1);
		 LogHandler.info(actual_message1);
	}
	@When("The user gets invalid data from excel sheet {string} and {string} for the login page")
	public void the_user_gets_invalid_data_from_excel_sheet_and_for_the_login_page(String sheetname, String Usecase) {
		reader = new ExcelReader("src/test/resources/TestData/Ds-AlgoTestData.xlsx");
		allRowsData = reader.readAllRows(sheetname);

		for (Map<String, String> row : allRowsData) {
			if (row.get("Scenario").equalsIgnoreCase(Usecase)) {
				username = row.get("Username");
				password = row.get("Password");
				break;
			}
		}

		loginobj.login_user(username);
		loginobj.login_passowrd(password);
		loginobj.login_button();
	}

	@Then("The user should get the error message {string}")
	public void the_user_should_get_the_error_message_invalid_username_and_password(String ExpectedLogedOutMessage) {
		
			String ActualinvalidMessage = loginobj.getInvalidLoginMessage();
			Assert.assertEquals(ActualinvalidMessage.trim(), ExpectedLogedOutMessage);
	}

	@When("The user gets valid data  {string} and {string} for the login page")
	public void the_user_gets_valid_data_and_for_the_login_page(String Sheetname, String Usecase) {
		reader = new ExcelReader("src/test/resources/TestData/Ds-AlgoTestData.xlsx");
		allRowsData = reader.readAllRows(Sheetname);
		
		for (Map<String, String> row : allRowsData) {
			if (row.get("Scenario").equalsIgnoreCase(Usecase)) {
				System.out.println("Inside map loop");
				username = row.get("Username");
				password = row.get("Password");
				break;
			}
		}

		
		loginobj.login_user(username);
		loginobj.login_passowrd(password);
		loginobj.login_button();
	}
	
	@Then("The user is in the home page with message {string}")
	public void the_user_is_in_the_home_page(String expected_message) {
		String ActualLogedinMessage = loginobj.getLoginMessage();
		Assert.assertEquals(ActualLogedinMessage, expected_message);
	}



}







	




