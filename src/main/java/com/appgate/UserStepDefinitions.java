package com.appgate;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class UserStepDefinitions {
    private String url;
    private String protocol;
    private String requestBody = "";

    private Response response;
    private int userId;

    @Given("a server (.*)$")
    public void aServer(String url) {
        this.url = url;
    }

    @And("with the protocol (.*)$")
    public void withTheProtocol(String protocol) {
        this.protocol = protocol;
    }

    @And("using a body with parameter (.*) and value (.*)$")
    public void usingABodyWithParameterAndValue(String bodyParameter, String bodyValue) {
        this.requestBody += '"' + bodyParameter + "\": \"" + bodyValue + "\",";

    }

    @When("the server execute the query for (.*) a User$")
    public void theServerExecuteTheQueryFor(String action) {
        Response response;
        switch (action) {
            case "Create":
                response = RestAssured.given()
                        .baseUri(this.protocol + "://" + this.url + "/api/users")
                        .body("{\n" +
                                this.requestBody.substring(0, this.requestBody.length() - 1) +
                                "}").when().post();
                break;
            case "Read":
                response = RestAssured.given()
                        .baseUri(this.protocol + "://" + this.url + "/api/users")
                        .when().get();
                break;
            case "Update":
                response =RestAssured.given()
                        .baseUri(this.protocol + "://" + this.url + "/api/users/" + this.userId)
                        .body("{\n" +
                                this.requestBody.substring(0, this.requestBody.length() - 1) +
                                "}").when().put();
                break;
            case "Delete":
                response =RestAssured.given()
                        .baseUri(this.protocol + "://" + this.url + "/api/users/" + this.userId)
                        .body("{\n" +
                                this.requestBody.substring(0, this.requestBody.length() - 1) +
                                "}").when().delete();
                break;
            default:
                System.err.println("not implemented");
                break;
        }
    }

    @Then("the server response with Status {int}")
    public void theServerResponseWithStatus(int statusCode) {
       response.then().statusCode(statusCode);

    }

    @And("with the userID as ID {int}")
    public void theUserWithID(int userId) {
        this.userId = userId;
    }

}