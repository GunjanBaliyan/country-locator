package com.example;

import java.io.InputStream;
import java.util.*;
import org.json.*;


/**
 * Provides functionality to locate a country based on latitude and longitude.
 */
public class CountryLocator {
    private List<Country> countries;

    
    /**
     * Constructs a CountryLocator instance by loading country data from a GeoJSON file.
     */
    public CountryLocator() {
        countries = new ArrayList<>();
        loadCountryData();
    }

    /**
     * Loads country data from a GeoJSON file.
     *
     */
    private void loadCountryData() {
        try (InputStream is = getClass().getResourceAsStream("/countries.geojson")) {
            if (is == null) {
                throw new RuntimeException("Country boundaries file not found");
            }
            String jsonTxt = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
            JSONObject json = new JSONObject(jsonTxt);
            JSONArray features = json.getJSONArray("features");
            for (int i = 0; i < features.length(); i++) {
                JSONObject feature = features.getJSONObject(i);
                String countryCode = feature.getJSONObject("properties").getString("ISO_A2");
                JSONArray coordinates = feature.getJSONObject("geometry").getJSONArray("coordinates");
                Country country = new Country(countryCode, coordinates);
                countries.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the country code based on the provided latitude and longitude.
     *
     * @param lat The latitude.
     * @param lon The longitude.
     * @return The country code if found, otherwise "Unknown".
     */

    public String getCountryCode(double latitude, double longitude) {
        for (Country country : countries) {
            if (country.contains(latitude, longitude)) {
                return country.getCode();
            }
        }
        return "Unknown";
    }

    public static void main(String[] args) {
        CountryLocator locator = new CountryLocator();
        double latitude = 37.7749;
        double longitude = -122.4194;
        System.out.println(locator.getCountryCode(latitude, longitude));
    }
}

