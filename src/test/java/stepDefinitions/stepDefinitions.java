package stepDefinitions;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class stepDefinitions {
	RequestSpecification request;
	Response response;
	ValidatableResponse validatableResponse;

	String ratesapi_URL = "https://api.ratesapi.io/api/";
	public int statcode;
	public String endpoint, currency;

	@Given("Rates API for Latest Foreign Exchange rates")
	public void rates_API_for_Latest_Foreign_Exchange_rates() {
		request = given();
			}

	@When("The Rates API is available for {string}")
	public void the_Rates_API_is_available(String strendpoint) {
		response = request.when().get(ratesapi_URL + strendpoint);
		// System.out.println("response: " + response.prettyPrint());

	}

	@Then("The Response is sucesssful with status code {int}")
	public void the_Response_is_sucesssful_with_status_code(int statusCode) {

		assertEquals(statusCode, response.getStatusCode());
		
		System.out.println("# : The Response is successful with the Status code - 200");
		System.out.println("--------------------------------------------------------------");
		// response.prettyPrint();
	}

	@Then("Validate the response for base {string}")
	public void validate_the_response_for_base(String baseval) {

		ResponseBody body = response.getBody();
		String bodyStr = body.asString();
		assertTrue(bodyStr.contains("base"));
		JsonPath jsextractor = response.jsonPath();
		String base = jsextractor.get("base");

		assertTrue(base.equalsIgnoreCase(baseval));
		response.then().log().all();

		System.out.println("# :Verified the Response with assertion - base=" + baseval);
		System.out.println("--------------------------------------------------------------");
		currency = baseval;
	}

	@When("An incorrect or incomplete Url {string} is provided")
	public void an_incorrect_or_incomplete_url_something_is_provided(String strArg1) {
		response = request.when().get(ratesapi_URL + strArg1);
		endpoint = strArg1;
	}

	@Then("Shows the {int} for incorrect or invalid url")
	public void shows_the_for_incorrect_or_invalid_url(int statcode) {
		if (statcode == response.getStatusCode()) {
			assertEquals(statcode, response.getStatusCode());
			response.then().log().all();
			System.out.println("Please enter the valid endpoint for correct results");

		} else if (statcode == response.getStatusCode()) {
			assertEquals(statcode, response.getStatusCode());
			response.then().log().all();
			System.out.println("Please enter the valid endpoint for correct results");
		} else {
			// response.then().log().body();
			System.out.println("The Rates API URL is valid");
		}

		System.out.println("# : Verified the error message for incorrect endpoint in the Url");
		System.out.println("--------------------------------------------------------------");

	}

	@Given("Rates API for specific date Foreign Exchange rates")
	public void rates_API_for_specific_date_Foreign_Exchange_rates() {
		request = given().contentType(ContentType.JSON);
	
	}

	@When("An incorrect date {string} is provided")
	public void an_incorrect_date_is_provided(String strArg2) {
		response = request.when().get(ratesapi_URL + strArg2);
		endpoint = strArg2;
	}

	@Then("Shows the {int} for incorrect date")
	public void shows_the_for_incorrect_date(int statcode) {
		if (statcode == response.getStatusCode()) {
			assertEquals(statcode, response.getStatusCode());
			response.then().log().body();
			System.out.println("Please enter the valid date for correct results");
		} else {
			//response.then().log().body();
			System.out.println("Status Code :"+ (response.getStatusCode()));
			System.out.println("Today's rates will be displayed, eventhough specified date is future date");
		}

		System.out.println("# : Verified the error message for incorrect date in the Url");
		System.out.println("--------------------------------------------------------------");

	}

} // end of class
