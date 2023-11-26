@login
Feature: Permissions Login Test
  This is my description and it is totally optional - where you describe your feature, in more details.

#  This is declarative style
  @smoke
  Scenario: valid admin login
    When admin user logs in with valid credentials
    Then dashboard header text is displayed on dashboard page

#  This step doesn't work due to server not saving a user other than default Admin
#  Scenario: valid ess login
#    When ess user logs in with valid credentials
#    Then ess user logs in successfully

#  @test123
  Scenario Outline:
    When user enters a valid "<username>" and a valid "<password>"
    And user clicks on the login button
    Then user logs in successfully and a "<message>" is displayed
    Examples:
      | username | password | message   |
      | Admin    | admin123 | Dashboard |
#      | Admin    | Exelent2022Sdet! | Welcome Admin |
#      | EssUser  | Ess@2023         | Welcome John  |


#  Similar to DataProvider in TestNG
