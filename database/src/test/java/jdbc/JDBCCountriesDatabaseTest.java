package jdbc;

import jdbc.dao.JDBCCountryDao;
import jdbc.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static jdbc.JDBCCountriesLoader.COUNTRY_INIT_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JDBCCountriesDatabaseTest {

    private final JDBCCountryDao JDBCCountryDao = new JDBCCountryDao();
    private final JDBCCountriesLoader JDBCCountriesLoader = new JDBCCountriesLoader();

    private final List<Country> expectedCountryList = new ArrayList<>();
    private final List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    @BeforeEach
    void setUp() {
        TablesManager.createTable();
        initExpectedCountryLists();
        JDBCCountriesLoader.loadCountries();
    }

    @Test
    void testCountryList() {
        List<Country> countryList = JDBCCountryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    public void testCountryListStartsWithA() {
        List<Country> countryList = JDBCCountryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    @AfterEach
    void dropDown() {
        TablesManager.dropTable();
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