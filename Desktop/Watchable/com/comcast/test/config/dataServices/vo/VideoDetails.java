package comcast.test.config.dataServices.vo;

import java.util.Date;

public class VideoDetails {

	private String contentType;
	private String level;
	private String showCount;
	private String noOfHits;
	private Date lastUpdatedTime;
	private String lastPublished;

	private String videoType;
	private String videoId;
	private String videoTitle;
	private String videoDesc;

	// Genre feature variables
	// New feature API Keys
	private int totalItems;
	private int totalBundles;
	private int totalChannels;
	private int totalShows;
	private int totalVideos;
	private String curatedListType;

	// Common for Channels/Shows/Videos
	private String curatedItemType;
	private String curatedItemTitle;
	private String curatedItemLinksValue;

	// Common for Channel/Show/Videos
	private String type;
	private String id;
	private String title;
	private String description;

	private String channelTitle;
	private String lastUpdateTimestamp;
	private String numOfVideos;

	// Bundle Details:
	private String monetizationType;
	private int price;

	// Channel Details:
	private String numOfShows;

	// Show Details:
	private String parentalGuidance;

	public String getEpisodeCount() {
		return this.episodeCount;
	}

	public void setEpisodeCount(String episodeCount) {
		this.episodeCount = episodeCount;
	}

	// Video Details:
	private String shortDescription;
	private String longDescription;
	private String liveBroadcastTime;
	private String showTitle;
	private String episode;
	private String episodeCount;
	private int duration;
	private String voteCount;

	// Publisher Details for Channels/Shows/Videos
	private String publisherId;
	private String publisherName;
	private String publisherDescription;
	private String numOfChannels;
	private String publisherURL;

	public String getPublisherURL() {
		return this.publisherURL;
	}

	public void setPublisherURL(String publisherURL) {
		this.publisherURL = publisherURL;
	}

	private String links;
	private int noOfShows;

	public int getTotalBundles() {
		return this.totalBundles;
	}

	public void setTotalBundles(int totalBundles) {
		this.totalBundles = totalBundles;
	}

	public String getCuratedListType() {
		return this.curatedListType;
	}

	public void setCuratedListType(String curatedListType) {
		this.curatedListType = curatedListType;
	}

	public String getCuratedItemType() {
		return this.curatedItemType;
	}

	public void setCuratedItemType(String curatedItemType) {
		this.curatedItemType = curatedItemType;
	}

	public String getCuratedItemTitle() {
		return this.curatedItemTitle;
	}

	public void setCuratedItemTitle(String curatedItemTitle) {
		this.curatedItemTitle = curatedItemTitle;
	}

	public String getCuratedItemLinksValue() {
		return this.curatedItemLinksValue;
	}

	public void setCuratedItemLinksValue(String curatedItemLinksValue) {
		this.curatedItemLinksValue = curatedItemLinksValue;
	}

	public int getNoOfShows() {
		return this.noOfShows;
	}

	public void setNoOfShows(int noOfShows) {
		this.noOfShows = noOfShows;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getShowCount() {
		return this.showCount;
	}

	public void setShowCount(String showCount) {
		this.showCount = showCount;
	}

	public String getNoOfHits() {
		return this.noOfHits;
	}

	public void setNoOfHits(String noOfHits) {
		this.noOfHits = noOfHits;
	}

	public Date getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getLastPublished() {
		return this.lastPublished;
	}

	public void setLastPublished(String lastPublished) {
		this.lastPublished = lastPublished;
	}

	// Genre Getter and Setter methods.
	public int getTotalChannels() {
		return this.totalChannels;
	}

	public void setTotalChannels(int totalChannels) {
		this.totalChannels = totalChannels;
	}

	public int getTotalShows() {
		return this.totalShows;
	}

	public void setTotalShows(int totalShows) {
		this.totalShows = totalShows;
	}

	public int getTotalVideos() {
		return this.totalVideos;
	}

	public void setTotalVideos(int totalVideos) {
		this.totalVideos = totalVideos;
	}

	public int getTotalItems() {
		return this.totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public String getLinks() {
		return this.links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	// ----New API
	public String getVideoType() {
		return this.videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String getVideoId() {
		return this.videoId;
	}

	public void setVideoID(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return this.videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoDesc() {
		return this.videoDesc;
	}

	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannelTitle() {
		return this.channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getLastUpdateTimestamp() {
		return this.lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(String lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	public String getNumOfVideos() {
		return this.numOfVideos;
	}

	public void setNumOfVideos(String numOfVideos) {
		this.numOfVideos = numOfVideos;
	}

	public String getNumOfShows() {
		return this.numOfShows;
	}

	public void setNumOfShows(String numOfShows) {
		this.numOfShows = numOfShows;
	}

	public String getParentalGuidance() {
		return this.parentalGuidance;
	}

	public void setParentalGuidance(String parentalGuidance) {
		this.parentalGuidance = parentalGuidance;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return this.longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getLiveBroadcastTime() {
		return this.liveBroadcastTime;
	}

	public void setLiveBroadcastTime(String liveBroadcastTime) {
		this.liveBroadcastTime = liveBroadcastTime;
	}

	public String getShowTitle() {
		return this.showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getEpisode() {
		return this.episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return this.publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherDescription() {
		return this.publisherDescription;
	}

	public void setPublisherDescription(String publisherDescription) {
		this.publisherDescription = publisherDescription;
	}

	public String getNumOfChannels() {
		return this.numOfChannels;
	}

	public void setNumOfChannels(String numOfChannels) {
		this.numOfChannels = numOfChannels;
	}

	public String getVoteCount() {
		return this.voteCount;
	}

	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}

	public String getMonetizationType() {
		return this.monetizationType;
	}

	public void setMonetizationType(String monetizationType) {
		this.monetizationType = monetizationType;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
