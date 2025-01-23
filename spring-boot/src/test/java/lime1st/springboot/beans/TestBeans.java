package lime1st.springboot.beans;

import lime1st.springboot.model.Country;
import lime1st.springboot.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * spring 에서 application-context.xml 파일에 설정했던 빈을 Configuration 으로 구성했다.
 */
@TestConfiguration
public class TestBeans {

    @Bean
    Passenger createPassenger() {
        Passenger passenger = new Passenger("John Smith");
        passenger.setCountry(createCountry());
        passenger.setIsRegistered(false);
        return passenger;
    }

    @Bean
    Country createCountry() {
        Country country = new Country("USA", "US");
        return country;
    }
}
