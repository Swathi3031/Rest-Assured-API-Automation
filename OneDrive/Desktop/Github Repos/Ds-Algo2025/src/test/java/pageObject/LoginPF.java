package pageObject;





import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dsUtilities.ConfigReader;


public class LoginPF {
	WebDriver driver;
	public LoginPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	@FindBy(linkText = "Sign in")WebElement signIn;
	@FindBy(linkText = "NumpyNinja")WebElement numpylink;
	 @FindBy(linkText = "Login")WebElement loginLink;
	@FindBy(xpath = "//div[@class='navbar-nav']/div/a[text()='Data Structures']")WebElement dropDown;
	@FindBy(linkText = "Register")WebElement registerLink;
	
	@FindBy(id = "id_username")WebElement Login_Username;
	@FindBy(id = "id_password")WebElement Login_Password;
	@FindBy(xpath = "//input[@value='Login']")WebElement Login_Button;
	@FindBy(xpath = "//a[text()='Login ']")WebElement Login_link;
	
	@FindBy(xpath = "//div[contains(text(),' You are logged in')] ")WebElement LogedIn_message;
	@FindBy(xpath = "//div[contains(text(),'Logged out')]")WebElement LogedOut_message;
	@FindBy(xpath = "//div[contains(text(), 'Invalid Username and Password')]")WebElement Invalid_Crendentials;
	@FindBy(linkText = "Sign out")WebElement Signout;

	
	public void click_homesignin() {
		signIn.click();
		
	}

	
public String page_title() {
		
	return driver.getTitle();	}
public void click_loginlink() {
	
	Login_link.click();
}
public boolean isNumpyNinjaVisible() {
	return numpylink.isDisplayed();
}

public boolean isDropdownVisible() {
	return dropDown.isDisplayed();
}
public boolean isRegisterVisible() {
	return registerLink.isDisplayed();
}
public boolean isSignin() {
	return signIn.isDisplayed();
}

public void login_user(String Username1) {
		Login_Username.clear();
		Login_Username.sendKeys(Username1);
		
	}
	
	public void login_passowrd(String Password1) {
		
		Login_Password.sendKeys(Password1);
	}
	
	public void login_button() {
		
		try {
			Login_Button.click();
		}
		catch(ElementClickInterceptedException e) {
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Login_Button));
		Login_Button.click();
		}
	}
		
	public void logout_button() {
		Signout.click();
		
	}
	
	public String getLoginMessage() {
		
		return LogedIn_message.getText();
	}
 public String getLogOutMessage() {
		
		return LogedOut_message.getText();
	}
		
	
	public void clickLoginwithoutusername() {
		
       Login_Username.clear();
		Login_Password.sendKeys("dummyusername");
		login_button();
	}
	public String isUsernameFieldEmpty() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String msg = (String) js.executeScript("return arguments[0].validationMessage;", Login_Username);
		 return msg;
	}
	
  public void clickLoginwithoutpassword() {
		
	Login_Password.clear();
	Login_Username.sendKeys("dummypassword");
		Login_Button.click();
	}
	public String isPasswordFieldEmpty() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String Passworfield_msg = (String) js.executeScript("return arguments[0].validationMessage;", Login_Password);
		return Passworfield_msg;
	}
	
	
	public String getInvalidLoginMessage() {
		
		return Invalid_Crendentials.getText();
		
	}
	
	public void loginBackgroundForPages() {
		signIn.click();
		String uname=ConfigReader.getProperty("username");
		String pwd=ConfigReader.getProperty("password");
		Login_Username.sendKeys(uname);
		Login_Password.sendKeys(pwd);
		Login_Button.click();
		
			
		
	}
}



