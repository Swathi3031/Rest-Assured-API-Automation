package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dsUtilities.ConfigReader;

public class GraphPF {

	
WebDriver driver;
   public GraphPF(WebDriver driver)
  { 
    //this.driver = DriverFactory.getDriver();
	this.driver=driver;
    PageFactory.initElements(driver, this);
  }
   @FindBy(linkText = "Sign in")
	WebElement signIn;
  
  @FindBy(xpath = "//a[@href='graph']") 
   WebElement getStartGraph;
  
  @FindBy(linkText = "NumpyNinja")
	WebElement numpylink;
  
  @FindBy(xpath = "//div[contains(@class,'alert-primary')]")
  WebElement sucessMessage;

  
  @FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")
	WebElement dropDown;

	
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu')]//a")
	List <WebElement> options;
	
	@FindBy(xpath = "//div[@id='navbarCollapse']/div[2]/ul/a[2]")
	WebElement usernamelink;
	
	@FindBy(linkText = "Sign out")
    WebElement signOutLink;
  
	@FindBy(linkText = "Graph") 
	  WebElement graphLink;
	  
	  @FindBy(linkText = "Graph Representations") 
	   WebElement graphRepresentationsLink;
	  
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
	    
	    @FindBy(xpath = "//div[@class='input']/div") 
		  WebElement codeEditor;
	    
	    
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
			
			public void graphPage() {
				driver.get(ConfigReader.getProperty("graphUrl"));
				
			}
				
			public void graphRepPage() {
				driver.get(ConfigReader.getProperty("graphrepresentationsurl"));
				
			}
			public void tryEditorPage() {
				driver.get(ConfigReader.getProperty("tryEditorUrl"));
				
			}
		
			public void getStartbtnclick() {
				getStartGraph.click();
			}
			
		public void clickGraph() { 
			  graphLink.click(); 
			  }
		  
		  public void clickTryHereLink() { 
			  tryHereBtn.click(); 
			  }
		  
		  public void clickGraphRepresentations() { 
			  graphRepresentationsLink.click(); 
			  }
		  
		  public void clickTryHere() {
		        tryHereBtn.click();
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

	    
	    public void clickSignOut() {
			signOutLink.click();

		}
		
	    public String logOutMessage() {
			return logOutMsg.getText();
		}
	    
	   
	    public String pageTitle() {
			String title=driver.getTitle();
			return title;
		}
	    
	    public void clearEditor() {
			codeEditor.clear();
		}
	   
	    public String getMessage() {
			String loginMessage=sucessMessage.getText();
			return loginMessage;
		}
}
