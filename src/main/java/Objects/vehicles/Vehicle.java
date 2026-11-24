package Objects.vehicles;

import Objects.exceptions.NoFreeSeatsException;
import Objects.exceptions.PassengerNotFoundException;
import Objects.people.Human;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Узагальнений транспортний засіб
 */
public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    protected Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути більшою за 0");
        }
        this.maxSeats = maxSeats;
    }

    /** Максимальна кількість місць. */
    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    /** Посадка пасажира. Кидає NoFreeSeatsException, якщо вільних місць немає. */
    public void board(T passenger) {
        if (passengers.size() >= maxSeats) {
            throw new NoFreeSeatsException(getClass().getSimpleName()
                    + ": усі " + maxSeats + " місць зайнято, " + passenger + " не може сісти");
        }
        passengers.add(passenger);
    }

    public void boardAll(Collection<? extends T> group) {
        if (passengers.size() + group.size() > maxSeats) {
            throw new NoFreeSeatsException(getClass().getSimpleName()
                    + ": недостатньо місць для групи з " + group.size() + " осіб");
        }
        passengers.addAll(group);
    }

    /** Висадка пасажира. Кидає PassengerNotFoundException, якщо його немає в транспорті. */
    public void disembark(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException(getClass().getSimpleName()
                    + ": пасажира " + passenger + " немає у транспорті");
        }
    }

    /** Список пасажирів*/
    public List<T> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + getOccupiedSeats() + "/" + maxSeats + "]";
    }
}
