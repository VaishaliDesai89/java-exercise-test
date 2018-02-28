package selenium.test;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"json:build/cucumber.json"},
features = {"src/main/java/selenium/test/features/SearchResults.feature"},
glue= {"selenium.test.stepdefs","stepdefs"}
)

public class TestRunner {
	
}
