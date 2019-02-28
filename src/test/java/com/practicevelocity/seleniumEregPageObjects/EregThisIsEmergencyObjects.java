package com.practicevelocity.seleniumEregPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * @author tcammon
 *
 */
public class EregThisIsEmergencyObjects {
	
private static WebElement element = null;
	
	public static WebElement get_pin(WebDriver driver){

		element = driver.findElement(By.xpath("//input[@name='Pin']"));
		return element;
	}
	
	public static WebElement insert_pin(WebDriver driver){

		element = driver.findElement(By.xpath("//input[@name='Pin']"));
		return element;
	}
	
	public static WebElement yesEmergency_btn(WebDriver driver){
		
		element = driver.findElement(By.id("NavigationSequence_Button1SequenceID"));
		return element;
	}
	
	public static WebElement SeeReceptionist_btn(WebDriver driver){

		element = driver.findElement(By.id("NavigationSequence_Button1SequenceID"));
		return element;
	}
	
	
}
