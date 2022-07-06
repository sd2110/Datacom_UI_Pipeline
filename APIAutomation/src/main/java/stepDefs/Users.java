package stepDefs;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

public class Users {
    private static Response response;
    private static RequestSpecification httpsRequest = RestAssured.given().contentType(ContentType.JSON);
    @Given("^User performs a GET call on the request URL \"(.*)\"")
    public void getcallonurl(String url){
        response = httpsRequest.request(Method.GET, url);
    }

    @Then("^API call is successful with a status code \"(.*)\"$")
    public void apiCallIsSuccessfulWithAStatusCode(int status) {
        try {
            Assert.assertEquals(status, response.statusCode());
        }
        catch (AssertionError ae)
        {
            Assert.fail("API call was not successful");
        }
    }

    @And("^The response contains \"(.*)\" users$")
    public void theResponseContainsUsers(int expectedNumberOfUsers) {
        List<HashMap<String, Object>> usersList = response.jsonPath().get();
        int actualNumberOfUsers = usersList.size();
        try {
            Assert.assertEquals(actualNumberOfUsers, expectedNumberOfUsers);
        } catch (AssertionError ae) {
            Assert.fail("There are either more or less than 10 number of users in the reponse");
        }
    }

    @And("^The response contains name \"(.*)\" for the id \"(.*)\"$")
    public void theResponseContainsNameForTheId(String expectedName, int userId) {
        List<HashMap<String, Object>> usersList = response.jsonPath().get();
        String actualName = (String) usersList.get(userId-1).get("name");
        Assert.assertEquals(expectedName,actualName);
    }

   @Then("The newly generated data gets added in the response")
    public void theNewlyGeneratedDataGetsAddedInTheResponse() {
        List<HashMap<String, Object>> usersList = response.jsonPath().get();
        int actualNumberOfUsers = usersList.size();
        Assert.assertEquals(11,actualNumberOfUsers);
    }

    @When("User performs a POST call on the request URL {string} with name {string}, username {string} and email {string} in the request body")
    public void userPerformsAPOSTCallOnTheRequestURLWithNameUsernameAndEmailInTheRequestBody(String url, String name, String username, String email) {
        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("name",name );
        requestParams.addProperty("username",username );
        requestParams.addProperty("email",email );
        httpsRequest.body(requestParams.toString());
        response = httpsRequest.request(Method.POST, url);
    }

    @And("The response contains the requested fields with name {string}, username {string} and email {string}")
    public void theResponseContainsTheRequestedFieldsWithNameUsernameAndEmail(String expectedName, String expectedUsername, String expectedEmail) {
        Assert.assertEquals(expectedName, response.getBody().jsonPath().getString("name"));
        Assert.assertEquals(expectedUsername, response.getBody().jsonPath().getString("username"));
        Assert.assertEquals(expectedEmail, response.getBody().jsonPath().getString("email"));
    }
}
