package info.stepImplementation;

import info.reusables.RESTOperations;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class SwapiAPIImplementation extends RESTOperations {

	protected final String BASE_URL = "https://swapi.dev/api";
	protected final String FILMS = "/films";
	protected final String PLANETS = "/planets";
	protected final String PEOPLE = "/people";
	protected final String VEHICLES = "/vehicles";
	protected final String STAR_SHIPS = "/starships";
	protected final String SPECIES = "/species";
	Response response;

	public Response getPeopleServiceCall() {
		return response = getService(BASE_URL + PEOPLE + "/3");

	}

	public Response getStarShipsServiceCall() {
		return response = getService(BASE_URL + STAR_SHIPS + "/9");

	}

	public Response getFilmServiceCall(String filmsEndpointFromResponse) {
		return response = getService(filmsEndpointFromResponse);

	}

	public Response getSpeciesServiceCall() {
		return response = getService(BASE_URL + SPECIES + "/3");
	}

	public Response getPlanetServiceCall(String actualHomeWorldValue) {
		return response = getService(actualHomeWorldValue);
	}

	public Response getPlanetServiceCall() {
		return response = getServiceWithPathParams("wookiee", BASE_URL + PLANETS + "/14/");

	}

	public Response getStarWarsServiceCall() {
		return response = getStarWarsService(BASE_URL + SPECIES + "/3");

	}

	public Response postStarWarsServiceCall() {
		return response = postStarWarsService(BASE_URL + SPECIES + "/3");

	}

	public Response putStarWarsServiceCall() {
		return response = putStarWarsService(BASE_URL + SPECIES + "/3");

	}

	public Response deleteStarWarsServiceCall() {
		return response = deleteStarWarsService(BASE_URL + SPECIES + "/3");

	}
}
