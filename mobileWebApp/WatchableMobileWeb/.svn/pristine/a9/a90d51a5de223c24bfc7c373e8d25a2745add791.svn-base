package comcast.config;

import static comcast.util.PropertyFileReader.TextProp;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import comcast.custom.CustomFun;
import comcast.util.PropertyFileReader;



public class BaseTest {

	//public static WebDriver driver;
	public static WebDriver driver;
	//public AppiumDriver driver;
	public static Logger log;
	public String rootDir = CustomFun.getRootDir();
	public static ITestResult result;
	public static Process process;
	public static String driverName;
	

	@BeforeSuite
	public void suiteSetUp() throws Exception {
		// For logging
		log = Logger.getLogger(this.getClass().getName());
	}

	//@Parameters({"DRIVER"})
	@BeforeTest
	public void setUp() throws Exception {
		
		// Assigning default value to ITestResult variable 
		result = null;
			
					


		// ************* Load Property File********************
		PropertyFileReader.loadProprtyFile();
		
		driverName= TextProp.getProperty("driverName");
		
		System.out.println("driverName=" + driverName);
		
		switch (driverName) {
		case "CHROME":
			
			
			// Chrome driver set up 
			System.setProperty("webdriver.chrome.driver", rootDir
					+ "/resources/chromedriver.exe");
			
			if (rootDir.contains("WatchableMobileWeb")) {

				System.setProperty("webdriver.chrome.driver", rootDir
						+ "/resources/chromedriver.exe");

			}

			else {
								
				System.setProperty("webdriver.chrome.driver", rootDir
						+ "/WatchableMobileWeb/resources/chromedriver.exe");

			}
				
			
			// Initiating the Chrome Driver.
			driver = new ChromeDriver();
			// Maximizing the webdriver browser window.
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			break;
		case "ANDROID":
			
			
			DesiredCapabilities capabilitiesAndroid = new DesiredCapabilities();
			capabilitiesAndroid.setCapability("browserName", "Chrome");
			// capabilitiesAndroid.setCapability("device", "Android");
			capabilitiesAndroid.setCapability("deviceName", "Android");
			capabilitiesAndroid.setCapability("platformName", "Android");
			// capabilitiesAndroid.setCapability("app-activity",
			// "com.android.chrome.Main");
			Thread.sleep(1000);
			driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"),
					capabilitiesAndroid);

			Thread.sleep(1000);
			driver.manage().deleteAllCookies();
						driver.manage().deleteAllCookies();
						log.info("Appium/nodejs server is initated..!!");
						break;
		}
		
		
		

		
	}

	@AfterTest
	public  void tearDown() throws Exception {

		// Quitting the driver once the execution is finished.
		if (driver != null) {
			driver.quit();
		}
		
	}
	
	
	
	
}

	

