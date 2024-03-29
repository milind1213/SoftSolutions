package com.sos.testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = {"src/test/resources/HSFeatures/TenatVerifications.feature"}, 
                  glue = {"com.sos.stepDefinitions"},
                  plugin = {"pretty","html:reports/Cucumber-Report.html",
                		    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                		    "rerun:reports/Failed-Scenarios.txt"},
                  dryRun = false,
                  monochrome = true)
public class TestNG_TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios(){
		return super.scenarios();  
	}
}	
