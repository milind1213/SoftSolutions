@sanity
Feature: Society Admin Login Functionality Verification

  Scenario: Login with Valid Credentials
    Given the User Navigates to the Landing Page
    When the User logs in with Valid Email "mamaearth@nobroker.com" and Password "nobroker1"
    Then the Username should be displayed as "Test"
    And the Admin Dashboard Page title should be "Nobrokerhood Society"

  Scenario: Login with Invalid Credentials
    Given the User Navigates to the Landing Page
    When the User logs in with Valid Email "mamaearth@nobroker.com" and Password "nobroker"
    Then an error message should be displayed"Bad credentials"
