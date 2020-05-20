Feature: Update a Legal Entity without partners

  Scenario Outline: Update a Legal Entity without partners
    Given this url "<url>"
    And this key "<key>"
    And Verify if This Legal Entity is registered "<name>","<tradeName>","<document>","<active>","<district>","<number>","<city>","<state>","<zipCode>","<main>"
    When the user updates a Legal Entity
    And this name is "<name>"
    And this tradeName is "<tradeName>"
    And this document is "<document>"
    And this active is "<active>"
    And Any Address
    And this district is "<district>"
    And this number is "<number>"
    And this city is "<city>"
    And this state is "<state>"
    And this zipCode is "<zipCode>"
    And this main is "<main>"
    When Client update a Legal Entity
    Then server must return a <httpStatusCode>

    Examples:
      |name|tradeName|document|active|district|number|city|state|zipCode|main|
      |    |         |        |      |        |      |    |     |       |    |
