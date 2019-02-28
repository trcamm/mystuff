package com.practicevelocity.seleniumEregTests;

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
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.specification.RequestSpecification;
import com.practicevelocity.seleniumEregPageObjects.EregAbortSessionObjects;
import com.practicevelocity.seleniumEregPageObjects.EregPinAPICheckObjects;

import static com.jayway.restassured.RestAssured.given;

import java.net.MalformedURLException;

import com.jayway.restassured.authentication.PreemptiveBasicAuthScheme;
import com.jayway.restassured.builder.RequestSpecBuilder;

/**
 * @author tcammon
 *
 */
public class EregPinAPICheck {

	WebDriver firefox;
	WebDriver chrome;
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
		//caps.setCapability("name", "eRegPinAPICheck");
		//driver = new RemoteWebDriver(new java.net.URL(URL), caps);

		projectPath = System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver", projectPath+"/seleniumdrivers/chrome/chromedriver.exe");
		chrome = new ChromeDriver();
	}

	@Test
	public void RestApiSignPin() throws JSONException,InterruptedException {

		//Rest API's URL
		restAPIUrl = "https://steregapi.practicevelocity.com/19_1/KioskAPI/NewPatientSession/qatc/44c0ddba-bfe5-e711-80ca-0050568cab3b/P";

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
		chrome.manage().window().maximize();
		chrome.get("https://stereg.practicevelocity.com/qatc");
		EregPinAPICheckObjects.get_pin(chrome).sendKeys(result);
		EregPinAPICheckObjects.insert_pin(chrome).sendKeys(Keys.RETURN);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(chrome.getTitle().contains("Is this an Emergency?"),"Pin validation failed");
	}


	@AfterTest
	public void teardownTest(){
		//close browser
		chrome.quit();
		System.out.println("session completed successfully check report for test results");

	}


}
