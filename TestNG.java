package CommentSystem;


import org.testng.annotations.*; 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import CommentSystem.Login;
import CommentSystem.CommentBox;
import org.testng.Reporter;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
 
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;



public class TestNG {
  public WebDriver driver; 
  public static final Logger OutLogger = Logger.getLogger(Main.class);
  @Test
  public void f() {
		 
			
			 BasicConfigurator.configure();
		   
		
			//create a FileAppender object and 
		     //associate the file name to which you want the logs
		     //to be directed to.
		     //You will also have to set a layout also, here
		     //We have chosen a SimpleLayout
		     FileAppender fileAppender = new FileAppender();
		     
		     fileAppender.setFile("C:/ToolsQA/PhoneArena/log/logfile.txt");
		     fileAppender.setAppend(false);
		     fileAppender.setLayout(new SimpleLayout());
	 
		     //Add the appender to our Logger Object. 
		     //from now onwards all the logs will be directed
		     //to file mentioned by FileAppender
		     OutLogger.addAppender(fileAppender);
		     fileAppender.activateOptions();
			
		     driver.findElement(By.xpath("//div[@id='livestream']/div/a[@class='hblock']")).click();
		     OutLogger.info("Going to page of first atricle in livestream");
		     
		     Login logIn =new Login();
		     logIn.LoginUser(driver);
			        
			CommentBox commentbox1 = new CommentBox();
			
			if (!commentbox1.isEditMode(driver)) {
				OutLogger.error("User is Logged but CommentBox is not visible ");
			}
			else {
				OutLogger.info("User is Logged and CommentBox is visible");
				
			}
			commentbox1.renderMessage(driver,"");
			commentbox1.checkMessageForPresence(driver, "ioanna", "123");
			
			 logIn.LogOutUser(driver); 
			
			
			 if (commentbox1.isEditMode(driver)) {
				 OutLogger.error("Logout performed but CommentBox is visible ");
			}
			 else {
					OutLogger.info("Logout performed and CommentBox is gone");
			 }
   }

		  
  @BeforeMethod
  public void beforeMethod() {
	  driver = new FirefoxDriver();
	 
	  //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 // OutLogger.info("Implicit wait applied on the driver for 10 seconds");
      
      //Launch the Online Store Website
      driver.get("https://www.phonearena.com");
  }

  @AfterMethod
  public void afterMethod() {
	//  driver.quit();
	  
  }

}
