package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/************************home page for elements and actions*********************************/


public class HomePage extends BasePage {


	public HomePage(WebDriver driver) {
		super(driver);
	}
By contactUS=By.xpath("//*[text()='Contact us']");
By name=By.id("cf-1");
By email=By.id("cf-2");
By mobile=By.id("cf-3");
By subject=By.id("cf-4");
By yourMessages=By.id("cf-5");
By send=By.xpath("//*[@value='Send']");
By emailValidMsg=By.xpath("//input[@id='cf-2']//following::span[1]");

   
public void clickContactUs () {
	   scrollByElement(contactUS);
	   click(contactUS, "click contact us");
       }
public void enterName (String text) {
	   writeText(name,text, "name type");
    }
public void enterEmail (String text) {
	writeText(email,text, "email type");
 }
public void enterMobile (String text) {
	writeText(mobile, text,"mobile type");
 }
public void enterSubject (String text) {
	writeText(subject,text, "subject type");
 }
public void enterMessages (String text) {
	writeText(yourMessages, text,"messages type");
 }
public void clickSend () {
	  
	   submit(send, "click send");
    }
public String getEmailValidMsg () throws InterruptedException {
	Thread.sleep(7000);
	String text= readText(emailValidMsg);
	   return text;
 }
}
