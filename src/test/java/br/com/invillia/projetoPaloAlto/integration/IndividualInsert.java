package br.com.invillia.projetoPaloAlto.integration;

import br.com.invillia.projetoPaloAlto.api.rest.IndividualController;
import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IndividualInsert {

    private String url = "/individual";

    private IndividualDTO individualDTO;

    private AddressDTO addressDTO;

    private Response responseEntity;

    private RequestSpecification requestSpecification;

    @BeforeAll()
    public void setUp(){
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Given("Create a IndividualDTO")
    public void createAIndividualDTO() {
        individualDTO = new IndividualDTO();
    }

    @And("The name is {string}")
    public void theNameIs(String name) {
        individualDTO.setName(name);
    }

    @And("The motherName is {string}")
    public void theMotherNameIs(String motherName) {
        individualDTO.setMotherName(motherName);
    }

    @And("The document is {string}")
    public void theDocumentIs(String document) {
        individualDTO.setDocument(document);
    }

    @And("The rg id {string}")
    public void theRgId(String rg) {
        individualDTO.setRg(rg);
    }

    @And("The birthDate is {string}")
    public void theBirthDateIs(String birthDate) {
        LocalDate localDate = LocalDate.parse(birthDate);
        individualDTO.setBirthDate(localDate);
    }

    @And("The active is active")
    public void theActiveIs() {
        individualDTO.setActive(true);
    }

    @Given("Create a AndressDTO")
    public void createAAndressDTO() {
        List<AddressDTO> addressesDTO = new ArrayList<>();
        this.addressDTO = new AddressDTO();
        addressesDTO.add(addressDTO);
        this.addressDTO.setIndividualDTO(individualDTO);
        this.individualDTO.setAddressesDTO(addressesDTO);
    }

    @And("The district is {string}")
    public void theDistrictIs(String district) {
        addressDTO.setDistrict(district);
    }

    @And("The number is {string}")
    public void theNumberIs(String number) {
        addressDTO.setNumber(number);
    }

    @And("The city is {string}")
    public void theCityIs(String city) {
        addressDTO.setCity(city);
    }

    @And("The state is {string}")
    public void theStateIs(String state) {
        addressDTO.setState(state);
    }

    @And("The zipCode is {string}")
    public void theZipCodeIs(String zipCode) {
        addressDTO.setZipCode(zipCode);
    }

    @And("The main is {string}")
    public void theMainIs(String main) {
        Boolean bool = Boolean.valueOf(main);
        addressDTO.setMain(bool);
    }

    @When("User executes a Post")
    public void userExecutesAPost() {
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON).with().body(individualDTO);
        responseEntity = this.requestSpecification.post(url);
    }

    @Then("the server should return a {int}")
    public void theServerShouldReturnA(int http) {
        Assertions.assertEquals(http, responseEntity.getStatusCode());
    }
}
