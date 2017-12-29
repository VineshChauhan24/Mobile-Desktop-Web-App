package comcast.test.app.common.homePageTestCases.HeaderAndFooterTestCases;

import org.junit.Test;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.LoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFooterLinksInLoginPage Description: This test case
 * validates All Footer Category Links are present in Login page in Gazeebo
 * application
 * **/

public class VerifyFooterLinksInLoginPage extends BaseTest {

	LoginFunctions loginFuntion = new LoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFooterLinksInLoginPage() throws Exception {

		driver.get(DataServiceProperties.APPURL);
		try {
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterCategoryLinks();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable e) {
			captureScreenshot();
			collector.addError(e);
		}
	}

}
