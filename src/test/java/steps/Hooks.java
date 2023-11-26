package steps;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks {
    @Before                           // Before annotation (from Cucumber, not JUnit) will run before every Scenario
    public void startBrowser() {
        BaseClass.setUp();
    }
    @After
    public void quitBrowser(Scenario scenario) {
//        if (scenario.isFailed()) {
//            CommonMethods.takeScreenshot("FailTests/" + scenario.getName());
//        }
        BaseClass.tearDown();
    }
}
