package cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;

@Disabled
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
