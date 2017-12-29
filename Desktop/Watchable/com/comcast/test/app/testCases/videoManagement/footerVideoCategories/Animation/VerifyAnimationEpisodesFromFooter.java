package comcast.test.app.testCases.videoManagement.footerVideoCategories.Animation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyAnimationAndGamesShowsFromFooter Description: This test
 * case validates whether shows are displayed in Animations and Games Category
 * in Home page by clicking on Animation And Games category in footer link
 * with/without logging into Watchable application.
 * **/

public class VerifyAnimationEpisodesFromFooter extends BaseTest {

	// Manoj: Refactored the code
	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	String pageTitle = "";

	@Test
	public void testVerifyAnimationAndGamesShowsFromFooter() throws Exception {

		// String genreCategoryName = orProUtil.getProperty("ANIMATION");
		String genreCategoryName = UILablesRepo.ANIMATION;

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.GenreCategoryEpisodesDetail(genreCategoryName);
		List<VideoDetails> genresCategoryList = videoDetails
				.get("genresCategoryList");
		List<VideoDetails> genreCategoriesEpisodeList = videoDetails
				.get("GenreCategoriesEpisodesList");

		try {
			// This Method is to register new user using Watchable application
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
				// into
				// Application.
				// assertionFunction.assertHomeActiveLink();

				boolean categoryFound = false;

				int noOfCategoriesDisplayed = genresCategoryList.size();
				if (genresCategoryList != null && noOfCategoriesDisplayed > 0
						&& genreCategoriesEpisodeList != null) {
					for (VideoDetails genreCategories : genresCategoryList) {
						if (genreCategories.getTitle().equalsIgnoreCase(
								genreCategoryName)) {
							for (int category = 1; category <= 3; category++) {
								for (int section = 1; section < 4; section++) {
									// Navigate to footer Video Categories
									// section.
									// driver.findElement(By.xpath(".//*[@id='footercategory']/div[1]")).click();
									driver.findElement(
											By.xpath(XpathObjectRepo.FOOTERCATEGORIES_ANIMATION_XPATH))
											.click();

									// String
									// getCategoryTitle=driver.findElement(By.xpath(".//*[@id='footerCatNav']/div/div["+category+"]/ul/li["+section+"]/div/a")).getText();
									String getCategoryTitle = driver
											.findElement(
													(By.xpath(XpathObjectRepo.FOOTER_VIDEO_CATEGORIES_XPATH
															+ category
															+ "]"
															+ XpathObjectRepo.FOOTER_VIDEO_CATEGORIES_TITLES
															+ section + "]")))
											.getText();
									if (getCategoryTitle
											.equalsIgnoreCase(genreCategoryName)) {
										// Click on Genre Category from footer.
										// driver.findElement(By.xpath(".//*[@id='footerCatNav']/div/div["+category+"]/ul/li["+section+"]/div/a")).click();

										driver.findElement(
												By.xpath((XpathObjectRepo.FOOTER_VIDEO_CATEGORIES_XPATH
														+ category
														+ "]"
														+ XpathObjectRepo.FOOTER_VIDEO_CATEGORIES_TITLES
														+ section + "]")))
												.click();

										Thread.sleep(sleepTime);
										// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*CATEGORY[\\s\\S]*$"));
										assertTrue(driver
												.findElement(
														By.xpath(XpathObjectRepo.FOOTER_CATEGORY_HEADER_XPATH))
												.getText()
												.equalsIgnoreCase(
														UILablesRepo.GENRE_CATEGORY));
										// assertEquals(genreCategories.getTitle(),driver.findElement(By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[1]/section/h2")).getText());
										assertEquals(
												genreCategories.getTitle(),
												driver.findElement(
														By.xpath(XpathObjectRepo.FOOTER_CATEGORY_TITLEHEADER_XPATH))
														.getText());

										// String
										// getTypeState=driver.findElement(By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[3]/header/div/nav/ul/li[1]/a")).getAttribute("class");
										String getTypeState = driver
												.findElement(
														By.xpath(XpathObjectRepo.FOOTER_CATEGORY_TYPES_HEADER_XPATH))
												.getAttribute("class");
										assertEquals("icn-rows active",
												getTypeState);

										categoryFound = true;
										if (genreCategoriesEpisodeList != null) {
											int loopMaxIndex = 0;
											if (genreCategoriesEpisodeList
													.size() < 4)
												loopMaxIndex = genreCategoriesEpisodeList
														.size();
											else
												loopMaxIndex = 4;
											int i = 0;
											for (int index = 0; index < loopMaxIndex; index++) {
												VideoDetails episodesList = genreCategoriesEpisodeList
														.get(index);
												int listIndex = genreCategoriesEpisodeList
														.indexOf(episodesList);
												for (; i <= listIndex; i++) {
													int j = (i % 5) + 1;

													// Below refactoring is not
													// done
													// as elements are not
													// highlighting

													assertEquals(
															episodesList
																	.getTitle(),
															driver.findElement(
																	By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[3]/div/div[3]/div/section/div/div/ul[1]/li["
																			+ j
																			+ "]/article/h1/a"))
																	.getText());

													for (int k = 0; k < (i + 1) / 5; k++) {
														String isNextEnable = driver
																.findElement(
																		By.xpath(".//*[@id='genre']/div/div[2]/div["
																				+ category
																				+ "]/div/section/a[2]"))
																.getAttribute(
																		"class");
														if (!isNextEnable
																.equalsIgnoreCase("next hidden")
																&& !isNextEnable
																		.equalsIgnoreCase("next disabled")) {
															driver.findElement(
																	By.xpath(".//*[@id='genre']/div/div[2]/div["
																			+ category
																			+ "]/div/section/a[2]"))
																	.click();
															Thread.sleep(5000);
														}
													}
												}
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
								// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+orProUtil.getProperty("ANIMATIONANDGAMES")+"[\\s\\S]*$"));
								assertTrue(driver
										.findElement(
												By.xpath(XpathObjectRepo.FOOTERCATEGORIES_ANIMATION_XPATH))
										.getText()
										.matches(UILablesRepo.ANIMATION));

						}
						if (categoryFound == true)
							break;
					}
				}

				// This method is used to logout from watchable Application.
				// userLogin.LogOut(driver);

				// This method is to ensure Login page is displayed when user
				// Sign
				// Out from Application.
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