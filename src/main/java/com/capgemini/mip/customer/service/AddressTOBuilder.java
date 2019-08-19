package com.capgemini.mip.customer.service;

public final class AddressTOBuilder {
  private String street;
  private String city;
  private String zip;
  private String state;

  private AddressTOBuilder() {
  }

  public static AddressTOBuilder address() {
    return new AddressTOBuilder();
  }

  public AddressTOBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  public AddressTOBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  public AddressTOBuilder withZip(String zip) {
    this.zip = zip;
    return this;
  }

  public AddressTOBuilder withState(String state) {
    this.state = state;
    return this;
  }

  public AddressTO build() {
    AddressTO address = new AddressTO();
    address.setStreet(street);
    address.setCity(city);
    address.setZip(zip);
    address.setState(state);
    return address;
  }
}
