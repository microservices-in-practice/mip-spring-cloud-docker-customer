package com.capgemini.mip.customer.service;

public final class CustomerBuilder {
  private Long id;
  private Integer version;
  private String code;
  private String name;
  private String vat;
  private Address shippingAddress;
  private Address billingAddress;

  private CustomerBuilder() {
  }

  public static CustomerBuilder customer() {
    return new CustomerBuilder();
  }

  public CustomerBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public CustomerBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public CustomerBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public CustomerBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CustomerBuilder withVat(String vat) {
    this.vat = vat;
    return this;
  }

  public CustomerBuilder withShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  public CustomerBuilder withBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
    return this;
  }

  public Customer build() {
    Customer customer = new Customer();
    customer.setId(id);
    customer.setVersion(version);
    customer.setCode(code);
    customer.setName(name);
    customer.setVat(vat);
    customer.setShippingAddress(shippingAddress);
    customer.setBillingAddress(billingAddress);
    return customer;
  }
}
