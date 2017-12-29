package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.ChannelTestCases.ChannelPageTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyShowCountForHomeFeaturedChannels Description: This test
 * case validates whether Shows count is displayed in channel page for the
 * channel present under Featured section in Home page by logging registered
 * user into Watchable application.
 * **/

public class VerifyShowCountForHomeFeaturedChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyShowCountForHomeFeaturedChannels() throws Exception {

		/*
		 * Map<String, List<VideoDetails>>
		 * videoDetails=RestAPIServices.featuredChannelsList(); List
		 * <VideoDetails> channelList=videoDetails.get("featuredChannelsList");
		 */

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu(); - Functionality
			// is not available now

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (channelList != null) {
				int loopMaxIndex = 0;
				if (channelList.size() < 2)
					loopMaxIndex = channelList.size();
				else
					loopMaxIndex = 2;

				for (int index = 0; index < loopMaxIndex; index++) {
					// Lekshmi : Changed Object identifier to fetch Channel Show
					// count
					Thread.sleep(sleepTime);
					int position = index + 1;
					/*
					 * String noOfShows = driver .findElement( By.xpath(
					 * "//div[@id='featured']/descendant::li[contains(@class,'cell-channel')]["
					 * + position + "]/descendant::div[@class='subtitle']"))
					 * .getText();
					 */

					String noOfShows = driver
							.findElement(
									By.xpath(XpathObjectRepo.NOOF_SHOWS_PARTONE_XPATH
											+ position
											+ XpathObjectRepo.NOOF_SHOWS_PARTTWO_XPATH))
							.getText();
					//
					VideoDetails channelDetails = channelList.get(index);
					if (Integer.parseInt(channelDetails.getNumOfShows()) > 1)
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelDetails.getNumOfShows()+" SHOWS[\\s\\S]*$"));
						assertTrue(noOfShows.equalsIgnoreCase(channelDetails
								.getNumOfShows() + " SHOWS"));
					else
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelDetails.getNumOfShows()+" SHOW[\\s\\S]*$"));
						assertTrue(noOfShows.equalsIgnoreCase(channelDetails
								.getNumOfShows() + " SHOW"));

					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(channelDetails
							.getTitle());

					// Manoj: Object changed and moved to XpathObjectRepoi file
					// String
					// Showcount=driver.findElement(By.xpath(".//*[@id='content-wrap']/article/div/div[2]/div[2]/div[1]/ul/li[1]")).getText();
					String Showcount = driver
							.findElement(
									By.xpath(XpathObjectRepo.CHANNELDETAILSHOWCOUNT_XPATH))
							.getText();
					assertEquals(
							"Shows " + channelDetails.getNumOfShows() + "",
							Showcount);

					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			} else {
				boolean isPresent;
				// Manoj: Object modified and moved to XpathObjectProp file
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

					// Commenting below assertion because of now there is no
					// channel present in featured section
					/*
					 * assertFalse(driver .findElement(
					 * By.xpath(XpathObjectRepo.FEATUREDROWLIST_XPATH))
					 * .getText().contains(channelName));
					 */
				}
			}
			// This method is used to logout from Watchable Application.
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
