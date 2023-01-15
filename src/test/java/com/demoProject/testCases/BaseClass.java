package com.demoProject.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.demoProject.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicationURL();
	public String userName = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")

	@BeforeClass
	public void setUP(String br) {

		logger = Logger.getLogger("Demo Project");
		PropertyConfigurator.configure("log4j.properties");
		System.out.println("br: " + br);
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.out.println("4");
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			System.out.println("5");
			driver = new FirefoxDriver();
			System.out.println("6");
		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to(baseURL);
		logger.info("application url is opened");
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public String randomstring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}