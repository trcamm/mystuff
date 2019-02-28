package com.practicevelocity.seleniumEregPageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author tcammon
 *
 */
public class EregPatientIDInsCardObjects {

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
	
	public static WebElement frontPatID_btn(WebDriver driver){

		element = driver.findElement(By.cssSelector("button.btn.btn-action"));
		return element;
	}
	
	public static WebElement frontPatID_image(WebDriver driver){

		element = driver.findElement(By.id("Front"));
		return element;
	}
	
	public static WebElement backPatID_btn(WebDriver driver){

		element = driver.findElement(By.cssSelector("button.btn.btn-action"));
		return element;
	}
	
	public static WebElement backPatID_image(WebDriver driver){

		element = driver.findElement(By.id("Back"));
		return element;
	}
	
	public static WebElement frontInsCard_btn(WebDriver driver){

		element = driver.findElement(By.cssSelector("button.btn.btn-action"));
		return element;
	}
	
	public static WebElement frontInsCard_image(WebDriver driver){

		element = driver.findElement(By.id("Front_0"));
		return element;
	}
	
	public static WebElement backInsCard_btn(WebDriver driver){

		element = driver.findElement(By.cssSelector("button.btn.btn-action"));
		return element;
	}
	
	public static WebElement backInsCard_image(WebDriver driver){

		element = driver.findElement(By.id("Back_0"));
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
