@starwarapitests
Feature: Starwar API tests
  The Star Wars API is a public API containing facts about the Star Wars universe.

  Scenario: Create Employee Records_<tc_id>
    Given I want to test SWAPI service
    When I do a GET request for SWAPI service
    Then I validate the response with statuscode is "200"

  @CRUDOperations
  Scenario Outline: Create Employee Records_<tc_id>
    Given I want to test SWAPI service
    When I do a request for SWAPI service "<request_type>"
    Then I validate the response with statuscode is "<expectedStatus>" and "<expectedValue>"

    Examples: 
      | request_type | expectedStatus | expectedValue                |
      | GET          |            200 |                              |
      | PUT          |            405 | Method 'PUT' not allowed.    |
      | POST         |            405 | Method 'POST' not allowed.   |
      | DELETE       |            405 | Method 'DELETE' not allowed. |

  @tagStarships
  Scenario Outline: Validate the response for starships
    Given I want to test SWAPI service
    When I do GET request for star ships endpoint
    Then I validate the response with status code "<expectedStatus>"
    And I validate the response with expected values "<name>" and "<crew_count>"

    Examples: 
      | tc_id | expectedStatus | name       | crew_count |
      | TC_01 |            200 | Death Star |    342,953 |

  @tagPeople
  Scenario Outline: Validate the response for people endpoint
    Given I want to test SWAPI service
    When I do GET request for people endpoint
    Then I validate the response with status code "<expectedStatus>"
    And I validate the response with expected values "<name>" "<skin_color>" and "<films>"

    Examples: 
      | tc_id | expectedStatus | name  | skin_color  | films                                                                                                                 |
      | TC_01 |            200 | R2-D2 | white, blue | A New Hope, The Empire Strikes Back, Return of the Jedi, The Phantom Menace, Attack of the Clones, Return of the Sith |

  @tagSpecies
  Scenario Outline: Validate the response for species
    Given I want to test SWAPI service
    When I do GET request for species endpoint
    Then I validate the response with status code "<expectedStatus>"
    And I validate the species response with expected values "<name>" "<classification>" and "<homeworld>"

    Examples: 
      | tc_id | expectedStatus | name   | classification | homeworld |
      | TC_01 |            200 | Wookie | mammal         | Kashyyyk  |

  @wookieFormat
  Scenario Outline: Validate the response for planes api in wookie format
    Given I want to test SWAPI service
    When I do GET request for planets endpoint in wookie format
    Then I validate the response with status code "<expectedStatus>"

    Examples: 
      | tc_id | expectedStatus |
      | TC_01 |            200 |
