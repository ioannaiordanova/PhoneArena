package CommentSystem;


import org.testng.annotations.Test;
import org.testng.annotations.*; 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import CommentSystem.LoginForm;
import CommentSystem.commentPageSection;
import org.testng.Reporter;

import java.io.Console;
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
  public static WebDriver driver; 
 
  commentPageSection pageComment;
  LoginForm logInForm;
  

  
  public static final Logger OutLogger = Logger.getLogger(TestNG.class);
  
  
  @DataProvider(name = "Authentication")
  
  public static Object[][] credentials() {
 
	  	
        return XMLUtils.getTableArray();
 
  }
  
 
  
  
  @Test(dataProvider = "Authentication")
  public void testUserPostComment(String sUsername, String sPassword, String sComment) {
	  	
			
			 BasicConfigurator.configure();
		   
			 String path = new java.io.File( "." ).getPath().toString();
			
			//create a FileAppender object and 
		     //associate the file name to which you want the logs
		     //to be directed to.
		     //You will also have to set a layout also, here
		     //We have chosen a SimpleLayout
		     FileAppender fileAppender = new FileAppender();
		     
		     String pathToLOG = new java.io.File( "." ).getAbsolutePath().replace(".", "")+"log/logfile.txt";
		     fileAppender.setFile(pathToLOG);
		     fileAppender.setAppend(false);
		     fileAppender.setLayout(new SimpleLayout());
	 
		     //Add the appender to our Logger Object. 
		     //from now onwards all the logs will be directed
		     //to file mentioned by FileAppender
		     OutLogger.addAppender(fileAppender);
		     fileAppender.activateOptions();
		     
		     driver.findElement(By.xpath("//div[@id='livestream']/div/a[@class='hblock']")).click();
		     OutLogger.info("Going to page of first atricle in livestream");
		   
		     LoginForm logIn = PageFactory.initElements(driver, LoginForm.class);;
		     logIn.LoginUser(sUsername,sPassword);
			        
		     //commentPageSection pageComment = new commentPageSection(sComment,driver);
		     pageComment = PageFactory.initElements(driver, commentPageSection.class);
				 
		     
		     if (!pageComment.isEditMode()) {
				OutLogger.error("User is Logged but CommentBox is not visible ");
			}
			else {
				OutLogger.info("User is Logged and CommentBox is visible");
				
			}
		     pageComment.renderMessage(sComment);
		     
		     pageComment.checkMessageForPresence(sUsername,sComment);
			
			 logIn.LogOutUser(); 
			
		    if (pageComment.isEditMode()) {
				 OutLogger.error("Logout performed but CommentBox is visible ");
			}
			 else {
				OutLogger.info("Logout performed and CommentBox is gone");
			 }
			
  }

  
  @BeforeMethod
  public void beforeMethod() {
		driver = new FirefoxDriver();
		 		
		driver.get("https://www.phonearena.com");
		
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }
		  
 

}
