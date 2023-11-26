package steps;

import base.BaseClass;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class SearchEmployeeSteps extends BaseClass {
    @Given("user is on the Exelenter homepage")
    public void user_is_on_the_exelenter_homepage() {
        setUp();
    }

    @Given("user logs in with valid admin credentials")
    public void user_logs_in_with_valid_admin_credentials() {
        loginPage.loginToWebsiteViaConfigs("username", "password");
    }

    @Given("user navigates to the employee list page")
    public void user_navigates_to_the_employee_list_page() {
        pimPage.navigateToEmployeeList();
    }

    @Given("user enters an existing employee id {string} in the id-search-field")
    public void user_enters_an_existing_employee_id_in_the_id_search_field(String empID) {
        wait(1);
        employeeListPage.searchEmployeeById(empID);
    }

    @When("user clicks on the search button")
    public void user_clicks_on_the_search_button() {
        click(employeeListPage.empListSearchButton);
    }

    @Then("the employee information is displayed on the employee list table")
    public void the_employee_information_is_displayed_on_the_employee_list_table() {
        System.out.println("Employee info is displayed");
    }

    @Given("user enters an existing employee name {string} in the employee name-search-field")
    public void user_enters_an_existing_employee_name_in_the_employee_name_search_field(String empName) {
        wait(1);
        employeeListPage.searchEmployeeByName(empName);
    }

    // This is hard-coded version of step definitions(without parameter)
    @And("user enters an existing employee id in the id-search-field")
    public void search_employee_by_id() {
        employeeListPage.searchEmployeeById("0909");
    }

    // This is hard-coded version of step definitions(without parameter)
    @And("user enters an existing employee name in the employee name-search-field")
    public void search_employee_by_name() {
        employeeListPage.searchEmployeeByName("John Doe");
    }
}
