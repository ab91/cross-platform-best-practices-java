@load
Feature: Load page works

  Scenario: Check SwagLabs Title
    Given I load the SwagLab page
    Then I should see 'Swag Labs' title