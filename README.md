# Gunjan
# Country Locator Project

## Overview

The Country Locator Project is a Java application that determines the country code based on geographic coordinates (latitude and longitude). It uses GeoJSON data to map country boundaries and performs point-in-polygon checks to identify the country.

## Project Structure

CountryLocatorProject/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── example/
│ │ │ ├── Country.java
│ │ │ ├── CountryLocator.java
│ │ │ └── PerformanceTest.java
│ │ └── resources/
│ │ └── countries.geojson
│ └── test/
│ └── java/
│ └── com/
│ └── example/
│ └── CountryLocatorTest.java
│
|
├── pom.xml
└── README.md


## Dependencies

- **org.json:json** - A JSON parsing library for Java.
- **junit:junit** - A testing framework for Java.


### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven

### Build and Run

1. **Clone the repository**:
   git clone https://github.com/GunjanBaliyan/country-locator.git
   cd CountryLocatorProject

2. **Build the project**:
   mvn compile
   
3. **Run the main application**:
   mvn exec:java -Dexec.mainClass="com.example.PerformanceTest"
   
4. **Run tests**:
   mvn test

# Performance Testing
The PerformanceTest class simulates multiple requests to the CountryLocator to measure its performance. It makes 100 requests per second for 10 seconds, checking if a given latitude and longitude correspond to a specific country.


# FUNCTION DOCUMENTATION-

1. **Country.java**
Country(String code, JSONArray coordinates): Constructs a Country instance.
String getCode(): Gets the ISO A3 code of the country.
JSONArray getCoordinates(): Gets the geographic coordinates of the country.

2. **CountryLocator.java**
CountryLocator(String geoJsonFilePath): Constructs a CountryLocator instance by loading country data from a GeoJSON file.
void loadCountries(String geoJsonFilePath): Loads country data from a GeoJSON file.
String getCountryCode(double lat, double lon): Gets the country code based on the provided latitude and longitude.
boolean isPointInPolygon(double lat, double lon, JSONArray coordinates): Determines if a point is inside a polygon.

3. **PerformanceTest.java**
main(String[] args): Tests the performance of the CountryLocator by making multiple requests in parallel.


# O U T P U T
1. overview
![Screenshot (57)](https://github.com/user-attachments/assets/51f09b9c-1696-4731-916c-85cafc8e582e)

2. compiling
 ![Screenshot (56)](https://github.com/user-attachments/assets/8b5fae5f-c3b7-4b8a-849c-be011ffc2c6d)

4. tests
![Screenshot (58)](https://github.com/user-attachments/assets/84c41a16-3bc8-4169-805c-09e0b2b6c227)

5. test results
![Screenshot (55)](https://github.com/user-attachments/assets/567492b9-195a-4e23-b541-15beb7816785)



