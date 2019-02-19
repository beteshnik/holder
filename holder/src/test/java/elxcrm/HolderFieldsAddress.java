package elxcrm;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.Properties;





public class HolderFieldsAddress {
  HolderUtils utils = new HolderUtils();
  private static RemoteWebDriver driver;
  private String baseUrl;
  private String gridUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationerrors = new StringBuffer();
  private String username = System.getProperty("new.username");
  private String password = System.getProperty("user.password");
  private int errorn = 0;
  


  @Before
  public void setUp() throws Exception {
       	baseUrl="http://" + System.getProperty("lk.url") + "." + System.getProperty("base.url");
		gridUrl=System.getProperty("grid.url");
		ChromeOptions options = new ChromeOptions();
		driver = new RemoteWebDriver(new URL(gridUrl),options);
  		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);


  }

  @Test
  public void testHolderFieldsAddress() throws Exception {

	 
//1. Авторизоваться, 
	driver.get(baseUrl + "");
	utils.waitForElement("//form", driver);
	driver.findElement(By.xpath("//div[@class='cookie-sticky-holder fixed ']//div[@class='close-btn icon-cross-white']")).click();
	driver.findElement(By.name("login")).click();
	driver.findElement(By.name("login")).clear();
	driver.findElement(By.name("login")).sendKeys(username + "@gmail.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.xpath("//form/button[@class='btn btn-primary']")).click();
	utils.waitForElement("//h1[text()='Личный кабинет']", driver);
//открыть форму акции ->	
	//driver.findElement(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='Принять участие']")).click();
	driver.get(baseUrl + "campaigns/1");
	utils.waitForElement("//form", driver);
//открылась страница с формой акции (проверка по заголовоку, url)
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//h1[contains(.,'Комплимент за отзыв')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	  

//2. Открыть закладку Адрес ->
driver.findElement(By.xpath("//a[div[text()='Адрес доставки']]")).click();
utils.waitForLoad(driver);

//нет попапа с ошибкой  
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//div[@class='modalMessage modalMessage_error']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//3. На форме присутствуют поля
//Город - пусто
	errorn=errorn+1;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[label[contains(.,'Город')]]//div[@class='Select-value']"),driver));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Улица - пусто
	errorn=errorn+1;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[label[contains(.,'Улица')]]//div[@class='Select-value']"),driver));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Дом - пусто
	errorn=errorn+1;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[label[contains(.,'Дом')]]//div[@class='Select-value']"),driver));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Квартира, поле ввода
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Квартира')]/following-sibling::input"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Квартира')]/following-sibling::input")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }


 
//Индекс, поле ввода
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Индекс (рассчитывается автоматически)')]/following-sibling::input"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Индекс (рассчитывается автоматически)')]/following-sibling::input")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }


//Ознакомление с правилами акции, флаг
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Я подтверждаю, что ознакомлен с')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'правилами акции')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'и даю согласие на обработку моих персональных данных.')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//флаг снят


	errorn=errorn+1;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[@class='customCheckbox']/span[@class=checked]"),driver));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Кнопка Отправить

   errorn=errorn+1;
   try {
   		assertEquals("Отправить на модерацию",driver.findElement(By.xpath("//div[@class='btn btn-disabled StepForm__button_right']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
  }


 
 
  	@After
	public void tearDown() throws Exception {

		driver.quit();
		String verificationerrorstring = verificationerrors.toString();
   		if (!"".equals(verificationerrorstring)) {
      		fail(verificationerrorstring);
    	}
	}
  
}
