package com.capgemini.mip.customer.domain;

public final class CustomerEntityBuilder {
  private Long id;
  private Integer version;
  private String code;
  private String name;
  private String vat;
  private AddressEmbeddable shippingAddress;
  private AddressEmbeddable billingAddress;

  private CustomerEntityBuilder() {
  }

  public static CustomerEntityBuilder customerEntity() {
    return new CustomerEntityBuilder();
  }

  public CustomerEntityBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public CustomerEntityBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public CustomerEntityBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public CustomerEntityBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CustomerEntityBuilder withVat(String vat) {
    this.vat = vat;
    return this;
  }

  public CustomerEntityBuilder withShippingAddress(AddressEmbeddable shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  public CustomerEntityBuilder withBillingAddress(AddressEmbeddable billingAddress) {
    this.billingAddress = billingAddress;
    return this;
  }

  public CustomerEntity build() {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(id);
    customerEntity.setVersion(version);
    customerEntity.setCode(code);
    customerEntity.setName(name);
    customerEntity.setVat(vat);
    customerEntity.setShippingAddress(shippingAddress);
    customerEntity.setBillingAddress(billingAddress);
    return customerEntity;
  }
}
