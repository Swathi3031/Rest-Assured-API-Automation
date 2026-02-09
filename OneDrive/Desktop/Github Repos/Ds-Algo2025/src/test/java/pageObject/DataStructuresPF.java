package pageObject;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dsUtilities.*;

public class DataStructuresPF {
	WebDriver driver;
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

	public DataStructuresPF(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h5[text()='Data Structures-Introduction']/../a[text()='Get Started']")
	WebElement getStartedBtn; //getstaredbutton
	
	@FindBy(xpath = "//div[contains(@class,'alert-primary')]")
    WebElement sucessMessage;
	
	@FindBy(linkText = "NumpyNinja")
	WebElement numpylink;

	@FindBy(linkText = "Sign out")
	WebElement signOutLink;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement logOutMsg;

	@FindBy(xpath = "//div[@id='navbarCollapse']/div[2]/ul/a[2]")
	WebElement usernamelink;	
	
	@FindBy(xpath = "//div[@class='navbar-nav']/div/a[text()='Data Structures']")
	WebElement dropDown;
	
	@FindBy(linkText = "Time Complexity")
	WebElement timeComplexityLink;
	
	@FindBy(linkText = "Practice Questions")
	WebElement practiceQuestLink;
	
	@FindBy(linkText = "Try here>>>")
	WebElement tryHereLink;
	
	@FindBy(xpath = "//form[@id='answer_form']/button")
	WebElement runBtn;
	
	@FindBy(xpath = "//div[@class='input']/div")
	WebElement codeEditor;
	
	@FindBy(id = "output")
	WebElement outputText;

	
	public String getMessage() {
		String loginMessage=sucessMessage.getText();
		return loginMessage;
	}
	
		
	public String pageTitle() {
		String title=driver.getTitle();
		return title;
	}

	public void getStartbtnclick() {
		getStartedBtn.click();
	}
	
	
	public boolean isNumpyNinjaVisible() {
		return numpylink.isDisplayed();
	}

	public boolean isDropdownVisible() {
		return dropDown.isDisplayed();
	}

	public boolean isUsernameVisible() {
		return usernamelink.isDisplayed();
	}

	public boolean isSignOutVisible() {
		return signOutLink.isDisplayed();
	}
	public void timeComplexity() {
		timeComplexityLink.click();

	}
	public void clickOnPracticeQuest() {
		practiceQuestLink.click();
			}
	
	public String getPageURL() {
        return driver.getCurrentUrl();
    }

	public void clickOnTryHere() {
		tryHereLink.click();

     }
	
	public boolean runButtonDisplayed() {
		
		return runBtn.isDisplayed();
	}
	public void clickOnRunBtn() {
		
		try {
			WebElement run=wait.until(ExpectedConditions.elementToBeClickable(runBtn));
			run.click();
	}
		catch(InvalidElementStateException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", runBtn);

		}
			
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
	
	
	
	public void clearEditor() {
		codeEditor.clear();
	}
	public void editor(String code1) {
						
		if (code1!= null && !code1.trim().isEmpty()) {
		   
			JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", code1);
					
		}
	}
	
	public String getOutputData() {
		String consoleOutput=outputText.getText();
		return consoleOutput;
	}
	
	public String navigateBack() {
		driver.navigate().back();
		driver.navigate().refresh();
		String title=driver.getTitle();
		return title;
	}
	
	public void signOut() {
		signOutLink.click();

	}
	public String logOutMessage() {
		return logOutMsg.getText();
	}
	public String laningPageValidation() {
		 
		String title=driver.getTitle();
		return title;
	}
	
	public boolean runButtonValidation() {
		if(runBtn.isEnabled()==true) {
			return true;
			
		}
		return false;
	}
	
}	
	
