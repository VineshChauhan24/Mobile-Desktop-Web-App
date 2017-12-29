package comcast.test.app.testCases.videoManagement.genres.homePage;

import org.junit.Test;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyGenresSectionDetailsWithoutLogin Description: This test
 * case validates whether Categories, Channels and LastUpdated link are
 * displayed in home page for the genres section by logging registered user into
 * Comcast application.
 * **/

public class VerifyGenresSectionDetailsWithoutLogin extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyGenresSectionDetailsWithoutLogin() throws Exception {

		try {

			driver.get(proUtil.getProperty("HOMEAPPURL"));

			// This method is ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method asserts Categories, Channels and Last Updated links.
			assertionFunction.assertGenresSectionLinks();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
