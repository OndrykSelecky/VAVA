package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.annotations.common.util.impl.LoggerFactory;

public class PropertiesWrapper {
	private static Properties properties;

	public static synchronized Properties getProperties() {
		if (properties != null) {
			return properties;
		}
		
        properties = new Properties();

		try(FileInputStream fs = new FileInputStream("res/config.properties")) {
			properties.load(fs);
		} catch (IOException e) {
			LoggerFactory.logger(PropertiesWrapper.class).error("wtf", e);
		}
		
		return properties;
	}
}
