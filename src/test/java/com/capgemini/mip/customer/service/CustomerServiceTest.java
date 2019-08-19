package com.capgemini.mip.customer.service;

import com.capgemini.mip.customer.domain.Customer;
import com.capgemini.mip.customer.testdata.TestdataProvider;
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

import static com.capgemini.mip.customer.testdata.TestdataProvider.provideCustomerTO;
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
    CustomerTO customer = TestdataProvider.provideCustomerTO();

    // when
    CustomerTO savedCustomer = customerService.saveCustomer(customer);

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
    CustomerTO customer = TestdataProvider.provideCustomerTO();
    CustomerTO savedCustomer = customerService.saveCustomer(customer);

    // when
    savedCustomer.getBillingAddress().setStreet("Test Street");
    CustomerTO updatedCustomer = customerService.saveCustomer(savedCustomer);

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
    CustomerTO savedCustomer = customerService.saveCustomer(TestdataProvider.provideCustomerTO());
    assertThat(entityManager.find(Customer.class, savedCustomer.getId())).isNotNull();

    // when
    customerService.deleteCustomer(savedCustomer.getId());

    // then
    assertThat(entityManager.find(Customer.class, savedCustomer.getId())).isNull();

  }

  @Test
  public void shouldFindById() {
    // given
    CustomerTO savedCustomer = customerService.saveCustomer(TestdataProvider.provideCustomerTO());

    // when
    CustomerTO foundCustomer = customerService.findById(savedCustomer.getId());

    // then
    assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getId()).isEqualTo(savedCustomer.getId());
    assertThat(foundCustomer.getCode()).isEqualTo(savedCustomer.getCode());
    assertThat(foundCustomer.getName()).isEqualTo(savedCustomer.getName());
  }

  @Test
  public void shouldFindByCode() {
    // given
    CustomerTO savedCustomer = customerService.saveCustomer(TestdataProvider.provideCustomerTO());

    // when
    CustomerTO foundCustomer = customerService.findByCode(savedCustomer.getCode());

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
      provideCustomerTO("1212"),
      provideCustomerTO("2323"),
      provideCustomerTO("3434")
    ).stream()
      .map(customer -> customerService.saveCustomer(customer))
      .map(CustomerTO::getId)
      .toArray(Long[]::new);

    // when
    List<CustomerTO> customers = customerService.findAll();

    // then
    assertThat(customers.stream().map(CustomerTO::getId).collect(Collectors.toList())).contains(customerIds);

  }


}
