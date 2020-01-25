package com;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class WhetherTestCase1 {
	@Test
	void exampleRest2() {
		RestAssured.given().
		when().
		get("http://restapi.demoqa.com/utilities/weather/city/Nagpur").
		then()
		.statusCode(200).
		body("City",CoreMatchers. equalTo("Nagpur")).
		body("Humidity", equalTo("53 Percent"));
	}
}
