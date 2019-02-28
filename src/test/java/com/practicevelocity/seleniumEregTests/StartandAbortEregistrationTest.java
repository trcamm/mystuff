package com.practicevelocity.seleniumEregTests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.practicevelocity.seleniumEregPageObjects.EregAbortSessionObjects;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practicevelocity.seleniumEregPageObjects.*;


/**
 * @author tcammon
 *
 */
public class StartandAbortEregistrationTest {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
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
	public void setUp() throws Exception {
		
		//sauce labs config
		//DesiredCapabilities caps = DesiredCapabilities.chrome();
		//caps.setCapability("platform", "Windows 10");
		//caps.setCapability("version", "71.0");
		//caps.setCapability("name", "StartandAbortSession");
		//driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		
		
		projectPath = System.getProperty("user.dir");
				
		System.setProperty("webdriver.chrome.driver", projectPath+"/seleniumdrivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}

	  @Test
	  public void eRegAbortSession() throws Exception {

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
		  EregAbortSessionObjects.get_pin(driver).sendKeys(result);
		  EregAbortSessionObjects.insert_pin(driver).sendKeys(Keys.RETURN);
		  
		  //Navigate pass Is Emergency page to PatientID Photo page then abort
		  EregAbortSessionObjects.noEmergency_btn(driver).click();
		  EregAbortSessionObjects.abort_btn(driver).click();
		  EregAbortSessionObjects.abort_yes(driver).click();
		  try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  Assert.assertTrue(driver.getTitle().contains("Registration Aborted"),"Abort session failed");
		  
	  }
	  
	  @AfterTest
	  public void tearDown() throws Exception {
		  driver.quit();
		  System.out.println("session completed successfully check report for test results");
	  }
	  
}

