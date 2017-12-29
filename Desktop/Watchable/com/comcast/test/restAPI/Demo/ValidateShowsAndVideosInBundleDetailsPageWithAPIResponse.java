package comcast.test.restAPI.Demo;

import static org.junit.Assert.assertEquals;
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
 * Class Name: ValidateShowsAndVideosInBundleDetailsPageWithAPIResponse
 * Description: This test case is to verify API response date and UI displayed
 * Data on screen in 'Store' page by logging into Comcast application.
 */

public class ValidateShowsAndVideosInBundleDetailsPageWithAPIResponse extends
		BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();

	@Test
	public void testVerifyShowsAndVideosInRowsForFeaturedChannel()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		List<VideoDetails> BundlesList = videoDetails.get("bundlesList");
		List<VideoDetails> ChannelListUnderBundleRows = videoDetails
				.get("showsInBundle");
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

		Thread.sleep(sleepTime);
		assertTrue(driver
				.findElement(By.cssSelector("BODY"))
				.getText()
				.matches(
						"^[\\s\\S]*"
								+ BundlesList.get(XidioConstant.selectBundle)
										.getTitle() + "[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		// Assert Channel Title to verify API response and UI displayed.
		assertEquals(
				"Activity TV",
				ChannelListUnderBundleRows.get(
						XidioConstant.selectBundleChannel).getTitle());

		// Assert Video Title to verify API response and UI displayed.
		Thread.sleep(sleepTime);
		assertEquals("SODACTSUF012",
				VideoListUnderBundleRows.get(XidioConstant.selectVideo)
						.getTitle());

		// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+ChannelListUnderBundleRows.get(XidioConstant.selectBundleChannel).getTitle()+"[\\s\\S]*$"));
		// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+VideoListUnderBundleRows.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));

		driver.findElement(By.linkText("Log Out")).click();
	}
}
