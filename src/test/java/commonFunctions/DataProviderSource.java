package commonFunctions;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class DataProviderSource {

	private int iTestCaseRow;

	@DataProvider(name = "TestData")
	public Object[][] getTestData(Method method) throws Exception {

		ConfigFileReader configFileReader = new ConfigFileReader();
		String testCase = method.getName();
		String FullPath = configFileReader.getExcelFilePath() + configFileReader.getExcelFileName();
		String sheetName = null;
        System.out.println(testCase);
        
        Object[][] testObjArray= null;
        if (testCase.toLowerCase().contains("contactus")) {
			sheetName = "InputData";
			testObjArray=getTestData(FullPath,sheetName,testCase ) ;
			return (testObjArray);
			
		} 
        
        return (testObjArray);
             
	}
	
	
	
	private Object[][] getTestData(String FullPath,String sheetName,String testCase ) throws Exception{
		System.out.println(sheetName);
		ExcelUtils.setExcelFile(FullPath, sheetName);
		iTestCaseRow = ExcelUtils.getRowContains(testCase, 0);
		System.out.println("iTestCaseRow ="+iTestCaseRow);
		Object[][] testObjArray = ExcelUtils.getTableArray(FullPath, sheetName,iTestCaseRow);
		return (testObjArray);
	}

	
}
