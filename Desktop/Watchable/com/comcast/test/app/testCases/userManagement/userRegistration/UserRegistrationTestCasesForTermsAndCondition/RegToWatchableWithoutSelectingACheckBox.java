package comcast.test.app.testCases.userManagement.userRegistration.UserRegistrationTestCasesForTermsAndCondition;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.userRegistration.UserRegistrationUsingComcast;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userRegistration.common.UserRegistrationFunction;

/**
 * Class Name: RegToWatchableWithoutSelectingACheckBox Description: This test
 * case registers to Watchable application by entering valid credentials without
 * accepting Terms And Conditions.
 * **/

public class RegToWatchableWithoutSelectingACheckBox extends BaseTest {

	UserRegistrationFunction userRegFun = new UserRegistrationFunction();
	UserRegistrationUsingComcast userReg = new UserRegistrationUsingComcast();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testRegToWatchableWithoutSelectingACheckBox() throws Exception {

		driver.manage().deleteAllCookies();

		driver.get(DataServiceProperties.APPURL);
		try {
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.click();

			// This method is user to enter registration fields details
			userRegFun.RegistrationDetails(driver, proUtil.getProperty("ZIP"),
					proUtil.getProperty("REG_PASSWORD"),
					proUtil.getProperty("REG_PASSWORD"));

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_EMPTYTERMS_CHEKBOX_WARNMSG_XPATH))
					.getText()
					.equalsIgnoreCase(
							UILablesRepo.SIGNUPPAGE_EMPTYTERMS_CHEKBOX_WARNMSG));

			// This method asserts Home and My Channels inactive link when user
			// clicks on Bundle/Channel/Show.
			assertionFunction.assertAllInActiveLink();

			// This method asserts Gazeebo Logo.
			assertionFunction.assertWatchableLogo();

			// This method is to assert Join Watchable Header in Sign Up page.
			assertionFunction.assertJoinWatchableBanner();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
