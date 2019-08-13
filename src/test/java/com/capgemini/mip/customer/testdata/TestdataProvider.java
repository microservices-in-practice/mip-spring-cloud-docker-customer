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
      .withCode("112332131")
      .withVat("111122223333")
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
      .withVat("111122223333")
      .withBillingAddress(
        AddressBuilder.address()
          .withCity("Mankato Mississippi")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("USA")
          .build()
      )
      .withShippingAddress(
        AddressBuilder.address()
          .withCity("Mankato Mississippi")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("USA")
          .build()
      )
      .build();
  }

  public static Customer provideCustomer() {
    return provideCustomer("112332131");
  }
}
