Feature: SerpApi Search API

  Scenario: Verify coffee search results

    Given user sets SerpApi base URL

    When user searches for "Coffee"

    Then response status code should be 200