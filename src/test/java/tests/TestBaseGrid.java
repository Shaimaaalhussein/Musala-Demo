package tests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import commonFunctions.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class TestBaseGrid {
	public ThreadLocal<RemoteWebDriver> driver = null;
    ConfigFileReader configFileReader;
  
	
	@BeforeMethod
	@Parameters(value=("browser"))
	public void setup(String browser) throws InterruptedException, MalformedURLException {
		System.out.println("TestBase.setup()");
		configFileReader=new ConfigFileReader();
		driver=new ThreadLocal<>();
        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setCapability("browserName", browser);	
        //selenium grid
      //  driver.set(new RemoteWebDriver(new URL("http://192.168.1.101:4444"), cap));
        
        //sauce lab grid
        String YourCredintialSauceLab=null;
        String sauceURL="https://"+YourCredintialSauceLab+"@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
        driver.set(new RemoteWebDriver(new URL(sauceURL), cap));
        driver.get().navigate().to(configFileReader.getURL());
		
	}

	
	@AfterMethod
	void tearDown() {
	
		driver.get().quit();
		driver.remove();
	}
	@AfterClass
	public void afterClass() {
	
	}
}
