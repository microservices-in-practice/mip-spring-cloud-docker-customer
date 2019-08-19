package com.capgemini.mip.customer.service;

import com.capgemini.mip.customer.config.ApplicationConfig;
import com.capgemini.mip.customer.domain.Customer;
import com.capgemini.mip.customer.testdata.TestdataProvider;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class BeanMapperTest {
  @Autowired
  private DozerBeanMapper beanMapper;

  @Test
  public void shouldMapCustomerEntityToCustomer() {
    Customer customerEntity = TestdataProvider.provideCustomer();
    CustomerTO customer = beanMapper.map(customerEntity, CustomerTO.class);

    assertThat(customer).isNotNull();
    assertThat(customer.getId()).isEqualTo(customer.getId());
    assertThat(customer.getCode()).isEqualTo(customer.getCode());
    assertThat(customer.getVat()).isEqualTo(customer.getVat());
    assertThat(customer.getName()).isEqualTo(customer.getName());
    assertThat(customer.getBillingAddress()).isNotNull();
    assertThat(customer.getBillingAddress().getCity()).isEqualTo(customerEntity.getBillingAddress().getCity());
    assertThat(customer.getBillingAddress().getStreet()).isEqualTo(customerEntity.getBillingAddress().getStreet());
    assertThat(customer.getBillingAddress().getZip()).isEqualTo(customerEntity.getBillingAddress().getZip());
    assertThat(customer.getBillingAddress().getState()).isEqualTo(customerEntity.getBillingAddress().getState());
    assertThat(customer.getShippingAddress()).isNotNull();
    assertThat(customer.getShippingAddress().getCity()).isEqualTo(customerEntity.getBillingAddress().getCity());
    assertThat(customer.getShippingAddress().getStreet()).isEqualTo(customerEntity.getBillingAddress().getStreet());
    assertThat(customer.getShippingAddress().getZip()).isEqualTo(customerEntity.getBillingAddress().getZip());
    assertThat(customer.getShippingAddress().getState()).isEqualTo(customerEntity.getBillingAddress().getState());
  }

  @Test
  public void shouldMapCustomerToCustomerEntity() {
    CustomerTO customer = TestdataProvider.provideCustomerTO();
    Customer customerEntity = beanMapper.map(customer, Customer.class);

    assertThat(customerEntity).isNotNull();
    assertThat(customerEntity.getId()).isEqualTo(customerEntity.getId());
    assertThat(customerEntity.getCode()).isEqualTo(customerEntity.getCode());
    assertThat(customerEntity.getVat()).isEqualTo(customerEntity.getVat());
    assertThat(customerEntity.getName()).isEqualTo(customerEntity.getName());
    assertThat(customerEntity.getBillingAddress()).isNotNull();
    assertThat(customerEntity.getBillingAddress().getCity()).isEqualTo(customer.getBillingAddress().getCity());
    assertThat(customerEntity.getBillingAddress().getStreet()).isEqualTo(customer.getBillingAddress().getStreet());
    assertThat(customerEntity.getBillingAddress().getZip()).isEqualTo(customer.getBillingAddress().getZip());
    assertThat(customerEntity.getBillingAddress().getState()).isEqualTo(customer.getBillingAddress().getState());
    assertThat(customerEntity.getShippingAddress()).isNotNull();
    assertThat(customerEntity.getShippingAddress().getCity()).isEqualTo(customer.getBillingAddress().getCity());
    assertThat(customerEntity.getShippingAddress().getStreet()).isEqualTo(customer.getBillingAddress().getStreet());
    assertThat(customerEntity.getShippingAddress().getZip()).isEqualTo(customer.getBillingAddress().getZip());
    assertThat(customerEntity.getShippingAddress().getState()).isEqualTo(customer.getBillingAddress().getState());
  }

}
