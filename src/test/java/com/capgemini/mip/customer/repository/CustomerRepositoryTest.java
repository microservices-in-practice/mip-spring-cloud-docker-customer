package com.capgemini.mip.customer.repository;

import com.capgemini.mip.customer.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.capgemini.mip.customer.testdata.TestdataProvider.provideCustomer;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void shouldFindCustomer() {
    // given
    Customer customer = provideCustomer();
    Customer savedCustomer = entityManager.persist(customer);

    // whem
    Customer foundCustomer = customerRepository.getOne(savedCustomer.getId());

    // then
    assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getCode()).isEqualTo(savedCustomer.getCode());
    assertThat(foundCustomer.getBillingAddress()).isNotNull();
    assertThat(foundCustomer.getShippingAddress()).isNotNull();

  }

  @Test
  public void shouldSaveCustomer() {
    // given
    Customer customer = provideCustomer();

    // whem
    Customer savedCustomer = customerRepository.save(customer);

    // then
    Customer foundCustomer = customerRepository.getOne(savedCustomer.getId());
    assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getCode()).isEqualTo(savedCustomer.getCode());
    assertThat(foundCustomer.getBillingAddress()).isNotNull();
    assertThat(foundCustomer.getShippingAddress()).isNotNull();

  }

  @Test
  public void shouldFindCustomerByCode() {
    // given
    Customer customer = provideCustomer();
    Customer savedCustomer = entityManager.persist(customer);

    // whem
    Customer foundCustomer = customerRepository.findByCode(savedCustomer.getCode());

    // then
    assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getId()).isEqualTo(savedCustomer.getId());
    assertThat(foundCustomer.getBillingAddress()).isNotNull();
    assertThat(foundCustomer.getShippingAddress()).isNotNull();

  }


}
