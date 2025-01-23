package lime1st.springjunit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lime1st.springjunit5.PassengerUtil.getExpectedPassenger;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit4에서 JUnit5로 전환
 *
 * RunWith -> ExtendWith
 * Before -> BeforeEach
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringAppTest {

    @Autowired
    private Passenger passenger;
    private Passenger expectedPassenger;


    @BeforeEach
    public void setUp() {
        expectedPassenger = getExpectedPassenger();
    }

    @Test
    public void testInitPassenger() {
        assertEquals(expectedPassenger, passenger);
        System.out.println(passenger);
    }

}