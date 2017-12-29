package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.FeaturedCategoryPageTestCases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.JsonParser;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.UrlFactoryUtil;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyFeaturedChannelWithItsDetails Description: This test case
 * validates whether the channel is displayed under Featured section in Home
 * page and its details by logging registered user into watchable application.
 * **/

public class VerifyFeaturedChannelWithItsDetails extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	List<VideoDetails> publishersDetails;

	@Test
	public void testVerifyFeaturedChannelWithItsDetails() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nFeaturedAPI();
		List<VideoDetails> channelList = videoDetails
				.get("featuredChannelsList");
		String sessionToken = RestAPIServices.executeGenreAuthentication();

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (channelList != null) {
				int loopMaxIndex = 0;
				if (channelList.size() < 4)
					loopMaxIndex = channelList.size();
				else
					loopMaxIndex = 4;

				for (int index = 0; index < loopMaxIndex; index++) {
					VideoDetails channelsDetail = channelList.get(index);
					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(channelsDetail
							.getTitle());

					publishersDetails = new ArrayList<VideoDetails>();

					// This is to get publisher details result
					String publisherResponse = RestAPIServices
							.getSessionTokenResponse(
									UrlFactoryUtil.getInstance()
											.getPublisherDetailsResponse(
													channelsDetail
															.getPublisherURL()),
									sessionToken);
					List<VideoDetails> publisherDetails = JsonParser
							.parsePublisherDetailsResponse(publisherResponse);

					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelsDetail.getTitle()+"[\\s\\S]*$"));

					// Manoj: Modified the Object and added to XpathObjectRepo
					// file
					/*
					 * assertTrue(driver .findElement(
					 * By.xpath(".//*[@id='content-wrap']/article/div/div[2]/h1[2]"
					 * )) .getText().matches(channelsDetail.getTitle()));
					 */

					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILSHOWLIST_XPATH))
							.getText().matches(channelsDetail.getTitle()));

					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channels.getDescription()+"[\\s\\S]*$"));

					// Manoj: Modified the Object and added to XpathObjectRepo
					// file
					/*
					 * assertTrue(driver .findElement(By.cssSelector("BODY"))
					 * .getText() .matches( "^[\\s\\S]*" +
					 * publisherDetails.get(0) .getPublisherName() +
					 * "[\\s\\S]*$"));
					 */

					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.CHANNELDETAILPUBLISHERTITLE_XPATH))
							.getText()
							.matches(publisherDetails.get(0).getPublisherName()));

					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			} else {
				boolean isPresent;
				// Manoj: Object modified and moved to XpathObjectProp file

				/*
				 * isPresent = driver .findElement(
				 * By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"))
				 * .findElements( By.xpath(
				 * ".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"
				 * )) .size() > 0; if (isPresent == true) { String channelName =
				 * driver .findElement(
				 * By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"))
				 * .findElement( By.xpath(
				 * ".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"
				 * )) .getText();
				 * assertFalse(driver.findElement(By.cssSelector("BODY"))
				 * .getText() .matches("^[\\s\\S]*" + channelName +
				 * "[\\s\\S]*$")); }
				 */

				// isPresent=driver.findElement(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]")).findElements(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div")).size()>0;
				isPresent = driver
						.findElement(
								By.xpath(XpathObjectRepo.FEATUREDROWLIST_XPATH))
						.findElements(
								By.xpath(XpathObjectRepo.FEATUREDICONS_XPATH))
						.size() > 0;
				if (isPresent == true) {
					// String
					// channelName=driver.findElement(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]")).findElement(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div")).getText();
					String channelName = driver
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDROWLIST_XPATH))
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDICONSFIRSTTITLE_XPATH))
							.getText();
					assertFalse(driver
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDROWLIST_XPATH))
							.getText().contains(channelName));
				}
			}

			// This method asserts Watchable Logo.
			assertionFunction.assertGazeeboLogo();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Gazeebo Application.
			userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
