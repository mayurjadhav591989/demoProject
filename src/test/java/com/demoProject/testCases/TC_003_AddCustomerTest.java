package com.demoProject.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoProject.pageObjects.AddCustomerPage;
import com.demoProject.pageObjects.LoginPage;

public class TC_003_AddCustomerTest extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickLoginBtn();

		Thread.sleep(3000);

		AddCustomerPage addcust = new AddCustomerPage(driver);

		addcust.clickAddNewCustomer();

		logger.info("providing customer details....");

		addcust.custName("Mayur");
		addcust.custgender("male");
		addcust.custdob("05", "09", "1989");
		Thread.sleep(5000);
		addcust.custaddress("INDIA");
		addcust.custcity("PUN");
		addcust.custstate("MH");
		addcust.custpinno("411033");
		addcust.custtelephoneno("9503371099");

		String email = randomstring() + "@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();

		Thread.sleep(3000);

		logger.info("validation started....");

		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (res == true) {
			Assert.assertTrue(true);
			logger.info("test case passed....");
		} else {
			logger.info("test case failed....");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}
}