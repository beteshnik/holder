package elxcrm;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.Properties;
import org.openqa.selenium.remote.*;
import java.io.File;
import java.util.List;



public class HolderBack {
  HolderUtils utils = new HolderUtils();
  private static RemoteWebDriver driver;
  private String baseUrl;
  private String gridUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationerrors = new StringBuffer();
  private String username = System.getProperty("new.username");
  private String moderatorname = System.getProperty("moderator.username"); 
  private String password = System.getProperty("moderator.password");
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
  
  private String xPathString;
  private String testtext = "Заявка типа Приз за отзыв. В админке поля заявки заполнены так, как это сделал пользователь при отправке заявки. ";

  @Before
  public void setUp() throws Exception {
       	baseUrl="http://" + System.getProperty("admin.url") + "." + System.getProperty("base.url");
		gridUrl=System.getProperty("grid.url");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--browser.helperApps.neverAsk.saveToDisk=image/jpg,image/png");
		driver = new RemoteWebDriver(new URL(gridUrl),options);
  		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

  }

  @Test
  public void testHolderBack() throws Exception {
	PageObjectLoginForm loginForm = new PageObjectLoginForm();
	loginForm.init(driver);
//Авторизоваться в админке
	driver.get(baseUrl + "");
	utils.waitForElement("//form",driver);
	loginForm.login(moderatorname,password);
	utils.waitForElement("//h2[text()='Акции']",driver);
//Открыть акцию
	driver.findElement(By.xpath("//a[contains(.,'Комплимент за отзыв')]")).click();
	utils.waitForElement("//table",driver);
//Выбрать последнюю заявку в списке

	driver.findElement(By.xpath("//table//a[1]")).click();
	utils.waitForElement("//div[@class='campaign-document-page']",driver);
	driver.navigate().refresh();
	utils.waitForElement("//div[@class='campaign-document-page']",driver);
//В админке заявка отображается так, как ее заполнил пользователь
   errorn++;
   try {
  		 xPathString = String.format("//label[text()='Email']/following-sibling::*[contains(.,'%1$s')]",username);
   		assertTrue(testtext + "Заявка не принадлежит пользователю теста. Возможно, отправленная заявка отсутствует в админке",driver.findElement(By.xpath(xPathString)).isDisplayed());

   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	

//Кол-во продуктов
	List<WebElement>  products = driver.findElements(By.xpath("//label[text()='Тип продукта']"));
	List<WebElement>  reviews = driver.findElements(By.xpath("//label[text()='Ссылка на отзыв']"));

   errorn++;
   try {
  
   		assertEquals(testtext +"Кол-во продуктов в заявке такое, как задал пользователь",2,products.size());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
//Кол-во отзывов 
	    errorn++;
   try {
  
   		assertEquals(testtext +"Кол-во отзывов в заявке такое, как задал пользователь",3,reviews .size());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
//Тип продуктов	 
   errorn++;
   try {

  
   		assertEquals(testtext +"Тип продукта такой, как задал пользователь",product0,  driver.findElement(By.xpath("//label[@for='$.products[0].category']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Тип продукта такой, как задал пользователь",product1,  driver.findElement(By.xpath("//label[@for='$.products[1].category']/following-sibling::*//div[@class='Select-value']/span")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	 
//Модель
   errorn++;
   try {

  
   		assertEquals(testtext +"Модель, как задал пользователь",model0,  driver.findElement(By.xpath("//label[@for='$.products[0].model']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Модель, как задал пользователь",model1,  driver.findElement(By.xpath("//label[@for='$.products[1].model']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }
	 
//PNC
   errorn++;
   try {

  
   		assertEquals(testtext +"PNC, как задал пользователь",PNC0,  driver.findElement(By.xpath("//label[@for='$.products[0].number']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"PNC, как задал пользователь",PNC1,  driver.findElement(By.xpath("//label[@for='$.products[1].number']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	 


//Серийный номер
   errorn++;
   try {

  
   		assertEquals(testtext +"Серийный номер, как задал пользователь",serial0,  driver.findElement(By.xpath("//label[@for='$.products[0].serial']/following-sibling::*//input")).getAttribute("value"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Серийный номер, как задал пользователь",serial1,  driver.findElement(By.xpath("//label[@for='$.products[1].serial']/following-sibling::*//input")).getAttribute("value"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
//Стоимость	 
	    errorn++;
   try {

  
   		assertEquals(testtext +"Стоимость, как задал пользователь",price0,  driver.findElement(By.xpath("//label[@for='$.products[0].price']/following-sibling::*//input")).getAttribute("value"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Стоимость, как задал пользователь",price1,  driver.findElement(By.xpath("//label[@for='$.products[1].price']/following-sibling::*//input")).getAttribute("value"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	 
	//Год 
	    errorn++;
   try {

  
   		assertEquals(testtext +"Год , как задал пользователь",year0,  driver.findElement(By.xpath("//label[@for='$.products[0].year']/following-sibling::*//input")).getAttribute("value"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Год , как задал пользователь",year1,  driver.findElement(By.xpath("//label[@for='$.products[1].year']/following-sibling::*//input")).getAttribute("value"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	 
//Магазин покупки	 
		    errorn++;
   try {

  
   		assertEquals(testtext +"Магазин покупки, как задал пользователь",shop0,  driver.findElement(By.xpath("//label[@for='$.products[0].store']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Магазин покупки, как задал пользователь",shop1,  driver.findElement(By.xpath("//label[@for='$.products[1].store']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	  
	 
	//Сайт с отзывом 
		    errorn++;
   try {

    		assertEquals(testtext +"Сайт с отзывом, как задал пользователь",site00,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[0].site']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Сайт с отзывом, как задал пользователь",site01,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[1].site']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	   
	   errorn++;
   try {
  
   		assertEquals(testtext +"Сайт с отзывом, как задал пользователь",site10,  driver.findElement(By.xpath("//label[@for='root.products[1].reviews[0].site']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	   
	 	 
	//Номер карты лояльности 

		    errorn++;
   try {

    		assertEquals(testtext +"Номер карты лояльности, как задал пользователь",cardnumber00,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[0].loyaltyCardNumber']/following-sibling::*//input")).getAttribute("value"));
 
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Номер карты лояльности, как задал пользователь",cardnumber01,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[1].loyaltyCardNumber']/following-sibling::*//input")).getAttribute("value"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	   
	   errorn++;
   try {

   		assertEquals(testtext +"Номер карты лояльности, как задал пользователь",cardnumber10,  driver.findElement(By.xpath("//label[@for='root.products[1].reviews[0].loyaltyCardNumber']/following-sibling::*//input")).getAttribute("value"));

   } catch (Error e) {
       utils.errorList(errorn,e);
     }	  	 
	
	//Ссылка на отзыв
		    errorn++;
   try {

    		assertEquals(testtext +"Ссылка на отзыв, как задал пользователь",link00,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[0].reviewUrl']/following-sibling::*//a")).getAttribute("text"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Ссылка на отзыв, как задал пользователь",link01,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[1].reviewUrl']/following-sibling::*//a")).getAttribute("text"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	   
	   errorn++;
   try {
  
   		assertEquals(testtext +"Ссылка на отзыв, как задал пользователь",link10,  driver.findElement(By.xpath("//label[@for='root.products[1].reviews[0].reviewUrl']/following-sibling::*//a")).getAttribute("text"));
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	  	 
	 
	 	//Приз
		    errorn++;
   try {

    		assertEquals(testtext +"Приз, как задал пользователь",prize00,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[0].prize']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	   errorn++;
   try {
  
   		assertEquals(testtext +"Приз, как задал пользователь",prize01,  driver.findElement(By.xpath("//label[@for='root.products[0].reviews[1].prize']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	   
	   errorn++;
   try {
  
   		assertEquals(testtext +"Приз, как задал пользователь",prize10,  driver.findElement(By.xpath("//label[@for='root.products[1].reviews[0].prize']/following-sibling::*//div[@class='Select-value']")).getText());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	 
	 //Адрес
	 //Город прописки
	   errorn++;
   try {
  
   		assertEquals(testtext +"Адрес доставки. Город прописки, как задал пользователь","г. Кстово, обл. Нижегородская, р-н. Кстовский",  driver.findElement(By.xpath("//div[@class='form-group' and contains (.,'Город прописки')]//div[@class='Select-value']")).getText());

   } catch (Error e) {
       utils.errorList(errorn,e);
     }	 
	 //Город прописки
	   errorn++;
   try {
  
   		assertEquals(testtext +"Адрес доставки. Улица, как задал пользователь","ул. Полевая",  driver.findElement(By.xpath("//div[@class='form-group' and contains (.,'Улица')]//div[@class='Select-value']")).getText());

   } catch (Error e) {
       utils.errorList(errorn,e);
     }		 
	 
	 	 //Дом
	   errorn++;
   try {
  
   		assertEquals(testtext +"Адрес доставки. Дом, как задал пользователь","д. 1",  driver.findElement(By.xpath("//div[@class='form-group' and contains (.,'Дом')]//div[@class='Select-value']")).getText());

   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
	 
	 	 	 //Квартира
	   errorn++;
   try {
  
   		assertEquals(testtext +"Адрес доставки. Квартира, как задал пользователь","123",  driver.findElement(By.xpath("//div[@class='form-group' and contains (.,'Квартира')]//input[@class='form-control']")).getAttribute("value"));

   } catch (Error e) {
       utils.errorList(errorn,e);
     }	
//Индекс	
		   errorn++;
   try {
  
   		assertEquals(testtext +"Адрес доставки. Индекс, как задал пользователь","607650",  driver.findElement(By.xpath("//label[text()='Индекс']/following-sibling::input")).getAttribute("value"));

   } catch (Error e) {
       utils.errorList(errorn,e);
     }	 
	 //Аттачи
	 List<WebElement>  files = driver.findElements(By.xpath("//i[@class='icon-download']"));
	 //Кол-во файлов
	    errorn++;
   try {
  
   		assertEquals(testtext +"Кол-во файлов в заявке такое, как задал пользователь",6,files.size());
   
   } catch (Error e) {
       utils.errorList(errorn,e);
     }	


	 
	/*
	 	errorn++;
	try {
	xPathString = String.format("/* /deep/ a#file-link:contains('%s')",file001);  // $("div:contains('Text')")
	assertEquals(testtext + "В заявке есть файл, который привязал пользователь",driver.findElement(By.cssSelector('$'+xPathString)).isDisplayed());
	
	
	xPathString = String.format("//*[contains(.,'%s')]",file000);
	assertTrue(testtext + "В заявке есть файл, который привязал пользователь",driver.findElement(By.xpath(xPathString)).isDisplayed());
	
	//driver.findElement(By.xpath("//button[id='remove']")).click();
	xPathString = String.format("//*[contains(.,'%s')]",file001);
	assertTrue(testtext + "В заявке есть файл, который привязал пользователь",driver.findElement(By.xpath(xPathString)).isDisplayed());
		//driver.findElement(By.xpath("//button[id='remove']")).click();
		xPathString = String.format("//a[text()='%s']",file010);
	assertTrue(testtext + "В заявке есть файл, который привязал пользователь",driver.findElement(By.xpath(xPathString)).isDisplayed());
		//driver.findElement(By.xpath("//button[id='remove']")).click();
		xPathString = String.format("//a[text()='%s']",file011);
	assertTrue(testtext + "В заявке есть файл, который привязал пользователь",driver.findElement(By.xpath(xPathString)).isDisplayed());
		//driver.findElement(By.xpath("//button[id='remove']")).click();
		xPathString = String.format("//a[text()='%s']",file101);
	assertTrue(testtext + "В заявке есть файл, который привязал пользователь",driver.findElement(By.xpath(xPathString)).isDisplayed());
		//driver.findElement(By.xpath("//button[id='remove']")).click();
		xPathString = String.format("//a[text()='%s']",file100);
	assertTrue(testtext + "В заявке есть файл, который привязал пользователь",driver.findElement(By.xpath(xPathString)).isDisplayed());
		//driver.findElement(By.xpath("//button[id='remove']")).click();
    } catch (Error e) {
      	utils.errorList(errorn,e);
      }	
	 
	driver.get("chrome://downloads");
	driver.findElement(By.cssSelector("* /deep/ #moreActions")).click();
	driver.findElement(By.cssSelector("* /deep/ button.dropdown-item.clear-all")).click();
        
*/	 
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
