package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import commonFunctions.DataProviderSource;
import pageClasses.CareerPage;
import pageClasses.CompanyPage;
import pageClasses.HomePage;
import commonFunctions.JiraCreateIssue;

public class MusalaTest extends TestBase {

	
    HomePage homePg;
    CompanyPage companyPg;
    CareerPage careerPg;
    String Actual;
    String Expected;
   
	@BeforeMethod
	
	void initUtlities() {
		
		System.out.println("initUtlities()");
		homePg=new HomePage(driver);
		companyPg=new CompanyPage(driver) ;
		careerPg=new CareerPage(driver);
			
	}



	@Test(description ="contact us with invalid email",dataProvider = "TestData", priority = 1, dataProviderClass = DataProviderSource.class)
	
	
	public void contactUs_withInvalidEmail(String Name,String Email,String Mobile,String Subject,String Messages) throws InterruptedException {
	
		homePg.clickContactUs();
		homePg.enterName(Name);
		homePg.enterEmail(Email);
		homePg.enterMobile(Mobile);
		homePg.enterSubject(Subject);
		homePg.enterMessages(Messages);
		homePg.clickSend();
		Actual=homePg.getEmailValidMsg();
	    Expected="The e-mail address entered is invalid.";
		Assert.assertEquals(Actual, Expected,"Wrong email validation message");
		
	}
	
@Test(description ="Company profile on facebook",priority = 1)
	
	
	public void facebookCompanyPage() throws InterruptedException {
	
		companyPg.clickCompany();
		Actual=companyPg.getCurrentPageURL();
	    Expected="https://www.musala.com/company/";
		Assert.assertEquals(Actual, Expected,"Wrong company link redirection");
	
		Assert.assertEquals(companyPg.leadershipDiplayed(), true,"Leadership section not displayed");
		companyPg.acceptCookie();
		companyPg.clickFacebook();
		companyPg.switchTab();
		Actual=companyPg.getCurrentPageURL();
		System.out.println(Actual);
	    Expected="facebook.com/MusalaSoft?fref=ts";
		Assert.assertEquals(Actual.contains(Expected), true,"Wrong facebook link redirection");
		Assert.assertEquals(companyPg.facebookProfilePicDisplayed(), true,"Facebook muslaa profile pic not displayed");
	}
@Test(description ="apply for a position",priority = 1)


public void applyForPosition() throws InterruptedException {

	careerPg.clickCareer();
	careerPg.clickCheckPosition();
	Actual=careerPg.getCurrentPageURL();
    Expected="https://www.musala.com/careers/join-us/";
	Assert.assertEquals(Actual, Expected,"Wrong join us link redirection");
	careerPg.clickLocationDropDown();
	careerPg.chooseDropDownOption("Anywhere");
	careerPg.clickJob("Automation QA Engineer");
	Assert.assertEquals(careerPg.displayText("General description"), true,"general setion not displayed");
	Assert.assertEquals(careerPg.displayText("Requirements"), true,"Requirements setion not displayed");
	Assert.assertEquals(careerPg.displayText("Responsibilities"), true,"Responsibilities setion not displayed");
	Assert.assertEquals(careerPg.displayText("What we offer"), true,"What we offer setion not displayed");
	careerPg.clickApply();
	careerPg.enterName("aa");
	careerPg.enterEmail("shaimaa");
	careerPg.enterMobile("shaimaaphone");
	careerPg.uploadCV();
	careerPg.checkAgree();
	careerPg.clickSend();
	careerPg.close();
	Actual=careerPg.getEmailValidMsg();
    Expected="The e-mail address entered is invalid.";
	Assert.assertEquals(Actual, Expected,"Wrong email validation mesage");
	Actual=careerPg.getMobileValidMsg();
    Expected="The telephone number is invalid.";
	Assert.assertEquals(Actual, Expected,"Wrong mobile validation message");
	
}
@JiraCreateIssue(isCreateIssue=true)
@Test(description ="Check open positions for Sofia and Skopje",priority = 1)


public void checkOpenPositions() throws InterruptedException {

	careerPg.clickCareer();
	careerPg.clickCheckPosition();
	careerPg.clickLocationDropDown();
	careerPg.chooseDropDownOption("Sofia");
	careerPg.getAvailableJob();
	careerPg.clickLocationDropDown();
	careerPg.chooseDropDownOption("Skopje");
	careerPg.getAvailableJob();
	
}
}
