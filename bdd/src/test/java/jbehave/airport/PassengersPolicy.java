package jbehave.airport;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PassengersPolicy {

    private Flight economyFlight;
    private Flight businessFlight;
    private Flight premiumFlight;
    private Passenger mike;
    private Passenger john;

    @Given("이코노미 항공편에서")
    public void givenEconomyFlight() {
        economyFlight = new EconomyFlight("1");
    }

    @When("일반 승객은")
    public void whenRegularPassenger() {
        mike = new Passenger("Mike", false);
    }

    @Then("이코노미 항공편에서 추가가 가능하고 삭제도 가능하다")
    public void thenCanAddAndRemoveForEconomyFlight() {
        assertAll("이코노미 항공편에서 일반 승객에 대한 결괏값 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertTrue(economyFlight.addPassenger(mike)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertEquals("Mike", new ArrayList<>(economyFlight.getPassengersSet())
                        .get(0).getName()),
                ()->assertTrue(economyFlight.removePassenger(mike)),
                ()->assertEquals(0, economyFlight.getPassengersSet().size())
        );
    }

    @Then("이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다")
    public void then1() {
        for (int i = 0; i < 10; i++) {
            economyFlight.addPassenger(mike);
        }
        assertAll("이코노미 항공편에 일반 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                () -> assertEquals("Mike", new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName())
        );
    }

    @When("VIP 승객은")
    public void whenVip() {
        john = new Passenger("John", true);
    }

    @Then("이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다")
    public void thenDoAddAndDoNotRemoveForEconomyFlight() {
        assertAll("VIP 승객은 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertTrue(economyFlight.addPassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertEquals("John", new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName()),
                () -> assertFalse(economyFlight.removePassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size())
        );
    }

    @Then("이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없다")
    public void then2() {
        for (int i = 0; i < 10; i++) {
            economyFlight.addPassenger(john);
        }

        assertAll("이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                () -> assertEquals("John", new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName())
        );
    }

    @Given("비즈니스 항공편에서")
    public void givenBusinessFlight() {
        businessFlight = new BusinessFlight("2");
    }

    @Then("비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다")
    public void thenDoNotAddAndRemoveForBusinessFlight() {
        assertAll("일반 승객은 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertFalse(businessFlight.addPassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                () -> assertFalse(businessFlight.removePassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengersSet().size())
        );
    }

    @Then("비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다")
    public void thenDoAddAndDoNotRemoveForBusinessFlight() {
        assertAll("VIP 승객은 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertTrue(businessFlight.addPassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                () -> assertFalse(businessFlight.removePassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengersSet().size())
        );
    }

    @Then("비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없다")
    public void then3() {
        for (int i = 0; i < 10; i++) {
            businessFlight.addPassenger(john);
        }

        assertAll("비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                () -> assertTrue(businessFlight.getPassengersSet().contains(john)),
                () -> assertEquals("John", new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName())
        );
    }

    @Given("프리미엄 항공편에서")
    public void givenPremiumFlight() {
        premiumFlight = new PremiumFlight("3");
    }

    @Then("프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능하다")
    public void thenDoNotAddAndRemoveForPremiumFlight() {
        assertAll("일반 승객은 프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertFalse(premiumFlight.addPassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                () -> assertFalse(premiumFlight.removePassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size())
        );
    }

    @Then("프리미엄 항공편에서 추가가 가능하고 삭제도 가능하다")
    public void thenDoAddAndRemoveForPremiumFlight() {
        assertAll("VIP 승객은 프리미엄 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                () -> assertTrue(premiumFlight.addPassenger(john)),
                () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                () -> assertTrue(premiumFlight.removePassenger(john)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size())
        );
    }

    @Then("프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없다")
    public void then4() {
        for (int i = 0; i < 10; i++) {
            premiumFlight.addPassenger(john);
        }

        assertAll("프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                () -> assertTrue(premiumFlight.getPassengersSet().contains(john)),
                () -> assertEquals("John", new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName())
        );
    }
}
