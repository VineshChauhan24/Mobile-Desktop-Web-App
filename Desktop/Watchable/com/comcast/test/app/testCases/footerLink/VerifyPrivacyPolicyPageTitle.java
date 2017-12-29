package comcast.test.app.testCases.footerLink;

//import static org.junit.Assert.assertTrue;

import org.junit.Test;
//import org.openqa.selenium.By;

//import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
////import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
//import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPrivacyPolicyPageTitle Description: This test case verify
 * the title of Privacy Policy page. Author: Manoj
 * 
 * **/

public class VerifyPrivacyPolicyPageTitle extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPrivacyPolicyPageTitle() throws Exception {

		try {

			log.info("Script: VerifyPrivacyPolicyPageTitle");
			log.info("**************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Click on Privacy Policy Link
			FooterLinkFun.clickOnPrivacyPolicyLink();

			// Verify Help page title
			AssertionRepoFunctions.assertPrivacyPolicyPageTitle();
			log.info("The Privacy Policy page title displayed is '"
					+ driver.getTitle() + "'");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
