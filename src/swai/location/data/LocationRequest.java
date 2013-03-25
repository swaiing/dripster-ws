package swai.location.data;

public class LocationRequest {

	private String term;

	// yelp field
	private String categoryFilter;

	private double longitude;

	private double latitude;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getCategoryFilter() {
		return categoryFilter;
	}

	public void setCategoryFilter(String categoryFilter) {
		this.categoryFilter = categoryFilter;
	}

	@Override
	public String toString() {
		return "LocationRequest [term=" + term + ", categoryFilter="
				+ categoryFilter + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}

}
