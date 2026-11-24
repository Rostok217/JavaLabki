package Objects.exceptions;

/** Виникає, коли всі місця у транспортному засобі зайняті. */
public class NoFreeSeatsException extends RuntimeException {
    public NoFreeSeatsException(String message) {
        super(message);
    }
}
