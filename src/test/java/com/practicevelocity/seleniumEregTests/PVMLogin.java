package com.practicevelocity.seleniumEregTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.practicevelocity.seleniumEregPageObjects.*;

/**
 * @author tcammon
 *
 */
public class PVMLogin {
	
	public static final String USERNAME = "tellycammon1";
	public static final String AUTOMATE_KEY = "Cnd8s8r5abqkZC1L5smy";
	public static final String URL="https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	private static WebDriver driver = null;
	
	public static void main(String[] args) throws Exception {
		
		login();
					
	}
	
	public static void login() throws Exception{
		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "70.0");
		caps.setCapability("name", "PVMLoginTest");
		caps.setCapability("browserstack.local", "false");
		caps.setCapability("browserstack.selenium_version", "3.5.2");

	    
	    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	    
		//String projectPath = System.getProperty("user.dir");
		//System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chromedriver/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", projectPath+"/seleniumdrivers/Firefox/geckodriver.exe");
		//WebDriver driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		
	    
	    //added implicit wait to find elements
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
	   
		//go to google dot com
	    driver.get("https://www.google.com");
	    //driver.get("https://stpvpm.practicevelocity.com/18_4");
		//enter text in search textbox
		
	    driver.findElement(By.name("q")).sendKeys("espn");
		//driver.findElement(By.name("txtLogin")).sendKeys("tcammon@admin");
		//PVMLoginObjects.login_username(driver).sendKeys("tcammon@admin");
		
		//driver.findElement(By.name("txtPassword")).sendKeys("Tester9*");
		//PVMLoginObjects.login_pwd(driver).sendKeys("Tester9*");


		//click enter to search
		//driver.findElement(By.name("btnSubmit")).sendKeys(Keys.RETURN);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
		//PVMLoginObjects.login_btn(driver).sendKeys(Keys.RETURN);
		//close browser
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
		driver.quit();
		System.out.println("test comoplete successfully");
		
	}

}
