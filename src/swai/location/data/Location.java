package swai.location.data;

import java.util.List;

public class Location extends Coordinate {
	
	private final String id;
	private final String name;
	private final boolean claimed;
	private final boolean closed;
	private final String mobileUrl;
	private final String url;
	private final String imageUrl;
	private final String phone;
	private final String displayPhone;
	private final double distance;
	private final int reviewCount;
	private final double rating;
	private final String ratingImgUrl;
	private final String ratingImgUrlSmall;
	private final String ratingImgUrlLarge;
	private final String snippetText;
	private final String snippetImageUrl;
	private final List<String> address;
	private final List<String> displayAddress;
	private final String city;
	private final String stateCode;
	private final String postalCode;
	private final String crossStreets;
	private final List<String> neighborhoods;
	private final int geoAccuracy;
	
	
	/**
	 * @param latitude
	 * @param longitude
	 * @param id
	 * @param name
	 * @param claimed
	 * @param closed
	 * @param mobileUrl
	 * @param url
	 * @param imageUrl
	 * @param phone
	 * @param displayPhone
	 * @param distance
	 * @param reviewCount
	 * @param rating
	 * @param ratingImgUrl
	 * @param ratingImgUrlSmall
	 * @param ratingImgUrlLarge
	 * @param snippetText
	 * @param snippetImageUrl
	 * @param address
	 * @param displayAddress
	 * @param city
	 * @param stateCode
	 * @param postalCode
	 * @param crossStreets
	 * @param neighborhoods
	 * @param geoAccuracy
	 */
	public Location(double latitude, double longitude, String id, String name,
			boolean claimed, boolean closed, String mobileUrl, String url,
			String imageUrl, String phone, String displayPhone,
			double distance, int reviewCount, double rating,
			String ratingImgUrl, String ratingImgUrlSmall,
			String ratingImgUrlLarge, String snippetText,
			String snippetImageUrl, List<String> address,
			List<String> displayAddress, String city, String stateCode,
			String postalCode, String crossStreets, List<String> neighborhoods,
			int geoAccuracy) {
		super(latitude, longitude);
		this.id = id;
		this.name = name;
		this.claimed = claimed;
		this.closed = closed;
		this.mobileUrl = mobileUrl;
		this.url = url;
		this.imageUrl = imageUrl;
		this.phone = phone;
		this.displayPhone = displayPhone;
		this.distance = distance;
		this.reviewCount = reviewCount;
		this.rating = rating;
		this.ratingImgUrl = ratingImgUrl;
		this.ratingImgUrlSmall = ratingImgUrlSmall;
		this.ratingImgUrlLarge = ratingImgUrlLarge;
		this.snippetText = snippetText;
		this.snippetImageUrl = snippetImageUrl;
		this.address = address;
		this.displayAddress = displayAddress;
		this.city = city;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.crossStreets = crossStreets;
		this.neighborhoods = neighborhoods;
		this.geoAccuracy = geoAccuracy;
	}
	
	public String getName() {
		return name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getPhone() {
		return phone;
	}
	public String getDisplayPhone() {
		return displayPhone;
	}
	public double getRating() {
		return rating;
	}
	public String getSnippetText() {
		return snippetText;
	}
	public List<String> getAddress() {
		return address;
	}
	public List<String> getDisplayAddress() {
		return displayAddress;
	}
	public String getCity() {
		return city;
	}
	public String getStateCode() {
		return stateCode;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public List<String> getNeighborhoods() {
		return neighborhoods;
	}
	public String getRatingImgUrl() {
		return ratingImgUrl;
	}
	public String getRatingImgUrlSmall() {
		return ratingImgUrlSmall;
	}
	public String getRatingImgUrlLarge() {
		return ratingImgUrlLarge;
	}
	public String getSnippetImageUrl() {
		return snippetImageUrl;
	}
	public int getGeoAccuracy() {
		return geoAccuracy;
	}
	public String getId() {
		return id;
	}
	public boolean isClaimed() {
		return claimed;
	}
	public boolean isClosed() {
		return closed;
	}
	public String getMobile_url() {
		return mobileUrl;
	}
	public String getUrl() {
		return url;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public String getCrossStreets() {
		return crossStreets;
	}
	public String getMobileUrl() {
		return mobileUrl;
	}
	public double getDistance() {
		return distance;
	}
}
