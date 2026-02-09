package pageObject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dsUtilities.ConfigReader;

public class StackPage {
	
 WebDriver driver;
	
	//constructor
 
 public StackPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
 }
		//  WebElements 
 
 @FindBy(xpath = "//a[text()='Get Started' and @href='stack']")
    private WebElement gettingStartedStackBtn;

 @FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")  // Dropdown button
 WebElement dropdownMenu;
 
 @FindBy(xpath = "//a[text()='Stack']")  // Array option under the dropdown
 WebElement StackOption;
 
 @FindBy(xpath = "//h4[contains(text(),'Stack')]")
 private WebElement StackPageHeader;
 
 //@FindBy(xpath = "//a[text()='Try here']")
 @FindBy(css = "a[href='/tryEditor']")
 private WebElement tryhereLink;
 
 
	    @FindBy(linkText = "Operations in Stack")
	    private WebElement operationsInStackLink;

	    @FindBy(linkText = "Implementation")
	    private WebElement implementationLink;

	    @FindBy(linkText = "Applications")
	    private WebElement applicationsLink;

	    @FindBy(linkText = "Practice Questions")
	    private WebElement practiceQuestionsLink;
	    
	    //Actions
	    
	    public void clickDropdown() {
	        dropdownMenu.click();
	    }
	    
	    public void selectstackFromDropdown() {
	    	StackOption.click(); // Click the "Stack" option from the dropdown directly after waiting for it to be clickable
	    }
	    
	    public void clickGettingStarted() {
	    	gettingStartedStackBtn.click();
	    }
	    
	    public void scrollAndClickDataStructuresDropdown() {
	        WebElement dataStructuresDropdown = driver.findElement(By.xpath("//a[text()='Data Structures']"));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(dataStructuresDropdown).perform();
	        dataStructuresDropdown.click();
	    }
	    
	    public boolean isStackPageDisplayed() {
	        return StackPageHeader.isDisplayed(); 
	        
	    }
	    
	    public void scrollToTryHere() {
	        try {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tryhereLink);
	            System.out.println("Scrolled to Try Here link successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to scroll to Try Here link: " + e.getMessage());
	        }
	    }
	    
	    public void clickOperationsInStack() {
	        operationsInStackLink.click();
	    }

	    public void clickImplementation() {
	        implementationLink.click();
	    }

	    public void clickApplications() {
	        applicationsLink.click();
	    }

	    public void clickPracticeQuestions() {
	        practiceQuestionsLink.click();
	    }
	   
	    public void clickTryhere() {
	    	tryhereLink.click();
	    }
	    
	 //  Try Editor Elements and Actions 
	    
	    @FindBy(xpath = "//form[@id='answer_form']/div/div/div/textarea")
	    private WebElement codeEditor;

	    @FindBy(xpath = "//button[text()='Run']")
	    private WebElement runButton;

	    @FindBy(id = "output")
	    private WebElement outputArea;
	    
	    
	    //TryEditor Actions 
	    public void enterCode(String code) {
	        try {
	            codeEditor.clear();
	            codeEditor.sendKeys(code);
	        } catch (Exception e) {
	            System.out.println("Failed to enter code in the editor: " + e.getMessage());
	        }
	    }

	    public void clickRunButton() {
	        runButton.click();
	    }

	    public String getEditorOutput() {
	        return outputArea.getText();
	    }

   
public boolean isRunButtonVisible() {
    try {
        return runButton.isDisplayed();
    } catch (NoSuchElementException e) {
        return false;  // Return false if the element is not found
    }
}

//Navigate methods inside ArraysPage

public void navigateToStackPage() {
	dropdownMenu.click();
	StackOption.click();
}


public void navigateToOperationsinStackPage() {
	dropdownMenu.click();
	StackOption.click();
	operationsInStackLink.click();
}


public void navigateToImplementationPage() {
	dropdownMenu.click();
	StackOption.click();
	implementationLink.click();
}

public void navigateToApplicationsPage() {
	dropdownMenu.click();
	StackOption.click();
	applicationsLink.click();
}


public void navigateToStackstryeditorPage() {
	gettingStartedStackBtn.click();
	operationsInStackLink.click();
	scrollToTryHere();   
    tryhereLink.click();
}



public void navigateTostackspracticequestion() {
	gettingStartedStackBtn.click();
	operationsInStackLink.click();
	practiceQuestionsLink.click();
}



}