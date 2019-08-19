package com.capgemini.mip.customer.testdata;

import com.capgemini.mip.customer.domain.AddressEmbeddableBuilder;
import com.capgemini.mip.customer.domain.CustomerEntity;
import com.capgemini.mip.customer.domain.CustomerEntityBuilder;
import com.capgemini.mip.customer.service.AddressBuilder;
import com.capgemini.mip.customer.service.Customer;
import com.capgemini.mip.customer.service.CustomerBuilder;

public class TestdataProvider {

  public static CustomerEntity provideCustomerEntity() {
    return CustomerEntityBuilder.customerEntity()
      .withName("Cecilia Chapman")
      .withCode("5637401")
      .withVat("563-7401")
      .withBillingAddress(
        AddressEmbeddableBuilder.address()
          .withCity("Mankato Mississippi")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("USA")
          .build()
      )
      .withShippingAddress(
        AddressEmbeddableBuilder.address()
          .withCity("Mankato Mississippi")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("USA")
          .build()
      )
      .build();
  }

  public static Customer provideCustomer(String code) {
    return CustomerBuilder.customer()
      .withName("Cecilia Chapman")
      .withCode(code)
      .withVat("563-7401")
      .withBillingAddress(
        AddressBuilder.address()
          .withCity("Mankato")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("Mississippi")
          .build()
      )
      .withShippingAddress(
        AddressBuilder.address()
          .withCity("Mankato")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("Mississippi")
          .build()
      )
      .build();
  }

  public static Customer provideCustomer() {
    return provideCustomer("5637401");
  }
}
