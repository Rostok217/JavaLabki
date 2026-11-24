package Objects;

import Objects.people.Human;
import Objects.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

/** Ділянка дороги, на якій знаходяться транспортні засоби. */
public class Road {
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    /** Кількість людей, яких перевозять усі машини на ділянці дороги. */
    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Human> car : carsInRoad) {
            count += car.getOccupiedSeats();
        }
        return count;
    }

    public void addCarToRoad(Vehicle<? extends Human> car) {
        carsInRoad.add(car);
    }
}
