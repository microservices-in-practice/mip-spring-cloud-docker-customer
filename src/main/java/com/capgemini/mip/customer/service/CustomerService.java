package com.capgemini.mip.customer.service;

import com.capgemini.mip.customer.domain.Customer;
import com.capgemini.mip.customer.repository.CustomerRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private DozerBeanMapper beanMapper;

  public CustomerTO saveCustomer(CustomerTO customerTO) {
    Customer customer = beanMapper.map(customerTO, Customer.class);
    customer = customerRepository.save(customer);
    return beanMapper.map(customer, CustomerTO.class);
  }

  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  public CustomerTO findById(Long id) {
    return Optional.of(customerRepository.getOne(id))
      .map(customer -> beanMapper.map(customer, CustomerTO.class))
      .get();
  }

  public CustomerTO findByCode(String code) {
    return Optional.ofNullable(customerRepository.findByCode(code))
      .map(customer -> beanMapper.map(customer, CustomerTO.class))
      .orElse(null);
  }

  public List<CustomerTO> findAll() {
    return customerRepository.findAll().stream()
      .map(customer -> beanMapper.map(customer, CustomerTO.class))
      .collect(Collectors.toList());
  }

}
