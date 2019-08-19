package com.capgemini.mip.customer.controller;

import com.capgemini.mip.customer.service.CustomerTO;
import com.capgemini.mip.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

import static com.capgemini.mip.customer.testdata.TestdataProvider.provideCustomerTO;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class AnotherCustomerControllerIntegrationTest {

  @Autowired
  private CustomerService customerService;

  @LocalServerPort
  private int port;

  private String uri;

  @PostConstruct
  public void init() {
    uri = "http://localhost:" + port;
  }

  @Test
  public void shouldGetCustomer() throws JsonProcessingException {

    CustomerTO customer = provideCustomerTO("111222");
    CustomerTO savedCustomer = customerService.saveCustomer(customer);

    given()
      .when().get(uri + "/customers/" + customer.getCode())
      .then()
      .log().ifValidationFails()
      .statusCode(OK.value())
      .contentType(JSON)
      .body("code", is(customer.getCode()))
      .body("name", is(customer.getName()))
      .body(equalTo(toJson(savedCustomer)));
  }

  @Test
  public void shouldCreateCustomer() throws JsonProcessingException {
    CustomerTO customer = provideCustomerTO("222111");

    CustomerTO createdCustomer = given()
      .body(toJson(customer)).contentType(JSON)
      .when().post(uri + "/customers")
      .then()
      .log().ifValidationFails()
      .statusCode(OK.value())
      .extract().as(CustomerTO.class);

    assertThat(customerService.findById(createdCustomer.getId())).isNotNull();

  }

  private static String toJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

}
