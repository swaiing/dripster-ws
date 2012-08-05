package swai.location.service.api;

import swai.location.data.LocationRequest;
import swai.location.data.LocationResponse;

public interface LocationService {
	
	public LocationResponse list(LocationRequest req);

}
