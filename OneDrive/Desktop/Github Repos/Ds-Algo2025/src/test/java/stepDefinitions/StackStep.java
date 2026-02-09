
package stepDefinitions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.DriverFactory;
import dsUtilities.ConfigReader;
import dsUtilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.LoginPF;
import pageObject.StackPage;

public class StackStep {
	
	WebDriver driver = DriverFactory.getDriver();
	 StackPage stackpage = new StackPage(driver);
	 LoginPF lp= new LoginPF(driver);
	 private static final Logger logger = LogManager.getLogger(StackStep.class);
	 String path=ConfigReader.getProperty("practice_questions_excelPath");
	 ExcelReader reader= new ExcelReader(path);
	 

@Given("The User is in the home page after sign in")
public void the_user_is_in_the_home_page_after_sign_in() {
	lp.loginBackgroundForPages();

 }

@When("The user scrolls down to the Data Structures dropdown and selects Stack")
public void the_user_scrolls_down_to_the_data_structures_dropdown_and_selects_stack() {
	stackpage.scrollAndClickDataStructuresDropdown();
     stackpage.selectstackFromDropdown();
 }

@Then("User is navigated to Stack Page")
public void user_is_navigated_to_stack_page() {
    Assert.assertTrue(stackpage.isStackPageDisplayed(), "Stack page is not displayed.");
    logger.info("User navigated to Stack Page");
}

@Given("The user is in the Stack Page")
public void the_user_is_in_the_stack_page() {
    stackpage.navigateToStackPage();
    logger.info("User navigated to Stack Page");
}

@When("The user clicks Operations in stack button")
public void the_user_clicks_operations_in_stack_button() {
    stackpage.clickOperationsInStack();
}

@Then("The user should be redirected to Operations in stack page")
public void the_user_should_be_redirected_to_operations_in_stack_page() {
    String pageTitle = DriverFactory.getDriver().getTitle();
    System.out.println("Actual Page Title: " + pageTitle); 
    Assert.assertTrue(
        pageTitle.toLowerCase().contains("operations in stack"),
        "User is not on Operations in Stack page. Actual title: " + pageTitle
    );
}
@Given("The user is on the Operations in Stack page")
public void the_user_is_on_the_operations_in_stack_page() {
    stackpage.navigateToOperationsinStackPage();
    logger.info("User navigated to Operations in Stack Page");
}

@When("The user scrolls down and clicks Try Here button in Operations in Stack page")
public void the_user_scrolls_down_and_clicks_try_here_button_in_operations_in_stack_page() {
    stackpage.scrollToTryHere();     
    stackpage.clickTryhere();        
}

@Then("The user is redirected to a page having a Stacks try Editor with a Run button to test")
public void the_user_is_redirected_to_a_page_having_a_stacks_try_editor_with_a_run_button_to_test() {
    Assert.assertTrue(stackpage.isRunButtonVisible(), "Run button not visible");
    driver.navigate().back();
}

@Given("The user is in the Stacks tryEditor page")
public void the_user_is_in_the_stacks_try_editor_page() {
    stackpage.navigateToStackstryeditorPage();
    logger.info("User navigated to Stacks tryEditor Page");
}

@When("The user clicks Run button without entering the code in the stacks tryEditor")
public void the_user_clicks_run_button_without_entering_the_code_in_the_stacks_try_editor() {
    stackpage.clickRunButton();
}

@Then("The user is able to see an error message in alert window")
public void the_user_is_able_to_see_an_error_message_in_alert_window() {
    try {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String alertText = alert.getText();

        Assert.assertTrue(
            alertText.toLowerCase().contains("error message"),
            "Expected error message in alert, but got: " + alertText
        );

        alert.accept();
    } catch (NoAlertPresentException e) {
        System.out.println("WARN: Bug - No alert shown for empty code. Expected an error message.");
        Assert.fail("Bug: No alert displayed when clicking Run without entering code.");
    }
}

@When("The user writes the invalid code from row number in stacks tryEditor and clicks the Run Button")
public void the_user_writes_the_invalid_code_from_row_number_in_stacks_try_editor_and_clicks_the_run_button() {
    new WebDriverWait(driver, Duration.ofSeconds(10));

    int rowNum = 1; 
    String rawCode = reader.readAllRows("Pythoncode").get(rowNum).get("Code");
    String code = rawCode.replace("\\n", "\n");

    JavascriptExecutor js = (JavascriptExecutor) driver; 
    js.executeScript("window.editor.setValue(arguments[0]);", code);
    stackpage.clickRunButton();
}

@Then("The user is able to see an nameerror message in alert window")
public void the_user_is_able_to_see_an_nameerror_message_in_alert_window() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    String alertText = alert.getText();

    Assert.assertTrue(
        alertText.toLowerCase().contains("nameerror"),
        "Expected alert to contain 'NameError', but got: " + alertText
    );
    alert.accept();
}

@When("The user writes the valid code from row number in stacks tryEditor and clicks the Run Button")
public void the_user_writes_the_valid_code_from_row_number_in_stacks_try_editor_and_clicks_the_run_button() {
    int rowNum = 0; 
    String rawCode = reader.readAllRows("Pythoncode").get(rowNum).get("Code");
    String code = rawCode.replace("\\n", "\n");

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.editor.setValue(arguments[0]);", code);
    stackpage.clickRunButton();
}

@Then("The user is able to see output in the console")
public void the_user_is_able_to_see_output_in_the_console() {
    try {
        String output = stackpage.getEditorOutput();  

        Assert.assertNotNull(output, "Output is null");  
        Assert.assertFalse(output.trim().isEmpty(), "Output is empty");  

        System.out.println("Output in console: " + output);  // print to console

    } catch (Exception e) {
        Assert.fail("Something went wrong while getting the output: " + e.getMessage());
    }
}

@When("The user clicks Practice Question button in stack")
public void the_user_clicks_practice_question_button_in_stack() {
    stackpage.clickPracticeQuestions();
}

@Then("The user should be redirected to Practice page in stack")
public void the_user_should_be_redirected_to_practice_page_in_stack() {
    String pageTitle = DriverFactory.getDriver().getTitle();
    System.out.println("Current page title: " + pageTitle);
    if (!pageTitle.contains("Practice")) {
        Assert.fail("BUG: The user is not on the Stack Practice page. Page title is: " + pageTitle);
    }
}

@When("The user clicks Implementation button")
public void the_user_clicks_implementation_button() {
    stackpage.clickImplementation();
}

@Then("The user should be redirected to Implementation page")
public void the_user_should_be_redirected_to_implementation_page() {
    String pageTitle = DriverFactory.getDriver().getTitle();
    Assert.assertTrue(pageTitle.contains("Implementation"), "User is not on Implementation page");
}

@Given("The user is on the Implementation page")
public void the_user_is_on_the_implementation_page() {
    stackpage.navigateToImplementationPage();
    logger.info("User navigated to Implementation Page");
}

@When("The user clicks Try Here button in Implementation page")
public void the_user_clicks_try_here_button_in_implementation_page() {
    stackpage.clickTryhere(); 
}

@When("The user clicks Application button")
public void the_user_clicks_application_button() {
    stackpage.clickApplications();
}

@Then("The user should be redirected to Application page")
public void the_user_should_be_redirected_to_application_page() {
    String pageTitle = DriverFactory.getDriver().getTitle();
    Assert.assertTrue(pageTitle.contains("Applications"), "User is not on Applications page");
}

@Given("The user is on the Application page")
public void the_user_is_on_the_application_page() {
    stackpage.navigateToApplicationsPage();
    logger.info("User navigated to Application Page");
}

@When("The user clicks Try Here button in Application page")
public void the_user_clicks_try_here_button_in_application_page() {
    stackpage.clickTryhere(); 
}

@When("The user clicks Practice Questionb button in stack")
public void the_user_clicks_practice_questionb_button_in_stack() {
    stackpage.clickPracticeQuestions();
}
}