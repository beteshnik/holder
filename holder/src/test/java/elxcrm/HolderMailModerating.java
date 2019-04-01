package elxcrm;


import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
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








public class HolderMailModerating {
  HolderUtils utils = new HolderUtils();
  private StringBuffer verificationerrors = new StringBuffer();
  private String message;
  private String host = "pop.gmail.com";
  private String mailStoreType = "pop3";
  private String username = System.getProperty("new.username");
  private String password = System.getProperty("gmail.password");//пароль в почту
  private String userpassword = System.getProperty("user.password");//пароль в личный кабинет
  private int errorn = 0;
  


  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testHolderMailModerating() throws Exception {

	
//1. Создать нового пользователя (или использовать ранее созданного), 
//авторизоваться
//заполнить форму акции и отправить ее ->
//пришло письмо для статуса На одобрении

	message=check(host, mailStoreType, username, password);
	errorn++;
	try {
    	assertThat(message, containsString("благодарим Вас за отзыв"));

    } catch (Error e) {
      	utils.errorList(errorn,e);
      }
	  
    }


 
  
 
   public String check (String host, String storeType, String user,
      String password) 
   {
      String messagetext="";
	  String username=System.getProperty("new.username");
	  String tofield="";
	  int t=0;
	  int timer=50000;
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
	while(messagetext=="" && t<timer)
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
				  		messagetext=bp.getContent().toString();
						}
			}
	 

      //close the folder object
      emailFolder.close(false);
	  Thread.sleep(timerstep);
	  t=t+timerstep;
	 }
	   
	   // close the store object
      store.close();
	  return messagetext;

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
	  return "Не удалось найти или прочитать письмо о том, что заявка на модерации";
   }
  

  
  	@After
	public void tearDown() throws Exception {

		String verificationerrorstring = verificationerrors.toString();
   		if (!"".equals(verificationerrorstring)) {
      		fail(verificationerrorstring);
    	}
	}
  
}
