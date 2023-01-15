package com.demoProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	// declared local driver
	WebDriver ldriver;

	// constructor for remote driver
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(name = "uid")
	@CacheLookup
	WebElement userName;

	@FindBy(name = "password")
	@CacheLookup
	WebElement password;

	@FindBy(name = "btnLogin")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//li/a[contains(text(),'Log out')]")
	@CacheLookup
	WebElement logOutLink;

	public void setUserName(String uName) {
		userName.sendKeys(uName);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void clickLoginBtn() {
		loginBtn.click();
	}

	public void clickLogOutLink() {
		logOutLink.click();
	}
}