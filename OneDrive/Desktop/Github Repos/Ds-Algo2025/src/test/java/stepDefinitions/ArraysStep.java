package stepDefinitions;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import driverFactory.DriverFactory;
import dsUtilities.ConfigReader;
import dsUtilities.ExcelReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.ArraysPage;
import pageObject.LoginPF;



public class ArraysStep {
	
	WebDriver driver = DriverFactory.getDriver();
	 ArraysPage arraysPage = new ArraysPage(driver);
	 LoginPF lp= new LoginPF(driver);
	 private static final Logger logger = LogManager.getLogger(ArraysStep.class);
	 String path=ConfigReader.getProperty("practice_questions_excelPath");
	 ExcelReader excelreader = new ExcelReader(path);
	
	 @Given("The User is in home page after sign in")
	 public void the_user_is_in_home_page_after_sign_in() {
		 lp.loginBackgroundForPages();
	 }


	 @When("The user scrolls down to the Data Structures dropdown and selects Array")
	 public void the_user_scrolls_down_to_the_data_structures_dropdown_and_selects_array() {
		 arraysPage.scrollAndClickDataStructuresDropdown();
	     arraysPage.selectArrayFromDropdown();
	 }

	 @Then("User is navigated to Array Page")
	 public void user_is_navigated_to_array_page() {
	     Assert.assertTrue(arraysPage.isArrayPageDisplayed(), "Array page is not displayed");
	 }

	 @Given("The user is in the Array page")
	 public void the_user_is_in_the_array_page() {
	     arraysPage.navigateToArraysPage();
	     logger.info("Navigated to: Arrays page");
	 }
	 @When("The user clicks Arrays in Python button")
	 public void the_user_clicks_arrays_in_python_button() {
	     arraysPage.clickArraysInPython();
	 }

	 @Then("The user should be redirected to Arrays in Python page")
	 public void the_user_should_be_redirected_to_arrays_in_python_page() {
	     String pageTitle = DriverFactory.getDriver().getTitle();
	     Assert.assertTrue(pageTitle.contains("Arrays in Python"), "User is not on Arrays in Python page");
	 }

	 @Given("The user is on the Arrays in Python page")
	 public void the_user_is_on_the_arrays_in_python_page() {
	     arraysPage.navigateToArraysInPythonPage();
	     logger.info("Navigated to: Arrays in Python page");
	 }

	 @When("The user scrolls down and clicks Try Here button in Arrays in Python page")
	 public void the_user_scrolls_down_and_clicks_try_here_button_in_arrays_in_python_page() {
	     arraysPage.scrollToTryHere();     
	     arraysPage.clickTryHereButton();       
	 }

	 @Then("The user IS redirected to a page having an try Editor with a Run button to test")
	 public void the_user_is_redirected_to_a_page_having_an_try_editor_with_a_run_button_to_test() {
	     Assert.assertTrue(arraysPage.isRunButtonVisible(), "Run button not visible");
	     driver.navigate().back();
	 }
	 
	 @Given("The user is on the Arrays tryEditor page")
	 public void the_user_is_on_the_arrays_try_editor_page() {
	     arraysPage.navigateToTryEditorPage();
	     logger.info("Navigated to: Arrays tryEditor page");
	 }

	 @When("The user clicks on the Run button without entering the code in the Editor")
	 public void the_user_clicks_on_the_run_button_without_entering_the_code_in_the_editor() {
	     arraysPage.clickRunButton();
	 }

	 @Then("The user should see an error message in alert window")
	 public void the_user_should_see_an_error_message_in_alert_window() {
	     try {
	         Alert alert = DriverFactory.getDriver().switchTo().alert();
	         String alertText = alert.getText();

	         Assert.assertTrue(alertText.toLowerCase().contains("error message"),
	                 "Expected error message in alert, but got: " + alertText);

	         alert.accept();
	     } catch (NoAlertPresentException e) {
	         System.out.println("WARN: Bug - No alert shown for empty code. Expected an error message.");
	         Assert.fail("Bug: No alert displayed when clicking Run without entering code.");
	     }
	 }

	 @When("The user gives the invalid code from row number in Editor and click the Run Button")
	 public void the_user_gives_the_invalid_code_from_row_number_in_editor_and_click_the_run_button() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(arraysPage.getRunButton()));
	     int rowNum = 1; 
	     String rawCode = excelreader.readAllRows("Pythoncode").get(rowNum).get("Code");
	     String code = rawCode.replace("\\n", "\n");

	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.editor.setValue(arguments[0]);", code);

	     arraysPage.clickRunButton();
	 }

	 @Then("The user should see an NameError message in alert window")
	 public void the_user_should_see_an_name_error_message_in_alert_window() {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	     String alertText = alert.getText();
	     Assert.assertTrue(alertText.toLowerCase().contains("nameerror"),
	             "Expected alert to contain 'NameError', but got: " + alertText);

	     alert.accept();
	 }

	 @When("The user gives the valid code from row number in Editor and click the Run Button")
	 public void the_user_gives_the_valid_code_from_row_number_in_editor_and_click_the_run_button() {
	     int rowNum = 0; 
	     String rawCode = excelreader.readAllRows("Pythoncode").get(rowNum).get("Code");
	     String code = rawCode.replace("\\n", "\n");

	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.editor.setValue(arguments[0]);", code);

	     arraysPage.clickRunButton();
	 }

	 @Then("The user should see output in the console")
	 public void the_user_should_see_output_in_the_console() {
	     try {
	         String output = arraysPage.getEditorOutput();
	         Assert.assertNotNull(output, "Output is null");
	         Assert.assertFalse(output.trim().isEmpty(), "Output is empty");

	         System.out.println("Output in console: " + output);
	     } catch (Exception e) {
	         Assert.fail("Something went wrong while getting the output: " + e.getMessage());
	     }
	 }


	 @When("The user clicks Practice Questions button")
	 public void the_user_clicks_practice_questions_button() {
	     arraysPage.clickPracticeQuestions();
	 }

	 @Then("The user is redirected to Practice page")
	 public void the_user_is_redirected_to_practice_page() {
	     Assert.assertTrue(arraysPage.isSearchTheArrayLinkVisible(), "Search the Array link is not visible on Practice page");
	 }

	 @Given("The users is on the Arrays Practice Question page")
	 public void the_users_is_on_the_arrays_practice_question_page() {
	     arraysPage.navigateToArraysPracticeQuestions();
	     logger.info("Navigated to: Arrays Practice Question page");
	 }


	 @When("The user clicks {string} link in the arrays question page")
	 public void the_user_clicks_link_in_the_arrays_question_page(String questionLink) {
	     arraysPage.navigateToQuestionEditor(questionLink);
	 }

	 @Then("The user should be redirected to Question page which has a question, and try Editor with Run and Submit buttons")
	 public void the_user_should_be_redirected_to_question_page_which_has_a_question_and_try_editor_with_run_and_submit_buttons() {
	     Assert.assertTrue(arraysPage.isRunButton1Visible(), "Run button is not visible");
	     Assert.assertTrue(arraysPage.isSubmitButtonVisible(), "Submit button is not visible");
	     Assert.assertTrue(arraysPage.isQuestionDisplayed(), "Question is not displayed");
	 }

	 @Given("The user is on the Search the array practice question editor")
	 public void the_user_is_on_the_search_the_array_practice_question_editor() {
	     arraysPage.navigateToSearchthearray();
	 }

	 @Given("The user is on the Max Consecutive Ones practice question editor")
	 public void the_user_is_on_the_max_consecutive_ones_practice_question_editor() {
	     arraysPage.navigateToMaxConsecutiveOnes();
	 }

	 @Given("The user is on the Find Numbers practice question editor")
	 public void the_user_is_on_the_find_numbers_practice_question_editor() {
	     arraysPage.navigateToFindnumberswithevennumberofdigits();
	 }

	 @Given("The user is on the Squares of a Sorted Array practice question editor")
	 public void the_user_is_on_the_squares_of_a_sorted_array_practice_question_editor() {
	     arraysPage.navigateToSquaresofasortedArray();
	 }

	 @When("The user writes invalid code from row number and clicks the Run Button Search the array")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_run_button_Search_the_array() {
	     String raw = excelreader.readAllRows("Pythoncode").get(3).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @Then("The user must see an error message in the alert window")
	 public void the_user_must_see_an_error_message_in_the_alert_window() {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	     Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	     String alertText = alert.getText();

	     Assert.assertTrue(
	         alertText.toLowerCase().contains("error"),
	         "Expected alert to contain (SyntaxError/NameError), but got: " + alertText
	     );

	     arraysPage.acceptAlert();
	 }
	 @When("The user writes valid code from row number and clicks the Run Button Search the array")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_run_button_Search_the_array() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(2).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @Then("The user must see output in the console")
	 public void the_user_must_see_output_in_the_console() {
	     String output = arraysPage.getConsoleOutput();
	     Assert.assertNotNull(output, "Console output is null");
	     Assert.assertFalse(output.trim().isEmpty(), "Expected output in console, but found none");
	 }

	 @When("The user writes invalid code from row number and clicks the Submit Button Search the array")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_submit_button_Search_the_array() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(3).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }

	 @Then("The user gets error message Error occurred during submission in the console")
	 public void the_user_gets_error_message_error_occurred_during_submission_in_the_console() {
	     String output = arraysPage.getConsoleOutput();
	     System.out.println("Console output: '" + output + "'");
	     Assert.assertNotNull(output, "Console output is null");
	     Assert.assertFalse(output.trim().isEmpty(), "Console output is empty");
	     Assert.assertTrue(output.contains("Error occurred"), 
	         "BUG: Expected message 'Error occurred' but got: '" + output + "'");
	 }

	 @When("The user writes valid code from row number and clicks the Submit Button Search the array")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_submit_button_Search_the_array() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(2).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }

	 @Then("The user gets success message Submission successful in the console")
	 public void the_user_gets_success_message_submission_successful_in_the_console() {
	     String output = arraysPage.getConsoleOutput();
	     System.out.println("Console output: '" + output + "'");
	     Assert.assertNotNull(output, "Console output is null");
	     Assert.assertFalse(output.trim().isEmpty(), "Console output is empty");
	     Assert.assertTrue(output.contains("Submission Successful"),
	         "BUG: Expected message 'Submission Successful' but got: '" + output + "'");
	 }

	 // Max Consecutive Ones
	 @When("The user writes invalid code from row number and clicks the Run Button Max Consecutive Ones")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_run_button_max_consecutive_ones() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(5).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @When("The user writes valid code from row number and clicks the Run Button Max Consecutive Ones")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_run_button_max_consecutive_ones() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(4).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @When("The user writes invalid code from row number and clicks the Submit Button Max Consecutive Ones")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_submit_button_max_consecutive_ones() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(5).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }

	 @When("The user writes valid code from row number and clicks the Submit Button Max Consecutive Ones")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_submit_button_max_consecutive_ones() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(4).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }

	 
	 @When("The user writes invalid code from row number and clicks the Run Button Find Numbers")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_run_button_find_numbers() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(7).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @When("The user writes valid code from row number and clicks the Run Button Find Numbers")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_run_button_find_numbers() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(6).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @When("The user writes invalid code from row number and clicks the Submit Button Find Numbers")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_submit_button_find_numbers() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(7).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }

	 @When("The user writes valid code from row number and clicks the Submit Button Find Numbers")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_submit_button_find_numbers() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(6).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }

	
	 @When("The user writes invalid code from row number and clicks the Run Button Sorted Array")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_run_button_sorted_array() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(9).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @When("The user writes valid code from row number and clicks the Run Button Sorted Array")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_run_button_sorted_array() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(8).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickRun();
	 }

	 @When("The user writes invalid code from row number and clicks the Submit Button Sorted Array")
	 public void the_user_writes_invalid_code_from_row_number_and_clicks_the_submit_button_sorted_array() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(9).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }

	 @When("The user writes valid code from row number and clicks the Submit Button Sorted Array")
	 public void the_user_writes_valid_code_from_row_number_and_clicks_the_submit_button_sorted_array() {
	     ExcelReader excelreader = new ExcelReader("src/test/resources/testdata/DsAlgo_PracticeuestionCode.xlsx");
	     String raw = excelreader.readAllRows("Pythoncode").get(8).get("Code");
	     String code = raw.replace("\\n", "\n");
	     arraysPage.enterpythonCode(code);
	     arraysPage.clickSubmit();
	 }
	 
	 

	 @When("The user clicks Arrays using List button")
	 public void the_user_clicks_arrays_using_list_button() {
	     arraysPage.clickArraysUsingList();
	 }
	 
	 @Then("The user should be redirected to Arrays using List page")
	 public void the_user_should_be_redirected_to_arrays_using_list_page() {
			 String pageTitle = DriverFactory.getDriver().getTitle();
		     Assert.assertTrue(pageTitle.contains("Arrays Using List"));
	 }
	 
	 @Given("The user is on the Arrays using List page")
	 public void the_user_is_on_the_arrays_using_list_page() {
	     arraysPage.navigateToArraysUsingListPage();
	     logger.info("Navigated to: Arrays using List page");
	 }
	 
	 @When("The user scrolls down and clicks Try Here button in Arrays Using List page")
	 public void the_user_scrolls_down_and_clicks_try_here_button_in_arrays_using_list_page() {
	     try {
	         arraysPage.scrollToTryHere(); 
	         arraysPage.clickTryHereButton();   
	     } catch (Exception e) {
	         System.out.println("Error while trying to scroll and click Try Here: " + e.getMessage());
	         e.printStackTrace();  
	     }
	 }


	 @When("The user clicks Basic Operations in List button")
	 public void the_user_clicks_basic_operations_in_list_button() {
	     arraysPage.clickBasicOperationsInList();
	 }

	 @Then("The user should be redirected to Basic Operations in List page")
	 public void the_user_should_be_redirected_to_basic_operations_in_list_page() {
	     String pageTitle = DriverFactory.getDriver().getTitle();
	     Assert.assertTrue(pageTitle.contains("Basic Operations in List"));
	 }

	 @Given("The user is on the Basic Operations in List page")
	 public void the_user_is_on_the_basic_operations_in_list_page() {
	     arraysPage.navigateToBasicOperationsInListPage();
	     logger.info("Navigated to: Basic Operations in List page");
	 }

	 @When("The user scrolls down and clicks Try Here button in Basic Operations in List page")
	 public void the_user_scrolls_down_and_clicks_try_here_button_in_basic_operations_in_list_page() {
	     arraysPage.scrollToTryHere();     
	     arraysPage.clickTryHereButton();       
	 }

	 
	 @When("The user clicks Applications of Array button")
	 public void the_user_clicks_applications_of_array_button() {
	     arraysPage.clickApplicationsOfArray();
	 }

	 @Then("The user should be redirected to Applications of Array page")
	 public void the_user_should_be_redirected_to_applications_of_array_page() {
	     String pageTitle = DriverFactory.getDriver().getTitle();
	     Assert.assertTrue(pageTitle.contains("Applications of Array"));
	 }

	 @Given("The user is on the Applications of Array page")
	 public void the_user_is_on_the_applications_of_array_page() {
	     arraysPage.navigateToApplicationsOfArrayPage();
	     logger.info("Navigated to: Applications of Array page");
	 }

	 @When("The user clicks Try Here button in Applications of Array page")
	 public void the_user_clicks_try_here_button_in_applications_of_array_page() {
	     arraysPage.clickTryHereButton();        // Click the link
	 }

	 
	 
	 }

	 