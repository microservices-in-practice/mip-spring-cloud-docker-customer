package com.capgemini.mip.customer.controller;

import com.capgemini.mip.customer.service.Customer;
import com.capgemini.mip.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static com.capgemini.mip.customer.testdata.TestdataProvider.provideCustomer;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class CustomerControllerIntegrationTest {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void initialiseRestAssuredMockMvcWebApplicationContext() {
    RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
  }

  @Test
  public void shouldGetCustomer() throws JsonProcessingException {

    Customer customer = provideCustomer("111222");
    Customer savedCustomer = customerService.saveCustomer(customer);

    given()
      .when().get("/customers/" + customer.getCode())
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
    Customer customer = provideCustomer("222111");

    Customer createdCustomer = given()
      .body(toJson(customer)).contentType(JSON)
      .when().post("/customers")
      .then()
      .log().ifValidationFails()
      .statusCode(OK.value())
      .extract().as(Customer.class);

    assertThat(customerService.findById(createdCustomer.getId())).isNotNull();

  }

  private static String toJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

}
