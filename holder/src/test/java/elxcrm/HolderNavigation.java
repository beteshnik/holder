package elxcrm;

import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.*;





public class HolderNavigation {
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
  		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void testHolderNavigation() throws Exception {

	
//1. Авторизоваться ->
	driver.get(baseUrl + "");
	utils.waitForElement("//form", driver);
	driver.findElement(By.xpath("//div[@class='close-btn icon-cross-white']")).click();
	driver.findElement(By.name("login")).click();
	driver.findElement(By.name("login")).clear();
	driver.findElement(By.name("login")).sendKeys(username + "@gmail.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.xpath("//form/button[@class='btn btn-primary']")).click();
	utils.waitForElement("//h1[text()='Личный кабинет']", driver);

// открывается страница /campaignes
	errorn=errorn+1;
	try {
    	assertEquals(baseUrl + "campaigns", driver.getCurrentUrl());
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }


//на странице есть кампания по прихватке (проверка по заголовку)
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//div[@class='campaignItem']//h2[contains(.,'Комплимент за отзыв')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }


//2. Кликнуть по блоку акции  ->
   driver.findElement(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='Принять участие']")).click();
   //driver.get(baseUrl + "campaigns/1");
	utils.waitForLoad(driver);
//открылась страница с формой акции (проверка по заголовоку, url)
	errorn=errorn+1;
	try {
    	assertEquals(baseUrl + "campaigns/1", driver.getCurrentUrl());
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//h1[contains(.,'Комплимент за отзыв')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	  
//нет попапа с ошибкой  
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//div[@class='modalMessage modalMessage_error']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//3. На странице присутствуют  
//заголовок Регистрация в акции
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//h2[contains(.,'Регистрация в акции')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	 
//текст Заполненные поля сохраняются...
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//p[text()='Заполненные поля сохраняются и доступны для последующего редактирования']"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	 

//ссылка Правила акции
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//a[text()='Правила акции']"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	 
//кнопки переключения шагов
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//div[text()='Данные о продуктах']"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	 
	  	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//div[text()='Адрес доставки']"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	 
//блок Оставьте свой отзыв о технике Electrolux и получите комплимент.
	  	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//h3[contains(.,'Оставьте свой отзыв о технике Electrolux и получите комплимент')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
          }	   
//ссылка Как найти информацию и код...
	  	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//a[contains(.,'Как найти информацию и код продукта')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
          }	  

//ссылка Как найти код PNC?
	  	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//a[contains(.,'Как найти код PNC?')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
          }	 
//4. На странице отсутствует блок Комментарии модератора
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//div[contains(.,'Комментарии')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//5. Блок Как найти информация и код... свёрнут
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//div[@class='center-block help__toggleBlockWrap']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//p[contains(.,'Первый способ:')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//p[contains(.,'Второй способ:')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//6. По ссылке Правила акции ->

//Правила акции
	
	errorn=errorn+1;
	try {
			 assertThat("Верная ссылка на правила акции", driver.findElement(By.linkText("Правила акции")).getAttribute("href"), containsString("rules.pdf"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	



//7. Кликнуть ссылку Как найти информацию и код...  ->
driver.findElement(By.xpath("//a[contains(.,'Как найти информацию и код продукта')]")).click();

//открылся блок
   errorn=errorn+1;
   try {
   		assertTrue(utils.isElementPresent(By.xpath("//div[@class='center-block help__toggleBlockWrap']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
   errorn=errorn+1;
   try {
   		assertTrue(utils.isElementPresent(By.xpath("//p[contains(.,'Первый способ:')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
   		assertTrue(utils.isElementPresent(By.xpath("//p[contains(.,'Второй способ:')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//8. Кликнуть ссылку Как найти информацию и код...  ->
driver.findElement(By.xpath("//a[contains(.,'Как найти информацию и код продукта')]")).click();

//свернулся блок
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//div[@class='center-block help__toggleBlockWrap']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//p[contains(.,'Первый способ:')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//p[contains(.,'Второй способ:')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

driver.get(baseUrl);
utils.waitForLoad(driver);


if (utils.isElementPresent(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='Заполняется']"),driver))
{driver.findElement(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='Заполняется']")).click();}
if (utils.isElementPresent(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='На заполнении']"),driver))
{driver.findElement(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='На заполнении']")).click();}
utils.waitForElement("//form", driver);

driver.findElement(By.xpath("//button[contains(.,'Добавить отзыв')]")).click();
utils.waitForLoad(driver);
int elementY= driver.findElement(By.xpath("//a[contains(.,'Как найти информацию и код продукта')]")).getLocation().y;
JavascriptExecutor executor = (JavascriptExecutor) driver;
Long scroll = (Long) executor.executeScript("return window.pageYOffset;");
int scrollY = scroll.intValue();


  errorn=errorn+1;
   try {
   		 	assertTrue(elementY +"VS"+ scrollY, elementY < scrollY);
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

driver.get(baseUrl);
utils.waitForElement("//*[text()='Личный кабинет']",driver);

driver.get(baseUrl + "campaigns/1");
utils.waitForElement("//form", driver);
driver.findElement(By.xpath("//button[contains(.,'Удалить отзыв')]")).click();
utils.waitForLoad(driver);

//9. 1. Кликнуть ссылку Как найти код PNC?  ->
driver.findElement(By.xpath("//a[contains(.,'Как найти код PNC?')]")).click();
utils.waitForLoad(driver);
//фокус на блок Как найти информацию и код...
elementY= driver.findElement(By.xpath("//a[contains(.,'Как найти информацию и код продукта')]")).getLocation().y;
scroll = (Long) executor.executeScript("return window.pageYOffset;");
scrollY = scroll.intValue();

 	    errorn=errorn+1;
   try {
   		 	assertTrue(elementY +"VS"+ scrollY,elementY > scrollY);
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
 
driver.get(baseUrl);
utils.waitForLoad(driver);
//driver.findElement(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='Принять участие']")).click();
driver.get(baseUrl + "campaigns/1");
utils.waitForElement("//form", driver);
driver.findElement(By.xpath("//button[contains(.,'Добавить отзыв')]")).click();
Thread.sleep(500);

//9. 2. Кликнуть ссылку Как найти серийный номер  ->
driver.findElement(By.xpath("//a[contains(.,'Как найти серийный номер?')]")).click();
utils.waitForLoad(driver);
//фокус на блок Как найти информацию и код...
elementY= driver.findElement(By.xpath("//a[contains(.,'Как найти информацию и код продукта')]")).getLocation().y;
scroll = (Long) executor.executeScript("return window.pageYOffset;");
scrollY = scroll.intValue();

 	    errorn=errorn+1;
   try {
   		 	assertTrue(elementY +"VS"+ scrollY,elementY > scrollY);
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	 
  driver.findElement(By.xpath("//button[contains(.,'Удалить отзыв 1')]")).click();
//10. Открыть закладку с адресом   ->
driver.get(baseUrl);
utils.waitForLoad(driver);
//driver.findElement(By.xpath("//h2[contains(.,'Комплимент за отзыв')]/following-sibling::*//a[text()='Принять участие']")).click();
driver.get(baseUrl + "campaigns/1");
utils.waitForElement("//form", driver);

driver.findElement(By.xpath("//a[div[text()='Адрес доставки']]")).click();
utils.waitForLoad(driver);


//есть ссылка на условия акции 
	  	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//a[contains(.,'правилами акции')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
          }	 
//11. По ссылке на условия акции   ->
//условия акции
	errorn=errorn+1;
	try {

		//assertEquals("http://s3." + System.getProperty("base.url") + "electrolux-crm/campaigns/1/assets/rules.pdf",  driver.findElement(By.linkText("правилами акции")).getAttribute("href"));
		 assertThat("Верная ссылка на правила акции", driver.findElement(By.linkText("правилами акции")).getAttribute("href"), containsString("rules.pdf"));
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
