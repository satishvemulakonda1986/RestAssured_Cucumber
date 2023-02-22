package runner;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.testng.Assert;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * This Class is the Runner class to initiate the Cucumber Context and Features
 * Lookup in order to Trigger Same.
 * 
 * @author Admin
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features/", monochrome = true, plugin = { "pretty",
		"html:target/site/cucumber-pretty.html",
		"json:target/cucumber.json" }, glue = { "stepDefinitions" }, tags = "", publish = true)
public class TestRunner {
	@Test
	public void sampleTest() {
		org.testng.Assert.assertEquals(true, true);
	}
}
