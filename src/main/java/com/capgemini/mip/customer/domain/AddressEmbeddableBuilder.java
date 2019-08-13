package com.capgemini.mip.customer.domain;

public final class AddressEmbeddableBuilder {
  private String street;
  private String city;
  private String zip;
  private String state;

  private AddressEmbeddableBuilder() {
  }

  public static AddressEmbeddableBuilder address() {
    return new AddressEmbeddableBuilder();
  }

  public AddressEmbeddableBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  public AddressEmbeddableBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  public AddressEmbeddableBuilder withZip(String zip) {
    this.zip = zip;
    return this;
  }

  public AddressEmbeddableBuilder withState(String state) {
    this.state = state;
    return this;
  }

  public AddressEmbeddable build() {
    AddressEmbeddable addressEmbeddable = new AddressEmbeddable();
    addressEmbeddable.setStreet(street);
    addressEmbeddable.setCity(city);
    addressEmbeddable.setZip(zip);
    addressEmbeddable.setState(state);
    return addressEmbeddable;
  }
}
