package airport;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    @DisplayName("Given 이코노미 항공편")
    @Nested
    class EconomyFlightsTest {

        private Flight economyFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {

            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하고 삭제도 가능하다")
            public void testEconomyFlightRegularPassenger() {
                assertAll("일반 승객은 이코노미 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertEquals("Mike", new ArrayList<>(economyFlight.getPassengersSet())
                                .get(0).getName()),
                        () -> assertTrue(economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengersSet().size())
                );
            }

            @DisplayName("Then 이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다")
            @RepeatedTest(5)
            public void testEconomyFlightRegularPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(mike);
                }

                assertAll("이코노미 항공편에서 일반 승객을 중복해서 추가할 수 없는지 검증",
                        ()->assertEquals(1, economyFlight.getPassengersSet().size()),
                        ()->assertTrue(economyFlight.getPassengersSet().contains(mike)),
                        ()-> assertEquals("Mike", new ArrayList<>(economyFlight.getPassengersSet())
                                .get(0).getName())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {
            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다")
            public void testEconomyFlightVipPassenger() {
                assertAll("VIP 승객은 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertEquals("James", new ArrayList<>(economyFlight.getPassengersSet())
                                .get(0).getName()),
                        () -> assertFalse(economyFlight.removePassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size())
                );

            }
        }
    }

    @DisplayName("Given 비즈니스 항공편")
    @Nested
    class BusinessFlightTest {

        private Flight businessFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }


        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {

            @Test
            @DisplayName("Then 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다")
            public void testBusinessFlightRegularPassenger() {
                assertAll("일반 승객은 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                        () -> assertFalse(businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                        () -> assertFalse(businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersSet().size())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {

            @Test
            @DisplayName("Then 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다")
            public void testBusinessFlightVipPassenger() {
                assertAll("VIP 승객은 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                        () -> assertTrue(businessFlight.addPassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                        () -> assertFalse(businessFlight.removePassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size())
                );
            }
        }
    }

    @DisplayName("Given 프리미어머 항공편")
    @Nested
    class PremiumFlightTest {

        private Flight premiumFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When 일반 승객")
        class RegularPassenger {

            @Test
            @DisplayName("Then 프리미엄 항공편에서 추가가 불가능, 삭제도 불가능")
            public void testPremiumFlightRegularPassenger() {
                assertAll("일반 승객은 프리미엄 항공편에서 추가, 삭제 불가 검증",
                        () -> assertFalse(premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                        () -> assertFalse(premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객")
        class VipPassenger {

            @Test
            @DisplayName("Then 프리미엄 항공 추가, 삭제 가능")
            public void testPremiumFlightVipPassenger() {
                assertAll("VIP 승객은 프리미엄 항공편 추가, 삭제 가능 점증",
                        ()->assertTrue(premiumFlight.addPassenger(james)),
                        ()->assertEquals(1, premiumFlight.getPassengersSet().size()),
                        ()->assertTrue(premiumFlight.removePassenger(james)),
                        ()->assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }
        }
    }
}