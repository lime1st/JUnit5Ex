package hibernate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountriesHibernateTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    private final List<Country> expectedCountryList = new ArrayList<>();
    private final List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    public static final String[][] COUNTRY_INIT_DATA = {
            { "Australia", "AU" }, { "Canada", "CA" }, { "France", "FR" },
            { "Germany", "DE" }, { "Italy", "IT" }, { "Japan", "JP" },
            { "Romania", "RO" }, { "Russian Federation", "RU" }, { "Spain", "ES" },
            { "Switzerland", "CH" }, { "United Kingdom", "UK" }, { "United States", "US" }
    };

    @BeforeEach
    void setUp() {
        initExpectedCountryLists();

        emf = Persistence.createEntityManagerFactory("database.hibernate");
        em = emf.createEntityManager();

        em.getTransaction().begin();

        for (String[] countryInitData : COUNTRY_INIT_DATA) {
            Country country = new Country(countryInitData[0], countryInitData[1]);
            em.persist(country);
        }

        em.getTransaction().commit();
    }

    @Test
    void testCountryList() {
        List countryList = em.createQuery("select c from Country c").getResultList();
        assertNotNull(countryList);
        assertEquals(COUNTRY_INIT_DATA.length, countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    void testCountryListStartsWithA() {
        List countryList = em.createQuery("select c from Country c where c.name like 'A%'").getResultList();
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    @AfterEach
    void dropDown() {
        em.close();
        emf.close();
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
