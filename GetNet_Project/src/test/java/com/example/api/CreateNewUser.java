package com.example.api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateNewUser {

	String id;

	@Test
	public void PostRequest() {

		RestAssured.baseURI = "https://reqres.in/api/users";

		String requestBody = "{\n" + 
		" \"name\": \"morpheus\",\n" + 
		" \"job\": \"leader\"\n" + 
		"}";

		Response response = null;

		try {
			response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("/create");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Response : " + response.asString());
		System.out.println("Status Code : " + response.getStatusCode());
		System.out.println("Contais 'morpheus'? :" + response.asString().contains("morpheus"));

		assertEquals(201, response.getStatusCode());

		id = response.getBody().asString();

		JsonPath jsonPath = new JsonPath(id);
		String user_id = jsonPath.getString("id");
		
		System.out.println(user_id);

	}

}
