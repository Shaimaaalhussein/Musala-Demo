package commonFunctions;


import java.io.IOException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import tests.TestBase;


public class TestListeners implements ITestListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
    
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String logtext = "<b>Test Method" + result.getMethod().getMethodName() + "Successful<b>";
		WebDriver driver = ((TestBase)result.getInstance()).driver;
	    CaptureScreenshot.takeScreenshot(driver, result.getMethod().getMethodName());
		MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, "Test Case PASSED is " + result.getMethod().getMethodName());
		
		
	}

	
		public void onTestFailure(ITestResult result) {
			// TODO Auto-generated method stub
			String MethodName= result.getMethod().getMethodName();
			
			WebDriver driver = ((TestBase)result.getInstance()).driver;
			
			String path = CaptureScreenshot.takeScreenshot(driver, result.getMethod().getMethodName());
			String logText = "<b>Test Method " + MethodName + " Failed</b>";
		    MarkupHelper.createLabel(logText, ExtentColor.RED);
			extentTest.get().log(Status.FAIL, "Test Case Failed is " + result.getMethod().getMethodName());
			extentTest.get().log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
			try {
				extentTest.get().addScreenCaptureFromPath(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 boolean islogIssue = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraCreateIssue.class).isCreateIssue();

		        if (islogIssue) {

		//Provide proper Jira project URL ex: https://browsertack.atlassian.net 

		//Jira User name ex: browserstack@gmail.com

		//API token copy from Jira dashboard ex: lorelimpusm12uijk

		//Project key should be, Short name ex: BS


		            JiraServiceProvider JiraServiceProvider = new JiraServiceProvider("https://shaimaa.atlassian.net",

		                    "shaimaaelhosen1@gmail.com", "SlQyRlynkFugLDupJsdz7F11", "SWJ");



		            String issueDescription = "Failure Reason from Automation Testing\n\n" + result.getThrowable().getMessage()

		                    + "\n";

		            issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));




		            String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()

		                    + " Failed in Automation Testing";

		            

		            JiraServiceProvider.createJiraIssue("Bug", issueSummary, issueDescription, "Automated Testing");
		        }

		}
	

		public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
            WebDriver driver = ((TestBase)result.getInstance()).driver;
			
			String path = CaptureScreenshot.takeScreenshot(driver, result.getMethod().getMethodName());
			String logtext = "<b>Test Method" + result.getMethod().getMethodName() + "Skipped<b>";
			MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
			extentTest.get().log(Status.SKIP, "Test Case Skipped is " + result.getMethod().getMethodName());
			try {
				extentTest.get().addScreenCaptureFromPath(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		if (extent != null) {
			extent.flush();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
		

		

}
