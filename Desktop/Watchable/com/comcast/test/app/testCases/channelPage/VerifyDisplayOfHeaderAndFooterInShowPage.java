package comcast.test.app.testCases.channelPage;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyDisplayOfHeaderAndFooterInShowPage Description: This test
 * case verifies header and footer sections are present in show details page.
 * Author: Manoj
 * **/

public class VerifyDisplayOfHeaderAndFooterInShowPage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDisplayOfHeaderAndFooterInShowPage() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyDisplayOfHeaderAndFooterInShowPage");
			log.info("***********************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Watchable SHOWS OF THE WEEK
			// Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify shows present in Watchable SHOWS OF THE WEEK row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				// Click on first show from Watchable SHOWS OF THE WEEK section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// Verify header section is present in show details page
				assertTrue(
						"Header section is NOT present in show details page",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.header_XPATH)));
				log.info("Header section is present in show details page");

				// Scroll to Footer
				HomePageCommonFunctions.scrollToFooterSection();
				Thread.sleep(sleepTime);

				// Verify footer section is present in show details page
				assertTrue(
						"Footer section is NOT present in show details page",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.footer_XPATH)));
				log.info("Fotter section is present in show details page");

				// Verifying Footer Copy Right is present in show details page
				Thread.sleep(LessSleepTime);
				assertTrue(
						"Copyright text is not present in in Sshow details page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.footerCopyRightText_XPATH)));
				log.info("Copyright text is present in in show details page");

				log.info("");

			} else {
				log.error("Shows are not present in Watchable SHOWS OF THE WEEK section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
