package pageClasses;

import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/************************Base class for common actions*********************************/

abstract class BasePage {

	
		protected WebDriver driver;
	    private WebDriverWait wait;
        
		//Constructor
		protected BasePage(WebDriver driver)  {
			this.driver = driver;
			wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		}


		//Wait  Method for visibility
				private void waitVisibility(By elementBy)  {
					
					wait.until(ExpectedConditions.elementToBeClickable(elementBy));
					
				}		
		
		
		//Click Method
				protected void click (By elementBy, String logText) {
			
			
			waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				element.click();
			
			
		}
				//click by java script
				
				protected void clickByJs (By elementBy, String logText) {
					
					
					waitVisibility(elementBy);
					WebElement m=driver.findElement(elementBy);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", m);
					
				}
				//get list of element
				public List<WebElement> getListOfElements (By elementBy,String logText) {
					
		            waitVisibility(elementBy);
					
		            List<WebElement> myList=driver.findElements(elementBy);
					
				
				return myList;

			}
				protected void submit (By elementBy, String logText) {
			
			
			waitVisibility(elementBy);
				WebElement element = driver.findElement(elementBy);
				element.submit();
			
			
		}
		
				
		
		
		//Write Text
				protected void writeText (By elementBy, String text, String logText) {
			
				
				WebElement element = driver.findElement(elementBy);
				element.sendKeys(text);
			
			

		}
	
		
	
		//Read Text by
				protected String readText (By elementBy)  {
			
			
			String text=driver.findElement(elementBy).getText();
			waitSec();
			return text;
			
		}
		//Element is displayed
				protected boolean elementDisplayed (By elementBy)  {
		try {	
			driver.findElement(elementBy).isDisplayed();
			return true;
		}
		catch ( org.openqa.selenium.NoSuchElementException e)
		{
			return false;
		}
			
		}
		//get current url
				public String getCurrentURL ()  {
					
					return driver.getCurrentUrl();
					
				}
	//Scroll to element
				protected void scrollByElement(By elementBy) {
					WebElement element=driver.findElement(elementBy);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				}
				
				//wait
				protected void waitSec() {
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				}
	}


