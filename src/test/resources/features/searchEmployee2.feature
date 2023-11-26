@regression
Feature: Searching existing Employees
  This one os more like imperative style (approach)

  Background:
    And user logs in with valid admin credentials
    * user navigates to the employee list page

  @searchEmployee
  Scenario: Search for an employee by id
    * user enters an existing employee id "0013" in the id-search-field
    When user clicks on the search button
    Then the employee information is displayed on the employee list table

  @searchEmployee
  Scenario: Search for an employee by name
    * user enters an existing employee name "Paul Brown" in the employee name-search-field
    When user clicks on the search button
    Then the employee information is displayed on the employee list table

