package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.CountriesApplication;
import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.models.CountryList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;

@RestController
public class CountryController {
  @GetMapping("/names/all")
  public ArrayList<Country> getCountries() {
    CountryList result = new CountryList(CountriesApplication.countries.countryList);
    result.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return result.countryList;
  }

  @GetMapping("/names/begin")
  public ArrayList<Country> getCountriesBegin(@RequestParam(value="letter") String letter) {
    CountryList result =
            new CountryList(CountriesApplication.countries.search(c -> c.getName().startsWith(letter)));
    result.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return result.countryList;
  }

  @GetMapping("/names/size")
  public ArrayList<Country> getCountriesByNameLength(@RequestParam(value="letters") int letters) {
    CountryList result =
            new CountryList(CountriesApplication.countries.search(c -> c.getName().length() >= letters));
    result.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return result.countryList;
  }

  @GetMapping("/population/size")
  public ArrayList<Country> getCountriesByPopulation(@RequestParam(value="people") long people) {
    return CountriesApplication.countries.search(c -> c.getPopulation() >= people);
  }

  @GetMapping("/population/min")
  public Country getMinPopulation() {
    CountryList countries = new CountryList(CountriesApplication.countries.countryList);
    countries.countryList.sort(Comparator.comparing(Country::getPopulation));
    return countries.countryList.get(0);
  }

  @GetMapping("/population/max")
  public Country getMaxPopulation() {
    CountryList countries = new CountryList(CountriesApplication.countries.countryList);
    countries.countryList.sort(Comparator.comparing(Country::getPopulation).reversed());
    return countries.countryList.get(0);
  }

  @GetMapping("/age/age")
  public ArrayList<Country> getCountriesByMedianAge(@RequestParam(value="age") int medianAge) {
    return CountriesApplication.countries.search(c -> c.getMedianAge() >= medianAge);
  }

  @GetMapping("/age/min")
  public Country getCountryByMinMedianAge() {
    CountryList countries = new CountryList(CountriesApplication.countries.countryList);
    countries.countryList.sort(Comparator.comparing(Country::getMedianAge));
    return countries.countryList.get(0);
  }

  @GetMapping("/age/max")
  public Country getCountryByMaxMedianAge() {
    CountryList countries = new CountryList(CountriesApplication.countries.countryList);
    countries.countryList.sort(Comparator.comparing(Country::getMedianAge).reversed());
    return countries.countryList.get(0);
  }
}
