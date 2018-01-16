package CommentSystem;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;


import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.ListIterator;
import CommentSystem.TestNG;



public class Login {
	public void LoginUser(WebDriver driver) {
		
		WebElement div_Links = driver.findElement(By.xpath("//div[@id='Discussions_Container']/div[@id='dialog_holder']"));
		 
		WebElement hrefLogin = div_Links.findElement(By.partialLinkText("login")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hrefLogin);		
		hrefLogin.click();
		TestNG.OutLogger.info("Click Login link");
		
		// Tell webdriver to wait
		WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.id("login_dialog")),ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("login_dialog"))));
    
		WebElement formSignIn = driver.findElement(By.id("login_dialog")); //login_dialog
		
		
		WebElement userNameInput = formSignIn.findElement(By.id("username"));
		userNameInput.sendKeys("ioanna");
		TestNG.OutLogger.info("Username entered in the Username text box");
		

		WebElement passwordInput = formSignIn.findElement(By.id("password"));
		passwordInput.sendKeys("ioannavassileva");
		TestNG.OutLogger.info("Password entered in the Password text box");
		
		formSignIn.findElement(By.id("login_btn")).submit(); 		
		WebDriverWait wait_logout = new WebDriverWait(driver, 30);
		wait_logout.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class,'s_logout')]")),ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(@class,'s_logout')]"))));

		
	}
	
	public void  LogOutUser(WebDriver driver) {
		
		if (driver.findElements(By.xpath("//a[contains(@class,'s_logout')]")).size()!=0) {
			                   
			WebElement logOutHref = driver.findElement(By.xpath("//a[contains(@class,'s_logout')]"));
			                                         
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logOutHref);
			logOutHref.click();
			
		}
	}
	

}
