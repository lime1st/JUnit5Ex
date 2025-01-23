package lime1st.springjunit4;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static lime1st.springjunit4.PassengerUtil.getExpectedPassenger;
import static org.junit.Assert.*;

public class SimpleAppTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "classpath:application-context.xml";

    private ClassPathXmlApplicationContext context;

    private Passenger expectedPassenger;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
        expectedPassenger = getExpectedPassenger();
    }

    @Test
    public void testInitPassenger() {
        // application-context.xml 에서 정의된 빈을 가져온다.
        Passenger passenger = (Passenger) context.getBean("passenger");

        // xml 에 정의된 빈과 expectedPassenger(새로생성한) 빈이 같은지 확인
        assertEquals(expectedPassenger, passenger);
        System.out.println(passenger);
    }

}