package comcast.test.app.testCases.channelPage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickingOnFooterWatchableLogoFromShowPageNavigatesHomePage
 * Description: This test script click on watchable logo from footer from show
 * page, and verifies user navigating back to Home page. 
 * Author: Manoj
 * **/

public class VerifyClickingOnFooterWatchableLogoFromShowPageNavigatesHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();

	@Test
	public void testVerifyClickingOnFooterWatchableLogoFromShowPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickingOnFooterWatchableLogoFromShowPageNavigatesHomePage");
			log.info("************************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Popular Channels Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Click on first channel name from FEATURED CHANNELS section

			HomeFun.clickOnFirstChannelLink();

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify Watchable logo is present in footer
			assertTrue(
					"Watchable logo is not present in footer",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH)));
			log.info("Watchable logo is present in footer");

			// Click on footer Watchable logo from show detail page to
			// navigate back
			// to home page

			HomeFun.clickOnBottomWatchableLogo();

			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on footer Watchable logo from show page");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
