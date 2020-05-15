Feature: Delete Legal Entity with a wrong key

  Scenario Outline: Delete a Legal Entity with a wrong key
    Given The URL "<url>"
    And The KEY "<key>"
    And Verify if this Legal Entity exists with
    When The User makes a Delete
    Then The server must return a <statusCode>
    And The Message "<message>"

    Examples:
      |    url     |       key      | statusCode |            message              |
      |     /      |      3000      |     404    | This Legal Entity wasn't found! |
      |     /      |      3000      |     404    | This Legal Entity was already deleted! |









      | /document/ | 39838621000184 |     404    | This Legal Entity wasn't found! |