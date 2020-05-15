Feature: Find a Legal Entity by key

  Scenario Outline: Find a Legal Entity by key
    Given a url is "<url>"
    And a key is "<key>"
    And Verify if this legal entity is registered "<name>","<tradeName>","<document>","<active>","<district>","<number>","<city>","<state>","<zipCode>","<main>","<nameI>","<documentI>","<rgI>","<motherNameI>", "<birthDateI>", "<activeI>", "<districtI>","<numberI>","<cityI>","<stateI>","<zipCodeI>","<mainI>"
    When the user executes a get
    Then the server should return a Legal Entity
    And any name "<name>"
    And any trade name "<tradeName>"
    And any document "<document>"
    And any active "<active>"
    And this address
    And any district's Legal Entity is "<district>"
    And any number's Legal Entity is "<number>"
    And any city's Legal Entity is "<city>"
    And any state's Legal Entity is "<state>"
    And any zipCode's Legal Entity is "<zipCode>"
    And any main's Legal Entity is "<main>"
    And this Individual
    And this name "<nameI>"
    And this document "<documentI>"
    And this rg "<rgI>"
    And this motherName "<motherNameI>"
    And this birthDate "<birthDateI>"
    And this active "<activeI>"
    And Address
    And this district "<districtI>"
    And this number "<numberI>"
    And this city "<cityI>"
    And this state "<stateI>"
    And this zipCode "<zipCodeI>"
    And this main "<mainI>"
    And this statusCode <httpStatusCode>

    Examples:
      |     url    |       key      |httpStatusCode|               name                     |       tradeName      |    document    | active |       district       | number |            city          |   state   | zipCode  | main |     nameI       |  documentI  |     rgI   |   motherNameI   | birthDateI | activeI |       districtI    | numberI |    cityI     |     stateI   | zipCodeI | mainI |
      |      /     |        5       |     200      | Supra Tec Informática - Soluções em TI | STI                  | 70237208000104 | true   | R. Dr. Brito Pereira | 689    | Santa Cruz das Palmeiras | São Paulo | 13650000 | true | Giovana Piza    | 72814747410 | 463988474 | Beatriz Piza    | 1998-07-23 | true    | Rua 15 de novembro | 421     | Pirassununga | São Paulo    | 17180109 | true  |
      |      /     |       100      |     200      | Kabum                                  | O melhor para você   | 15532051000131 | true   | Rua Vereador Souza   | 679    | Santos                   | São Paulo | 13650011 | true | Giovana Piza    | 72814747410 | 463988474 | Beatriz Piza    | 1998-07-23 | true    | Rua 15 de novembro | 421     | Pirassununga | São Paulo    | 17180109 | true  |
      | /document/ | 42267387000197 |     200      | HELP TI                                | HELP                 | 42267387000197 | true   | Av. J Bento Ferreira | 313    | Tambaú                   | São Paulo | 13710000 | true | Vinicius fausto | 94305391830 | 447132118 | Gabriela fausto | 2001-02-21 | true    | Av. portugal       | 879     | Bauru        | Minas Gerais | 98180100 | true  |
      | /document/ | 82468760000192 |     200      | Portal informática                     | Conforto e segurança | 82468760000192 | true   | Rua Travessa Vitória | 310    | Porto Ferreira           | São Paulo | 13719900 | true | Vinicius fausto | 94305391830 | 447132118 | Gabriela fausto | 2001-02-21 | true    | Av. portugal       | 879     | Bauru        | Minas Gerais | 98180100 | true  |

















