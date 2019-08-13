package com.capgemini.mip.customer.service;

import com.capgemini.mip.customer.domain.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.capgemini.mip.customer.testdata.TestdataProvider.provideCustomer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@DirtiesContext
public class CustomerServiceTest {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private EntityManager entityManager;

  @Test
  public void shouldCreateCustomer() {
    // given
    Customer customer = provideCustomer();

    // when
    Customer savedCustomer = customerService.saveCustomer(customer);

    // then

    assertSoftly(softly -> {
      softly.assertThat(savedCustomer).isNotNull();
      softly.assertThat(savedCustomer.getCode()).isEqualTo(customer.getCode());
      softly.assertThat(savedCustomer.getName()).isEqualTo(customer.getName());
      softly.assertThat(savedCustomer.getId()).isNotNull();
      softly.assertThat(savedCustomer.getVersion()).isNotNull();
      softly.assertThat(savedCustomer.getShippingAddress()).isNotNull();
      softly.assertThat(savedCustomer.getBillingAddress()).isNotNull();
    });
  }

  @Test
  public void shouldUpdateCustomer() {
    // given
    Customer customer = provideCustomer();
    Customer savedCustomer = customerService.saveCustomer(customer);

    // when
    savedCustomer.getBillingAddress().setStreet("Test Street");
    Customer updatedCustomer = customerService.saveCustomer(savedCustomer);

    // then
    assertThat(updatedCustomer).isNotNull();
    assertThat(updatedCustomer.getCode()).isEqualTo(savedCustomer.getCode());
    assertThat(updatedCustomer.getBillingAddress().getStreet()).isEqualTo(savedCustomer.getBillingAddress().getStreet());
    assertThat(updatedCustomer.getName()).isEqualTo(savedCustomer.getName());
    assertThat(updatedCustomer.getId()).isEqualTo(savedCustomer.getId());
  }

  @Test
  public void shouldDeleteCustomer() {
    // given
    Customer savedCustomer = customerService.saveCustomer(provideCustomer());
    assertThat(entityManager.find(CustomerEntity.class, savedCustomer.getId())).isNotNull();

    // when
    customerService.deleteCustomer(savedCustomer.getId());

    // then
    assertThat(entityManager.find(CustomerEntity.class, savedCustomer.getId())).isNull();

  }

  @Test
  public void shouldFindById() {
    // given
    Customer savedCustomer = customerService.saveCustomer(provideCustomer());

    // when
    Customer foundCustomer = customerService.findById(savedCustomer.getId());

    // then
    assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getId()).isEqualTo(savedCustomer.getId());
    assertThat(foundCustomer.getCode()).isEqualTo(savedCustomer.getCode());
    assertThat(foundCustomer.getName()).isEqualTo(savedCustomer.getName());
  }

  @Test
  public void shouldFindByCode() {
    // given
    Customer savedCustomer = customerService.saveCustomer(provideCustomer());

    // when
    Customer foundCustomer = customerService.findByCode(savedCustomer.getCode());

    // then
    assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getId()).isEqualTo(savedCustomer.getId());
    assertThat(foundCustomer.getCode()).isEqualTo(savedCustomer.getCode());
    assertThat(foundCustomer.getName()).isEqualTo(savedCustomer.getName());
  }

  @Test
  public void shouldFindAll() {
    // given
    Long[] customerIds = Arrays.asList(
      provideCustomer("1212"),
      provideCustomer("2323"),
      provideCustomer("3434")
    ).stream()
      .map(customer -> customerService.saveCustomer(customer))
      .map(Customer::getId)
      .toArray(Long[]::new);

    // when
    List<Customer> customers = customerService.findAll();

    // then
    assertThat(customers.stream().map(Customer::getId).collect(Collectors.toList())).contains(customerIds);

  }


}
