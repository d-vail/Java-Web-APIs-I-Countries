package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.CountriesApplication;
import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.models.CountryList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CountryController {
  @GetMapping("/names/all")
  public ArrayList<Country> getCountries() {
    CountriesApplication.countries.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return CountriesApplication.countries.countryList;
  }

  @GetMapping("/names/begin")
  public ArrayList<Country> getCountriesBegin(@RequestParam(value="letter") String letter) {
    CountryList result =
            new CountryList(CountriesApplication.countries.search(c -> c.getName().startsWith(letter)));
    result.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return result.countryList;
  }
}
