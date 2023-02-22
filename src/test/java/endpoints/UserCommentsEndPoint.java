package endpoints;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

/**
 * This Class is End Point for User Comments Data.
 * @author Admin
 *
 */
public class UserCommentsEndPoint extends CommonEndPoint {

	private static final String END_POINT_URL = BASE_URL + "/comments";
	
	/**
	 * This Method is make call to Comments API and get Response.
	 * @param table DataTable with given Input parameters
	 * @return Response  from API Call.
	 */
	public Response getCommentsDetailsByUserProperties(DataTable table) {
		Response response = makeApiCall(END_POINT_URL, table);
		return response;
	}
	
	/**
	 * This Method is to Make Post call to add Comment for Post with given parameters. 
	 * @param table Data Table with Input parameters.
	 * @return Response from API Call.
	 */
	public Response addUserPostCommentDetailsByParameters(DataTable table) {
		Response response = makeApiCall(END_POINT_URL, table);
		return response;
	}
}
