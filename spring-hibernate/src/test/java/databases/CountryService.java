package databases;

import databases.model.Country;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CountryService {

    @PersistenceContext
    private EntityManager em;

    public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"}, {"Romania", "RO"},
            {"Russian Federation", "RU"}, {"Spain", "ES"}, {"Switzerland", "CH"},
            {"United Kingdom", "UK"}, {"United States", "US"}};

    @Transactional
    public void init() {
        for (String[] countryInitData : COUNTRY_INIT_DATA) {
            Country country = new Country(countryInitData[0], countryInitData[1]);
            em.persist(country);
        }
    }

    @Transactional
    public void clear() {
        em.createQuery("delete from Country c").executeUpdate();
    }

    public List getAllCountries() {
        return em.createQuery("select c from Country c").getResultList();
    }

    public List getCountriesStartingWithA() {
        return em.createQuery("select c from Country c where c.name like 'A%'").getResultList();
    }
}