package com.stadiumgood.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.stadiumgoods.com.testrunner.RunCucumberTests;
import org.stadiumgoods.com.testrunner.RunCucumberTests;
import org.testng.annotations.BeforeMethod;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.LogStatus;
import com.stadiumgood.Utils.ReadPropertyFile;

public class Driver extends RunCucumberTests {

	public static WebDriver driver = null;
 
	public  Driver() {
		
	}
	
	public static void compare(String expected, String actual, String Matched) {
		if (expected.equals(actual)) {
			logger.log(LogStatus.PASS, "Actual Result :: " + actual + "Expected Result :: " + expected);
		} else {
			logger.log(LogStatus.FAIL, "Actual Result :: " + actual + "Expected Result :: " + expected);
		}

	}

	public static void compare(String ExpectedResult, List<String> ActualResult) {
		boolean bool = false;
		int index = 0;
		for (String actual : ActualResult) {
			if (ExpectedResult.equals(actual)) {
				bool = true;
				break;
			}
			index++;
		}
		if (bool) {
			logger.log(LogStatus.PASS,
					"Expected Result :: " + ExpectedResult + "Actual Result :: " + ActualResult.get(index));
		} else {
			logger.log(LogStatus.FAIL,
					"Expected Result :: " + ExpectedResult + "Actual Result :: " + ActualResult.get(index));
		}
	}

	public static void LoadConfigProperty() throws IOException {
		config = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "//resources//TestRunDetails.properties");
		config.load(ip);
	}

	public void configureDriverPath() throws IOException {
		if (System.getProperty("os.name").startsWith("Linux")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/linux/geckodriver";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/linux/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}
		if (System.getProperty("os.name").startsWith("Mac")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/geckodriver";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}
		if (System.getProperty("os.name").startsWith("Windows")) {
			String firefoxDriverPath = System.getProperty("user.dir")
					+ "//resources//drivers//windows//geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir")
					+ "//resources//drivers//windows//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}
	}

	public WebDriver openBrowser() throws Exception {
		// loads the config options
		LoadConfigProperty();
		// configures the driver path
		configureDriverPath();
		if (config.getProperty("Browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (config.getProperty("Browser").equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver();
		}
		driver.get(ReadPropertyFile.get("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String takeScreenShot() throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_hh_mm_ss");
		Date date = new Date();
		String currentDate = formatter.format(date);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File trgtFile = new File(System.getProperty("user.dir") + "//screenshots/screenshot" + currentDate + ".png");
		trgtFile.getParentFile().mkdir();
		trgtFile.createNewFile();
		Files.copy(scrFile, trgtFile);
		return System.getProperty("user.dir") + "//screenshots/screenshot" + currentDate + ".png";
	}
}
