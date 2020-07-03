Feature: Validating Rates API 

Scenario: Verify Rates API response returns success status code 
	Given Rates API for Latest Foreign Exchange rates 
	When The Rates API is available for "latest" 
	Then The Response is sucesssful with status code 200 
	
Scenario: Verify Rates API response with assertions 
	Given Rates API for Latest Foreign Exchange rates 
	When The Rates API is available for "latest" 
	Then Validate the response for base "EUR" 
	
Scenario Outline:Verify error is displayed when incorrect Rates API Url is submitted 
	Given Rates API for Latest Foreign Exchange rates 
	When An incorrect or incomplete Url <endpoint> is provided 
	Then Shows the <statcode> for incorrect or invalid url 
	Examples: 
		|endpoint|statcode|
		|"lates" |  400   |
		|"latest/345" |  404   |
		|"latest/ghf" |  404   |
		
Scenario: Verify Rates API for specific date returns success status code 
	Given Rates API for specific date Foreign Exchange rates 
	When The Rates API is available for "2019-12-05" 
	Then The Response is sucesssful with status code 200 
	
Scenario: Verify Rates API for specific date shows asserted response 
	Given Rates API for specific date Foreign Exchange rates 
	When The Rates API is available for "2020-01-05" 
	Then Validate the response for base "EUR" 
	
Scenario Outline: Verify response when future or incorrect date is submitted to API 
	Given Rates API for specific date Foreign Exchange rates 
	When An incorrect date <vardate> is provided 
	Then Shows the <statcode> for incorrect date
	Examples: 
		|vardate|statcode|
		|"2010-01-35" |  400   |
		|"2020-01-" |  400   |
		|"01-05-2019" |  400   |
		|"2020-10-15" |  200   |
		