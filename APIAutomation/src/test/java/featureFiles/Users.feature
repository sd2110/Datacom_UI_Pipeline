Feature: Verify Users API

  Scenario Outline: Verify GET Users status and response size
    When User performs a GET call on the request URL "<url>"
    Then API call is successful with a status code "<statusCode>"
    And The response contains "<numberOfUsers>" users
    Examples:
    |url|statusCode|numberOfUsers|
    |https://jsonplaceholder.typicode.com/users|200|10|

  Scenario Outline: Verify GET Users status code and name for a given id
    When User performs a GET call on the request URL "<url>"
    Then API call is successful with a status code "<statusCode>"
    And The response contains name "<name>" for the id "<idNumber>"
    Examples:
      |url|statusCode|name|idNumber|
      |https://jsonplaceholder.typicode.com/users|200|Nicholas Runolfsdottir V|8|

  Scenario Outline: Verify POST Users status code and verify the created user
    When User performs a POST call on the request URL "<url>" with name "<name>", username "<userName>" and email "<email>" in the request body
    Then API call is successful with a status code "<statusCode>"
    And The response contains the requested fields with name "<name>", username "<userName>" and email "<email>"
    Examples:
      |url|statusCode|name|userName|email|
      |https://jsonplaceholder.typicode.com/users|201|Sagar|sd|abc@test.com|