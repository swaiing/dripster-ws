package swai.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.util.StringUtils;

public class PropertiesHelper {

	// logger
	private static final Logger log = Logger.getLogger(PropertiesHelper.class
			.getName());

	private static final String PROP_FILE = "app.properties";

	private static final String APP_ENGINE_VERSION_PROP = "com.google.appengine.runtime.version";

	private static PropertiesHelper instance = new PropertiesHelper();

	private boolean isRunningContainer;

	private Properties properties;

	private PropertiesHelper() {

		// check app engine runtime version property is set
		isRunningContainer = System.getProperty(APP_ENGINE_VERSION_PROP) != null;

		properties = new Properties();
		InputStream in = this.getClass().getResourceAsStream(PROP_FILE);
		try {
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertiesHelper getInstance() {
		return instance;
	}

	public String get(String key) {

		if (!StringUtils.hasText(key)) {
			log.warning("Key not a valid string");
			// TODO: throw exception
			return "";
		}

		// Retrieve value
		String value;
		if (isRunningContainer && System.getProperty(key) != null)
			value = System.getProperty(key);
		else
			value = properties.getProperty(key);
		
		return value;
	}

}
