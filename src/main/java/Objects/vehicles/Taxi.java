package Objects.vehicles;

import Objects.people.Human;

public class Taxi extends Car<Human> {
    public Taxi(int maxSeats) {
        super(maxSeats);
    }
}
