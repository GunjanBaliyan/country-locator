package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountryLocatorTest {

    @Test
    public void testValidCoordinates() {
        CountryLocator locator = new CountryLocator();
        assertEquals("US", locator.getCountryCode(37.7749, -122.4194)); // San Francisco, USA
        assertEquals("IN", locator.getCountryCode(28.6139, 77.2090)); // New Delhi, India
    }

    @Test
    public void testInvalidCoordinates() {
        CountryLocator locator = new CountryLocator();
        assertEquals("Unknown", locator.getCountryCode(0.0, 0.0)); // Middle of the ocean
    }

    @Test
    public void testEdgeCaseCoordinates() {
        CountryLocator locator = new CountryLocator();
        assertEquals("CA", locator.getCountryCode(49.2827, -123.1207)); // Vancouver, Canada
    }
}
