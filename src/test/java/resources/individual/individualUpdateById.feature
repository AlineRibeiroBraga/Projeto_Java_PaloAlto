Feature: Update a Individual by Id

  Scenario Outline: Update a Individual by id
    Given Any individual
    And the key is "<id>"
    And the name is "<name>"
    And the motherName is "<motherName>"
    And the document is "<document>"
    And the rg id "<rg>"
    And the birthDate is "<birthDate>"
    And the active is "<active>"
    And Any address
    And the district is "<district>"
    And the number is "<number>"
    And the city is "<city>"
    And the state is "<state>"
    And the zipCode is "<zipCode>"
    And the main is "<main>"
    When User executes the update by id
    Then the server should return a <httpStatusCode>
    And the id "<id>"

    Examples:
      | id |      name        |    motherName   |   document  |    rg     |  birthDate | active |  district    | number |  city  |    state     | zipCode  | main | httpStatusCode |        message        |
      | 10 | Vinicius fausto  | Gabriela Fausto | 94305391830 | 447132118 | 2001-02-21 | true   | Av. Portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Update the name       |
#      | 10 | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-02-21 | true   | Av. Portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Update the motherName |
#      | 10 | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. Portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Update the birthDate  |
#      | 10 | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Update the district   |
#      | 10 | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Osasco | São Paulo    | 98180100 | true |      200       | Update the number     |
#      | 10 | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Bauru  | São Paulo    | 98180100 | true |      200       | Update the city       |
#      | 10 | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Bauru  | Minas Gerais | 98180100 | true |      200       | Update the state      |
#      | 10 | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Bauru  | Minas Gerais | 78180100 | true |      200       | Update the zipCode    |

#  Scenario Outline: Find a individual by id
#    Given The url "<url>"
#    And The key: "<key>"
#    When User executes a Get
#    Then The server should return a Individual
#    And The statusCode is <httpStatusCode>
#    And Document is "<document>"
#    And Name is "<name>"
#    And MotherName is "<motherName>"
#    And Rg is "<rg>"
#    And BirthDate is "<birthDate>"
#    And Active is "<active>"
#    And District is "<district>"
#    And Number is "<number>"
#    And City is "<city>"
#    And State is "<state>"
#    And ZipCode is "<zipCode>"
#    And Main is "<main>"
#
#    Examples:
#      | url | key |       name       |    motherName   |  document   |    rg     |  birthDate | active |  district    | number |  city  |   state      |  zipCode | main | httpStatusCode |     message           |
#      |  /  | 10  | Vinicius fausto  | Gabriela Fausto | 94305391830 | 447132118 | 2001-02-21 | true   | Av. Portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Verify the name       |
#      |  /  | 10  | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-02-21 | true   | Av. Portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Verify the motherName |
#      |  /  | 10  | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. Portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Verify the birthDate  |
#      |  /  | 10  | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 899    | Osasco | São Paulo    | 98180100 | true |      200       | Verify the district   |
#      |  /  | 10  | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Osasco | São Paulo    | 98180100 | true |      200       | Verify the number     |
#      |  /  | 10  | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Bauru  | São Paulo    | 98180100 | true |      200       | Verify the city       |
#      |  /  | 10  | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Bauru  | Minas Gerais | 98180100 | true |      200       | Verify the state      |
#      |  /  | 10  | Vinicius fausto  | Gabriela fausto | 94305391830 | 447132118 | 2001-03-11 | true   | Av. portugal | 879    | Bauru  | Minas Gerais | 78180100 | true |      200       | Verify the zipCode    |