Feature: Find a individual with a invalided key

  Scenario Outline: Delete a individual
    Given the url "<url>"
    And the key: "<key>"
    When The user executes a Delete
    Then The server should return a statusCode <httpStatusCode>
    And a message "<message>"

    Examples:
      | httpStatusCode |    url      |     key    |                message               |
      |      404       |     /       |    100     | This Individual wasn't found!        |
      |      404       |  /document/ |40328944091 | This Individual wasn't found!        |
      |      404       |     /       |     3      | This Individual was already deleted! |
      |      404       |  /document/ |75884560192 | This Individual was already deleted! |