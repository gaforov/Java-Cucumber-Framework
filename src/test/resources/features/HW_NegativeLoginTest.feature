Feature: Login Test (Negative)
  As an Invalid User, I should not be able to log in using invalid login
  credentials, and when I try to log in I should see an error message

  @negative
  Scenario: Login with invalid credentials
    When I enter an invalid username andOr password I will see an error message
      | Username | Password    | ErrorMessage             |
      | Admin    | invalidPass | Invalid credentials      |
      |          | invalidPass | Username cannot be empty |
      | Admin    |             | Password cannot be empty |
      |          |             | Username cannot be empty |
