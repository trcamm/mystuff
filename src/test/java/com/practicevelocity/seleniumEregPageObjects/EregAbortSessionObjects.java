package com.practicevelocity.seleniumEregPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author tcammon
 *
 */
public class EregAbortSessionObjects {
	
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
	
	public static WebElement abort_btn(WebDriver driver){

		element = driver.findElement(By.xpath("//input[@name='abortButton']"));
		return element;
	}
	
	public static WebElement abort_yes(WebDriver driver){

		element = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Are you sure that you want to abort this eRegistration session?'])[1]/following::button[1]"));
		return element;
	}

}
