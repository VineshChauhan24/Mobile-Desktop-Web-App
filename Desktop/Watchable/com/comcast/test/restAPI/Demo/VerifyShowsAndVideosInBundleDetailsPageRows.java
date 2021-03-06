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
 * Class Name: VerifyShowsAndVideosInBundleDetailsPageRows Description: This
 * test case is to verify Channels and Video after sorting by 'ROWS'for a Bundle
 * displayed under 'Featured' section on 'Store' screen by logging into Comcast
 * application.
 */

public class VerifyShowsAndVideosInBundleDetailsPageRows extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();

	@Test
	public void testVerifyShowsAndVideosInRowsForFeaturedChannel()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		List<VideoDetails> BundlesList = videoDetails.get("bundlesList");

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
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Activity TV[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		driver.findElement(By.linkText("ROWS")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Activity TV[\\s\\S]*$"));

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*SODACTSUF012[\\s\\S]*$"));

		driver.findElement(By.linkText("Log Out")).click();
	}
}
