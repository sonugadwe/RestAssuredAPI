package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest1 {
	final static String ROOT_URI1= "http://restapi.demoqa.com/utilities/weather/city/Nagpur";

	@Test
	public void getWhetherDetails1() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest1= RestAssured.given();
		Response response1=httpRequest1.request(Method.GET, "/Nagpur");
		String responseBody1=response1.getBody().asString();
		System.out.println("response body is==>" +responseBody1);
	}
	@Test
	public void getWhetherDetatilsStatus1() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest1=RestAssured.given();
		Response response1 = httpRequest1.get("/Nagpur");
		int statusCode1 = response1.getStatusCode();
		Assert.assertEquals(statusCode1 , 200 , "Correct status code returned");
		
	}
	@Test
	public void WeatherMessageBody() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest1 = RestAssured.given();
		Response response1 = httpRequest1.get("/Nagpur");
		ResponseBody body1 = response1.getBody();
		String bodyAsString1 = body1.asString();
		Assert.assertEquals(bodyAsString1.contains("Nagpur"),true,"Response body contains Nagpur");
	}

	@Test
	public void VerifyCityInJsonResponse() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest1 = RestAssured.given();
		Response response1 = httpRequest1.get("/Nagpur");
		JsonPath jsonPathEvaluator1 = response1.jsonPath();
		String city1 = jsonPathEvaluator1.get("City");
		System.out.println("City received from Response " + city1);
		Assert.assertEquals(city1, "Nagpur", "Correct city name received in the Response");
	}

	@Test
	public void VerifyCityInJsonResponseJson() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest1 = RestAssured.given();
		Response response1 = httpRequest1.get("/Nagpur");
		JsonPath jsonPathEvaluator1 = response1.jsonPath();
		String city1 = jsonPathEvaluator1.get("City");
		System.out.println("City received from Response " + city1);
		Assert.assertEquals(city1, "Nagpur", "Correct city name received in the Response");
	}

	@Test
	public void GetWeatherDetailsInvalidCity() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest1 = RestAssured.given();
		Response response1 = httpRequest1.get("/78789798798");
		int statusCode1 = response1.getStatusCode();
		Assert.assertEquals(statusCode1 , 400, "Correct status code returned");
	}

}
