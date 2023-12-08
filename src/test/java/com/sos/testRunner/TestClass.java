package com.sos.testRunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestClass {
@Test
public  void mainMethod() {
	  System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");  // Replace with your actual path
      WebDriver driver = new ChromeDriver();
      driver.get("https://www.google.com");
      driver.quit();
}
}
