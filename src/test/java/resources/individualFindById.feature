Feature: Find a individual by id

  Scenario Outline: Find a individual by id
    Given The url "<url>"
    And The key: "<id>"
    When User executes a Get
    Then The server should return a Individual
    And The statusCode is <httpStatusCode>
    And Name is "<name>"
    And MotherName is "<motherName>"
    And Document is "<document>"
    And Rg is "<rg>"
    And BirthDate is "<birthDate>"
    And Active is "<active>"
    And District is "<district>"
    And Number is "<number>"
    And City is "<city>"
    And State is "<state>"
    And ZipCode is "<zipCode>"
    And Main is "<main>"

    Examples:
      | httpStatusCode |id |    url    |         name        |     motherName                |  document   |    rg     | birthDate  | active| district         | number | city                      | state     | zipCode | main |    message  |
      |      200       |1  | / |Alice Ribeiro Braga  |Angélica Ribeiro de Paula Braga| 95186571148 | 418757896 | 2003-02-05 | true  |Rua Antônio Garcia| 301    | Santa Rita do Passa Quatro| São Paulo | 13670000| true |      ok     |
#      |      400       |100|Alice Ribeiro Braga  |Angélica Ribeiro de Paula Braga| 95186571148 | 418757896 | 2003-02-05 | true  |Rua Antônio Garcia| 301    | Santa Rita do Passa Quatro| São Paulo | 13670000| true | Invalided id|
#      |      400       |100|         null        |             null              |     null    |   null    |   null     | null  |      null        |  null  |            null           |   null    |   null  | null |             |