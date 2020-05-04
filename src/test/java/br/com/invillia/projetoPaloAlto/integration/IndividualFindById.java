//package br.com.invillia.projetoPaloAlto.integration;
//
//import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
//import br.com.invillia.projetoPaloAlto.domain.dto.IndividualDTO;
//import io.cucumber.java.en.Given;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.junit.jupiter.api.BeforeAll;
//
//public class IndividualFindById {
//
//    private String url = "/individual";
//
//    private RequestSpecification requestSpecification;
//
//    private Response response;
//
//    private IndividualDTO individualDTO;
//
//    private AddressDTO addressDTO;
//
//    @BeforeAll()
//    public void setUp(){
//        RestAssured.baseURI = "http://localhost:8080";
//    }
//
//    @Given("The {int}")
//    public void theId(int id) {
//        url = url.concat("/").concat(String.valueOf(id));
//    }
//
////    @When("User executes a Get")
////    public void userExecutesAGet() {
////        this.requestSpecification = RestAssured.given();
////        response = this.requestSpecification.get(url);
////    }
////
////    @Then("The server should return a Individual")
////    public void theServerShouldReturnAIndividual() {
////        this.individualDTO = response.getBody().as(IndividualDTO.class);
////    }
////
////    @And("The statusCode is {int}")
////    public void theStatusCodeIsHttpStatusCode(int http) {
////        Assertions.assertEquals(http,response.getStatusCode());
////    }
////
////    @And("The name is {string}")
////    public void theNameIs(String name) {
////        Assertions.assertEquals(individualDTO.getName(),name);
////    }
////
////    @And("The motherName is {string}")
////    public void theMotherNameIs(String motherName) {
////        Assertions.assertEquals(individualDTO.getMotherName(),motherName);
////    }
////
////    @And("The document is {string}")
////    public void theDocumentIs(String document) {
////        Assertions.assertEquals(individualDTO.getDocument(),document);
////    }
////
////    @And("The rg id {string}")
////    public void theRgId(String rg) {
////        Assertions.assertEquals(individualDTO.getRg(),rg);
////    }
////
////    @And("The birthDate is {string}")
////    public void theBirthDateIs(String birthDate) {
////        Assertions.assertEquals(individualDTO.getBirthDate().toString(),birthDate);
////    }
////
////    @And("The active is {string}")
////    public void theActiveIsActive(String active) {
////        Assertions.assertEquals(individualDTO.getActive().toString(),active);
////    }
////
////    @And("The district is {string}")
////    public void theDistrictIs(String district) {
////        this.addressDTO = individualDTO.getAddressesDTO().get(0);
////        Assertions.assertEquals(addressDTO.getDistrict(),district);
////    }
////
////    @And("The number is {string}")
////    public void theNumberIs(String number) {
////        Assertions.assertEquals(addressDTO.getNumber(),number);
////    }
////
////    @And("The city is {string}")
////    public void theCityIs(String city) {
////        Assertions.assertEquals(addressDTO.getCity(),city);
////    }
////
////    @And("The state is {string}")
////    public void theStateIs(String state) {
////        Assertions.assertEquals(addressDTO.getState(),state);
////    }
////
////    @And("The zipCode is {string}")
////    public void theZipCodeIs(String zipCode) {
////        Assertions.assertEquals(addressDTO.getZipCode(),zipCode);
////    }
////
////    @And("The main is {string}")
////    public void theMainIs(String main) {
////        Assertions.assertEquals(addressDTO.getMain().toString(),main);
////    }
//}
//
