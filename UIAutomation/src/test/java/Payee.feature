Feature: Verify Payee options

  Scenario Outline: Verify that the user can navigate to the Payees page
    Given User launches the demo website "<website>"
    And User clicks on Menu
    When User clicks on Payees option
    Then Payees page is loaded successful
    Examples:
      | website                  | username | password     |
      | https://www.demo.bnz.co.nz/client/| testuser | Password@123 |


  Scenario Outline: Verify that a new payee can be added on the Payees page
    Given User launches the demo website "<website>"
    And Navigates to the payee page
    When User clicks on the add button
    And Enters the payee details with name "<name>" and account number "<bank>""<branch>""<account>""<suffix>"
    And Clicks on the add button
    Then Payee added message "<message>" gets displayed
    And Payee gets added in the list of payees
    Examples:
      |website|bank|branch|account|suffix|message|

  Scenario Outline: Verify that the payee name is a mandatory field
    Given User launches the demo website "<website>"
    And Navigates to the payee page
    And User clicks on the add button
    When Clicks on the add button
    Then Validation error gets displayed for the mandatory payee name field
    When Enters the payee details with name "<name>" and account number "<bank>""<branch>""<account>""<suffix>"
    And Clicks on the add button
    Then Payee added message "<message>" gets displayed
    Examples:
      |website|bank|branch|account|suffix|

  Scenario Outline: Verify that the payee names can be sorted by name
    Given User launches the demo website "<website>"
    When Navigates to the payee page
    And User clicks on the add button
    Then List of added payee names are sorted in the ascending order
    When User clicks on the Name column
    Then List of added payee names get sorted in the descending order
    Examples:
    |website|

  Scenario Outline: Navigate and verify the payments page
    Given User launches the demo website "<website>"
    When Navigates to the payments page
    And User clicks on the from Everyday account to Bills account
    And Transfer amount "<amount>"
    And Clicks on the Pay or Transfer button
    Then Payment is successful
    And Everyday account balance reduces by "<amount>"
    And Bill account balance increases by "<amount>"
    Examples:
      |website|amount|



