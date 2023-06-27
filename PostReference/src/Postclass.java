import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class Postclass {

	public static void main(String[] args) {
	//step 1 : declare base URL and request body
		String baseURI="https://restful-api.dev/";
	RestAssured.baseURI=baseURI;
	
	
	String requestBody="{\r\n"
			+ "   \"name\": \"Apple MacBook Pro 16\",\r\n"
			+ "   \"data\": {\r\n"
			+ "      \"year\": 2019,\r\n"
			+ "      \"price\": 1849.99,\r\n"
			+ "      \"CPU model\": \"Intel Core i9\",\r\n"
			+ "      \"Hard disk size\": \"1 TB\"\r\n"
			+ "   }\r\n"
			+ "}";
	
	//step 2 : validate status code and response body
	int statusCode=given().header("Content-Type","application/json").body(requestBody)
			              .when().post("https://api.restful-api.dev/objects")
			              .then().extract().statusCode();
	
	System.out.println(statusCode);
	
	String responseBody=given().header("Content-Type","application/json").body(requestBody)
            .when().post("https://api.restful-api.dev/objects")
            .then().extract().response().asString();
	
	System.out.println(responseBody);
	
	//step 3 : parse response body
	JsonPath jsp=new JsonPath(responseBody);
	String res_id=jsp.getString("id");
	String res_name=jsp.getString("name");
	String res_createdAt=jsp.getString("createdAt");
	String res_year=jsp.getString("year");
	String res_price=jsp.getString("price");
	String res_CPUmodel=jsp.getString("CPUmodel");
	String res_Harddisksize=jsp.getString("Harddisksize");
	
	JsonPath jsprequest=new JsonPath(requestBody);
	String req_id=jsprequest.getString("id");
	String req_name=jsprequest.getString("name");
	String req_createdAt=jsprequest.getString("createdAt");
	String req_year=jsprequest.getString("year");
	String req_price=jsprequest.getString("price");
	String req_CPUmodel=jsprequest.getString("CPUmodel");
	String req_Harddisksize=jsprequest.getString("Harddisksize");
	
	//step 4: validate response body
	Assert.assertNotNull(res_id);
    Assert.assertEquals(res_name, req_name);
    Assert.assertNotNull(res_createdAt);
    Assert.assertEquals(res_year, req_year);
    Assert.assertEquals(res_price,req_price);
    Assert.assertEquals(res_CPUmodel,req_CPUmodel);
    Assert.assertEquals(res_Harddisksize,req_Harddisksize);

	}

}
