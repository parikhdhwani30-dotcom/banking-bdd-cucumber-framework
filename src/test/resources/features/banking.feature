@smoke @regression
Feature: Banking Account Management & Fund Transfer
  As a registered banking customer
  I want to log in, view account balances, and initiate fund transfers
  So that I can manage my personal finances securely

  Background:
    Given I navigate to the banking portal login page

  @positive @login
  Scenario: Successful login with valid customer credentials
    When I log in with username "john" and password "demo"
    Then I should be navigated to the "Accounts Overview" dashboard
    And I should see customer welcome message "Welcome John Smith"

  @negative @login
  Scenario Outline: Failed login attempts with invalid credentials
    When I log in with username "<username>" and password "<password>"
    Then I should see an authentication error message "<error_message>"

    Examples:
      | username     | password     | error_message                          |
      | invalidUser  | demo         | An internal error has occurred and has not been reported. |
      | john         | wrongPass    | The username and password could not be verified.          |
      |              | demo         | Please enter a username and password.  |

  @transfer @critical
  Scenario: Transfer funds between checking and savings account
    Given I am authenticated as "john" with password "demo"
    When I navigate to the "Transfer Funds" service
    And I transfer "$250.00" from account "12345" to account "54321"
    Then I should receive a transfer confirmation message "Transfer Complete!"
    And the transaction details should state "$250.00 has been transferred"
