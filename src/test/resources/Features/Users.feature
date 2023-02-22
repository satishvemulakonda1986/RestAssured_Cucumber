@socialMediaApiValidation
Feature: Users List API

  Scenario: Verify Users List API GET call should returns 200 response with appropriate results set based in the input parameters given
    When user calls User List API with given input parameters
    |username  				|Moriah.Stanton      |
    |requestMethodType|GET 								 |   
    Then user validate folowing values in response
    |statusCode|200          |
    |username| Moriah.Stanton|
    |email| Rey.Padberg@karina.biz|

  Scenario: Invalid Input: Verify Users List API GET call should returns 200 response code with empty response object
    When  user calls User List API  with invalid Parameters
    |username  				|Test123     |
    |requestMethodType|GET 								 |
    Then user validate Post Empty response and below status code
    |statusCode|200          |
    

  Scenario: Verify Users List API GET call should return list of Users avilable with 200 response with expected count of Users given
    When user calls User List APAI with No Input parameters
    |requestMethodType|GET 								 |
    Then users list returned with expected count as given
    | recordCount | 10 |
    | statusCode | 200 |
    
  Scenario: Verify Users List API POST call should returns 201 response code
    When user calls User List API with valid json request and request type
      | requestMethodType | POST    |
      | jsonFileName      | addUser |
    Then user validate folowing values in response
      | statusCode | 201 |
      | id         |  11 |
  