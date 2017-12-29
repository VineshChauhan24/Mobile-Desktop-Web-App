package comcast.test.app.testCases.providerPage;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.app.testCases.providerPage.providerPageFunctions.providerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickingOnHomeFooterLinkFromProviderPageNavigatesHomePage
 * Description: This test case click on home footer from provider page,
 * navigating back to Home page. Author: Manoj
 * **/

public class VerifyClickingOnHomeFooterLinkFromProviderPageNavigatesHomePage
		extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnHomeFooterLinkFromProviderPageNavigatesHomePage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyClickingOnHomeFooterLinkFromProviderPageNavigatesHomePage");
			log.info("***********************************************************************");

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

			// This method asserts Watchable SHOWS OF THE WEEK title.
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			// Verify Shows present in Watchable SHOWS OF THE WEEK row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				log.info(channelCount
						+ " Shows are present in Watchable SHOWS OF THE WEEK section in home page");

				// Click on first Shows from Watchable SHOWS OF THE WEEK section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to Shows details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// Verify provider name in Shows detail page
				assertTrue(
						"Provider name is not present in Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageProviderTitle_XPATH)));
				log.info("Provider name is present in Shows detail page");

				// Click on Provider title from Shows detail page
				providerPageFun.clickOnProviderLinkFromChannelPage();

				// Verify successfully navigate to Provider details page
				assertionFunction.assertProviderPageTitle();

				// Scroll to Footer
				HomePageCommonFunctions.scrollToFooterSection();
				Thread.sleep(sleepTime);

				// Verify Home Link is present in footer
				assertTrue(
						"Home Link is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
				log.info("Home Link is present");

				// Click on footer Home link from Provider detail page to
				// navigate back
				// to home page

				FooterLinkFun.clickOnHomeLink();
				AssertionRepoFunctions.assertWatchableTitle();
				log.info("Successfully Navigated to Home page after clicking on Home link from Provider page");

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
