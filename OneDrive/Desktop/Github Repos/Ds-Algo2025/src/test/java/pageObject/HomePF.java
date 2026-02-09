package pageObject;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dsUtilities.ConfigReader;
import dsUtilities.LogHandler;

public class HomePF {
    WebDriver driver; 
    LinkedListPF ll;

    public HomePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ll = new LinkedListPF(driver);
    }

    @FindBy(xpath = "//button[text()='Get Started']")
    WebElement mainGetStartedBtn;

    @FindBy(linkText = "Sign in")
    WebElement signInLink;

    @FindBy(linkText = "Register")
    WebElement registerLink;
 
    @FindBy(xpath = "//div[@class='alert alert-primary']")
     WebElement warningMsg;
    
    @FindBy (xpath="//a[@href='data-structures-introduction']")
    WebElement getStartedDataStructures;
    
	@FindBy (xpath="//a[@href='array']") 
	WebElement getStartedArrays;
	
	@FindBy (xpath="//a[@href='linked-list']")
	WebElement getStartedLinkedList;
	
	@FindBy (xpath="//a[@href='stack']")
	WebElement getStartedStack;
	
	@FindBy (xpath="//a[@href='queue']")
	WebElement getStartedQueue;
	
	@FindBy (xpath="//a[@href='tree']")
	WebElement getStartedTree;
	
	@FindBy (xpath = "//a[@href='/graph']")
	WebElement getStartedGraph;

	
    
    @FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")
    WebElement dropdownMenu;

//    @FindBy(xpath = "//div[@class='dropdown-menu show']/a")
//    List<WebElement> dropdownOptions;
    
    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]//a")
    List<WebElement> dropdownOptions;
    
    @FindBy(linkText = "Sign out")
    WebElement signOutLink;
    
    @FindBy(xpath = "//div[@role='alert']")
	WebElement logOutMsg;
    
    public void clickGetStartedButton() {
        mainGetStartedBtn.click();
    }
    
    public void getBaseUrl() {
		driver.get(ConfigReader.getProperty("baseUrl"));
	}

    
    public void clickGetStarted(String option) {
        try {
            Map<String, WebElement> getStartedMap = Map.of(
                "Data Structures", getStartedDataStructures,
                "Arrays", getStartedArrays,
                "Linked List", getStartedLinkedList,
                "Stack", getStartedStack,
                "Queue", getStartedQueue,
                "Tree", getStartedTree,
                "Graph", getStartedGraph
            );

            WebElement button = getStartedMap.get(option.trim());
            if (button == null) {
                throw new IllegalArgumentException("Unknown module: " + option);
            }

            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        } catch (Exception e) {
            System.err.println("Error while clicking Get Started for: " + option);
            e.printStackTrace();
        }
         
    	String Title=pageTitle();
    	LogHandler.info("Title of the current page is : " + Title);
    }   
   

    
    public void clickDropdownOption(String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Click the dropdown menu
            wait.until(ExpectedConditions.elementToBeClickable(dropdownMenu)).click();

            // Re-fetch dropdown items after opening to avoid staleness
            List<WebElement> freshOptions = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='dropdown-menu show']//a")));

            for (WebElement item : freshOptions) {
                if (item.getText().trim().equalsIgnoreCase(option)) {
                    wait.until(ExpectedConditions.elementToBeClickable(item)).click();
                    return;
                }
            }
            throw new NoSuchElementException("Dropdown option not found: " + option);
        } catch (StaleElementReferenceException e) {
            // Retry once in case of DOM refresh
            clickDropdownOption(option);
        }
    }
    
    public String getWarningMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-primary']")));
        return warning.getText().trim();
    }

    
    public boolean isOnCorrectModulePage(String option) {
        // Map option names to actual page titles
        Map<String, String> titleMap = new HashMap<>();
        titleMap.put("Arrays", "Array");
        titleMap.put("Linked List", "Linked List");
        titleMap.put("Stack", "Stack");
        titleMap.put("Queue", "Queue");
        titleMap.put("Tree", "Tree");
        titleMap.put("Graph", "Graph");

        String expectedTitle = titleMap.getOrDefault(option, option); // fallback if key not found
        String actualTitle = driver.getTitle().trim().toLowerCase();
        return actualTitle.contains(expectedTitle.toLowerCase());
    }
    
    public void clickSignIn() {
		signInLink.click();

	}
    
    public void clickRegister() {
    	registerLink.click();

	}
    
    public String pageTitle() {
		String title=driver.getTitle();
		return title;
	}
    
    public void clickSignOut() {
		signOutLink.click();

	}
    public String logOutMessage() {
  		return logOutMsg.getText();
  	}
          
}

