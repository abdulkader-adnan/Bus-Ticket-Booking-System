

import java.io.*;
import java.util.*;
import java.text.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Admin implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    public static List<Admin> admins = new ArrayList<>();

    
    public Admin(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    
    
    
    public void addVehicle(Vehicle v){
    Vehicle.vehicles.add(v);
    }
    
    
    
    public void editVehicle(String vehicleId,String newitem){
    for(Vehicle v: Vehicle.vehicles){
    if(v.getId().equalsIgnoreCase(vehicleId)){
    v.setCategory(newitem);
    }
    }
    }
    
    public void removeVehicle(String id){
        if(Vehicle.vehicles.isEmpty()){
            System.out.print("There is no Vehicle to remove!");
            return;
        }
        for(Vehicle v : Vehicle.vehicles ){
            if(v.getId().equalsIgnoreCase(id)){
            Vehicle.vehicles.remove(v);
            System.out.println("The Vehicle "+ id + " has been successfuly removed!");
            return;
            }
        }
        System.out.print("There is no vehicle with the id: "+id);
    }
    
    public List<Vehicle> searchVehicles(String query){
    List<Vehicle> result = new ArrayList<>();
    for(Vehicle v : Vehicle.vehicles ){
            if(v.getId().equalsIgnoreCase(query) || v.getCategory().equalsIgnoreCase(query)){
            result.add(v);
            }
        }
    return result;
    }
    
    public String vehiclesCategory(){
        
        if(Vehicle.vehicles.isEmpty()){
        System.out.print("Tere is no vehicles!");
        return "";
        }
        String result="";
        for(Vehicle v : Vehicle.vehicles){
        result += "Vehicle ID: "+v.getId()+" Category: "+v.getCategory()+"\n";
        }
    return result;
    }
    
    public int tripPerTimeForEachV(Vehicle vehicle, Date stdate, Date endate) {
    int tripsNo = 0;

    
    if (vehicle == null || stdate == null || endate == null) {
        throw new IllegalArgumentException("Vehicle or dates cannot be null");
    }

    
    for (Trip t : Trip.trips) {
        if (t.getVehicleId().equals(vehicle.getId()) &&
            !t.getDate().before(stdate) &&
            !t.getDate().after(endate)) {
            tripsNo++;
        }
    }

    return tripsNo;
}
    
    public int bookingPerTimeForEachV(Vehicle vehicle,Date stdate,Date endate){
    int bookingNo = 0;

    // Check for null input to avoid exceptions
    if (vehicle == null || stdate == null || endate == null) {
        throw new IllegalArgumentException("Vehicle or dates cannot be null");
    }

    // Loop through trips and count only those matching the vehicle and date range
    for (Trip t : Trip.trips) {
        if (t.getVehicleId().equals(vehicle.getId()) &&
            !t.getDate().before(stdate) &&
            !t.getDate().after(endate)) {
            bookingNo += t.getBookings();
        }
    }

    return bookingNo;
    }
    
    public Vehicle mostBookedVehicle(Date stdate, Date endate) {
    Map<String, Vehicle> vehicleMap = new HashMap<>(); 
    for (Vehicle v : Vehicle.vehicles) {
        vehicleMap.put(v.getId(), v);
    }

    Map<Vehicle, Integer> bookingMap = new HashMap<>(); 

    for (Trip t : Trip.trips) {

        if (!t.getDate().before(stdate) && !t.getDate().after(endate)) {
            Vehicle v = vehicleMap.get(t.getVehicleId());
            if (v != null) {
                bookingMap.merge(v, t.getBookings(), Integer::sum);
            }
        }
    }

    
    Vehicle maxVehicle = null;
    int maxBookings = Integer.MIN_VALUE;

    for (Map.Entry<Vehicle, Integer> entry : bookingMap.entrySet()) {
        if (entry.getValue() > maxBookings) {
            maxBookings = entry.getValue();
            maxVehicle = entry.getKey();
        }
    }

    return maxVehicle; 
}

    
    public Vehicle mostRevenueVehicle(Date stdate, Date endate) {
    Map<String, Vehicle> vehicleMap = new HashMap<>();
    for (Vehicle v : Vehicle.vehicles) {
        vehicleMap.put(v.getId(), v);
    }
    Map<Vehicle, Double> revenueMap = new HashMap<>(); 
    for (Trip t : Trip.trips) {
        
        if (!t.getDate().before(stdate) && !t.getDate().after(endate)) {
            Vehicle v = vehicleMap.get(t.getVehicleId());
            if (v != null) {
                revenueMap.merge(v, t.getRevenue(), Double::sum);
            }
        }
    }
    Vehicle maxVehicle = null;
    double maxRevenue = Double.MIN_VALUE;

    for (Map.Entry<Vehicle, Double> entry : revenueMap.entrySet()) {
        if (entry.getValue() > maxRevenue) {
            maxRevenue = entry.getValue();
            maxVehicle = entry.getKey();
        }
    }
    return maxVehicle;
}

    
    public void addTrip(Trip t){
    Trip.trips.add(t);
    }
    
    //public void editTrip(String tripId){}
    
    public void removeTrip(String id){
        if(Trip.trips.isEmpty()){
            System.out.print("There is no Vehicle to remove!");
            return;
        }
        for(Trip t : Trip.trips ){
            if(t.getId().equalsIgnoreCase(id)){
            Trip.trips.remove(t);
            System.out.println("The Trip "+ id + " has been successfuly removed!");
            return;
            }
        }
        System.out.print("There is no Trip with the id: "+id);
    }
    
    public List<Trip> searchTrips(String query){
    List<Trip> result = new ArrayList<>();
    for(Trip t : Trip.trips ){
            if(t.getId().equalsIgnoreCase(query) || t.getCategory().equalsIgnoreCase(query)){
            result.add(t);
            }
        }
    return result;
    }
    
    public void tripsCategory(){
        if(Trip.trips.isEmpty()){
        System.out.print("Tere is no Trips!");
        return;
        }
        for(Trip t : Trip.trips){
        System.out.println("Vehicle "+t.getId()+" Category: "+t.getCategory());
        }
    
    }
    
    
    
    public <T extends User> void addUser(List<T> userList, T user) {
    // Validate input parameters
    if (userList == null || user == null || user.getId() == null || user.getName() == null) {
        System.err.println("Error: User list or user details cannot be null.");
        return;
    }

    // Check for duplicate user ID
    for (T existingUser : userList) {
        if (existingUser.getId().equalsIgnoreCase(user.getId())) {
            System.err.println("Error: A user with ID " + user.getId() + " already exists.");
            return;
        }
    }

    // Add the user to the list
    userList.add(user);
    System.out.println("User added successfully: ID = " + user.getId() + ", Name = " + user.getName());
}
    
    // Generic Edit User Method
public <T extends User> void editUser(List<T> userList, String userId, String newName) {
    if (userList == null || userId == null || userId.isEmpty() || newName == null || newName.isEmpty()) {
        System.err.println("Error: User list, user ID, and new name cannot be null or empty.");
        return;
    }

    for (T user : userList) {
        if (user.getId().equalsIgnoreCase(userId)) {
            user.setName(newName);
            System.out.println("User with ID " + userId + " has been successfully updated to name: " + newName);
            return;
        }
    }

    System.err.println("Error: No user found with ID: " + userId);
}
    
    public int bookingPerTimeForEachT(Trip trip,Date stdate,Date endate){
    int bookingNo = 0;

    // Check for null input to avoid exceptions
    if (trip == null || stdate == null || endate == null) {
        throw new IllegalArgumentException("Trip or dates cannot be null");
    }

    // Loop through bookings and count only those matching the trip and date range
    for (Booking b : Booking.bookings) {
        if (b.getTripId().equals(trip.getId()) &&
            !b.getDate().before(stdate) &&
            !b.getDate().after(endate)) {
            bookingNo++;
        }
    }

    return bookingNo;
    }
    
    public Trip mostBookedTrip(Date stdate,Date endate){
    Trip T = Trip.trips.get(0);
        for(Trip t : Trip.trips){
            if(t.getDate().before(endate) && t.getDate().after(stdate)){
                if(t.getBookings() > T.getBookings()){
                    T = t;
                }
            }            
        }
        return T;
    }
    
    public Trip mostRevenueTrip(Date stdate,Date endate){
        Trip T = Trip.trips.get(0);
        for(Trip t : Trip.trips){
            if(t.getDate().before(endate) && t.getDate().after(stdate)){
                if(t.getRevenue() > T.getRevenue()){
                    T = t;
                }
            }            
        }
        return T;
    }
    
    public <T extends User> void removeUser(List<T> userList, String userId) {
    // Validate input parameters
    if (userList == null || userId == null || userId.isEmpty()) {
        System.err.println("Error: User list and user ID cannot be null or empty.");
        return;
    }

    // Iterator to safely remove the user while iterating
    Iterator<T> iterator = userList.iterator();
    while (iterator.hasNext()) {
        T user = iterator.next();
        if (user.getId().equalsIgnoreCase(userId)) {
            iterator.remove(); // Safely remove the user
            System.out.println("User with ID " + userId + " has been successfully removed.");
            return;
        }
    }

    // If no user is found with the specified ID, print an error message
    System.err.println("Error: No user found with ID: " + userId);
}
    
    public List<User> searchUsers(String query){
    List<User> result = new ArrayList<>();
    for(User u : Guest.guests ){
            if(u.getId().equalsIgnoreCase(query) || u.getName().equalsIgnoreCase(query)){
            result.add(u);
            }
        }
    for(User u : Receptionist.receptionists ){
            if(u.getId().equalsIgnoreCase(query) || u.getName().equalsIgnoreCase(query)){
            result.add(u);
            }
        }
    return result;
    }
    
    public void bookingForEachR(){
        if (Receptionist.receptionists.isEmpty()) {
        System.out.println("No receptionists available.");
        return;
    }
        for(Receptionist r : Receptionist.receptionists ){
            System.out.println("Details of Guest id: "+ r.getId()+" ,name: "+r.getName()+ " ,Number of Bookings: "
                    +r.getTotalBookings()+" ,Total Revenue: "+r.getTotalRevenue());
            for(Booking b: Booking.bookings){
            if(b.getGuestId().equalsIgnoreCase(r.getId())){
            System.out.println("Booking ID : "+b.getId()+" ,Date: "+b.getDate()+" ,Price"+b.getPayment()+" ,Trip ID:"+b.getTripId()
                    +" ,Status: "+b.getStatus()+" ,Rate: "+b.getRate());
            }
            }
        }
    }
    
    public Receptionist maxBookingsR(){
    Receptionist R = Receptionist.receptionists.get(0);
        for(Receptionist r : Receptionist.receptionists ){
            if(r.getTotalBookings() > R.getTotalBookings()){
            R = r;
            }
        }
    return R;
    }
    
    public Receptionist maxRevenueR(){
    Receptionist R = Receptionist.receptionists.get(0);
        for(Receptionist r : Receptionist.receptionists ){
            if(r.getTotalRevenue() > R.getTotalRevenue()){
            R = r;
            }
        }
    return R;
    }
    
    public void bookingForEachG(){
    
        for(Guest g : Guest.guests ){
            System.out.println("Details of Guest id: "+ g.getId()+" ,name: "+g.getName()+ " ,Number of Bookings: "
                    +g.getTotalBookings()+" ,Total Revenue: "+g.getTotalRevenue());
            for(Booking b: Booking.bookings){
            if(b.getGuestId().equalsIgnoreCase(g.getId())){
            System.out.println("Booking ID : "+b.getId()+" ,Date: "+b.getDate()+" ,Price"+b.getPayment()+" ,Trip ID:"+b.getTripId()
                    +" ,Status: "+b.getStatus()+" ,Rate: "+b.getRate());
            }
            }
        }
    
    }
    
    public Guest maxBookingsG(){
    Guest G = Guest.guests.get(0);
        for(Guest g : Guest.guests ){
            if(g.getTotalBookings() > G.getTotalBookings()){
            G = g;
            }
        }
    return G;
    }
    
    public Guest maxRevenueG(){
        Guest G = Guest.guests.get(0);
        for(Guest g : Guest.guests ){
            if(g.getTotalRevenue() > G.getTotalRevenue()){
            G = g;
            }
        }
    return G;
    }
    
    public void bookingDetails(String bookingId){
        
    for(Booking b : Booking.bookings){
        if(b.getId().equalsIgnoreCase(bookingId)){
        System.out.println("Booking Details: "+"\nID :"+b.getId()+"\nGuest ID : "+b.getGuestId()+"\nTrip ID : "
                +b.getTripId()+"\nRate : "+b.getRate()+"\nStatus : "+b.getStatus()
                +"\nDate : "+b.getDate()+"\nPayment : "+b.getPayment());
        return;
        }
    }   
    }
    
    public double avgRevenue(Date stdate,Date endate){
        double totalRevenue=0;
        int count=0;
    for(Booking b : Booking.bookings){
        if(b.getDate().before(endate) && b.getDate().after(stdate)){
            totalRevenue += b.getPayment(); 
            count++;
        }
    }  
    double avgRevenue=0;
    
    try{
        avgRevenue = totalRevenue/count;
    }catch(Exception ex){
    ex.getMessage();
    }
    
    return avgRevenue;
    }
    
    public double totalRevenue(Date stdate,Date endate){
    double totalRevenue=0;
    for(Booking b : Booking.bookings){
        if(b.getDate().before(endate) && b.getDate().after(stdate)){
            totalRevenue += b.getPayment();        
        }
    }   
    return totalRevenue;
}
    
    
    
    

}

