Feature: Find a individual by document

  Scenario Outline: Find a individual by document
    Given The url "<url>"
    And The key: "<document>"
    When User executes a Get
    Then The server should return a Individual
    And The statusCode is <httpStatusCode>
    And Name is "<name>"
    And MotherName is "<motherName>"
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

      |     url    |  document   | httpStatusCode |         name        |     motherName                |    rg     | birthDate  | active| district               | number | city                      | state     | zipCode | main |       message      |
      | /document/ | 40328944098 |      200       |Aline Ribeiro Braga  |Angelica Ribeiro de Paula Braga| 911225341 | 2000-10-05 | false |Av. João Soares e Arruda| 1444   | Araraquara                | São Paulo | 14801790| true |          ok        |
#      | /document/ | 40328944098 |    400         |Alice Ribeiro Braga  |Angélica Ribeiro de Paula Braga| 911225341 | 2003-02-05 | false |Rua Antônio Garcia      | 301    | Santa Rita do Passa Quatro| São Paulo | 13670000| true | Invalided document |
#      | /document/ | 40328944098 |    400         |         null        |             null              |     null  |    null    |  null |        null            | null   |            null           |   null    |   null  | null | null |