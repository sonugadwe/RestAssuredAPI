
package com.test;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RESTAssuredJSONTests {

	/* For this testing JSON response be like this 
	 * {
		  "employees": [
		    {
		      "id": 1,
		      "name": "kiran",
		      "salary": "10000"
		    },
		    {
		      "name": "darshit",
		      "salary": "8000",
		      "id": 2
		    }
		  ]
		}*/
		
	final static String ROOT_URI = "http://localhost:7000/employees";

	@Test
	public void simple_get_test() {
		Response response = get(ROOT_URI + "/list");
		System.out.println(response.asString());

		response.then().body("id", hasItems(1, 2));
		response.then().body("name", hasItems("Kiran"));
	}

	@Test(dataProvider = "dpGetWithParam")
	public void get_with_param(int id, String name) {
		get(ROOT_URI + "/get/" + id).then().body("name", Matchers.is(name));
	}

	@DataProvider
	public Object[][] dpGetWithParam() {
		Object[][] testDatas = new Object[][] { 
			new Object[] { 1, "Kiran" },
			new Object[] { 2, "Darshit" } };
		return testDatas;
	}

	@Test
	public void post_test() {
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\"name\": \"Kiran\",\"salary\": \"2000\"}")
				.when()
				.post(ROOT_URI + "/create");
		System.out.println("POST Response\n" + response.asString());
		// tests
		response.then().body("id", Matchers.any(Integer.class));
		response.then().body("name", Matchers.is("Kiran"));
	}

	@Test
	public void put_test() {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\"name\": \"Kiran\",\"salary\": \"20000\"}")
				.when()
				.put(ROOT_URI + "/update/3");
		System.out.println("PUT Response\n" + response.asString());
		// tests
		response.then().body("id", Matchers.is(3));
		response.then().body("name", Matchers.is("Kiran"));
		response.then().body("salary", Matchers.is("20000"));
	}

	@Test
	public void delete_test() {
		Response response = delete(ROOT_URI + "/delete/3");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		// check if id=3 is deleted
		response = get(ROOT_URI + "/list");
		System.out.println(response.asString());
		response.then().body("id", Matchers.not(3));
	}
}