package br.com.invillia.projetoPaloAlto.integration.legalEntity;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateWithoutIndividuals {

    private String url = "/legal-entity";

    private RequestSpecification requestSpecification;

    private Response response;

    private LegalEntityDTO legalEntityDTO;

    private AddressDTO addressDTO;


    @Given("this url {string}")
    public void thisUrl(String url) {
        this.url = this.url.concat(url);
    }

    @And("this key {string}")
    public void thisKey(String key) {
        this.url = this.url.concat(key);
    }

    @And("Verify if This Legal Entity is registered {string},{string},{string},{string},{string},{string},{string}," +
            "{string},{string},{string}")
    public void verifyIfThisLegalEntityIsRegistered(String arg0, String arg1, String arg2, String arg3, String arg4,
                                                    String arg5, String arg6, String arg7, String arg8, String arg9) {
    }

    @When("the user updates a Legal Entity")
    public void theUserUpdatesALegalEntity() {
    }

    @And("this name is {string}")
    public void thisNameIs(String arg0) {
    }

    @And("this tradeName is {string}")
    public void thisTradeNameIs(String arg0) {
    }

    @And("this document is {string}")
    public void thisDocumentIs(String arg0) {
    }

    @And("this active is {string}")
    public void thisActiveIs(String arg0) {
    }

    @And("Any Address")
    public void anyAddress() {
    }

    @And("this district is {string}")
    public void thisDistrictIs(String arg0) {
    }

    @And("this number is {string}")
    public void thisNumberIs(String arg0) {
    }

    @And("this city is {string}")
    public void thisCityIs(String arg0) {
    }

    @And("this state is {string}")
    public void thisStateIs(String arg0) {
    }

    @And("this zipCode is {string}")
    public void thisZipCodeIs(String arg0) {
    }

    @And("this main is {string}")
    public void thisMainIs(String arg0) {
    }

    @When("Client update a Legal Entity")
    public void clientUpdateALegalEntity() {
    }

    @Then("server must return a <httpStatusCode>")
    public void serverMustReturnAHttpStatusCode() {
    }
}
