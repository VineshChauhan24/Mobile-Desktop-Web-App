package comcast.test.app.testCases.providerPage.providerPageFunctions;

import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.config.configServices.utils.BaseTest;

public class providerPageFun extends BaseTest {

	/**
	 * Method Name: clickOnProviderLinkFromChannelPage Description: This method
	 * used click On Provider name link from show detail page
	 * 
	 * @throwsdException
	 */
	public static void clickOnProviderLinkFromChannelPage() throws Exception {

		// click On Provider name link

		String providerTitle = driver.findElement(
				By.xpath(XpathObjectRepo.channelPageProviderTitle_XPATH))
				.getText();

		driver.findElement(
				By.xpath(XpathObjectRepo.channelPageProviderTitle_XPATH))
				.click();

		Thread.sleep(sleepTime);
		log.info("Successfully clicked On provider title '" + providerTitle
				+ "' from Show Page");

	}

}
