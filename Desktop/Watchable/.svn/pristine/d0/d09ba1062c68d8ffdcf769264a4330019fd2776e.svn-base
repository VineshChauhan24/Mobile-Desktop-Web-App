package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.ShowTestCases;

import static org.junit.Assert.assertEquals;

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
 * Class Name: VerifyUpdatedStatusForHomeFeaturedShows Description: This test
 * case is to verify the shown last updated status/day for a Shows displayed
 * under 'Featured' Channel section on 'Home' screen by logging into Watchable
 * application.
 */

public class VerifyUpdatedStatusForHomeFeaturedShows extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUpdatedStatusForHomeFeaturedShows() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nFeaturedAPI();
		List<VideoDetails> featuredShowsList = videoDetails
				.get("featuredShowList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (featuredShowsList != null) {
				int loopMaxIndex = 0;
				if (featuredShowsList.size() < 4)
					loopMaxIndex = featuredShowsList.size();
				else
					loopMaxIndex = 4;

				for (int index = 0; index < loopMaxIndex; index++) {
					VideoDetails showList = featuredShowsList.get(index);
					// This Method verifies Show present in Home Featured list
					// and selects a Show.
					homePageCommonFun.selectHomeFeaturedShow(showList
							.getTitle());

					// String
					// publishedDate=driver.findElement(By.xpath(".//*[@id='content-wrap']/article/div/div[2]/div/div[1]/ul/li[2]")).getText();
					// Lekshmi : Changed Object identifier to fetch updated
					// status
					// String updatedStatus =
					// driver.findElement(By.xpath("//div[@class='content-metadata']/ul/li[2]")).getText();
					// Manoj: Object modified and moved to XpathObjectRepo file
					String updatedStatus = driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILVIDEOSUPDATED_XPATH))
							.getText();
					assertEquals(showList.getLastPublished(), updatedStatus);

					driver.navigate().back();
					Thread.sleep(sleepTime);
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
