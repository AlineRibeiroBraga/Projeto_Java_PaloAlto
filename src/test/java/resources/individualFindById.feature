#Feature: Find a individual by id
#
#  Scenario Outline: Find a individual
#    Given The <id>
#    When User executes a Get
#    Then The server should return a Individual
#    And The statusCode is <httpStatusCode>
#    And The name is "<name>"
#    And The motherName is "<motherName>"
#    And The document is "<document>"
#    And The rg id "<rg>"
#    And The birthDate is "<birthDate>"
#    And The active is "<active>"
#    And The district is "<district>"
#    And The number is "<number>"
#    And The city is "<city>"
#    And The state is "<state>"
#    And The zipCode is "<zipCode>"
#    And The main is "<main>"
#
#    Examples:
#
#      | httpStatusCode |id  |         name        |     motherName                |  document   |    rg     | birthDate  | active| district         | number | city                      | state     | zipCode | main |
##      |      200       |1   |Alice Ribeiro Braga  |Angélica Ribeiro de Paula Braga| 95186571148 | 418757896 | 2003-02-05 | true  |Rua Antônio Garcia| 301    | Santa Rita do Passa Quatro| São Paulo | 13670000| true |
##      |      400       |100   |Alice Ribeiro Braga  |Angélica Ribeiro de Paula Braga| 95186571148 | 418757896 | 2003-02-05 | true  |Rua Antônio Garcia| 301    | Santa Rita do Passa Quatro| São Paulo | 13670000| true |
##      |      400       |100 |         null        |             null              |     null    |   null    |   null     | null  |      null        |  null  |            null           |   null    |   null  | null |