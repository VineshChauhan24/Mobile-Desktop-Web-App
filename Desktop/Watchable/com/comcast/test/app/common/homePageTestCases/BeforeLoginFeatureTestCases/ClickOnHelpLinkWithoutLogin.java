package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: ClickOnHelpLinkWithoutLogin Description: This test case is to
 * verify Active Help page link without logging into Gazeebo application.
 * **/

public class ClickOnHelpLinkWithoutLogin extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testClickOnHelpLinkWithoutLogin() throws Exception {

		// Manoj: Refactored complete code

		// driver.get(proUtil.getProperty("HOMEAPPURL"));
		driver.get(DataServiceProperties.APPURL);
		try {

			// Additional action added to make 'HOME' link active. Otherwise
			// below assertion will fail

			driver.findElement(
					By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
					.click();

			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// driver.findElement(By.linkText("Help")).click();
			driver.findElement(
					By.xpath(XpathObjectRepo.TOP_MENU_HELP_BUTTON_XPATH))
					.click();

			// This method is to ensure Help is Active page before Login into
			// Application.
			assertionFunction.assertHelpActiveLink();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method asserts Search Text Box and its value.
			assertionFunction.assertSearchTextBox();

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
