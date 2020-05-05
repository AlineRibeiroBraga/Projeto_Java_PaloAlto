Feature: Insert a Legal Entity

  Scenario Outline: Insert a Legal Entity
    Given A Legal Entity
    And The name's Legal Entity is "<name>"
    And The trade Name's Legal Entity is "<tradeName>"
    And The document's Legal Entity is is "<document>"
    And The active's Legal Entity "<active>"
    And A Address
    And The district is "<district>"
    And The number is "<number>"
    And The city is "<city>"
    And The state is "<state>"
    And The zipCode is "<zipCode>"
    And The main is "<main>"
    When User executes a Post
    Then The server should return a <httpStatusCode>