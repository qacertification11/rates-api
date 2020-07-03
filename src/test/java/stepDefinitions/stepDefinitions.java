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
	RequestSpecification requestSpec;
	Response response;
	ValidatableResponse validatableResponse;

	public int statcode;
	public String endpoint, currency;

	public stepDefinitions() {
		RestAssured.baseURI = "https://api.ratesapi.io";
		RestAssured.basePath = "/api";
	}

	@Given("Rates API for Latest Foreign Exchange rates")
	public void rates_API_for_Latest_Foreign_Exchange_rates() {
		// requestSpec = RestAssured.given();
		response = given().when().get("/latest/");
		// given().when().get("https://api.ratesapi.io/api/latest/").then().log().body();
	}

	@When("The Rates API is available")
	public void the_Rates_API_is_available() {
		assertEquals(response.getStatusCode(), 200);
		// response.prettyPrint();

	}

	@Then("The Response is sucesssful with status code")
	public void the_Response_is_sucesssful_with_status_code() {
		System.out.println("........ The Rates API is live.....");

		response.then().log().body();

		System.out.println("# : The Response is successful with the Status code - 200");
		System.out.println("--------------------------------------------------------------");
		// response.prettyPrint();
	}

	@Then("Validate the response with assertions")
	public void validate_the_response_with_assertions() {

		// response = given().get("https://api.ratesapi.io/api/latest/");

		ResponseBody body = response.getBody();
		// Get Response Body as String
		String bodyStr = body.asString();
		assertTrue(bodyStr.contains("base"));
		JsonPath jsextractor = response.jsonPath();
		//extract specific element from JSON document
		String base = jsextractor.get("base");
		currency = "EUR";
		// check if the specific JSON element is equal to expected value
		assertTrue(base.equalsIgnoreCase(currency));
		response.then().log().all();

		System.out.println("# :Verified the Response with assertions shows base=" + currency);
		System.out.println("--------------------------------------------------------------");
	}

	@When("An incorrect or incomplete Url {string} is provided")
	public void an_incorrect_or_incomplete_url_something_is_provided(String strArg1) {

		//RestAssured.baseURI = "https://api.ratesapi.io";
		//RestAssured.basePath = "/api";
		endpoint = strArg1;
		response = given().get(strArg1);
		statcode = response.getStatusCode();
		// assertEquals(response.getStatusCode(), 404);

	}

	@Then("incorrect URI error is displayed")
	public void incorrect_URI_error_is_displayed() {

		if (statcode == 400) {
			given().when().get(endpoint).then().log().ifStatusCodeIsEqualTo(400);
			// System.out.println("Error: Requested URL is not found");
			System.out.println("Please provide the correct endpoint [example:/latest,/latest?base=USD]");

		} else if (statcode == 404) {
			given().when().get(endpoint).then().log().ifStatusCodeIsEqualTo(404);
			// System.out.println("\"error\": \"time data " + endpoint + " does not match
			// format '%Y-%m-%d'\"");
			System.out.println("Please provide the correct endpoint [example:/latest,/latest?base=USD]");

		}

		else {
			System.out.println("Requested Rates API URL is correct");
		}
		System.out.println("--------------------------------------------------------------");
		System.out.println("# : Verified the error message for incorrect endpoint in the Url");
		System.out.println("--------------------------------------------------------------");
	}

	
	
	@Given("Rates API for specific date Foreign Exchange rates")
	public void rates_API_for_specific_date_Foreign_Exchange_rates() {
		response = given().when().get("/2020-07-03");

		
	}

	@When("An incorrect date {string} is provided")
	public void an_incorrect_date_is_provided(String strArg2) {
		endpoint = strArg2;
		response = given().get(strArg2);
		statcode = response.getStatusCode();
	}

	@Then("incorrect date format error message is displayed")
	public void incorrect_date_format_error_message_is_displayed() {

		if (statcode == 400) {
			given().when().get(endpoint).then().log().ifStatusCodeIsEqualTo(400);
			System.out.println("Please enter the valid date for correct results");

		} else if (statcode == 404) {
			given().when().get(endpoint).then().log().ifStatusCodeIsEqualTo(404);
			System.out.println("Please enter the valid date for correct results");
		}

		else {
			given().when().get(endpoint).then().log().body();
			System.out.println("The Date spefied is either current / future date");
		}
		System.out.println("--------------------------------------------------------------");
		System.out.println("# : Verified the error message for incorrect date in the Url");
		System.out.println("--------------------------------------------------------------");
	
	}


	
	
	
	
	
} // end of class
