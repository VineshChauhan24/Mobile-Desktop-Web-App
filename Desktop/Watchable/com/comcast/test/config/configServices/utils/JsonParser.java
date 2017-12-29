package comcast.test.config.configServices.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import comcast.test.config.dataServices.vo.VideoDetails;

public class JsonParser {
	/*
	 * This method parse the Store Feature Category Channels, Shows under
	 * channel and Videos user Channel
	 */
	public static List<VideoDetails> parseStoreFeaturedChannelsResponse(
			String featureResponse) {
		List<VideoDetails> featuredChannelList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject
					.getJSONObject("contentPanelElements");
			JSONArray jsonArray = jsonObject1
					.getJSONArray("contentPanelElement");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("contentKey"))
					videoDetails.setId(json.getString("contentKey"));
				if (json.has("contentType"))
					videoDetails.setContentType(json.getString("contentType"));
				if (json.has("category")) {
					JSONObject catagory = json.getJSONObject("category");
					if (catagory.has("level"))
						videoDetails.setLevel(catagory.getString("level"));
				}

				if (!videoDetails.getContentType().equalsIgnoreCase(
						"productgroup"))
					featuredChannelList.add(videoDetails);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return featuredChannelList;
	}

	/* This method parse the Store Feature Category Show Count under Channel */
	public static String parseStoreFeaturedShowCountResponse(
			String featureResponse) {
		String noOfHits = null;

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");
			if (jsonObject1.has("numberOfHits"))
				noOfHits = jsonObject1.getString("numberOfHits");
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return noOfHits;
	}

	/* This method parse the Store Popular Category Show Count under Channel */
	public static String parseHomePopularShowCountResponse(
			String popularResponse) {
		int noOfShows = 0;
		String showCount = null;
		boolean showInstance;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(popularResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");

			if (jsonObject1.has("category") && jsonObject1 != null) {
				showInstance = (jsonObject1.get("category") instanceof JSONObject);

				if (showInstance == true) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("category");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("category");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));
					if (json.has("level"))
						videoDetails.setLevel(json.getString("level"));
					if (json.has("lastAssetPublishedDate")) {
						String lastUapdatedDate = json
								.getString("lastAssetPublishedDate");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}
					noOfShows = i + 1;
					showCount = Integer.toString(noOfShows);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return showCount;
	}

	/* This method parse the episode Count under Channel */
	public static String parseEpisodeCountForChannelResponse(
			String popularResponse) {
		String noOfHits = null;
		try {
			JSONObject jsonObject = new JSONObject(popularResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("assets");
			if (jsonObject1.has("numberOfHits"))
				noOfHits = jsonObject1.getString("numberOfHits");
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return noOfHits;
	}

	/* This Method is to parse Featured shows Response from selected Channel */
	public static List<VideoDetails> parseHomeFeaturedShowsResponse(
			String featureResponse) {
		List<VideoDetails> featuredShowList = new ArrayList<VideoDetails>();
		boolean showInstance;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");

			if (jsonObject1.has("category") && jsonObject1 != null) {
				showInstance = (jsonObject1.get("category") instanceof JSONObject);

				if (showInstance == true) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("category");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("category");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));
					if (json.has("level"))
						videoDetails.setLevel(json.getString("level"));

					if (json.has("lastAssetPublishedDate")) {
						String lastUapdatedDate = json
								.getString("lastAssetPublishedDate");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}

					featuredShowList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return featuredShowList;
	}

	/* This Method is to parse Featured Video from selected Shows in a Channel */
	public static List<VideoDetails> parseHomeFeaturedVideosResponse(
			String featureResponse) {
		List<VideoDetails> featuredVideoList = new ArrayList<VideoDetails>();
		int noOfVideos = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("assets");

			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfVideos = Integer.parseInt(noOfHits);
			}

			if (jsonObject1.has("asset") && jsonObject1 != null) {
				if (noOfVideos == 1) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("asset");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("asset");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));

					featuredVideoList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return featuredVideoList;
	}

	/*
	 * This Method is to parse Store Featured Video from selected Shows in a
	 * Channel
	 */
	public static List<VideoDetails> parseStorePopularVideosResponse(
			String popularResponse) {
		List<VideoDetails> popularVideoList = new ArrayList<VideoDetails>();
		int noOfVideos = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(popularResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("assets");

			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfVideos = Integer.parseInt(noOfHits);
			}

			if (jsonObject1.has("asset") && jsonObject1 != null) {
				if (noOfVideos == 1) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("asset");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("asset");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));

					popularVideoList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return popularVideoList;
	}

	/*
	 * HOME
	 */

	/*
	 * This Method is to parse Store Featured Video from selected Shows in a
	 * Channel
	 */
	public static List<VideoDetails> parseUpNextVideosResponse(
			String upNextResponse) {
		List<VideoDetails> upNextVideoList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(upNextResponse);
			// JSONObject jsonObject1 = jsonObject.getJSONObject("assets");
			JSONArray jsonArray = jsonObject.getJSONArray("items");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("@id"))
					videoDetails.setId(json.getString("@id"));

				upNextVideoList.add(videoDetails);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return upNextVideoList;
	}

	/*
	 * This method used to parse the Home Feature Category Channels, Shows under
	 * channel and Videos
	 */
	public static List<VideoDetails> parseHomeFeaturedChannelsResponse(
			String featureResponse) {
		List<VideoDetails> featuredChannelList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject
					.getJSONObject("contentPanelElements");
			JSONArray jsonArray = jsonObject1
					.getJSONArray("contentPanelElement");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title").trim());
				if (json.has("contentKey"))
					videoDetails.setId(json.getString("contentKey"));
				if (json.has("contentType"))
					videoDetails.setContentType(json.getString("contentType"));
				if (json.has("category")) {
					JSONObject catagory = json.getJSONObject("category");
					if (catagory.has("level"))
						videoDetails.setLevel(catagory.getString("level"));
					if (catagory.has("lastAssetPublishedDate")) {
						String lastUapdatedDate = catagory
								.getString("lastAssetPublishedDate");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}
				}
				featuredChannelList.add(videoDetails);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return featuredChannelList;
	}

	/* This method used to parse the Feature Updated field date */
	public static List<VideoDetails> parseFeaturedAPIToGetLastUpdDetails(
			String featureResponse) {
		List<VideoDetails> featuredChannelList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject
					.getJSONObject("contentPanelElements");
			JSONArray jsonArray = jsonObject1
					.getJSONArray("contentPanelElement");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("contentKey"))
					videoDetails.setId(json.getString("contentKey"));
				if (json.has("contentType"))
					videoDetails.setContentType(json.getString("contentType"));
				if (json.has("category")) {
					JSONObject catagory = json.getJSONObject("category");
					if (catagory.has("level")) {
						videoDetails.setLevel(catagory.getString("level"));
					}
					if (catagory.has("lastAssetPublishedDate")) {
						String lastUapdatedDate = catagory
								.getString("lastAssetPublishedDate");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}

						featuredChannelList.add(videoDetails);
					}
				}
				if (!videoDetails.getContentType().equalsIgnoreCase(
						"productgroup"))
					featuredChannelList.add(videoDetails);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return featuredChannelList;
	}

	private static String getUpdateedInterval(Date lastUpdate) {

		/*
		 * System.out.println(lastUpdate.getTime());
		 * 
		 * getTheAppropriateLastUpadtedTime(lastUpdate.getTime());
		 * 
		 * int sec =Integer.parseInt(lastUpdate.getTime());
		 * 
		 * Calendar calendar = Calendar.getInstance();
		 * calendar.setTime(lastUpdate); int year = calendar.get(Calendar.YEAR);
		 * int month = calendar.get(Calendar.MONTH); int
		 * week=calendar.get(Calendar.WEEK_OF_MONTH); int yearDiff =
		 * Calendar.getInstance().get(Calendar.YEAR)-year; int monthDiff =
		 * Calendar.getInstance().get(Calendar.MONTH)-month; int
		 * weekDiff=Calendar.getInstance().get(Calendar.WEEK_OF_MONTH)-week;
		 * System.out.println("year  "+year);
		 * System.out.println("month  "+month);
		 * System.out.println("Week  "+week);
		 * System.out.println("yearDiff  "+yearDiff);
		 * System.out.println("monthDiff  "+monthDiff);
		 * System.out.println("Week Diff  "+weekDiff); int
		 * totalmonths=(yearDiff*12)+monthDiff;
		 * System.out.println("totalmonths "+totalmonths);
		 */
		Long todayinMilliSec = Calendar.getInstance().getTimeInMillis();
		Long updatedTimeinSec = (todayinMilliSec - lastUpdate.getTime()) / 1000;
		Integer updatedTimeinHours = Integer.valueOf(String
				.valueOf(updatedTimeinSec / (3600 * 24 * 60)));
		Integer updatedTimeinDays = Integer.valueOf(String
				.valueOf(updatedTimeinSec / (3600 * 24)));
		Integer updatedTimeinWeek = updatedTimeinDays / 7;
		Integer updatedTimeinMonths = updatedTimeinWeek / 4;

		if (updatedTimeinMonths >= 1000) {
			return "Updated N/A";
		} else if (updatedTimeinMonths >= 1) {
			if (updatedTimeinMonths == 1) {
				return "Updated Last month";
			}
			return "Updated " + updatedTimeinMonths + " months ago";
		} else if (updatedTimeinWeek >= 1) {
			if (updatedTimeinWeek == 1) {
				return "Updated Last week";
			}
			return "Updated " + updatedTimeinWeek + " weeks ago";
		} else if (updatedTimeinDays > 1) {
			return "Updated " + updatedTimeinDays + " days ago";
		} else if (updatedTimeinHours < 24) {
			return "Updated Yesterday";
		}

		return null;
	}

	/*
	 * This method used to parse the Home Feature Category Channels, Shows under
	 * channel and Videos
	 */
	public static List<VideoDetails> parseHomePopularChannelsResponse(
			String featureResponse) {
		List<VideoDetails> featuredChannelList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject
					.getJSONObject("contentPanelElements");
			JSONArray jsonArray = jsonObject1
					.getJSONArray("contentPanelElement");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("contentKey"))
					videoDetails.setId(json.getString("contentKey"));
				if (json.has("contentType"))
					videoDetails.setContentType(json.getString("contentType"));
				if (json.has("category")) {
					JSONObject catagory = json.getJSONObject("category");
					if (catagory.has("level"))
						videoDetails.setLevel(catagory.getString("level"));
				}

				if (!videoDetails.getContentType().equalsIgnoreCase(
						"productgroup"))
					featuredChannelList.add(videoDetails);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return featuredChannelList;
	}

	/*
	 * This method used to parse the Home Popular Shows Category Shows and
	 * Videos
	 */
	public static List<VideoDetails> parseHomePopularShowResponse(
			String featureResponse) {
		List<VideoDetails> popularShowsList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject
					.getJSONObject("contentPanelElements");
			JSONArray jsonArray = jsonObject1
					.getJSONArray("contentPanelElement");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("contentKey"))
					videoDetails.setId(json.getString("contentKey"));
				if (json.has("contentType"))
					videoDetails.setContentType(json.getString("contentType"));
				if (json.has("category")) {
					JSONObject catagory = json.getJSONObject("category");
					if (catagory.has("level"))
						videoDetails.setLevel(catagory.getString("level"));
					if (catagory.has("lastAssetPublishedDate")) {
						String lastUapdatedDate = catagory
								.getString("lastAssetPublishedDate");
						Date lastUpdate = getFormatedDate(lastUapdatedDate);
						videoDetails
								.setLastPublished(getUpdateedInterval(lastUpdate));

						popularShowsList.add(videoDetails);
					}
				}

				if (!videoDetails.getContentType().equalsIgnoreCase(
						"productgroup"))
					popularShowsList.add(videoDetails);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return popularShowsList;
	}

	/* This Method is to parse Home Video from selected Shows */
	public static List<VideoDetails> parsePopularShowsVideosResponse(
			String featureResponse) {
		List<VideoDetails> popularShowVideoList = new ArrayList<VideoDetails>();
		int noOfAssets = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("assets");

			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfAssets = Integer.parseInt(noOfHits);
			}

			if (jsonObject1 != null && jsonObject1.has("asset")) {
				if (noOfAssets == 1) {
					JSONObject assetJson = jsonObject1.getJSONObject("asset");
					// jsonArray = new JSONArray(categoryJson);
					jsonArray = new JSONArray();
					jsonArray.put(assetJson);
				} else
					jsonArray = jsonObject1.getJSONArray("asset");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));

					popularShowVideoList.add(videoDetails);
				}
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return popularShowVideoList;
	}

	/*
	 * This method is to parse the Home Featured Category Show Count under
	 * Channel
	 */
	public static String parseHomeFeaturedShowCountResponse(
			String featuredResponse) {
		int noOfShows = 0;
		String showCount = null;
		boolean showInstance;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featuredResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");

			if (jsonObject1.has("category") && jsonObject1 != null) {
				showInstance = (jsonObject1.get("category") instanceof JSONObject);

				if (showInstance == true) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("category");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("category");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));
					if (json.has("level"))
						videoDetails.setLevel(json.getString("level"));
					if (json.has("lastAssetPublishedDate")) {
						String lastUapdatedDate = json
								.getString("lastAssetPublishedDate");
						Date lastUpdate = getFormatedDate(lastUapdatedDate);
						videoDetails
								.setLastPublished(getUpdateedInterval(lastUpdate));
					}
					noOfShows = i + 1;
					showCount = Integer.toString(noOfShows);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return showCount;
	}

	/*
	 * Module: SUBSCRIPTIONS PAGE Descripiton: This method is to parse
	 * Subscriptions Channels Response
	 */

	public static List<VideoDetails> parseSubscriptionChannelsResponse(
			String featureResponse) {
		List<VideoDetails> subscriptionChannelList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject
					.getJSONObject("contentPanelElements");
			JSONArray jsonArray = jsonObject1
					.getJSONArray("contentPanelElement");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("contentKey"))
					videoDetails.setId(json.getString("contentKey"));
				if (json.has("contentType"))
					videoDetails.setContentType(json.getString("contentType"));
				if (json.has("category")) {
					JSONObject catagory = json.getJSONObject("category");
					if (catagory.has("level"))
						videoDetails.setLevel(catagory.getString("level"));
				}

				if (!videoDetails.getContentType().equalsIgnoreCase(
						"productgroup"))
					subscriptionChannelList.add(videoDetails);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return subscriptionChannelList;
	}

	/* This Method is to parse Subscriptions Shows Response */
	public static List<VideoDetails> parseSubscriptionsShowsResponse(
			String subscriptionsResponse) {
		List<VideoDetails> subscribedChannelShowList = new ArrayList<VideoDetails>();

		boolean showInstance;
		JSONArray jsonArray = null;

		try {
			JSONObject jsonObject = new JSONObject(subscriptionsResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");

			if (jsonObject1 != null && jsonObject1.has("category")) {
				showInstance = (jsonObject1.get("category") instanceof JSONObject);

				if (showInstance == true) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("category");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("category");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));
					if (json.has("level"))
						videoDetails.setLevel(json.getString("level"));
					if (json.has("lastAssetPublishedDate")) {
						String lastUapdatedDate = json
								.getString("lastAssetPublishedDate");
						Date lastUpdate = getFormatedDate(lastUapdatedDate);
						videoDetails
								.setLastPublished(getUpdateedInterval(lastUpdate));
					}
					subscribedChannelShowList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return subscribedChannelShowList;
	}

	/* This Method is to parse Subscriptions Video Response */
	public static List<VideoDetails> parseSubscriptionsVideosResponse(
			String featureResponse) {
		List<VideoDetails> subscriptionsVideoList = new ArrayList<VideoDetails>();
		int noOfVideos = 0;
		JSONArray jsonArray = null;

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("assets");

			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfVideos = Integer.parseInt(noOfHits);
			}

			if (jsonObject1.has("asset") && jsonObject1 != null) {
				if (noOfVideos == 1) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("asset");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("asset");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));

					subscriptionsVideoList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return subscriptionsVideoList;
	}

	/* This Method is to parse Featured Channel Response from selected Bundle */
	public static List<VideoDetails> parseFeaturedBundleResponse(
			String featureResponse) {
		List<VideoDetails> featuredShowList = new ArrayList<VideoDetails>();
		int noOfShows = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");
			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfShows = Integer.parseInt(noOfHits);
			}
			if (noOfShows == 1) {
				JSONObject categoryJson = jsonObject1.getJSONObject("category");
				jsonArray = new JSONArray(categoryJson);
			} else
				jsonArray = jsonObject1.getJSONArray("category");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("@id"))
					videoDetails.setId(json.getString("@id"));
				if (json.has("level"))
					videoDetails.setLevel(json.getString("level"));
				featuredShowList.add(videoDetails);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return featuredShowList;
	}

	/* This method parse the Store Feature Category Show Count under Channel */
	public static List<String> parseFeaturedBundleShowAndVideoCountResponse(
			String featureResponse) {
		List<String> resultList = new ArrayList<String>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			if (jsonObject.has("number_of_shows"))
				resultList.add(jsonObject.getString("number_of_shows"));
			if (jsonObject.has("number_of_videos"))
				resultList.add(jsonObject.getString("number_of_videos"));
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return resultList;
	}

	/*
	 * This method parse the Store Feature Category Channels, Shows under
	 * channel and Videos user Channel
	 */
	public static List<VideoDetails> parseSubscriptionResponse(
			String subscriptionsResponse) {
		List<VideoDetails> subscribedChannelsList = new ArrayList<VideoDetails>();
		int noOfShows = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(subscriptionsResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");
			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfShows = Integer.parseInt(noOfHits);
			}
			if (noOfShows == 1) {
				JSONObject categoryJson = jsonObject1.getJSONObject("category");
				// jsonArray = new JSONArray(categoryJson);
				jsonArray = new JSONArray();
				jsonArray.put(categoryJson);
			} else
				jsonArray = jsonObject1.getJSONArray("category");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("@id"))
					videoDetails.setId(json.getString("@id"));
				if (json.has("level"))
					videoDetails.setLevel(json.getString("level"));
				if (json.has("lastAssetPublishedDate")) {
					String lastUapdatedDate = json
							.getString("lastAssetPublishedDate");
					Date lastUpdate = getFormatedDate(lastUapdatedDate);
					videoDetails
							.setLastPublished(getUpdateedInterval(lastUpdate));
				}
				subscribedChannelsList.add(videoDetails);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return subscribedChannelsList;
	}

	/* This method parse the Genres response */
	public static List<VideoDetails> parseGenresResponse(String genresResponse) {
		List<VideoDetails> genresCategoryList = new ArrayList<VideoDetails>();
		int totalItems = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(genresResponse);
			if (jsonObject.has("totalItems")) {
				String noOfItems = jsonObject.getString("totalItems");
				if (noOfItems != null && noOfItems.length() > 0)
					totalItems = Integer.parseInt(noOfItems);
			}
			if (totalItems == 1) {
				JSONObject categoryJson = jsonObject.getJSONObject("items");
				jsonArray = new JSONArray();
				jsonArray.put(categoryJson);
			} else
				jsonArray = jsonObject.getJSONArray("items");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("totalChannels"))
					videoDetails.setTotalChannels(json.getInt("totalChannels"));
				if (json.has("totalShows"))
					videoDetails.setTotalShows(json.getInt("totalShows"));
				if (json.has("totalVideos"))
					videoDetails.setTotalVideos(json.getInt("totalVideos"));
				if (json.has("totalItems"))
					videoDetails.setTotalItems(json.getInt("totalItems"));
				if (json.has("type"))
					videoDetails.setVideoType(json.getString("type"));
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title"));
				if (json.has("id"))
					videoDetails.setId(json.getString("id"));

				if (json.has("links")) {
					videoDetails.setLinks(json.getString("links"));
					// String lastUapdatedDate = json.getString("links");
					// Date lastUpdate = getFormatedDate(lastUapdatedDate);
					// videoDetails.setLastPublished(getUpdateedInterval(lastUpdate));
				}
				genresCategoryList.add(videoDetails);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return genresCategoryList;
	}

	private static Date getDateFromString(String date) {
		Date newDate = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss'Z'");
			newDate = formatter.parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return newDate;
	}

	private static Date getFormatedDate(String date) {
		Date updatedDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (!date.equalsIgnoreCase(""))
				updatedDate = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updatedDate;
	}

	/* This method is to parse the Genres Shows Count under particular category */
	public static int parseGenresItemsCountResponse(String featuredResponse) {
		int totalItems = 0;
		try {
			JSONObject jsonObject = new JSONObject(featuredResponse);
			if (jsonObject.has("totalItems")) {
				String noOfItems = jsonObject.getString("totalItems");
				if (noOfItems != null && noOfItems.length() > 0)
					totalItems = Integer.parseInt(noOfItems);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return totalItems;
	}

	/* This method is to parse the Genres Shows under particular category */
	public static List<VideoDetails> parseGenresShowsResponse(
			String genreShowsResponse) {
		List<VideoDetails> genresCategoryShowList = new ArrayList<VideoDetails>();
		int totalItems = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(genreShowsResponse);
			if (jsonObject.has("totalItems")) {
				totalItems = jsonObject.getInt("totalItems");
			}
			/*
			 * if(totalItems==1) { JSONObject categoryJson =
			 * jsonObject.getJSONObject("items"); jsonArray = new JSONArray();
			 * jsonArray.put(categoryJson); } else
			 */
			jsonArray = jsonObject.getJSONArray("items");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();
				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("type"))
					videoDetails.setVideoType(json.getString("type"));
				if (json.has("title"))
					videoDetails.setTitle(json.getString("title").trim());
				if (json.has("id"))
					videoDetails.setId(json.getString("id"));

				if (json.has("numOfVideos"))
					videoDetails.setNumOfVideos(json.getString("numOfVideos"));

				if (json.has("lastUpdateTimestamp")) {
					String lastUapdatedDate = json
							.getString("lastUpdateTimestamp");
					if (!lastUapdatedDate.equalsIgnoreCase("")) {
						Date lastUpdate = getFormatedDate(lastUapdatedDate);
						videoDetails
								.setLastPublished(getUpdateedInterval(lastUpdate));
					}
				}

				if (json.has("publisher")) {
					JSONObject publisherData = json.getJSONObject("publisher");
					if (publisherData.has("id"))
						videoDetails.setPublisherId(publisherData
								.getString("id"));
					if (publisherData.has("name"))
						videoDetails.setPublisherName(publisherData
								.getString("name"));
					if (publisherData.has("description"))
						videoDetails.setPublisherDescription(publisherData
								.getString("description"));
				}

				genresCategoryShowList.add(videoDetails);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return genresCategoryShowList;
	}

	/* This method is to parse the Genres Channel under particular category */
	public static List<VideoDetails> parseGenresChannelsResponse(
			String genreShowsResponse) {
		List<VideoDetails> genresCategoryChannelList = new ArrayList<VideoDetails>();
		int totalItems = 0;
		boolean showInstance;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(genreShowsResponse);
			if (jsonObject.has("totalItems")) {
				totalItems = jsonObject.getInt("totalItems");
			}

			if (jsonObject.has("items") && jsonObject != null) {
				showInstance = (jsonObject.get("items") instanceof JSONObject);

				if (showInstance == true) {
					JSONObject categoryJson = jsonObject.getJSONObject("items");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject.getJSONArray("items");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("type"))
						videoDetails.setVideoType(json.getString("type"));
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title").trim());
					if (json.has("id"))
						videoDetails.setId(json.getString("id"));
					if (json.has("numOfShows"))
						videoDetails.setNoOfShows(json.getInt("numOfShows"));
					if (json.has("numOfVideos"))
						videoDetails.setNumOfVideos(json
								.getString("numOfVideos"));
					if (json.has("links"))
						videoDetails.setLinks(json.getString("links"));

					if (json.has("lastUpdateTimestamp")) {
						String lastUapdatedDate = json
								.getString("lastUpdateTimestamp");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}

					if (json.has("publisher")) {
						JSONObject publisherData = json
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}

					genresCategoryChannelList.add(videoDetails);
				}
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return genresCategoryChannelList;
	}

	/* This Method is to parse UpNext Category shows Response */
	public static List<VideoDetails> parseUpNextCategoryResponse(
			String upNextResponse) {
		List<VideoDetails> upNextShowList = new ArrayList<VideoDetails>();
		int noOfShows = 0;
		String subShowDetails = null;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(upNextResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");

			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfShows = Integer.parseInt(noOfHits);
			}
			if (jsonObject1.has("category") && jsonObject1 != null) {
				if (noOfShows == 1) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("category");
					subShowDetails = categoryJson.getString("level");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else {
					jsonArray = jsonObject1.getJSONArray("category");
					// subShowDetails =categoryJson.getString("level");
				}

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));
					if (json.has("level"))
						videoDetails.setLevel(json.getString("level"));
					if (json.has("lastAssetPublishedDate")
							&& json.getString("level").equals("SUB_SHOW")) {
						String lastUapdatedDate = json
								.getString("lastAssetPublishedDate");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							System.out.println("lastUapdatedDate>>>>"
									+ lastUapdatedDate);
							Date lastUpdate = getDateFromString(lastUapdatedDate);
							videoDetails.setLastUpdatedTime(lastUpdate);
						}
						upNextShowList.add(videoDetails);
					}

				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return upNextShowList;
	}

	/* This Method is to parse Home UpNext Category Video from selected Shows */
	public static List<VideoDetails> parseUpNextShowsResponse(
			String upNextResponse) {
		List<VideoDetails> upNextShowsVideoList = new ArrayList<VideoDetails>();
		int noOfAssets = 0;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(upNextResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("assets");

			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfAssets = Integer.parseInt(noOfHits);
			}

			if (jsonObject1 != null && jsonObject1.has("asset")) {
				if (noOfAssets == 1) {
					JSONObject assetJson = jsonObject1.getJSONObject("asset");
					// jsonArray = new JSONArray(categoryJson);
					jsonArray = new JSONArray();
					jsonArray.put(assetJson);
				} else
					jsonArray = jsonObject1.getJSONArray("asset");

				for (int i = 0; i < 1; i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));

					upNextShowsVideoList.add(videoDetails);
				}
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return upNextShowsVideoList;
	}

	// --------------------------------------------------------------------------New
	// API Parser-------------------------------------------------------//
	/*
	 * This method used to parse the Home Feature Category Channels, Shows under
	 * channel and Videos
	 */
	public static List<VideoDetails> parsenFeaturedResponse(
			String featureResponse) {
		List<VideoDetails> featuredList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			// JSONObject jsonObject1 = jsonObject.getJSONObject("curatedList");
			VideoDetails videoDetails1 = new VideoDetails();
			if (jsonObject.has("totalItems"))
				videoDetails1.setTotalItems(jsonObject.getInt("totalItems"));
			if (jsonObject.has("totalBundles"))
				videoDetails1
						.setTotalBundles(jsonObject.getInt("totalBundles"));
			if (jsonObject.has("totalChannels"))
				videoDetails1.setTotalChannels(jsonObject
						.getInt("totalChannels"));
			if (jsonObject.has("totalShows"))
				videoDetails1.setTotalShows(jsonObject.getInt("totalShows"));
			if (jsonObject.has("totalVideos"))
				videoDetails1.setTotalVideos(jsonObject.getInt("totalVideos"));
			if (jsonObject.has("curatedListType"))
				videoDetails1.setCuratedListType(jsonObject
						.getString("curatedListType"));

			JSONArray jsonArray = jsonObject.getJSONArray("curatedItem");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();

				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("type"))
					videoDetails.setCuratedItemType(json.getString("type"));
				if (json.has("title"))
					videoDetails.setCuratedItemTitle(json.getString("title"));
				if (json.has("links")) {
					JSONObject catagory = json.getJSONObject("links");
					String value = (String) catagory.get("imageUri");
					videoDetails.setCuratedItemLinksValue(value);
				}

				if (json.has("bundle")) {
					JSONObject catagory = json.getJSONObject("bundle");
					if (catagory.has("type"))
						videoDetails.setType(catagory.getString("type"));
					if (catagory.has("id"))
						videoDetails.setId(catagory.getString("id"));
					if (catagory.has("title"))
						videoDetails.setTitle(catagory.getString("title"));
					if (catagory.has("description"))
						videoDetails.setDescription(catagory
								.getString("description"));
					if (catagory.has("monetizationType"))
						videoDetails.setMonetizationType(catagory
								.getString("monetizationType"));
					if (catagory.has("price"))
						videoDetails.setPrice(catagory.getInt("price"));

					if (catagory.has("publisher")) {
						JSONObject publisherData = catagory
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}
				}

				if (json.has("channel")) {
					JSONObject catagory = json.getJSONObject("channel");
					if (catagory.has("type"))
						videoDetails.setType(catagory.getString("type"));
					if (catagory.has("id"))
						videoDetails.setId(catagory.getString("id"));
					if (catagory.has("title"))
						videoDetails.setTitle(catagory.getString("title")
								.trim());
					if (catagory.has("description"))
						videoDetails.setDescription(catagory
								.getString("description"));
					if (catagory.has("numOfShows"))
						videoDetails.setNumOfShows(catagory
								.getString("numOfShows"));
					if (catagory.has("numOfVideos"))
						videoDetails.setNumOfVideos(catagory
								.getString("numOfVideos"));

					if (catagory.has("lastUpdateTimestamp")) {
						String lastUapdatedDate = catagory
								.getString("lastUpdateTimestamp");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}

					if (catagory.has("links")) {
						JSONObject publisherData = catagory
								.getJSONObject("links");
						String value = (String) publisherData.get("publisher");
						videoDetails.setPublisherURL(value);
					}

					if (catagory.has("publisher")) {
						JSONObject publisherData = catagory
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}
				}

				if (json.has("show")) {
					JSONObject catagory = json.getJSONObject("show");
					if (catagory.has("type"))
						videoDetails.setType(catagory.getString("type"));
					if (catagory.has("id"))
						videoDetails.setId(catagory.getString("id"));
					if (catagory.has("title"))
						videoDetails.setTitle(catagory.getString("title")
								.trim());
					if (catagory.has("channelTitle"))
						videoDetails.setChannelTitle(catagory
								.getString("channelTitle"));
					if (catagory.has("numOfVideos"))
						videoDetails.setNumOfVideos(catagory
								.getString("numOfVideos"));
					if (catagory.has("description"))
						videoDetails.setDescription(catagory
								.getString("description"));

					if (catagory.has("lastUpdateTimestamp")) {
						String lastUapdatedDate = catagory
								.getString("lastUpdateTimestamp");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
							System.out.println("lastUpdate" + lastUpdate);
						}
					}

					if (catagory.has("publisher")) {
						JSONObject publisherData = catagory
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}
				}

				if (json.has("video")) {
					JSONObject catagory = json.getJSONObject("video");
					if (catagory.has("type"))
						videoDetails.setType(catagory.getString("type"));
					if (catagory.has("id"))
						videoDetails.setId(catagory.getString("id"));
					if (catagory.has("title"))
						videoDetails.setTitle(catagory.getString("title")
								.trim());
					if (catagory.has("shortDescription"))
						videoDetails.setShortDescription(catagory
								.getString("shortDescription"));
					if (catagory.has("liveBroadcastTime"))
						videoDetails.setLiveBroadcastTime(catagory
								.getString("liveBroadcastTime"));
					if (catagory.has("showTitle"))
						videoDetails.setShowTitle(catagory
								.getString("showTitle"));
					if (catagory.has("channelTitle"))
						videoDetails.setChannelTitle(catagory
								.getString("channelTitle"));
					if (catagory.has("duration"))
						videoDetails.setDuration(catagory.getInt("duration"));
					if (catagory.has("voteCount"))
						videoDetails.setVoteCount(catagory
								.getString("voteCount"));

					if (catagory.has("publisher")) {
						JSONObject publisherData = catagory
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}

					if (json.has("liveBroadcastTime")) {
						String lastUapdatedDate = json
								.getString("liveBroadcastTime");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
							System.out.println("lastUpdate" + lastUpdate);
						}
					}
				}
				featuredList.add(videoDetails);
			}
			featuredList.add(videoDetails1);

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return featuredList;
	}

	/* This Method is to parse Featured shows Response from selected Channel */
	public static List<VideoDetails> parseShowsResponse(String featureResponse) {
		List<VideoDetails> featuredShowList = new ArrayList<VideoDetails>();
		boolean showInstance;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			// JSONObject jsonObject1 = jsonObject.getJSONObject("curatedList");
			VideoDetails videoDetails1 = new VideoDetails();
			if (jsonObject.has("totalItems"))
				videoDetails1.setTotalItems(jsonObject.getInt("totalItems"));

			if (jsonObject.has("items") && jsonObject != null) {
				showInstance = (jsonObject.get("items") instanceof JSONObject);

				if (showInstance == true) {
					JSONObject categoryJson = jsonObject.getJSONObject("items");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject.getJSONArray("items");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();

					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("type"))
						videoDetails.setType(json.getString("type"));
					if (json.has("id"))
						videoDetails.setId(json.getString("id"));
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("description"))
						videoDetails.setDescription(json
								.getString("description"));
					if (json.has("channelTitle"))
						videoDetails.setChannelTitle(json
								.getString("channelTitle"));
					if (json.has("numOfVideos"))
						videoDetails.setNumOfVideos(json
								.getString("numOfVideos"));

					if (json.has("lastUpdateTimestamp")) {
						String lastUapdatedDate = json
								.getString("lastUpdateTimestamp");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}

					if (json.has("publisher")) {
						JSONObject publisherData = json
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}

					featuredShowList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return featuredShowList;
	}

	/* This Method is to parse Featured shows Response from selected Channel */
	public static List<VideoDetails> parseChannelShowsVideosResponse(
			String featureResponse) {
		List<VideoDetails> featuredShowList = new ArrayList<VideoDetails>();
		boolean showInstance;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			VideoDetails videoDetails1 = new VideoDetails();
			if (jsonObject.has("totalItems"))
				videoDetails1.setTotalItems(jsonObject.getInt("totalItems"));

			if (jsonObject.has("items") && jsonObject != null) {
				showInstance = (jsonObject.get("items") instanceof JSONObject);

				if (showInstance == true) {
					JSONObject categoryJson = jsonObject.getJSONObject("items");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject.getJSONArray("items");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();

					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("type"))
						videoDetails.setType(json.getString("type"));
					if (json.has("id"))
						videoDetails.setId(json.getString("id"));
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title").trim());
					if (json.has("shortDescription"))
						videoDetails.setShortDescription(json
								.getString("shortDescription"));
					if (json.has("showTitle"))
						videoDetails.setShowTitle(json.getString("showTitle")
								.trim());
					if (json.has("channelTitle"))
						videoDetails.setChannelTitle(json.getString(
								"channelTitle").trim());
					if (json.has("duration"))
						videoDetails.setDuration(json.getInt("duration"));
					if (json.has("voteCount"))
						videoDetails.setVoteCount(json.getString("voteCount"));

					if (json.has("liveBroadcastTime")) {
						String lastUapdatedDate = json
								.getString("liveBroadcastTime");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}

					if (json.has("publisher")) {
						JSONObject publisherData = json
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}

					featuredShowList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return featuredShowList;
	}

	/* This method used to parse the Home Popular Category response */
	public static List<VideoDetails> parsePopularChannelsResponse(
			String popularResponse) {
		List<VideoDetails> popularList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(popularResponse);
			// JSONObject jsonObject1 = jsonObject.getJSONObject("curatedList");
			VideoDetails videoDetails1 = new VideoDetails();
			if (jsonObject.has("totalChannels"))
				videoDetails1.setTotalChannels(jsonObject
						.getInt("totalChannels"));
			if (jsonObject.has("curatedListType"))
				videoDetails1.setCuratedListType(jsonObject
						.getString("curatedListType"));

			JSONArray jsonArray = jsonObject.getJSONArray("curatedItem");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();

				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("type"))
					videoDetails.setCuratedItemType(json.getString("type"));
				if (json.has("title"))
					videoDetails.setCuratedItemTitle(json.getString("title")
							.trim());
				if (json.has("links")) {
					JSONObject catagory = json.getJSONObject("links");
					String value = (String) catagory.get("imageUri");
					videoDetails.setCuratedItemLinksValue(value);
				}

				if (json.has("channel")) {
					JSONObject catagory = json.getJSONObject("channel");
					if (catagory.has("type"))
						videoDetails.setType(catagory.getString("type"));
					if (catagory.has("id"))
						videoDetails.setId(catagory.getString("id"));
					if (catagory.has("title"))
						videoDetails.setTitle(catagory.getString("title")
								.trim());
					if (catagory.has("description"))
						videoDetails.setDescription(catagory
								.getString("description"));
					if (catagory.has("numOfShows"))
						videoDetails.setNumOfShows(catagory
								.getString("numOfShows"));
					if (catagory.has("numOfVideos"))
						videoDetails.setNumOfVideos(catagory
								.getString("numOfVideos"));

					if (catagory.has("lastUpdateTimestamp")) {
						String lastUapdatedDate = catagory
								.getString("lastUpdateTimestamp");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}

					if (catagory.has("links")) {
						JSONObject publisherData = catagory
								.getJSONObject("links");
						String value = (String) publisherData.get("publisher");
						videoDetails.setPublisherURL(value);
					}

					if (catagory.has("publisher")) {
						JSONObject publisherData = catagory
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}
				}
				popularList.add(videoDetails);
			}
			popularList.add(videoDetails1);

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return popularList;
	}

	/* This method used to parse the Home Popular Shows Category response */
	public static List<VideoDetails> parsePopularShowsResponse(
			String popularResponse) {
		List<VideoDetails> popularList = new ArrayList<VideoDetails>();

		try {
			JSONObject jsonObject = new JSONObject(popularResponse);
			// JSONObject jsonObject1 = jsonObject.getJSONObject("curatedList");
			VideoDetails videoDetails1 = new VideoDetails();
			if (jsonObject.has("totalShows"))
				videoDetails1.setTotalShows(jsonObject.getInt("totalShows"));
			if (jsonObject.has("curatedListType"))
				videoDetails1.setCuratedListType(jsonObject
						.getString("curatedListType"));

			JSONArray jsonArray = jsonObject.getJSONArray("curatedItem");

			for (int i = 0; i < jsonArray.length(); i++) {
				VideoDetails videoDetails = new VideoDetails();

				JSONObject json = (JSONObject) jsonArray.get(i);
				if (json.has("type"))
					videoDetails.setCuratedItemType(json.getString("type"));
				if (json.has("title"))
					videoDetails.setCuratedItemTitle(json.getString("title")
							.trim());
				if (json.has("links")) {
					JSONObject catagory = json.getJSONObject("links");
					String value = (String) catagory.get("imageUri");
					videoDetails.setCuratedItemLinksValue(value);
				}

				if (json.has("show")) {
					JSONObject catagory = json.getJSONObject("show");
					if (catagory.has("type"))
						videoDetails.setType(catagory.getString("type"));
					if (catagory.has("id"))
						videoDetails.setId(catagory.getString("id"));
					if (catagory.has("title"))
						videoDetails.setTitle(catagory.getString("title")
								.trim());
					if (catagory.has("description"))
						videoDetails.setDescription(catagory
								.getString("description"));
					if (catagory.has("channelTitle"))
						videoDetails.setChannelTitle(catagory.getString(
								"channelTitle").trim());
					if (catagory.has("numOfVideos"))
						videoDetails.setNumOfVideos(catagory
								.getString("numOfVideos"));

					if (catagory.has("lastUpdateTimestamp")) {
						String lastUapdatedDate = catagory
								.getString("lastUpdateTimestamp");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							Date lastUpdate = getFormatedDate(lastUapdatedDate);
							videoDetails
									.setLastPublished(getUpdateedInterval(lastUpdate));
						}
					}

					if (catagory.has("publisher")) {
						JSONObject publisherData = catagory
								.getJSONObject("publisher");
						if (publisherData.has("id"))
							videoDetails.setPublisherId(publisherData
									.getString("id"));
						if (publisherData.has("name"))
							videoDetails.setPublisherName(publisherData
									.getString("name"));
						if (publisherData.has("description"))
							videoDetails.setPublisherDescription(publisherData
									.getString("description"));
					}
				}
				popularList.add(videoDetails);
			}
			// popularList.add(videoDetails1);
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return popularList;
	}

	/*
	 * This method is to parse the Home Featured Category Show Count under
	 * Channel
	 */
	public static String parseFeaturedChannelShowCountResponse(
			String featuredResponse) {
		String numOfShows = null;
		try {
			JSONObject jsonObject = new JSONObject(featuredResponse);
			if (jsonObject.has("numOfShows"))
				numOfShows = jsonObject.getString("numOfShows");
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return numOfShows;
	}

	/*
	 * This method is to parse the Home Featured Category Video Count under
	 * Channel shows
	 */
	public static String parseFeaturedChannelVideoCountResponse(
			String featuredResponse) {
		String numOfVideos = null;
		try {
			JSONObject jsonObject = new JSONObject(featuredResponse);
			if (jsonObject.has("numOfVideos"))
				numOfVideos = jsonObject.getString("numOfVideos");
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return numOfVideos;
	}

	/*
	 * This method is to parse the Home Featured Category Video
	 * lastUpdateTimestamp under Channel shows
	 */
	public static String parseLastUpdatedTimeStampResponse(
			String featuredResponse) {
		String lastupdTimeStamp = null;
		try {
			JSONObject jsonObject = new JSONObject(featuredResponse);
			if (jsonObject.has("lastUpdateTimestamp")) {
				String lastUapdatedDate = jsonObject
						.getString("lastUpdateTimestamp");
				if (!lastUapdatedDate.equalsIgnoreCase("")) {
					Date lastUpdate = getFormatedDate(lastUapdatedDate);
					lastupdTimeStamp = (getUpdateedInterval(lastUpdate));
				}
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return lastupdTimeStamp;
	}

	/* This Method is to parse Channel Search API Response */
	public static List<VideoDetails> parseChannelSearchResponse(
			String searchChannelAPI) {
		System.out.println("In Parsing.... !!!");
		List<VideoDetails> searchResultChannelList = new ArrayList<VideoDetails>();
		int noOfShows = 0;
		String subShowDetails = null;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(searchChannelAPI);
			JSONObject jsonObject1 = jsonObject.getJSONObject("categories");

			if (jsonObject1.has("numberOfHits")) {
				String noOfHits = jsonObject1.getString("numberOfHits");
				if (noOfHits != null && noOfHits.length() > 0)
					noOfShows = Integer.parseInt(noOfHits);
			}
			if (jsonObject1.has("category") && jsonObject1 != null) {
				if (noOfShows == 1) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("category");
					subShowDetails = categoryJson.getString("level");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else {
					jsonArray = jsonObject1.getJSONArray("category");
					// subShowDetails =categoryJson.getString("level");
				}

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));
					if (json.has("level"))
						videoDetails.setLevel(json.getString("level"));
					if (json.has("lastAssetPublishedDate")
							&& json.getString("level").equals("SUB_SHOW")) {
						String lastUapdatedDate = json
								.getString("lastAssetPublishedDate");
						if (!lastUapdatedDate.equalsIgnoreCase("")) {
							System.out.println("lastUapdatedDate>>>>"
									+ lastUapdatedDate);
							Date lastUpdate = getDateFromString(lastUapdatedDate);
							videoDetails.setLastUpdatedTime(lastUpdate);
						}
						searchResultChannelList.add(videoDetails);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return searchResultChannelList;
	}

	/* This Method is to parse episode Search response */
	public static List<VideoDetails> parseEpisodeSearchResponse(
			String featureResponse) {
		List<VideoDetails> searchVideoList = new ArrayList<VideoDetails>();
		boolean episodeInstance;
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(featureResponse);
			JSONObject jsonObject1 = jsonObject.getJSONObject("assets");

			if (jsonObject1.has("asset") && jsonObject1 != null) {
				episodeInstance = (jsonObject1.get("asset") instanceof JSONObject);

				if (episodeInstance == true) {
					JSONObject categoryJson = jsonObject1
							.getJSONObject("asset");
					jsonArray = new JSONArray();
					jsonArray.put(categoryJson);
				} else
					jsonArray = jsonObject1.getJSONArray("asset");

				for (int i = 0; i < jsonArray.length(); i++) {
					VideoDetails videoDetails = new VideoDetails();
					JSONObject json = (JSONObject) jsonArray.get(i);
					if (json.has("title"))
						videoDetails.setTitle(json.getString("title"));
					if (json.has("@id"))
						videoDetails.setId(json.getString("@id"));

					searchVideoList.add(videoDetails);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return searchVideoList;
	}

	/* This Method is to parse publisher details API Response */
	public static List<VideoDetails> parsePublisherDetailsResponse(
			String publisherDetailsAPI) {
		List<VideoDetails> publisherDetails = new ArrayList<VideoDetails>();
		try {
			JSONObject jsonObject = new JSONObject(publisherDetailsAPI);

			VideoDetails videoDetails = new VideoDetails();
			if (jsonObject.has("id"))
				videoDetails.setPublisherId(jsonObject.getString("id"));
			if (jsonObject.has("name"))
				videoDetails.setPublisherName(jsonObject.getString("name"));
			if (jsonObject.has("numOfChannels"))
				videoDetails.setNumOfChannels(jsonObject
						.getString("numOfChannels"));
			if (jsonObject.has("numOfShows"))
				videoDetails.setNumOfShows(jsonObject.getString("numOfShows"));
			if (jsonObject.has("numOfVideos"))
				videoDetails
						.setNumOfVideos(jsonObject.getString("numOfVideos"));
			if (jsonObject.has("description"))
				videoDetails.setPublisherDescription(jsonObject
						.getString("description"));

			if (jsonObject.has("lastUpdateTimestamp")) {
				String lastUapdatedDate = jsonObject
						.getString("lastUpdateTimestamp");
				if (!lastUapdatedDate.equalsIgnoreCase("")) {
					Date lastUpdate = getFormatedDate(lastUapdatedDate);
					videoDetails
							.setLastPublished(getUpdateedInterval(lastUpdate));
				}
			}

			publisherDetails.add(videoDetails);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return publisherDetails;
	}

	public static void main(String[] args) {
		/*
		 * String test=
		 * "{\r\n    \"id\": \"276\",\r\n    \"name\": \"Web Mezz\",\r\n    \"description\": \"Publisher of Best of TV on the Web\",\r\n    \"numOfChannels\": \"13\",\r\n    \"numOfShows\": \"12\",\r\n    \"numOfVideos\": \"58\",\r\n    \"lastUpdateTimestamp\": \"2013-09-04T17:40:10Z\",\r\n    \"links\": {\r\n        \"imageUri\": \"http://image.stage.xidio.com/api/v2/img/52373520e4b0ece75f3c3c7e+1379349792000\",\r\n        \"channelPageTemplate\": \"/api/{platform}/publishers/{publisherId}/channels?pageNum={pageNum}&pageSize={pageSize}\",\r\n        \"self\": \"/api/web/publishers/276\",\r\n        \"channelPage\": \"/api/web/publishers/276/channels?start=0&size=1\",\r\n        \"genres_channels\": \"/api/web/publishers/276/genres/{genreId}/channels\"\r\n    }\r\n}"
		 * ;
		 * 
		 * try { JSONObject obj=new JSONObject(test);
		 * 
		 * System.out.println(obj.get("id")); } catch (JSONException e) { //
		 * TODO Auto-generated catch block
		 * e.printStackTrace();"2013-09-03T18:56:04" }
		 */
		Calendar calender = Calendar.getInstance();
		calender.set(2013, 5, 3, 18, 56);
		System.out.println(getUpdateedInterval(calender.getTime()));
	}
}
