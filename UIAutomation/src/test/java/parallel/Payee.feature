Feature: Verify Payee options

  @VerifyPayeePage
  Scenario Outline: Verify that the user can navigate to the Payees page
    Given User launches the demo application website "<website>" on "<browserName>"
    And User clicks on Menu
    When User clicks on Payees option
    Then Payees page is loaded successfully
    Examples:
      | website                            |browserName|
      | https://www.demo.bnz.co.nz/client/ |opera     |

  @AddPayee
  Scenario Outline: Verify that a new payee can be added on the Payees page
    Given User launches the demo application website "<website>" on "<browserName>"
    And Navigates to the payee page
    When User clicks on the add button
    And Enters the payee details with name "<name>" and account number "<bank>""<branch>""<account>""<suffix>"
    And Clicks on the add button
    Then Payee added message "<message>" gets displayed
    And Payee with "<name>" gets added in the list of payees
    Examples:
      | website                            |browserName| name    | bank | branch | account | suffix | message     |
      | https://www.demo.bnz.co.nz/client/ | firefox   |Dell NZ | 01   | 0002   | 0190234 | 10     | Payee added |

    @PayeePageValidations
  Scenario Outline: Verify that the payee name is a mandatory field
    Given User launches the demo application website "<website>" on "<browserName>"
    And Navigates to the payee page
    And User clicks on the add button
    When Clicks on the add button
    Then Validation error gets displayed for the mandatory payee name field
    When Enters the payee details with name "<name>" and account number "<bank>""<branch>""<account>""<suffix>"
    And Clicks on the add button
    Then Payee added message "<message>" gets displayed
    Examples:
      | website                            | browserName|name    | bank | branch | account | suffix | message     |
      | https://www.demo.bnz.co.nz/client/ | firefox     |Dell NZ | 01   | 0002   | 0190234 | 10     | Payee added |

      @PayeeNameSorting
  Scenario Outline: Verify that the payee names can be sorted by name
    Given User launches the demo application website "<website>" on "<browserName>"
    And Navigates to the payee page
    When User clicks on the add button
    And Enters the payee details with name "<name>" and account number "<bank>""<branch>""<account>""<suffix>"
    And Clicks on the add button
    And Payee added message "<message>" gets displayed
    Then List of added payee names are sorted in the ascending order
    When User clicks on the Name column
    Then List of added payee names get sorted in the descending order
    Examples:
      | website                            | browserName|name    | bank | branch | account | suffix | message     |
      | https://www.demo.bnz.co.nz/client/ | firefox    |Dell NZ | 01   | 0002   | 0190234 | 10     | Payee added |





