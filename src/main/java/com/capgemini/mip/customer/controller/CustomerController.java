package com.capgemini.mip.customer.controller;

import com.capgemini.mip.customer.service.Customer;
import com.capgemini.mip.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping(path = "/{code}")
  public ResponseEntity<Customer> getCustomer(@PathVariable String code) {
    return Optional.ofNullable(customerService.findByCode(code))
      .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping()
  public ResponseEntity<List<Customer>> getCustomers() {
    return Optional.ofNullable(customerService.findAll())
      .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping()
  public ResponseEntity<Customer> saveOrUpdate(@RequestBody Customer customer) {
    return Optional.ofNullable(customerService.saveCustomer(customer))
      .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
