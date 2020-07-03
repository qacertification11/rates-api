Feature: Validating Rates API 

Scenario: Verify Rates API response returns success status code 
	Given Rates API for Latest Foreign Exchange rates 
	When The Rates API is available 
	Then The Response is sucesssful with status code 
	
Scenario: Verify Rates API response with assertions 
	Given Rates API for Latest Foreign Exchange rates 
	When The Rates API is available 
	Then Validate the response with assertions 
	
Scenario: Verify error is displayed when incorrect Rates API Url is submitted 
	Given Rates API for Latest Foreign Exchange rates 
	When An incorrect or incomplete Url "/lat" is provided 
	Then incorrect URI error is displayed 

	
Scenario: Verify Rates API for specific date returns success status code 
	Given Rates API for specific date Foreign Exchange rates 
	When The Rates API is available 
	Then The Response is sucesssful with status code 
	
Scenario: Verify Rates API for specific date shows asserted response 
	Given Rates API for specific date Foreign Exchange rates 
	When The Rates API is available 
	Then Validate the response with assertions 
	
Scenario: Verify response when future or incorrect date is submitted to API 
	Given Rates API for specific date Foreign Exchange rates 
	When An incorrect date "/2021-01-01" is provided 
	Then incorrect date format error message is displayed 
	