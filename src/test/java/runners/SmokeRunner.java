package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
        , glue = "steps"
        , tags = "@smoke"
        , plugin = {
        "pretty",
        "html:target/cucumber-report/cucumberReport.html",
        "json:target/cucumber-report/cucumberReport.json",
        "rerun:target/failed.txt"
}
)


public class SmokeRunner {
}
