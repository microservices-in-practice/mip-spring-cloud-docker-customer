package com.capgemini.mip.customer.testdata;

import com.capgemini.mip.customer.domain.AddressBuilder;
import com.capgemini.mip.customer.domain.Customer;
import com.capgemini.mip.customer.domain.CustomerBuilder;
import com.capgemini.mip.customer.service.AddressTOBuilder;
import com.capgemini.mip.customer.service.CustomerTO;
import com.capgemini.mip.customer.service.CustomerTOBuilder;

public class TestdataProvider {

  public static Customer provideCustomer() {
    return CustomerBuilder.customer()
      .withName("Cecilia Chapman")
      .withCode("5637401")
      .withVat("563-7401")
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

  public static CustomerTO provideCustomerTO(String code) {
    return CustomerTOBuilder.customer()
      .withName("Cecilia Chapman")
      .withCode(code)
      .withVat("563-7401")
      .withBillingAddress(
        AddressTOBuilder.address()
          .withCity("Mankato")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("Mississippi")
          .build()
      )
      .withShippingAddress(
        AddressTOBuilder.address()
          .withCity("Mankato")
          .withStreet("711-2880 Nulla St.")
          .withZip("96522")
          .withState("Mississippi")
          .build()
      )
      .build();
  }

  public static CustomerTO provideCustomerTO() {
    return provideCustomerTO("5637401");
  }
}
