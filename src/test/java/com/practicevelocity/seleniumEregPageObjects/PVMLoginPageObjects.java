package com.practicevelocity.seleniumEregPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


/**
 * @author tcammon
 *
 */
public class PVMLoginPageObjects {
	
	WebDriver driver = null;
	
	By txtbox_username = By.name("txtLogin");
	By txtbox_pwd = By.name("txtPassword");
	By btn_submit = By.name("btnSubmit");

	//constructor
	public PVMLoginPageObjects(WebDriver driver){
		this.driver = driver;
	}
	//end constructor
	
	public void setUsrname(String text){
		driver.findElement(txtbox_username).sendKeys(text);
	}
	
	public void setPwd(String text){
		driver.findElement(txtbox_pwd).sendKeys(text);
	}
	
	public void setSubmitBtn(){
		driver.findElement(btn_submit).sendKeys(Keys.RETURN);
	}
}
