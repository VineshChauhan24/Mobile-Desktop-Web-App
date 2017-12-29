package comcast.test.app.testCases.videoManagement.footerVideoCategories.Animation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyAnimationAndGamesPopularVideosFromFooter Description: This
 * test case validates whether popular videos are displayed in Animations and
 * Games Category in Genre details page by clicking on Animation And Games
 * category in footer link with/without logging into Watchable application.
 * **/

public class VerifyAnimationPopularVideosFromFooter extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	String pageTitle = "";

	@Test
	public void testVerifyAnimationAndGamesPopularVideosFromFooter()
			throws Exception {

		String genreCategoryName = orProUtil.getProperty("ANIMATIONANDGAMES");
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.GenreCategoryPopularDetail(genreCategoryName);
		List<VideoDetails> genresCategoryList = videoDetails
				.get("genresCategoryList");
		List<VideoDetails> genreCategoriesPopularList = videoDetails
				.get("GenreCategoriesPopularList");

		try {
			// This Method is to register new user using Gazeebo application
			// * and to change a password.

			// This test case does not required login. So commenting below Login
			// method
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// Opening application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);
			pageTitle = driver.getTitle();

			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				log.info("Successfully Opened the application");

				// This method is to ensure Home is Active page before Login
				// into Application.
				// assertionFunction.assertHomeActiveLink();

				boolean categoryFound = false;

				int noOfCategoriesDisplayed = genresCategoryList.size();
				if (genresCategoryList != null && noOfCategoriesDisplayed > 0
						&& genreCategoriesPopularList != null) {
					for (VideoDetails genreCategories : genresCategoryList) {
						if (genreCategories.getTitle().equalsIgnoreCase(
								genreCategoryName)) {
							for (int category = 1; category <= 3; category++) {
								for (int section = 1; section < 4; section++) {
									// Navigate to footer Video Categories
									// section.
									driver.findElement(
											By.xpath(".//*[@id='footercategory']/div[1]"))
											.click();

									String getCategoryTitle = driver
											.findElement(
													By.xpath(".//*[@id='footerCatNav']/div/div["
															+ category
															+ "]/ul/li["
															+ section
															+ "]/div/a"))
											.getText();
									if (getCategoryTitle
											.equalsIgnoreCase(genreCategoryName)) {
										// Click on Genre Category from footer.
										driver.findElement(
												By.xpath(".//*[@id='footerCatNav']/div/div["
														+ category
														+ "]/ul/li["
														+ section + "]/div/a"))
												.click();

										Thread.sleep(sleepTime);
										assertTrue(driver
												.findElement(
														By.cssSelector("BODY"))
												.getText()
												.matches(
														"^[\\s\\S]*Category[\\s\\S]*$"));
										assertEquals(
												genreCategories.getTitle(),
												driver.findElement(
														By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[1]/section/h2"))
														.getText());

										String getTypeState = driver
												.findElement(
														By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[3]/header/div/nav/ul/li[1]/a"))
												.getAttribute("class");
										assertEquals("icn-rows active",
												getTypeState);

										categoryFound = true;
										if (genreCategoriesPopularList != null) {
											int loopMaxIndex = 0;
											if (genreCategoriesPopularList
													.size() < 4)
												loopMaxIndex = genreCategoriesPopularList
														.size();
											else
												loopMaxIndex = 4;

											for (int index = 0; index < loopMaxIndex; index++) {
												VideoDetails popularList = genreCategoriesPopularList
														.get(index);
												int value = index + 1;
												assertEquals(
														popularList.getTitle(),
														driver.findElement(
																By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[2]/div[2]/div/section/div/div/ul[1]/li["
																		+ value
																		+ "]/article/h1/a"))
																.getText());
												driver.findElement(
														By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[2]/div[2]/div/section/div/div/ul[1]/li["
																+ value
																+ "]/article/h1/a"))
														.click();

												Thread.sleep(sleepTime);
												// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+popularList.getTitle()+"[\\s\\S]*$"));
												assertEquals(
														popularList.getTitle(),
														driver.findElement(
																By.xpath(".//*[@id='content-wrap']/article/div/div[2]/h1[2]"))
																.getText());

												Thread.sleep(sleepTime);
												driver.navigate().back();
											}
										}
									}
									if (categoryFound == true)
										break;
								}
								if (categoryFound == true)
									break;
							}
							if (categoryFound == false)
								assertTrue(driver
										.findElement(By.cssSelector("BODY"))
										.getText()
										.matches(
												"^[\\s\\S]*"
														+ orProUtil
																.getProperty("ANIMATIONANDGAMES")
														+ "[\\s\\S]*$"));
						}
						if (categoryFound == true)
							break;
					}
				}

				// This method is used to logout from Gazeebo Application.
				// userLogin.LogOut(driver);

				// This method is to ensure Login page is displayed when user
				// Sign Out from Application.
				assertionFunction.assertLoginPageDetails();
			}
		} catch (Throwable t) {
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				captureScreenshot();
				collector.addError(t);
			}
		}
	}
}
