package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyClickingOnGazeeboHeaderLogoNavigatesHomePage Description:
 * This test case is to verify Watchable Header link is clickable and once
 * clicking on Logo from login page it should navigate to Home page.
 * **/

public class VerifyClickingOnWatchableHeaderLogoNavigatesHomePage extends
		BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyClickingOnGazeeboHeaderLogoNavigatesHomePage()
			throws Exception {

		List<VideoDetails> videoDetails = RestAPIServices.nhomePageAPIs();

		// driver.get(proUtil.getProperty("HOMEAPPURL"));
		driver.get(DataServiceProperties.HOMEAPPURL);
		try {

			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			Thread.sleep(sleepTime);
			// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+orProUtil.getProperty("UPNEXT")+"[\\s\\S]*$"));

			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+orProUtil.getProperty("FEATURED")+"[\\s\\S]*$"));

			Thread.sleep(sleepTime);
			// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH)).getText().matches(UILablesRepo.HOMEPAGE_YOUAREWATCHING));
			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATURED));
			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSTITLELABEL_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_POPULARSHOWS));
			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSTITLELABEL_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATUREDCHANNELS));

			/*
			 * assertTrue(driver .findElement(By.cssSelector("BODY")) .getText()
			 * .matches( "^[\\s\\S]*" + videoDetails.get(0).getTitle() +
			 * "[\\s\\S]*$"));
			 */

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINER_FIRSTELEMENT_XPATH))
					.getText().matches(videoDetails.get(0).getTitle()));

			Thread.sleep(sleepTime);
			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+orProUtil.getProperty("POPULARSHOWS")+"[\\s\\S]*$"));
			/*
			 * assertTrue(driver .findElement(By.cssSelector("BODY")) .getText()
			 * .matches( "^[\\s\\S]*" + videoDetails.get(
			 * XidioConstant.selectPopularShow) .getTitle() + "[\\s\\S]*$"));
			 */
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINER_FIRSTELEMENT_XPATH))
					.getText()
					.matches(
							videoDetails.get(XidioConstant.selectPopularShow)
									.getTitle()));

			Thread.sleep(sleepTime);
			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+orProUtil.getProperty("POPULARCHANNELS")+"[\\s\\S]*$"));
			/*
			 * assertTrue(driver .findElement(By.cssSelector("BODY")) .getText()
			 * .matches( "^[\\s\\S]*" + videoDetails.get(
			 * XidioConstant.selectPopularChannel) .getTitle() + "[\\s\\S]*$"));
			 */

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINER_FIRSTELEMENT_XPATH))
					.getText()
					.matches(
							videoDetails
									.get(XidioConstant.selectPopularChannel)
									.getTitle()));

			// driver.findElement(By.linkText("Log In")).click();
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.click();
			/*
			 * This method asserts Home and My Channels inactive link when user
			 * clicks on 'My Channels' link before logging into Application.
			 */
			assertionFunction.assertAllInActiveLink();

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();

			/*
			 * assertEquals("", driver.findElement(By.name("user[user_name]"))
			 * .getAttribute("value"));
			 */

			assertEquals(
					"",
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGIN_EMAIL_FIELD_XPATH))
							.getAttribute("value"));

			/*
			 * assertEquals("", driver.findElement(By.name("user[password]"))
			 * .getAttribute("value"));
			 */

			assertEquals(
					"",
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGIN_PASSWORD_FIELD_XPATH))
							.getAttribute("value"));

			/*
			 * assertEquals("Log In", driver.findElement(By.id("user_login"))
			 * .getText());
			 */

			assertEquals(
					UILablesRepo.LOGIN_PAGE_LOGIN,
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
							.getText());

			// This method asserts featured title not present on UI.
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.matches(UILablesRepo.HOMEPAGE_FEATURED));
			// assertFalse(driver.findElement(By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH)).getText().matches(UILablesRepo.HOMEPAGE_FEATURED));

			// Click on Header Logo image.
			// driver.findElement(By.xpath(".//*[@id='header-wrap']/header/a")).click();
			driver.findElement(
					By.xpath(XpathObjectRepo.WATCHABLELOGOIMAGE_XPATH)).click();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts Search Text Box and its value.
			assertionFunction.assertSearchTextBox();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			/*
			 * assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
			 * .matches("^[\\s\\S]*Welcome to Gazeebo![\\s\\S]*$"));
			 */
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
