package comcast.test.app.testCases.userManagement.userRegistration.UserRegistrationTestCasesForAllMandatoryFields;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userRegistration.common.UserRegistrationFunction;

/**
 * Class Name: RegToGazeeboWithoutProvidingAnyDetails Description: This test
 * case is to register to Watchable application without entering any details.
 * **/

public class RegToWatchableWithoutProvidingAnyDetails extends BaseTest {

	UserRegistrationFunction useRegFun = new UserRegistrationFunction();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testRegToWatchableWithoutProvidingAnyDetails() throws Exception {

		driver.get(DataServiceProperties.APPURL);

		try {
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.click();

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUP_EMPTYEMAIL_MESSAGE_XPATH))
					.getText()
					.equalsIgnoreCase(UILablesRepo.SIGNUP_EMPTYEMAIL_MESSAGE));

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.ACCOUNT_ZIP_EMPTYFLD_MSG_XPATH))
					.getText()
					.equalsIgnoreCase(UILablesRepo.ACCOUNT_ZIP_EMPTYFLD_MSG));

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_EMPTYPASSWD_WARNMSG_XPATH))
					.getText()
					.equalsIgnoreCase(
							UILablesRepo.SIGNUPPAGE_EMPTYPASSWD_WARNMSG));

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_EMPTYCONFPASSWD_WARNMSG_XPATH))
					.getText()
					.equalsIgnoreCase(
							UILablesRepo.SIGNUPPAGE_EMPTYCONFPASSWD_WARNMSG));

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_EMPTYSECURITYCHK_WARNMSG_XPATH))
					.getText()
					.equalsIgnoreCase(
							UILablesRepo.SIGNUPPAGE_EMPTYSECURITYCHK_WARNMSG));

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_EMPTYTERMS_CHEKBOX_WARNMSG_XPATH))
					.getText()
					.equalsIgnoreCase(
							UILablesRepo.SIGNUPPAGE_EMPTYTERMS_CHEKBOX_WARNMSG));

			// This method asserts Home and My Channels inactive link when user
			// clicks on Bundle/Channel/Show.
			assertionFunction.assertAllInActiveLink();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method is to assert Join Watchable Header in Sign Up page.
			// assertionFunction.assertJoinGazeeboBanner();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
