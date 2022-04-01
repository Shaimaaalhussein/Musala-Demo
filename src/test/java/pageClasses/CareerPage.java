package pageClasses;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/************************Career page for elements and actions*********************************/

public class CareerPage extends BasePage {

    String city;
	public CareerPage(WebDriver driver) {
		super(driver);
	}
	
By career=By.xpath("//*[@id=\"menu-main-nav-1\"]/li[5]/a");
By checkPositions=By.xpath("(//a[contains(@href,'join')])[2]");
By locationDropDown=By.id("get_location");
By apply=By.xpath("//input[@value='Apply']");
By name=By.id("cf-1");
By email=By.id("cf-2");
By mobile=By.id("cf-3");
By uploadCV=By.id("uploadtextfield");
By agreePolicyCheck=By.id("adConsentChx");
By send=By.xpath("//input[@value='Send']");
By close=By.xpath("//button[text()='Close']");
By jobTitle=By.xpath("//h2[@class='card-jobsHot__title']");
By url=By.xpath("//a[@class='card-jobsHot__link']");
By emailValidMsg=By.xpath("//input[@id='cf-2']//following::span[1]");
By mobileValidMsg=By.xpath("//input[@id='cf-3']//following::span[1]");
public void clickCareer () {
		clickByJs(career, "click career");
	}
public void clickCheckPosition () {
	click(checkPositions, "click check position");
}
public String getCurrentPageURL () {
	return getCurrentURL();
}

public void clickLocationDropDown() {
	click(locationDropDown, "click loaction dropdown");
}
public void chooseDropDownOption(String Text) {
	By option=By.xpath("//option[text()='"+Text+"']");
	city=Text;
	click(option, "click option dropdown");
}

public void clickText(String Text) {
	By textLoc=By.xpath("//*[text()='"+Text+"']");
	click(textLoc, "click text");
}
public void clickJob(String Text) throws InterruptedException {
	       
			By textLoc=By.xpath("(//img[@alt='"+Text+"'])[1]");
			clickByJs(textLoc, "click text");
}
public boolean displayText(String Text) {
	By textLoc=By.xpath("//*[text()='"+Text+"']");
	return elementDisplayed(textLoc);
}
public void clickApply() {
	
	clickByJs(apply, "click apply");
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

public void uploadCV() {
	
	writeText(uploadCV,System.getProperty("user.dir")+"cv.pdf","upload cv");
}
public void checkAgree() {
	
	click(agreePolicyCheck, "check agree");
}
public void clickSend() {
	
	click(send,"click send");
}
public void close() {
	
	click(close,"click close");
}
public String getEmailValidMsg () throws InterruptedException {
	Thread.sleep(5000);
	String text= readText(emailValidMsg);
	   return text;
 }
public String getMobileValidMsg () throws InterruptedException {
	Thread.sleep(5000);
	String text= readText(mobileValidMsg);
	   return text;
 }
public void getAvailableJob() {
	List<WebElement> ListJob = getListOfElements(jobTitle, "get JOB title");
	List<WebElement> ListURL = getListOfElements(url, "get url");
	 System.out.println(city);
	for (int i=0;i<ListJob.size();i++)
	{
	 System.out.println("Position: "+ListJob.get(i).getText());
	 System.out.println("More info: "+ListURL.get(i).getAttribute("href"));
	}
	 System.out.println("...........................");
}
}

