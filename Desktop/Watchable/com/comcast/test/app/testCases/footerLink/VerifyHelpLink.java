package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyHelpLink Description: This test case click on help link and
 * verified the main content of help page. Author: Manoj
 * **/

public class VerifyHelpLink extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHelpLink() throws Exception {

		try {

			log.info("Script: VerifyHelpLink");
			log.info("*********************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Help Link is present in footer
			assertTrue(
					"Help Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Help Link is present");

			// Click on Help Link
			FooterLinkFun.clickOnHelpLink();

			// Verify Help form is opened
			assertTrue(
					"Help form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Help form is opened");

			// Verify Help form Title
			assertTrue(
					"Title not present for HELP form",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.helpFormTitle_XPATH)));

			log.info("The Title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.helpFormTitle_XPATH))
							.getText() + "' is present in help form");

			int questionCount = driver.findElements(
					By.xpath(XpathObjectRepo.helpFormQuestionTitle_XPATH))
					.size();

			if (questionCount > 0) {

				log.info("Questions are present in help page");
				log.info(questionCount + " Questions are present in help page");
				log.info("The following Questions are present in help page");
				log.info("--------------------------------------------------");

				for (int i = 0; i < questionCount; i++) {
					int j = i + 1;

					log.info(j

							+ ". "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.helpFormQuestionsTitle_XPATH
											+ "[" + j + "]/descendant::h2"))
									.getText());

				}

			} else {
				log.error("Questions are not present in help page");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
