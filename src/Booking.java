
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
        this.id = id;
        this.guestId = guestId;
        this.tripId = tripId;
        this.payment = payment;
        this.status = status;
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
        this.payment = payment;
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
