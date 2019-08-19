package com.capgemini.mip.customer.service;

import java.io.Serializable;

public class CustomerTO implements Serializable {

  private Long id;
  private Integer version;
  private String code;
  private String name;
  private String vat;
  private AddressTO shippingAddress;
  private AddressTO billingAddress;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVat() {
    return vat;
  }

  public void setVat(String vat) {
    this.vat = vat;
  }

  public AddressTO getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(AddressTO shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public AddressTO getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(AddressTO billingAddress) {
    this.billingAddress = billingAddress;
  }

  @Override
  public String toString() {
    return "CustomerEntity{" +
      "id=" + id +
      ", version=" + version +
      ", code='" + code + '\'' +
      ", name='" + name + '\'' +
      ", vat='" + vat + '\'' +
      ", shippingAddress=" + shippingAddress +
      ", billingAddress=" + billingAddress +
      '}';
  }
}
