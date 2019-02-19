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





public class HolderFieldsReview {
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
  public void testHolderFieldsReview() throws Exception {

	 
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



//2. По умолчанию нет полей отзыва
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//label[contains(.,'Площадка размещения отзыва')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//label[contains(.,'Сcылка на отзыв')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//3.  Нажать Добавить отзыв
	driver.findElement(By.xpath("//button[contains(.,'Добавить отзыв')]")).click();
	utils.waitForLoad(driver);

//нет попапа с ошибкой  
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//div[@class='modalMessage modalMessage_error']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }


//На форме присутствуют поля
//Площадка размещения отзыва, выпадающий список
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Площадка размещения отзыва')]/following-sibling::*/select[@name='$.products[0].reviews[0].site']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле по умолчанию заполнено "Яндекс Маркет"

   errorn=errorn+1;
   try {
   		assertEquals("Яндекс Маркет",driver.findElement(By.xpath("//label[contains(.,'Площадка размещения отзыва')]/following-sibling::*/span[@class='StepForm__selectFakeElem']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//доступны для выбора М.Видео, Технопарк, Holodilnik, Эльдорадо
   errorn=errorn+1;
   try {
   		assertEquals("М.Видео",driver.findElement(By.xpath("//label[contains(.,'Площадка размещения отзыва')]/following-sibling::*//option[@value='М.Видео']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
   		assertEquals("Технопарк",driver.findElement(By.xpath("//label[contains(.,'Площадка размещения отзыва')]/following-sibling::*//option[@value='Технопарк']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
   		assertEquals("Holodilnik",driver.findElement(By.xpath("//label[contains(.,'Площадка размещения отзыва')]/following-sibling::*//option[@value='Holodilnik']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	    errorn=errorn+1;
   try {
   		assertEquals("Эльдорадо",driver.findElement(By.xpath("//label[contains(.,'Площадка размещения отзыва')]/following-sibling::*//option[@value='Эльдорадо']")).getText());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Номер карты магазина, поле ввода

  errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Номер бонусной/скидочной карты Магазина')]/following-sibling::input[@name='$.products[0].reviews[0].loyaltyCardNumber']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Номер бонусной/скидочной карты Магазина')]/following-sibling::input")).getAttribute("value"));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

	//Скриншот отзыва (png, jpg), заголовок
  errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Скриншот отзыва (PNG, JPG) (Сделайте скриншот отзыва, сохраните его, и Вы сможете загрузить изображение или фото с Вашего компьютера. Загрузка может не заканчиваться, если у Вас очень большой размер изображения. Уменьшите его и повторите загрузку.)')]/following-sibling::*//input[@name='attachment']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }


//ссылка Добавьте  документ
  errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Скриншот отзыва (PNG, JPG)')]/following-sibling::*//*[contains(.,'Добавьте документ')]"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//нет картинок
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//label[contains(.,'Скриншот отзыва (PNG, JPG)')]/following-sibling::*//div[@class='StepForm__attach ']"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Ссылка на отзыв, поле ввода
  errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Сcылка на отзыв (На  скриншоте должно быть четко   видно: Адресная строка, где размещен отзыв(сайт), от чьего лица отзыв, сам отзыв, дата отзыва)')]/following-sibling::input[@name='$.products[0].reviews[0].reviewUrl']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//поле пусто
   errorn=errorn+1;
   try {
   		assertEquals("",driver.findElement(By.xpath("//label[contains(.,'Сcылка на отзыв')]/following-sibling::input")).getAttribute("value"));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//4. Выбрать приз, выпадающий список
   errorn=errorn+1;
   try {
		assertTrue(utils.isElementPresent(By.xpath("//label[contains(.,'Выбрать комплимент (Выберите один из предложенных вариантов.)')]/following-sibling::*//div[@class='Select-input']"),driver));

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	 
   errorn=errorn+1;
   try {
   		assertFalse(utils.isElementPresent(By.xpath("//*[contains(.,'Чистящее средство')]"),driver));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//поставить фокус в поле

driver.findElement(By.xpath("//label[contains(.,'Выбрать комплимент')]/following-sibling::*//div[@class='Select-placeholder']")).click();
//доступен для выбора список призов
   errorn=errorn+1;
  try {
   		assertTrue(utils.isElementPresent(By.xpath("//*[contains(.,'Чистящее средство')]"),driver));
   
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

  

driver.get(baseUrl + "");
utils.waitForLoad(driver);
driver.get(baseUrl + "campaigns/1");
utils.waitForElement("//form", driver);
driver.findElement(By.xpath("//button[@class='form-block form-block-delete']")).click();


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
