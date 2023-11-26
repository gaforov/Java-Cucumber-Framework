package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed.txt"
        , glue = "steps"
        , plugin = {
        "pretty",
        "html:target/cucumber-report/cucumberReport.html",
        "json:target/cucumber-report/cucumberReport.json",
        "rerun:target/failed.txt" // <== keep updating project's failed.txt file each time I fix and rerun my failed tests.
}
)


public class FailRunner {
      /*
   Alternative to Showing Only Failed Tests via Maven execution:
   https://maven.apache.org/surefire/maven-surefire-report-plugin/examples/show-failures.html
     */
}
