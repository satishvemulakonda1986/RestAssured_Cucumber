package stepDefinitions;

import org.junit.Before;

import endpoints.UserPostEndPoint;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

/**
 * This is Step Definitions For User Post API.
 * 
 * @author Admin
 *
 */
public class PostEndPointStepDefinitions {

	private UserPostEndPoint endPoint = new UserPostEndPoint();
	
	private Response response;
	
    @When("user calls User Posts API with given data")
    public void getUserPostsInfoWithParameters(DataTable table) {
        this.response = endPoint.getPostDetailsByParameters(table);
    }
    
    @Then("user validate folowing values in response post")
    public void validatePostAPICallResponse(DataTable table) {
    	this.endPoint.verifyResponse(response, table);
    }
    
    @When("user calls User Posts API  with not exist data")
    public void getUserPostInfoWithInvalidParameters(DataTable table) {
    	this.response = endPoint.getPostDetailsByParameters(table);
    }
    @Then("user validate Empty Post response and below status code")
    public void validateEmptyPostAPIResponseWithInvalidArgument(DataTable table) {
      	this.endPoint.verifyEmptyResponse(response, table);
    }
    
    @When("user calls User Posts API with valid json request and request type")
    public void addUserPostWithValidJson(DataTable table) {
    	this.response = endPoint.addUserPostWithValidJson(table);
    }  
    
    @Then("post list returned with expected count as given")
    public void validatePostCount(DataTable table) {
      	this.endPoint.verifyResponse(response, table);
    }
}