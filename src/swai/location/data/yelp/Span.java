package swai.location.data.yelp;

public class Span {
	
	private double latitude_delta;
	private double longitude_delta;

	public Span() {
	}

	public double getLatitude_delta() {
		return latitude_delta;
	}

	public void setLatitude_delta(double latitude_delta) {
		this.latitude_delta = latitude_delta;
	}

	public double getLongitude_delta() {
		return longitude_delta;
	}

	public void setLongitude_delta(double longitude_delta) {
		this.longitude_delta = longitude_delta;
	}
}