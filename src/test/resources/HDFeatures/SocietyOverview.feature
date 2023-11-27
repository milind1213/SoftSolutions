@sanity
Feature: Validate Society Dashboard Metrics

  Background:
    Given User landed on Society Dashboard and Logged in with valid email "mamaearth@nobroker.com" and password "nobroker1"

  Scenario: Validate the Number of Staff on Duty
    When User Clicks on Society Overview
    Then Validate the total number of Staff on Duty
    And Validate the Number of Security and Other staff

  Scenario: Validate the Total Number of Flats
    When User Clicks on Society Overview
    Then Validate the total number of Flats
    And Validate the number of Owner Residing, Tenant Residing, Vacant, and Free flats

  Scenario: Validate the Total Number of Pending Approvals
    When User Clicks on Society Overview
    Then Validate the number of Pending Approvals
    And Validate the Move In, Staff, Services, and Moved Out Pending Approval Numbers

  Scenario: Validate the Total Number of All Complaints
    When User Clicks on Society Overview
    Then Verify the number of Total Number of Complaints
    And Verify the Number of Open, In Progress, Resolved, and Closed complaints status

