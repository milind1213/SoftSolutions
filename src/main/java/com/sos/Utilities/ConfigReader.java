package com.sos.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private Properties prop;
	private FileInputStream fis;

	public Properties init_prop() {

		prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/ConfigDirectory/Config.properties");
			prop.load(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}