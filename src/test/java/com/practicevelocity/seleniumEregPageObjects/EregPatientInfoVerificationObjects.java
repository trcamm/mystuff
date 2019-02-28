package com.practicevelocity.seleniumEregPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * @author tcammon
 *
 */
public class EregPatientInfoVerificationObjects {
	
private static WebElement element = null;
	
	public static WebElement get_pin(WebDriver driver){

		element = driver.findElement(By.xpath("//input[@name='Pin']"));
		return element;
	}
	
	public static WebElement insert_pin(WebDriver driver){

		element = driver.findElement(By.xpath("//input[@name='Pin']"));
		return element;
	}
	
	public static WebElement noEmergency_btn(WebDriver driver){
		
		element = driver.findElement(By.xpath("//input[@name='NavigationSequence_Button2SequenceID']"));
		return element;
	}
	
	public static WebElement no_photoID(WebDriver driver){

		element = driver.findElement(By.cssSelector("button.btn.btn-default"));
		return element;
	}
	
	public static WebElement no_InsuranceCard(WebDriver driver){

		element = driver.findElement(By.cssSelector("button.btn.btn-default"));
		return element;
	}
	
	public static WebElement nxt_btn(WebDriver driver){

		element = driver.findElement(By.id("NavigationSequence_Button1SequenceID"));
		return element;
	}
	
	public static WebElement back_btn(WebDriver driver){

		element = driver.findElement(By.id("NavigationSequence_Button1SequenceID"));
		return element;
	}
	
	public static WebElement first_name(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_FirstName"));
		return element;
	}
	
	public static WebElement last_name(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_LastName"));
		return element;
	}
	
	public static WebElement gender(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_Gender"));
		return element;
	}
	
	public static WebElement patient_ssn(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_Ssn"));
		return element;
	}
	
	public static WebElement patient_birthdate(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_BirthDate"));
		return element;
	}
	
	public static WebElement patient_address(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_Address1"));
		return element;
	}
	
	public static WebElement patient_zip(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_Zip"));
		return element;
	}
	
	public static WebElement county(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_CountyParish"));
		return element;
	}
	
	public static WebElement patient_race(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_RaceAsList"));
		return element;
	}
	
	public static WebElement patient_ethnicity(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_Ethnicities"));
		return element;
	}
	
	public static WebElement hear_from(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_HearFrom"));
		return element;
	}
	
	public static WebElement alt_location(WebDriver driver){

		element = driver.findElement(By.id("KioskPatient_AlternateLocation"));
		return element;
	}
	
	public static WebElement abort_btn(WebDriver driver){

		element = driver.findElement(By.xpath("//input[@name='abortButton']"));
		return element;
	}
	
	public static WebElement abort_yes(WebDriver driver){

		element = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Are you sure that you want to abort this eRegistration session?'])[1]/following::button[1]"));
		return element;
	}

}
