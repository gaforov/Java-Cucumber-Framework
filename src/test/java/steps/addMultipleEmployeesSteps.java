package steps;

import base.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.Constants;
import utils.ExcelUtility;

import java.util.List;
import java.util.Map;

public class addMultipleEmployeesSteps extends BaseClass {
    @Given("user navigates to the add employee page")
    public void user_navigates_to_the_add_employee_page() {
        pimPage.navigateToAddEmployee();
    }

    @When("user enters new employee's {string}, {string}, and {string}")
    public void userEntersNewEmployeeSAnd(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("user clicks on the save button")
    public void user_clicks_on_the_save_button() {
        addEmployeePage.saveButton.click();
    }

    @Then("new employee {string} is added successfully")
    public void new_employee_is_added_successfully(String fullName) {
        Assert.assertEquals("Employee full name does not match", fullName, personalDetailsPage.employeeFullName.getText());
    }

    @When("user enters employee's full name and clicks on save button")
    public void user_enters_employee_s_full_name_and_clicks_on_save_button(DataTable dataTable) {
        List<Map<String, String>> mapList = dataTable.entries();
//        int mapSize = mapList.size();
//        System.out.println(mapList);
//        mapList.forEach(System.out::println);
//        int counter = 0;
        for (Map<String, String> user : mapList) {
            addEmployeePage.firstName.sendKeys(user.get("FirstName"));   // Ariana
            addEmployeePage.middleName.sendKeys(user.get("MiddleName")); // A.
            addEmployeePage.lastName.sendKeys(user.get("LastName"));     // Knight
            addEmployeePage.saveButton.click();

            // Assertion/Validation
            String expectedFullName = user.get("FirstName") + " " + user.get("MiddleName") + " " + user.get("LastName");
            String actualFullName = personalDetailsPage.employeeFullName.getText();
            Assert.assertEquals("User does not match", expectedFullName, actualFullName);

            // click Add Employee again
            wait(1);
            // 1st way: avoid last unnecessary click on 'Add Employee' sub-menu
//            counter++;
//            if (mapList.size() = !counter) {
//                click(pimPage.addEmployee);
//            }

            // 2nd way: avoid last unnecessary click on 'Add Employee' sub-menu
            // hard code version of it
//            if (!user.get("Firstname").equals("Alexis")) {
//                click(pimPage.addEmployee);
//            }
            // we can avoid last click by either the counter OR code below.
            // Firstname is not best example, EmpID or SSN, a unique identifier is better.
            if (!user.get("FirstName").equals(mapList.get(mapList.size() - 1).get("FirstName"))) {
                pimPage.addEmployee.click();
            }
        }

    }

    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        System.out.println("All employees are added successfully using DataTable");
    }

    @When("user enters employee data from the {string} sheet")
    public void user_enters_employee_data_from_the_sheet(String sheetName) {
        List<Map<String, String>> mapList = ExcelUtility.readFromExcelMap(Constants.TESTDATA_FILEPATH, sheetName);
        for (Map<String, String> map : mapList) {
            addEmployeePage.firstName.sendKeys(map.get("Firstname"));
            addEmployeePage.lastName.sendKeys(map.get("Lastname"));
            addEmployeePage.saveButton.click();

            // validation
            String expectedFullName = map.get("Firstname") + " " + map.get("Lastname");
            String actualFullName = personalDetailsPage.employeeFullName.getText();
            Assert.assertEquals("Employee name does not match",expectedFullName, actualFullName);
            System.out.println(actualFullName + " is added successfully using Excel import");

            // click addEmployee sub-menu again
            if (!map.get("Firstname").equals(mapList.get(mapList.size() - 1).get("Firstname"))) {
                pimPage.addEmployee.click();
            }
        }
    }

    @Then("new employee is added successfully using Excel import")
    public void new_employee_is_added_successfully_using_excel_import() {
        System.out.println("All new employees are added successfully using the Excel file");
    }

}
