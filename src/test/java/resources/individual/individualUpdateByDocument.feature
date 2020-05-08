Feature: Update a Individual by document

  Scenario Outline: Update a Individual by document
    Given any individual
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
      |      name      |    motherName   |   document  |    rg     |  birthDate | active |        district    | number |  city  |      state     | zipCode  | main | httpStatusCode |        message        |
      | Gabriel Santos | Gabriela Santos | 55686887454 | 276043017 | 2000-05-29 | true   | Av. Espanha        | 1333   | Guarujá| São Paulo      | 98185430 | true |      200       | Update the name       |
      | Gabriel Santos | Gabriela santos | 55686887454 | 276043017 | 2000-05-29 | true   | Av. Espanha        | 1333   | Guarujá| São Paulo      | 98185430 | true |      200       | Update the motherName |
      | Gabriel Santos | Gabriela santos | 55686887454 | 276043017 | 2000-04-04 | true   | Av. Espanha        | 1333   | Guarujá| São Paulo      | 98185430 | true |      200       | Update the birthDate  |
      | Gabriel Santos | Gabriela santos | 55686887454 | 276043017 | 2000-04-04 | true   | Av. 15 de Novembro | 1333   | Guarujá| São Paulo      | 98185430 | true |      200       | Update the district   |
      | Gabriel Santos | Gabriela santos | 55686887454 | 276043017 | 2000-04-04 | true   | Av. 15 de Novembro | 1432   | Guarujá| São Paulo      | 98185430 | true |      200       | Update the number     |
      | Gabriel Santos | Gabriela santos | 55686887454 | 276043017 | 2000-04-04 | true   | Av. 15 de Novembro | 1432   | Santos | São Paulo      | 98185430 | true |      200       | Update the city       |
      | Gabriel Santos | Gabriela santos | 55686887454 | 276043017 | 2000-04-04 | true   | Av. 15 de Novembro | 1432   | Santos | Espírito Santo | 98185430 | true |      200       | Update the state      |
      | Gabriel Santos | Gabriela santos | 55686887454 | 276043017 | 2000-04-04 | true   | Av. 15 de Novembro | 1432   | Santos | Espírito Santo | 98111430 | true |      200       | Update the zipCode    |