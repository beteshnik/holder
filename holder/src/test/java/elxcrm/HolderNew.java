package elxcrm;


import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.Properties;
import org.openqa.selenium.remote.*;
import java.io.File;
import java.util.List;




public class HolderNew {
  HolderUtils utils = new HolderUtils();
  private static RemoteWebDriver driver;
  private String baseUrl;
  private String gridUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationerrors = new StringBuffer();
  private String username = System.getProperty("new.username");
  private String password = System.getProperty("user.password");
  private int errorn = 0;
  private String PNC0="925031107";
  private String product0="Холодильник";
  private String model0="S52900CSS0";
  private String serial0="12345678";
  private String year0="2001";
  private String price0="15789";
  private String shop0="Эльдорадо";
  
  private String PNC1="949592351";
  private String product1="Варочная поверхность";
  private String model1="61300M-MN";
  private String serial1="12345671";
  private String year1="2017";
  private String price1="120009";
  private String shop1="М.Видео";
  
 private String site00="Яндекс Маркет";
  private String cardnumber00="card №1";
  private String screenshot00;
  private String link00="http://www.link.ru?par=234&par_2=235";
  private String prize00="Чистящее средство для стеклокерамических варочных поверхностей";
  private String file000="holder000.jpg";
  private String file001="holder001.png";

 private String site01="Эльдорадо";
  private String cardnumber01="card №2";
  private String screenshot01;
  private String link01="http://www.mink.ru?par=234&par_2=235";
  private String prize01="Скребок для удаления загрязнений со стеклокерамических варочных поверхностей, стальной";
  private String file010="holder010.jpg";
  private String file011="holder011.png";

 private String site10="М.Видео";
  private String cardnumber10="card №3";
  private String screenshot10;
  private String link10="http://www.vink.ru?par=234&par_2=235";
  private String prize10="Средство для удаления накипи (очищает нагреватели и бак стиральных и посудомоечных машин)";
  private String file100="holder100.jpg";
  private String file101="holder101.jpg";
  
  private String xPathQuery;

  @Before
  public void setUp() throws Exception {
       	baseUrl="http://" + System.getProperty("lk.url") + "." + System.getProperty("base.url");
		gridUrl=System.getProperty("grid.url");

		ChromeOptions options = new ChromeOptions();
		driver = new RemoteWebDriver(new URL(gridUrl),options);
  		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

  }

  @Test
  public void testHolderNew() throws Exception {

//1. Создать нового пользователя (или использовать ранее созданного без заявок), авторизоваться, 
	driver.get(baseUrl + "");
	utils.waitForElement("//form", driver);
	driver.findElement(By.xpath("//div[@class='cookie-sticky-holder fixed ']//div[@class='close-btn icon-cross-white']")).click();
	driver.findElement(By.name("login")).click();
	driver.findElement(By.name("login")).clear();
	driver.findElement(By.name("login")).sendKeys(username + "@gmail.com");
	driver.findElement(By.name("password")).click();
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.xpath("//button[text()='Войти']")).click();
	utils.waitForElement("//h1[text()='Личный кабинет']", driver);
	
//добавить продукт, участвующий в акции, в Мои продукты
	driver.get(baseUrl + "products");
	utils.waitForElement("//div[text()='Добавить товар']", driver);
	driver.findElement(By.xpath("//div[text()='Добавить товар']")).click();
	//utils.waitForElement("//form", driver);
	utils.waitForLoad(driver);
  	driver.findElement(By.xpath("//label[contains(.,'PNC код товара')]/following-sibling::div//input[@role='combobox']")).sendKeys(PNC0);
	xPathQuery = String.format("//div[@class='Select-menu-outer' and contains(.,'%s')]", PNC0);
	utils.waitForElement(xPathQuery,driver);
	driver.findElement(By.xpath("//label[contains(.,'PNC код товара')]/following-sibling::div//input[@role='combobox']")).sendKeys(Keys.TAB);
	xPathQuery = String.format("//input[@value='%s']", product0);
	utils.waitForElement(xPathQuery, driver);
	
  	driver.findElement(By.xpath("//label[contains(.,'Серийный номер')]/following-sibling::input")).sendKeys(serial0);
	driver.findElement(By.xpath("//label[contains(.,'Год покупки')]/following-sibling::input")).sendKeys(year0);
	driver.findElement(By.xpath("//label[contains(.,'Цена')]/following-sibling::div//input")).sendKeys(price0);
	driver.findElement(By.xpath("//label[contains(.,'Название магазина')]/following-sibling::div//input")).sendKeys(shop0 + Keys.TAB);
	utils.waitForLoad(driver);
	driver.findElement(By.xpath("//button[text()='Добавить']")).click();
	utils.waitForElement("//div[text()='Вы уверены, что хотите добавить товар?']", driver);
	driver.findElement(By.xpath("//div[div[text()='Вы уверены, что хотите добавить товар?']]//div[text()='Да']")).click();
	

	
//открыть форму акции ->	
	driver.get(baseUrl + "campaigns/1");
	utils.waitForLoad(driver);

//открылась страница с формой акции (проверка по заголовку, url)
	errorn=errorn+1;
	try {
    		driver.findElement(By.xpath("//h1[contains(.,'Комплимент за отзыв')]"));
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	  

//2. Активна вкладка Добавить новый продукт. 


   errorn=errorn+1;
   try {
   		assertTrue(driver.findElement(By.xpath("//div[@class='dataSwitcher__item dataSwitcher__item_active' and text()='Добавить новый продукт']")).isDisplayed());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Заполнить Тип товара, модель, PNC, серийный номер, год покупки, цена, магазин.
    	new Select(driver.findElement(By.name("$.products[0].category"))).selectByVisibleText(product1);
  

    	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).sendKeys(Keys.DELETE);
	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).clear();
	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).sendKeys(model1);
	utils.waitForLoad(driver);
	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).sendKeys(Keys.TAB);
	
	new Select(driver.findElement(By.name("$.products[0].number"))).selectByVisibleText(PNC1);
	 driver.findElement(By.name("$.products[0].number")).sendKeys(Keys.TAB);
	

    driver.findElement(By.name("$.products[0].serial")).sendKeys(serial1);
	driver.findElement(By.name("$.products[0].serial")).sendKeys(Keys.TAB);
	 
	  

    driver.findElement(By.name("$.products[0].price")).sendKeys(price1);
	driver.findElement(By.name("$.products[0].price")).sendKeys(Keys.TAB);
	 
	driver.findElement(By.xpath("//label[contains(.,'Где был куплен')]/following-sibling::div//input[@role='combobox']")).sendKeys(Keys.DELETE);
	driver.findElement(By.xpath("//label[contains(.,'Где был куплен')]/following-sibling::div//input[@role='combobox']")).clear();
	driver.findElement(By.xpath("//label[contains(.,'Где был куплен')]/following-sibling::div//input[@role='combobox']")).sendKeys(shop1 + Keys.TAB);
	//utils.waitForLoad(driver);
	//driver.findElement(By.xpath("//label[contains(.,'Где был куплен')]/following-sibling::div//input[@role='combobox']")).sendKeys(Keys.TAB);
	

	driver.findElement(By.name("$.products[0].year")).sendKeys(year1);
	xPathQuery = String.format("//input[@value='%s']", year1);
    driver.findElement(By.name("$.products[0].year")).sendKeys(Keys.TAB);
	utils.waitForLoad(driver);
	
//Кликнуть по вкладке Выбрать из моих продуктов ->
   WebElement we = driver.findElement(By.xpath("//div[text()='Выбрать из моих продуктов']"));
  ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
	utils.waitForElement("//div[@class='popup__message']", driver);
	
//Появляется окно: При переключении, введенные данные будут удалены! 
//Перейти: Да/Нет
   errorn=errorn+1;
   try {
   
   assertTrue("Кликнуть по вкладке Выбрать из моих продуктов ->Появляется окно: Данные о продукте будут удалены: Да, Нет", driver.findElement(By.xpath("//div[@class='popup__message' and contains(.,'Данные о продукте будут удалены')]")).isDisplayed());
   
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }

//Да
   errorn=errorn+1;
   try {
   
   assertTrue("Кликнуть по вкладке Выбрать из моих продуктов ->Появляется окно: Данные о продукте будут удалены: Да, Нет", driver.findElement(By.xpath("//div[contains(.,'Данные о продукте будут удалены')]//div[contains(.,'Да')]")).isDisplayed());
   
   
   } catch (Error e) {
      utils.errorList(errorn,e);
     }
//Нет
   errorn=errorn+1;
   try {
   
   assertTrue("Кликнуть по вкладке Выбрать из моих продуктов ->Появляется окно: Данные о продукте будут удалены: Да, Нет", driver.findElement(By.xpath("//div[contains(.,'Данные о продукте будут удалены')]//div[contains(.,'Нет')]")).isDisplayed());
   
   
   } catch (Error e) {
     utils.errorList(errorn,e);
     }
//3. Кликнуть Нет ->
    driver.findElement(By.xpath("//div[contains(.,'Данные о продукте будут удалены')]/following-sibling::*//div[contains(.,'Нет')]")).click();
	utils.waitForLoad(driver);
//Активна вкладка Добавить новый продукт
   errorn=errorn+1;
   try {
   		assertTrue("Активна вкладка Добавить новый продукт",driver.findElement(By.xpath("//div[@class='dataSwitcher__item dataSwitcher__item_active' and text()='Добавить новый продукт']")).isDisplayed());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//Заполнены Тип товара, 
	errorn=errorn+1;
	try {

	assertTrue("Заполнено поле Тип товара",driver.findElement(By.xpath("//label[contains(.,'Тип продукта')]/following-sibling::*//span[@class='StepForm__selectFakeElem' and contains(.,product1)]")).isDisplayed());

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//модель, 
	errorn=errorn+1;
	try {

	assertTrue("Заполнено поле модель",driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::*//span[@class='StepForm__selectFakeElem' and contains(.,model1)]")).isDisplayed());

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//PNC, 
	errorn=errorn+1;
	try {

	assertTrue("Заполнено поле PNC",driver.findElement(By.xpath("//label[contains(.,'PNC')]/following-sibling::*//span[@class='StepForm__selectFakeElem' and contains(.,PNC1)]")).isDisplayed());

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//серийный номер, 
	errorn=errorn+1;
	try {


	assertEquals("Заполнено поле серийный номер",serial1, driver.findElement(By.name("$.products[0].serial")).getAttribute("value"));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//год покупки, 
	errorn=errorn+1;
	try {


	assertEquals("Заполнено поле год покупки",year1, driver.findElement(By.name("$.products[0].year")).getAttribute("value"));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//цена, 
	errorn=errorn+1;
	try {


			assertEquals("Заполнено поле цена",price1, driver.findElement(By.name("$.products[0].price")).getAttribute("value"));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//магазин.
	errorn=errorn+1;
	try {

		assertEquals("Заполнено поле магазин",shop1, driver.findElement(By.xpath("//label[contains(.,'Где был куплен')]/following-sibling::*//span[@class='Select-value-label']")).getText());
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//4. Кликнуть по вкладке Выбрать из моих продуктов ->
   
  	((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
	utils.waitForElement("//div[@class='popup__message']", driver);


//5. Кликнуть Да ->
    driver.findElement(By.xpath("//div[contains(.,'Данные о продукте будут удалены')]/following-sibling::*//div[contains(.,'Да')]")).click();
	utils.waitForLoad(driver);
//Активна вкладка Выбрать из моих продуктов
   errorn=errorn+1;
   try {
   		assertTrue("Кликнуть по вкладке Выбрать из моих продуктов ->Кликнуть Да ->Активна вкладка Выбрать из моих продуктов",driver.findElement(By.xpath("//div[@class='dataSwitcher__item dataSwitcher__item_active' and text()='Выбрать из моих продуктов']")).isDisplayed());
   

   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	 

//Заполнены год покупки, цена, магазин.
//цена, 
	errorn=errorn+1;
	try {


			assertEquals("Заполнено поле цена",price1, driver.findElement(By.name("$.products[0].price")).getAttribute("value"));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//магазин.
	errorn=errorn+1;
	try {

	assertTrue("Заполнено поле магазин",driver.findElement(By.xpath("//label[contains(.,'Где был куплен')]/following-sibling::*//span[@class='Select-value-label' and contains(.,shop1)]")).isDisplayed());
	

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
	 //год
	errorn=errorn+1;
	try {


	assertEquals("Заполнено поле год",year1, driver.findElement(By.name("$.products[0].year")).getAttribute("value"));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
//6. Добавить продукт 1 из Мои продукты
	new Select(driver.findElement(By.name("$.products[0].category"))).selectByVisibleText(product0);
    	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).sendKeys(Keys.DELETE);
	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).clear();
	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).sendKeys(model0);
	utils.waitForLoad(driver);
	driver.findElement(By.xpath("//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox']")).sendKeys(Keys.TAB);
	
	new Select(driver.findElement(By.name("$.products[0].number"))).selectByVisibleText(PNC0);
	utils.waitForLoad(driver);
	 driver.findElement(By.name("$.products[0].number")).sendKeys(Keys.TAB);
	


//добавить отзывы 11 и 12 для продукта 1
	driver.findElement(By.xpath("//button[contains(.,'Добавить отзыв')]")).click();
	utils.waitForLoad(driver);
	new Select(driver.findElement(By.name("$.products[0].reviews[0].site"))).selectByVisibleText(site00);
	driver.findElement(By.xpath("//label[contains(.,'Номер бонусной/скидочной карты Магазина')]/following-sibling::input")).sendKeys(cardnumber00);
    driver.findElement(By.xpath("//label[contains(.,'Сcылка на отзыв')]/following-sibling::input")).sendKeys(link00);
	

	driver.setFileDetector(new LocalFileDetector());
    File file = new File("src/test/resources/" + file000);
    driver.findElement(By.name("attachment")).sendKeys(file.getAbsolutePath());
    file = new File("src/test/resources/" + file001);
    driver.findElement(By.name("attachment")).sendKeys(file.getAbsolutePath());

	
	driver.findElement(By.xpath("//label[contains(.,'Выбрать комплимент')]/following-sibling::*//span[@class='Select-arrow']")).click();	
	xPathQuery = String.format("//*[@class='Select-option is-focused' and contains(.,'%s')]", prize00);
	utils.waitForElement(xPathQuery, driver);
	driver.findElement(By.xpath(xPathQuery)).click();
	

	driver.findElement(By.xpath("//button[contains(.,'Добавить отзыв')]")).click();
	utils.waitForElement("//*[contains(.,'Сcылка на отзыв')]", driver);
	new Select(driver.findElement(By.name("$.products[0].reviews[1].site"))).selectByVisibleText(site01);
	driver.findElement(By.xpath("//input[@name='$.products[0].reviews[1].loyaltyCardNumber']")).sendKeys(cardnumber01);
	driver.findElement(By.xpath("//input[@name='$.products[0].reviews[1].reviewUrl']")).sendKeys(link01);	
	
	file = new File("src/test/resources/" + file010);
    driver.findElement(By.name("attachment")).sendKeys(file.getAbsolutePath());
	file = new File("src/test/resources/" + file011);
    driver.findElement(By.name("attachment")).sendKeys(file.getAbsolutePath());
	

	List<WebElement>  prizes = driver.findElements(By.xpath("//label[contains(.,'Выбрать комплимент')]/following-sibling::*//span[@class='Select-arrow']"));
	prizes.get(1).click();
	xPathQuery = String.format("//*[@class='Select-option' and contains(.,'%s')]", prize01);
	utils.waitForElement(xPathQuery, driver);
	driver.findElement(By.xpath(xPathQuery)).click();
	utils.waitForLoad(driver);

//7. Добавить продукт 2, как новый
	driver.findElement(By.xpath("//button[contains(.,'Добавить продукт')]")).click();
	utils.waitForLoad(driver);
	
	
	new Select(driver.findElement(By.name("$.products[1].category"))).selectByVisibleText(product1);
    driver.findElement(By.xpath("(//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox'])[2]")).sendKeys(Keys.DELETE);
    	driver.findElement(By.xpath("(//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox'])[2]")).clear();
    	driver.findElement(By.xpath("(//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox'])[2]")).sendKeys(model1);
    driver.findElement(By.xpath("(//label[contains(.,'Модель')]/following-sibling::div//input[@role='combobox'])[2]")).sendKeys(Keys.TAB);
	
	new Select(driver.findElement(By.name("$.products[1].number"))).selectByVisibleText(PNC1);
	 driver.findElement(By.name("$.products[1].number")).sendKeys(Keys.TAB);


    driver.findElement(By.name("$.products[1].serial")).sendKeys(serial1);
	driver.findElement(By.name("$.products[1].serial")).sendKeys(Keys.TAB);

	  
	driver.findElement(By.name("$.products[1].price")).sendKeys(Keys.DELETE);
	driver.findElement(By.name("$.products[1].price")).clear();
    driver.findElement(By.name("$.products[1].price")).sendKeys(price1);
	driver.findElement(By.name("$.products[1].price")).sendKeys(Keys.TAB);
	
	driver.findElement(By.xpath("(//label[contains(.,'Где был куплен')][1]/following-sibling::div//input[@role='combobox'])[2]")).sendKeys(Keys.DELETE);
	driver.findElement(By.xpath("(//label[contains(.,'Где был куплен')][1]/following-sibling::div//input[@role='combobox'])[2]")).clear();
	driver.findElement(By.xpath("(//label[contains(.,'Где был куплен')][1]/following-sibling::div//input[@role='combobox'])[2]")).sendKeys(shop1);
	utils.waitForLoad(driver);
	driver.findElement(By.xpath("(//label[contains(.,'Где был куплен')][1]/following-sibling::div//input[@role='combobox'])[2]")).sendKeys(Keys.TAB);

	
	driver.findElement(By.xpath("//input[@name='$.products[1].year']")).clear();
	driver.findElement(By.xpath("//input[@name='$.products[1].year']")).sendKeys(year1);
   driver.findElement(By.xpath("//input[@name='$.products[1].year']")).sendKeys(Keys.TAB);
	
//добавить отзывы 21 для продукта 2

   	List<WebElement>  addreviews = driver.findElements(By.xpath("//button[contains(.,'Добавить отзыв')]"));
	addreviews.get(1).click();
	utils.waitForElement("//*[@name='$.products[1].reviews[0].reviewUrl']", driver);
	
	new Select(driver.findElement(By.xpath("//select[@name='$.products[1].reviews[0].site']"))).selectByVisibleText(site10);
	driver.findElement(By.xpath("//select[@name='$.products[1].reviews[0].site']")).sendKeys(Keys.TAB);
	driver.findElement(By.xpath("//input[@name='$.products[1].reviews[0].loyaltyCardNumber']")).sendKeys(Keys.DELETE);
	driver.findElement(By.xpath("//input[@name='$.products[1].reviews[0].loyaltyCardNumber']")).clear();
	driver.findElement(By.xpath("//input[@name='$.products[1].reviews[0].loyaltyCardNumber']")).sendKeys(cardnumber10);
    driver.findElement(By.xpath("//input[@name='$.products[1].reviews[0].loyaltyCardNumber']")).sendKeys(Keys.TAB);
	driver.findElement(By.xpath("//input[@name='$.products[1].reviews[0].reviewUrl']")).clear();
	driver.findElement(By.xpath("//input[@name='$.products[1].reviews[0].reviewUrl']")).sendKeys(link10);	
	driver.findElement(By.xpath("//input[@name='$.products[1].reviews[0].reviewUrl']")).sendKeys(Keys.TAB);

		
	prizes = driver.findElements(By.xpath("//label[contains(.,'Выбрать комплимент')]/following-sibling::*//span[@class='Select-arrow']"));
	prizes.get(2).click();
	xPathQuery = String.format("//*[@class='Select-option' and contains(.,'%s')]", prize10);
	utils.waitForElement(xPathQuery, driver);
	driver.findElement(By.xpath(xPathQuery)).click();
	utils.waitForLoad(driver);
		
	file = new File("src/test/resources/" + file100);
  driver.findElement(By.name("attachment")).sendKeys(file.getAbsolutePath());
	file = new File("src/test/resources/" + file101);
   driver.findElement(By.name("attachment")).sendKeys(file.getAbsolutePath());	
	utils.waitForLoad(driver);
	

	
  



//8. Заполнить адрес
	driver.findElement(By.xpath("//a[text()='Далее']")).click();
	utils.waitForElement("//h3[text()='Адрес доставки']", driver);
	//Отметить флаг Я подтверждаю
	driver.findElement(By.xpath("//div[div[@class='customCheckbox'] and label[contains(.,'Я подтверждаю')]]//span")).click();
	//-> есть шаг 2 Адрес доставки, активный, зеленый
	
	errorn=errorn+1;
	try {
    		assertTrue("шаг 2 Адрес доставки, активный, зеленый",driver.findElement(By.xpath("//a[div[text()='Адрес доставки'] and div[text()='заполнено'] and div[@class='StepForm__navNumber StepForm__navNumber_complete StepForm__navNumber_cur']]")).isDisplayed());
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	

//-> кнопка Отправить - доступна
   errorn=errorn+1;
   try {
   		assertTrue("кнопка Отправить доступна",driver.findElement(By.xpath("//button[@class='btn btn-primary StepForm__button_right' and contains(.,'Отправить заявку')]")).isDisplayed());
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	 

	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Город')]]//input")).sendKeys("Кстово");
	utils.waitForLoad(driver);
	utils.waitForElement("//label[contains(.,'Город')]/following-sibling::*//div[@class='Select-menu-outer' and contains(.,'Кстово')]", driver);
	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Город')]]//input")).sendKeys(Keys.TAB);

	
	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Улица')]]//input")).sendKeys("Полевая");
	utils.waitForLoad(driver);
	utils.waitForElement("//label[contains(.,'Улица')]/following-sibling::*//div[@class='Select-menu-outer' and contains(.,'Полевая')]", driver);
	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Улица')]]//input")).sendKeys(Keys.TAB);

	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Дом')]]//input")).sendKeys("1");
	utils.waitForLoad(driver);
 	utils.waitForElement("//label[contains(.,'Дом')]/following-sibling::*//div[@class='Select-menu-outer' and contains(.,'д. 1')]", driver);
	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Дом')]]//input")).sendKeys(Keys.TAB);
	
	
	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Квартира')]]//input")).sendKeys("123");	 
 	utils.waitForElement("//div[@class='form-field' and label[contains(.,'Квартира')]]//input[@value='123']", driver);
	driver.findElement(By.xpath("//div[@class='form-field' and label[contains(.,'Квартира')]]//input")).sendKeys(Keys.TAB);
	utils.waitForLoad(driver);

//9. Отправить форму ->
	driver.findElement(By.xpath("//button[@class='btn btn-primary StepForm__button_right' and contains(.,'Отправить заявку')]")).click();
	utils.waitForLoad(driver);
	//((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-250)", "");



//появился счетчик, отсчет от текущего момента
		   errorn=errorn+1;
   try {


		assertThat("Отправить форму -> появился счетчик, отсчет от текущего момента", driver.findElement(By.xpath("//div[@class='react-cntdwn-timer']")).getText(), containsString("9дн 23ч"));
  
   } catch (Error e) {
       utils.errorList(errorn,e);
     }



//10. Открыть форму заново
	driver.get(baseUrl + "campaigns/1");
	utils.waitForElement("//h1[contains(.,'Комплимент за отзыв')]", driver);
//нет формы
//отображается статус и счетчик
		   errorn=errorn+1;
   try {


		assertThat("Отправить форму -> появился счетчик, отсчет от текущего момента", driver.findElement(By.xpath("//div[@class='react-cntdwn-timer']")).getText(), containsString("9дн 23ч"));
  
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
//нет попапа с ошибкой  
   errorn=errorn+1;
   try {
   		assertFalse("нет попапа с ошибкой ",utils.isElementPresent(By.xpath("//div[@class='modalMessage modalMessage_error']"),driver));
   
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
