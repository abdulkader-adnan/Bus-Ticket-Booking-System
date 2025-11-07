

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private double totalRevenue;
    private int totalBookings;

    
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", totalRevenue=" + totalRevenue + ", totalBookings=" + totalBookings + '}'+"\n";
    }

    
    

}
