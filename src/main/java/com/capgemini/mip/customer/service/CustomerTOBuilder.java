package com.capgemini.mip.customer.service;

public final class CustomerTOBuilder {
  private Long id;
  private Integer version;
  private String code;
  private String name;
  private String vat;
  private AddressTO shippingAddress;
  private AddressTO billingAddress;

  private CustomerTOBuilder() {
  }

  public static CustomerTOBuilder customer() {
    return new CustomerTOBuilder();
  }

  public CustomerTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public CustomerTOBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public CustomerTOBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public CustomerTOBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CustomerTOBuilder withVat(String vat) {
    this.vat = vat;
    return this;
  }

  public CustomerTOBuilder withShippingAddress(AddressTO shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  public CustomerTOBuilder withBillingAddress(AddressTO billingAddress) {
    this.billingAddress = billingAddress;
    return this;
  }

  public CustomerTO build() {
    CustomerTO customer = new CustomerTO();
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
