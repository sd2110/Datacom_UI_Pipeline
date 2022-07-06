Feature: Verify the payment functionality

  @VerifyPayments
  Scenario Outline: Navigate and verify the payments page
    Given User launches the demo application website "<website>" on "<browserName>"
    When Navigates to the payments page
    And User clicks on the from Everyday account to Bills account
    And Transfer amount "<amount>"
    And Clicks on the Pay or Transfer button
    Then Payment is successful
    And Everyday account balance reduces by "<amount>"
    And Bill account balance increases by "<amount>"
    Examples:
      | website | amount |browserName|
      |https://www.demo.bnz.co.nz/client/ |500|firefox|
      |https://www.demo.bnz.co.nz/client/ |500|opera|
      |https://www.demo.bnz.co.nz/client/ |500|firefox |