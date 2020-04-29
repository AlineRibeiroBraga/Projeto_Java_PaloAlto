Feature: Individual
  How user enter with dates
  To register yourself

  Scenario Outline: Insert one Individual
    Given Create a IndividualDTO
    And The name is "<name>"
    And The motherName is "<motherName>"
    And The document is "<document>"
    And The rg id "<rg>"
    And The birthDate is "<birthDate>"
    And The active is active

    Examples:
      |         name        |     motherName     |  document   |    rg     | birthDate  |
      |Aline Ribeiro Braga  |Angélica Ribeiro    | 63352516987 | 231147041 | 2000-05-10 |

  Scenario Outline: Insert one address
    Given Create a AndressDTO
    And The district is "<district>"
    And The number is "<number>"
    And The city is "<city>"
    And The state is "<state>"
    And The zipCode is "<zipCode>"
    And The main is "<main>"
    When User executes a Post
    Then the server should return a "<httpStatusCode>"

    Examples:
      |district           | number| city                      | state     | zipCode | main |
      | Rua Antonio Garcia| 301   | Santa Rita do Passa Quatro| São Paulo | 02469125| true |
