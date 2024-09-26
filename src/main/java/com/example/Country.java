package com.example;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a country with its code and geographic coordinates.
 */
public class Country {
    private String code;
    private List<List<double[]>> polygons;

    
    /**
     * Constructs a Country instance.
     *
     * @param code        The ISO A3 code of the country.
     * @param coordinates The geographic coordinates of the country.
     */
    public Country(String code, JSONArray coordinates) {
        this.code = code;
        this.polygons = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            JSONArray polygon = coordinates.getJSONArray(i).getJSONArray(0);
            List<double[]> points = new ArrayList<>();
            for (int j = 0; j < polygon.length(); j++) {
                JSONArray point = polygon.getJSONArray(j);
                points.add(new double[]{point.getDouble(1), point.getDouble(0)});
            }
            polygons.add(points);
        }
    }

    
    /**
     * Gets the ISO A3 code of the country.
     *
     * @return The country code.
     */
    public String getCode() {
        return code;
    }

    
    
    
    /**
     * Checks if the given latitude and longitude are within the boundaries of this country.
     *
     * @param latitude  The latitude coordinate to check.
     * @param longitude The longitude coordinate to check.
     * @return {@code true} if the coordinates are within the country's boundaries,
     *         {@code false} otherwise.
     */
    public boolean contains(double latitude, double longitude) {
        for (List<double[]> polygon : polygons) {
            if (isPointInPolygon(polygon, latitude, longitude)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a point is inside a polygon.
     *
     * @param lat The latitude of the point.
     * @param lon The longitude of the point.
     * @param coordinates The coordinates of the polygon.
     * @return True if the point is inside the polygon, otherwise false.
     */
    private boolean isPointInPolygon(List<double[]> polygon, double latitude, double longitude) {
        boolean result = false;
        int j = polygon.size() - 1;
        for (int i = 0; i < polygon.size(); i++) {
            if ((polygon.get(i)[1] < longitude && polygon.get(j)[1] >= longitude) || 
                (polygon.get(j)[1] < longitude && polygon.get(i)[1] >= longitude)) {
                if (polygon.get(i)[0] + (longitude - polygon.get(i)[1]) / (polygon.get(j)[1] - polygon.get(i)[1]) * 
                    (polygon.get(j)[0] - polygon.get(i)[0]) < latitude) {
                    result = !result;
                }
            }
            j = i;
        }
        return result;
    }
}
