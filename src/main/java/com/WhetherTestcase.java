package com;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class WhetherTestcase {

	@Test
	 void exampleRest() {
		RestAssured.given().
		when().
		get("http://restapi.demoqa.com/utilities/weather/city/Pune").
		then()
		.statusCode(200).
		body("City",CoreMatchers. equalTo("Pune")).
		body("Humidity", equalTo("23 Percent"));
	}
	
}