package comcast.test.app.testCases.videoManagement.videoSearch.searchVideoByInvalidData;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: VerifySearchResultWithoutEnteringText Description: This test case
 * is used to validate whether search working for without Entering text feature
 * by logging registered user into Watchable application.
 * **/

public class VerifySearchResultWithoutEnteringText extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySearchResultWithoutEnteringText() throws Exception {
		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts Watchable Logo.
			// assertionFunction.assertGazeeboLogo();
			assertionFunction.assertWatchableLogo();

			// This method asserts Help link.
			assertionFunction.assertHelpLink();

			// Lekshmi : Change the object identifier
			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Featured[\\s\\S]*$"));
			/*
			 * assertTrue(driver.findElement(By.xpath(orProUtil.getProperty(
			 * "homeFeaturedContainerLabel_XPATH"
			 * ))).getText().equalsIgnoreCase("FEATURED"));
			 * 
			 * //assertTrue(driver.findElement(By.cssSelector("BODY")).getText().
			 * matches("^[\\s\\S]*Watchable[\\s\\S]*$"));
			 * 
			 * assertTrue(driver.findElement(By.xpath(orProUtil.getProperty(
			 * "youAreWatchingHeaderLabel_XPATH"
			 * ))).getText().equalsIgnoreCase("YOU ARE WATCHING"));
			 * 
			 * Thread.sleep(sleepTime);
			 * //driver.findElement(By.id("go-search")).click();
			 * driver.findElement
			 * (By.xpath(orProUtil.getProperty("homeSearchBoxMenu_XPATH"
			 * ))).click();
			 * assertTrue(driver.findElement(By.xpath(orProUtil.getProperty
			 * ("homeSearchButton_XPATH"))).isEnabled());
			 * 
			 * Thread.sleep(sleepTime);
			 */
			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*[\\s\\S]*$"));
			// Lekshmi : Added
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.HOMEPAGE_FEATURED));

			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Watchable[\\s\\S]*$"));

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH))
					.getText()
					.equalsIgnoreCase(UILablesRepo.HOMEPAGE_YOUAREWATCHING));

			driver.findElement(
					By.xpath(XpathObjectRepo.HOMESEARCHBOXMENU_XPATH)).click();
			assertTrue(driver.findElement(
					By.xpath(XpathObjectRepo.HOMESEARCHBUTTON_XPATH))
					.isEnabled());

			Thread.sleep(sleepTime);

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Watchable Application.
			userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
