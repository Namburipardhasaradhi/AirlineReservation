package in.srkr.project3.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Booking {
    private Flight flight;
    private List<DetailsOfPassenger> passengers;

    public Booking(Flight flight) {
        this.flight = flight;
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(DetailsOfPassenger passenger) {
        passengers.add(passenger);
    }

    public void removePassenger(DetailsOfPassenger passenger) {
        passengers.remove(passenger);
    }

    public Flight getFlight() {
        return flight;
    }

    public List<DetailsOfPassenger> getPassengers() {
        return passengers;
    }

    public int getNumPassengers() {
        return passengers.size();
    }

    public int getTotalPrice() {
        return passengers.size() * DetailsOfPassenger.getTravelPrice();
    }
   
}
