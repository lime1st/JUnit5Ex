package lime1st.springboot.model;

import lime1st.springboot.exception.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    @Autowired
    private PassengerRepository repository;

    @Autowired
    private Map<String, Country> countriesMap;

    @GetMapping
    List<Passenger> findAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Passenger createPassenger(@RequestBody Passenger passenger) {
        return repository.save(passenger);
    }

    @GetMapping("/{id}")
    Passenger findPassenger(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @PatchMapping("/{id}")
    Passenger patchPassenger(@RequestBody Map<String, String> updates, @PathVariable("id") Long id) {
        return repository.findById(id)
                .map(passenger -> {
                    String name = updates.get("name");
                    if (null != name) {
                        passenger.setName(name);
                    }

                    Country country = countriesMap.get(updates.get("country"));
                    if (null != country) {
                        passenger.setCountry(country);
                    }

                    String isRegistered = updates.get("isRegistered");
                    if (null != isRegistered) {
                        passenger.setIsRegistered(isRegistered.equalsIgnoreCase("true") ? true : false);
                    }
                    return repository.save(passenger);
                })
                .orElseGet(() -> {
                    throw new PassengerNotFoundException(id);
                });
    }

    @DeleteMapping("/{id}")
    void deletePassenger(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}
