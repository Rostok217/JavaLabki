package Objects;

import Objects.people.Firefighter;
import Objects.people.Passenger;
import Objects.people.Policeman;
import Objects.vehicles.Bus;
import Objects.vehicles.FireTruck;
import Objects.vehicles.PoliceCar;
import Objects.vehicles.Taxi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Objects.exceptions.NoFreeSeatsException;
import Objects.exceptions.PassengerNotFoundException;
import Objects.people.*;
import Objects.vehicles.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Passenger passenger;
    private Firefighter firefighter;
    private Policeman policeman;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("Іван");
        firefighter = new Firefighter("Петро");
        policeman = new Policeman("Олег");
    }

    @Test
    void busCarriesAnyPassengers() {
        Bus bus = new Bus(10);
        bus.board(passenger);
        bus.board(firefighter);
        bus.board(policeman);
        assertEquals(3, bus.getOccupiedSeats());
        assertEquals(10, bus.getMaxSeats());
    }

    @Test
    void taxiCarriesAnyPassengers() {
        Taxi taxi = new Taxi(4);
        taxi.board(passenger);
        taxi.board(firefighter);
        taxi.board(policeman);
        assertEquals(3, taxi.getOccupiedSeats());
    }

    @Test
    void fireTruckCarriesFirefighters() {
        FireTruck truck = new FireTruck(5);
        truck.board(firefighter);
        truck.board(new Firefighter("Василь"));
        assertEquals(2, truck.getOccupiedSeats());
    }

    @Test
    void policeCarCarriesPolicemen() {
        PoliceCar car = new PoliceCar(2);
        car.board(policeman);
        assertEquals(1, car.getOccupiedSeats());
        assertTrue(car.getPassengers().contains(policeman));
    }

    @Test
    void boardingWhenFullThrowsException() {
        PoliceCar car = new PoliceCar(1);
        car.board(policeman);
        assertThrows(NoFreeSeatsException.class, () -> car.board(new Policeman("Тарас")));
        assertEquals(1, car.getOccupiedSeats());
    }

    @Test
    void disembarkRemovesPassenger() {
        Taxi taxi = new Taxi(4);
        taxi.board(passenger);
        taxi.disembark(passenger);
        assertEquals(0, taxi.getOccupiedSeats());
    }

    @Test
    void disembarkMissingPassengerThrowsException() {
        Bus bus = new Bus(10);
        bus.board(passenger);
        assertThrows(PassengerNotFoundException.class, () -> bus.disembark(firefighter));
        assertEquals(1, bus.getOccupiedSeats());
    }

    @Test
    void boardAllAcceptsSubtypeCollection() {
        Bus bus = new Bus(10);
        List<Firefighter> crew = List.of(new Firefighter("А"), new Firefighter("Б"));
        bus.boardAll(crew); // List<Firefighter> у Vehicle<Human> завдяки ? extends T
        assertEquals(2, bus.getOccupiedSeats());
    }

    @Test
    void boardAllWithoutEnoughSeatsThrowsException() {
        FireTruck truck = new FireTruck(2);
        List<Firefighter> crew = List.of(new Firefighter("А"), new Firefighter("Б"), new Firefighter("В"));
        assertThrows(NoFreeSeatsException.class, () -> truck.boardAll(crew));
        assertEquals(0, truck.getOccupiedSeats());
    }

    @Test
    void invalidSeatCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Bus(0));
    }
}
