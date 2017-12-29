package comcast.test.restAPI;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import comcast.test.config.configServices.utils.DomParserXPATH;
import comcast.test.config.configServices.utils.TestDataGenerator;

public class restAPITestCaseToVerifyRowShowAndVideos extends DomParserXPATH {
	private WebDriver driver;
	public long sleepTime = 2500;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://192.168.136.202:3000/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// DomParserXPATH.getCategories();
	}

	@Test
	public void testVerifyRowShowAndVideos() throws Exception {
		TestDataGenerator proUtil = new TestDataGenerator();
		proUtil.load(new FileInputStream(new File("com/apidata.properties")));

		driver.get(baseUrl + "/login");

		driver.findElement(By.name("user[user_name]")).clear();
		driver.findElement(By.name("user[user_name]")).sendKeys("test_302");

		driver.findElement(By.name("user[password]")).clear();
		driver.findElement(By.name("user[password]")).sendKeys("Demo1111");

		driver.findElement(By.id("user_login")).click();

		Thread.sleep(sleepTime);
		driver.findElement(
				By.xpath(".//*[@id='content-wrap']/div[1]/section/a[2]/span"))
				.click();

		Thread.sleep(sleepTime);
		driver.findElement(
				By.xpath(".//*[@id='content-wrap']/div[1]/section/a[1]/span"))
				.click();

		Thread.sleep(sleepTime);
		driver.findElement(
				By.linkText(proUtil.getProperty("FEATUREDVIDEO_TITLE")))
				.click();

		String FeaturedVideoTitle = (driver.findElement(By
				.xpath(".//*[@id='content-wrap']/article/div/div[2]/h1")))
				.getText();
		asssertEquals(proUtil.getProperty("FEATUREDVIDEO_TITLE"),
				FeaturedVideoTitle);

		// Click on ROWS link
		driver.findElement(By.linkText("ROWS")).click();

		Thread.sleep(sleepTime);
		String ShowVideoTitle = (driver
				.findElement(By
						.xpath(".//*[@id='content-wrap']/article/section/article/div/div[1]/section/ul/li/article/h1/a")))
				.getText();
		asssertEquals(proUtil.getProperty("SHOWVIDEO_TITLE"), ShowVideoTitle);

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Video ID : 167[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		driver.findElement(
				By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[1]/section/a[2]/span"))
				.click();

		Thread.sleep(sleepTime);
		driver.findElement(
				By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[1]/section/a[1]/span"))
				.click();

		// Click on SHOWS link
		driver.findElement(By.linkText("SHOWS")).click();

		Thread.sleep(sleepTime);
		String ShowVideo1 = (driver
				.findElement(By
						.xpath(".//*[@id='content-wrap']/article/section/article/div/div/section/ul/li[1]/article/h1/a")))
				.getText();
		asssertEquals(proUtil.getProperty("SHOWVIDEO_TITLE"), ShowVideo1);

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Brave[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		String ShowVideo4 = (driver
				.findElement(By
						.xpath(".//*[@id='content-wrap']/article/section/article/div/div[1]/section/ul/li/article/h1/a")))
				.getText();
		asssertEquals(proUtil.getProperty("Brave"), ShowVideo4);

		// Click on VIDEO'S link
		driver.findElement(By.linkText("VIDEOS")).click();

		Thread.sleep(sleepTime);
		asssertEquals(driver
				.findElement(By
						.xpath(".//*[@id='content-wrap']/article/section/article/div/div/section/ul/li[1]/article/h1/a")));

		String VideoTitle = (driver
				.findElement(By
						.xpath(".//*[@id='content-wrap']/article/section/article/div/div/section/ul/li[26]/article/h1/a")))
				.getText();
		asssertEquals(proUtil.getProperty("VIDEO_TITLE"), VideoTitle);

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Video ID : 167[\\s\\S]*$"));

		driver.findElement(By.linkText("Log Out")).click();
	}

	private void asssertEquals(WebElement findElement) {
		// TODO Auto-generated method stub

	}

	private void asssertEquals(String string, String title) {
		// TODO Auto-generated method stub

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
