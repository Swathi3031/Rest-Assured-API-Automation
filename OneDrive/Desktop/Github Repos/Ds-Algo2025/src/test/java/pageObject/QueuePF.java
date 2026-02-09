package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueuePF {

    WebDriver driver;

    public QueuePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(linkText = "Sign in")
   	WebElement signIn;
    
    @FindBy(xpath = "//a[@href='queue']")
    WebElement getStartQueue;
    
    @FindBy(linkText = "NumpyNinja")
	WebElement numpylink;
    
    @FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")
	WebElement dropDown;

	
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu')]//a")
	List <WebElement> options;
	
	@FindBy(xpath = "//div[@id='navbarCollapse']/div[2]/ul/a[2]")
	WebElement usernamelink;
	
	@FindBy(linkText = "Sign out")
    WebElement signOutLink;
    
    @FindBy(linkText = "Implementation of Queue in Python")
    WebElement implementationOfQueueInPython;

    @FindBy(linkText = "Implementation using collections.deque")
    WebElement implementationUsingCollectionsDeque;

    @FindBy(linkText = "Implementation using array")
    WebElement implementationUsingArray;

    @FindBy(linkText = "Queue Operations")
    WebElement queueOperations;

    @FindBy(linkText = "Try here>>>")
    WebElement tryHereBtn;

    @FindBy(xpath = "//button[text()='Run']")
    WebElement runButton;
    
    @FindBy(id = "output") 
	WebElement outputArea;
	  
    @FindBy(linkText = "Practice Questions")
    WebElement practiceQuestionsLink;
    
    @FindBy(xpath = "//div[@role='alert']")
	WebElement logOutMsg;
    
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
	
	
	public void clickSignIn() {
		 signIn.click();
		}
    
    public void clickQueueTopic(String topic) {
        Map<String, WebElement> topicMap = new HashMap<>();
        topicMap.put("Implementation of Queue in Python", implementationOfQueueInPython);
        topicMap.put("Implementation using collections.deque", implementationUsingCollectionsDeque);
        topicMap.put("Implementation using array", implementationUsingArray);
        topicMap.put("Queue Operations", queueOperations);

        WebElement element = topicMap.get(topic);
        if (element == null) {
            throw new IllegalArgumentException("Unknown topic: " + topic);
        }

        element.click();
    }
    
    
    
    private String getTitleFromTopic(String topic) {
        switch (topic.toLowerCase()) {
            case "implementation of queue in python":
                return "Implementation of Queue in Python";
            case "implementation using collections.deque":
                return "Implementation using collections.deque";
            case "implementation using array":
                return "Implementation using array";
            case "queue operations":
                return "Queue Operations";
            default:
                throw new IllegalArgumentException("Unknown topic for title mapping: " + topic);
        }
    }

    
    public void verifyPageNavigation(String topic) {
        String expectedTitle = getTitleFromTopic(topic);
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, 
            "Expected title: \"" + expectedTitle + "\" but got: \"" + actualTitle + "\"");
    }

    
    public void clickRunButton() {
        runButton.click();
    }
    public String getOutputData() { 
		  return outputArea.getText();
		  }
    
    public void clickPracticeQuestionsLink() {
        practiceQuestionsLink.click();
    }

    
    public void clickImplementationOfQueueInPython() {
		implementationOfQueueInPython.click();
		}
    
    public void navigateToQueuePage() {
    	getStartQueue.click();
    }
    
    public void clickSignOut() {
		signOutLink.click();

	}
    public String logOutMessage() {
		return logOutMsg.getText();
	}
    
    public void clickTryHere() {
        tryHereBtn.click();
    }
    
    public String pageTitle() {
		String title=driver.getTitle();
		return title;
	}
    
}
