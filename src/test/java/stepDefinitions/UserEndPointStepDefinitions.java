package stepDefinitions;

import endpoints.UserEndPoint;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

/**
 * This is Step Definitions For User API.
 * 
 * @author Admin
 *
 */
public class UserEndPointStepDefinitions {

	private UserEndPoint endPoint = new UserEndPoint();
	
	private Response response;
	
    @When("user calls User List API with given input parameters")
    public void postApiCallIsMade(DataTable table) {
        this.response = endPoint.getUserDetailsByUserProperties(table);
    }
    
    @Then("user validate folowing values in response")
    public void validateAPICallResponse(DataTable table) {
    	this.endPoint.verifyResponse(response, table);
    }
    
    @When("user calls User List API  with invalid Parameters")
    public void getUserInfoWithInvalidParameters(DataTable table) {
    	this.response = endPoint.getUserDetailsByUserProperties(table);
    }
    
    @Then("user validate Post Empty response and below status code")
    public void validateEmptyAPIResponseWithInvalidArgument(DataTable table) {
      	this.endPoint.verifyEmptyResponse(response, table);
    }
    
    @When("user calls User List APAI with No Input parameters")
    public void getAllUserCount(DataTable table) {
    	this.response = endPoint.getUserDetailsByUserProperties(table);
    }
    
    @Then("users list returned with expected count as given")
    public void validateUserCount(DataTable table) {
      	this.endPoint.verifyResponse(response, table);
    }
    
    @When("user calls User List API with valid json request and request type")
    public void postUserInfoWithInvalidParameters(DataTable table) {
    	this.response = endPoint.addUserDetailsWithValidJson(table);
    }             
}