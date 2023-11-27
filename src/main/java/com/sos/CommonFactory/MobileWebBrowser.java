package com.sos.CommonFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import com.sos.Utilities.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileWebBrowser {
    public static String proj = "";
    ConfigReader config = new ConfigReader();
    public static WebDriver webDriver;
    public static ArrayList<WebDriver> webDriverArrayList = new ArrayList<>();

    public WebDriver getMobileWebDriverInstance(String browser) {

        System.out.println("Browser value is: " + browser);
        ConfigReader configReader = new ConfigReader();
        boolean isHeadless = Boolean.parseBoolean(configReader.init_prop().getProperty("headless"));
        
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            if (isHeadless) {
                System.out.println("launching headless browser");
                options.addArguments("--no-sandbox");
                options.addArguments("--headless"); // headless not recommended for react ui
                options.addArguments("--remote-debugging-port=9222");
            }
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            options.setExperimentalOption("useAutomationExtension", false); // deprecated
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.addArguments("--disable-web-security");
            options.addArguments("--no-proxy-server");
            options.addArguments("enable-automation");
            // options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");

            Map<String, String> mobileWeb = new HashMap<>();
            mobileWeb.put("deviceName", "iPhone X");
            options.setExperimentalOption("mobileEmulation", mobileWeb);

            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("autofill.profile_enabled", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            webDriver = new ChromeDriver(options);

            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

            webDriverArrayList.add(webDriver);
            setWebDriverDriver(webDriverArrayList);
            System.out.println("Added All Created drivers to WebDriver list");
        }
        return webDriver;
    }

    public static void setWebDriverDriver(ArrayList<WebDriver> webDrivers) {
        webDriverArrayList = webDrivers;
    }
}
