package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"   // Specify path from Content Root, features option will establish connection with feature file(s).
        ,glue = "steps"     // specify path from Source Root, glue option will establish connection with step definitions.
        ,dryRun = false     // if set to True, it will check if ALL step definitions have implementation. Without running your actual scenarios (headless).
        ,monochrome = true    // This will print test results in the console in more human-readable format.
//        ,tags = "@smoke"       // run test(s) with provided tag name
//        ,tags = "@regression or @smoke"       // Run any scenario test that has either regression OR smoke test.  ||
//        tags = "@regression and @smoke"       // Run any scenario test that has BOTH regression AND smoke test. If one missing it will not run.  &&
        ,tags = "@test123"
        ,plugin = {
                "pretty",  // Prints Gherkin steps in the console AND terminal/command line
                "html:target/cucumber-report/cucumberReport.html",
                "json:target/cucumber-report/cucumberReport.json",
                "rerun:target/failed.txt"
}


)




public class TestRunner {
}
