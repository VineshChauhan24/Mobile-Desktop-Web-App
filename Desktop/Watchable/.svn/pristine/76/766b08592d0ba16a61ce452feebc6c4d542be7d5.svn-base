package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyLoginFunctionalityFromHomeURL Description: This test case
 * is to verify Log In page functionality from Home page URL when user clicks on
 * Log In Link it should navigate to Login page.
 * **/

public class VerifyLoginFunctionalityFromHomeURL extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyLoginFunctionalityFromHomeURL() throws Exception {

		List<VideoDetails> videoDetails = RestAPIServices.nhomePageAPIs();

		// driver.get(proUtil.getProperty("HOMEAPPURL"));
		driver.get(DataServiceProperties.HOMEAPPURL);
		try {
			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			Thread.sleep(sleepTime);
			// Lekshmi : Change the object identifier.
			/*
			 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText().
			 * matches("^[\\s\\S]*Featured[\\s\\S]*$"));
			 * assertTrue(driver.findElement
			 * (By.cssSelector("BODY")).getText().matches
			 * ("^[\\s\\S]*"+videoDetails
			 * .get(XidioConstant.selectFeaturedChannel
			 * ).getTitle()+"[\\s\\S]*$"));
			 * 
			 * Thread.sleep(sleepTime);
			 * assertTrue(driver.findElement(By.cssSelector
			 * ("BODY")).getText().matches
			 * ("^[\\s\\S]*Popular Shows[\\s\\S]*$"));
			 * assertTrue(driver.findElement
			 * (By.cssSelector("BODY")).getText().matches
			 * ("^[\\s\\S]*"+videoDetails
			 * .get(XidioConstant.selectPopularShow).getTitle()+"[\\s\\S]*$"));
			 * 
			 * Thread.sleep(sleepTime);
			 * assertTrue(driver.findElement(By.cssSelector
			 * ("BODY")).getText().matches
			 * ("^[\\s\\S]*Popular Channels[\\s\\S]*$"));
			 * assertTrue(driver.findElement
			 * (By.cssSelector("BODY")).getText().matches
			 * ("^[\\s\\S]*"+videoDetails
			 * .get(XidioConstant.selectPopularChannel)
			 * .getTitle()+"[\\s\\S]*$"));
			 */

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.HOMEPAGE_FEATURED));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSTITLELABEL_XPATH))
					.getText()
					.equalsIgnoreCase(UILablesRepo.HOMEPAGE_FEATUREDCHANNELS));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSTITLELABEL_XPATH))
					.getText()
					.equalsIgnoreCase(UILablesRepo.HOMEPAGE_POPULARSHOWS));
			// Navigate to Log In page.
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.click();

			/*
			 * This method asserts Home and My Channels inactive link when user
			 * clicks on 'My Channels' link before logging into Application.
			 */
			assertionFunction.assertAllInActiveLink();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method asserts Help link.
			assertionFunction.assertHelpLink();

			// This method asserts Search Text Box and its value.
			assertionFunction.assertSearchTextBox();

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();

			assertTrue(driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.TOPMENU_SIGNUP));

			// assertEquals("login_username",
			// driver.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH)).getAttribute("id"));
			assertEquals(UILablesRepo.LOGIN_USER_TXTBOX_ID_VALUE, driver
					.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH))
					.getAttribute("id"));

			// assertEquals("login_psd",
			// driver.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH)).getAttribute("id"));
			assertEquals(UILablesRepo.LOGIN_PWD_TXTBOX_ID_VALUE, driver
					.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH))
					.getAttribute("id"));

			assertEquals(
					UILablesRepo.LOGIN_PAGE_LOGIN,
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
							.getText());

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Welcome to Gazeebo![\\s\\S]*$"));

			/*
			 * //Navigate to Log In page.
			 * driver.findElement(By.linkText("Log In")).click();
			 * 
			 * //This method is used to enter user name and password credentials
			 * userLogin.UserLoginCredentials(driver);
			 * 
			 * driver.findElement(By.id("user_login")).click();
			 * 
			 * //This method is to ensure Home is Active page before Login into
			 * Application. assertionFunction.assertHomeActiveLink();
			 * 
			 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText().
			 * matches("^[\\s\\S]*Welcome to Gazeebo![\\s\\S]*$"));
			 */
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
