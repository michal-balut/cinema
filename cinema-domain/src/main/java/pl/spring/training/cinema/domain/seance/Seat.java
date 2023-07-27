package pl.spring.training.cinema.domain.seance;

public class Seat {

    int number;

    boolean available;

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return available;
    }

    public void reserve() {
        available = false;
    }

    public Seat(final int number, final boolean available) {
        this.number = number;
        this.available = available;
    }
}
