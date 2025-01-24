package jbehave.airport;

public class PremiumFlight extends Flight {

    public PremiumFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        //  프리미엄 편은 VIP 만 추가
        if (passenger.isVip()) {
            return passengers.add(passenger);
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        //  프리미엄 편은 VIP 만 삭제
        if (passenger.isVip()) {
            return passengers.remove(passenger);
        }
        return false;
    }
}
