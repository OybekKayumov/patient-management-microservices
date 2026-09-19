import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

	@BeforeAll
	static void setUp() {
		RestAssured.baseURI = "http://localhost:4004";
	}

	@Test
	public void shouldReturnOKWithValidToken() {

		//* 1. arrange - setup test
		//* 2. act     - code
		//* 3. assert  - result

		//! 1
		String loginPayload = """
							{
								"email": "testuser@test.com",
								"password": "password123"
							}
						""";

		//! 2
		Response response = given()
						.contentType("application/json")
						.body(loginPayload)
						.when()
						.post("/auth/login")
						.then()                                     //! 3
						.statusCode(200)
						.body("token", notNullValue())
						.extract()
						.response();

		System.out.println("Generated Token: " +
						response.jsonPath().getString("token"));

	}
}
