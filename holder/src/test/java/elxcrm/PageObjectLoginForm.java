package elxcrm;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class PageObjectLoginForm {
 
   //private RemoteWebDriver driver;
 
    @FindBy(xpath = "//div[@class='cookie-sticky-holder fixed ']//div[@class='close-btn icon-cross-white']")
    private WebElement stickyHolderButton;
	
	 @FindBy(name = "login")
     private WebElement loginInput;
	 
	 @FindBy(name = "password")
     private WebElement passwordInput;
	 
	 @FindBy(xpath= "//button[text()='Войти']")
     private WebElement loginButton;
  
  	 public void login(String username, String password) {
	 stickyHolderButton.click();
	  loginInput.click();
	  loginInput.clear();
	  loginInput.sendKeys(username + "@gmail.com");
	  passwordInput.click();
	  passwordInput.clear();
	  passwordInput.sendKeys(password);
	  loginButton.click();
    }
 
 	public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}