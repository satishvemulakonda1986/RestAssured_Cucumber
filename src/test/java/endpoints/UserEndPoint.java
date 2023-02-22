package endpoints;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

/**
 * This Class is End Point for Users Data.
 * @author Admin
 *
 */
public class UserEndPoint extends CommonEndPoint {

	private static final String END_POINT_URL = BASE_URL + "/users";
	
	/**
	 * This Method is used to get Users Data in response for given parameters.
	 * @param table DataTable with parameters for API Call.
	 * @return Response from API Call
	 */
	public Response getUserDetailsByUserProperties(DataTable table) {
		Response response = makeApiCall(END_POINT_URL, table);
		return response;
	}
	
	/**
	 * This Method is to Make Post call to add User with given parameters. 
	 * @param table Data Table with Input parameters.
	 * @return Response from API Call.
	 */
	public Response addUserDetailsWithValidJson(DataTable table) {
		Response response = makeApiCall(END_POINT_URL, table);
		return response;
	}
}
