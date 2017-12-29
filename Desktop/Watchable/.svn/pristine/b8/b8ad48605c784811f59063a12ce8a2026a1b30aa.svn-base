package comcast.test.app.common.videoManagement.subscriptionsPage.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.xml.sax.SAXException;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.vo.VideoDetails;

public class SubscriptionsPageCommonFunctions extends BaseTest {

	/*
	 * Lekshmi : As the Bundle functionality is removed from the Watchable
	 * application, the function is not refactoring.
	 */
	public void selectSubscribedBundle() throws XPathExpressionException,
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
			} else {
				driver.findElement(
						By.xpath("//*[@id='featured']/div/section/a[2]/span"))
						.click();
				Thread.sleep(sleepTime);
			}
		} while (isPresent == false);
	}

	public void selectSubscribedChannel() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException,
			InterruptedException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.SubscriptionsAPI();
		List<VideoDetails> subscribedChannelList = videoDetails
				.get("subscribedChannelsList");

		Thread.sleep(sleepTime);
		boolean isPresent;
		do {
			isPresent = driver.findElements(
					By.linkText(subscribedChannelList.get(
							XidioConstant.selectFeaturedChannel).getTitle()))
					.size() > 0;
			if (isPresent == true) {
				// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+subscribedChannelList.get(XidioConstant.selectSubscribedChannel).getTitle()+"[\\s\\S]*$"));
				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.MYCHANNELSCHANNELTITLELABEL_XPATH))
						.getText()
						.matches(
								subscribedChannelList.get(
										XidioConstant.selectSubscribedChannel)
										.getTitle()));
				driver.findElement(
						By.linkText(subscribedChannelList.get(
								XidioConstant.selectSubscribedChannel)
								.getTitle())).click();
			} else {
				// driver.findElement(By.xpath(".//*[@id='next_subscribed']/span")).click();
				Thread.sleep(sleepTime);
			}
		} while (isPresent == false);
	}

	public void selectSubscribedShow() throws XPathExpressionException,
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
				// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showList.get(XidioConstant.selectShow).getTitle()+"[\\s\\S]*$"));
				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
						.getText()
						.matches(
								showList.get(XidioConstant.selectShow)
										.getTitle()));
				driver.findElement(
						By.xpath(XpathObjectRepo.FEATUREDROW_XPATH).linkText(
								showList.get(XidioConstant.selectShow)
										.getTitle())).click();
			} else {
				driver.findElement(
						By.xpath(XpathObjectRepo.FEATURED_PAGINATION_NEXT_XPATH))
						.click();
				Thread.sleep(sleepTime);
			}
		} while (present == false);
	}

	/*
	 * Lekshmi : As the Shows link functionality is removed from the Watchable
	 * application, the function is not refactoring.
	 */
	public void clickOnShowsLink() {
		driver.findElement(By.linkText(orProUtil.getProperty("SHOWSLINK")))
				.click();
	}

	/*
	 * Lekshmi : As the Unwatched link functionality is removed from the
	 * Watchable application, the function is not refactoring.
	 */
	public void clickOnUnwatchedLink() {
		driver.findElement(By.linkText(orProUtil.getProperty("UNWATCHEDLINK")))
				.click();
	}

	/*
	 * Lekshmi : As the Last Updated link functionality is removed from the
	 * Watchable application, the function is not refactoring.
	 */
	public void clickOnLastUpdatedLink() {
		driver.findElement(
				By.linkText(orProUtil.getProperty("LASTUPDATEDLINK"))).click();
	}

	public void clickChannelsLink() {
		driver.findElement(
				By.xpath(XpathObjectRepo.MYCHANNELS_CHANNELS_TITLE_XPATH))
				.click();
	}

	/*
	 * Name: clickCategoriesLink Description:
	 */
	public void clickCategoriesLink() {
		driver.findElement(
				By.xpath(XpathObjectRepo.MYCHANNELS_CATEGORIES_TITLE_XPATH))
				.click();
	}

	/**
	 * Name: navigateToMyChannelsPage Description: This method is to navigate My
	 * Channel page.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void navigateToMyChannelsPage() throws FileNotFoundException,
			IOException {

		/*
		 * orProUtil.load(new FileInputStream(new File("com/OR.properties")));
		 * driver
		 * .findElement(By.linkText(orProUtil.getProperty("MYCHANNELSLINK")
		 * )).click();
		 */
		driver.findElement(
				By.xpath(XpathObjectRepo.TOP_MENU_MYCHANNELS_BUTTON_XPATH))
				.click();
	}

	/**
	 * Name: subscribeChannel Description: This method is to subscribe a Channel
	 * from Home page.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public void subscribeChannel(String channelName)
			throws FileNotFoundException, IOException, InterruptedException {
		boolean isPresent;
		do {
			isPresent = driver
					.findElement(By.xpath(XpathObjectRepo.HOMEPAGE_DETAILS))
					.findElements(By.linkText(channelName)).size() > 0;
			if (isPresent == true) {

				// driver.findElement(By.xpath("//div[@id='featured']/descendant::li[contains(@class,'cell-channel')]")).findElement(By.linkText(channelName)).click();
				driver.findElement(
						By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH))
						.findElement(By.linkText(channelName)).click();
				Thread.sleep(sleepTime);

				boolean isFollowNowPresent = false;
				// Verify for the Channel subscription by checking the
				// "Follow Now" Button
				try {
					driver.findElement(By
							.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH));
					isFollowNowPresent = true;
				} catch (NoSuchElementException nse) {
					System.out.println("In exception");
				}
				// click on the "Follow Now" button if present else go back to
				// Home Page
				if (isFollowNowPresent) {
					driver.findElement(
							By.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH))
							.click();

				}

				// assertEquals("YOU ARE NOW FOLLOWING",
				// driver.findElement(By.xpath("//div[@id='content-wrap']/descendant::div[@class='follow']/div")));
			} else {
				String isNextEnable = driver
						.findElement(
								By.xpath(XpathObjectRepo.FEATURED_PAGINATION_NEXT_SECTION_XPATH))
						.getAttribute("class");
				if (!isNextEnable.equalsIgnoreCase("next hidden")
						&& !isNextEnable.equalsIgnoreCase("next disabled")) {
					for (int i = 0; i < 6; i++) {
						driver.findElement(By.tagName("body")).sendKeys(
								Keys.DOWN);
						Thread.sleep(1000);
					}
					driver.findElement(
							By.xpath(XpathObjectRepo.FEATURED_PAGINATION_NEXT_SECTION_XPATH))
							.click();
					Thread.sleep(sleepTime);
				} else {
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelList.getTitle()+"[\\s\\S]*$"));
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDROW_XPATH))
							.getText()
							.matches("^[\\s\\S]*" + channelName + "[\\s\\S]*$"));
					break;
				}

			}
		} while (isPresent == false);
	}

	/**
	 * Name: unSubscribeAChannel Description: This method is to Unsubscribe a
	 * Channel from My Channels page.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public void unSubscribeAChannel(String channelName)
			throws FileNotFoundException, IOException, InterruptedException {
		boolean isPresent;
		do {
			isPresent = driver
					.findElement(
							By.xpath(XpathObjectRepo.MYCHANNELS_CONTENT_SECTION_XPATH))
					.findElements(By.linkText(channelName)).size() > 0;
			if (isPresent == true) {

				driver.findElement(
						By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"))
						.findElement(By.linkText(channelName)).click();

				Thread.sleep(sleepTime);
				assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
						.matches("^[\\s\\S]*" + channelName + "[\\s\\S]*$"));

				// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Free[\\s\\S]*$"));

				driver.findElement(
						By.xpath(".//*[@id='content-wrap']/article/div/div[1]/div/span/form/input[4]"))
						.click();

				// driver.findElement(By.xpath(".//*[@id='follow-tip-content']/div[1]")).click();

				Thread.sleep(sleepTime);
				assertEquals(
						"YOU ARE NO LONGER FOLLOWING",
						driver.findElement(
								By.xpath(".//*[@id='follow-tip-content']/div[1]"))
								.getText());
			} else {
				assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
						.matches("^[\\s\\S]*" + channelName + "[\\s\\S]*$"));
				break;
			}
		} while (isPresent == false);
	}

	/**
	 * Name: unSubscribeAChannel Description: This method is to Un subscribe a
	 * Channel from My Channels page.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public void unSubscribeASubscribedChannels() throws FileNotFoundException,
			IOException, InterruptedException, XPathExpressionException,
			ParserConfigurationException, SAXException {
		// This list contains all the subscribed channels.
		Thread.sleep(sleepTime);
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.SubscriptionsAPI();
		List<VideoDetails> subscribedChannels = videoDetails
				.get("subscribedChannelsList");

		if (subscribedChannels != null) {
			int loopIndexMax = 0;
			if (subscribedChannels.size() < 2) {
				loopIndexMax = subscribedChannels.size();
			} else {
				loopIndexMax = 2;
			}
			for (int index = 0; index < loopIndexMax; index++) {
				VideoDetails subscribedChannelDetails = subscribedChannels
						.get(index);

				// This method is to navigate My Channels Page.
				// driver.findElement(By.linkText("MY CHANNELS")).click();
				Thread.sleep(sleepTime);
				// Unsubscribe a channel
				boolean isPresent;
				do {// //div[@class='seq-content-list']/descendant::section
					// isPresent=driver.findElement(By.xpath(XpathObjectRepo.MYCHANNELS_ROW_LIST_XPATH)).findElements(By.linkText(subscribedChannelDetails.getTitle())).size()>0;
					isPresent = driver
							.findElements(
									By.xpath(XpathObjectRepo.MYCHANNELS_CHANNELS_XPATH))
							.size() > 0;
					if (isPresent == true) {
						driver.findElement(
								By.xpath(XpathObjectRepo.MYCHANNELS_ROW_LIST_XPATH))
								.findElement(
										By.linkText(subscribedChannelDetails
												.getTitle())).click();
						Thread.sleep(sleepTime);
						// ///
						// driver.findElement(By.xpath(".//*[@id='content-wrap']/article/div/div[1]/div/span[2]/a/img")).click();

						int getTotalChannels = driver
								.findElements(
										By.xpath(XpathObjectRepo.MYCHANNELS_ROW_LIST_XPATH))
								.size();

						if (getTotalChannels > 0)

						{
							System.out.println("total number of Channels="
									+ getTotalChannels);

						}

						// driver.findElement(By.xpath("//div[@id='content-wrap']/descendant::input[@type='image']")).click();

					} else {
						driver.navigate().back();
						Thread.sleep(sleepTime);
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+subscribedChannelDetails.getTitle()+"[\\s\\S]*$"));
						break;
					}
				} while (isPresent == false);
			}
		}
	}

	/**
	 * Name: subscribeChannel Description: This method is to subscribe a Channel
	 * from Home page.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public void subscribeChannelFromPopularChannels(String channelName)
			throws FileNotFoundException, IOException, InterruptedException,
			XPathExpressionException, ParserConfigurationException,
			SAXException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nPopularAPI();
		List<VideoDetails> ChannelsList = videoDetails
				.get("popularChannelsList");
		int i = 1;
		String channelTitle = "";
		HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();

		// Iterate through the Popular Channels to check for the Subscribed
		// Channels
		for (VideoDetails popChnl : ChannelsList) {
			// Verify the Channel Title
			homePageCommonFun.scrollToSection();
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH
									+ "["
									+ i
									+ "]"
									+ XpathObjectRepo.CHANNELSHOWLINK_XPATH))
					.getText().equalsIgnoreCase(popChnl.getTitle()));
			// Click on the title to land in Channels Page
			driver.findElement(
					By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH
							+ "[" + i + "]"
							+ XpathObjectRepo.CHANNELSHOWLINK_XPATH)).click();
			Thread.sleep(sleepTime);
			boolean isFollowNowPresent = false;
			// Verify for the Channel subscription by checking the
			// "Follow Now" Button
			try {
				driver.findElement(By
						.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH));
				channelTitle = popChnl.getTitle();
				isFollowNowPresent = true;
			} catch (NoSuchElementException nse) {
				System.out.println("In exception");
			}
			// click on the "Follow Now" button if present else go back to
			// Home Page
			if (isFollowNowPresent) {
				driver.findElement(
						By.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH))
						.click();
				break;
			} else {
				driver.navigate().back();
				i++;
				Thread.sleep(sleepTime);
			}

		}
	}

}
