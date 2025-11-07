
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Booking implements Serializable {
    private String id;
    private String guestId;
    private String tripId;
    private double payment;
    private String rate;
    private String status;
    public static List<Booking> bookings = new ArrayList<>();
    private Date date;
    private static final long serialVersionUID = 1L;

    public Booking(String id, String guestId, String tripId, double payment, String status) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking ID cannot be empty");
        }
        if (guestId == null || guestId.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest ID cannot be empty");
        }
        if (tripId == null || tripId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trip ID cannot be empty");
        }
        if (payment < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        
        this.id = id.trim();
        this.guestId = guestId.trim();
        this.tripId = tripId.trim();
        this.payment = payment;
        this.status = status.trim();
        this.date = new Date(); // Set current date as default
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public void setPayment(double payment) {
        if (payment < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative");
        }
        this.payment = payment;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Booking date cannot be null");
        }
        if (date.before(new Date())) {
            throw new IllegalArgumentException("Booking date cannot be in the past");
        }
        this.date = date;
    }

    public String getId() {
        return id;
    }
    

    public String getGuestId() {
        return guestId;
    }

    public String getTripId() {
        return tripId;
    }

    public double getPayment() {
        return payment;
    }

    public String getStatus() {
        return status;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
    

    public void setStatus(String status) {
        this.status = status;
    }
}
