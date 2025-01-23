package lime1st.springboot;

import lime1st.springboot.beans.TestBeans;
import lime1st.springboot.model.Passenger;
import lime1st.springboot.registration.PassengerRegistrationEvent;
import lime1st.springboot.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestBeans.class)
public class RegistrationTest {

    @Autowired
    private Passenger passenger;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testPersonRegistration() {
        registrationManager.getApplicationContext()
                .publishEvent(new PassengerRegistrationEvent(passenger));
        System.out.println("After registering: ");
        System.out.println(passenger);
        assertTrue(passenger.isRegistered());
    }
}
