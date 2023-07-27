package pl.spring.training.cinema.domain.seance;

public class Seat {

    private int number;

    private boolean available;

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return available;
    }

	public void setNumber(final int number) {
		this.number = number;
	}

	public void setAvailable(final boolean available) {
		this.available = available;
	}

	public void reserve() {
        available = false;
    }

	public Seat() {
		//needed for mapstruct
	}

    public Seat(final int number, final boolean available) {
        this.number = number;
        this.available = available;
    }
}
