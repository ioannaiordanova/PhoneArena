package CommentSystem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import CommentSystem.TestNG;

public class CommentBox {

	public Boolean isEditMode(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);	
		if (driver.findElements(By.xpath("//span[@class='postcomment']/button[@type='submit']")).size()==1) {
			return true;
			
		}
		else return false;
	}
	
	public void renderMessage(WebDriver driver,String message) {
		WebElement div_container = driver.findElement(By.xpath("//div[@id='Discussions_Container']/div[@id='dialog_holder']"));
		WebElement commentBox = div_container.findElement(By.tagName("textarea"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", commentBox);
		
		commentBox.sendKeys(message);
		div_container.findElement(By.xpath("//span[@class='postcomment']/button[@type='submit']")).submit();
		TestNG.OutLogger.info("Button submit comment is activated");
		
	}
	
	public void checkMessageForPresence(WebDriver driver,String user,String message) {
		if (message.length()<5) {
			//check for error
			if (driver.findElements(By.xpath("//span[@for='content' and class='s_error_message']")).size()==0)
				TestNG.OutLogger.info("Error message for incomplete text shown");
			else 
				TestNG.OutLogger.error("Missing error message for incomplete content");
			
		}
		else {
		
			int linkUserCount= driver.findElements(By.xpath("//blockquote/h4/a[@href='/user/"+user+"']")).size();
			if (linkUserCount == 0) {
				//no comment has been found check for error message
				TestNG.OutLogger.error("Comment has not been posted");
			}
			else {
				WebElement UserHref =	driver.findElements(By.xpath("//blockquote/h4/a[@href='/user/"+user+"']")).get(linkUserCount);
				// UserHref.
				while (UserHref.getTagName()!="blockquote") {
					UserHref = (WebElement) ((JavascriptExecutor) driver).executeScript(
                        "return arguments[0].parentNode;", UserHref);
				
				}
				
				if (UserHref.findElement(By.tagName("p")).getText()!=message) {
					//message is not as expected
					TestNG.OutLogger.error("Comment differs from requested");
				}
				else {
					TestNG.OutLogger.info("Comment posted with success");
				}
				
			}
		}
		
	}
	
}
