Feature: The user executes a bad request

  Scenario Outline: The user executes a bad request
    Given The url is "<url>"
    And The Key is "<key>"
    And the individual
    And a name "<name>"
    And a motherName "<motherName>"
    And a document "<document>"
    And a rg "<rg>"
    And a birthDate "<birthDate>"
    And a active "<active>"
    And the address
    And a district "<district>"
    And a number "<number>"
    And a city "<city>"
    And a state "<state>"
    And a zipCode "<zipCode>"
    And a main "<main>"
    When user executes the update
    Then the server should return the <httpStatusCode>
    And the message "<message>"

    Examples:
      |     url   |  key |       name       |     motherName     |  document   |    rg     | birthDate  | active |      district      | number |             city           |   state   | zipCode  | main | httpStatusCode |                                 message                               |
      |      /    |  100 | Isabela Monteiro | Ana Luísa Monteiro | 05173661852 | 262567246 | 2000-08-17 |  true  | Rua 22 de setembro |   172  | Santa Rita do Passa Quatro | São Paulo | 98109932 | true |      404       |  This Individual wasn't found!                                        |
      |      /    |  12  | Isabela Monteiro | Ana Luísa Monteiro | 05173661851 | 262567246 | 2000-08-17 |  true  | Rua 22 de setembro |   172  | Santa Rita do Passa Quatro | São Paulo | 98109932 | true |      404       |  This Individual wasn't found!                                        |
      |      /    |  12  | Isabela Monteiro | Ana Luísa Monteiro | 05173661852 | 262567242 | 2000-08-17 |  true  | Rua 22 de setembro |   172  | Santa Rita do Passa Quatro | São Paulo | 98109932 | true |      404       |  This Individual wasn't found!                                        |
      |      /    |  12  | Isabela Monteiro | Ana Luísa Monteiro | 05173661852 | 262567246 | 2000-08-17 |  false | Rua 22 de setembro |   172  | Santa Rita do Passa Quatro | São Paulo | 98109932 | true |      404       |  This Individual wasn't found!                                        |
      |      /    |  12  | Isabela Monteiro | Ana Luísa Monteiro | 05173661852 | 262567246 | 2000-08-17 |  true  | Rua 22 de setembro |   172  | Santa Rita do Passa Quatro | São Paulo | 98109932 | false|      404       |  There are more than one main address or There isn't one main address!|
      | /document |      | Ricardo Braga    | Dairce Nadir       | 95186571140 | 418757895 | 1987-04-09 |  true  | Av. Paulista       |   39   | São Paulo                  | São Paulo | 17865110 | true |      404       |  This Individual wasn't found!                                        |
      | /document |      | Ricardo Braga    | Dairce Nadir       | 95186571141 | 418757894 | 1987-04-09 |  true  | Av. Paulista       |   39   | São Paulo                  | São Paulo | 17865110 | true |      404       |  This Individual wasn't found!                                        |
      | /document |      | Ricardo Braga    | Dairce Nadir       | 95186571141 | 418757895 | 1987-04-09 |  false | Av. Paulista       |   39   | São Paulo                  | São Paulo | 17865110 | true |      404       |  This Individual wasn't found!                                        |
      | /document |      | Ricardo Braga    | Dairce Nadir       | 95186571141 | 418757895 | 1987-04-09 |  true  | Av. Paulista       |   39   | São Paulo                  | São Paulo | 17865110 | false|      404       |  There are more than one main address or There isn't one main address!|




