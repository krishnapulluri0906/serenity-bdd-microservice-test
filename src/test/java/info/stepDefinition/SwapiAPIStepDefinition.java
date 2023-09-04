package info.stepDefinition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import info.reusables.customEnsure;
import info.stepImplementation.SwapiAPIImplementation;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SwapiAPIStepDefinition {

	SwapiAPIImplementation swapImpl = new SwapiAPIImplementation();
	Response response;

	@Given("I want to test SWAPI service")
	public void i_want_to_test_SWAPI_service() {
		System.out.println("==========STAR WAR API TESTS================");

	}

	@When("I do a request for SWAPI species service {string}")
	public void i_do_a_request_for_SWAPI_service(String request_type) {
		if (request_type.equals("GET")) {
			response = swapImpl.getStarWarsServiceCall();

		} else if (request_type.equals("POST")) {
			response = swapImpl.postStarWarsServiceCall();

		} else if (request_type.equals("PUT")) {
			response = swapImpl.putStarWarsServiceCall();
		} else if (request_type.equals("DELETE")) {
			response = swapImpl.deleteStarWarsServiceCall();
		} else {
			customEnsure.customLogWithoutScreenShot("request type doesn't match the condition", "fail");
		}

	}

	@Then("I validate the response with statuscode is {string} and {string}")
	public void i_validate_the_response_with_statuscode_is_and(String expectedStatus, String expectedValue) {
		Assert.assertEquals(expectedStatus, String.valueOf(response.getStatusCode()));
	}

	@When("I do a GET request for SWAPI service")
	public void i_do_a_GET_request_for_SWAPI_service() {
		response = swapImpl.getPeopleServiceCall();
		System.out.println(response.prettyPrint());

	}

	@Then("I validate the response with statuscode is {string}")
	public void i_validate_the_response_with_statuscode_is(String responseCode) {
		Assert.assertEquals(responseCode, response.getStatusCode());

	}

	@When("I do GET request for star ships endpoint")
	public void i_do_GET_request_for_star_ships_endpoint() {
		response = swapImpl.getStarShipsServiceCall();

	}

	@Then("I validate the response with status code {string}")
	public void i_validate_the_response_with_status_code(String responseCode) {
		System.out.println(response.prettyPrint());
		Assert.assertEquals(responseCode, String.valueOf(response.getStatusCode()));

	}

	@Then("I validate the response with expected values {string} and {string}")
	public void i_validate_the_response_with_expected_values_and(String name, String crew_count) {
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(name, jsonPath.getString("name"));
		Assert.assertEquals(crew_count, jsonPath.getString("crew"));

	}

	@When("I do GET request for people endpoint")
	public void i_do_GET_request_for_people_endpoint() {
		response = swapImpl.getPeopleServiceCall();

	}

	@Then("I validate the response with expected values {string} {string} and {string}")
	public void i_validate_the_response_with_expected_values_and(String name, String skin_color, String expectedfilms) {
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(name, jsonPath.getString("name"));
		Assert.assertEquals(skin_color, jsonPath.getString("skin_color"));
		List<String> filmsList = new ArrayList<String>();
		filmsList = jsonPath.getList("films");
		List<String> filmsNamesFromResponse = new ArrayList<String>();
		String filmsEndpointFromResponse;

		for (int i = 0; i < filmsList.size(); i++) {
			filmsEndpointFromResponse = filmsList.get(i).toString();
			System.out.println(filmsEndpointFromResponse);
			Response objResponse = swapImpl.getFilmServiceCall(filmsEndpointFromResponse);
			JsonPath jsonPathFilms = objResponse.jsonPath();
			filmsNamesFromResponse.add(jsonPathFilms.getString("title"));
		}

		Assert.assertEquals(expectedfilms, filmsNamesFromResponse.toString());

	}

	@When("I do GET request for species endpoint")
	public void i_do_GET_request_for_species_endpoint() {
		response = swapImpl.getSpeciesServiceCall();

	}

	@Then("I validate the species response with expected values {string} {string} and {string}")
	public void i_validate_the_species_response_with_expected_values_and(String name, String classification,
			String homeworld) {
		JsonPath jsonPath = response.jsonPath();
		String actualHomeWorldValue = jsonPath.getString("homeworld");
		Response responsePlanetCall = swapImpl.getPlanetServiceCall(actualHomeWorldValue);
		Assert.assertEquals(name, jsonPath.getString("name"));
		Assert.assertEquals(classification, jsonPath.getString("classification"));
		Assert.assertEquals(homeworld, responsePlanetCall.jsonPath().getString("name"));
	}

	@When("I do GET request for planets endpoint in wookie format")
	public void i_do_GET_request_for_planets_endpoint_in_wookie_format() {
		response = swapImpl.getPlanetServiceCall();

	}

}
