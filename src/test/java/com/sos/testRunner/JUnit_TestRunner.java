package com.sos.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/Features" }, glue = { "stepDefinitions"},
        plugin = {"pretty","html:reports/myreport.html", "html:reports/myreport.json",
        		 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
        dryRun=false,
        monochrome=true)
public class JUnit_TestRunner {
	
}