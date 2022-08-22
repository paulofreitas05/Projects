package dcs;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAccessToken 
{
	public static String get_cds_token() {
		Response responseToMyRequest = 
		RestAssured.given().
		contentType("application/x-www-form-urlencoded; charset=utf-8").
			formParam("grant_type", "client_credentials").
			formParam("client_id", "lithium-datacatalogservice-clientcredentials").
			formParam("client_secret", "PwWkdMJsepZN8K+YMd6RSt8jSrsaQUS4pbZoJT0nj14+cjD0EcOBid8jE1kjvJHAT1zsexn6RuWN0LVgc7YrMA==").
		when().
			post("https://stg-identity.primaverabss.com/connect/token").
		then().
			statusCode(200).
			extract().response();
		
		
		String jsonString = responseToMyRequest.getBody().asString();
		String accessToken = JsonPath.from(jsonString).get("access_token");
		String bearer = "Bearer ".concat(accessToken);
		
		return bearer;
	}
}
