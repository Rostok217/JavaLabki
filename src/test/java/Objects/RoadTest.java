package Objects;

import Objects.people.Firefighter;
import Objects.people.Passenger;
import Objects.people.Policeman;
import Objects.vehicles.Bus;
import Objects.vehicles.FireTruck;
import Objects.vehicles.PoliceCar;
import Objects.vehicles.Taxi;
import org.junit.jupiter.api.Test;
import Objects.people.*;
import Objects.vehicles.*;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {

    @Test
    void emptyRoadHasNoHumans() {
        assertEquals(0, new Road().getCountOfHumans());
    }

    @Test
    void countsHumansInAllVehicles() {
        Bus bus = new Bus(20);
        bus.board(new Passenger("Іван"));
        bus.board(new Firefighter("Петро"));

        Taxi taxi = new Taxi(4);
        taxi.board(new Policeman("Олег"));

        FireTruck truck = new FireTruck(6);
        truck.board(new Firefighter("Василь"));
        truck.board(new Firefighter("Микола"));

        PoliceCar police = new PoliceCar(2);
        police.board(new Policeman("Дмитро"));

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(truck);
        road.addCarToRoad(police);

        assertEquals(4, road.carsInRoad.size());
        assertEquals(6, road.getCountOfHumans());
    }

    @Test
    void countChangesAfterDisembark() {
        Policeman p = new Policeman("Дмитро");
        PoliceCar police = new PoliceCar(2);
        police.board(p);

        Road road = new Road();
        road.addCarToRoad(police);
        assertEquals(1, road.getCountOfHumans());

        police.disembark(p);
        assertEquals(0, road.getCountOfHumans());
    }

    @Test
    void emptyVehicleAddsNothing() {
        Road road = new Road();
        road.addCarToRoad(new Bus(30));
        road.addCarToRoad(new FireTruck(6));
        assertEquals(0, road.getCountOfHumans());
    }
}
