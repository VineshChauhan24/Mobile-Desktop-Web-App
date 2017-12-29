package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickingOnBannersNavigatesToRegistrationPage Description:
 * This test case is to verify Registration page on click of Banner without
 * logging into Watchable application.
 * **/

public class VerifyClickingOnBannersNavigatesToRegistrationPage extends
		BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnBannersNavigatesToRegistrationPage()
			throws Exception {

		// Open application
		// driver.get(proUtil.getProperty("HOMEAPPURL"));
		driver.get(DataServiceProperties.HOMEAPPURL);

		try {
			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// Click on Banner Image. -
			// Clicking on Banner Image is not going to Registration page now
			// So clicking on 'Sign UP' link from footer and also the image in
			// below object is not present now
			// driver.findElement(By.xpath(".//*[@id='banner']/div/div[1]/div/section/div[1]/div/ul[1]/li/article/div/div/a/img")).click();
			driver.findElement(
					By.xpath(XpathObjectRepo.FOOTER_SIGNUP_LINK_XPATH)).click();

			/*
			 * This method asserts Home and My Channels inactive link when user
			 * clicks on 'My Channels' link before logging into Application.
			 */
			assertionFunction.assertAllInActiveLink();

			// This method is to assert Join Gazeebo Header in Sign Up page.
			// assertionFunction.assertJoinGazeeboBanner();

			// Assert Registration page details
			assertionFunction.assertRegistreationPageDetails();

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
