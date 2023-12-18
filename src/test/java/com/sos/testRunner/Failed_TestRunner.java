package com.sos.testRunner;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = {"@reports/Failed-Scenarios.txt"}, 
                  glue = {"com.sos.stepDefinitions"},
                  plugin = {"pretty","rerun:reports/Failed-Scenarios.txt"},
                  dryRun = false,
                  monochrome = true)
public class Failed_TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();  
	}
}	
