package com.capgemini.mip.customer.repository;

import com.capgemini.mip.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  public Customer findByCode(String code);

}
