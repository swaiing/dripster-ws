package swai.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swai.location.data.LocationRequest;
import swai.location.data.LocationResponse;
import swai.location.service.api.LocationService;
import swai.location.service.api.LocationServiceFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author wais
 * 
 */
@SuppressWarnings("serial")
public class CoffeeServiceServlet extends HttpServlet {

	// logger
	private static final Logger log = Logger
			.getLogger(CoffeeServiceServlet.class.getName());

	// Coffee web service
	private static final LocationService cs = LocationServiceFactory
			.getInstance().getService(LocationServiceFactory.COFFEE_SERVICE);

	// GET request parameter keys
	private static final String TERM = "term";
	private static final String LATITUDE_LONGITUDE = "ll";
	private static final String EXCLUDE_CHAINS = "nochains";
	private static final String WIFI = "wifi";

	private static Gson gson;

	public CoffeeServiceServlet() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		// gson = new Gson();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// build request
		LocationRequest locReq = CoffeeServiceServlet.formatRequest(req);

		// execute
		LocationResponse res = cs.list(locReq);

		// write to output stream
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(res));
		out.flush();
	}

	/**
	 * Format GET request:
	 * http://localhost:8888/cs?term=coffee&ll=42.393865,-71.120693&nochains=y&wifi=y
	 * 
	 * @param httpReq
	 * @return
	 */
	private static final LocationRequest formatRequest(
			HttpServletRequest httpReq) {

		LocationRequest req = new LocationRequest();
		Map<String, String[]> params = httpReq.getParameterMap();
		for (String key : params.keySet()) {

			String[] values = params.get(key);
			if (values == null || values.length < 1)
				continue;

			String firstValue = values[0];
			if (TERM.equalsIgnoreCase(key)) {
				req.setTerm(firstValue);
			} else if (LATITUDE_LONGITUDE.equalsIgnoreCase(key)) {
				String[] args = firstValue.split(",");
				if (args == null || args.length < 2) {
					log.warning("Invalid " + LATITUDE_LONGITUDE
							+ " parameter sent: " + firstValue);
				} else {
					try {
						req.setLatitude(Double.valueOf(args[0]));
						req.setLongitude(Double.valueOf(args[1]));
					} catch (NumberFormatException e) {
						log.warning("Invalid " + LATITUDE_LONGITUDE
								+ " parameter sent: " + firstValue);
					}
				}
			} else if (EXCLUDE_CHAINS.equalsIgnoreCase(key)) {
				req.setExcludeChains("y".equalsIgnoreCase(firstValue));
			} else if (WIFI.equalsIgnoreCase(key)) {
				req.setWifi("y".equalsIgnoreCase(firstValue));
			}
		}
		return req;
	}
}
