package Objects.exceptions;

/** Виникає, коли пасажира, якого висаджують, немає у транспортному засобі. */
public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String message) {
        super(message);
    }
}
