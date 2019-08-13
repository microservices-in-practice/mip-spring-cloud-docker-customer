package com.capgemini.mip.customer.service;

import com.capgemini.mip.customer.domain.CustomerEntity;
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

  public Customer saveCustomer(Customer customer) {
    CustomerEntity customerEntity = beanMapper.map(customer, CustomerEntity.class);
    customerEntity = customerRepository.save(customerEntity);
    return beanMapper.map(customerEntity, Customer.class);
  }

  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  public Customer findById(Long id) {
    return Optional.of(customerRepository.getOne(id))
      .map(customerEntity -> beanMapper.map(customerEntity, Customer.class))
      .get();
  }

  public Customer findByCode(String code) {
    return Optional.ofNullable(customerRepository.findByCode(code))
      .map(customerEntity -> beanMapper.map(customerEntity, Customer.class))
      .orElse(null);
  }

  public List<Customer> findAll() {
    return customerRepository.findAll().stream()
      .map(customerEntity -> beanMapper.map(customerEntity, Customer.class))
      .collect(Collectors.toList());
  }

}
