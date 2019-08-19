package com.capgemini.mip.customer.domain;

public final class AddressBuilder {
  private String street;
  private String city;
  private String zip;
  private String state;

  private AddressBuilder() {
  }

  public static AddressBuilder address() {
    return new AddressBuilder();
  }

  public AddressBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  public AddressBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  public AddressBuilder withZip(String zip) {
    this.zip = zip;
    return this;
  }

  public AddressBuilder withState(String state) {
    this.state = state;
    return this;
  }

  public Address build() {
    Address address = new Address();
    address.setStreet(street);
    address.setCity(city);
    address.setZip(zip);
    address.setState(state);
    return address;
  }
}
