@login
Feature: Login page works

  @passedTest
  Scenario: Success Login
    Given I load the SwagLab page
    When I login with a valid login credentials
    Then I should be able to login

  @failedTest
  Scenario: Failed Login
    Given I load the SwagLab page
    When I login with an invalid login credentials
    Then I should not be able to login