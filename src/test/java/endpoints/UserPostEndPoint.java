package endpoints;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

/**
 * This Class Implementation for Posts Data.
 * @author Admin
 *
 */
public class UserPostEndPoint extends CommonEndPoint {

	private static final String END_POINT_URL = BASE_URL + "/posts";
	
	/**
	 * This Method is to perform  GET API Call For Posts data with Given Data Table.
	 * @param table Data Table with input parameters.
	 * @return Response from API Call
	 */
	public Response getPostDetailsByParameters(DataTable table) {
		Response response = makeApiCall(END_POINT_URL, table);
		return response;
	}
	
	/**
	 * This Method is to perform Add User Post With POST Method API Call with Valid Inputs.
	 * @param table Data Table with input parameters.
	 * @return Response from API Call 
	 */
	public Response addUserPostWithValidJson(DataTable table) {
		Response response = makeApiCall(END_POINT_URL, table);
		return response;
	}
	
}
