package com.lambdaschool.countries;

import com.lambdaschool.countries.models.CountryList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountriesApplication {
  public static CountryList countries;

  public static void main(String[] args) {
    countries = new CountryList();
    SpringApplication.run(CountriesApplication.class, args);
  }

}

