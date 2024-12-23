package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		
		String path="C:\\Users\\dell\\Downloads\\maven project\\Opencart\\testData\\Opencart_Login details.xlsx";//taking xl filepath fromtestdata folder
		ExcelUtility xlutil=new ExcelUtility(path);//creating object for xl utility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0
			}
		}
		
		return logindata;//returning two dimensional array
	}
	
	//DataProvider 2
	
	//DataProvider 3

}
