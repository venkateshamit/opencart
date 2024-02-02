package Utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Data provider 1
	@DataProvider(name="logindata")
	public String[][] testdata() throws Throwable
	{
		String path=".\\Opencart2\\TestData\\Opencart_LoginData.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);// ikkada excel file xlutility loki link or cop aiyindhi
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcells=xlutil.getCellCount("Sheet1", 1);
		
		// ippudu okka emty array store ni java lo open chesthe xecl sheet loni data antha e arrey loki vasthundhi
		String logindata[][]=new String [totalrows][totalcells];//e aray lo exel sheet loni rows colus ki equal ga empy arry sheet ni creat chesinanu
		
		// ila emty arrey sheet loki exel data ni add chesthe ha data antha java memory loki vasthundhi annamata
		//created for two dimension array which can store the data user and password
		
		
		for(int i=1; i<=totalrows;i++)//1
		{
			for(int j=0; j<totalcells; j++ )
			{
				//xlutil getcelldata ni emty logindata ki attched chesunnam
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
			}
		}
		return logindata;//returning two dimension array
	}
	
	//Data provider 2
	
	//Data provider 3

}
