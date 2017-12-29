package comcast.test.app.testCases.userManagement.userRegistration;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyRegistrationPageDetails Description: This test case is to
 * verify Registration page details without logging into Watchable application.
 * **/

public class VerifyRegistrationPageDetails extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	String pageTitle = "";

	@Test
	public void testVerifyRegistrationPageDetails() throws Exception {

		try {

			// Opening application
			driver.get(proUtil.getProperty("HOMEAPPURL"));
			Thread.sleep(sleepTime);
			pageTitle = driver.getTitle();
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				log.info("Successfully Opened the application");

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method asserts Watchable Logo.
				// assertionFunction.assertGazeeboLogo();

				// Click on Banner Image.
				driver.findElement(
						By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH)).click();

				// This method is to ensure Sign Up is Active page.
				assertionFunction.assertSignUpActiveLink();

				// This method is to assert Join Gazeebo Header in Sign Up page.
				assertionFunction.assertJoinWatchableBanner();

				// Assert Registration page details
				assertionFunction.assertRegistreationPageDetails();

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();
			}

			// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Welcome to Gazeebo![\\s\\S]*$"));
		} catch (Throwable t) {
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				captureScreenshot();
				collector.addError(t);
			}
		}
	}
}