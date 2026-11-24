package Objects;

import Objects.exceptions.NoFreeSeatsException;
import Objects.exceptions.PassengerNotFoundException;
import Objects.people.Firefighter;
import Objects.people.Passenger;
import Objects.people.Policeman;
import Objects.vehicles.*;
import Objects.people.*;
import Objects.vehicles.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus(30);
        Taxi taxi = new Taxi(4);
        FireTruck fireTruck = new FireTruck(6);
        PoliceCar policeCar = new PoliceCar(2);

        bus.board(new Passenger("Іван"));
        bus.board(new Firefighter("Петро"));
        bus.board(new Policeman("Олег"));
        bus.boardAll(List.of(new Firefighter("Андрій"), new Firefighter("Сергій")));

        taxi.board(new Passenger("Марія"));
        fireTruck.board(new Firefighter("Василь"));
        fireTruck.board(new Firefighter("Микола"));

        Policeman p1 = new Policeman("Дмитро");
        policeCar.board(p1);
        policeCar.board(new Policeman("Тарас"));

        // policeCar.board(new Passenger("Анна")); // помилка компіляції: не той тип пасажира
        // fireTruck.board(new Policeman("Юрій")); // помилка компіляції

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        for (Vehicle<?> v : road.carsInRoad) {
            System.out.println(v + " -> " + v.getPassengers());
        }
        System.out.println("Людей на дорозі: " + road.getCountOfHumans());

        try {
            policeCar.board(new Policeman("Зайвий"));
        } catch (NoFreeSeatsException e) {
            System.out.println("Виняток: " + e.getMessage());
        }
        try {
            taxi.disembark(new Passenger("Невідомий"));
        } catch (PassengerNotFoundException e) {
            System.out.println("Виняток: " + e.getMessage());
        }

        policeCar.disembark(p1);
        System.out.println("Після висадки " + p1 + ": " + policeCar);
        System.out.println("Людей на дорозі: " + road.getCountOfHumans());
    }
}
