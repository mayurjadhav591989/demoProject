package com.demoProject.testCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demoProject.pageObjects.LoginPage;
import com.demoProject.utilities.XLUtils;

public class TC_002_DataDrivenLoginTest extends BaseClass {

	@Test(dataProvider = "Login data")
	public void ddLoginTest(String username, String password) throws InterruptedException, IOException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("username entered");
		lp.setPassword(password);
		logger.info("password entered");
		lp.clickLoginBtn();
		logger.info("clicked on Login button");

		Thread.sleep(2000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			logger.warn("login failed and pop up is dismissed");
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
			lp.clickLogOutLink();
			Thread.sleep(2000);
			logger.info("clicked on log out link");
			driver.switchTo().alert().accept();
			logger.info("logged out and pop up is dismissed");
			driver.switchTo().defaultContent();
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return false;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name = "Login data")
	String[][] getData() throws IOException {
		String xlPath = System.getProperty("user.dir") + "/src/test/java/com/demoProject/testData/LoginData.xlsx";
		int rowcount = XLUtils.getRowCount(xlPath, "Sheet1");
		System.out.println("rowcount: " + rowcount);
		int colcount = XLUtils.getCellCount(xlPath, "Sheet1", 1);
		System.out.println("colcount: " + colcount);
		String loginData[][] = new String[rowcount][colcount];

		for (int i = 1; i <= rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(xlPath, "Sheet1", i, j);
			}
		}
		return loginData;
	}

}
