package comcast.test.config.configServices.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UrlFactoryUtil extends BaseTest {
	private static UrlFactoryUtil instance;

	private UrlFactoryUtil() {
	}

	static TestDataGenerator proUtil;
	String url = "";

	public static synchronized UrlFactoryUtil getInstance()
			throws FileNotFoundException, IOException {
		proUtil = new TestDataGenerator();
		proUtil.load(new FileInputStream(new File("com/data.properties")));

		if (instance == null)
			instance = new UrlFactoryUtil();

		return instance;
	}

	public static String getLoginUrl() {
		return proUtil.getProperty("BASEURL")
				+ "/api/authentication/user/login.json";
	}

	// User Authentication URL
	public static String getAuthenticationURL() {
		// return "http://107.20.31.47:9000/api/web/users/authenticate";
		return "http://qafabric.demo.xidio.com/api/web/users/authenticate";
		// return
		// "http://prod-fabric-lb-14472425.us-east-1.elb.amazonaws.com/api/web/users/authenticate";
	}

	public String getFeaturedURL() {
		return proUtil.getProperty("BASEURL") + "/api/web/curated/featured";
	}

	public String getPopularURL() {
		return proUtil.getProperty("BASEURL") + "/api/web/curated/channels";
	}

	public String getChannelDetailsURL(String id) {
		return proUtil.getProperty("BASEURL") + "/api/web/channels/" + id + "";
	}

	public String getChannelAllVideosURL(String id) {
		return proUtil.getProperty("BASEURL") + "/api/web/channels/" + id
				+ "/allvideos";
	}

	public String getSubShowURL(String channelId) {
		return proUtil.getProperty("BASEURL") + "/api/web/channels/"
				+ channelId + "/shows?pageNum=1&pageSize=10";
	}

	public String getEpisodeCountURL(String id) {
		return "http://goldengate:SomeSuperSecretLetters@demo3-api-lb-1761503566.us-east-1.elb.amazonaws.com/api/web/search/categories/"
				+ id + "/assets";
	}

	public String getVideoDetailsURL(String Id, int size) {
		return proUtil.getProperty("BASEURL") + "/api/web/shows/" + Id
				+ "/videos?pageNum=1&pageSize=" + size + "";
	}

	public String getGenreAllEpisodesURL(String Id) {
		return proUtil.getProperty("BASEURL") + "/api/web/genres/" + Id
				+ "/videos";
	}

	public String getGenrePopularURL(String Id) {
		return proUtil.getProperty("BASEURL") + "/api/web/genres/" + Id
				+ "/popular-channels-and-shows";
	}

	public String getPopularShowsURL() {
		return proUtil.getProperty("BASEURL") + "/api/web/curated/shows";
	}

	public String getUpNextURL() {
		return "http://qafabric.demo.xidio.com/api/web/users/upnextlist/homepage";
		// return
		// "http://prod-fabric-lb-14472425.us-east-1.elb.amazonaws.com/api/web/users/upnextlist/homepage";
		// return
		// "http://api.projecthelen.net:3000/api/user/upnext?userId="+userID+"&sessionId="+sessionID+"&platform=web&page=1&size=10";
	}

	/*
	 * public String getUpNextCategoryURL() { return
	 * "http://api.stage2.xidio.com/api/web/search/categories/100/?sort=categoryUpdateTime+desc&size=20"
	 * ; }
	 */

	public String getUpNextAssetsURL(String categoryID) {
		return "http://goldengate:SomeSuperSecretLetters@demo3-api-lb-1761503566.us-east-1.elb.amazonaws.com/api/web/search/categories/"
				+ categoryID + "/assets?size=10";
	}

	public String getSubscriptionsURL() {
		// return
		// proUtil.getProperty("BASEURL")+"/api/web/user/"+userId+"/access/categories.json";
		// return
		// proUtil.getProperty("BASEURL")+"/api/web/users/subscriptions/channels";
		// return
		// "http://prod-fabric-lb-14472425.us-east-1.elb.amazonaws.com/api/web/users/subscriptions/channels";
		return "http://qafabric.demo.xidio.com/api/web/users/subscriptions/channels";
	}

	public String getSubscriptionsGenreURL() {
		// return
		// proUtil.getProperty("BASEURL")+"/api/web/user/"+userId+"/access/categories.json";
		return proUtil.getProperty("BASEURL")
				+ "/api/web/users/subscriptions/genres";
	}

	public String getBundleChannelsURL(String id, int size) {
		return proUtil.getProperty("BASEURL")
				+ "/api/web/search/categories/100?start=0&query=prodGroupId%3A"
				+ id + "&size=" + size + "";
	}

	public String getBundleShowCountURL(String Id) {
		return "http://www.projecthelen.net:3000/api/bundles/" + Id + "";
	}

	public String getSSOToken(String userId, String sessionId) {
		return "http://api.projecthelen.net:3000/api/user/token/?userId="
				+ userId + "&sessionId=" + sessionId + "&platform=web";
	}

	public static String getGenresAuthenticationUrl() {
		// return "http://107.20.31.47:9000/api/web/authenticate";
		// return proUtil.getProperty("BASEURL")+"/api/web/authenticate";
		return "http://qafabric.demo.xidio.com/api/web/authenticate";
		// return
		// "http://prod-fabric-lb-14472425.us-east-1.elb.amazonaws.com/api/web/authenticate";
	}

	public static String getGenresUrl() {
		return proUtil.getProperty("BASEURL") + "/api/web/genres";
	}

	// Genre channels URL
	public String getGenresShowUrl(String id) {
		return proUtil.getProperty("BASEURL") + "/api/web/genres/" + id
				+ "/shows";
	}

	// Genre channels URL
	public String getGenresChannelURL(String id) {
		return proUtil.getProperty("BASEURL") + "/api/web/genres/" + id
				+ "/channels?filterEmptyChannels=true";
	}

	// Genre subscribed channels URL
	public String getSubscribedGenresChannelURL(String id) {
		return proUtil.getProperty("BASEURL")
				+ "/api/web/users/subscriptions/genres/" + id + "/channels";
	} // Genre episode URL

	public String getGenresEpisodesURL(String id) {
		return proUtil.getProperty("BASEURL") + "/api/web/genres/" + id
				+ "/videos";
	}

	// Channel Search Details API
	public String getChannelSeachURL(String keyword, int size) {
		// return
		// "http://api.stage2.xidio.com/api/web/search/categories/100?sort=relevancy&text=CNN World&query=levelName:SHOW=&size=25";

		// return
		// "http://api.stage2.xidio.com/api/web/search/categories/100?sort=relevancy&text="
		// + keyword + "&query=levelName:SHOW=&size=" + size + "";

		/*
		 * return
		 * "http://demo3-fabric-lb-1756645713.us-east-1.elb.amazonaws.com/api/web/search/categories/100?sort=relevancy&text="
		 * + keyword + "&query=levelName:SHOW=&size=" + size + "";
		 */
		return "http://goldengate:SomeSuperSecretLetters@demo3-api-lb-1761503566.us-east-1.elb.amazonaws.com/api/web/search/categories/100?sort=relevancy&text="
				+ keyword + "&query=levelName:SHOW=&size=" + size + "";

		// return
		// "http://goldengate:SomeSuperSecretLetters@demo3-api-lb-1761503566.us-east-1.elb.amazonaws.com/api/web/search/categories/100?sort=relevancy&text="
		// + keyword + "&query=levelName:SHOW=&size=" + size + "";

		// return
		// "http://api.stage2.xidio.com/api/web/search/categories/100/?text="+keyword+"&query=levelName:SHOW&size="+size+"";
	}

	// Show Search Details API
	public String getShowSeachURL(String keyword, int size) {
		// return
		// "http://api.stage2.xidio.com/api/web/search/categories/100?sort=relevancy&text="
		// + keyword + "&size=" + size + "&query=levelName:SUB_SHOW";
		return "http://goldengate:SomeSuperSecretLetters@demo3-api-lb-1761503566.us-east-1.elb.amazonaws.com/api/web/search/categories/100?sort=relevancy&text="
				+ keyword + "&size=" + size + "&query=levelName:SUB_SHOW";
		// return
		// "http://api.stage2.xidio.com/api/web/search/categories/100/?text="+keyword+"&query=(levelName:SUB_SHOW)&size="+size+"";
	}

	// Episode Search Details API
	public String getEpisodeSeachURL(String keyword, int size) {
		// return
		// "http://api.stage2.xidio.com/api/web/search/categories/100/assets/?sort=relevancy&text="
		// + keyword + "&size=" + size + "";
		return "http://goldengate:SomeSuperSecretLetters@demo3-api-lb-1761503566.us-east-1.elb.amazonaws.com/api/web/search/categories/100/assets/?sort=relevancy&text="
				+ keyword + "&size=" + size + "";
		// return
		// "http://api.stage2.xidio.com/api/web/search/categories/100/assets/?text="+keyword+"&size="+size+"";
	}

	// Publisher details API.
	public String getPublisherDetailsURL(String Id) {
		return proUtil.getProperty("BASEURL") + "/api/web/publishers/" + Id
				+ "";
	}

	// Publisher details API.
	public String getPublisherDetailsResponse(String publisherURL) {
		return proUtil.getProperty("BASEURL") + publisherURL + "";
	}

	// Publisher Channels details API.
	public String getPublisherChannelsDetailsURL(String publisherId) {
		return proUtil.getProperty("BASEURL") + "/api/web/publishers/"
				+ publisherId + "/channels";
	}

	// Publisher Genre category Channels details API.
	public String getPublisherGenreChannelsDetailsURL(String publisherId,
			String genreId) {
		return proUtil.getProperty("BASEURL") + "/api/web/publishers/"
				+ publisherId + "/genres/" + genreId + "/channels";
	}

	// IOS and Android

	public String getIOSFeaturedURL() {
		return proUtil.getProperty("BASEURL") + "/api/stb/curated/featured";
	}

	public String getAndroidFeaturedURL() {
		return proUtil.getProperty("BASEURL") + "/api/iptv/curated/featured";
	}
}
