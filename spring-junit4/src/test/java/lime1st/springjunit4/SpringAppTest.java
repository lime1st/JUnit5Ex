package lime1st.springjunit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static lime1st.springjunit4.PassengerUtil.getExpectedPassenger;
import static org.junit.Assert.assertEquals;

/**
 * SimpleAppTest 를 테스트 콘텍스트 프레임워크를 사용해 리팩토링
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringAppTest {

    @Autowired
    private Passenger passenger;

    private Passenger expectedPassenger;

    @Before
    public void setUp() {
        expectedPassenger = getExpectedPassenger();
    }

    @Test
    public void testInitPassenger() {
        assertEquals(expectedPassenger, passenger);
        System.out.println(passenger);
    }
}
