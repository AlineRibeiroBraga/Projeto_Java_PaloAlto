Feature: Update a Individual by document

  Scenario Outline: Update a Individual by document
    Given any individual
    And The key is "<document>"
    And A name "<name>"
    And A motherName "<motherName>"
    And A document "<document>"
    And A rg "<rg>"
    And A birthDate "<birthDate>"
    And A active "<active>"
    And any address
    And A district "<district>"
    And A number "<number>"
    And A city "<city>"
    And A state "<state>"
    And A zipCode "<zipCode>"
    And A main "<main>"
    When User executes the update by document
    Then Server should return a <httpStatusCode>
    And the document "<document>"

    Examples:
      |      name        |    motherName   |   document  |    rg     |  birthDate | active |  district    | number |  city  |    state     | zipCode  | main | httpStatusCode |        message        |
      | Vinicius fausto  | Gabriela Fausto | 94305391830 | 447132118 | 2001-02-21 | true   | Av. Portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Update the name       |
