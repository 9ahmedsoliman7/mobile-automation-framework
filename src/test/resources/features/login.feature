Feature: Login
  As a shopper
  I want to log in to the My Demo App
  So that I can access my account

  Scenario: Successful login with valid credentials
    Given the user is on the login screen
    When the user logs in with username "bob@example.com" and password "10203040"
    Then the user should leave the login screen

  Scenario: Login fails for a locked out user
    Given the user is on the login screen
    When the user logs in with username "alice@example.com" and password "10203040"
    Then an error message should be displayed