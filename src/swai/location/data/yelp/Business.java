package swai.location.data.yelp;

public class Business {

	private String id;
	private String name;
	private boolean is_claimed;
	private boolean is_closed;
	private String mobile_url;
	private String url;
	private String image_url;
	private String phone;
	private String display_phone;
	private double distance;
	private int review_count;
	private double rating;
	private String rating_img_url;
	private String rating_img_url_small;
	private String rating_img_url_large;
	private String snippet_text;
	private String snippet_image_url;
	private Location location;

	public Business() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIs_claimed() {
		return is_claimed;
	}

	public void setIs_claimed(boolean is_claimed) {
		this.is_claimed = is_claimed;
	}

	public boolean isIs_closed() {
		return is_closed;
	}

	public void setIs_closed(boolean is_closed) {
		this.is_closed = is_closed;
	}

	public String getMobile_url() {
		return mobile_url;
	}

	public void setMobile_url(String mobile_url) {
		this.mobile_url = mobile_url;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDisplay_phone() {
		return display_phone;
	}

	public void setDisplay_phone(String display_phone) {
		this.display_phone = display_phone;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getReview_count() {
		return review_count;
	}

	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getRating_img_url() {
		return rating_img_url;
	}

	public void setRating_img_url(String rating_img_url) {
		this.rating_img_url = rating_img_url;
	}

	public String getRating_img_url_small() {
		return rating_img_url_small;
	}

	public void setRating_img_url_small(String rating_img_url_small) {
		this.rating_img_url_small = rating_img_url_small;
	}

	public String getRating_img_url_large() {
		return rating_img_url_large;
	}

	public void setRating_img_url_large(String rating_img_url_large) {
		this.rating_img_url_large = rating_img_url_large;
	}

	public String getSnippet_text() {
		return snippet_text;
	}

	public void setSnippet_text(String snippet_text) {
		this.snippet_text = snippet_text;
	}

	public String getSnippet_image_url() {
		return snippet_image_url;
	}

	public void setSnippet_image_url(String snippet_image_url) {
		this.snippet_image_url = snippet_image_url;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
