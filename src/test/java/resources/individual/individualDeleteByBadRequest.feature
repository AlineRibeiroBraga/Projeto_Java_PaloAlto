Feature: Find a individual with a invalided key

  Scenario Outline: Delete a individual
    Given the url "<url>"
    And the key: "<key>"
    When The user executes a Delete
    Then The server should return a statusCode <httpStatusCode>
    And a message "<message>"

    Examples:
      | httpStatusCode |    url      |     key    |                message               |        messages        |
      |      404       |     /       |    100     | This Individual wasn't found!        |                        |
      |      404       |  /document/ |40328944091 | This Individual wasn't found!        |                        |
      |      404       |     /       |     5      | This Individual was already deleted! | id: 5 -> active = false|
      |      404       |  /document/ |22278311115 | This Individual was already deleted! | id: 6 -> active = false|