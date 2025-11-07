


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Vehicle implements Serializable{
    private String id;
    private String category;
    private int totalTrips;
    private int totalBookings;
    private double totalRevenue;
    public static List<Vehicle> vehicles = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty");
        }
        this.id = id.trim();
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle category cannot be empty");
        }
        this.category = category.trim();
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", category=" + category 
                + ", totalTrips=" + totalTrips + ", totalBookings=" + totalBookings + ", totalRevenue=" + totalRevenue + '}'+"\n";
    }
    
    
    
    
    Vehicle(String id, String category) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle category cannot be empty");
        }
        this.id = id.trim();
        this.category = category.trim();
        this.totalTrips = 0;
        this.totalBookings = 0;
        this.totalRevenue = 0.0;
    }
     
}
