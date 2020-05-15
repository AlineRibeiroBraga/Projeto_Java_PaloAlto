package br.com.invillia.projetoPaloAlto.integration.legalEntity;


import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class LegalEntityDeleteByIdBadRequest {

    private String url = "/legal-entity";
    private String id;

    private Faker faker;

    private RequestSpecification requestSpecification;

    private Response response;

    @BeforeAll
    public void setUp(){
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Given("The URL {string}")
    public void theURL(String url) {
        this.url = this.url.concat(url);
    }

    @And("The KEY {string}")
    public void theKEY(String key) {
        this.url = this.url.concat(key);
        this.id = key;
    }

    @And("Verify if this Legal Entity exists")
    public void verifyIfThisLegalEntityExists() {

        int flg;

        do{
            flg = 0;
            this.requestSpecification = given();
            this.response = this.requestSpecification.get(this.url);

            if(this.response.getStatusCode() == 200){
                flg = 1;
                this.url = "/legal-entity/";
                this.id = faker.number().digit();
                this.url = this.url.concat(id);
            }
        }while(flg == 1);
    }

    @When("The User makes a Delete")
    public void theUserMakesADelete() {
        this.requestSpecification = given();
        this.response = this.requestSpecification.delete(this.url);
    }

    @Then("The server must return a {int}")
    public void theServerMustReturnAStatusCode(int httpStatusCode) {
        Assertions.assertEquals(httpStatusCode,this.response.getStatusCode());
    }

    @And("The Message {string}")
    public void theMessage(String message) {
        Assertions.assertEquals(message,this.response.getBody().path("message"));
    }
}
