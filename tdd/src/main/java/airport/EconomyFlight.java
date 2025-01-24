package airport;

public class EconomyFlight extends Flight {

    public EconomyFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        // 이코노미편에는 승객 추가에 제한이 없다.
        return passengers.add(passenger);
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        //  vip 승객이 아닐 때만 삭제할 수 있다.
        if (!passenger.isVip()) {
            return passengers.remove(passenger);
        }
        return false;
    }
}
