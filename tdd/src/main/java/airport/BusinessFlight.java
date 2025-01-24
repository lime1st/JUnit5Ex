package airport;

public class BusinessFlight extends Flight {

    public BusinessFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        // 비즈니스편은 vip 승객만 추가할 수 있다.
        if (passenger.isVip()) {
            return passengers.add(passenger);
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        //  비즈니스 항공편은 승객을 지울 수 없다고 했으므로 false
        return false;
    }
}
