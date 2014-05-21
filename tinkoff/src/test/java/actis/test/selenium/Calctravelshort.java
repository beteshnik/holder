package actis.test.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.*;
import org.testng.annotations.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Calctravelshort extends actis.test.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testCalctravelshort() throws Exception {  
	int errorn = 0;
	driver.get(baseUrl + "/");
    driver.findElement(By.xpath("//a[@class='yellow-btn yellow-btn__header-buy']")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout: не появилась кнопка");
    	if ("Расчет и покупка страхового полиса".equals(driver.findElement(By.xpath("//div[@class='popup__title popup__title_calc']")).getText())) break;
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("//a[@class='round-icon round-icon_link round-icon_small round-icon_travel _get-view']")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout: не появилась форма калькулятора");
    	if ("Куда вы планируете отправиться?".equals(driver.findElement(By.xpath("//div[@class='fieldset__title fieldset__title_country']")).getText())) break;
    	Thread.sleep(1000);
    }
    try {
    assertTrue(isElementPresent(By.id("search")));
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    driver.findElement(By.linkText("Шенген")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout: не подгрузился блок с типом страховки");
    	if ("Тип страховки".equals(driver.findElement(By.xpath("//div[@class='fieldset__title fieldset__title_type']")).getText())) break;
    	Thread.sleep(1000);
    }
    try {
    assertTrue(driver.findElement(By.id("type-single")).isSelected());
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    try {
    assertTrue(isElementPresent(By.id("travel-period-from")));
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    try {
    assertTrue(isElementPresent(By.id("travel-period-to")));
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    try {
    assertEquals("16", driver.findElement(By.xpath("//div[@class='element-val element-val_days']")).getText());
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    try {
    driver.findElement(By.xpath("//div[@class='input-wrap input-wrap_spinner input-wrap_inline input-wrap_adults']/span/a[.='▲']")).click();
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    Thread.sleep(1000);
    try {
    assertTrue(driver.findElement(By.id("cover-price-30")).isSelected());
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    try {
    assertEquals("Добавить опции и риски", driver.findElement(By.xpath("//div[@class='popup__subtitle popup__subtitle_extra _collapsed']")).getText());
    } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    driver.findElement(By.xpath("//div[@class='radio-wrap radio-wrap_default radio-wrap_15']/span")).click();
    Thread.sleep(3000);
    //assertEquals("443,42", driver.findElement(By.xpath("//span[@class='policy-cost__sum-num _result']")).getText());
    try {
        assertEquals("446,422", driver.findElement(By.xpath("//span[@class='policy-cost__sum-num _result']")).getText());
      } catch (Error e) {
        	errorn=errorn+1;
          	errorList(errorn,e);
      }
    driver.findElement(By.xpath("//a[@class='yellow-btn yellow-btn_make-policy _get-view']")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout: не появилась форма страхователя");
    	if ("Страхователь".equals(driver.findElement(By.xpath("//div[@class='fieldset__title fieldset__title_insurer']")).getText())) break;
    	Thread.sleep(1000);
    }
    //assertEquals("443,422", driver.findElement(By.xpath("//span[@class='policy-cost__sum-num _result']")).getText());
    try {
        assertEquals("443,422", driver.findElement(By.xpath("//span[@class='policy-cost__sum-num _result']")).getText());
      } catch (Error e) {
      	errorn=errorn+1;
      	errorList(errorn,e);
      }
    String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
	  fail(verificationErrorString);
	  }
  }
  
  private StringBuffer errorList (int errorn, Error e) {
	  String errornString = "\n" + errorn + ". ";
      verificationErrors.append(errornString);
      verificationErrors.append(e.toString());
	  return verificationErrors;
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
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
}