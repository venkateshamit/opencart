package Utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilites1 {

	public String path;// path means excel file
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	
	public ExcelUtilites1(String path)// e class mothaniki excel file link chesinanu
	{
		this.path=path;
	}
	public int getRowcount(String sheetname) throws Throwable
	{
		fi=new FileInputStream(path);// loading excel
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int numofrows=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return (numofrows);
	}
	public int getCellcount(String sheetname, int rownumber) throws Throwable
	{
		fi=new FileInputStream(path);// loading excel
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int numofcells=sheet.getRow(rownumber).getLastCellNum();
		workbook.close();
		fi.close();
		return (numofcells);	
	}
	
	public String getCellData(String sheetname,int rownum, int cellnum) throws Throwable
	{
		fi=new FileInputStream(path);// loading excel
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		
		DataFormatter formater=new DataFormatter();
		String data;
		try
		{
			 data=formater.formatCellValue(cell);// if cell lo data unte ok adhi String formate loki convrt aiythndhi
		}   // ikkada cell lo unna any data aiyna sare String formate loki marche syntax one of all 
		
		catch (Exception e) 
		{
			data="";
		}
		workbook.close();
		fi.close();
		return(data);	
	}
	
	public void setCellData1(String sheetname,int rownuber, int cellnumber, String data) throws Throwable
	{
		fi=new FileInputStream(path);// loading excel
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownuber);
		cell=row.createCell(cellnumber);
		cell.setCellValue(data);
		
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		workbook.write(fo);
		}
				
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
			
		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
			workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
					
		if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
				sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
	public void fillGreenClour(String sheetname, int rownum, int cellnum) throws Throwable
	{
		fi=new FileInputStream(path);// loading excel
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		
		style=workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);// doubt
		workbook.close();
		fi.close();
		fo.close();
	}
	public void fillRedClour(String sheetname, int rownum, int cellnum) throws Throwable
	{
		fi=new FileInputStream(path);// loading excel
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		
		style=workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);// doubt
		workbook.close();
		fi.close();
		fo.close();
	}
}
