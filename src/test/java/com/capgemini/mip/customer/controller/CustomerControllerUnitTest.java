package com.capgemini.mip.customer.controller;

import com.capgemini.mip.customer.service.Customer;
import com.capgemini.mip.customer.service.CustomerBuilder;
import com.capgemini.mip.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.capgemini.mip.customer.testdata.TestdataProvider.provideCustomer;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
public class CustomerControllerUnitTest {

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController customerController;

  @Before
  public void initialiseRestAssuredMockMvcStandalone() {
    RestAssuredMockMvc.standaloneSetup(customerController);
  }

  @Test
  public void shouldGetCustomer() throws JsonProcessingException {

    Customer customer = provideCustomer();
    when(customerService.findByCode(customer.getCode())).thenReturn(customer);


    Customer foundCustomer =
      given()
        .when().get("/customers/" + customer.getCode())
        .then()
        .log().ifValidationFails()
        .statusCode(OK.value())
        .contentType(JSON)
        .body("code", is(customer.getCode()))
        .body("name", is(customer.getName()))
        .body(equalTo(toJson(customer)))
        .extract().as(Customer.class);

    assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getCode()).isEqualTo(customer.getCode());

  }

  @Test
  public void shouldCreateCustomer() throws JsonProcessingException {
    Customer customer = provideCustomer();
    Customer updatedCustomer = CustomerBuilder.customer()
      .withBillingAddress(customer.getBillingAddress())
      .withShippingAddress(customer.getShippingAddress())
      .withCode(customer.getCode())
      .withName(customer.getName())
      .withVat(customer.getVat())
      .withId(900L)
      .withVersion(1)
      .build();

    when(customerService.saveCustomer(any(Customer.class))).thenReturn(updatedCustomer);

    given()
      .body(toJson(customer)).contentType(JSON)
      .when().post("/customers")
      .then()
      .log().ifValidationFails()
      .statusCode(OK.value())
      .contentType(JSON)
      .body(equalTo(toJson(updatedCustomer)));

  }

  private static String toJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }


}
