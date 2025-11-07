

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


class Receptionist extends User implements Serializable {

    public static List<Receptionist> receptionists = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    public Receptionist(String id, String name) {
        super(id, name);
    }
    
    public void createBooking(Booking b) {
    // Null check for the input
    if (b == null) {
        System.out.println("Booking cannot be null.");
        return;
    }

    // Check for duplicate booking ID
    for (Booking existingBooking : Booking.bookings) {
        if (existingBooking.getId().equals(b.getId())) {
            System.out.println("Booking with ID " + b.getId() + " already exists.");
            return;
        }
    }

    // Add the new booking to the list
    Booking.bookings.add(b);
    System.out.println("Booking with ID " + b.getId() + " has been successfully created.");
}

    
    public void cancelBooking(String id) {
    // Null check for input
    if (id == null || id.isEmpty()) {
        System.out.println("Booking ID cannot be null or empty.");
        return;
    }

    boolean bookingFound = false;

    // Iterate through bookings to find the one to cancel
    for (Booking b : Booking.bookings) {
        if (b.getId() != null && b.getId().equalsIgnoreCase(id)) {
            b.setStatus("canceled"); // Use a constant or enum for statuses
            System.out.println("Booking with ID " + id + " has been successfully canceled.");
            bookingFound = true;
            break;
        }
    }

    // Inform user if no booking with the given ID is found
    if (!bookingFound) {
        System.out.println("No booking found with ID: " + id);
    }
}

    
    public double calculatePayment(Booking b){
    return b.getPayment() + 0.14*b.getPayment();
    }
    
    public void selectTripDetails(Booking b,String category,String from,String to){
        for(Trip t : Trip.trips){
        if(t.getCategory().equalsIgnoreCase(category) && t.getFrom().equalsIgnoreCase(from) &&
                t.getTo().equalsIgnoreCase(to)){
            b.setTripId(t.getId());
            b.setStatus("Confirmed");
        }
        }
    
    }
}


