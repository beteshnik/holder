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
  private String username = System.getProperty("new.username") + "@gmail.com";
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

	PageObjectLoginForm loginForm = new PageObjectLoginForm();
	loginForm.init(driver);
//1. Авторизоваться ->
	driver.get(baseUrl + "");
	utils.waitForElement("//form", driver);
	loginForm.cookiePanelClose();
	loginForm.login(username,password);
	utils.waitForElement("//h1[text()='Личный кабинет']", driver);
//открыть форму акции ->	
	//driver.findElement(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='Принять участие']")).click();
	driver.get(baseUrl + "campaigns/1");
	utils.waitForElement("//form", driver);
//открылась страница с формой акции (проверка по заголовоку, url)
	errorn++;
	try {
    		driver.findElement(By.xpath("//h1[contains(.,'Комплимент за отзыв')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	  

//2. Открыть закладку Адрес ->
driver.findElement(By.xpath("//a[div[text()='Адрес доставки']]")).click();
utils.waitForLoad(driver);

//нет попапа с ошибкой  
   errorn++;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//div[@class='modalMessage modalMessage_error']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//3. На форме присутствуют поля
//Город - пусто
	errorn++;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[label[contains(.,'Город')]]//div[@class='Select-value']"),driver));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Улица - пусто
	errorn++;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[label[contains(.,'Улица')]]//div[@class='Select-value']"),driver));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Дом - пусто
	errorn++;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[label[contains(.,'Дом')]]//div[@class='Select-value']"),driver));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Квартира, поле ввода
   errorn++;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Квартира')]/following-sibling::input"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn++;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Квартира')]/following-sibling::input")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }


 
//Индекс, поле ввода
   errorn++;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Индекс (рассчитывается автоматически)')]/following-sibling::input"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn++;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Индекс (рассчитывается автоматически)')]/following-sibling::input")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }


//Ознакомление с правилами акции, флаг
   errorn++;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Я подтверждаю, что ознакомлен с')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn++;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'правилами акции')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn++;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'и даю согласие на обработку моих персональных данных.')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//флаг снят


	errorn++;
	try {

		assertFalse(utils.isElementPresent(By.xpath("//div[@class='customCheckbox']/span[@class=checked]"),driver));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//Кнопка Отправить

   errorn++;
   try {
   		assertEquals("Отправить заявку",driver.findElement(By.xpath("//div[@class='btn btn-disabled StepForm__button_right']")).getText());

   
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
