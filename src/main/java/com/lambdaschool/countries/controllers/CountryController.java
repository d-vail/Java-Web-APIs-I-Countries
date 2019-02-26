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
  private CountryList countryData = CountriesApplication.countries;

  private ArrayList<Country> clone(ArrayList<Country> countries) {
    CountryList clone = new CountryList(countries);
    return clone.countryList;
  }

  @GetMapping("/names/all")
  public ArrayList<Country> getCountries() {
    ArrayList<Country> result = clone(countryData.countryList);
    result.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return result;
  }

  @GetMapping("/names/begin")
  public ArrayList<Country> getCountriesByPrefix(@RequestParam(value="letter") String letter) {
    ArrayList<Country> result = clone(countryData.search(c -> c.getName().startsWith(letter)));
    result.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return result;
  }

  @GetMapping("/names/size")
  public ArrayList<Country> getCountriesByNameLength(@RequestParam(value="letters") int letters) {
    ArrayList<Country> result =
            clone(countryData.search(c -> c.getName().length() >= letters));
    result.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    return result;
  }

  @GetMapping("/population/size")
  public ArrayList<Country> getCountriesByPopulation(@RequestParam(value="people") long people) {
    return countryData.search(c -> c.getPopulation() >= people);
  }

  @GetMapping("/population/min")
  public Country getCountryByMinPopulation() {
    ArrayList<Country> result = clone(countryData.countryList);
    result.sort(Comparator.comparing(Country::getPopulation));
    return result.get(0);
  }

  @GetMapping("/population/max")
  public Country getCountryByMaxPopulation() {
    ArrayList<Country> result = clone(countryData.countryList);
    result.sort(Comparator.comparing(Country::getPopulation).reversed());
    return result.get(0);
  }

  @GetMapping("/age/age")
  public ArrayList<Country> getCountriesByMedianAge(@RequestParam(value="age") int medianAge) {
    return countryData.search(c -> c.getMedianAge() >= medianAge);
  }

  @GetMapping("/age/min")
  public Country getCountryByMinMedianAge() {
    ArrayList<Country> result = clone(countryData.countryList);
    result.sort(Comparator.comparing(Country::getMedianAge));
    return result.get(0);
  }

  @GetMapping("/age/max")
  public Country getCountryByMaxMedianAge() {
    ArrayList<Country> result = clone(countryData.countryList);
    result.sort(Comparator.comparing(Country::getMedianAge).reversed());
    return result.get(0);
  }
}
