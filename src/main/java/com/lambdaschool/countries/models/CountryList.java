package com.lambdaschool.countries.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CountryList {
  public ArrayList<Country> countryList = new ArrayList<>();

  public CountryList(String jsonFile) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      File file = new ClassPathResource(jsonFile).getFile();
      countryList = mapper.readValue(file, new TypeReference<ArrayList<Country>>(){});
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public CountryList(ArrayList<Country> countries) {
    countryList.addAll(countries);
  }

  public ArrayList<Country> search(Search conditions) {
    ArrayList<Country> results = new ArrayList<>();

    for (Country c : countryList) {
      if(conditions.test(c)) {
        results.add(c);
      }
    }

    return results;
  }
}
