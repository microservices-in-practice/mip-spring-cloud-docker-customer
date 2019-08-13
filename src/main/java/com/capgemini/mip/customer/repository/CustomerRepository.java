package com.capgemini.mip.customer.repository;

import com.capgemini.mip.customer.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  public CustomerEntity findByCode(String code);

}
