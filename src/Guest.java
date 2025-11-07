import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Guest extends User implements Serializable {

    public static List<Guest> guests = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    public Guest(String id, String name) {
        super(id, name);
    }
    
    public String viewBookingHistory(String guestId) {
    StringBuilder bookingHistory = new StringBuilder();

    if (guestId == null || guestId.isEmpty()) {
        return "Guest ID cannot be null or empty.";
    }

    bookingHistory.append("Booking History:\n");

    boolean hasBookings = false;
    for (Booking b : Booking.bookings) {
        if (guestId.equalsIgnoreCase(b.getGuestId())) {
            hasBookings = true;
            bookingHistory.append(String.format(
                "Booking: %s, Date: %s, Status: %s, Trip ID: %s, Price: %.2f, Rate: %s%n",
                b.getId(),
                b.getDate(),
                b.getStatus(),
                b.getTripId(),
                b.getPayment(),
                b.getRate() == null ? "Not Rated" : b.getRate()
            ));
        }
    }

    if (!hasBookings) {
        bookingHistory.append("No bookings found for the given Guest ID.");
    }

    return bookingHistory.toString();
}

    
    public void rateBookingHistory(String bookingId, String rate) {
    if (rate == null || rate.isEmpty()) {
        System.out.println("Invalid rate. Please provide a valid rate.");
        return;
    }

    boolean updated = false;
    for (Booking b : Booking.bookings) {
        if (bookingId != null && bookingId.equalsIgnoreCase(b.getId())) {
            b.setRate(rate);
            updated = true;
            System.out.println("Booking " + bookingId + " has been successfully rated: " + rate);
            break;
        }
    }

    if (!updated) {
        System.out.println("No booking found with ID: " + bookingId);
    }
}
    
    
}