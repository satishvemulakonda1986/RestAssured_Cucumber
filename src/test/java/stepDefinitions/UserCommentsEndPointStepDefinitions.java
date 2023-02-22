package stepDefinitions;

import endpoints.UserCommentsEndPoint;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

/**
 * This is Step Definitions For User Post Comments API.
 * 
 * @author Admin
 *
 */
public class UserCommentsEndPointStepDefinitions {

	private UserCommentsEndPoint endPoint = new UserCommentsEndPoint();
	
	private Response response;
	
	
    @When("user calls User Post Comments API with given input parameters")
    public void usrCommentsByPropetiesCall(DataTable table) {
        this.response = endPoint.getCommentsDetailsByUserProperties(table);
    }
    
    @Then("user validate folowing values in response comments")
    public void validateAPICallResponse(DataTable table) {
    	this.endPoint.verifyResponse(response, table);
    }
    
    @When("user calls User Post Comments API  with invalid paramters")
    public void getUserInfoWithInvalidParameters(DataTable table) {
    	this.response = endPoint.getCommentsDetailsByUserProperties(table);
    }
    @Then("user validate empty comments response and success status code")
    public void validateEmptyAPIResponseWithInvalidArgument(DataTable table) {
      	this.endPoint.verifyEmptyResponse(response, table);
    }    

    @When("user calls User Post Comments API with valid json request and request type")
    public void addUserPostCommentWithValidJson(DataTable table) {
    	this.response = endPoint.addUserPostCommentDetailsByParameters(table);
    }  
}