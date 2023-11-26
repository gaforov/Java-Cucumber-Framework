package steps;

import base.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NegativeLoginTestSteps extends BaseClass {
    @When("I enter an invalid username andOr password I will see an error message")
    public void i_enter_an_invalid_username_and_or_password_i_will_see_an_error_message(DataTable dataTable) {
        List<Map<String, String>> mapList = dataTable.entries();
        for (Map<String, String> map : mapList) {
            // 1st way:
            if (Objects.isNull(map.get("Username"))) {
                sendText(loginPage.username, map.get("Username"));
            } else {
                loginPage.username.clear();
            }
            if (map.get("Password") != null) {
                sendText(loginPage.password, map.get("Password"));
            } else {
                loginPage.password.clear();
            }

            // 2nd way: (if you use this one, comment out login click)
//            if (map.get("Username") == null) {
//                if (map.get("Password") == null) {
//                    loginPage.loginToWebsite("", "");
//                    wait(1);
//                }else {
//                    loginPage.loginToWebsite("", map.get("Password"));}
//                wait(1);
//            } else if (map.get("Password") == null) {
//                loginPage.loginToWebsite(map.get("Username"), "");
//                wait(1);
//            } else {
//                loginPage.loginToWebsite(map.get("Username"), map.get("Password"));
//                wait(1);
//            }

            loginPage.loginBtn.click();
            wait(1);

            Assert.assertEquals("Login Error Message is Incorrect", map.get("ErrorMessage"), loginPage.loginErrorMessage.getText());
            System.out.println("Negative Login Test Passed. Error Message '" + map.get("ErrorMessage") + "' is displayed");
            driver.navigate().refresh();
            wait(1);
        }
    }
}


/**
 * As of Cucumber 7.0 and later asList(), asLists(), and asMaps() have changed:
 * Replace DataTable.asList() with -> DataTable.values()
 * Replace DataTable.asLists() with -> DataTable.cells()
 * Replace DataTable.asMaps() with -> DataTable.entries()
 */