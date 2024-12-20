package steps;

import base.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before                           // Before annotation (from Cucumber, not JUnit) will run before every Scenario
    public void startBrowser() {
        WebDriverManager.setUp();
    }

    @After
    public void quitBrowser(Scenario scenario) {
//        if (scenario.isFailed()) {
//            CommonMethods.takeScreenshot("FailTests/" + scenario.getName());
//        }
        WebDriverManager.tearDown();
    }
}
