package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: ClickOnMyChannelsLinkWithoutLogin Description: This test case is
 * to verify after clicking on 'My Channels' link without logging into Comcast
 * application it should navigate to Login page.
 * **/

public class ClickOnMyChannelsLinkWithoutLogin extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testClickOnMyChannelsLinkWithoutLogin() throws Exception {

		// Manoj: Code refactored

		// Opening application
		// driver.get(proUtil.getProperty("HOMEAPPURL"));
		driver.get(DataServiceProperties.HOMEAPPURL);

		try {

			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to navigate My Channels Page.
			subscriptionsPageCommonFun.navigateToMyChannelsPage();

			/*
			 * This method asserts Home and My Channels inactive link when user
			 * clicks on 'My Channels' link before logging into Application.
			 */

			// This method is to ensure Login is Active link.
			assertionFunction.assertLoginActiveLink();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method is to assert all Header links.
			assertionFunction.assertAllHeaderLink();

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();

			// Lekshmi : change the object identifier
			/*
			 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText().
			 * matches("^[\\s\\S]*Sign Up[\\s\\S]*$")); assertEquals("",
			 * driver.findElement
			 * (By.name("user[user_name]")).getAttribute("value"));
			 * assertEquals("",
			 * driver.findElement(By.name("user[password]")).getAttribute
			 * ("value")); assertEquals("Log In",
			 * driver.findElement(By.id("user_login")).getText());
			 */

			assertTrue(driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.TOPMENU_SIGNUP));
			assertEquals(
					UILablesRepo.LOGIN_EMAIL_NAME,
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGIN_EMAIL_FIELD_XPATH))
							.getAttribute("name"));
			assertEquals(
					UILablesRepo.LOGIN_PASSWORD_NAME,
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGIN_PASSWORD_FIELD_XPATH))
							.getAttribute("name"));
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
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
