Feature: User Management
# 1. {
#  "name": "morpheus",
#  "email": "eve.holt@reqres.in",
#  "password": "pistol"
#  }
# 2. {
#  "name": "morpheus",
#  "email": "eve.holt@reqres.in",
#  "password": "pistol",
#  "edad":21,
#   "status":true
#  }

  Scenario: : Create a new User
    Given a server reqres.in
    And with the protocol https
    And using a body with parameter username and value rafaboca
    And using a body with parameter email and value rafaboca93@gmail.com
    And using a body with parameter password and value rafa1234
    When the server execute the query for Create a User
    Then the server response with Status 201

  Scenario: Read a User
    Given a server reqres.in
    And with the protocol https
    And with the userID as ID 2
    When the server execute the query for Read a User
    Then the server response with Status 200

  Scenario: Update a User
    Given a server reqres.in
    And with the protocol https
    And with the userID as ID 2
    And using a body with parameter username and value rafaboca
    And using a body with parameter email and value rafaboca93@gmail.com
    And using a body with parameter password and value rafa1234
    When the server execute the query for Update a User
    Then the server response with Status 200

  Scenario: Delete a User
    Given a server reqres.in
    And with the protocol https
    And with the userID as ID 2
    When the server execute the query for Delete a User
    Then the server response with Status 204


