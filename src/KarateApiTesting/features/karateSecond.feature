Feature: This Is First Feature For Karate API Testing.

  Background:
	Given url "https://reqres.in/"
	When header Accept = "application/json"

  @apple
  Scenario: First Karate Scenario
	Given path '/api/unknown'
	Then method GET
	Then status 200

  @apple
  Scenario: First Karate Scenario
	Given path '/api/users/23'
	Then method GET
	Then status 200