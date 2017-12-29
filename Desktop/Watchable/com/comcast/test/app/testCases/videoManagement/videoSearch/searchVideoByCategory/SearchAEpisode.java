package comcast.test.app.testCases.videoManagement.videoSearch.searchVideoByCategory;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: SearchAEpisode Description: This test case validates Episodes
 * search functionality in Home page by logging registered user into Watchable
 * application.
 * **/

public class SearchAEpisode extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	String pageTitle = "";

	@Test
	public void testSearchAEpisode() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredSectionVideos();
		List<VideoDetails> episodesList = videoDetails.get("featuredVideoList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			driver.get(DataServiceProperties.HOMEAPPURL);

			// This method is used to enter user name and password credential
			Thread.sleep(sleepTime);
			pageTitle = driver.getTitle();

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method asserts Help link.
			assertionFunction.assertHelpLink();

			// Lekshmi : Change the object identifier.

			/*
			 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText().
			 * matches("^[\\s\\S]*Featured[\\s\\S]*$"));
			 * 
			 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText().
			 * matches("^[\\s\\S]*Up Next[\\s\\S]*$"));
			 */

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.HOMEPAGE_FEATURED));

			driver.findElement(By.xpath(XpathObjectRepo.SEARCHNAVMENU_XPATH))
					.click();

			// driver.findElement(By.xpath(XpathObjectRepo.HOMESEARCHQUERYID_XPATH)).clear();

			driver.findElement(
					By.xpath(XpathObjectRepo.HOMESEARCHQUERYID_XPATH))
					.sendKeys(
							episodesList.get(
									XidioConstant.selectFeaturedChannel)
									.getTitle());

			driver.findElement(By.xpath(XpathObjectRepo.HOMESEARCHBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);

			int searchResult = driver.findElements(
					By.xpath(XpathObjectRepo.EPISODE_SEARCH_XPATH)).size();

			if (searchResult == 1) {

				assertTrue(
						"Search result does contains searched Episode",
						driver.findElement(
								By.xpath(XpathObjectRepo.EPISODE_SEARCH_XPATH))
								.getText()
								.contains(
										episodesList
												.get(XidioConstant.selectFeaturedChannel)
												.getTitle()));
				log.info("Search result contains searched Episode");
			} else {
				log.error("Application Error..! Search result does contains searched Episode");
			}

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Gazeebo Application.
			// userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();

		} catch (Throwable t) {

			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				captureScreenshot();
				collector.addError(t);
			}

		}
	}
}
