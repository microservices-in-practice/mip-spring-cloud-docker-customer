package com.capgemini.mip.customer.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  @Version
  @Column(name = "version")
  private Integer version;

  @Column(nullable = false)
  private String code;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String vat;

  @Embedded
  @AttributeOverrides(value = {
    @AttributeOverride(name = "street", column = @Column(name = "shipping_street", nullable = false)),
    @AttributeOverride(name = "city", column = @Column(name = "shipping_city", nullable = false)),
    @AttributeOverride(name = "zip", column = @Column(name = "shipping_zip", nullable = false, length = 6)),
    @AttributeOverride(name = "state", column = @Column(name = "shipping_state", nullable = false))
  })
  private Address shippingAddress;

  @Embedded
  @AttributeOverrides(value = {
    @AttributeOverride(name = "street", column = @Column(name = "billing_street", nullable = false)),
    @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
    @AttributeOverride(name = "zip", column = @Column(name = "billing_zip", nullable = false, length = 6)),
    @AttributeOverride(name = "state", column = @Column(name = "billing_state", nullable = false))
  })
  private Address billingAddress;

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

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
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
