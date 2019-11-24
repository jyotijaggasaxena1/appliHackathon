package com.applitools.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility to provide getters for property file methods.
 * 
 * @author jyotisaxena
 *
 */
public class PropUtil {
	Properties props;

	public PropUtil() throws IOException {
		InputStream is = new FileInputStream("src//test//resources//login.properties");
		props = new Properties();
		props.load(is);
	}

	public String getURL() {
		return props.getProperty("URL");
	}

	public String getURLwithAds() {
		return props.getProperty("URLWithAds");
	}

	public String getUserName() {
		return props.getProperty("username");
	}

	public String getPassword() {
		return props.getProperty("password");
	}
}
