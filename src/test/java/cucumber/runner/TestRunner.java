package cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "cucumber.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true,
        tags = "@Registration"
)
public class TestRunner {
}
