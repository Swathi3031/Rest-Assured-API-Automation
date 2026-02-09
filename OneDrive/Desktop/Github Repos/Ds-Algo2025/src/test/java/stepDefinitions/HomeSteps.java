package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePF;
import pageObject.LinkedListPF;
import pageObject.LoginPF;
import driverFactory.DriverFactory;
import dsUtilities.LogHandler;

public class HomeSteps {

    WebDriver driver = DriverFactory.getDriver();
    HomePF homePage = new HomePF(driver);
     LoginPF lp= new LoginPF(driver);
   

    @Given("The user opens DS Algo portal link")
    public void the_user_opens_ds_algo_portal_link() {
    	homePage.getBaseUrl();
    }

    @When("The user clicks the {string} button")
    public void the_user_clicks_the_get_started_button(String buttonText) {
        homePage.clickGetStartedButton();
    }

    @Then("The user should be redirected to homepage")
    public void the_user_should_be_redirected_to_homepage() {
    	String Title=homePage.pageTitle();
    	LogHandler.info("Title of the current page is : " + Title);
    	Assert.assertEquals(Title, "NumpyNinja", "Title not matched");
    }

    @Given("The user is on Home page")
    public void the_user_is_on_home_page() {
    	String Title=homePage.pageTitle();
    	LogHandler.info("Title of the current page is : " + Title);
    	Assert.assertEquals(Title, "NumpyNinja", "Title not matched");
    }

    @When("The user clicks on Get Started link on homepage {string} without login")
    public void the_user_clicks_get_started_link_without_login(String option) {
        homePage.clickGetStarted(option);
    }

    @Then("The user get warning message {string}")
    public void the_user_get_warning_message(String expectedMessage) {
        String actualMessage = homePage.getWarningMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Warning message mismatch");
    }
    
    @Given("The user is logged in and on the Home page")
    public void the_user_is_logged_in_and_on_the_home_page() {
    	
      	lp.loginBackgroundForPages();
    }

    @When("The user clicks on dropdown {string}")
    public void the_user_clicks_on_dropdown(String option) {
        homePage.clickDropdownOption(option);
    }

    @When("The user clicks on Get Started link on homepage {string} with login")
    public void the_user_clicks_on_get_started_link_with_login(String option) {
        homePage.clickGetStarted(option);    
    }

    @Then("The user land on correponding pages {string}")
    public void the_user_land_on_corresponding_pages(String option) {
        Assert.assertTrue(homePage.isOnCorrectModulePage(option), "Not navigated to " + option);
    }

    @When("The user clicks on signin link")
    public void the_user_clicks_on_signin_link() {
         homePage.clickSignIn();
    }

    @Then("The user redirected to login page")
    public void the_user_redirected_to_login_page() {
    	String Title=homePage.pageTitle();
    	LogHandler.info("Title of the current page is : " + Title);
    	Assert.assertEquals(Title, "Login", "Title not matched");
    }

    @When("The user clicks on register link")
    public void the_user_clicks_on_register_link() {
         homePage.clickRegister();
    }

    @Then("The user redirected to Registration page")
    public void the_user_redirected_to_registration_page() {
    	String Title=homePage.pageTitle();
    	LogHandler.info("Title of the current page is : " + Title);
    	Assert.assertEquals(Title, "Registration", "Title not matched");
    }
    
    @When("The user clicks on Sign out button on Home page")
    public void the_user_clicks_on_sign_out_button_on_home_page() {
    	homePage.clickSignOut();
    }

    @Then("user should able signout on Home page and can view {string} message")
    public void user_should_able_signout_on_home_page_and_can_view_message(String expectedMsg) {
    	String actualMsg=homePage.logOutMessage();
    	Assert.assertEquals(actualMsg, expectedMsg);

    }
    
}
