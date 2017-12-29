package comcast.config;

import static comcast.util.PropertyFileReader.TextProp;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import comcast.custom.CustomFun;
import comcast.util.PropertyFileReader;

public class BaseTest {

	// public static WebDriver driver;
	public static WebDriver driver;
	public static Logger log;
	public String rootDir = CustomFun.getRootDir();
	public static ITestResult result;
	public static Process process;
	public static String driverName;
	public static String baseUrl;

	@BeforeSuite
	public void suiteSetUp() throws Exception {
		// For logging
		log = Logger.getLogger(this.getClass().getName());
	}

	// @Parameters({"DRIVER"})
	@BeforeTest
	public void setUp() throws Exception {

		// Assigning default value to ITestResult variable
		result = null;

		// ************* Load Property File********************
		PropertyFileReader.loadProprtyFile();

		driverName = TextProp.getProperty("driverName");
		baseUrl = TextProp.getProperty("url");
		
		switch (driverName) {
		case "CHROME":

			String Os = TextProp.getProperty("os");
			if (Os.equalsIgnoreCase("MAC")) {
				System.setProperty("webdriver.chrome.driver",
						"/usr/bin/chromedriver");
			} else {

				if (rootDir.contains("WatchableWebClientNewUI")) {

					System.setProperty("webdriver.chrome.driver", rootDir
							+ "/resources/chromedriver_Windows.exe");

				}

				else {

					System.setProperty(
							"webdriver.chrome.driver",
							rootDir
									+ "/WatchableWebClientNewUI/resources/chromedriver_Windows.exe");

				}
			}
			
			//Disable developer mode extensions pop up
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches","--disable-extensions");
			
			// Initiating the Chrome Driver.
			//driver = new ChromeDriver();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			break;

		case "FF":

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			break;

		case "IE":

			if (rootDir.contains("WatchableWebClientNewUI")) {

				System.setProperty("webdriver.chrome.driver", rootDir
						+ "/resources/IEDriverServer.exe");

			}

			else {

				System.setProperty("webdriver.chrome.driver", rootDir
						+ "/WatchableWebClientNewUI/resources/IEDriverServer.exe");

			}
			driver = new InternetExplorerDriver();
			break;

		case "SFARI":

			driver = new SafariDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			break;
		}

	}

	@AfterTest
	public void tearDown() throws Exception {

		// Quitting the driver once the execution is finished.
		if (driver != null) {
			driver.quit();
		}

	}

}
