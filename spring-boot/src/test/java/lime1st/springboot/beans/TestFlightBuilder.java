package lime1st.springboot.beans;

import lime1st.springboot.model.Country;
import lime1st.springboot.model.Flight;
import lime1st.springboot.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class TestFlightBuilder {

    private static final Map<String, Country> countriesMap = new HashMap<>();

    static {
        countriesMap.put("AU", new Country("Australia", "AU"));
        countriesMap.put("US", new Country("USA", "US"));
        countriesMap.put("UK", new Country("United Kingdom", "UK"));
    }

    @Bean
    Flight buildFlightFromCsv() throws IOException {
        Flight flight = new Flight("AA1234", 20);
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/flights_information.csv")
        )) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] passengerString = line.toString().split(";");
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
