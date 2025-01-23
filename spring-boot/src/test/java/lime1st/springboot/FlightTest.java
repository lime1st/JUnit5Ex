package lime1st.springboot;

import lime1st.springboot.beans.TestFlightBuilder;
import lime1st.springboot.model.Flight;
import lime1st.springboot.model.Passenger;
import lime1st.springboot.registration.PassengerRegistrationEvent;
import lime1st.springboot.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestFlightBuilder.class)
public class FlightTest {

    @Autowired
    private Flight flight;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testFlightPassengerRegistration() {
        for (Passenger passenger : flight.getPassengers()) {
            assertFalse(passenger.isRegistered());
            registrationManager.getApplicationContext()
                    .publishEvent(new PassengerRegistrationEvent(passenger));
        }

        System.out.println("모든 승객이 등록되었는지 확인");

        for (Passenger passenger : flight.getPassengers()) {
            assertTrue(passenger.isRegistered());
        }
    }
}
