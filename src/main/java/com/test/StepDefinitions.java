package com.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import junit.framework.Assert;

import org.apache.http.HttpStatus;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.table.DataTable;

public class StepDefinitions{
	
	Response response;
	RequestSpecification rspec;
		
	@Given("^the user makes a call to the all country service$")
	public void the_user_makes_a_call_to_the_all_country_service() throws URISyntaxException {
		baseURI = "http://services.groupkt.com";
		basePath= "/country/get/";
		response = when().get(new URI("all"));
	}

	
	@When("^a user searches the countries by code$")
	public void a_user_searches_the_countries_by_code(DataTable table) {
		List<List<String>>data = table.raw();
		response.then().body("RestResponse.result.alpha2_code",
				hasItems(data.get(1).get(1),
						data.get(2).get(1),
						data.get(3).get(1)));
	}
	

       
    @Then("^the status code is Http Ok$")
    public void the_status_code_is_Http_Ok() {
    	response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }
    
    
    @Given("^the user calls the \"([^\"]*)\" service$")
    public void the_user_calls_the_service(String arg1) throws URISyntaxException {
    	baseURI = "http://services.groupkt.com";
		basePath= "/country/get/";
		response = when().get(new URI(arg1));
    }
  
    
    
    @When("^a user validates response the \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\"$")
    public void a_user_validates_response_the_(String country, String alpha2, String alpha3) {
        
    	response.then().body("RestResponse.result.name", 
    			equalTo(country),
    			"RestResponse.result.alpha2_code",equalTo(alpha2),
    			"RestResponse.result.alpha3_code",equalTo(alpha3));
        	
    }
    

	@When("^a user validates response the \"([^\"]*)\"$")
	public void a_user_validates_response_the(String arg1) {
		String responseData = response.thenReturn().body().asString();
		Assert.assertTrue((responseData.contains("The requested resource is not available")));

	}
	
	@Given("^the user makes a post request \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\"$")
	public void the_user_makes_a_post_request(String country, String alpha2, String alpha3) {
		given().parameters("name","country" ,
				"alpha2_code", "alpha2", "alpha3_code", "alpha3").post("/post");
		
	}
		

	@Then("^user gets a valid response back for post$")
	public void user_gets_a_valid_response_back(String country) {
	    response.then().body("RestResponse.result.name", 
    			equalTo(country));
	}

	

}
