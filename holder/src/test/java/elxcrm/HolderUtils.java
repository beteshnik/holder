package elxcrm;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;





public class HolderUtils {


  public StringBuffer errorList (int errorn, Error e) {
      StringBuffer verificationerrors = new StringBuffer();
	  String errornString = "\n" + errorn + ". ";
      verificationerrors.append(errornString);
      verificationerrors.append(e.toString());
	  return verificationerrors;
  }

  public boolean isElementPresent(By by, WebDriver driver) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent(WebDriver driver) {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public String closeAlertAndGetItsText(WebDriver driver) {
  boolean acceptNextAlert = true;
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
 
	public boolean waitForElement(String element, WebDriver driver) {
 
 WebDriverWait wait = new WebDriverWait(driver, 20);
 wait.withMessage("Не обнаружен элемент");
 try{
 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
   } catch (TimeoutException ignore) {

     }
return true;
}
  
public void waitForLoad(WebDriver driver) {
     WebDriverWait wait = new WebDriverWait(driver, 30);
	 wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
}  	
  
}
