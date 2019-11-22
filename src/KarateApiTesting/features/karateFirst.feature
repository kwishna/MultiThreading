Feature: This Is First Feature For Karate API Testing.

  Background:
	Given url "https://reqres.in/"
	When header Accept = "application/json"

  @apple
  Scenario: First Karate Scenario
	Given path 'api/users/'
	And params '/page=2'
	Then method GET
	Then status 200

  @boys
  Scenario: First Karate Scenario
	Given path 'api/users/'
	And params '/page=2'
	Then method GET
	Then status 200

