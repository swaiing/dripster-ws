package swai.location.data.yelp;

import java.util.List;

/**
 * Object to hold Yelp data deserialized from JSON
 * Needed for GSon
 */
public class YelpResponse {

	private Region region;
	private int total;
	private List<Business> businesses;

	public YelpResponse() {
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Business> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}

}
