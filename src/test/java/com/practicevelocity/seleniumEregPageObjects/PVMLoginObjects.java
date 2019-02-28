package com.practicevelocity.seleniumEregPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * @author tcammon
 *
 */
public class PVMLoginObjects {

	
	private static WebElement element = null;
	
	public static WebElement login_username(WebDriver driver){
		
		element = driver.findElement(By.name("txtLogin"));
		return element;
	}
	
	public static WebElement login_pwd(WebDriver driver){

		element = driver.findElement(By.name("txtPassword"));
		return element;
	}
	
	public static WebElement login_btn(WebDriver driver){

		element = driver.findElement(By.name("btnSubmit"));
		return element;
	}

}

