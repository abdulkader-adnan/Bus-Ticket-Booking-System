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
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", category=" + category 
                + ", totalTrips=" + totalTrips + ", totalBookings=" + totalBookings + ", totalRevenue=" + totalRevenue + '}'+"\n";
    }
    Vehicle(String id,String category){
    this.id=id;
    this.category=category;
    }
}
