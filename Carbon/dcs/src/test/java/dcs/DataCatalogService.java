package dcs;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class DataCatalogService {
	@Test
	void ReadCatalog() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		
		Response response = (Response) request.get("https://st-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice");
		//ResponseBody body =response.getBody();
		
		//System.out.println("response body is"+response.asString());
		
		
		int statusCode = response.getStatusCode();
	
		JsonPath js = response.jsonPath();
		String value1 = js.get("values[0].key[0]");
		//String value1 = js.get("$.values.*.key.*~");
		Assert.assertEquals(value1.equals("sources"), true, "My key");
		Assert.assertEquals(statusCode, 200, "Correct status code");
	}
	
	@Test
	void ReadCatalogCountries() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		
		Response response = request.get("https://st-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/countries");
		//System.out.println("response body is"+response.asString());
		JsonPath js = response.jsonPath();
		String value1 = js.get("values[0].value[4]");
		Assert.assertEquals(value1.equals("Angola"), true, "My Country");
		System.out.println("My Country is "+value1);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct status code");
		}
	@Test
	void ReadCatalogCountriesPT() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		
		Response response = request.get("https://st-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/countries?partitionKey=EU&rowKey=PT");
		//System.out.println("response body is"+response.asString());
		JsonPath js = response.jsonPath();
		String value1 = js.get("values[0].value[4]");
		Assert.assertEquals(value1.equals("Portugal"), true, "My Country");
		System.out.println("My Country is "+value1);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct status code");
	}
	/*@Test
	void ReadCatalogCountriesCaseSensitive() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		
		Response response = request.get("https://st-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/countries?partitionKey=Eu&rowKey=Pt");
		//System.out.println("response body is"+response.asString());
		JsonPath js = response.jsonPath();
		String value1 = js.get("values[0].value[4]");
		Assert.assertEquals(value1.equals("Portugal"), true, "My Country");
		System.out.println("My Country is "+value1);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct status code");
		}
		*/
	
		
	@Test
	void ReadCatalogCountriesNotFound() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		Response response = request.get("https://st-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/countries?partitionKey=Eu&rowKey=NotFound");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 404, "Correct status code");
	}
	@Test
	void ReadCatalogTaxAutServices() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		Response response = request.get("https://dv-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/taxAuthorityServices?partitionKey=Pt");
		JsonPath js = response.jsonPath();
		String value1 = js.get("rowKey[0]");
		Assert.assertEquals(value1.equals("01$01"), true, "Correct rowkey");
		System.out.println("My RowKey is "+value1);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct status code");
	}
	@Test
	void ReadCatalogAddressCodesByDistrict() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		Response response = request.get("https://dv-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/addressCodes?partitionKey=PT$District");
		JsonPath js = response.jsonPath();
		String value1 = js.get("rowKey[0]");
		Assert.assertEquals(value1.equals("01"), true, "Correct rowkey");
		System.out.println("My RowKey is "+value1);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct status code");
	}
	@Test
	void ReadCatalogAddressCodesByDistrict01() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		Response response = request.get("https://dv-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/addressCodes?partitionKey=PT$District&rowKey=01");
		JsonPath js = response.jsonPath();
		String value1 = js.get("values[0].value[0]");
		Assert.assertEquals(value1.equals("AVEIRO"), true, "Correct District");
		System.out.println("My District is "+value1);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct status code");
	}
	@Test
	void ReadCatalogAddressCodesByDistrictNotFound() {
		
		String token = GetAccessToken.get_cds_token();
		
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		Response response = request.get("https://dv-dcs.lithium.primaverabss.com/api/v1.0/datacatalogservice/addressCodes?partitionKey=PT$District&rowKey=NotFound");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 404, "Correct status code");
	}
	
}


