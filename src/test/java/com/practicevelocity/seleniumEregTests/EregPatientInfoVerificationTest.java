package com.practicevelocity.seleniumEregTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.specification.RequestSpecification;
import com.practicevelocity.seleniumEregPageObjects.EregAbortSessionObjects;
import com.practicevelocity.seleniumEregPageObjects.EregPatientInfoVerificationObjects;
import com.practicevelocity.seleniumEregPageObjects.EregPinAPICheckObjects;

import static com.jayway.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import com.jayway.restassured.authentication.PreemptiveBasicAuthScheme;
import com.jayway.restassured.builder.RequestSpecBuilder;


/**
 * @author tcammon
 *
 */
public class EregPatientInfoVerificationTest {
	
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
	private static WebDriver driver = null;
	private String Pin;
	private long unixTime = System.currentTimeMillis() / 1000L;
	private String unixtimeFormatted = String.valueOf(unixTime);
	private String FinalDate = unixtimeFormatted +"000"+"-"+"0000";
	private String firstName = RandomStringUtils.randomAlphabetic(8).toUpperCase();
	String restAPIUrl= null;
	String apiBody = null;
	String projectPath;
	
	//sauce labs config
	//public static final String USERNAME = "trcammon";
	//public static final String ACCESS_KEY = "a214f868-4101-4408-bec2-4839e96e60f0";
	//public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	@BeforeTest
	public void setupTest() throws Exception{
		
		//sauce labs config
		  //DesiredCapabilities caps = DesiredCapabilities.chrome();
		  //caps.setCapability("platform", "Windows 10");
		  //caps.setCapability("version", "71.0");
		  //caps.setCapability("name", "PatientInfoVerification");
		  //driver = new RemoteWebDriver(new java.net.URL(URL), caps);
	
		projectPath = System.getProperty("user.dir");
	
		//System.setProperty("webdriver.chrome.driver", projectPath+"/seleniumdrivers/chrome/chromedriver.exe");
		
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", projectPath+"/seleniumdrivers/Firefox/geckodriver.exe");    
	       driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@Test
	public void PatientInfoValid() throws JSONException,InterruptedException,IOException {
	
		//Rest API's URL
		restAPIUrl = "https://st2eregapi.practicevelocity.com/18_5/KioskAPI/NewPatientSession/Test/fc2945ce-25aa-dd11-a228-00304853942f/P";
	
		//API Body
		apiBody = "{\"firstName\":\""+ firstName + "\",\"lastName\":\"Automaton\",\"ssn\":\"\",\"sessionSettings\":[{\"settingKey\":\"UsesESign\",\"settingValue\":\"true\"},{\"settingKey\":\"UsesMarketingAltlocation\",\"settingValue\":\"true\"},{\"settingKey\":\"RequireInHouseDispensing\",\"settingValue\":\"N/A\"},{\"settingKey\":\"RequirePreferredPharmacy\",\"settingValue\":\"true\"},{\"settingKey\":\"RequiresVisitCategories\",\"settingValue\":\"true\"},{\"settingKey\":\"ClinicLocalTime\",\"settingValue\":\"2018-05-26T20:49:55.2445873\"},{\"settingKey\":\"eRegSignatureSetting\",\"settingValue\":\"1\"},{\"settingKey\":\"UsesVoicemailAuth\",\"settingValue\":\"true\"},{\"settingKey\":\"PatientPortalRequired\",\"settingValue\":\"true\"},{\"settingKey\":\"EthnicityRequired\",\"settingValue\":\"true\"},{\"settingKey\":\"RaceRequired\",\"settingValue\":\"true\"},{\"settingKey\":\"LanguageRequired\",\"settingValue\":\"true\"},{\"settingKey\":\"RequiredPrimaryPhysician\",\"settingValue\":\"true\"},{\"settingKey\":\"OverrideEregEmergencyContactInfo\",\"settingValue\":\"false\"},{\"settingKey\":\"OverrideVisitCategory\",\"settingValue\":\"false\"}],\"eSignDocuments\":[{\"createdOn\":\"\\/Date("+FinalDate+")\\/\",\"documentTemplatePk\":\"b275094e26f24802ab3eb3dbc596c89b\",\"documentTemplateRootStorageId\":26,\"documentTypeName\":\"HIPAAPolicy\",\"isPracticeDocument\":false},{\"createdOn\":\"\\/Date("+FinalDate+")\\/\",\"documentTemplatePk\":\"8c2c4060ca3648efba5ffb44eb552189\",\"documentTemplateRootStorageId\":26,\"documentTypeName\":\"PatientAuth\",\"isPracticeDocument\":false}],\"sessionDocuments\":[]}";
	
		// Building request by using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();
	
		//Set API's Body
		builder.setBody(apiBody);
	
		//Setting content type as application/json
		builder.setContentType("application/json; charset=UTF-8");
	
		RequestSpecification requestSpec = builder.build();
	
		//Making post request with authentication or leave blank if you don't have credentials like: basic("","")
		com.jayway.restassured.response.Response response = given().authentication().preemptive().basic("PvApiUser", "Bh4LeWLnCByeMUq6")
				.spec(requestSpec).when().post(restAPIUrl);
	
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
	
		//Get the desired value of a parameter
		String result = JSONResponseBody.getString("pin");
	
		//Print the Result
		Pin = result;
		System.out.println(result);
	
		// Navigate to eRegistration Page, verify correct page, enter pin
		driver.get("https://st2ereg.practicevelocity.com/test");
		EregPatientInfoVerificationObjects.get_pin(driver).sendKeys(result);
		EregPatientInfoVerificationObjects.insert_pin(driver).sendKeys(Keys.RETURN);
	    
		// Import excel sheet.
		 File src=new File(projectPath+"/data/TestData.xlsx"); 
		 
		 // Load the file.
		 FileInputStream rc = new FileInputStream(src);

		 // Load the workbook.
		 workbook = new XSSFWorkbook(rc);

		 // Load the sheet in which data is stored.
		 sheet= workbook.getSheetAt(1);		 
		 
		 for(int i=1; i<=sheet.getLastRowNum(); i++){
			 
			//Navigate pass Is Emergency page to PatientID Photo page then abort
			  EregPatientInfoVerificationObjects.noEmergency_btn(driver).click();
			 
			  EregPatientInfoVerificationObjects.no_photoID(driver).click();
			 try {
				 Thread.sleep(3000);
			 } catch (InterruptedException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
			 EregPatientInfoVerificationObjects.no_InsuranceCard(driver).click();
			 try {
				 Thread.sleep(3000);
			 } catch (InterruptedException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
			 EregPatientInfoVerificationObjects.nxt_btn(driver).click();
			 try {
				 Thread.sleep(3000);
			 } catch (InterruptedException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
			 
			 Select dropdown4 = new Select(EregPatientInfoVerificationObjects.gender(driver));
			 dropdown4.selectByIndex(1);
			 try {
				 Thread.sleep(3000);
			 } catch (InterruptedException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
			 
			 cell = sheet.getRow(i).getCell(0);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 EregPatientInfoVerificationObjects.patient_ssn(driver).clear();
			 EregPatientInfoVerificationObjects.patient_ssn(driver).sendKeys(cell.getStringCellValue());
			 
			 cell = sheet.getRow(i).getCell(1);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 EregPatientInfoVerificationObjects.patient_birthdate(driver).clear();
			 EregPatientInfoVerificationObjects.patient_birthdate(driver).sendKeys(cell.getStringCellValue());
			 
			 cell = sheet.getRow(i).getCell(2);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 EregPatientInfoVerificationObjects.patient_address(driver).clear();
			 EregPatientInfoVerificationObjects.patient_address(driver).sendKeys(cell.getStringCellValue());
			
			 cell = sheet.getRow(i).getCell(3);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 EregPatientInfoVerificationObjects.patient_zip(driver).clear();
			 EregPatientInfoVerificationObjects.patient_zip(driver).sendKeys(cell.getStringCellValue());
		 }
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Select dropdown = new Select(EregPatientInfoVerificationObjects.county(driver));
	    dropdown.selectByIndex(1);
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	    Select selectbox= new Select(EregPatientInfoVerificationObjects.patient_race(driver)); 
	    selectbox.selectByIndex(3);   
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Select dropdown2 = new Select(EregPatientInfoVerificationObjects.patient_ethnicity(driver));
	    dropdown2.selectByIndex(1);
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Select dropdown3 = new Select(EregPatientInfoVerificationObjects.hear_from(driver));
	    dropdown3.selectByIndex(2);
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	    Select dropdown5 = new Select(EregPatientInfoVerificationObjects.alt_location(driver));
	    dropdown5.selectByIndex(2);
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    EregPatientInfoVerificationObjects.nxt_btn(driver).click();
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    EregPatientInfoVerificationObjects.back_btn(driver).click();
	    try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    EregPatientInfoVerificationObjects.abort_btn(driver).click();
	    EregPatientInfoVerificationObjects.abort_yes(driver).click();
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Registration Aborted"),"Abort session failed");
	}
	
	@AfterTest
	public void teardownTest(){
		//close browser
		driver.quit();
		System.out.println("session completed successfully check report for test results");
	
	}
	
	

}
