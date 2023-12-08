package com.sos.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/Drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://chromedriver.chromium.org/getting-started");
        driver.quit();
    }
}

