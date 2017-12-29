package comcast.test.app.common.videoManagement.storePage.common;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.openqa.selenium.By;
import org.xml.sax.SAXException;

import comcast.test.app.common.constant.XidioConstant;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.vo.VideoDetails;

public class StorePageCommonFunctions extends BaseTest {

	public void selectBundle() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException,
			InterruptedException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		List<VideoDetails> BundlesList = videoDetails.get("bundlesList");

		Thread.sleep(sleepTime);
		boolean isPresent;
		do {
			isPresent = driver.findElements(
					By.linkText(BundlesList.get(XidioConstant.selectBundle)
							.getTitle())).size() > 0;
			if (isPresent == true) {
				assertTrue(driver
						.findElement(By.cssSelector("BODY"))
						.getText()
						.matches(
								"^[\\s\\S]*"
										+ BundlesList.get(
												XidioConstant.selectBundle)
												.getTitle() + "[\\s\\S]*$"));
				driver.findElement(
						By.linkText(BundlesList.get(XidioConstant.selectBundle)
								.getTitle())).click();
				Thread.sleep(sleepTime);
			} else {
				driver.findElement(
						By.xpath("//*[@id='featured']/div/section/a[2]/span"))
						.click();
				Thread.sleep(sleepTime);
			}
		} while (isPresent == false);
	}

	public void selectFeaturedChannel() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException,
			InterruptedException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.StoreFeaturedAPI();
		List<VideoDetails> channelList = videoDetails.get("show");

		Thread.sleep(sleepTime);
		boolean isPresent;
		do {
			isPresent = driver.findElements(
					By.xpath(".//*[@id='featured']/div/section").linkText(
							channelList
									.get(XidioConstant.selectFeaturedChannel)
									.getTitle())).size() > 0;
			if (isPresent == true) {
				assertTrue(driver
						.findElement(By.cssSelector("BODY"))
						.getText()
						.matches(
								"^[\\s\\S]*"
										+ channelList
												.get(XidioConstant.selectFeaturedChannel)
												.getTitle() + "[\\s\\S]*$"));
				driver.findElement(
						By.xpath(".//*[@id='featured']/div/section").linkText(
								channelList.get(
										XidioConstant.selectFeaturedChannel)
										.getTitle())).click();
				Thread.sleep(sleepTime);
			} else {
				driver.findElement(
						By.xpath("//*[@id='featured']/div/section/a[2]/span"))
						.click();
				Thread.sleep(sleepTime);
			}
		} while (isPresent == false);
	}

	public void selectFeaturedShow() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException,
			InterruptedException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.StoreFeaturedAPI();
		List<VideoDetails> showList = videoDetails.get("subshow");

		boolean present;
		do {
			present = driver.findElements(
					By.linkText(showList.get(XidioConstant.selectShow)
							.getTitle())).size() > 0;
			if (present == true) {
				assertTrue(driver
						.findElement(By.cssSelector("BODY"))
						.getText()
						.matches(
								"^[\\s\\S]*"
										+ showList
												.get(XidioConstant.selectShow)
												.getTitle() + "[\\s\\S]*$"));
				driver.findElement(
						By.xpath(".//*[@id='featured']/div/section").linkText(
								showList.get(XidioConstant.selectShow)
										.getTitle())).click();
			} else {
				driver.findElement(
						By.xpath("//*[@id='featured']/div/section/a[2]/span"))
						.click();
				Thread.sleep(sleepTime);
			}
		} while (present == false);
	}

	public void selectPopularChannel() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException,
			InterruptedException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.StorePopularAPI();
		List<VideoDetails> channelList = videoDetails.get("show");

		boolean isPresent;
		do {
			isPresent = driver.findElements(
					By.xpath("//*[@id='popular_channels']/div/section")
							.partialLinkText(
									channelList.get(
											XidioConstant.selectPopularChannel)
											.getTitle())).size() > 0;
			if (isPresent == true) {
				assertTrue(driver
						.findElement(By.cssSelector("BODY"))
						.getText()
						.matches(
								"^[\\s\\S]*"
										+ channelList
												.get(XidioConstant.selectPopularChannel)
												.getTitle() + "[\\s\\S]*$"));
				driver.findElement(
						By.xpath("//*[@id='popular_channels']/div/section")
								.partialLinkText(
										channelList
												.get(XidioConstant.selectPopularChannel)
												.getTitle())).click();
			} else {
				driver.findElement(
						By.xpath(".//*[@id='popular_channels']/div/section/a[2]/span"))
						.click();
				Thread.sleep(sleepTime);
			}
		} while (isPresent == false);
	}
}
