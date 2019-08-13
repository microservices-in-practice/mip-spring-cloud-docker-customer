package com.capgemini.mip.customer.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public DozerBeanMapper beanMapper() {
    return new DozerBeanMapper();
  }


}
