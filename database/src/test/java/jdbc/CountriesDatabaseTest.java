package jdbc;

import jdbc.dao.CountryDao;
import jdbc.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static jdbc.JDBCCountriesLoader.COUNTRY_INIT_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @ExtendWith 를 사용해 JUnit Jupiter 테스트에 스프링 테스트 콘텍스트를 통합한다.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class CountriesDatabaseTest {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CountriesLoader countriesLoader;

    private final List<Country> expectedCountryList = new ArrayList<>();
    private final List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        initExpectedCountryLists();
        countriesLoader.loadCountries();
    }

    //  @DirtiesContext 테스트가 Context 를 변경했을 때 사용한다. 이 애너테이션은 테스트마다 새로운 Context 를 제공하도록 한다.
    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    private void initExpectedCountryLists() {
        for (String[] countryInitData : COUNTRY_INIT_DATA) {
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}
