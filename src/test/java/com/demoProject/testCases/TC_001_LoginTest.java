package com.demoProject.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoProject.pageObjects.LoginPage;

public class TC_001_LoginTest extends BaseClass {

	@Test
	public void loginTest() throws IOException {

		LoginPage lp = new LoginPage(driver);

		lp.setUserName(userName);
		logger.info("username is entered");
		lp.setPassword(password);
		logger.info("password is entered");
		lp.clickLoginBtn();
		logger.info("clicked on Login button");

		if (driver.getTitle().equals(readConfig.getWebPageTitle())) {
			Assert.assertTrue(true);
			logger.info("web page title is verified successfully");
		} else {
			captureScreen(driver, "Login failed");
			logger.info("web page title verification failed");
			Assert.assertTrue(false);
		}
	}
}
