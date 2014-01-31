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

public class CorrectPassword extends actis.test.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testCorrectPassword() throws Exception {
    driver.get("http://eventsystem.bmw.customers/LogOn");
    assertEquals("Вход в систему - BMW Event System", driver.getTitle());
    driver.findElement(By.id("UserName")).clear();
    driver.findElement(By.id("UserName")).sendKeys("admin");
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("e9lY5z52");
    driver.findElement(By.cssSelector("button.form__button.form__button_login")).click();
    assertEquals("Изменить пароль - BMW Event System", driver.getTitle());
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
