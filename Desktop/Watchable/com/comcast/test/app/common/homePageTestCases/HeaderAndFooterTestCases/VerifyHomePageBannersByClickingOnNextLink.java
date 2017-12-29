package comcast.test.app.common.homePageTestCases.HeaderAndFooterTestCases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.TestDataGenerator;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: VerifyHomePageBannersByClickingOnNextLink Description: This test
 * case validates Home page Banner Next link functionality by logging registered
 * user into Watchable application.
 * **/

public class VerifyHomePageBannersByClickingOnNextLink extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	TestDataGenerator proUtil = new TestDataGenerator();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyHomePageBannersByClickingOnNextLink()
			throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts Gazeebo Logo.
			assertionFunction.assertWatchableLogo();

			// Below Functionality is not present now, So commenting the code

			// This method is to assert Gazeebo Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu(); - Functionality
			// not present now

			// Click on Welcome To Gazeebo! Link - Functionality not present now
			// driver.findElement(By.xpath(".//*[@id='topmiddle_menu']/div")).click();
			Thread.sleep(sleepTime);

			/*
			 * boolean isBannerPresent;
			 * isBannerPresent=driver.findElements(By.xpath(
			 * ".//*[@id='banner']/div/div[1]/div/section/div[1]/div/ul[1]/li/article/div/div/a/img"
			 * )).size()>0; if(isBannerPresent==true) {
			 * System.out.println("hello"); String
			 * welcomeToGazeeboLinkState=driver.findElement(By.xpath(
			 * ".//*[@id='banner']/div/div[2]/header/nav/ul/li[1]/a"
			 * )).getText(); String
			 * gettingStartedLinkState=driver.findElement(By
			 * .xpath(".//*[@id='banner']/div/div[2]/header/nav/ul/li[2]/a"
			 * )).getText(); String
			 * GazeeboOniPadAndTVLinkState=driver.findElement
			 * (By.xpath(".//*[@id='banner']/div/div[2]/header/nav/ul/li[3]/a"
			 * )).getText();
			 * 
			 * assertEquals("Welcome to Gazeebo",welcomeToGazeeboLinkState);
			 * 
			 * assertEquals("Getting Started",gettingStartedLinkState);
			 * 
			 * assertEquals("Gazeebo on iPad and TV",GazeeboOniPadAndTVLinkState)
			 * ;
			 * 
			 * //Click on banner next link. driver.findElement(By.xpath(
			 * ".//*[@id='banner']/div/div[1]/div/section/a[2]/span")).click();
			 * 
			 * for(int i=0; i<2; i++) { //Click on banner previous link.
			 * driver.findElement
			 * (By.xpath(".//*[@id='banner']/div/div[1]/div/section/a[1]"
			 * )).click(); Thread.sleep(sleepTime); } }
			 * 
			 * //Click on Banner collapse link. Thread.sleep(sleepTime);
			 * driver.findElement
			 * (By.xpath(".//*[@id='banner']/div/div[2]/header/nav/span/a"
			 * )).click();
			 * 
			 * Thread.sleep(sleepTime);
			 * Assert.assertTrue(driver.findElement(By.cssSelector
			 * ("BODY")).getText
			 * ().matches("^[\\s\\S]*Welcome to Gazeebo![\\s\\S]*$"));
			 */
			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
