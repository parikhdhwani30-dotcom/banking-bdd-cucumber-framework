@login @smoke @regression
Feature: Banking Portal Customer Login
  As a customer of ParaBank
  I want to log in with valid credentials
  And see descriptive error messages when login fails
  So that I can access my banking dashboard securely

  Background:
    Given I navigate to the banking portal login page

  @positive @valid_login
  Scenario: Valid customer login to Accounts Overview
    When I log in with username "john" and password "demo"
    Then I should be navigated to the "Accounts Overview" dashboard
    And I should see customer welcome message "Welcome John Smith"

  @negative @invalid_login
  Scenario Outline: Failed customer login validations
    When I log in with username "<username>" and password "<password>"
    Then I should see an authentication error message "<error_message>"

    Examples:
      | username     | password     | error_message                                             |
      | invalidUser  | demo         | An internal error has occurred and has not been reported. |
      | john         | wrongPass    | The username and password could not be verified.          |
      |              | demo         | Please enter a username and password.                     |
