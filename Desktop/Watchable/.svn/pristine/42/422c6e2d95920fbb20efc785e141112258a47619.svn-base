package comcast.test.config.configServices.utils;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.config.dataServices.vo.VideoDetails;

public class RestAPIServices {
	public static String featuredResponse;
	public static String popularResponse;
	// for
	private static HttpContext context;

	static {
		/*
		 * try {
		 */
		// featuredResponse
		// =DomParserXPATH.getCategories(UrlFactoryUtil.getInstance().getFeaturedURL());
		// popularResponse
		// =DomParserXPATH.getCategories(UrlFactoryUtil.getInstance().getPopularURL());

		context = new BasicHttpContext();
		context.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());

		/*
		 * } catch (XPathExpressionException e) { e.printStackTrace(); } catch
		 * (ParserConfigurationException e) { e.printStackTrace(); } catch
		 * (SAXException e) { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

	private static List<VideoDetails> featuredResponseDetails;
	private static List<VideoDetails> featuredBundlesList;
	private static List<VideoDetails> allFeaturedShowsList;
	private static List<VideoDetails> featuredChannelListHasOnlyEpisodesNoShows;
	private static List<VideoDetails> featuredShowsList;
	private static List<VideoDetails> featuredSubShowList;
	private static List<VideoDetails> featuredVideoList;
	private static List<VideoDetails> featuredVideoListDirectlyUnderChannels;

	/*
	 * Name: FeaturedAPI Module: STORE/HOME Page Description: This method
	 * provides Featured API Response Details.
	 */
	public static Map<String, List<VideoDetails>> FeaturedAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		featuredResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getFeaturedURL());
		List<VideoDetails> featuredAPIResponseList = JsonParser
				.parseHomeFeaturedChannelsResponse(featuredResponse);

		featuredResponseDetails = new ArrayList<VideoDetails>();
		featuredBundlesList = new ArrayList<VideoDetails>();
		allFeaturedShowsList = new ArrayList<VideoDetails>();
		featuredShowsList = new ArrayList<VideoDetails>();
		featuredChannelListHasOnlyEpisodesNoShows = new ArrayList<VideoDetails>();
		featuredSubShowList = new ArrayList<VideoDetails>();
		featuredVideoList = new ArrayList<VideoDetails>();
		featuredVideoListDirectlyUnderChannels = new ArrayList<VideoDetails>();

		if (featuredAPIResponseList != null
				&& featuredAPIResponseList.size() <= 20) {
			for (VideoDetails videoDetails : featuredAPIResponseList) {
				featuredResponseDetails.add(videoDetails);

				if (videoDetails.getContentType() != null
						&& videoDetails.getContentType().equalsIgnoreCase(
								"bundle")) {
					featuredBundlesList.add(videoDetails);
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					allFeaturedShowsList.add(videoDetails);
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					int showCount = 0;
					int episodeCount = 0;
					String featuredChannelId = videoDetails.getId();
					String subShowResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getSubShowURL(featuredChannelId));
					List<VideoDetails> featuredSubShowsList = JsonParser
							.parseHomeFeaturedShowsResponse(subShowResponse);

					List<VideoDetails> featuredShowsListUnderChannel = new ArrayList<VideoDetails>();
					if (featuredSubShowsList != null
							&& featuredSubShowsList.size() < 5) {
						for (VideoDetails subShowList : featuredSubShowsList) {
							featuredShowsListUnderChannel.add(subShowList);
						}
					} else {
						for (int index = 0; index < 5; index++) {
							VideoDetails subShowList = featuredSubShowsList
									.get(index);
							featuredShowsListUnderChannel.add(subShowList);
						}
					}

					if (subShowResponse != null
							&& subShowResponse.contains("category")) {
						String subShowRepsponseCount = JsonParser
								.parseHomeFeaturedShowCountResponse(subShowResponse);
						showCount = Integer
								.parseInt((String) (subShowRepsponseCount == null ? "0"
										: subShowRepsponseCount));
						if (showCount > 0) {
							String episodeCountResponse = DomParserXPATH
									.getCategories(UrlFactoryUtil.getInstance()
											.getEpisodeCountURL(
													featuredShowsListUnderChannel
															.get(0).getId()));
							if (episodeCountResponse != null
									&& episodeCountResponse.length() > 0) {
								episodeCount = Integer
										.parseInt(JsonParser
												.parseEpisodeCountForChannelResponse(episodeCountResponse));
								if (episodeCount > 0)
									featuredShowsList.add(videoDetails);
							}
						}
					}
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					int showCount = 0;
					int episodeCount = 0;
					String showCountResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getSubShowURL(videoDetails.getId()));
					if (showCountResponse != null
							&& showCountResponse.length() > 0) {
						String subShowRepsponseCount = JsonParser
								.parseHomeFeaturedShowCountResponse(showCountResponse);
						showCount = Integer
								.parseInt((String) (subShowRepsponseCount == null ? "0"
										: subShowRepsponseCount));
						if (showCount == 0) {
							String episodeCountResponse = DomParserXPATH
									.getCategories(UrlFactoryUtil.getInstance()
											.getEpisodeCountURL(
													videoDetails.getId()));
							if (episodeCountResponse != null
									&& episodeCountResponse.length() > 0) {
								episodeCount = Integer
										.parseInt(JsonParser
												.parseEpisodeCountForChannelResponse(episodeCountResponse));
								if (episodeCount > 0) {
									/* monitor this code */
									String videoResponse = DomParserXPATH
											.getCategories(UrlFactoryUtil
													.getInstance()
													.getVideoDetailsURL(
															videoDetails
																	.getId(),
															12));
									List<VideoDetails> featuredVideoList = JsonParser
											.parseHomeFeaturedVideosResponse(videoResponse);

									if (featuredVideoList != null
											&& featuredVideoList.size() < 5) {
										for (VideoDetails videoList : featuredVideoList) {
											featuredVideoListDirectlyUnderChannels
													.add(videoList);
										}
									} else {
										for (int index = 0; index < 5; index++) {
											VideoDetails videoList = featuredVideoList
													.get(index);
											featuredVideoListDirectlyUnderChannels
													.add(videoList);
										}
									}/* monitor this code */
									featuredChannelListHasOnlyEpisodesNoShows
											.add(videoDetails);
								}
							}
						}
					}
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SUB_SHOW")) {
					featuredSubShowList.add(videoDetails);
				}
				if (videoDetails.getContentType() != null
						&& videoDetails.getContentType().equalsIgnoreCase(
								"asset")) {
					featuredVideoList.add(videoDetails);
				}
			}
		} else {
			for (int index = 0; index < 20; index++) {
				VideoDetails videoDetails = featuredAPIResponseList.get(index);
				featuredResponseDetails.add(videoDetails);

				if (videoDetails.getContentType() != null
						&& videoDetails.getContentType().equalsIgnoreCase(
								"bundle")) {
					featuredBundlesList.add(videoDetails);
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					allFeaturedShowsList.add(videoDetails);
				}

				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					int showCount = 0;
					int episodeCount = 0;
					String featuredChannelId = videoDetails.getId();
					String subShowResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getSubShowURL(featuredChannelId));
					List<VideoDetails> featuredSubShowsList = JsonParser
							.parseHomeFeaturedShowsResponse(subShowResponse);

					List<VideoDetails> featuredShowsListUnderChannel = new ArrayList<VideoDetails>();
					if (featuredSubShowsList != null
							&& featuredSubShowsList.size() < 5) {
						for (VideoDetails subShowList : featuredSubShowsList) {
							featuredShowsListUnderChannel.add(subShowList);
						}
					} else {
						for (int showListIndex = 0; showListIndex < 5; showListIndex++) {
							VideoDetails subShowList = featuredSubShowsList
									.get(showListIndex);
							featuredShowsListUnderChannel.add(subShowList);
						}
					}

					if (subShowResponse != null
							&& subShowResponse.contains("category")) {
						String subShowRepsponseCount = JsonParser
								.parseHomeFeaturedShowCountResponse(subShowResponse);
						showCount = Integer
								.parseInt((String) (subShowRepsponseCount == null ? "0"
										: subShowRepsponseCount));
						if (showCount > 0) {
							String episodeCountResponse = DomParserXPATH
									.getCategories(UrlFactoryUtil.getInstance()
											.getEpisodeCountURL(
													featuredShowsListUnderChannel
															.get(0).getId()));
							if (episodeCountResponse != null
									&& episodeCountResponse.length() > 0) {
								episodeCount = Integer
										.parseInt(JsonParser
												.parseEpisodeCountForChannelResponse(episodeCountResponse));
								if (episodeCount > 0)
									featuredShowsList.add(videoDetails);
							}
						}
					}
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					int showCount = 0;
					int episodeCount = 0;
					String showCountResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getSubShowURL(videoDetails.getId()));
					if (showCountResponse != null
							&& showCountResponse.length() > 0) {
						String subShowRepsponseCount = JsonParser
								.parseHomeFeaturedShowCountResponse(showCountResponse);
						showCount = Integer
								.parseInt((String) (subShowRepsponseCount == null ? "0"
										: subShowRepsponseCount));
						if (showCount == 0) {
							String episodeCountResponse = DomParserXPATH
									.getCategories(UrlFactoryUtil.getInstance()
											.getEpisodeCountURL(
													videoDetails.getId()));
							if (episodeCountResponse != null
									&& episodeCountResponse.length() > 0) {
								episodeCount = Integer
										.parseInt(JsonParser
												.parseEpisodeCountForChannelResponse(episodeCountResponse));
								if (episodeCount > 0) {
									/*
									 * monitor this code and comment if not
									 * required- Jamuna
									 */

									String videoResponse = DomParserXPATH
											.getCategories(UrlFactoryUtil
													.getInstance()
													.getVideoDetailsURL(
															videoDetails
																	.getId(),
															12));
									List<VideoDetails> featuredVideoList = JsonParser
											.parseHomeFeaturedVideosResponse(videoResponse);
									if (featuredVideoList != null
											&& featuredVideoList.size() < 5) {
										for (VideoDetails videoList : featuredVideoList) {
											featuredVideoListDirectlyUnderChannels
													.add(videoList);
										}
									} else {
										for (int i = 0; i < 5; i++) {
											VideoDetails videoList = featuredVideoList
													.get(i);
											featuredVideoListDirectlyUnderChannels
													.add(videoList);
										}
									}
									/*
									 * monitor this code and comment if not
									 * required- Jamuna
									 */
									featuredChannelListHasOnlyEpisodesNoShows
											.add(videoDetails);
								}
							}
						}
					}
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SUB_SHOW")) {
					featuredSubShowList.add(videoDetails);
				}
				if (videoDetails.getContentType() != null
						&& videoDetails.getContentType().equalsIgnoreCase(
								"asset")) {
					featuredVideoList.add(videoDetails);
				}
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("featuredResponseList", featuredResponseDetails);
		finalMap.put("featuredBundleList", featuredBundlesList);
		finalMap.put("allFeaturedShowsList", allFeaturedShowsList);
		finalMap.put("featuredShowsList", featuredShowsList);
		finalMap.put("featuredChannelHasOnlyEpisodes",
				featuredChannelListHasOnlyEpisodesNoShows);
		finalMap.put("featuredSubShowsList", featuredSubShowList);
		finalMap.put("featuredVideoList", featuredVideoList);
		// finalMap.put("fvListDirectlyUnderChannels",
		// featuredVideoListDirectlyUnderChannels);

		return finalMap;
	}

	private static List<VideoDetails> popularBundlesList;
	private static List<VideoDetails> allPopularShowsList;
	private static List<VideoDetails> popularShowsList;
	private static List<VideoDetails> popularSubShowList;
	private static List<VideoDetails> popularChannelListHasOnlyEpisodes;
	private static List<VideoDetails> popularChannelHasZeroShowNoEpisodes;

	/*
	 * Name: popularAPI Module: HOME Page Description: This method provides
	 * Popular Channel API Response Details.
	 */
	public static List<VideoDetails> popularChannelAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		popularResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getPopularURL());
		List<VideoDetails> popularChannelAPIResponseList = JsonParser
				.parseHomePopularChannelsResponse(popularResponse);

		return popularChannelAPIResponseList;
	}

	private static List<VideoDetails> allSubscribedChannelsList;
	private static List<VideoDetails> subscribedChannelsList;

	/*
	 * Name: SubscriptionsAPI Module: SUBSCRIPTIONS Page Description: This
	 * method provides Subscriptions API Response Details.
	 */
	public static Map<String, List<VideoDetails>> SubscriptionsAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		allSubscribedChannelsList = new ArrayList<VideoDetails>();
		subscribedChannelsList = new ArrayList<VideoDetails>();

		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TestDataGenerator proUtil=new TestDataGenerator();

		/*
		 * proUtil.load(new FileInputStream(new File("com/data.properties")));
		 * String userName=proUtil.getProperty("USER_NAME"); String
		 * regPassword=proUtil.getProperty("REG_PASSWORD");
		 */
		String userName = UILablesRepo.USERNAME;
		String regPassword = UILablesRepo.PASSWORD;

		String bearerToken = null;

		try {
			bearerToken = userAuthentication(userName, regPassword,
					sessionToken);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String subscriptionsResponse = getBearerResponse(UrlFactoryUtil
				.getInstance().getSubscriptionsURL(), sessionToken, bearerToken);
		List<VideoDetails> subscriptionsDetails = JsonParser
				.parseGenresChannelsResponse(subscriptionsResponse);

		if (subscriptionsDetails != null) {
			int showCount = 0;
			int subscriptionLoopIndex = 5;
			if (subscriptionsDetails.size() < 5)
				subscriptionLoopIndex = subscriptionsDetails.size();
			else
				subscriptionLoopIndex = 5;

			for (int index = 0; index < subscriptionLoopIndex; index++) {
				VideoDetails publisherResult = subscriptionsDetails.get(index);
				allSubscribedChannelsList.add(publisherResult);

				showCount = (publisherResult.getNoOfShows());
				if (showCount > 0)
					subscribedChannelsList.add(publisherResult);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("subscribedChannelsList", subscribedChannelsList);
		finalMap.put("allSubscribedChannelsList", allSubscribedChannelsList);

		return finalMap;
	}

	/*
	 * Name: StorePageAPIs Module: STORE Page Description: This method provides
	 * Featured and Popular API's Details.
	 */
	public static Map<String, List<VideoDetails>> StorePageAPIs()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		// Calling Popular API.
		popularChannelAPI();

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("Featuredshow", featuredShowsList);
		finalMap.put("Popularshow", popularShowsList);

		return finalMap;
	}

	/*
	 * Name: StoreFeaturedAPI Module: STORE Page Description: This method
	 * provides Stored Featured, Show list and Video list Details.
	 */
	public static Map<String, List<VideoDetails>> StoreFeaturedAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredChannelDetails = featuredShowsList
				.get(XidioConstant.selectFeaturedChannel);
		String subShowResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getSubShowURL(featuredChannelDetails.getId()));
		List<VideoDetails> storeFeaturedSubShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowResponse);

		List<VideoDetails> storeFeaturedShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (storeFeaturedSubShowsList != null
				&& storeFeaturedSubShowsList.size() < 10) {
			for (VideoDetails videoDetails : storeFeaturedSubShowsList) {
				storeFeaturedShowsListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storeFeaturedSubShowsList
						.get(index);
				storeFeaturedShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails featuredSubShowDetails = storeFeaturedShowsListUnderChannel
				.get(XidioConstant.selectShow);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(
						featuredSubShowDetails.getId(), 12));
		List<VideoDetails> storeFeaturedVideoList = JsonParser
				.parseHomeFeaturedVideosResponse(videoResponse);

		List<VideoDetails> storeFeaturedVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (storeFeaturedVideoList != null
				&& storeFeaturedVideoList.size() < 10) {
			for (VideoDetails videoDetails : storeFeaturedVideoList) {
				storeFeaturedVideoListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storeFeaturedVideoList.get(index);
				storeFeaturedVideoListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails VideoUnderSubShowOfStorePage = featuredSubShowList.get(0);
		String homePageSubShowVideoResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getVideoDetailsURL(
						VideoUnderSubShowOfStorePage.getId(), 12));
		List<VideoDetails> storePageSubShowVideos = JsonParser
				.parseHomeFeaturedVideosResponse(homePageSubShowVideoResponse);

		List<VideoDetails> storePageSubShowVideoList = new ArrayList<VideoDetails>();
		if (storePageSubShowVideos != null
				&& storePageSubShowVideos.size() <= 10) {
			for (VideoDetails videoDetails : storePageSubShowVideos) {
				storePageSubShowVideoList.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storePageSubShowVideos.get(index);
				storePageSubShowVideoList.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("BundlesList", featuredBundlesList);
		finalMap.put("show", featuredShowsList);
		finalMap.put("subshow", featuredSubShowList);
		finalMap.put("subshowVideoList", storePageSubShowVideoList);
		finalMap.put("video", storeFeaturedVideoListUnderChannel);
		finalMap.put("showsUnderChannel", storeFeaturedShowsListUnderChannel);
		return finalMap;
	}

	/*
	 * Name: storeShowCountUnderChannel Module: STORE Page Description: This
	 * method provides Show count under Channel details page.
	 */
	public static String storeShowCountUnderChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredChannelDetails = featuredShowsList.get(0);
		String subShowCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						featuredChannelDetails.getId()));
		return JsonParser
				.parseStoreFeaturedShowCountResponse(subShowCountResponse);
	}

	/*
	 * Name: episodeListUnderChannel Module: STORE/HOME Page Description: This
	 * method provides Episode count for a Channel.
	 */
	public static List<VideoDetails> episodeListUnderChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredChannelDetails = featuredShowsList
				.get(XidioConstant.selectFeaturedChannel);
		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getEpisodeCountURL(
						featuredChannelDetails.getId()));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		featuredChannelDetails.setNoOfHits(noOfHits);

		List<VideoDetails> episodeCountUnderChanne = new ArrayList<VideoDetails>();
		episodeCountUnderChanne.add(featuredChannelDetails);

		return episodeCountUnderChanne;
	}

	/*
	 * Name: episodeListUnderPopularChannel Module: HOME Page Description: This
	 * method provides Episode count for a Popular Channel.
	 */
	public static List<VideoDetails> episodeListUnderPopularChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API.
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList
				.get(XidioConstant.selectPopularChannel);
		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getEpisodeCountURL(
						popularChannelDetails.getId()));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		popularChannelDetails.setNoOfHits(noOfHits);

		List<VideoDetails> episodeCountUnderPopularChanne = new ArrayList<VideoDetails>();
		episodeCountUnderPopularChanne.add(popularChannelDetails);

		return episodeCountUnderPopularChanne;
	}

	/*
	 * Name: subscribedChannelEpisode Module: SUBSCRIPTIONS Page Description:
	 * This method provides Episode count for a Subscribed Channel.
	 */
	/*
	 * public static List<VideoDetails> subscribedChannelEpisode() throws
	 * XPathExpressionException, ParserConfigurationException, SAXException,
	 * IOException { //Calling Subscription API. subscriptionChannelAPIs();
	 * 
	 * VideoDetails featuredChannelDetails
	 * =subscriptionsShowsList.get(XidioConstant.selectSubscribedChannel);
	 * String episodeCountResponse
	 * =DomParserXPATH.getCategories(UrlFactoryUtil.getInstance
	 * ().getEpisodeCountURL(featuredChannelDetails.getId())); String noOfHits
	 * =JsonParser.parseEpisodeCountForChannelResponse(episodeCountResponse);
	 * featuredChannelDetails.setNoOfHits(noOfHits);
	 * 
	 * List<VideoDetails> episodeCountUnderChanne=new ArrayList<VideoDetails>();
	 * episodeCountUnderChanne.add(featuredChannelDetails);
	 * 
	 * return episodeCountUnderChanne; }
	 */

	/*
	 * Name: featuredEpisodeListUnderShows Module: HOME Page Description: This
	 * method provides Episode count for a Channel.
	 */
	public static List<VideoDetails> featuredEpisodeListUnderShows()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredChannelDetails = featuredShowsList.get(0);

		String subShowResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getSubShowURL(featuredChannelDetails.getId()));
		List<VideoDetails> storeFeaturedShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowResponse);
		VideoDetails featuredShowDetails = storeFeaturedShowsList.get(0);

		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getEpisodeCountURL(
						featuredShowDetails.getId()));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		featuredShowDetails.setNoOfHits(noOfHits);

		List<VideoDetails> episodeCountUnderChanne = new ArrayList<VideoDetails>();

		episodeCountUnderChanne.add(featuredChannelDetails);
		episodeCountUnderChanne.add(featuredShowDetails);

		return episodeCountUnderChanne;

	}

	/*
	 * Name: featuredEpisodeListUnderShows Module: STORE/HOME Page Description:
	 * This method provides Episode count for a Channel.
	 */
	public static Map<String, List<VideoDetails>> videoCountUnderShows()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredChannelDetails = featuredSubShowList.get(0);
		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getVideoDetailsURL(
						featuredChannelDetails.getId(), 0));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		featuredChannelDetails.setNoOfHits(noOfHits);

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("show", featuredShowsList);
		finalMap.put("subshow", featuredSubShowList);

		return finalMap;
	}

	/*
	 * Name: storeShowListUnderChannel Module: STORE Page Description: This
	 * method provides Shows list under Channel details page.
	 */
	public static Map<String, List<VideoDetails>> storeShowListUnderChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		String ShowCategoryID = featuredShowsList.get(0).getId();
		String subShowListResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						ShowCategoryID));
		List<VideoDetails> storeFeaturedShowList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowListResponse);

		List<VideoDetails> homeFeaturedShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (storeFeaturedShowList != null && storeFeaturedShowList.size() < 10) {
			for (VideoDetails videoDetails : storeFeaturedShowList) {
				homeFeaturedShowsListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storeFeaturedShowList.get(index);
				homeFeaturedShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails featuredShowDetails = storeFeaturedShowList.get(0);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(featuredShowDetails.getId(),
						12));
		List<VideoDetails> storeFeaturedVideoList = JsonParser
				.parseHomeFeaturedVideosResponse(videoResponse);

		List<VideoDetails> homeFeaturedVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (storeFeaturedVideoList != null
				&& storeFeaturedVideoList.size() < 10) {
			for (VideoDetails videoDetails : storeFeaturedVideoList) {
				homeFeaturedVideoListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storeFeaturedVideoList.get(index);
				homeFeaturedVideoListUnderChannel.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("bundle", featuredBundlesList);
		finalMap.put("show", featuredShowsList);
		finalMap.put("subshow", featuredSubShowList);
		finalMap.put("showsUnderChannel", homeFeaturedShowsListUnderChannel);
		finalMap.put("video", homeFeaturedVideoListUnderChannel);

		return finalMap;
	}

	/*
	 * Name: StorePopularAPI Module: STORE Page > Popular Channel Description:
	 * This method provides Channel List, Shows list and Video List for Popular
	 * Channel Category.
	 */
	public static Map<String, List<VideoDetails>> StorePopularAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API.
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList.get(0);
		String subShowResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getSubShowURL(popularChannelDetails.getId()));
		List<VideoDetails> storePopularSubShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowResponse);

		List<VideoDetails> storePopularShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (storePopularSubShowsList != null
				&& storePopularSubShowsList.size() <= 10) {
			for (VideoDetails videoDetails : storePopularSubShowsList) {
				storePopularShowsListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storePopularSubShowsList.get(index);
				storePopularShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails PopularShowDetails = storePopularShowsListUnderChannel
				.get(0);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(PopularShowDetails.getId(),
						12));
		List<VideoDetails> storePopularVideoList = JsonParser
				.parseStorePopularVideosResponse(videoResponse);

		List<VideoDetails> storePopularVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (storePopularVideoList != null && storePopularVideoList.size() <= 10) {
			for (VideoDetails videoDetails : storePopularVideoList) {
				storePopularVideoListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storePopularVideoList.get(index);
				storePopularVideoListUnderChannel.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("bundle", popularBundlesList);
		finalMap.put("show", popularShowsList);
		finalMap.put("subshow", popularSubShowList);
		finalMap.put("showsUnderChannel", storePopularShowsListUnderChannel);
		finalMap.put("video", storePopularVideoListUnderChannel);

		return finalMap;
	}

	/*
	 * Name: showCountUnderPopularChannel Module: STORE Page > Popular Channel
	 * Description: This method provides Store Popular Channel list and Shows
	 * count.
	 */
	public static String showCountUnderPopularChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API.
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList.get(0);
		String subShowCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						popularChannelDetails.getId()));
		return JsonParser
				.parseHomePopularShowCountResponse(subShowCountResponse);
	}

	/*
	 * Name: popularEpisodeListUnderShows Module: STORE/HOME Page Description:
	 * This method provides Episode count for a Channel.
	 */
	public static List<VideoDetails> popularEpisodeListUnderShows()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API.
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList
				.get(XidioConstant.selectPopularChannel);
		String subShowResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getSubShowURL(popularChannelDetails.getId()));
		List<VideoDetails> storeFeaturedShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowResponse);
		VideoDetails popularSubShowDetails = storeFeaturedShowsList
				.get(XidioConstant.selectShow);

		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getEpisodeCountURL(
						popularSubShowDetails.getId()));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		popularChannelDetails.setNoOfHits(noOfHits);

		List<VideoDetails> episodeCountUnderChannel = new ArrayList<VideoDetails>();
		episodeCountUnderChannel.add(popularChannelDetails);
		episodeCountUnderChannel.add(popularSubShowDetails);

		return episodeCountUnderChannel;
	}

	/*
	 * Name: storePopularShowListUnderChannel Module: STORE Page > Popular
	 * Channel Description: This method provides Store Popular Show list under
	 * Channel.
	 */
	public static Map<String, List<VideoDetails>> storePopularShowListUnderChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API.
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList.get(0);

		String subShowListResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						popularChannelDetails.getId()));
		List<VideoDetails> storePopularShowList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowListResponse);

		List<VideoDetails> storePopularShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (storePopularShowList != null && storePopularShowList.size() <= 10) {
			for (VideoDetails videoDetails : storePopularShowList) {
				storePopularShowsListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storePopularShowList.get(index);
				storePopularShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails popularSubShowDetails = storePopularShowsListUnderChannel
				.get(0);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(
						popularSubShowDetails.getId(), 12));
		List<VideoDetails> storePopularVideoList = JsonParser
				.parseStorePopularVideosResponse(videoResponse);

		List<VideoDetails> storePopularVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (storePopularVideoList != null && storePopularVideoList.size() <= 10) {
			for (VideoDetails videoDetails : storePopularVideoList) {
				storePopularVideoListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storePopularVideoList.get(index);
				storePopularVideoListUnderChannel.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("bundle", popularBundlesList);
		finalMap.put("show", popularShowsList);
		finalMap.put("subshow", popularSubShowList);
		finalMap.put("showsUnderChannel", storePopularShowsListUnderChannel);
		finalMap.put("video", storePopularVideoListUnderChannel);

		return finalMap;
	}

	/* HOME Page API's */
	public static List<VideoDetails> homePageAPIs()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String featuredResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getFeaturedURL());
		List<VideoDetails> homeFeaturedChannelList = JsonParser
				.parseHomeFeaturedChannelsResponse(featuredResponse);
		VideoDetails homefeaturedChannelDetails = homeFeaturedChannelList
				.get(0);

		String popularShowResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance()
						.getPopularShowsURL());
		List<VideoDetails> homePopularShowslList = JsonParser
				.parseHomePopularShowResponse(popularShowResponse);
		VideoDetails homePopularShowDetails = homePopularShowslList.get(0);

		String popularChannelResponse1 = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getPopularURL());
		List<VideoDetails> homePopularChannelList = JsonParser
				.parseHomePopularChannelsResponse(popularChannelResponse1);
		VideoDetails homePopularChannelDetails = homePopularChannelList.get(0);

		List<VideoDetails> homePageVideoDetailsList = new ArrayList<VideoDetails>();

		homePageVideoDetailsList.add(homefeaturedChannelDetails);
		homePageVideoDetailsList.add(homePopularShowDetails);
		homePageVideoDetailsList.add(homePopularChannelDetails);

		return homePageVideoDetailsList;
	}

	/*
	 * HOME Featured API's
	 */
	public static Map<String, List<VideoDetails>> HomeFeaturedAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		nFeaturedAPI();

		VideoDetails featuredChannelDetails = nfeaturedShowsList
				.get(XidioConstant.selectFeaturedChannel);
		String subShowResponse = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getSubShowURL(featuredChannelDetails.getId()),
				sessionToken);
		List<VideoDetails> homeFeaturedSubShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowResponse);

		List<VideoDetails> homeFeaturedShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (homeFeaturedSubShowsList != null
				&& homeFeaturedSubShowsList.size() <= 10) {
			for (VideoDetails videoDetails : homeFeaturedSubShowsList) {
				homeFeaturedShowsListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = homeFeaturedSubShowsList.get(index);
				homeFeaturedShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails featuredSubShowDetails = homeFeaturedShowsListUnderChannel
				.get(0);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(
						featuredSubShowDetails.getId(), 12));
		List<VideoDetails> storeFeaturedVideoList = JsonParser
				.parseHomeFeaturedVideosResponse(videoResponse);

		List<VideoDetails> homeFeaturedVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (storeFeaturedVideoList != null
				&& storeFeaturedVideoList.size() <= 10) {
			for (VideoDetails videoDetails : storeFeaturedVideoList) {
				homeFeaturedVideoListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storeFeaturedVideoList.get(index);
				homeFeaturedVideoListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails VideoUnderSubShowOfHomePage = featuredSubShowList
				.get(XidioConstant.selectShow);
		String homePageSubShowVideoResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getVideoDetailsURL(
						VideoUnderSubShowOfHomePage.getId(), 12));
		List<VideoDetails> homePageSubShowVideos = JsonParser
				.parseHomeFeaturedVideosResponse(homePageSubShowVideoResponse);

		List<VideoDetails> homePageSubShowVideoList = new ArrayList<VideoDetails>();
		if (homePageSubShowVideos != null && homePageSubShowVideos.size() <= 10) {
			for (VideoDetails videoDetails : homePageSubShowVideos) {
				homePageSubShowVideoList.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = homePageSubShowVideos.get(index);
				homePageSubShowVideoList.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("show", nfeaturedShowsList);
		finalMap.put("subshow", featuredSubShowList);
		finalMap.put("subShowVideos", homePageSubShowVideoList);
		finalMap.put("video", homeFeaturedVideoListUnderChannel);
		finalMap.put("showsUnderChannel", homeFeaturedShowsListUnderChannel);

		return finalMap;
	}

	/*
	 * UPNEXT API's
	 */
	/*
	 * public static List<VideoDetails> upNextAPI() throws
	 * XPathExpressionException, ParserConfigurationException, SAXException,
	 * IOException { String upNextCategoryResponse
	 * =DomParserXPATH.getCategories(
	 * UrlFactoryUtil.getInstance().getUpNextCategoryURL());
	 * System.out.println("UpNext API Response>>>>>"+upNextCategoryResponse);
	 * 
	 * List<VideoDetails> HomeUpNextCategorylList
	 * =JsonParser.parseUpNextCategoryResponse(upNextCategoryResponse);
	 * 
	 * 
	 * Collections.sort(HomeUpNextCategorylList, new Comparator<VideoDetails>()
	 * { public int compare(VideoDetails v1, VideoDetails v2) {
	 * System.out.println("V!!!!!!!!!!!"+v1.getLastUpdatedTime());
	 * System.out.println("V!!!!!!!!!!!"+v2.getLastUpdatedTime()); return
	 * v1.getLastUpdatedTime().compareTo(v2.getLastUpdatedTime()); } });
	 * for(VideoDetails video:HomeUpNextCategorylList) {
	 * System.out.println("After sort>>>>"+video.getTitle());
	 * System.out.println("After sort Date >>>>"+video.getLastUpdatedTime()); }
	 * 
	 * List<VideoDetails> homeUpNextCategoyrList=new ArrayList<VideoDetails>();
	 * 
	 * if(HomeUpNextCategorylList!=null && HomeUpNextCategorylList.size()<20) {
	 * for(VideoDetails videoDetails:HomeUpNextCategorylList) {
	 * if(videoDetails.getLevel()!=null &&
	 * videoDetails.getLevel().equalsIgnoreCase("SUB_SHOW")) {
	 * homeUpNextCategoyrList.add(videoDetails);
	 * 
	 * System.out.println("UpNext SHOWS:>>>"+videoDetails.getTitle()); } } }
	 * else { for(int index=0; index<20; index++) { VideoDetails
	 * videoDetails=HomeUpNextCategorylList.get(index);
	 * if(videoDetails.getLevel()!=null &&
	 * videoDetails.getLevel().equalsIgnoreCase("SUB_SHOW")) {
	 * homeUpNextCategoyrList.add(videoDetails);
	 * System.out.println("UpNext SHOWS:>>>"+videoDetails.getTitle()); } } }
	 * 
	 * List<VideoDetails> homeUpNextDetailsList=new ArrayList<VideoDetails>();
	 * 
	 * if(homeUpNextCategoyrList!=null && homeUpNextCategoyrList.size()<10) {
	 * for(VideoDetails vidoDetails:homeUpNextCategoyrList) { String
	 * upNextResponse
	 * =DomParserXPATH.getCategories(UrlFactoryUtil.getInstance().
	 * getUpNextAssetsURL(vidoDetails.getId())); List<VideoDetails>
	 * HomeUpNextVideoList =JsonParser.parseUpNextShowsResponse(upNextResponse);
	 * 
	 * for(VideoDetails videoDetails:HomeUpNextVideoList) {
	 * homeUpNextDetailsList.add(videoDetails);
	 * System.out.println("UpNext Videos:>>>"+videoDetails.getTitle()); } } }
	 * 
	 * 
	 * for(VideoDetails vidoDetails:homeUpNextCategoyrList) { String
	 * upNextResponse
	 * =DomParserXPATH.getCategories(UrlFactoryUtil.getInstance().
	 * getUpNextAssetsURL(vidoDetails.getId())); List<VideoDetails>
	 * HomeUpNextVideolList
	 * =JsonParser.parseUpNextShowsResponse(upNextResponse);
	 * 
	 * if(HomeUpNextVideolList!=null && HomeUpNextVideolList.size()<10) {
	 * for(VideoDetails videoDetails:HomeUpNextVideolList) {
	 * homeUpNextDetailsList.add(videoDetails);
	 * System.out.println("UpNext Videos Details :>>>"+videoDetails.getTitle());
	 * } } else { for(int index=0; index<20; index++) { VideoDetails
	 * videoDetails=HomeUpNextVideolList.get(index);
	 * homeUpNextDetailsList.add(videoDetails);
	 * System.out.println("UpNext Videos Details :>>>"+videoDetails.getTitle());
	 * } } }
	 * 
	 * return homeUpNextDetailsList; }
	 */

	/*
	 * HOME Popular Channel API's
	 */
	public static Map<String, List<VideoDetails>> HomePopularChannelAPIs()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList
				.get(XidioConstant.selectPopularChannel);
		String subShowResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getSubShowURL(popularChannelDetails.getId()));
		List<VideoDetails> popularSubShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowResponse);

		List<VideoDetails> popularSubShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (popularSubShowsList != null && popularSubShowsList.size() < 10) {
			for (VideoDetails videoDetails : popularSubShowsList) {
				popularSubShowsListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = popularSubShowsList.get(index);
				popularSubShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails featuredShowDetails = popularSubShowsListUnderChannel
				.get(0);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(featuredShowDetails.getId(),
						12));
		List<VideoDetails> popularVideoList = JsonParser
				.parseStorePopularVideosResponse(videoResponse);

		List<VideoDetails> popularVideosList = new ArrayList<VideoDetails>();
		if (popularVideoList != null && popularVideoList.size() < 10) {
			for (VideoDetails videoDetails : popularVideoList) {
				popularVideosList.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = popularVideoList.get(index);
				popularVideosList.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("bundle", popularBundlesList);
		finalMap.put("show", popularShowsList);
		finalMap.put("subshow", popularSubShowList);
		finalMap.put("showsUnderChannel", popularSubShowsListUnderChannel);
		finalMap.put("video", popularVideosList);

		return finalMap;
	}

	/*
	 * HOME Popular Channel API's
	 */
	public static Map<String, List<VideoDetails>> HomePopularChannelDetailsAPIs()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API.
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList
				.get(XidioConstant.selectPopularChannel);
		String subShowResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getSubShowURL(popularChannelDetails.getId()));
		List<VideoDetails> storePopularShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(subShowResponse);

		List<VideoDetails> storePopularShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (storePopularShowsList != null && storePopularShowsList.size() < 10) {
			for (VideoDetails videoDetails : storePopularShowsList) {
				storePopularShowsListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storePopularShowsList.get(index);
				storePopularShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails PopularShowDetails = storePopularShowsListUnderChannel
				.get(XidioConstant.selectShow);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(PopularShowDetails.getId(),
						12));
		List<VideoDetails> storePopularVideoList = JsonParser
				.parseHomeFeaturedVideosResponse(videoResponse);

		List<VideoDetails> storePopularVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (storePopularVideoList != null && storePopularVideoList.size() < 10) {
			for (VideoDetails videoDetails : storePopularVideoList) {
				storePopularVideoListUnderChannel.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails videoDetails = storePopularVideoList.get(index);
				storePopularVideoListUnderChannel.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("bundle", popularBundlesList);
		finalMap.put("show", popularShowsList);
		finalMap.put("subshow", popularSubShowList);
		finalMap.put("showsUnderChannel", storePopularShowsListUnderChannel);
		finalMap.put("video", storePopularVideoListUnderChannel);

		return finalMap;
	}

	/*
	 * HOME Popular Show API's
	 */
	public static Map<String, List<VideoDetails>> HomePopularShowsAPIs()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String popularShowResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance()
						.getPopularShowsURL());
		List<VideoDetails> HomePopularShowsList = JsonParser
				.parseHomePopularShowResponse(popularShowResponse);
		VideoDetails popularShowDetails = HomePopularShowsList
				.get(XidioConstant.selectPopularShow);

		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(popularShowDetails.getId(),
						12));
		List<VideoDetails> homePopularShowsVideoList = JsonParser
				.parsePopularShowsVideosResponse(videoResponse);
		for (VideoDetails vid : homePopularShowsVideoList) {
			System.out.println("PopularVideo:>>" + vid.getTitle());
		}
		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("popularShows", HomePopularShowsList);
		finalMap.put("popularvideos", homePopularShowsVideoList);

		return finalMap;
	}

	/*
	 * Name: popularShowEpisodeList Module: HOME Page Description: This method
	 * provides Episode count for a Episodes.
	 */
	public static List<VideoDetails> popularShowEpisodeList()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {

		String popularShowResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance()
						.getPopularShowsURL());
		List<VideoDetails> HomePopularShowsList = JsonParser
				.parseHomePopularShowResponse(popularShowResponse);
		VideoDetails popularShowDetails = HomePopularShowsList.get(0);

		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getEpisodeCountURL(
						popularShowDetails.getId()));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		popularShowDetails.setNoOfHits(noOfHits);

		List<VideoDetails> popularShowEpisodeCount = new ArrayList<VideoDetails>();
		popularShowEpisodeCount.add(popularShowDetails);

		return popularShowEpisodeCount;
	}

	/*
	 * HOME
	 */
	/*
	 * public static String showCountForHomeFeaturedChannel() throws
	 * XPathExpressionException, ParserConfigurationException, SAXException,
	 * IOException { //Calling Featured API. FeaturedAPI();
	 * 
	 * VideoDetails featuredChannelDetails
	 * =featuredShowsList.get(XidioConstant.selectFeaturedChannel); String
	 * showCountResponse
	 * =DomParserXPATH.getCategories(UrlFactoryUtil.getInstance
	 * ().getSubShowURL(featuredChannelDetails.getId(), 0)); return
	 * JsonParser.parseHomeFeaturedShowCountResponse(showCountResponse); }
	 */

	/*
	 * public static String showCountForHomeFeaturedChannel() throws
	 * XPathExpressionException, ParserConfigurationException, SAXException,
	 * IOException { //Calling Featured API. //FeaturedAPI();
	 * 
	 * VideoDetails featuredChannelDetails
	 * =featuredShowsList.get(XidioConstant.selectFeaturedChannel); String
	 * showCountResponse
	 * =DomParserXPATH.getCategories(UrlFactoryUtil.getInstance
	 * ().getSubShowURL(featuredChannelDetails.getId())); return
	 * JsonParser.parseHomeFeaturedShowCountResponse(showCountResponse); }
	 */

	/* This method parse Home Popular channel */
	public static String showCountForHomePopularChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Popular API.
		popularChannelAPI();

		VideoDetails popularChannelDetails = popularShowsList.get(0);
		String subShowCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						popularChannelDetails.getId()));
		return JsonParser
				.parseHomeFeaturedShowCountResponse(subShowCountResponse);
	}

	/*
	 * SUBSCRIPION Page API's
	 */
	public static Map<String, List<VideoDetails>> subscribedChannelDetails()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Subscriptions API.
		SubscriptionsAPI();

		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TestDataGenerator proUtil = new TestDataGenerator();

		proUtil.load(new FileInputStream(new File("com/data.properties")));
		// String userName=proUtil.getProperty("USER_NAME");
		// String regPassword=proUtil.getProperty("REG_PASSWORD");
		String userName = UILablesRepo.USERNAME;
		String regPassword = UILablesRepo.PASSWORD;
		String bearerToken = null;

		try {
			bearerToken = userAuthentication(userName, regPassword,
					sessionToken);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VideoDetails subscriptionsChannelDetails = subscribedChannelsList
				.get(XidioConstant.selectSubscribedChannel);
		String ChannelsShowsResponse = getBearerResponse(
				UrlFactoryUtil.getInstance().getSubShowURL(
						subscriptionsChannelDetails.getId()), sessionToken,
				bearerToken);
		List<VideoDetails> subscriptionsShowList = JsonParser
				.parseShowsResponse(ChannelsShowsResponse);

		List<VideoDetails> subscribedShowsListUnderChannel = new ArrayList<VideoDetails>();
		if (subscriptionsShowList != null) {
			int loopIndexMax = 0;
			if (subscriptionsShowList.size() < 5)
				loopIndexMax = subscriptionsShowList.size();
			else
				loopIndexMax = 5;
			for (int index = 0; index < loopIndexMax; index++) {
				VideoDetails videoDetails = subscriptionsShowList.get(index);
				subscribedShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails subscriptionsShowDetails = subscriptionsShowList.get(0);
		String videoResponse = RestAPIServices.getSessionTokenResponse(
				UrlFactoryUtil.getInstance().getVideoDetailsURL(
						subscriptionsShowDetails.getId(), 10), sessionToken);
		List<VideoDetails> subscriptionsVideoList = JsonParser
				.parseChannelShowsVideosResponse(videoResponse);

		List<VideoDetails> subscriptionsVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (subscriptionsVideoList != null && !subscriptionsVideoList.isEmpty()) {
			int loopIndexMax = 0;
			if (subscriptionsVideoList.size() < 5)
				loopIndexMax = subscriptionsVideoList.size();
			else
				loopIndexMax = 5;
			for (int index = 0; index < loopIndexMax; index++) {
				VideoDetails videoDetails = subscriptionsVideoList.get(index);
				subscriptionsVideoListUnderChannel.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("subscribedChannels", subscribedChannelsList);
		finalMap.put("subscribedShowsUnderChannel",
				subscribedShowsListUnderChannel);
		finalMap.put("subscribedVideo", subscriptionsVideoListUnderChannel);
		return finalMap;
	}

	/*
	 * SUBSCRIPIONS Page API's
	 */
	public static String showCountForSubscriptionsChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Subscriptions API.
		/*
		 * SubscriptionsAPI();
		 * 
		 * VideoDetails subscriptionsChannelDetails
		 * =subscriptionsShowsList.get(XidioConstant.selectSubscribedChannel);
		 * String subShowCountResponse
		 * =DomParserXPATH.getCategories(UrlFactoryUtil
		 * .getInstance().getSubShowURL(subscriptionsChannelDetails.getId()));
		 * return
		 * JsonParser.parseHomeFeaturedShowCountResponse(subShowCountResponse);
		 */
		return null;
	}

	/*
	 * Name: FeaturedBundleAPI Module: STORE Page Description: This method
	 * provides Stored Featured, Bundle,Channel,Shows and Video list Details.
	 */
	public static Map<String, List<VideoDetails>> FeaturedBundleAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredBundleDetails = featuredBundlesList
				.get(XidioConstant.selectBundle);
		String BundleShowsResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil
						.getInstance()
						.getBundleChannelsURL(featuredBundleDetails.getId(), 12));
		List<VideoDetails> FeaturedShowsListUnderBundle = JsonParser
				.parseHomeFeaturedShowsResponse(BundleShowsResponse);

		List<VideoDetails> featuredShowsListUnderBundle = new ArrayList<VideoDetails>();
		if (FeaturedShowsListUnderBundle != null
				&& FeaturedShowsListUnderBundle.size() <= 20) {
			for (VideoDetails videoDetails : FeaturedShowsListUnderBundle) {
				if (videoDetails.getContentType() != null
						&& videoDetails.getContentType().equalsIgnoreCase(
								"bundle")) {
					featuredBundlesList.add(videoDetails);
				}
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					int showCount = 0;
					String showCountResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getSubShowURL(videoDetails.getId()));
					if (showCountResponse != null
							&& showCountResponse.length() > 0)
						showCount = Integer
								.parseInt(JsonParser
										.parseStoreFeaturedShowCountResponse(showCountResponse));
					if (showCount > 0)
						featuredShowsListUnderBundle.add(videoDetails);
					System.out.println("Bundle Channels List:>>"
							+ videoDetails.getTitle());
				}
			}
		}
		/*
		 * -- if(FeaturedShowsListUnderBundle!=null &&
		 * FeaturedShowsListUnderBundle.size()<10) { for(VideoDetails
		 * videoDetails:FeaturedShowsListUnderBundle) {
		 * featuredShowsListUnderBundle.add(videoDetails);
		 * System.out.println("Bundle Shows List:>>"+videoDetails.getTitle()); }
		 * }
		 */
		else {
			for (int index = 0; index < 20; index++) {
				VideoDetails videoDetails = FeaturedShowsListUnderBundle
						.get(index);
				if (videoDetails.getLevel() != null
						&& videoDetails.getLevel().equalsIgnoreCase("SHOW")) {
					int showCount = 0;
					String showCountResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getSubShowURL(videoDetails.getId()));
					if (showCountResponse != null
							&& showCountResponse.length() > 0)
						showCount = Integer
								.parseInt(JsonParser
										.parseStoreFeaturedShowCountResponse(showCountResponse));
					if (showCount > 0)

						featuredShowsListUnderBundle.add(videoDetails);
					System.out.println("Bundle Channels List:>>"
							+ videoDetails.getTitle());
				}
			}
		}

		VideoDetails featuredChannelDetails = featuredShowsListUnderBundle
				.get(XidioConstant.selectBundleChannel);
		String bundleChannelsSubShowResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						featuredChannelDetails.getId()));
		List<VideoDetails> featuredBundleChannelSubShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(bundleChannelsSubShowResponse);

		List<VideoDetails> featuredBundleChannelsSubShowsList = new ArrayList<VideoDetails>();

		if (featuredBundleChannelSubShowsList != null
				&& featuredBundleChannelSubShowsList.size() < 20) {
			for (VideoDetails videoDetails : featuredBundleChannelSubShowsList) {
				featuredBundleChannelsSubShowsList.add(videoDetails);
				System.out.println("Bundle Sub Shows List:>>"
						+ videoDetails.getTitle());
			}
		} else {
			for (int index = 0; index < 20; index++) {
				VideoDetails videoDetails = featuredBundleChannelSubShowsList
						.get(index);
				featuredBundleChannelsSubShowsList.add(videoDetails);
				System.out.println("Bundle Sub Shows List:>>"
						+ videoDetails.getTitle());
			}
		}

		VideoDetails featuredSubShowDetails = featuredBundleChannelsSubShowsList
				.get(XidioConstant.selectShow);
		String videoResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getVideoDetailsURL(
						featuredSubShowDetails.getId(), 12));
		List<VideoDetails> storeFeaturedVideoList = JsonParser
				.parseHomeFeaturedVideosResponse(videoResponse);

		List<VideoDetails> storeFeaturedVideoListUnderChannel = new ArrayList<VideoDetails>();
		if (storeFeaturedVideoList != null
				&& storeFeaturedVideoList.size() < 20) {
			for (VideoDetails videoDetails : storeFeaturedVideoList) {
				storeFeaturedVideoListUnderChannel.add(videoDetails);
				System.out.println("Bundle Sub Shows Video List:>>"
						+ videoDetails.getTitle());
			}
		} else {
			for (int index = 0; index < 20; index++) {
				VideoDetails videoDetails = storeFeaturedVideoList.get(index);
				storeFeaturedVideoListUnderChannel.add(videoDetails);
				System.out.println("Bundle Sub Shows Video List:>>"
						+ videoDetails.getTitle());
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("featuedResponseList", featuredResponseDetails);
		finalMap.put("bundlesList", featuredBundlesList);
		finalMap.put("showsInBundle", featuredShowsListUnderBundle);
		finalMap.put("subShowInBundleChannel",
				featuredBundleChannelsSubShowsList);
		finalMap.put("videosInBundleChannel",
				storeFeaturedVideoListUnderChannel);
		return finalMap;
	}

	/*
	 * Name: ChannelCountUnderBundle Module: STORE Page Description: This method
	 * provides Channel count under Bundle details page.
	 */
	public static String ChannelCountUnderBundle()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		VideoDetails featuredChannelDetails = featuredBundlesList
				.get(XidioConstant.selectBundle);
		String ChannelCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil
						.getInstance()
						.getBundleChannelsURL(featuredChannelDetails.getId(), 0));
		return JsonParser
				.parseStoreFeaturedShowCountResponse(ChannelCountResponse);
	}

	/*
	 * Name: episodeListUnderBundle Module: STORE/HOME Page Description: This
	 * method provides Episode count for a Bundle.
	 */
	public static Map<String, List<VideoDetails>> episodeListUnderBundle()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredBundleDetails = featuredBundlesList
				.get(XidioConstant.selectBundle);
		String BundleShowsResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil
						.getInstance()
						.getBundleChannelsURL(featuredBundleDetails.getId(), 12));
		List<VideoDetails> FeaturedShowsListUnderBundle = JsonParser
				.parseHomeFeaturedShowsResponse(BundleShowsResponse);

		VideoDetails featuredBundleEpisodeDetails = FeaturedShowsListUnderBundle
				.get(0);
		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getEpisodeCountURL(
						featuredBundleEpisodeDetails.getId()));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		featuredBundleEpisodeDetails.setNoOfHits(noOfHits);

		List<VideoDetails> episodeCountUnderBundle = new ArrayList<VideoDetails>();
		episodeCountUnderBundle.add(featuredBundleEpisodeDetails);

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("bundlesList", featuredBundlesList);
		finalMap.put("episodeCountInBundle", episodeCountUnderBundle);
		return finalMap;
	}

	/*
	 * Name: ShowsCountFeaturedBundle Module: STORE/HOME Page Description: This
	 * method provides Shows count for a Bundle.
	 */
	public static String ShowsCountFeaturedBundle()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredBundleDetails = featuredBundlesList
				.get(XidioConstant.selectBundle);
		String BundleShowsResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil
						.getInstance()
						.getBundleChannelsURL(featuredBundleDetails.getId(), 12));
		List<VideoDetails> FeaturedShowsListUnderBundle = JsonParser
				.parseHomeFeaturedShowsResponse(BundleShowsResponse);

		VideoDetails subShowsInBundle = FeaturedShowsListUnderBundle.get(0);
		String subShowCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						subShowsInBundle.getId()));
		return JsonParser
				.parseHomeFeaturedShowCountResponse(subShowCountResponse);
	}

	/*
	 * Name: episodeListUnderBundle Module: STORE/HOME Page Description: This
	 * method provides Episode count for a Bundle.
	 */
	public static Map<String, List<VideoDetails>> episodeListUnderBundleSHOWS()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// Calling Featured API.
		FeaturedAPI();

		VideoDetails featuredBundleDetails = featuredBundlesList
				.get(XidioConstant.selectBundle);
		String BundleShowsResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil
						.getInstance()
						.getBundleChannelsURL(featuredBundleDetails.getId(), 12));
		List<VideoDetails> FeaturedShowsListUnderBundle = JsonParser
				.parseHomeFeaturedShowsResponse(BundleShowsResponse);

		VideoDetails featuredChannelDetails = FeaturedShowsListUnderBundle
				.get(XidioConstant.selectBundleChannel);
		String bundleChannelsSubShowResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getSubShowURL(
						featuredChannelDetails.getId()));
		List<VideoDetails> featuredBundleChannelSubShowsList = JsonParser
				.parseHomeFeaturedShowsResponse(bundleChannelsSubShowResponse);

		VideoDetails featuredBundleEpisodeDetails = featuredBundleChannelSubShowsList
				.get(XidioConstant.selectShow);
		String episodeCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getEpisodeCountURL(
						featuredBundleEpisodeDetails.getId()));
		String noOfHits = JsonParser
				.parseEpisodeCountForChannelResponse(episodeCountResponse);
		featuredBundleEpisodeDetails.setNoOfHits(noOfHits);

		List<VideoDetails> episodeCountUnderBundleSHOWS = new ArrayList<VideoDetails>();
		episodeCountUnderBundleSHOWS.add(featuredBundleEpisodeDetails);

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("bundlesList", featuredBundlesList);
		finalMap.put("episodeCountInBundleSHOWS", episodeCountUnderBundleSHOWS);
		return finalMap;
	}

	public static String convertStreamToString(java.io.InputStream is) {
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	private static List<String> executeLogin(String userName, String password,
			String sessionToken) throws Exception {

		String userId = null;
		String sessionId = null;
		List<String> userAndsessionID = new ArrayList<String>();

		HttpClient httpClient = new DefaultHttpClient();

		// Authentication API
		HttpPost httpPost = new HttpPost(UrlFactoryUtil.getAuthenticationURL());
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("username", userName));
		nameValuePairs.add(new BasicNameValuePair("password", password));

		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpResponse response = httpClient.execute(httpPost, context);
		System.out.println("Response>>" + response);
		InputStream responseBody = response.getEntity().getContent();

		JSONObject loginObject = new JSONObject(
				getStringFromInputStream(responseBody));
		if (loginObject.has("response") && !loginObject.isNull("response")
				&& loginObject.getJSONObject("response").has("userId")
				&& !loginObject.getJSONObject("response").isNull("userId")) {

			userId = loginObject.getJSONObject("response").getString("userId");
		}

		if (loginObject.has("response") && !loginObject.isNull("response")
				&& loginObject.getJSONObject("response").has("reference")
				&& !loginObject.getJSONObject("response").isNull("reference")) {
			sessionId = loginObject.getJSONObject("response").getString(
					"reference");
		}

		EntityUtils.consumeQuietly(response.getEntity());

		userAndsessionID.add(userId);
		userAndsessionID.add(sessionId);
		return userAndsessionID;
		// return userId;

	}

	// User Authentication:
	private static String userAuthentication(String userName, String password,
			String sessionToken) throws Exception {
		List<String> userAndsessionID = new ArrayList<String>();

		HttpClient httpClient = new DefaultHttpClient();

		// Genres Authentication API URL.
		System.out.println("URL:" + UrlFactoryUtil.getAuthenticationURL());
		HttpPost httpPost = new HttpPost(UrlFactoryUtil.getAuthenticationURL());
		// List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		// nameValuePairs.add(new BasicNameValuePair("username", userName));
		// nameValuePairs.add(new BasicNameValuePair("password", password));
		// nameValuePairs.add(new BasicNameValuePair("SessionToken",
		// sessionToken));

		StringEntity entity = new StringEntity("{\"username\":\"" + userName
				+ "\",\"password\":\"" + password + "\"}");

		httpPost.setEntity(entity);

		Header[] headers = { new BasicHeader("SessionToken", sessionToken),
				new BasicHeader("Content-Type", "text/plain"),
				new BasicHeader("Content-Type", "application/json") };

		// httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		httpPost.setHeader("SessionToken", sessionToken);
		// httpPost.addHeader("Content-Type", "text/plain");
		// httpPost.addHeader("Content-Type", "application/json");

		httpPost.setHeader("Content-Type", "text/plain");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Authorization", "authorization");
		httpPost.setHeaders(headers);
		HttpResponse response = httpClient.execute(httpPost, context);

		return getAutorization(response);
	}

	private static String getAutorization(HttpResponse response) {

		return response.getFirstHeader("Authorization").getValue();
	}

	private static String getSubscriptionResponse(String subURL,
			HttpContext context) {
		InputStream responseBodySubscription = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();// Get Subscription
															// Details
			HttpGet httpGet = new HttpGet(subURL);
			HttpResponse responseSubscription = httpClient.execute(httpGet,
					context);
			String response = getStringFromInputStream(responseSubscription
					.getEntity().getContent());
			EntityUtils.consumeQuietly(responseSubscription.getEntity());

			return response;

		} catch (Exception e) {

		}
		return null;
	}

	public static String getBearerResponse(String subURL, String sessionToken,
			String bearerToken) {
		InputStream responseBodySubscription = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();// Get Subscription
															// Details
			HttpGet httpGet = new HttpGet(subURL);

			httpGet.setHeader("Authorization", bearerToken);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("SessionToken", sessionToken);

			HttpResponse responseSubscription = httpClient.execute(httpGet,
					context);
			String response = getStringFromInputStream(responseSubscription
					.getEntity().getContent());
			EntityUtils.consumeQuietly(responseSubscription.getEntity());

			return response;

		} catch (Exception e) {
			System.out.println("Exception in SessionToken Response");
		}
		return null;
	}

	public static String getSessionTokenResponse(String subURL,
			String sessionToken) {
		InputStream responseBodySubscription = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();// Get Subscription
															// Details
			HttpGet httpGet = new HttpGet(subURL);

			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("SessionToken", sessionToken);

			HttpResponse responseSubscription = httpClient.execute(httpGet,
					context);
			String response = getStringFromInputStream(responseSubscription
					.getEntity().getContent());
			EntityUtils.consumeQuietly(responseSubscription.getEntity());

			return response;

		} catch (Exception e) {
			System.out.println("Exception in SessionToken Response");
		}
		return null;
	}

	public static String getSessionTokenResponseForSub(String subURL,
			String sessionToken, String username, String password) {
		InputStream responseBodySubscription = null;
		try {
			HttpPost httpPost = new HttpPost(
					UrlFactoryUtil.getAuthenticationURL());
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

			StringEntity entity = new StringEntity("{\"username\":\""
					+ username + "\",\"password\":\"" + password + "\"}");
			httpPost.setEntity(entity);

			HttpClient httpClient = new DefaultHttpClient();// Get Subscription
															// Details
			HttpGet httpGet = new HttpGet(subURL);

			httpGet.setHeader("sessionToken", sessionToken);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-Type", "text/plain");
			httpGet.setHeader("Authorization", "authorization");

			HttpResponse responseSubscription = httpClient.execute(httpGet,
					context);
			String response = getStringFromInputStream(responseSubscription
					.getEntity().getContent());
			EntityUtils.consumeQuietly(responseSubscription.getEntity());

			return response;

		} catch (Exception e) {
			System.out.println("Exception in SessionToken Response");
		}
		return null;
	}

	public static String getSessionTokenResponseForUpNext(String subURL,
			String sessionToken, String username, String password) {
		InputStream responseBodySubscription = null;
		try {

			HttpPost httpPost = new HttpPost(
					UrlFactoryUtil.getAuthenticationURL());
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

			StringEntity entity = new StringEntity("{\"username\":\""
					+ username + "\",\"password\":\"" + password + "\"}");
			httpPost.setEntity(entity);

			HttpClient httpClient = new DefaultHttpClient();// Get Subscription
															// Details
			HttpGet httpGet = new HttpGet(subURL);

			httpGet.setHeader("SessionToken", sessionToken);
			httpGet.setHeader("Content-Type", "text/plain");
			httpGet.setHeader("Content-Type", "application/json");
			httpGet.setHeader("Accept", "application/xml");

			HttpResponse responseSubscription = httpClient.execute(httpGet,
					context);
			String response = getStringFromInputStream(responseSubscription
					.getEntity().getContent());
			EntityUtils.consumeQuietly(responseSubscription.getEntity());

			return response;

		} catch (Exception e) {
			System.out.println("Exception in SessionToken Response");
		}
		return null;
	}

	private static String getStringFromInputStream(InputStream input) {
		BufferedReader br = null;
		StringBuilder subscription = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(input));
			while ((line = br.readLine()) != null) {
				subscription.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return subscription.toString();
	}

	public static String executeGenreAuthentication() throws Exception {
		String userId = null;
		String sessionId = null;
		// String sessionToken="";
		// List<String> userAndsessionID=new ArrayList<String>();

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(
				UrlFactoryUtil.getGenresAuthenticationUrl());

		// Genres Authentication API URL.
		System.out
				.println("URL:" + UrlFactoryUtil.getGenresAuthenticationUrl());
		// HttpPost httpPost = new
		// HttpPost(UrlFactoryUtil.getGenresAuthenticationUrl());

		// List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		// nameValuePairs.add(new BasicNameValuePair("username", userName));
		// nameValuePairs.add(new BasicNameValuePair("password", password));

		StringEntity entity = new StringEntity(
				"9a5901f0-63f9-4938-87cc-7a9b733788c1_3a5a0a7f-3e23-4fb2-9e16-4df426cf2190");
		httpPost.setEntity(entity);

		// httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		/*
		 * httpPost.setHeader("Content-Type", "text/plain");
		 * httpPost.setHeader("Accept", "application/xml");
		 */

		// httpPost.setHeader("Authorization","authorization");

		HttpResponse response = httpClient.execute(httpPost, context);
		// System.out.println("Response>>"+response);
		// InputStream responseBody = response.getEntity().getContent();
		// sessionToken=getSessionToken(response);

		/*
		 * String responseStr=getStringFromInputStream(responseBody); JSONObject
		 * loginObject = new JSONObject(getStringFromInputStream(responseBody));
		 * if (loginObject.has("response") && !loginObject.isNull("response") &&
		 * loginObject.getJSONObject("response").has("userId") &&
		 * !loginObject.getJSONObject("response").isNull("userId")) {
		 * 
		 * userId = loginObject.getJSONObject("response").getString("userId");
		 * 
		 * }
		 * 
		 * if (loginObject.has("response") && !loginObject.isNull("response") &&
		 * loginObject.getJSONObject("response").has("reference") &&
		 * !loginObject.getJSONObject("response").isNull("reference")) {
		 * sessionId =
		 * loginObject.getJSONObject("response").getString("reference"); }
		 * 
		 * EntityUtils.consumeQuietly(response.getEntity());
		 * 
		 * userAndsessionID.add(userId); userAndsessionID.add(sessionId); return
		 * userAndsessionID;
		 */
		return getSessionToken(response);
	}

	private static String getSessionToken(HttpResponse response) {
		return response.getFirstHeader("SessionToken").getValue();
	}

	/*
	 * Name: ChannelCountUnderBundle Module: STORE Page Description: This method
	 * provides Channel count under Bundle details page.
	 */
	public static List<String> showsCountUnderBundle()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		VideoDetails featuredChannelDetails = featuredBundlesList
				.get(XidioConstant.selectBundle);
		String ChannelCountResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance()
						.getBundleShowCountURL(featuredChannelDetails.getId()));
		List<String> showAndVideoCountUnderBundle = JsonParser
				.parseFeaturedBundleShowAndVideoCountResponse(ChannelCountResponse);
		return showAndVideoCountUnderBundle;
	}

	/*
	 * UPNEXT API's
	 */
	public static List<VideoDetails> upNextAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TestDataGenerator proUtil = new TestDataGenerator();

		/*
		 * Lekshmi: Change the location of the user credentials to make the
		 * method work. proUtil.load(new FileInputStream(new
		 * File("com/data.properties"))); String
		 * userName=proUtil.getProperty("USER_NAME"); String
		 * regPassword=proUtil.getProperty("REG_PASSWORD");
		 */
		String userName = UILablesRepo.USERNAME;
		String regPassword = UILablesRepo.PASSWORD;
		String bearerToken = null;

		try {
			bearerToken = userAuthentication(userName, regPassword,
					sessionToken);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String upNextResponse = getBearerResponse(UrlFactoryUtil.getInstance()
				.getUpNextURL(), sessionToken, bearerToken);
		List<VideoDetails> HomeUpNextVideolList = JsonParser
				.parseUpNextVideosResponse(upNextResponse);

		List<VideoDetails> homeUpNextDetailsList = new ArrayList<VideoDetails>();

		if (HomeUpNextVideolList != null && HomeUpNextVideolList.size() < 20) {
			for (VideoDetails videoDetails : HomeUpNextVideolList) {
				homeUpNextDetailsList.add(videoDetails);
			}
		} else {
			for (int index = 0; index < 20; index++) {
				VideoDetails videoDetails = HomeUpNextVideolList.get(index);
				homeUpNextDetailsList.add(videoDetails);
			}
		}

		return homeUpNextDetailsList;
	}

	// --------------------------------------------------------New API
	// Implementation--------------------------------------------------//

	private static List<VideoDetails> nfeaturedResponseDetails;
	private static List<VideoDetails> nfeaturedBundlesList;
	private static List<VideoDetails> nallFeaturedShowsList;
	private static List<VideoDetails> nfeaturedShowsList;
	private static List<VideoDetails> nfeaturedSubShowList;
	private static List<VideoDetails> nfeaturedVideoList;
	private static List<VideoDetails> nfeaturedChannelsHasOnlyEpisodes;

	/*
	 * Name: FeaturedAPI Module: STORE/HOME Page Description: This method
	 * provides Featured API Response Details.
	 */
	public static Map<String, List<VideoDetails>> nFeaturedAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String nFeaturedDetails = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getFeaturedURL(), sessionToken);
		List<VideoDetails> featuredAPIResponseList = JsonParser
				.parsenFeaturedResponse(nFeaturedDetails);

		nfeaturedResponseDetails = new ArrayList<VideoDetails>();
		nfeaturedBundlesList = new ArrayList<VideoDetails>();
		nallFeaturedShowsList = new ArrayList<VideoDetails>();
		nfeaturedShowsList = new ArrayList<VideoDetails>();
		nfeaturedSubShowList = new ArrayList<VideoDetails>();
		nfeaturedVideoList = new ArrayList<VideoDetails>();
		nfeaturedChannelsHasOnlyEpisodes = new ArrayList<VideoDetails>();

		if (featuredAPIResponseList != null
				&& featuredAPIResponseList.size() <= 30) {
			for (VideoDetails videoDetails : featuredAPIResponseList) {
				nfeaturedResponseDetails.add(videoDetails);

				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedBundle")) {
					nfeaturedBundlesList.add(videoDetails);
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					nallFeaturedShowsList.add(videoDetails);
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					int showCount = 0;
					int episodeCount = 0;
					String showCountResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getChannelDetailsURL(videoDetails.getId()));
					if (showCountResponse != null
							&& showCountResponse.length() > 0)
						showCount = Integer
								.parseInt(JsonParser
										.parseFeaturedChannelShowCountResponse(showCountResponse));
					if (showCount > 0) {
						String episodeCountResponse = getSessionTokenResponse(
								UrlFactoryUtil.getInstance()
										.getChannelDetailsURL(
												videoDetails.getId()),
								sessionToken);
						if (episodeCountResponse != null
								&& episodeCountResponse.length() > 0) {
							episodeCount = Integer
									.parseInt(JsonParser
											.parseFeaturedChannelVideoCountResponse(episodeCountResponse));
							if (episodeCount > 0)
								nfeaturedShowsList.add(videoDetails);
						}
					}
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					int showCount = 0;
					int episodeCount = 0;
					String showCountResponse = DomParserXPATH
							.getCategories(UrlFactoryUtil.getInstance()
									.getChannelDetailsURL(videoDetails.getId()));
					if (showCountResponse != null
							&& showCountResponse.length() > 0)
						showCount = Integer
								.parseInt(JsonParser
										.parseFeaturedChannelShowCountResponse(showCountResponse));
					if (showCount == 0) {
						String episodeCountResponse = getSessionTokenResponse(
								UrlFactoryUtil.getInstance()
										.getChannelDetailsURL(
												videoDetails.getId()),
								sessionToken);
						if (episodeCountResponse != null
								&& episodeCountResponse.length() > 0) {
							episodeCount = Integer
									.parseInt(JsonParser
											.parseFeaturedChannelVideoCountResponse(episodeCountResponse));
							if (episodeCount > 0)
								nfeaturedChannelsHasOnlyEpisodes
										.add(videoDetails);
						}
					}
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedShow")) {
					nfeaturedSubShowList.add(videoDetails);
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedVideo")) {
					nfeaturedVideoList.add(videoDetails);
				}
			}
		} else {
			for (int index = 0; index < 30; index++) {
				VideoDetails videoDetails = featuredAPIResponseList.get(index);
				nfeaturedResponseDetails.add(videoDetails);

				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedBundle")) {
					nfeaturedBundlesList.add(videoDetails);
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					nallFeaturedShowsList.add(videoDetails);
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					int showCount = 0;
					int episodeCount = 0;
					String showCountResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance().getChannelDetailsURL(
									videoDetails.getId()), sessionToken);
					if (showCountResponse != null
							&& showCountResponse.length() > 0)
						showCount = Integer
								.parseInt(JsonParser
										.parseFeaturedChannelShowCountResponse(showCountResponse));
					if (showCount > 0) {
						String episodeCountResponse = getSessionTokenResponse(
								UrlFactoryUtil.getInstance()
										.getChannelDetailsURL(
												videoDetails.getId()),
								sessionToken);
						if (episodeCountResponse != null
								&& episodeCountResponse.length() > 0) {
							episodeCount = Integer
									.parseInt(JsonParser
											.parseFeaturedChannelVideoCountResponse(episodeCountResponse));
							if (episodeCount > 0)
								nfeaturedShowsList.add(videoDetails);
						}
					}
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					int showCount = 0;
					int episodeCount = 0;
					String showCountResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance().getChannelDetailsURL(
									videoDetails.getId()), sessionToken);
					if (showCountResponse != null
							&& showCountResponse.length() > 0)
						showCount = Integer
								.parseInt(JsonParser
										.parseFeaturedChannelShowCountResponse(showCountResponse));
					if (showCount == 0) {
						String episodeCountResponse = getSessionTokenResponse(
								UrlFactoryUtil.getInstance()
										.getChannelDetailsURL(
												videoDetails.getId()),
								sessionToken);
						if (episodeCountResponse != null
								&& episodeCountResponse.length() > 0) {
							episodeCount = Integer
									.parseInt(JsonParser
											.parseFeaturedChannelVideoCountResponse(episodeCountResponse));
							if (episodeCount > 0)
								nfeaturedChannelsHasOnlyEpisodes
										.add(videoDetails);
						}
					}
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedShow")) {
					nfeaturedSubShowList.add(videoDetails);
				}
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedVideo")) {
					nfeaturedVideoList.add(videoDetails);
				}
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("featuredChannelsList", nfeaturedShowsList);
		finalMap.put("featuredChannelsHasOnlyEpisodes",
				nfeaturedChannelsHasOnlyEpisodes);
		finalMap.put("allFeaturedShowsList", nallFeaturedShowsList);
		finalMap.put("featuredShowList", nfeaturedSubShowList);
		finalMap.put("featuredVideoList", nfeaturedVideoList);

		return finalMap;
	}

	/*
	 * Name: featuredSectionVideos Module: HOME Featured Section Description:
	 * This method provides Featured API Response only episodes list Details.
	 */
	public static Map<String, List<VideoDetails>> featuredSectionVideos()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String nFeaturedDetails = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getFeaturedURL(), sessionToken);
		List<VideoDetails> featuredAPIResponseList = JsonParser
				.parsenFeaturedResponse(nFeaturedDetails);

		featuredVideoList = new ArrayList<VideoDetails>();

		if (featuredAPIResponseList != null
				&& featuredAPIResponseList.size() <= 30) {
			for (VideoDetails videoDetails : featuredAPIResponseList) {
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedVideo")) {
					featuredVideoList.add(videoDetails);
				}
			}
		} else {
			for (int index = 0; index < 30; index++) {
				VideoDetails videoDetails = featuredAPIResponseList.get(index);
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedVideo")) {
					featuredVideoList.add(videoDetails);
				}
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("featuredVideoList", featuredVideoList);

		return finalMap;
	}

	/*
	 * Name: nPopularAPI Module: HOME Page Description: This method provides
	 * Popular Channels API Response Details.
	 */
	private static List<VideoDetails> popularChannelsList = null;
	private static List<VideoDetails> popularChannelShowsList = null;
	private static List<VideoDetails> popularChannelShowVideosList = null;

	public static Map<String, List<VideoDetails>> nPopularAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		popularResponse = getSessionTokenResponse(UrlFactoryUtil.getInstance()
				.getPopularURL(), sessionToken);
		List<VideoDetails> popularAPIResponseList = JsonParser
				.parsePopularChannelsResponse(popularResponse);

		popularChannelsList = new ArrayList<VideoDetails>();
		popularChannelShowsList = new ArrayList<VideoDetails>();
		popularChannelShowVideosList = new ArrayList<VideoDetails>();

		if (popularAPIResponseList != null
				&& popularAPIResponseList.size() < 20) {
			for (VideoDetails popularChannelDetails : popularAPIResponseList) {
				if (popularChannelDetails.getCuratedItemType() != null
						&& popularChannelDetails.getCuratedItemType()
								.equalsIgnoreCase("CuratedChannel")) {
					int showCount = Integer.parseInt(popularChannelDetails
							.getNumOfShows());
					int episodeCount = Integer.parseInt(popularChannelDetails
							.getNumOfVideos());
					if (showCount > 0 && episodeCount > 0) {
						popularChannelsList.add(popularChannelDetails);
					}
				}
			}
			String popularChannelId = popularChannelsList.get(
					XidioConstant.selectPopularChannel).getId();
			String subShowResponse = getSessionTokenResponse(UrlFactoryUtil
					.getInstance().getSubShowURL(popularChannelId),
					sessionToken);
			List<VideoDetails> popularSubShowsList = JsonParser
					.parseShowsResponse(subShowResponse);

			if (popularSubShowsList != null && popularSubShowsList.size() <= 10) {
				for (VideoDetails showsList : popularSubShowsList) {
					popularChannelShowsList.add(showsList);
				}
			} else {
				for (int index = 0; index < 10; index++) {
					VideoDetails showsList = popularSubShowsList.get(index);
					popularChannelShowsList.add(showsList);
				}
			}

			String popularChannelShowId = popularChannelShowsList.get(
					XidioConstant.selectPopularShow).getId();
			String videoResponse = getSessionTokenResponse(
					UrlFactoryUtil.getInstance().getVideoDetailsURL(
							popularChannelShowId, 10), sessionToken);
			List<VideoDetails> popularVideoList = JsonParser
					.parseChannelShowsVideosResponse(videoResponse);

			if (popularVideoList != null && popularVideoList.size() < 5) {
				for (VideoDetails videoList : popularVideoList) {
					popularChannelShowVideosList.add(videoList);
				}
			} else {
				for (int i = 0; i < 5; i++) {
					VideoDetails videoList = popularVideoList.get(i);
					popularChannelShowVideosList.add(videoList);
				}
			}
		} else {
			for (int index = 0; index < 20; index++) {
				VideoDetails popularChannelDetails = popularAPIResponseList
						.get(index);
				if (popularChannelDetails.getCuratedItemType() != null
						&& popularChannelDetails.getCuratedItemType()
								.equalsIgnoreCase("CuratedChannel")) {
					int showCount = Integer.parseInt(popularChannelDetails
							.getNumOfShows());
					int episodeCount = Integer.parseInt(popularChannelDetails
							.getNumOfVideos());
					if (showCount > 0 && episodeCount > 0) {
						popularChannelsList.add(popularChannelDetails);
					}
				}
			}
			String popularChannelId = popularChannelsList.get(
					XidioConstant.selectPopularChannel).getId();
			String subShowResponse = getSessionTokenResponse(UrlFactoryUtil
					.getInstance().getSubShowURL(popularChannelId),
					sessionToken);
			List<VideoDetails> popularSubShowsList = JsonParser
					.parseShowsResponse(subShowResponse);

			if (popularSubShowsList != null && popularSubShowsList.size() <= 10) {
				for (VideoDetails showsList : popularSubShowsList) {
					popularChannelShowsList.add(showsList);
				}
			} else {
				for (int index1 = 0; index1 < 10; index1++) {
					VideoDetails showsList = popularSubShowsList.get(index1);
					popularChannelShowsList.add(showsList);
				}
			}

			String popularChannelShowId = popularChannelShowsList.get(
					XidioConstant.selectPopularShow).getId();
			String videoResponse = getSessionTokenResponse(
					UrlFactoryUtil.getInstance().getVideoDetailsURL(
							popularChannelShowId, 10), sessionToken);
			List<VideoDetails> popularVideoList = JsonParser
					.parseChannelShowsVideosResponse(videoResponse);

			if (popularVideoList != null && popularVideoList.size() < 5) {
				for (VideoDetails videoList : popularVideoList) {
					popularChannelShowVideosList.add(videoList);
					System.out.println("Videio List:" + videoList.getTitle());
				}
			} else {
				for (int i = 0; i < 5; i++) {
					VideoDetails videoList = popularVideoList.get(i);
					popularChannelShowVideosList.add(videoList);
					System.out.println("Videio List:" + videoList.getTitle());
				}
			}
		}
		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();

		finalMap.put("popularChannelsList", popularChannelsList);
		finalMap.put("popularChannelShowsList", popularChannelShowsList);
		finalMap.put("popularChannelShowVideosList",
				popularChannelShowVideosList);

		return finalMap;
	}

	/*
	 * Name: allPopularChannelsList Module: HOME Page Description: This method
	 * to get all Popular Channels List Details.
	 */
	private static List<VideoDetails> allPopularChannelsList = null;

	public static Map<String, List<VideoDetails>> allPopularChannelsList()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		popularResponse = getSessionTokenResponse(UrlFactoryUtil.getInstance()
				.getPopularURL(), sessionToken);
		List<VideoDetails> popularAPIResponseList = JsonParser
				.parsePopularChannelsResponse(popularResponse);

		allPopularChannelsList = new ArrayList<VideoDetails>();
		int loopIndex = 0;
		if (popularAPIResponseList != null) {
			if (popularAPIResponseList.size() < 20)
				loopIndex = popularAPIResponseList.size();
			else
				loopIndex = 20;

			for (int index = 0; index < loopIndex; index++) {
				VideoDetails videoDetails = popularAPIResponseList.get(index);

				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					allPopularChannelsList.add(videoDetails);
				}
			}
		}
		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();

		finalMap.put("popularChannelsList", allPopularChannelsList);

		return finalMap;
	}

	/*
	 * Name: popularChannelsHasOnlyEpisodes Module: HOME Page Description: This
	 * method to get all Popular Channels List Details.
	 */
	private static List<VideoDetails> popularVideosDirectlyUnderChannels = null;
	private static List<VideoDetails> popularChannelsWithZeroShowAndEpisodes = null;
	private static List<VideoDetails> popularChannelHasZeroShows = null;
	private static List<VideoDetails> popularChannelHasOnlyEpisodes = null;

	public static Map<String, List<VideoDetails>> popularChannelsHasShowsOrEpisodes()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		popularResponse = getSessionTokenResponse(UrlFactoryUtil.getInstance()
				.getPopularURL(), sessionToken);
		List<VideoDetails> popularAPIResponseList = JsonParser
				.parsePopularChannelsResponse(popularResponse);

		popularVideosDirectlyUnderChannels = new ArrayList<VideoDetails>();
		popularChannelHasZeroShows = new ArrayList<VideoDetails>();
		popularChannelsWithZeroShowAndEpisodes = new ArrayList<VideoDetails>();
		popularChannelHasOnlyEpisodes = new ArrayList<VideoDetails>();

		if (popularAPIResponseList != null
				&& popularAPIResponseList.size() < 15) {
			for (VideoDetails popularChannelDetails : popularAPIResponseList) {
				if (popularChannelDetails.getCuratedItemType() != null
						&& popularChannelDetails.getCuratedItemType()
								.equalsIgnoreCase("CuratedChannel")) {
					int showCount = Integer.parseInt(popularChannelDetails
							.getNumOfShows());
					if (showCount == 0) {
						popularChannelHasZeroShows.add(popularChannelDetails);

						int episodeCount = Integer
								.parseInt(popularChannelDetails
										.getNumOfVideos());
						if (episodeCount > 0) {
							popularChannelHasOnlyEpisodes
									.add(popularChannelDetails);
							String videoResponse = getSessionTokenResponse(
									UrlFactoryUtil
											.getInstance()
											.getVideoDetailsURL(
													popularChannelDetails
															.getId(),
													10), sessionToken);
							List<VideoDetails> popularVideoList = JsonParser
									.parseChannelShowsVideosResponse(videoResponse);

							if (popularVideoList != null
									&& popularVideoList.size() < 5) {
								for (VideoDetails videoList : popularVideoList) {
									popularVideosDirectlyUnderChannels
											.add(videoList);
								}
							} else {
								for (int i = 0; i < 5; i++) {
									VideoDetails videoList = popularVideoList
											.get(i);
									popularVideosDirectlyUnderChannels
											.add(videoList);
								}
							}
						}
						if (showCount == 0 && episodeCount == 0) {
							popularChannelsWithZeroShowAndEpisodes
									.add(popularChannelDetails);
						}
					}
				}
			}
		} else {
			for (int index = 0; index < 15; index++) {
				VideoDetails popularChannelDetails = popularAPIResponseList
						.get(index);
				if (popularChannelDetails.getCuratedItemType() != null
						&& popularChannelDetails.getCuratedItemType()
								.equalsIgnoreCase("CuratedChannel")) {
					int showCount = Integer.parseInt(popularChannelDetails
							.getNumOfShows());
					if (showCount == 0) {
						popularChannelHasZeroShows.add(popularChannelDetails);

						int episodeCount = Integer
								.parseInt(popularChannelDetails
										.getNumOfVideos());
						if (episodeCount > 0) {

							String videoResponse = getSessionTokenResponse(
									UrlFactoryUtil
											.getInstance()
											.getVideoDetailsURL(
													popularChannelDetails
															.getId(),
													10), sessionToken);
							List<VideoDetails> popularVideoList = JsonParser
									.parseChannelShowsVideosResponse(videoResponse);

							if (popularVideoList != null
									&& popularVideoList.size() < 5) {
								for (VideoDetails videoList : popularVideoList) {
									popularVideosDirectlyUnderChannels
											.add(videoList);
								}
							} else {
								for (int i = 0; i < 5; i++) {
									VideoDetails videoList = popularVideoList
											.get(i);
									popularVideosDirectlyUnderChannels
											.add(videoList);
								}
							}
						}
						if (showCount == 0 && episodeCount == 0) {
							popularChannelsWithZeroShowAndEpisodes
									.add(popularChannelDetails);
						}
					}
				}
			}
		}
		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();

		finalMap.put("popularChannelHasZeroShows", popularChannelHasZeroShows);
		finalMap.put("popularVideosDirectlyUnderChannels",
				popularVideosDirectlyUnderChannels);
		finalMap.put("popularChannelsWithZeroShowAndEpisodes",
				popularChannelsWithZeroShowAndEpisodes);
		finalMap.put("popularChannelHasOnlyEpisodes",
				popularChannelHasOnlyEpisodes);

		return finalMap;
	}

	/*
	 * Name: featuredChannelsList Module: HOME Page Description: This method
	 * provides all featured channels list.
	 */
	public static int loopMaxIndex = 0;

	public static Map<String, List<VideoDetails>> featuredChannelsList()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String nFeaturedDetails = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getFeaturedURL(), sessionToken);
		List<VideoDetails> featuredAPIResponseList = JsonParser
				.parsenFeaturedResponse(nFeaturedDetails);

		featuredChannelList = new ArrayList<VideoDetails>();

		if (featuredAPIResponseList != null) {
			if (featuredAPIResponseList.size() < 20)
				loopMaxIndex = featuredAPIResponseList.size();
			else
				loopMaxIndex = 20;

			for (int index = 0; index < loopMaxIndex; index++) {
				VideoDetails videoDetails = featuredAPIResponseList.get(index);
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					int showCount = 0;
					int episodeCount = 0;
					// String showCountResponse
					// =getSessionTokenResponse(UrlFactoryUtil.getInstance().getChannelDetailsURL(videoDetails.getId()),sessionToken);
					// if(showCountResponse!=null &&
					// showCountResponse.length()>0)
					// showCount =Integer.parseInt(
					// JsonParser.parseFeaturedChannelShowCountResponse(showCountResponse));
					showCount = Integer.parseInt(videoDetails.getNumOfShows());
					if (showCount > 0) {
						String episodeCountResponse = getSessionTokenResponse(
								UrlFactoryUtil.getInstance()
										.getChannelDetailsURL(
												videoDetails.getId()),
								sessionToken);
						if (episodeCountResponse != null
								&& episodeCountResponse.length() > 0) {
							// episodeCount
							// =Integer.parseInt(JsonParser.parseFeaturedChannelVideoCountResponse(episodeCountResponse));
							episodeCount = Integer.parseInt(videoDetails
									.getNumOfVideos());
							if (episodeCount > 0)
								featuredChannelList.add(videoDetails);
						}
					}
				}
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("featuredChannelsList", featuredChannelList);

		return finalMap;
	}

	private static List<VideoDetails> featuredChannelList;
	private static List<VideoDetails> featuredShowList;

	public static Map<String, List<VideoDetails>> nHomeFeaturedAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		featuredChannelList = new ArrayList<VideoDetails>();
		featuredShowList = new ArrayList<VideoDetails>();

		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String nFeaturedDetails = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getFeaturedURL(), sessionToken);
		List<VideoDetails> featuredAPIResponseList = JsonParser
				.parsenFeaturedResponse(nFeaturedDetails);

		if (featuredAPIResponseList != null) {
			if (featuredAPIResponseList.size() < 10)
				loopMaxIndex = featuredAPIResponseList.size();
			else
				loopMaxIndex = 10;

			for (int index = 0; index < loopMaxIndex; index++) {
				VideoDetails videoDetails = featuredAPIResponseList.get(index);
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					int showCount = 0;
					int episodeCount = 0;
					String showCountResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance().getChannelDetailsURL(
									videoDetails.getId()), sessionToken);
					if (showCountResponse != null
							&& showCountResponse.length() > 0)
						showCount = Integer
								.parseInt(JsonParser
										.parseFeaturedChannelShowCountResponse(showCountResponse));
					if (showCount > 0) {
						String episodeCountResponse = getSessionTokenResponse(
								UrlFactoryUtil.getInstance()
										.getChannelDetailsURL(
												videoDetails.getId()),
								sessionToken);
						if (episodeCountResponse != null
								&& episodeCountResponse.length() > 0) {
							episodeCount = Integer
									.parseInt(JsonParser
											.parseFeaturedChannelVideoCountResponse(episodeCountResponse));
							if (episodeCount > 0)
								featuredChannelList.add(videoDetails);
						}
					}
				}
			}
		}
		if (featuredAPIResponseList != null) {
			if (featuredAPIResponseList.size() < 20)
				loopMaxIndex = featuredAPIResponseList.size();
			else
				loopMaxIndex = 20;

			for (int index = 0; index < loopMaxIndex; index++) {
				VideoDetails videoDetails = featuredAPIResponseList.get(index);
				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedShow")) {
					featuredShowList.add(videoDetails);
				}
			}
		}

		VideoDetails featuredChannelDetails = featuredChannelList
				.get(XidioConstant.selectFeaturedChannel);
		String subShowResponse = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getSubShowURL(featuredChannelDetails.getId()),
				sessionToken);
		List<VideoDetails> homeFeaturedSubShowsList = JsonParser
				.parseShowsResponse(subShowResponse);

		List<VideoDetails> featuredShowsListUnderChannel = new ArrayList<VideoDetails>();

		if (homeFeaturedSubShowsList != null) {
			if (homeFeaturedSubShowsList.size() < 10)
				loopMaxIndex = homeFeaturedSubShowsList.size();
			else
				loopMaxIndex = 10;

			for (int index = 0; index < loopMaxIndex; index++) {
				VideoDetails videoDetails = homeFeaturedSubShowsList.get(index);
				featuredShowsListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails featuredSubShowDetails = featuredShowsListUnderChannel
				.get(0);
		String videoResponse = getSessionTokenResponse(
				UrlFactoryUtil.getInstance().getVideoDetailsURL(
						featuredSubShowDetails.getId(), 10), sessionToken);
		List<VideoDetails> channelShowsVideoList = JsonParser
				.parseChannelShowsVideosResponse(videoResponse);

		List<VideoDetails> featuredVideoListUnderChannel = new ArrayList<VideoDetails>();

		if (channelShowsVideoList != null) {
			if (channelShowsVideoList.size() < 10)
				loopMaxIndex = channelShowsVideoList.size();
			else
				loopMaxIndex = 10;

			for (int index = 0; index < loopMaxIndex; index++) {
				VideoDetails videoDetails = channelShowsVideoList.get(index);
				featuredVideoListUnderChannel.add(videoDetails);
			}
		}

		VideoDetails VideoUnderSubShowOfHomePage = featuredShowList
				.get(XidioConstant.selectShow);
		String homePageSubShowVideoResponse = getSessionTokenResponse(
				UrlFactoryUtil.getInstance().getVideoDetailsURL(
						VideoUnderSubShowOfHomePage.getId(), 10), sessionToken);
		List<VideoDetails> homePageSubShowVideos = JsonParser
				.parseChannelShowsVideosResponse(homePageSubShowVideoResponse);

		List<VideoDetails> homePageSubShowVideoList = new ArrayList<VideoDetails>();
		if (homePageSubShowVideos != null) {
			if (homePageSubShowVideos.size() < 10)
				loopMaxIndex = homePageSubShowVideos.size();
			else
				loopMaxIndex = 10;

			for (int index = 0; index < loopMaxIndex; index++) {
				VideoDetails videoDetails = homePageSubShowVideos.get(index);
				homePageSubShowVideoList.add(videoDetails);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("featuredChannelsList", featuredChannelList);
		finalMap.put("featuredShowList", featuredShowList);
		finalMap.put("subShowVideos", homePageSubShowVideoList);
		finalMap.put("video", featuredVideoListUnderChannel);
		finalMap.put("showsUnderChannel", featuredShowsListUnderChannel);

		return finalMap;
	}

	/*
	 * Name: nhomePageAPIs Module: HOME Page Description: This method provides
	 * all category details.
	 */
	public static List<VideoDetails> nhomePageAPIs()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String nFeaturedDetails = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getFeaturedURL(), sessionToken);
		List<VideoDetails> featuredAPIResponseList = JsonParser
				.parsenFeaturedResponse(nFeaturedDetails);
		VideoDetails homefeaturedChannelDetails = featuredAPIResponseList
				.get(0);

		String popularShowResponse = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getPopularShowsURL(), sessionToken);
		List<VideoDetails> homePopularShowslList = JsonParser
				.parsePopularShowsResponse(popularShowResponse);
		VideoDetails homePopularShowDetails = homePopularShowslList.get(0);

		String popularChannelResponse1 = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getPopularURL(), sessionToken);
		List<VideoDetails> homePopularChannelList = JsonParser
				.parsePopularChannelsResponse(popularChannelResponse1);
		VideoDetails homePopularChannelDetails = homePopularChannelList.get(0);

		List<VideoDetails> homePageVideoDetailsList = new ArrayList<VideoDetails>();

		homePageVideoDetailsList.add(homefeaturedChannelDetails);
		homePageVideoDetailsList.add(homePopularShowDetails);
		homePageVideoDetailsList.add(homePopularChannelDetails);

		return homePageVideoDetailsList;
	}

	/*
	 * HOME Popular Show API's
	 */
	private static List<VideoDetails> popularShowslList = null;
	private static List<VideoDetails> popularShowsVideoList = null;

	public static Map<String, List<VideoDetails>> nHomePopularShowsAPIs()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}
		popularShowslList = new ArrayList<VideoDetails>();
		popularShowsVideoList = new ArrayList<VideoDetails>();

		String popularShowResponse = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getPopularShowsURL(), sessionToken);
		List<VideoDetails> homePopularShowslList = JsonParser
				.parsePopularShowsResponse(popularShowResponse);

		int loopIndex = 0;
		if (homePopularShowslList != null) {
			if (homePopularShowslList.size() < 12)
				loopIndex = homePopularShowslList.size();
			else
				loopIndex = 12;
			for (int index = 0; index < loopIndex; index++) {
				VideoDetails popularShowList = homePopularShowslList.get(index);
				popularShowslList.add(popularShowList);
			}
		}

		int ShowsLoopIndex = 0;
		if (popularShowslList != null) {
			if (popularShowslList.size() < 5)
				ShowsLoopIndex = popularShowsList.size();
			else
				ShowsLoopIndex = 5;
			for (int i = 0; i < ShowsLoopIndex; i++) {
				VideoDetails popularShowVideos = popularShowslList.get(i);
				String videoResponse = getSessionTokenResponse(
						UrlFactoryUtil.getInstance().getVideoDetailsURL(
								popularShowVideos.getId(), 10), sessionToken);
				List<VideoDetails> homePopularShowsVideoList = JsonParser
						.parseChannelShowsVideosResponse(videoResponse);

				int videoIndex = 0;
				if (homePopularShowsVideoList != null) {
					if (homePopularShowsVideoList.size() < 4)
						videoIndex = homePopularShowsVideoList.size();
					else
						videoIndex = 4;
					for (int index = 0; index < videoIndex; index++) {
						VideoDetails videos = homePopularShowsVideoList
								.get(index);
						popularShowsVideoList.add(videos);
					}
				}

			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("popularShows", popularShowslList);
		finalMap.put("popularvideos", popularShowsVideoList);

		return finalMap;
	}

	/*
	 * Name: homeFeaturedChannelDetails Module: HOME Page Description: This
	 * method provides Channel details, Show count, Episode count and
	 * lastUpdated Time stamp details.
	 */

	public static Map<String, List<VideoDetails>> homeFeaturedChannelDetails()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String nFeaturedDetails = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getFeaturedURL(), sessionToken);
		List<VideoDetails> featuredAPIResponseList = JsonParser
				.parsenFeaturedResponse(nFeaturedDetails);
		nfeaturedShowsList = new ArrayList<VideoDetails>();

		if (featuredAPIResponseList != null
				&& featuredAPIResponseList.size() < 20) {
			int maxloopIndex = 0;
			if (featuredAPIResponseList.size() < 20)
				maxloopIndex = featuredAPIResponseList.size();
			else
				maxloopIndex = 20;

			for (int index = 0; index < maxloopIndex; index++) {
				VideoDetails videoDetails = featuredAPIResponseList.get(index);

				if (videoDetails.getCuratedItemType() != null
						&& videoDetails.getCuratedItemType().equalsIgnoreCase(
								"CuratedChannel")) {
					String shwcnt = null;
					String episodeCount = null;
					String lastUpdatedTimeStamp = null;
					String showCountResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance().getChannelDetailsURL(
									videoDetails.getId()), sessionToken);
					if (showCountResponse != null
							&& showCountResponse.length() > 0) {
						shwcnt = JsonParser
								.parseFeaturedChannelShowCountResponse(showCountResponse);
						if (shwcnt != null) {
							if (Integer.parseInt(shwcnt) > 0) {
								videoDetails.setShowCount(shwcnt);
								String episodeCountResponse = getSessionTokenResponse(
										UrlFactoryUtil.getInstance()
												.getChannelDetailsURL(
														videoDetails.getId()),
										sessionToken);
								if (episodeCountResponse != null
										&& episodeCountResponse.length() > 0) {
									episodeCount = JsonParser
											.parseFeaturedChannelVideoCountResponse(episodeCountResponse);
									lastUpdatedTimeStamp = JsonParser
											.parseLastUpdatedTimeStampResponse(episodeCountResponse);
									videoDetails
											.setLastUpdateTimestamp(lastUpdatedTimeStamp);

									if (episodeCount != null) {
										if (Integer.parseInt(episodeCount) > 0) {
											videoDetails
													.setEpisodeCount(episodeCount);
											nfeaturedShowsList
													.add(videoDetails);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("featuredShowsList", nfeaturedShowsList);

		return finalMap;
	}

	public static int episodeCountForHomeFeaturedChannel()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		int episodeCount = 0;
		VideoDetails featuredChannelDetails = nfeaturedShowsList
				.get(XidioConstant.selectFeaturedChannel);
		String episodeCountResponse = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getSubShowURL(featuredChannelDetails.getId()),
				sessionToken);
		episodeCount = Integer.parseInt(JsonParser
				.parseFeaturedChannelVideoCountResponse(episodeCountResponse));
		return episodeCount;
	}

	/*
	 * Genres Category API's
	 */
	public static Map<String, List<VideoDetails>> GenresCategoryDetails()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		/*
		 * //Calling Subscriptions API. GenresAPI();
		 * 
		 * VideoDetails subscriptionsChannelDetails
		 * =subscriptionsShowsList.get(XidioConstant.selectSubscribedChannel);
		 * System
		 * .out.println("subscriptionsChannelDetails>>>"+subscriptionsChannelDetails
		 * .getTitle()); String subShowResponse =
		 * DomParserXPATH.getCategories(UrlFactoryUtil
		 * .getInstance().getSubShowURL(subscriptionsChannelDetails.getId()));
		 * 
		 * List<VideoDetails> subscriptionsShowList
		 * =JsonParser.parseSubscriptionsShowsResponse(subShowResponse);
		 * 
		 * List<VideoDetails> subscribedShowsListUnderChannel=new
		 * ArrayList<VideoDetails>(); if(subscriptionsShowList!=null &&
		 * subscriptionsShowList.size()<10) { for(VideoDetails
		 * videoDetails:subscriptionsShowList) {
		 * subscribedShowsListUnderChannel.add(videoDetails); } } else { for(int
		 * index=0; index<10; index++) { VideoDetails
		 * videoDetails=subscriptionsShowList.get(index);
		 * subscribedShowsListUnderChannel.add(videoDetails); } }
		 * 
		 * VideoDetails subscriptionsShowDetails =subscriptionsShowList.get(0);
		 * System
		 * .out.println("subscriptionsShowDetails>>>"+subscriptionsShowDetails
		 * .getTitle()); String videoResponse
		 * =DomParserXPATH.getCategories(UrlFactoryUtil
		 * .getInstance().getVideoDetailsURL(subscriptionsShowDetails.getId(),
		 * 12)); List<VideoDetails> subscriptionsVideoList
		 * =JsonParser.parseSubscriptionsVideosResponse(videoResponse);
		 * 
		 * List<VideoDetails> subscriptionsVideoListUnderChannel=new
		 * ArrayList<VideoDetails>(); if(subscriptionsVideoList!=null &&
		 * subscriptionsVideoList.size()<10) { for(VideoDetails
		 * videoDetails:subscriptionsVideoList) {
		 * subscriptionsVideoListUnderChannel.add(videoDetails); } } else {
		 * for(int index=0; index<10; index++) { VideoDetails
		 * videoDetails=subscriptionsVideoList.get(index);
		 * subscriptionsVideoListUnderChannel.add(videoDetails); } }
		 * 
		 * Map <String,List<VideoDetails>> finalMap= new HashMap
		 * <String,List<VideoDetails>>(); finalMap.put("subscribedBundle",
		 * subscriptionsBundlesList); finalMap.put("subscribedShows",
		 * subscriptionsShowsList); finalMap.put("subscribedSubShow",
		 * subscriptionsSubShowList);
		 * finalMap.put("subscribedShowsUnderChannel",
		 * subscribedShowsListUnderChannel); finalMap.put("subscribedVideo",
		 * subscriptionsVideoListUnderChannel);
		 * 
		 * return finalMap;
		 */
		return null;
	}

	/*
	 * Name: GenresAPI Module: Home page Description: This method provides Genre
	 * API Response Details.
	 */
	private static List<VideoDetails> genresCategoriesList = null;
	private static List<VideoDetails> genresCategoryShowsList = null;
	private static List<VideoDetails> genresCategoryChannelsList = null;
	public static List<VideoDetails> genresResponseList;
	public static String sessionToken = null;

	public static Map<String, List<VideoDetails>> GenresAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String geressResponse = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getGenresUrl(), sessionToken);
		System.out.println(" genreResponse ... >>>>>>>" + geressResponse);
		genresResponseList = JsonParser.parseGenresResponse(geressResponse);

		genresCategoryShowsList = new ArrayList<VideoDetails>();
		genresCategoryChannelsList = new ArrayList<VideoDetails>();
		genresCategoriesList = new ArrayList<VideoDetails>();

		int totalGenreItems = 0;
		if (genresResponseList != null) {
			totalGenreItems = JsonParser
					.parseGenresItemsCountResponse(geressResponse);

			for (int index = 0; index < totalGenreItems; index++) {
				VideoDetails genreCategory = genresResponseList.get(index);
				genresCategoriesList.add(genreCategory);

				if (genreCategory.getTotalShows() >= 1) {
					genresCategoryShowsList.add(genreCategory);
				}

				if (genreCategory.getTotalChannels() >= 1) {
					genresCategoryChannelsList.add(genreCategory);
				}
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("genresCategoriesList", genresCategoriesList);
		finalMap.put("genresCategoryShowsList", genresCategoryShowsList);
		finalMap.put("genresCategoryChannelsList", genresCategoryChannelsList);
		return finalMap;
	}

	public static Map<String, List<VideoDetails>> subscriptionGenresAPI()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TestDataGenerator proUtil = new TestDataGenerator();

		proUtil.load(new FileInputStream(new File("com/data.properties")));
		String userName = proUtil.getProperty("USER_NAME");
		String regPassword = proUtil.getProperty("REG_PASSWORD");
		String bearerToken = null;

		try {
			bearerToken = userAuthentication(userName, regPassword,
					sessionToken);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String subGenreResponse = getBearerResponse(UrlFactoryUtil
				.getInstance().getSubscriptionsGenreURL(), sessionToken,
				bearerToken);
		System.out.println(" genreResponse ... >>>>>>>" + subGenreResponse);
		genresResponseList = JsonParser.parseGenresResponse(subGenreResponse);

		genresCategoryShowsList = new ArrayList<VideoDetails>();
		genresCategoryChannelsList = new ArrayList<VideoDetails>();
		genresCategoriesList = new ArrayList<VideoDetails>();
		GenreCategoriesChannelList = new ArrayList<VideoDetails>();

		if (genresResponseList != null && genresResponseList.size() < 13) {
			for (VideoDetails videoDetails : genresResponseList) {
				genresCategoriesList.add(videoDetails);

				if (videoDetails.getTotalShows() >= 1) {
					genresCategoryShowsList.add(videoDetails);
				}

				if (videoDetails.getTotalChannels() >= 1) {
					genresCategoryChannelsList.add(videoDetails);
				}

				if (videoDetails.getTotalChannels() >= 1) {
					int totalItemsInChannelsResp = 0;
					String genreChannelCountResponse = getBearerResponse(
							UrlFactoryUtil.getInstance()
									.getSubscribedGenresChannelURL(
											videoDetails.getId()),
							sessionToken, bearerToken);
					totalItemsInChannelsResp = JsonParser
							.parseGenresItemsCountResponse(genreChannelCountResponse);
					if (totalItemsInChannelsResp > 0) {
						List<VideoDetails> genreChannelsResponse = JsonParser
								.parseGenresChannelsResponse(genreChannelCountResponse);
						for (VideoDetails channelsDetails : genreChannelsResponse) {
							GenreCategoriesChannelList.add(channelsDetails);
							System.out.println("GenreChannelList:>>"
									+ channelsDetails.getTitle());
						}
					}
				}
			}

		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("genresCategoriesList", genresCategoriesList);
		finalMap.put("genresCategoryChannelsList", GenreCategoriesChannelList);
		return finalMap;
	}

	/*
	 * Name: AnimationAndGames Module: Home Page Description: This method
	 * provides Animation And Games API Response Details.
	 */
	private static List<VideoDetails> GenreCategoriesShowList;
	private static List<VideoDetails> GenreCategoriesAllShowList;
	private static List<VideoDetails> GenreCategoriesZeroEpisodeShowList;
	private static List<VideoDetails> GenreCategoriesShowVideosList;
	private static List<VideoDetails> GenreCategoriesChannelList;

	public static Map<String, List<VideoDetails>> GenreCategoriesDetail(
			String genCategoryName) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// Call Genre API
		GenresAPI();

		boolean categoryFound = false;
		GenreCategoriesShowList = new ArrayList<VideoDetails>();
		GenreCategoriesAllShowList = new ArrayList<VideoDetails>();
		GenreCategoriesChannelList = new ArrayList<VideoDetails>();
		GenreCategoriesZeroEpisodeShowList = new ArrayList<VideoDetails>();
		GenreCategoriesShowVideosList = new ArrayList<VideoDetails>();

		if (genresResponseList != null) {
			int loopGenreMaxIndex = 0;
			if (genresResponseList.size() <= 25)
				loopGenreMaxIndex = genresResponseList.size();
			else
				loopGenreMaxIndex = 25;

			for (int index = 0; index < loopGenreMaxIndex; index++) {
				VideoDetails genreCategory = genresResponseList.get(index);
				// if(genreCategory.getTotalShows()>=1 ||
				// genreCategory.getTotalChannels()>=1 &&
				// genreCategory.getTitle().equalsIgnoreCase(genCategoryName))
				if (genreCategory.getTitle().equalsIgnoreCase(genCategoryName)) {
					categoryFound = true;
					int totalItemsInChannelsResp = 0;
					int totalItemsInShowsResp = 0;

					String geresShowsResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance().getGenresShowUrl(
									genreCategory.getId()), sessionToken);
					if (geresShowsResponse != null
							&& geresShowsResponse.length() > 0) {
						totalItemsInShowsResp = JsonParser
								.parseGenresItemsCountResponse(geresShowsResponse);
						if (totalItemsInShowsResp > 0) {
							List<VideoDetails> genreShowsResponse = JsonParser
									.parseGenresShowsResponse(geresShowsResponse);
							int loopMaxIndex = 0;
							if (genreShowsResponse.size() < 12)
								loopMaxIndex = genreShowsResponse.size();
							else
								loopMaxIndex = 12;

							for (int index1 = 0; index1 < loopMaxIndex; index1++) {
								VideoDetails showDetails = genreShowsResponse
										.get(index1);
								GenreCategoriesAllShowList.add(showDetails);

								int numOfVideos = Integer.parseInt(showDetails
										.getNumOfVideos());
								if (numOfVideos > 0) {
									GenreCategoriesShowList.add(showDetails);
									String geresEpisodeAPIResponse = getSessionTokenResponse(
											UrlFactoryUtil
													.getInstance()
													.getVideoDetailsURL(
															(showDetails
																	.getId()),
															10), sessionToken);
									List<VideoDetails> genreShowsVideoList = JsonParser
											.parseChannelShowsVideosResponse(geresEpisodeAPIResponse);

									if (genreShowsVideoList != null) {
										int maxShowList = 0;
										if (genreShowsVideoList.size() < 4)
											maxShowList = genreShowsVideoList
													.size();
										else
											maxShowList = 4;
										for (int videos = 0; videos < maxShowList; videos++) {
											VideoDetails videoList = genreShowsVideoList
													.get(videos);
											GenreCategoriesShowVideosList
													.add(videoList);
										}
									}
								} else {
									GenreCategoriesZeroEpisodeShowList
											.add(showDetails);
								}
							}
						}
					}

					String genreChannelCountResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance().getGenresChannelURL(
									genreCategory.getId()), sessionToken);
					if (genreChannelCountResponse != null
							&& genreChannelCountResponse.length() > 0) {
						totalItemsInChannelsResp = JsonParser
								.parseGenresItemsCountResponse(genreChannelCountResponse);
						if (totalItemsInChannelsResp > 0) {
							List<VideoDetails> genreChannelsResponse = JsonParser
									.parseGenresChannelsResponse(genreChannelCountResponse);
							int maxChannelsList = 0;
							if (genreChannelsResponse.size() < 10)
								maxChannelsList = genreChannelsResponse.size();
							else
								maxChannelsList = 10;

							for (int channelList = 0; channelList < maxChannelsList; channelList++) {
								VideoDetails genreChannels = genreChannelsResponse
										.get(channelList);
								GenreCategoriesChannelList.add(genreChannels);
							}
						}
					}
				}
				if (categoryFound == true)
					break;
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("genresCategoryList", genresCategoriesList);
		finalMap.put("GenreCategoriesAllShowList", GenreCategoriesAllShowList);
		finalMap.put("genresCategoriesList", genresCategoryShowsList);
		finalMap.put("genresCategoryChannelsList", genresCategoryChannelsList);
		finalMap.put("genreCategoriesShowsList", GenreCategoriesShowList);
		finalMap.put("genreCategoriesChannelsList", GenreCategoriesChannelList);
		finalMap.put("genreCategoriesZeroEpisodeShowList",
				GenreCategoriesZeroEpisodeShowList);

		return finalMap;
	}

	/*
	 * Name: AnimationAndGames Module: Home Page Description: This method
	 * provides Animation And Games API Response Details.
	 */
	private static List<VideoDetails> GenreCategoriesEpisodesList;

	public static Map<String, List<VideoDetails>> GenreCategoryEpisodesDetail(
			String genCategoryName) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// Call Genre API
		GenresAPI();

		boolean categoryFound = false;
		GenreCategoriesEpisodesList = new ArrayList<VideoDetails>();

		if (genresResponseList != null && genresResponseList.size() < 13) {
			for (VideoDetails videoDetails : genresResponseList) {
				if (videoDetails.getTotalShows() >= 1
						&& videoDetails.getTotalChannels() >= 1
						&& videoDetails.getTitle().equalsIgnoreCase(
								genCategoryName)) {
					categoryFound = true;
					String geresEpisodeAPIResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance()
									.getGenreAllEpisodesURL(
											videoDetails.getId()), sessionToken);
					List<VideoDetails> genreVideoList = JsonParser
							.parseChannelShowsVideosResponse(geresEpisodeAPIResponse);
					if (genreVideoList != null) {
						int maxloopIndex = 0;
						if (genreVideoList.size() < 5)
							maxloopIndex = genreVideoList.size();
						else
							maxloopIndex = 5;

						for (int index = 0; index < maxloopIndex; index++) {
							VideoDetails videoList = genreVideoList.get(index);
							GenreCategoriesEpisodesList.add(videoList);
						}

					}
				}
				if (categoryFound == true)
					break;
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("genresCategoryList", genresCategoriesList);
		finalMap.put("GenreCategoriesEpisodesList", GenreCategoriesEpisodesList);

		return finalMap;
	}

	/*
	 * Name: AnimationAndGames Module: Home Page Description: This method
	 * provides Animation And Games API Response Details.
	 */
	private static List<VideoDetails> GenreCategoriesPopular;

	public static Map<String, List<VideoDetails>> GenreCategoryPopularDetail(
			String genCategoryName) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// Call Genre API
		GenresAPI();

		boolean categoryFound = false;
		GenreCategoriesPopular = new ArrayList<VideoDetails>();

		if (genresResponseList != null && genresResponseList.size() < 25) {
			for (VideoDetails videoDetails : genresResponseList) {
				if (videoDetails.getTitle().equalsIgnoreCase(genCategoryName)) {
					categoryFound = true;
					String geresPopularAPIResponse = getSessionTokenResponse(
							UrlFactoryUtil.getInstance().getGenrePopularURL(
									videoDetails.getId()), sessionToken);
					List<VideoDetails> genrePopularList = JsonParser
							.parseChannelShowsVideosResponse(geresPopularAPIResponse);
					if (genrePopularList != null) {
						int maxloopIndex = 0;
						if (genrePopularList.size() < 10)
							maxloopIndex = genrePopularList.size();
						else
							maxloopIndex = 10;

						for (int index = 0; index < maxloopIndex; index++) {
							VideoDetails videoList = genrePopularList
									.get(index);
							GenreCategoriesPopular.add(videoList);
						}

					}
				}
				if (categoryFound == true)
					break;
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("genresCategoryList", genresCategoriesList);
		finalMap.put("GenreCategoriesPopularList", GenreCategoriesPopular);

		return finalMap;
	}

	/*
	 * Name: AnimationAndGames Module: Home Page Description: This method
	 * provides Animation And Games API Response Details.
	 */
	private static List<VideoDetails> searchChannelsList;
	private static List<VideoDetails> searchShowsList;
	private static List<VideoDetails> searchEpisodesList;

	public static Map<String, List<VideoDetails>> searchResultDetails(
			String channelName, String showName, String episodeName)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		searchChannelsList = new ArrayList<VideoDetails>();
		searchShowsList = new ArrayList<VideoDetails>();
		searchEpisodesList = new ArrayList<VideoDetails>();

		// This is to get all the channels search result
		String channelSearchResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getChannelSeachURL(
						channelName, 25));
		List<VideoDetails> searchChannelList = JsonParser
				.parseChannelSearchResponse(channelSearchResponse);
		if (searchChannelList != null && searchChannelList.size() < 5) {
			for (VideoDetails channelSearchResult : searchChannelList) {
				searchChannelsList.add(channelSearchResult);
			}
		} else {
			for (int index = 0; index < 5; index++) {
				VideoDetails channelSearchResult = searchChannelList.get(index);
				searchChannelsList.add(channelSearchResult);
			}
		}

		// This is to get all the show search result
		String showSearchResponse = DomParserXPATH.getCategories(UrlFactoryUtil
				.getInstance().getChannelSeachURL(showName, 25));
		List<VideoDetails> searchShowList = JsonParser
				.parseChannelSearchResponse(showSearchResponse);
		if (searchShowList != null && searchShowList.size() < 5) {
			for (VideoDetails showSearchResult : searchShowList) {
				searchShowsList.add(showSearchResult);
			}
		} else {
			for (int index = 0; index < 5; index++) {
				VideoDetails showSearchResult = searchShowList.get(index);
				searchShowsList.add(showSearchResult);
			}
		}

		// This is to get all the episodes search result
		String episodeSearchResponse = DomParserXPATH
				.getCategories(UrlFactoryUtil.getInstance().getChannelSeachURL(
						episodeName, 25));
		List<VideoDetails> searchEpisodeList = JsonParser
				.parseEpisodeSearchResponse(episodeSearchResponse);
		if (searchEpisodeList != null && searchEpisodeList.size() < 5) {
			for (VideoDetails episodeSearchResult : searchEpisodeList) {
				searchEpisodesList.add(episodeSearchResult);
			}
		} else {
			for (int index = 0; index < 5; index++) {
				VideoDetails episodeSearchResult = searchEpisodeList.get(index);
				searchEpisodesList.add(episodeSearchResult);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("searchChannelsResult", searchChannelsList);
		finalMap.put("searchShowsResult", searchShowsList);
		finalMap.put("searchEpisodesResult", searchEpisodesList);

		return finalMap;
	}

	/*
	 * Name: publisherDetails Module: Home Page Description: This method
	 * provides publisher details API Response.
	 */
	private static List<VideoDetails> publishersDetails;

	public static Map<String, List<VideoDetails>> publisherDetails(
			String publisherID) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		publishersDetails = new ArrayList<VideoDetails>();

		// This is to get publisher details result
		String publisherResponse = RestAPIServices.getSessionTokenResponse(
				UrlFactoryUtil.getInstance()
						.getPublisherDetailsURL(publisherID), sessionToken);
		List<VideoDetails> publisherDetails = JsonParser
				.parsePublisherDetailsResponse(publisherResponse);

		if (publisherDetails != null) {
			for (VideoDetails publisherResult : publisherDetails) {
				publishersDetails.add(publisherResult);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("publishersDetails", publishersDetails);

		return finalMap;
	}

	/*
	 * Name: publisherDetails Module: Home Page Description: This method
	 * provides publisher details API Response.
	 */
	private static List<VideoDetails> publishersChannelsDetail;

	public static Map<String, List<VideoDetails>> publisherChannelsDetails(
			String publisherID) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		publishersChannelsDetail = new ArrayList<VideoDetails>();

		// This is to get publisher details result
		String publisherResponse = RestAPIServices.getSessionTokenResponse(
				UrlFactoryUtil.getInstance().getPublisherChannelsDetailsURL(
						publisherID), sessionToken);
		List<VideoDetails> publisherChannelDetails = JsonParser
				.parseGenresChannelsResponse(publisherResponse);

		if (publisherChannelDetails != null
				&& publisherChannelDetails.size() < 10) {
			for (VideoDetails publisherResult : publisherChannelDetails) {
				publishersChannelsDetail.add(publisherResult);
			}
		} else {
			for (int index = 0; index < 10; index++) {
				VideoDetails publisherResult = publisherChannelDetails
						.get(index);
				publishersChannelsDetail.add(publisherResult);
			}
		}

		Map<String, List<VideoDetails>> finalMap = new HashMap<String, List<VideoDetails>>();
		finalMap.put("publishersChannels", publishersChannelsDetail);

		return finalMap;
	}

	/* API Testing */
	public static String webAPI_Testing() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String webFeaturedAPI = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getFeaturedURL(), sessionToken);
		return webFeaturedAPI;
	}

	/* API Testing */
	public static String iosAPI_Testing() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String iosFeaturedAPI = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getIOSFeaturedURL(), sessionToken);
		return iosFeaturedAPI;
	}

	/* API Testing */
	public static String androidAPI_Testing() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		try {
			sessionToken = executeGenreAuthentication();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String androidFeaturedAPI = getSessionTokenResponse(UrlFactoryUtil
				.getInstance().getAndroidFeaturedURL(), sessionToken);
		return androidFeaturedAPI;
	}
}
