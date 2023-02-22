@socialMediaApiValidation
Feature: Valiodate the User Post Comments API

  Scenario: Verify User Post Comments API GET call should retrive the all comments information of a given user post with response code as 200
    When user calls User Post Comments API with given input parameters
      | postId            |   1 |
      | requestMethodType | GET |
    Then user validate folowing values in response comments
      | statusCode   | 200 |
      | postId       |   1 |
      | recordCount | 5 |

  Scenario: Verify User Post Comments API GET call should retrive the specific comments information of a given post with response code as 200
    When user calls User Post Comments API with given input parameters
      | postId            |   1 |
      | id                |   1 |
      | requestMethodType | GET |
    Then user validate folowing values in response comments
      | statusCode |                          200 |
      | name       | id labore ex et quam laborum |
      | email      | Eliseo@gardner.biz           |
      | id         |                            1 |

  Scenario: Invalid Input: Verify User Post Comments API GET call should returns 200 response code with empty response object
    When user calls User Post Comments API  with invalid paramters
      | postId            | 1001 |
      | requestMethodType | GET  |
    Then user validate empty comments response and success status code
      | key        | value |
      | statusCode |   200 |
      
  
   Scenario: Verify Users Post Comments API POST call should returns 201 response code
    When user calls User Post Comments API with valid json request and request type
      | requestMethodType | POST     |
      | jsonFileName      | addComments |
    Then user validate folowing values in response comments
      | statusCode | 201 |
      | id         | 501 | 
