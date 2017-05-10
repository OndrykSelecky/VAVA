package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wraps properties for convenient access. Suffices for our purposes.
 * 
 * @author thecodecook
 *
 */
public class PropertiesWrapper {
	private static Properties properties;

	public static synchronized Properties getProperties() {
		if (properties != null) {
			return properties;
		}

		properties = new Properties();

		try (FileInputStream fs = new FileInputStream("res/config.properties")) {
			properties.load(fs);
		} catch (IOException e) {
			Logger.getLogger(PropertiesWrapper.class.getName()).log(Level.SEVERE,
					"Failed opening our properties file config.properties.", e);
		}

		return properties;
	}
}
