Feature: Number of Staff/Security Verification

  Scenario: Validating the number of staff under the Manage Staff/Security option
    Given the user is on the landing page
    And the user is logged in with email "<email>" and password "<password>"
    When the user clicks on "Gate & Visitor Management" and selects "Manage Staff/Security"
    Then the user verifies the number of staff

    Examples:
      | email                        | password    |
      | mamaearth@nobroker.com       | nobroker1   |
