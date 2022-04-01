package pageClasses;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/************************company page for elements and actions*********************************/

public class CompanyPage extends BasePage {


	public CompanyPage(WebDriver driver) {
		super(driver);
	}
By company=By.xpath("//*[@id=\"menu-main-nav-1\"]/li[1]/a");
By leadership=By.xpath("//*[text()='Leadership']");
By acceptCookies=By.id("wt-cli-accept-all-btn");
By facebookLink=By.xpath("//a[contains(@href,'facebook')]");
By muslaProfilePic=By.xpath("(//*[name()='svg']/*[name()='g']/*[name()='image'])[1]");

public void clickCompany ()  {
	 
	   clickByJs(company, "click company");
    }

public void switchTab(){
	// hold all window handles in array list
    ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
    //switch to new tab
    driver.switchTo().window(newTb.get(1));
    
}
public String getCurrentPageURL () throws InterruptedException {
	String url=getCurrentURL();
	Thread.sleep(5000);
	  return url;
}
public boolean leadershipDiplayed () {
	   
	return   elementDisplayed(leadership);
 }
public void acceptCookie () {
	   
	   click(acceptCookies,"accept cookies");
 }
public void clickFacebook () {
	   
	click(facebookLink, "click facebook");
}

public boolean facebookProfilePicDisplayed () {
	   
	return elementDisplayed(muslaProfilePic);
}
	
}

