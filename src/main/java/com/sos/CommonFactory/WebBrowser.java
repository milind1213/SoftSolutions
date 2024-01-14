package com.sos.CommonFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
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
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--headless");
				options.addArguments("disable-infobars");
				options.addArguments("--user-data-dir=/home/milind/IdeaProjects/SoftSolutions/test-output/Default suite");
				options.addArguments("--disable-dev-shm-usage"); // Disable DevToolsActivePort check
				System.out.println("Launching headless browser");
			}
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--force-device-scale-factor=0.8");
			LoggingPreferences logs = new LoggingPreferences();
			logs.enable(LogType.BROWSER, Level.ALL);
			options.setCapability("goog:loggingPrefs", logs);

			if (isHeadless) {
				ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort()
						.withLogFile(new File("chromedriver.log")).withSilent(true).build();

				tlDriver.set(new ChromeDriver(service, options));
			} else {
				tlDriver.set(new ChromeDriver(options));
			}
		} else {
			System.out.println("Please pass the correct Browser value: " + browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		// getDriver().manage().window().setSize(new Dimension(280, 1000));
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
