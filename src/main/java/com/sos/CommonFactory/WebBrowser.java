package com.sos.CommonFactory;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.sos.Utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebBrowser {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver init_driver(String browser) {

		System.out.println("Browser value is: " + browser);
		ConfigReader configReader = new ConfigReader();
		boolean isHeadless = Boolean.parseBoolean(configReader.init_prop().getProperty("headless"));

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if (isHeadless) {
				options.addArguments("--headless");
				System.out.println("launching headless browser");
			}
			options.addArguments("--force-device-scale-factor=0.8");
			tlDriver.set(new ChromeDriver(options));
		} else {
			System.out.println("Please pass the correct Browser value: " + browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//getDriver().manage().window().setSize(new Dimension(280, 1000));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public static String getUrl() {
		ConfigReader config = new ConfigReader();
		return (String) config.init_prop().get("url");
	}
}
