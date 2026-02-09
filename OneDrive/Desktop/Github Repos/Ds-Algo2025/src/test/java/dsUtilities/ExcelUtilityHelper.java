package dsUtilities;

import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.DriverFactory;
import dsUtilities.ExcelReader;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ExcelUtilityHelper {

  
    WebDriver driver = DriverFactory.getDriver();
    String excelPath = ConfigReader.getProperty("excelfilepath");
    String code;
    String result;
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    // Fetch Python code from Excel
    public String getPythonCodeFromExcel(String sheetName, int rowNumber) throws InvalidFormatException, IOException {
    	//ExcelReader	reader=new ExcelReader("src/test/resources/TestData/PythonCode.xlsx");
    	 ExcelReader reader = new ExcelReader(excelPath);
   	 List<Map<String, String>> allRowsData = reader.readAllRows(sheetName);
   	 return allRowsData.get(rowNumber).get("Pcode");
   	
    }

    // Fetch expected result from Excel
   public String getExpectedResultFromExcel(String sheetName, int rowNumber) throws InvalidFormatException, IOException {
        ExcelReader reader = new ExcelReader(excelPath);
        List<Map<String, String>> testData = reader.readAllRows(sheetName);
        validateRowNumber(rowNumber, testData);
        return testData.get(rowNumber).get("Result");
    }

    // Helper: validate row index
    private void validateRowNumber(int rowNumber, List<Map<String, String>> data) {
        if (rowNumber < 0 || rowNumber >= data.size()) {
            throw new IllegalArgumentException("Row number out of bounds: " + rowNumber);
        }
    }

    // Fetch code and enter it directly into the focused editor
    public void enterPythonCode(String sheetName, int rowNumber) throws InvalidFormatException, IOException {
        String code = getPythonCodeFromExcel(sheetName, rowNumber);
        enterPracticeCode(code);
    }

    // Fetch expected result only
    public String getExpectedResult(String sheetName, int rowNumber) throws InvalidFormatException, IOException {
        return getExpectedResultFromExcel(sheetName, rowNumber);
    }
    
    // Send plain code to the focused input
    public void enterCode(String code) {
        new Actions(driver).sendKeys(code).perform();
    }

    // Send multi-line code with simulated ENTER and BACKSPACE keys
    public void enterPracticeCode(String code) {
        Keys modifierKey = System.getProperty("os.name").toLowerCase().contains("mac") ? Keys.COMMAND : Keys.CONTROL;

        // Clear focused input before typing
        new Actions(driver)
            .keyDown(modifierKey)
            .sendKeys("a")
            .keyUp(modifierKey)
            .sendKeys(Keys.BACK_SPACE)
            .perform();

        for (String line : code.split("\n")) {
            if (line.trim().equalsIgnoreCase("\\b")) {
                new Actions(driver).sendKeys(Keys.BACK_SPACE).perform();
            } else {
                new Actions(driver).sendKeys(line).sendKeys(Keys.RETURN).perform();
            }
        }
    }
    
    public static void handleAlert(WebDriver driver ) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            LogHandler.info("Alert found: " + alertText);
            // alert.accept(); // Optional
        } catch (TimeoutException e) {
        	LogHandler.info("No alert present.");
        } catch (Exception e) {
        	LogHandler.error("Unexpected error while handling alert:", e);
        }
    }

public String getErrorText() {
	String errorMsg = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	return errorMsg;
}
	
public boolean isAlertIsPresent() 
{
	try {
        
        wait.until(ExpectedConditions.alertIsPresent());
        return true;
    } catch (Exception e) {
        return false;
    }
}		

}
