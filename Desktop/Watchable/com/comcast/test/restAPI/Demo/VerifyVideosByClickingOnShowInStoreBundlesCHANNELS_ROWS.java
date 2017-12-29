package comcast.test.restAPI.Demo;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.constant.XidioConstant;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyVideosByClickingOnShowInStoreBundleCHANNELS_ROWS
 * Description: This test case is to verify Videos by clicking on Show in Bundle
 * > CHANNEL > ROWS for a Bundle displayed under 'Featured' section on 'Store'
 * screen by logging into Comcast application.
 */

public class VerifyVideosByClickingOnShowInStoreBundlesCHANNELS_ROWS extends
		BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();

	@Test
	public void testVerifyVideosByClickingOnShowInStoreBundleCHANNELS_ROWS()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		List<VideoDetails> BundlesList = videoDetails.get("bundlesList");
		List<VideoDetails> BundlesChannelList = videoDetails
				.get("showsInBundle");
		List<VideoDetails> ShowsListUnderBundleRows = videoDetails
				.get("subShowInBundleChannel");
		List<VideoDetails> VideoListUnderBundleRows = videoDetails
				.get("videosInBundleChannel");

		/*
		 * This Method is to register new user using Comcast application and to
		 * change a password.
		 */
		RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

		driver.findElement(By.linkText("STORE")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Featured[\\s\\S]*$"));

		driver.findElement(
				By.linkText(BundlesList.get(XidioConstant.selectBundle)
						.getTitle())).click();

		assertTrue(driver
				.findElement(By.cssSelector("BODY"))
				.getText()
				.matches(
						"^[\\s\\S]*"
								+ BundlesList.get(XidioConstant.selectBundle)
										.getTitle() + "[\\s\\S]*$"));

		driver.findElement(By.linkText("CHANNELS")).click();

		Thread.sleep(sleepTime);
		driver.findElement(
				By.linkText(BundlesChannelList.get(
						XidioConstant.selectBundleChannel).getTitle())).click();

		Thread.sleep(sleepTime);
		assertTrue(driver
				.findElement(By.cssSelector("BODY"))
				.getText()
				.matches(
						"^[\\s\\S]*"
								+ BundlesChannelList.get(
										XidioConstant.selectBundleChannel)
										.getTitle() + "[\\s\\S]*$"));

		driver.findElement(By.linkText("ROWS")).click();

		Thread.sleep(sleepTime);
		driver.findElement(
				By.linkText(ShowsListUnderBundleRows.get(
						XidioConstant.selectShow).getTitle())).click();

		// This is to assert Show Name.
		assertTrue(driver
				.findElement(By.cssSelector("BODY"))
				.getText()
				.matches(
						"^[\\s\\S]*"
								+ ShowsListUnderBundleRows.get(
										XidioConstant.selectShow).getTitle()
								+ "[\\s\\S]*$"));

		// This is to assert Video Name.
		assertTrue(driver
				.findElement(By.cssSelector("BODY"))
				.getText()
				.matches(
						"^[\\s\\S]*"
								+ VideoListUnderBundleRows.get(
										XidioConstant.selectVideo).getTitle()
								+ "[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		driver.findElement(By.linkText("Log Out")).click();
	}
}
