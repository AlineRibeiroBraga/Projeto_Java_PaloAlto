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
    Given Create a AndressDTO
    And The district is "<district>"
    And The number is "<number>"
    And The city is "<city>"
    And The state is "<state>"
    And The zipCode is "<zipCode>"
    And The main is "<main>"
    When User executes a Post
    Then the server should return a <httpStatusCode>

    Examples:
      |         name        |     motherName     |  document   |    rg     | birthDate  |district           | number| city                      | state     | zipCode | main | httpStatusCode|
      |Alice Ribeiro Braga  |Angélica Ribeiro    | 63352516987 | 231147041 | 2000-02-05 | Rua Antonio Garcia| 301   | Santa Rita do Passa Quatro| São Paulo | 02469125| true | 201           |
      |Aline Ribeiro Braga  |Angélica Ribeiro    | 63352516987 | 231147041 | 2000-05-10 | Rua Antonio Garcia| 31    | Porto Ferreira            | São Paulo | 19910222| true | 400           |
      |Albertina Ribeiro    |Arminda Ribeiro     | 90322337267 | 231147041 | 1983-04-12 | Rua Antonio Garcia| 32    | Osasco                    | São Paulo | 17206200| true | 400           |
      |Angélica Ribeiro     |Arminda Ribeiro     | 63352516987 | 386109746 | 1976-12-24 | Rua Antonio Garcia| 33    | Ribeirão Preto            | São Paulo | 17306200| true | 400           |
      |Arminda Ribeiro      |Maria Ribeiro       | 37200265829 | 277958714 | 1934-07-21 | Rua Antonio Garcia| 34    | Pirassunuga               | São Paulo | 17106200| false| 400           |



