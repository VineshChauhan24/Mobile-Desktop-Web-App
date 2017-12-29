package comcast.test.testSuite;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comcast.test.app.testCases.allChannels.VeriyAllChannelsPage;
import comcast.test.app.testCases.channelPage.VerifyChannelDetailPageContent;
import comcast.test.app.testCases.channelPage.VerifyNavigationToChannelPage;
import comcast.test.app.testCases.contactUs.VerifyContactUsMandatoryFields;
import comcast.test.app.testCases.contactUs.VeriyContactUsPageContent;
import comcast.test.app.testCases.follow.VerifyFollowFromChannelPageAfterLogin;
import comcast.test.app.testCases.follow.VerifyFollowFromChannelPageWithoutLogin;
import comcast.test.app.testCases.follow.VerifyFollowFromVideoControlAfterLogin;
import comcast.test.app.testCases.follow.VerifyFollowFromVideoPageAfterLogin;
import comcast.test.app.testCases.follow.VerifyUnfollowFromChannelPage;
import comcast.test.app.testCases.follow.VerifyUnfollowFromVideoPage;
import comcast.test.app.testCases.follow.VerifyUnfollowFromVideoPlayerControls;
import comcast.test.app.testCases.footerLink.VerifyAdChoicesLink;
import comcast.test.app.testCases.footerLink.VerifyClickingOnHomeFooterLinkFromHelpPageNavigatesHomePage;
import comcast.test.app.testCases.footerLink.VerifyClickingOnHomeFooterLinkNavigatesHomePage;
import comcast.test.app.testCases.footerLink.VerifyHelpLink;
import comcast.test.app.testCases.footerLink.VerifyPrivacyPolicyLink;
import comcast.test.app.testCases.footerLink.VerifyTermsOfUseLink;
import comcast.test.app.testCases.homePage.VerifyAllHomeCategoriesAreDisplayed;
import comcast.test.app.testCases.homePage.VerifyFeaturedChannelsAreDisplayed;
import comcast.test.app.testCases.homePage.VerifyFeaturedPlaylistsAreDisplayed;
import comcast.test.app.testCases.homePage.VerifyFeaturedVideosAreDisplayed;
import comcast.test.app.testCases.homePage.VerifyFooterlinksWithoutLogin;
import comcast.test.app.testCases.homePage.VerifyHeaderlinksWithLogin;
import comcast.test.app.testCases.homePage.VerifyHeaderlinksWithoutLogin;
import comcast.test.app.testCases.homePage.VerifyPresenceOfSocialSharingOptionsInHomePage;
import comcast.test.app.testCases.loginPageAndLogin.LoginToWatchableWithValidCredentials;
import comcast.test.app.testCases.loginPageAndLogin.LoginToWatchableWithValidUserName;
import comcast.test.app.testCases.loginPageAndLogin.VerifyChangePasswordFunctionality;
import comcast.test.app.testCases.loginPageAndLogin.VerifyChangePasswordMandatoryFields;
import comcast.test.app.testCases.loginPageAndLogin.VerifyForgotPasswordFunctionalityWithNoEmail;
import comcast.test.app.testCases.loginPageAndLogin.VerifyForgotPasswordFunctionalityWithValidEmail;
import comcast.test.app.testCases.loginPageAndLogin.VerifyLoginPageMandatoryFields;
import comcast.test.app.testCases.loginPageAndLogin.VerifyLogoutFromWatchable;
import comcast.test.app.testCases.loginPageAndLogin.VeriyForgotPasswordPageContent;
import comcast.test.app.testCases.loginPageAndLogin.VeriyLogInPageContent;
import comcast.test.app.testCases.loginPageAndLogin.VeriyResetPasswordPageContent;
import comcast.test.app.testCases.myWatchlist.VerifyMyWatchlistPageContentWhenVideosNotPresent;
import comcast.test.app.testCases.myWatchlist.VerifyMyWatchlistPageContentWhenVideosPresent;
import comcast.test.app.testCases.myWatchlist.VerifyNavigatingToMyWatchlistAfterLogin;
import comcast.test.app.testCases.myWatchlist.VeriyClickOnMyWatchlistWithOutLogin;
import comcast.test.app.testCases.playLists.VerifyAllPlayListsPageContents;
import comcast.test.app.testCases.playLists.VerifyNavigatingToVideoPageClickingOnPlayIconFromPlayList;
import comcast.test.app.testCases.playLists.VerifyPlayListShareOptionInVideoPage;
import comcast.test.app.testCases.playerVideoPage.VerifyControlsOnVideoPlayerDuringPause;
import comcast.test.app.testCases.playerVideoPage.VerifyControlsOnVideoPlayerDuringPlay;
import comcast.test.app.testCases.playerVideoPage.VerifyMetadataDisplayedMouseOverOnVideo;
import comcast.test.app.testCases.playerVideoPage.VerifyNavigatingToChannelPageFromVideoPage;
import comcast.test.app.testCases.playerVideoPage.VerifyNavigatingToShowPageOnClickingShowTitleFromUpNextSection;
import comcast.test.app.testCases.playerVideoPage.VerifyNavigatingToVideoPageFromChannelPage;
import comcast.test.app.testCases.playerVideoPage.VerifyNavigatingToVideoPageFromHomePage;
import comcast.test.app.testCases.playerVideoPage.VerifyNavigatingToVideoPageFromMyWatchlistPage;
import comcast.test.app.testCases.playerVideoPage.VerifyNavigatingToVideoPageFromSearchResultPage;
import comcast.test.app.testCases.playerVideoPage.VerifyPauseFunctionality;
import comcast.test.app.testCases.playerVideoPage.VerifyReportAfterLogin;
import comcast.test.app.testCases.playerVideoPage.VerifyUpNexSectionInVideoPage;
import comcast.test.app.testCases.playerVideoPage.VerifyVideoContentsWhenPlayPaused;
import comcast.test.app.testCases.providerPage.VerifyNavigationToProviderPage;
import comcast.test.app.testCases.providerPage.VerifyProviderDetailPageContent;
import comcast.test.app.testCases.search.VerifySearchResultPageContentWithOutResult;
import comcast.test.app.testCases.search.VerifySearchResultPageContentWithResult;
import comcast.test.app.testCases.signUp.RegToWatchableWithoutProvidingAnyDetails;
import comcast.test.app.testCases.signUp.VeriySignUpPageContent;
import comcast.test.app.testCases.socialSharing.VerifyPresenceOfSocialSharingFunctionalityInVideoControls;

@RunWith(Suite.class)
@Suite.SuiteClasses({

		// Home Page Test Cases
		VerifyAllHomeCategoriesAreDisplayed.class,
		VerifyHeaderlinksWithoutLogin.class,
		VerifyHeaderlinksWithLogin.class,
		VerifyFooterlinksWithoutLogin.class,
		VerifyFeaturedPlaylistsAreDisplayed.class,
		VerifyFeaturedVideosAreDisplayed.class,
		VerifyFeaturedChannelsAreDisplayed.class,
		VerifyPresenceOfSocialSharingOptionsInHomePage.class,

		// Login functionality Test Cases
		VeriyLogInPageContent.class,
		VerifyLoginPageMandatoryFields.class,
		LoginToWatchableWithValidCredentials.class,
		VerifyLogoutFromWatchable.class,
		LoginToWatchableWithValidUserName.class,

		// Forgot Password Test Cases
		VeriyForgotPasswordPageContent.class,
		VerifyForgotPasswordFunctionalityWithNoEmail.class,
		VerifyForgotPasswordFunctionalityWithValidEmail.class,

		// Reset Password Test Cases
		VeriyResetPasswordPageContent.class,
		VerifyChangePasswordMandatoryFields.class,
		VerifyChangePasswordFunctionality.class,

		// Sign Up Test Cases
		VeriySignUpPageContent.class,
		RegToWatchableWithoutProvidingAnyDetails.class,

		// Contact US Test Cases
		VeriyContactUsPageContent.class,
		VerifyContactUsMandatoryFields.class,

		// All Shows Test Cases
		VeriyAllChannelsPage.class,

		// Footer Test Cases
		VerifyClickingOnHomeFooterLinkNavigatesHomePage.class,
		VerifyHelpLink.class,
		VerifyPrivacyPolicyLink.class,
		VerifyTermsOfUseLink.class,
		VerifyAdChoicesLink.class,
		VerifyClickingOnHomeFooterLinkFromHelpPageNavigatesHomePage.class,

		// Show Detail Page Test Cases
		VerifyNavigationToChannelPage.class,
		VerifyChannelDetailPageContent.class,

		// Provider Detail Page Test Cases
		VerifyNavigationToProviderPage.class,
		VerifyProviderDetailPageContent.class,

		// Search Test Cases
		VerifySearchResultPageContentWithResult.class,
		VerifySearchResultPageContentWithOutResult.class,

		// My Shows list test cases

		VerifyNavigatingToMyWatchlistAfterLogin.class,
		VeriyClickOnMyWatchlistWithOutLogin.class,
		VerifyMyWatchlistPageContentWhenVideosPresent.class,
		VerifyMyWatchlistPageContentWhenVideosNotPresent.class,

		// Follow US test cases
		VerifyFollowFromChannelPageWithoutLogin.class,
		VerifyFollowFromChannelPageAfterLogin.class,
		VerifyFollowFromVideoPageAfterLogin.class,
		VerifyFollowFromVideoControlAfterLogin.class,
		VerifyUnfollowFromChannelPage.class,
		VerifyUnfollowFromVideoPage.class,
		VerifyUnfollowFromVideoPlayerControls.class,

		// Social Sharing test cases

		VerifyPresenceOfSocialSharingFunctionalityInVideoControls.class,

		// Play Lists test cases

		VerifyAllPlayListsPageContents.class,
		VerifyNavigatingToVideoPageClickingOnPlayIconFromPlayList.class,
		VerifyPlayListShareOptionInVideoPage.class,

		// Video Page test cases
		VerifyNavigatingToVideoPageFromHomePage.class,
		VerifyNavigatingToVideoPageFromChannelPage.class,
		VerifyNavigatingToVideoPageFromSearchResultPage.class,
		VerifyNavigatingToVideoPageFromMyWatchlistPage.class,
		VerifyControlsOnVideoPlayerDuringPlay.class,
		VerifyControlsOnVideoPlayerDuringPause.class,
		VerifyNavigatingToChannelPageFromVideoPage.class,
		VerifyPauseFunctionality.class, VerifyReportAfterLogin.class,
		VerifyVideoContentsWhenPlayPaused.class,
		VerifyUpNexSectionInVideoPage.class,
		VerifyNavigatingToShowPageOnClickingShowTitleFromUpNextSection.class,
		VerifyMetadataDisplayedMouseOverOnVideo.class,

})
public class runSeleniumTestCasesForSmokeQA {

	@AfterClass
	public static void tearDown() throws FileNotFoundException, IOException,
			InterruptedException {
		// Changes the browser for multiple browser execution.
		// @Note: Comment if single browser
		// TestDataGenerator.ChangeBrowser();
	}
}
