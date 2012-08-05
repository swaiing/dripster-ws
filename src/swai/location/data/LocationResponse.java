package swai.location.data;

import java.util.List;

public class LocationResponse {

	private final Coordinate regionCenter;
	private final Coordinate regionSpanDelta;
	private List<? super Location> locations;

	public LocationResponse(List<? super Location> locations,
			Coordinate regionCenter, Coordinate regionSpanDelta) {
		this.locations = locations;
		this.regionCenter = regionCenter;
		this.regionSpanDelta = regionSpanDelta;
	}

	public Coordinate getRegionCenter() {
		return regionCenter;
	}

	public Coordinate getRegionSpanDelta() {
		return regionSpanDelta;
	}

	public List<? super Location> getLocations() {
		return locations;
	}

	public int getTotal() {
		if (locations != null)
			locations.size();
		return -1;
	}

}
