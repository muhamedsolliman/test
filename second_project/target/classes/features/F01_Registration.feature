@smoke
Feature: F01_Registration | guest users could create new accounts
  #postive scenario
  Scenario: user could create new account with valid date
    Given user go to register page
    When user select gender type
    And user Enter "automation" & "tester"
    And user select date of birth
    And user add Enter email
    And User Enter "P@ssw0rd" password and confirm password
    And user click on register button
    Then The account is created Successfully