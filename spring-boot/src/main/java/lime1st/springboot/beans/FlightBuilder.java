package lime1st.springboot.beans;

import lime1st.springboot.model.Country;
import lime1st.springboot.model.Flight;
import lime1st.springboot.model.Passenger;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FlightBuilder {

    private final Map<String, Country> countriesMap = new HashMap<>();

    public FlightBuilder() throws IOException {
        try (BufferedReader reader = new BufferedReader(
//                new FileReader("src/main/resources/countries_information.csv")
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("countries_information.csv")),
                        StandardCharsets.UTF_8)
        )) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] countriesString = line.toString().split(";");
                    Country country = new Country(countriesString[0].trim(),
                            countriesString[1].trim());
                    countriesMap.put(countriesString[1].trim(), country);
                }
            } while (line != null);
        }
    }

    @Bean
    Map<String, Country> getCountriesMap() {
        return Collections.unmodifiableMap(countriesMap);
    }

    @Bean
    public Flight buildFlightFromCsv() throws IOException {
        Flight flight = new Flight("AA1234", 20);
        try (BufferedReader reader = new BufferedReader(
//                new FileReader("flights_information.csv")
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("flights_information.csv")),
                        StandardCharsets.UTF_8)
        )) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] passengerString = line.split(";");
                    Passenger passenger = new Passenger(passengerString[0].trim());
                    passenger.setCountry(countriesMap.get(passengerString[1].trim()));
                    passenger.setIsRegistered(false);
                    flight.addPassenger(passenger);
                }
            } while (line != null);
        }
        return flight;
    }
}
