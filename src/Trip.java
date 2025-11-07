import java.io.*;
import java.text.*;
import java.util.*;
class Trip implements Serializable {
    private String id;
    private String category;
    private int bookings;
    private double revenue;
    public static List<Trip> trips=new ArrayList<>();
    private String from;
    private String to;
    private String vehicleId;
    private Date date;
    private static final long serialVersionUID = 1L;
    
    public Trip(String id, String category, int bookings, String from, String to, String vehicleId, Date date, Date endDate) {
        this.id = id;
        this.category = category;
        this.bookings = bookings;
        this.from = from;
        this.to = to;
        this.vehicleId = vehicleId;
        this.date = date;
        
    }
    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getBookings() {
        return bookings;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void addBooking(double payment) {
        this.bookings++;
        this.revenue += payment;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getVehicleId() {
        return vehicleId;
    }
    
    




    
    
}
