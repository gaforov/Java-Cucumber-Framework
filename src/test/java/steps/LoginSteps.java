package steps;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.ConfigsReader;

public class LoginSteps extends BaseClass {
    @Given("user is on the exelenter webpage")
    public void userIsOnTheExelenterWebpage() {
        setUp();
    }

    @When("user pass the valid username and invalid password")
    public void user_pass_the_valid_username_and_invalid_password() {
        loginPage.loginToWebsite(ConfigsReader.getProperties("username"), "invalidpassword");
    }

    @When("user pass the valid username and empty password")
    public void user_pass_the_valid_username_and_empty_password() {
        loginPage.loginToWebsite(ConfigsReader.getProperties("username"), "");
    }

    @Then("login error message for invalid password is displayed")
    public void login_error_message_is_displayed_in_loginpage() {
        // Detailed, in three line.
        String expectedLoginErrorMsg = "Invalid credentials";
        String actualLoginErrorMsg = loginPage.loginErrorMessage.getText();
        Assert.assertEquals("Login Error Message is Incorrect", expectedLoginErrorMsg, actualLoginErrorMsg);

        // Assertion, in one line
//        Assert.assertEquals("Login Error Message is Incorrect","Invalid credentials", loginPage.loginErrorMessage.getText());

        tearDown();
    }

    @Then("login error message for empty password is displayed")
    public void loginErrorMessageForEmptyPasswordIsDisplayed() {
        String passwordBlankTextHint = "Required";   // Not good practice, in-line hard code used, can be optimized later.
        waitForVisibility(loginPage.passwordBlankErrorMessage);
        if (loginPage.passwordBlankErrorMessage.getText().contains("Required")) {
            Assert.assertEquals("Password cannot be blank", passwordBlankTextHint, loginPage.passwordBlankErrorMessage.getText());
        }
        //Assert.fail(); // Failing this for rerun failed.txt, please fix after you are done testing.
        tearDown();
    }

}
