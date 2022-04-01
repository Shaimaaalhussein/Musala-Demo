package commonFunctions;

		import java.io.FileInputStream;

		import java.io.FileNotFoundException;

		import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;


		import org.apache.poi.xssf.usermodel.XSSFSheet;

		import org.apache.poi.xssf.usermodel.XSSFWorkbook;


    public class ExcelUtils {

			private static XSSFSheet ExcelWSheet;

			private static XSSFWorkbook ExcelWBook;

			private static XSSFCell Cell;
			static int startCol = 0;
			static  int totalCols = 0;
			static   int startRow=0;
			static  int totalRows=0;
			
		//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

		public static void setExcelFile(String Path,String SheetName) throws Exception {

			   try {

					// Open the Excel file

					FileInputStream ExcelFile = new FileInputStream(Path);

					// Access the required test data sheet

					ExcelWBook = new XSSFWorkbook(ExcelFile);

					ExcelWSheet = ExcelWBook.getSheet(SheetName);

					} catch (Exception e){

						throw (e);

					}

			}
		
		public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow)    throws Exception

		{   

		   String[][] tabArray = null;

		   try{

			   FileInputStream ExcelFile = new FileInputStream(FilePath);

			   // Access the required test data sheet

			   ExcelWBook = new XSSFWorkbook(ExcelFile);

			   ExcelWSheet = ExcelWBook.getSheet(SheetName);

			  
			   
			   if (SheetName == "InputData") {
					
					 startCol = 1;
					 totalCols = 5;
					 startRow=1;
					 totalRows=iTestCaseRow-1;
					 
					 tabArray=getTableData(totalRows,totalCols,SheetName);
					}
			  
			   
		 
		   }
			catch (FileNotFoundException e)

			{

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

			}

			catch (IOException e)

			{

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

			}

			return(tabArray);

		}
	
		private static String[][] getTableData(int totalRows,int totalCols,String SheetName) throws Exception {
	    	   

	    	   String[][] tabArray = null;
				  tabArray=new String[totalRows][totalCols];
				
				for  (int j=startCol;j<=totalCols;j++)
				{	    
					for (int i=startRow;i<=totalRows;i++)
					{
						   tabArray[i-1][j-1] =getCellData(i,j);
					}  
				}
				return tabArray;

	    }
 

	
		//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

		public static String getCellData(int RowNum, int ColNum) throws Exception{

		   try{
			   
			   DataFormatter formatter = new DataFormatter();
			
			  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			  String CellData = formatter.formatCellValue(Cell);
			  System.out.println( CellData);
			  return CellData;

			  }catch (Exception e){
				return"";

				}

			}

		

		
		public static int getRowContains(String sTestCaseName, int colNum) throws Exception{

			int i;
			
			try {

				int rowCount = ExcelUtils.getRowUsed();
				System.out.println("Row count ="+rowCount);
				
			
                
				for ( i=0 ; i<=rowCount; i++){

					if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
						System.out.println("test case name="+sTestCaseName);
                        
						
					}
					System.out.println("Row data ="+i);

				}
				return i;

					}catch (Exception e){

				throw(e);

				}

			}

		public static int getRowUsed() throws Exception {

				try{

					int RowCount = ExcelWSheet.getLastRowNum();
             
					return RowCount;

				}catch (Exception e){

					System.out.println(e.getMessage());

					throw (e);

				}

			}

}
