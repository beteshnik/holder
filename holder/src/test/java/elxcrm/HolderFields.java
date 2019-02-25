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





public class HolderFields {
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
  public void testHolderFields() throws Exception {
	PageObjectLoginForm loginForm = new PageObjectLoginForm();
	loginForm.init(driver);

//1. Авторизоваться, 
	driver.get(baseUrl + "");
	utils.waitForElement("//form", driver);
	loginForm.login(username,password);
	utils.waitForElement("//h1[text()='Личный кабинет']", driver);
//открыть форму акции ->	
	
	driver.get(baseUrl + "campaigns/1");
	utils.waitForElement("//form", driver);
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

//2. Есть кнопки
//Выбрать из моих продуктов, закладка
   errorn=errorn+1;
   try {
   		assertTrue(driver.findElement(By.xpath("//div[text()='Выбрать из моих продуктов']")).isDisplayed());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Добавить новый продукт, закладка
   errorn=errorn+1;
   try {
   		assertTrue(driver.findElement(By.xpath("//div[text()='Добавить новый продукт']")).isDisplayed());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//3. Закладка Выбрать из моих продуктов выделена как неактивная и недоступная (у пользователя нет продуктов)
   errorn=errorn+1;
   try {
   		assertTrue(driver.findElement(By.xpath("//div[@class='dataSwitcher__item dataSwitcher__item_disabled' and text()='Выбрать из моих продуктов']")).isDisplayed());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Закладка  Добавить новый продукт выделена как активная
   errorn=errorn+1;
   try {
   		assertTrue(driver.findElement(By.xpath("//div[@class='dataSwitcher__item dataSwitcher__item_active' and text()='Добавить новый продукт']")).isDisplayed());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//4. На форме присутствуют поля
//Тип товара, выпадающий список
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*/select[@name='$.products[0].category']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*/span[@class='StepForm__selectFakeElem']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//доступны для выбора
//Варочная поверхность
   errorn=errorn+1;
   try {
   		assertEquals("Варочная поверхность",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Варочная поверхность']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Вытяжка
   errorn=errorn+1;
   try {
   		assertEquals("Вытяжка",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Вытяжка']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Духовой шкаф
   errorn=errorn+1;
   try {
   		assertEquals("Духовой шкаф",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Духовой шкаф']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Микроволновая печь
   errorn=errorn+1;
   try {
   		assertEquals("Микроволновая печь",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Микроволновая печь']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Плита
   errorn=errorn+1;
   try {
   		assertEquals("Плита",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Плита']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Посудомоечная машина
   errorn=errorn+1;
   try {
   		assertEquals("Посудомоечная машина",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Посудомоечная машина']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Стиральная машина
   errorn=errorn+1;
   try {
   		assertEquals("Стиральная машина",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Стиральная машина']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Сушильная машина
   errorn=errorn+1;
   try {
   		assertEquals("Сушильная машина",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Сушильная машина']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Узкая стиральная машина с фронт. загрузкой
   errorn=errorn+1;
   try {
   		assertEquals("Узкая стиральная машина с фронт. загрузкой",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Узкая стиральная машина с фронт. загрузкой']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Холодильник
   errorn=errorn+1;
   try {
   		assertEquals("Холодильник",driver.findElement(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Холодильник']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//недоступны для выбора
//Пылесос
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//label[contains(.,'Тип продукта (Выберите тип продукта)')]/following-sibling::*//option[@value='Пылесос']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Модель товара, выпадающий список с возможностью ввода
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Модель (Выберите модель из списка)')]/following-sibling::*//input"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Модель (Выберите модель из списка)')]/following-sibling::*/span[@class='StepForm__selectFakeElem']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }



//PNC номер, выпадающий список с возможностью ввода
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'PNC код товара (Выберите PNC из списка)')]/following-sibling::*/select[@name='$.products[0].number']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'PNC код товара (Выберите PNC из списка)')]/following-sibling::*/span[@class='StepForm__selectFakeElem']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Серийный номер, поле ввода	
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Серийный номер (Указан на продукте.)')]/following-sibling::input[@name='$.products[0].serial']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Серийный номер (Указан на продукте.)')]/following-sibling::input")).getAttribute("value"));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Год покупки, поле ввода
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Год покупки (В Акции могут участвовать все модели крупной бытовой техники, приобретенной Участником с символикой Electrolux, начиная с 2010 года, на территории Российской Федерации, предложенные к продаже в интернет-магазинах)')]/following-sibling::input[@name='$.products[0].year']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("0000",driver.findElement(By.xpath("//label[contains(.,'Год покупки')]/following-sibling::input")).getAttribute("value"));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Цена, поле ввода
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Цена (При наличии скидки или использовании бонусов при покупке указать стоимость единицы товара после бонусов и скидки)')]/following-sibling::input[@name='$.products[0].price']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		
		String actualValue = driver.findElement(By.xpath("//label[contains(.,'Цена')]/following-sibling::input")).getAttribute("value");
		boolean isOk = actualValue.equals("0") || actualValue.equals("");
		assertTrue(isOk);


   		//assertEquals("0",driver.findElement(By.xpath("//label[contains(.,'Цена')]/following-sibling::input")).getAttribute("value"));/////////////////////////////////

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Где был куплен продукт выпадающий список с возможностью ввода
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//div[label[contains(.,'Где был куплен')]]//input[@name='$.products[0].store']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//div[label[contains(.,'Где был куплен')]]//input")).getAttribute("value"));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//5. На форме присутствуют кнопки
//Добавить отзыв

   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//button[contains(.,'Добавить отзыв')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Добавить продукт

   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//button[contains(.,'Добавить продукт')]"),driver));

   
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
