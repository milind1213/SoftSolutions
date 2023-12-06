package com.sos.testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/ParallelFeatures" }, glue = { "Parallel" },
		// tags = "@dataTable",
		plugin = { "pretty", "html:reports/Cucumber-Report.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/" }, dryRun = false, monochrome = true)
public class Parallel_TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
 