Feature: Adding multiple employees
  To perform Data Driven Testing in Cucumber we use Scenario Online

  Background:
    Given user logs in with valid admin credentials
    And user navigates to the add employee page


  Scenario Outline: Adding multiple employees
    When user enters new employee's "<First Name>", "<Middle Name>", and "<Last Name>"
    And user clicks on the save button
    Then new employee "<Full Name>" is added successfully
    Examples:
      | First Name | Middle Name | Last Name | Full Name             |
      | Lily       | M.          | Graham    | Lily M. Graham        |
      | Benjamin   | J.          | Parker    | Benjamin J. Parker    |
      | Sophia     | L.          | Kim       | Sophia L. Kim         |
      | David      | A.          | Evans     | David A. Evans        |
      | Gabrielle  | R.          | Thompson  | Gabrielle R. Thompson |

#   2nd way: Adding multiple users using Cucumber DataTable
  @dataTable
  Scenario: Adding multiple employees
    When user enters employee's full name and clicks on save button
      | FirstName | MiddleName | LastName |
      | Ariana    | A.         | Knight   |
      | Zain      | H.         | Bailey   |
      | Alexis    | J.         | Cameron  |
    Then employee is added successfully

#3rd way: from Excel
  @excel
  Scenario: Adding multiple employees from Excel
  When user enters employee data from the "Employee" sheet
  Then new employee is added successfully using Excel import


#  Scenario Outline:
#    When user enters "<firstName>" and "<lastName>"
#    And user enters employeeID"<employeeId>"
#    And user click on create login Details "<checkbox>"
#    And user enters username "<username>" and  password "<password>"
#    And user clicks save button
#    Then user verifies employee "<verify full name>" successfully created
#    Examples:
#      | firstName | lastName | employeeId | checkbox | username | password       | verify full name |
#      | Tony      | Rip      | 2001       |          |          |                | Tony Rip         |
#      | Bob       | Star     |            |          |          |                | Bob Star         |
#      | Emma      | Maria    | 2002       | YES      | Emmmar   | Exelenter2023! | Emma Maria       |


#    Alternatively, you can do this
#    Then new employee "<First Name> <Middle Name> <Last Name>" is added successfully
#    Examples:
#      | First Name | Middle Name | Last Name |
#      | Lily       | M.          | Graham    |
#      | Benjamin   | J.          | Parker    |
#      | Sophia     | L.          | Kim       |
#      | David      | A.          | Evans     |
#      | Gabrielle  | R.          | Thompson  |


#    """
#    This is multiline String, not a comment.
#    Expected is local = Full name column, ex:  "Lily M. Graham"
#    Actual full name is coming from Personal Details Page header.
#    """