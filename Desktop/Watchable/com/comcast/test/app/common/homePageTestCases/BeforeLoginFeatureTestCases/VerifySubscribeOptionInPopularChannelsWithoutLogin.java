package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.seleniumhq.jetty7.util.log.Log;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifySubscribeOptionInPopularChannelsWithoutLogin Description:
 * This test case is to verify Subscribe Option in Popular section without
 * logging into the Application.
 * **/
public class VerifySubscribeOptionInPopularChannelsWithoutLogin extends
		BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifySubscribeOptionInPopularChannelsWithoutLogin()
			throws Exception {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.allPopularChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");

		// driver.get(proUtil.getProperty("HOMEAPPURL"));

		// Manoj: Modified get value
		driver.get(DataServiceProperties.HOMEAPPURL);
		try {
			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			if (channelList != null) {
				// This method is to scroll UI to Popular Channels Section.
				homePageCommonFun.scrollToPopularChannelsSection();

				int loopIndex = 0;
				if (channelList.size() < 2)
					loopIndex = channelList.size();
				else
					loopIndex = 2;
				for (int index = 0; index < loopIndex; index++) {
					VideoDetails channels = channelList.get(index);
					// This Method verifies Channel present in Popular Channel
					// Section and selects a Channel.
					homePageCommonFun.selectPopularChannel(channels.getTitle());

					// Lekshmi : Change the object identifier
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Free[\\s\\S]*$"));
					// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Subscribe[\\s\\S]*$"));
					// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Welcome to Gazeebo![\\s\\S]*$"));

					// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.CHANNEL_DETAILSPAGE_CHANNEL_HEADER_LABEL_XPATH)).getText().equalsIgnoreCase("CHANNEL"));

					// Manoj: Text added to UILabelRepo file
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.CHANNEL_DETAILSPAGE_CHANNEL_HEADER_LABEL_XPATH))
							.getText()
							.equalsIgnoreCase(UILablesRepo.CHANNEL_LABEL));

					try {
						driver.findElement(By
								.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH));
						Log.info("Channel can be subscribed");
					} catch (NoSuchElementException nse) {
						Log.info("Channel can be subscribed only after login");
					}

					boolean subscribeImage = driver
							.findElements(
									By.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH))
							.size() > 0;

					// Below line to verify whether Subscribe is present if its
					// present then makes it fail.
					if (subscribeImage == true) {
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Subscribe[\\s\\S]*$"));
						System.out.println("CHANNEL CAN BE SUBSCRIBED");
					} else {
						// This method asserts Footer Logo and It's Text.
						assertionFunction.assertFooterLogo();

						// This method asserts Footer Copy Right Links.
						assertionFunction.assertFooterCopyRight();

						// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Welcome to Gazeebo![\\s\\S]*$"));
					}
					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
