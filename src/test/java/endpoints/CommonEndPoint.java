package endpoints;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.testng.Assert;
import org.testng.log4testng.Logger;
import org.utilities.JsonUtilities;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This Base class For End  Points and it have Defined Common Functionality For :
 *  -- Performing Step based Request with given parameters
 *  -- Validate Response and Status code with given parameters 
 * 
 * @author Admin
 *
 */
public class CommonEndPoint {

	public static final int SUCCESSFUL_GET_RESPONSE_CODE = 200;
	
	protected static final String BASE_URL="https://jsonplaceholder.typicode.com";
	
	private Map<String,  String> paramMap = null;
	
	private String receivedMethodType = null;
	
	//Empty Constructor
	public void CommonEndPoint() {
		
	}
	
	/**
	 * This Method will be called with three parameters to make the respective API call based on given:
	 *  - MethodType -- GET | POST | DELETE 
	 *  - EndUrl     -- The End Url of given Resource
	 * @param endUrl Application URL 
	 * @param table DataTable object containing the input data parameters.   
	 */
	public Response makeApiCall(String endUrl, DataTable table) {
		Response response = null;
		System.out.println("End Point URL is >> " + endUrl);
		receivedMethodType = getRequestMethodType(table);
		try {
			switch (receivedMethodType) {
			case "POST":
				response = performPostCall(endUrl);
				break;
			case "GET":
				response = performGetCall(endUrl);
				break;
			case "PATCH":
				break;
			case "DELETE":
				break;
			default:
				Assert.assertFalse(false, "NO Request available to execute");

			}
		} catch (Exception e) {
			System.out.println("makeApiCall.. Exception Occured.. >>> " + e.getLocalizedMessage());
			Assert.assertTrue(false, "API call Failed.. Got Exception");
		}
		
		
		return response;
	}

	
	
	/**
	 * This Method is to validate the received response with provided Value from  Then Clause in Scenario
	 * @param response The Response received from API
	 * @param table DataTable Object Contains the verification related values from given Scenario.
	 */
	public void verifyResponse(Response response, DataTable table) {
		
		Map<String, String> givenData = null;
		List<String> givenKeys = null;
		try {
			if(table != null && !table.isEmpty()) {
				givenKeys = new ArrayList<String>();
				givenData = table.asMap(String.class, String.class);
				Iterator itr = givenData.keySet().iterator();
				while (itr.hasNext()) {
					givenKeys.add((String) itr.next());
				}
				for (String tempKey : givenKeys) {
					if ("statusCode".equalsIgnoreCase(tempKey) || "recordCount".equalsIgnoreCase(tempKey)) {
						performMetadataChecks(tempKey, givenData, response);	
					} else {
						validateGivenKeyAndValueInResponse(tempKey, givenData.get(tempKey), response);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("verifyResponse() Method.. Exception Occured.. >>> " + e.getMessage());
			Assert.assertTrue(false, "Response Is Not valid.. Got Exception");
		}
		
		
	}
	
	/**
	 * This Method Will perform the Meta data level checks such as Status Code | Count of Records ( As Of Now).
	 * 
	 * @param givenKey Given parameter Key from Data table 
	 * @param givenData Given Data for parameter
	 * @param response Response Object
	 */
	private void performMetadataChecks(String tempKey, Map<String, String> givenData, Response response) {
		int tempStatusCode = -1;
		if (!StringUtils.isEmpty(tempKey) && givenData != null && !givenData.isEmpty()) {
				if("statusCode".equalsIgnoreCase(tempKey)) {
					if (givenData.get(tempKey) != null) {
						tempStatusCode = Integer.valueOf(givenData.get(tempKey));
					}
					statusCodeShouldBe(response, tempStatusCode);	
				} else if ("recordCount".equalsIgnoreCase(tempKey)) {
					JsonPath jp = new JsonPath(response.toString());
					int sizeFrmCollection = response.jsonPath().getList("$").size();
					System.out.println("performMetadataChecks() :: Record Count from the response >> " + sizeFrmCollection);
					int givenCount = Integer.valueOf(givenData.get(tempKey)).intValue();
					Assert.assertEquals(sizeFrmCollection, givenCount);
				}				
			}
	}
	
	/**
	 * This Method is to validate Empty Response in Invalid data input based Scenarios.
	 * @param response Response Object from API Call
	 * @param table Given Data Table inputs for validation
	 */
	public void verifyEmptyResponse(Response response, DataTable table) {

		JsonPath jp = new JsonPath(response.toString());
		System.out.println("verifyEmptyResponse :: Record Count from the response object is : " + response.jsonPath().getList("$").size());
		int sizeFrmCollection = response.jsonPath().getList("$").size();;
		Assert.assertEquals(0, sizeFrmCollection);
	}
	
	/**
	 * This method is to validate the Status code given from Scenario against Response status code.
	 * @param response Response received from API Call.
	 * @param statusCode Given Status Code from Data Table Input.
	 */
    private void statusCodeShouldBe(Response response, int statusCode) {
    	System.out.println("Expected Status Code is: " + statusCode + "Status Code from the Response is : " + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(), statusCode);
    }

	/**
	 * This method is to fetch the specified Request Method Type for particular Scenario 
	 * @param table DataTable Object contains all parameters for given scenario
	 * @return Request Method Type
	 */
	private String getRequestMethodType(DataTable table) {
		if(table != null && !table.isEmpty()) {
			paramMap = table.asMap(String.class, String.class);
			if (paramMap.get("requestMethodType") != null) {
				return paramMap.get("requestMethodType");
			}
		}
		return "";
	}
	
	/**
	 * This  Method is to perform POST Method Type based call to given URL with provided input parameters as Request Body.
	 * 
	 * @param endUrl Given End URL.
	 * @return Response From  API  Call.
	 */
	private Response performPostCall(String endUrl) {
        String jsonStringBody = null;
        String temp = null;
        Iterator<String> itr = this.paramMap.keySet().iterator();
        String jsonFileName = "";
		int i = 0;
		while (itr.hasNext()) {
		 temp = itr.next();
			if ("jsonFilename".equalsIgnoreCase(temp)) {
				jsonFileName = "src/test/resources/testDataFiles/" + this.paramMap.get(temp) + ".json";
			}
		}
		
		jsonStringBody = JsonUtilities.generateStringFromResource(jsonFileName);

        RestAssured.useRelaxedHTTPSValidation();
        Response response = RestAssured.given().body(jsonStringBody).post(endUrl).then().extract().response();
        return response;

	}
	
	/**
	 * This  Method is to perform GET Method Type based call to given URL with provided input parameters as Query params.
	 *  
	 * @param endUrl Given API URL
	 * @return Response Object 
	 */
	private Response performGetCall(String endUrl) {
		StringBuffer preparedURL = new StringBuffer();
		String tempParamKey = null;
		preparedURL.append(endUrl);
		
		Iterator<String> itr = this.paramMap.keySet().iterator();
		int i = 0;
		while (itr.hasNext()) {
			tempParamKey = itr.next();
			if (!"requestMethodType".equalsIgnoreCase(tempParamKey) && !"statusCode".equalsIgnoreCase(tempParamKey)) {
				if (i > 0) {
					preparedURL.append("&");
				} else {
					preparedURL.append("?");
				}
				preparedURL.append(tempParamKey);
				preparedURL.append("=");
				preparedURL.append(this.paramMap.get(tempParamKey));
				i++;
				
			}
		}
		System.out.println("In performGetCall() ...Query Param Appended URL is >>> "+ preparedURL.toString());
		//Make GET Call to Prepared URL with Query params
		Response response = RestAssured.given().get(preparedURL.toString()).then().extract().response();
			
		return response;
	}
	
	/**
	 * This Method is to validate given labels and values in Response specific to API End Point.
	 * @param key Given Label of data
	 * @param Value value of parameter from data table
	 * @param response Response received from API Call.
	 */
	private void validateGivenKeyAndValueInResponse(String key, String expectedValue, Response response) {

		String expectedResult = null;

		if (response != null) {
			JsonPath jsonPathEvaluator = response.jsonPath();
			if("GET".equalsIgnoreCase(receivedMethodType)) {
				for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
					expectedResult = jsonPathEvaluator.getString(key + "[" + i + "]");
					System.out.println("Given expected Value >>> " + expectedValue + " , Value Received from Response >>> " + expectedResult);
					Assert.assertEquals(expectedResult, expectedValue);
				}
			} else if("POST".equalsIgnoreCase(receivedMethodType)) {
				for (int i = 0; i < response.jsonPath().getMap("$").size(); i++) {
					expectedResult = String.valueOf(response.jsonPath().getMap("$").get(key));
					System.out.println("Given expected Value >>> " + expectedValue + " , Value Received from Response >>> " + expectedResult);
					Assert.assertEquals(expectedResult, expectedValue);
				}
			} else {
				Assert.assertTrue(false, "Method Not Supported..");
			}
			
		}
	}
}
