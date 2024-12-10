package steps;

import base.WebDriverManager;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class Login2Steps extends WebDriverManager {
    @When("admin user logs in with valid credentials")
    public void admin_user_logs_in_with_valid_credentials() {
        //1st way
        loginPage.loginToWebsiteViaConfigs("username", "password");

        //2nd way
//        sendText(loginPage.username, ConfigsReader.getProperties("username"));
//        sendText(loginPage.password, ConfigsReader.getProperties("password"));
//        click(loginPage.loginBtn);

        //3rd way
//        loginPage.username.sendKeys("Admin");  // hardcode
//        loginPage.password.sendKeys(ConfigsReader.getProperties("password")); // from ConfigsReader
//        loginPage.loginBtn.clear();
    }
    @Then("admin user logs in successfully")
    public void admin_user_logs_in_successfully() {
//        Assert.fail(); // Failing on purpose for failed.txt generating. Fix later.
        String expectedMessage = "Welcome Admin";
        String actualMessage = dashboardPage.essUserFullName.getText();
        Assert.assertEquals("Admin user unable to log in",expectedMessage,actualMessage);
    }

    @When("ess user logs in with valid credentials")
    public void ess_user_logs_in_with_valid_credentials() {
        loginPage.loginToWebsiteViaConfigs("essUsername", "essPassword");
    }
    @Then("ess user logs in successfully")
    public void ess_user_logs_in_successfully() {
        //Assert.fail(); // Failing this for rerun fail txt, change back when you are done.
        String expectedMessage = "ESS_Firstname ESS_Lastname";
        String actualMessage = dashboardPage.essUserFullName.getText();
        Assert.assertEquals("ESS user unable to log in",expectedMessage,actualMessage);
    }

    @When("user enters a valid {string} and a valid {string}")
    public void user_enters_a_valid_and_a_valid(String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
    }
    @And("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.loginBtn.click();
    }
    @Then("user logs in successfully and a {string} is displayed")
    public void user_logs_in_successfully_and_a_is_displayed(String expectedMessage) {
        //String actualMessage = dashboardPage.welcome.getText();
        Assert.assertEquals("User login is NOT successful",expectedMessage, dashboardPage.dashboardHeaderText.getText());
    }












}
