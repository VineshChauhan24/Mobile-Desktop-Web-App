package comcast.test.app.testCases.channelPage;

import static org.junit.Assert.assertTrue;
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
 * Class Name: VerifyOtherChannelsSectionContent Description: This test cases
 * verifies the contents of other show section show detail page. Author: Manoj
 * **/

public class VerifyOtherChannelsSectionContent extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyOtherChannelsSectionContent() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyOtherChannelsSectionContent");
			log.info("*****************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Watchable shows of the week
			// Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify show present in Watchable shows of the week row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				// Click on first show from Watchable shows of the week section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// This method is to scroll UI to other show Section.
				ChannelPageFun.scrollToOtherChannelsSection();
				Thread.sleep(sleepTime);
				int otherShowPresent = driver
						.findElements(
								By.xpath(XpathObjectRepo.channelPageOtherSectionTitle_XPATH))
						.size();
				if (otherShowPresent > 0) {

					// Verify other show section in show detail page
					assertTrue(
							"Other show section is not present in show detail page",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.channelPageOtherSectionTitle_XPATH)));
					log.info("Other show section is present in show detail page of selected show");

					// Verify Shows present in other show section
					assertTrue(
							"Show is not present in other show sectionin show detail page",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.channelPageOtherSectionChannelRow_XPATH)));
					log.info("Show is present in other Show section in Show detail page");

					int otherChannelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.channelPageOtherSectionChannelIcon_XPATH))
							.size();
					if (otherChannelCount > 0) {

						log.info(otherChannelCount
								+ " Show(s) is present in other Show section on Show detail page");

						log.info("The following Show are present in other Show section on Show page");
						log.info("---------------------------------------------------------------------------");
						for (int i = 1; i <= otherChannelCount; i++) {
							log.info(i
									+ ". "
									+ driver.findElement(
											By.xpath(XpathObjectRepo.channelPageOtherSectionChannelTitle_XPATH
													+ i + "]/descendant::h1/a"))
											.getText());

						}

					}
				} else {
					log.info("Other show section is NOT present in show detail page of selected show");
				}

				log.info("");

			} else {
				log.error("Show are not present in Watchable SHOWS OF THE WEEK section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
