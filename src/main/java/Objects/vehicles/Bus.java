package Objects.vehicles;

import Objects.people.Human;

public class Bus extends Vehicle<Human> {
    public Bus(int maxSeats) {
        super(maxSeats);
    }
}
