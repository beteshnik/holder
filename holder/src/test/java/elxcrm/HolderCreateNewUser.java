package elxcrm;


import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;



public class HolderCreateNewUser {
  HolderUtils utils = new HolderUtils();
  private static RemoteWebDriver driver;
   private String baseUrl;
  private String gridUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationerrors = new StringBuffer();
  private String confirmlink;
  private String host = "pop.gmail.com";
  private String mailStoreType = "pop3";
  private String username = System.getProperty("gmail.username");//логин gmail, будет создан пользователь с таким же именем +текущая дата, письма попадут в тот же ящик
  private String password = System.getProperty("gmail.password");//пароль в почту
  private String userpassword = System.getProperty("user.password");//пароль в личный кабинет
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
  public void testHolderCreateNewUser() throws Exception {


//1. Создать нового пользователя (или использовать ранее созданного), авторизоваться ->
//1. 1. Сформировать email для регистрации
	Date date = new Date();
	long millis = date.getTime();
	String mills = String.valueOf(millis);
	System.setProperty("new.username", username + "+" + mills);
//1.2. Открыть /registration, 
	driver.get(baseUrl + "registration");
	utils.waitForElement("//*[text()='Регистрация']",driver);
	driver.findElement(By.xpath("//div[@class='cookie-sticky-holder fixed ']//div[@class='close-btn icon-cross-white']")).click();
//заполнить корректно все поля, 

	driver.findElement(By.xpath("//div[label[contains(.,'Фамилия')]]//input")).sendKeys("Иванов");
	driver.findElement(By.xpath("//div[label[contains(.,'Имя')]]//input")).sendKeys("Валерий");
	driver.findElement(By.xpath("//div[label[contains(.,'Отчество')]]//input")).sendKeys("Иванович");
	driver.findElement(By.xpath("//div[label[contains(.,'Дата рождения')]]//input")).sendKeys("11.04.1978");
	driver.findElement(By.xpath("//div[label[contains(.,'Email')]]//input")).sendKeys(username + "+" + mills + "@gmail.com");
	driver.findElement(By.xpath("//div[label[contains(.,'Телефон')]]//input")).clear();
	driver.findElement(By.xpath("//div[label[contains(.,'Телефон')]]//input")).sendKeys("9252772383");
	driver.findElement(By.xpath("//div[label[contains(.,'Пароль')]]//input")).sendKeys(userpassword);
	driver.findElement(By.xpath("//div[label[contains(.,'Повторите пароль')]]//input")).sendKeys(userpassword);
	driver.findElement(By.xpath("//div[label[contains(.,'Согласен с правилами')]]//div[@class='customCheckbox']")).click();
	driver.findElement(By.xpath("//div[label[contains(.,'Я даю согласие на обработку')]]//div[@class='customCheckbox']")).click();
	utils.waitForElement("//div[@class='btn btn-primary ' and text()='Регистрация']",driver);

//нажать Регистрация ->
	driver.findElement(By.xpath("//div[text()='Регистрация']")).click();
	utils.waitForLoad(driver);
	driver.findElement(By.xpath("//button[contains(.,'Регистрация')]")).click();
	utils.waitForLoad(driver);
	driver.findElement(By.xpath("//div[h3[contains(.,'Регистрация')]]/i")).click();

//3.1. Перейти по ссылке из письма на почту для подтверждения регистрации. 
	confirmlink=check(host, mailStoreType, username, password);
	driver.get(confirmlink);
	utils.waitForLoad(driver);
	  
  
  }


 
 
   public String check (String host, String storeType, String user,
      String password) 
   {
      String messagelink="";
	  String username=System.getProperty("new.username");
	  String tofield="";
	  int t=0;
	  int timer=120000;
	  int timerstep=2000;
	  try {

	 
      //create properties field
      Properties properties = new Properties();

      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", "995");
      properties.put("mail.pop3.starttls.enable", "true");
      Session emailSession = Session.getDefaultInstance(properties);
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");
      store.connect(host, user, password);
      
      //create the folder object and open it
	  Folder emailFolder = store.getFolder("INBOX");
	  
     //check the last message arrives for the  just created username
	while(messagelink=="" && t<timer)
	{
	
	  emailFolder.open(Folder.READ_ONLY);
	  int last = emailFolder.getMessageCount();
	  if (last>0)
		{
	  	Message message=emailFolder.getMessage(last);
	  	tofield = message.getRecipients(Message.RecipientType.TO)[0].toString();
		if (tofield.indexOf(username) > 1)
			{
	  		Multipart mp = (Multipart) message.getContent();
      		BodyPart bp = mp.getBodyPart(0);
	  		messagelink=bp.getContent().toString();
	  		int indexOf = messagelink.lastIndexOf("http://back");
	  		messagelink = messagelink.substring(indexOf).trim();
	  		indexOf = messagelink.indexOf("\"");
	  		messagelink = messagelink.substring(0, indexOf).trim();
			}

		}
	// close the folder object
    emailFolder.close(false);
	Thread.sleep(timerstep);
	t=t+timerstep;
}

     // close the store object
	  store.close();
	  return messagelink;

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
	  return messagelink;
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
