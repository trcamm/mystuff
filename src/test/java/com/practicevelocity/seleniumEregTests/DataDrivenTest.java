package com.practicevelocity.seleniumEregTests;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
/**
 * @author tcammon
 *
 */
public class DataDrivenTest {
 WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
 
    @BeforeTest
 public void initialization(){

    	String projectPath = System.getProperty("user.dir");
    	
		System.setProperty("webdriver.chrome.driver", projectPath+"/seleniumdrivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
      
		driver.get("https://stpvpm.practicevelocity.com/18_4");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
   
 @Test
 public void fbLoginLogout() throws IOException{
 // Import excel sheet.
 File src=new File("C:/EregFramework/SeleniumEregFramework-master/com.practicevelocity.seleniumEreg/data/TestData.xlsx"); 
 
 // Load the file.
 FileInputStream fis = new FileInputStream(src);
 // Load he workbook.
 workbook = new XSSFWorkbook(fis);
 // Load the sheet in which data is stored.
 sheet= workbook.getSheetAt(0);
 for(int i=1; i<=sheet.getLastRowNum(); i++){
 /*I have added test data in the cell A2 as "testemailone@test.com" and B2 as "password"
 Cell A2 = row 1 and column 0. It reads first row as 0, second row as 1 and so on 
 and first column (A) as 0 and second column (B) as 1 and so on*/ 
 
 // Import data for Email.
 cell = sheet.getRow(i).getCell(0);
 cell.setCellType(Cell.CELL_TYPE_STRING);
 driver.findElement(By.name("txtLogin")).clear();
 driver.findElement(By.name("txtLogin")).sendKeys(cell.getStringCellValue());
 cell = sheet.getRow(i).getCell(1);
 cell.setCellType(Cell.CELL_TYPE_STRING);
 driver.findElement(By.name("txtPassword")).clear();  
 driver.findElement(By.name("txtPassword")).sendKeys(cell.getStringCellValue());
 
 driver.findElement(By.name("btnSubmit")).sendKeys(Keys.RETURN);
 }
 }
 
}
