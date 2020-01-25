package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {

	final static String ROOT_URI = "http://restapi.demoqa.com/utilities/weather/city/Pune";

	@Test
	public void GetWeatherDetails() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Pune");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}

	@Test
	public void GetWeatherDetailsStatus() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Pune");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode , 200 , "Correct status code returned");
	}

	@Test
	public void WeatherMessageBody() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Pune");
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Pune"),true,"Response body contains Pune");
	}

	@Test
	public void VerifyCityInJsonResponse() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Pune");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String city = jsonPathEvaluator.get("City");
		System.out.println("City received from Response " + city);
		Assert.assertEquals(city, "Pune", "Correct city name received in the Response");
	}

	@Test
	public void VerifyCityInJsonResponseJson() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Pune");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String city = jsonPathEvaluator.get("City");
		System.out.println("City received from Response " + city);
		Assert.assertEquals(city, "Pune", "Correct city name received in the Response");
	}

	@Test
	public void GetWeatherDetailsInvalidCity() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/78789798798");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode , 400, "Correct status code returned");
	}


}