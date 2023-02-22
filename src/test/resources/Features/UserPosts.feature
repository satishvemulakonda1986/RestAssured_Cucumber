@socialMediaApiValidation
Feature: Valiodate the User Posts API

  Scenario: Verify User Posts API GET call should retrive the all posts information of a given user with response code as 200
    When user calls User Posts API with given data
    |userId  				|10     |
    |requestMethodType|GET 								 |   
    Then user validate folowing values in response post 
    |statusCode|200          |
    |userId| 10|
  
  Scenario: Verify User Posts API GET call should retrive the specific post information of a given post title with response code as 200
    When user calls User Posts API with given data
    |userId  				|1     |
    |id							| 10			|
    |requestMethodType|GET 								 |   
    Then user validate folowing values in response post 
    |statusCode|200          |
    |title| optio molestias id quia eum|
    |id							| 10			|
    
  Scenario: Invalid Input: Verify User Posts API GET call should returns 200 response code with empty response object
    When  user calls User Posts API  with not exist data
    |userId  				|1001     |
    |requestMethodType|GET 								 |
    Then user validate Empty Post response and below status code
    |key			 | value			 |
    |statusCode|200          |

  Scenario: Verify Users Post API GET call should return list of Posts avilable with 200 response with expected count of Post given
    When user calls User Posts API with given data
    |requestMethodType|GET 								 |
    | userId | 1 |
    Then post list returned with expected count as given
    | recordCount | 10 |
    | statusCode | 200 |

  Scenario: Verify Users Posts API POST call should returns 201 response code
    When user calls User Posts API with valid json request and request type
      | requestMethodType | POST     |
      | jsonFileName      | addPosts |
    Then user validate folowing values in response post
      | statusCode | 201 |
      | id         | 101 |