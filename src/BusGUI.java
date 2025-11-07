
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BusGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
                
        Admin.admins = loadFromFile("admins.dat");
        Receptionist.receptionists = loadFromFile("receptionists.dat");
        Guest.guests = loadFromFile("guests.dat");
        Vehicle.vehicles = loadFromFile("vehicles.dat");
        Trip.trips = loadFromFile("trips.dat");
        Booking.bookings = loadFromFile("bookings.dat");
        
        
        // Main Menu
        Button adminBtn = new Button("Admin");
        Button receptionistBtn = new Button("Receptionist");
        Button guestBtn = new Button("Guest");

        adminBtn.setPrefSize(250, 100);
        receptionistBtn.setPrefSize(250, 100);
        guestBtn.setPrefSize(250, 100);
        
        VBox mainPane = new VBox();
        mainPane.setSpacing(20);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(adminBtn, receptionistBtn, guestBtn);

        Scene mainScene = new Scene(mainPane, 400, 400);
        
        adminBtn.setOnAction(e -> {openSignInWindow(new Admin("",""),primaryStage,mainScene);});
        receptionistBtn.setOnAction(e -> {openSignInWindow(new Receptionist("",""),primaryStage,mainScene);});
        guestBtn.setOnAction(e -> {openSignInWindow(new Guest("",""),primaryStage,mainScene);});

        
        
        primaryStage.setTitle("Bus Ticket Booking System");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        
        
        primaryStage.setOnCloseRequest(e -> {
    e.consume(); 

    // Save data to files
    saveToFile("admins.dat",Admin.admins);
    saveToFile("receptionists.dat",Receptionist.receptionists);
    saveToFile("guests.dat",Guest.guests);
    saveToFile("vehicles.dat",Vehicle.vehicles);
    saveToFile("trips.dat",Trip.trips);
    saveToFile("bookings.dat",Booking.bookings);

    // Confirm exit
    Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
    exitAlert.setTitle("Exit Confirmation");
    exitAlert.setHeaderText("Are you sure you want to exit?");
    exitAlert.setContentText("All data has been saved.");
    ButtonType result = exitAlert.showAndWait().orElse(ButtonType.CANCEL);

    if (result == ButtonType.OK) {
        System.out.println("Application is closing.");
        primaryStage.close();
    } else {
        System.out.println("Exit canceled.");
    }
        });

    }
    private void openAdminMenuWindow(Admin admin,Stage primaryStage,Scene mainScene){
    //Admin Menu
        VBox adminPane = new VBox();
        adminPane.setAlignment(Pos.CENTER);
        adminPane.setSpacing(20);
        Button manageVehiclesBtn = new Button("Manage Vehicles");
        Button viewVehicleReportsBtn = new Button("View Vehicle Reports");
        Button manageTripsBtn = new Button("Manage Trips");
        Button viewTripReportsBtn = new Button("View Trip Reports");
        Button manageUsersBtn = new Button("Manage Users");
        Button viewUserReportsBtn = new Button("View User Reports");
        Button viewBookingReportsBtn = new Button("View Booking Reports");
        Button previousBtn = new Button("Previous");
        
        manageVehiclesBtn.setPrefSize(250, 50);
        viewVehicleReportsBtn.setPrefSize(250, 50);
        manageTripsBtn.setPrefSize(250, 50);
        viewTripReportsBtn.setPrefSize(250, 50);
        manageUsersBtn.setPrefSize(250, 50);
        viewUserReportsBtn.setPrefSize(250, 50);
        viewBookingReportsBtn.setPrefSize(250, 50);

        adminPane.getChildren().addAll(manageVehiclesBtn, viewVehicleReportsBtn, manageTripsBtn,
                viewTripReportsBtn, manageUsersBtn, viewUserReportsBtn, viewBookingReportsBtn,previousBtn);

        Scene adminScene = new Scene(adminPane, 500, 600);
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Admin");
        
        manageVehiclesBtn.setOnAction(e -> openManageVehiclesWindow(admin,primaryStage,adminScene));
        viewVehicleReportsBtn.setOnAction(e -> openViewVehicleReportsWindow(admin,primaryStage,adminScene));
        manageTripsBtn.setOnAction(e -> openManageTripsWindow(admin,primaryStage,adminScene));
        viewTripReportsBtn.setOnAction(e -> openViewTripReportsWindow(admin,primaryStage,adminScene));
        manageUsersBtn.setOnAction(e -> openManageUsersWindow(admin,primaryStage,adminScene));
        viewUserReportsBtn.setOnAction(e -> openViewUserReportsWindow(admin,primaryStage,adminScene));
        viewBookingReportsBtn.setOnAction(e -> openViewBookingReportsWindow(admin,primaryStage,adminScene));
        previousBtn.setOnAction(e -> {
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Sign in");
        });
        
    }
    private void openReceptionistMenuWindow(Receptionist receptionist,Stage primaryStage,Scene mainScene){
    // Receptionist Menu
        VBox receptionistPane = new VBox();
        receptionistPane.setAlignment(Pos.CENTER);
        receptionistPane.setSpacing(20);
        Button createBookingBtn = new Button("Create Booking");
        Button selectTripDetailsBtn = new Button("Select Trip Details");
        Button cancelBookingBtn = new Button("Cancel Booking");
        Button calculatePaymentBtn = new Button("Calculate Payment");
        Button previousBtn = new Button("Previous");
        
        createBookingBtn.setPrefSize(250, 50);
        selectTripDetailsBtn.setPrefSize(250, 50);
        cancelBookingBtn.setPrefSize(250, 50);
        calculatePaymentBtn.setPrefSize(250, 50);

        receptionistPane.getChildren().addAll(createBookingBtn, selectTripDetailsBtn, cancelBookingBtn, calculatePaymentBtn,previousBtn);

        Scene receptionistScene = new Scene(receptionistPane, 400, 400);
        primaryStage.setScene(receptionistScene);
        primaryStage.setTitle("Receptionist Menu");
        
        createBookingBtn.setOnAction(e -> openCreateBookingWindow(receptionist,primaryStage,receptionistScene));
        selectTripDetailsBtn.setOnAction(e -> openSelectTripDetailsWindow(receptionist,primaryStage,receptionistScene));
        cancelBookingBtn.setOnAction(e -> openCancelBookingWindow(receptionist,primaryStage,receptionistScene));
        calculatePaymentBtn.setOnAction(e -> openCalculatePaymentWindow(receptionist,primaryStage,receptionistScene));
        previousBtn.setOnAction(e -> {
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Sign in");
        });
    }
    private void openGuestMenuWindow(Guest guest,Stage primaryStage,Scene mainScene){
    // Guest Menu
        VBox guestPane = new VBox();
        guestPane.setAlignment(Pos.CENTER);
        guestPane.setSpacing(20);
        Button bookingDetailsBtn = new Button("View Booking Details");
        Button rateBookingBtn = new Button("Rate a Booking");
        Button previousBtn = new Button("Previous");
        
        bookingDetailsBtn.setPrefSize(250, 50);
        rateBookingBtn.setPrefSize(250, 50);

        guestPane.getChildren().addAll(bookingDetailsBtn, rateBookingBtn,previousBtn);

        Scene guestScene = new Scene(guestPane, 400, 300);
        primaryStage.setScene(guestScene);
        primaryStage.setTitle("Guest Menu");
        
        bookingDetailsBtn.setOnAction(e -> openViewBookingDetailsWindow(guest,primaryStage,guestScene));
        rateBookingBtn.setOnAction(e -> openRateBookingWindow(guest,primaryStage,guestScene));
        
        previousBtn.setOnAction(e -> {
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Sign in");
        });
    }
    
    private void openSignInWindow(Object obj,Stage primaryStage,Scene mainScene){
    
    VBox signInPane = new VBox();
    signInPane.setAlignment(Pos.CENTER);
    signInPane.setSpacing(20);
    
    Label usernameLabel=new Label("Username: "),passwordLabel=new Label("Password: ");
    TextField usernameTextField=new TextField(),passwordTextField=new TextField(); 
    
    Button signInBtn = new Button("Sign in"); 
    Button previousBtn = new Button("Previous");
    
    HBox usernamePane = new HBox();
    usernamePane.setAlignment(Pos.CENTER);
    usernamePane.setSpacing(10);
    HBox passwordPane = new HBox();
    passwordPane.setAlignment(Pos.CENTER);
    passwordPane.setSpacing(10);
    usernamePane.getChildren().addAll(usernameLabel,usernameTextField);
    passwordPane.getChildren().addAll(passwordLabel,passwordTextField);
    
    signInPane.getChildren().addAll(usernamePane,passwordPane,signInBtn,previousBtn);
    Scene signInScene = new Scene(signInPane,400,400);
    primaryStage.setTitle("Sing in");
    primaryStage.setScene(signInScene);
    
     
    
    signInBtn.setOnAction(e -> { 
        System.out.println("Sign in button clicked");
        String username = usernameTextField.getText(),password=passwordTextField.getText();
    if(obj instanceof Admin){
        for(Admin a : Admin.admins){
            if(a.getId().equals(password) && a.getName().equalsIgnoreCase(username))
                openAdminMenuWindow(a,primaryStage,signInScene);
        }
    }else if(obj instanceof Receptionist)
    {
        for(Receptionist r : Receptionist.receptionists){
            if(r.getId().equals(password) && r.getName().equalsIgnoreCase(username))
                openReceptionistMenuWindow(r,primaryStage,signInScene);
    }
    }else if(obj instanceof Guest)
    {
        for(Guest g : Guest.guests){
            if(g.getId().equals(password) && g.getName().equalsIgnoreCase(username))
                openGuestMenuWindow(g,primaryStage,signInScene);
        }
    }
    System.out.println("Sign in button end");
    });
    previousBtn.setOnAction(e -> {
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Bus Ticket Booking System");
    });
    
    } 

    // Admin Menu Sub-windows
    private void openManageVehiclesWindow(Admin admin,Stage primaryStage,Scene adminScene) {

    VBox vehiclePane = new VBox();
    vehiclePane.setAlignment(Pos.CENTER);
    vehiclePane.setSpacing(20);
    
    Label vehicleIdLabel = new Label("Vehicle ID:");
    TextField vehicleIdField = new TextField();

    Label vehicleCategoryLabel = new Label("Category:");
    TextField vehicleCategoryField = new TextField();

    // Buttons for vehicle management
    Button addVehicleBtn = new Button("Add Vehicle");
    Button editVehicleBtn = new Button("Edit Vehicle");
    Button removeVehicleBtn = new Button("Remove Vehicle");
    Button searchVehicleBtn = new Button("Search Vehicle");
    Button previousBtn = new Button("Previous");
    
    // Action for "Add Vehicle"
    addVehicleBtn.setOnAction(e -> {
        String id = vehicleIdField.getText();
        String category = vehicleCategoryField.getText();

        if (id.isEmpty() || category.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Vehicle ID and Category are required.");
            return;
        }

        Vehicle newVehicle = new Vehicle(id, category);
        admin.addVehicle(newVehicle);
        

        showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle added successfully: ID = " + id);
    });

    // Action for "Edit Vehicle"
    editVehicleBtn.setOnAction(e -> {
        String id = vehicleIdField.getText();
        String newCategory = vehicleCategoryField.getText();

        if (id.isEmpty() || newCategory.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Vehicle ID and new Category are required.");
            return;
        }

        admin.editVehicle(id, newCategory);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle updated: ID = " + id + ", New Category = " + newCategory);
                
        
    });

    // Action for "Remove Vehicle"
    removeVehicleBtn.setOnAction(e -> {
        String id = vehicleIdField.getText();
        if (id != null) {
            admin.removeVehicle(id);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Vehicle removed successfully: ID = " + id);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Vehicle ID is required to remove a vehicle.");
        }
    });

    // Action for "Search Vehicle"
    searchVehicleBtn.setOnAction(e -> {
        String id = vehicleIdField.getText();
        String result = "";
        List<Vehicle> vs = admin.searchVehicles(id);
        for(Vehicle v : vs){
        result += v.toString();
        }

        showAlert(Alert.AlertType.INFORMATION, "Result:", result);
    });

    
    vehiclePane.getChildren().addAll(
        vehicleIdLabel, vehicleIdField,
        vehicleCategoryLabel, vehicleCategoryField,
        addVehicleBtn, editVehicleBtn, removeVehicleBtn, searchVehicleBtn,previousBtn
    );

    Scene vehicleScene = new Scene(vehiclePane, 400, 400);
    primaryStage.setScene(vehicleScene);
    primaryStage.setTitle("Manage Vehicles");
    
    previousBtn.setOnAction(e -> {
    primaryStage.setScene(adminScene);
    primaryStage.setTitle("Admin Menu");
    });
    
}


    private void openManageTripsWindow(Admin admin,Stage primaryStage,Scene adminScene) {

    VBox tripPane = new VBox();
    tripPane.setAlignment(Pos.CENTER);
    tripPane.setSpacing(20);

    
    Label tripIdLabel = new Label("Trip ID:");
    TextField tripIdField = new TextField();

    Label tripCategoryLabel = new Label("Category:");
    TextField tripCategoryField = new TextField();

    Label tripFromLabel = new Label("From:");
    TextField tripFromField = new TextField();

    Label tripToLabel = new Label("To:");
    TextField tripToField = new TextField();

    Label vehicleIdLabel = new Label("Vehicle ID:");
    TextField vehicleIdField = new TextField();

    Label tripDateLabel = new Label("Date (dd/MM/yyyy):");
    TextField tripDateField = new TextField();

    // Buttons for trip management
    Button addTripBtn = new Button("Add Trip");
    Button editTripBtn = new Button("Edit Trip");
    Button removeTripBtn = new Button("Remove Trip");
    Button searchTripBtn = new Button("Search Trip");
    Button previousBtn = new Button("Previous");

    // Action for "Add Trip"
    addTripBtn.setOnAction(e -> {
        String tripId = tripIdField.getText();
        String category = tripCategoryField.getText();
        String from = tripFromField.getText();
        String to = tripToField.getText();
        String vehicleId = vehicleIdField.getText();
        String dateString = tripDateField.getText();

        if (tripId.isEmpty() || category.isEmpty() || from.isEmpty() || to.isEmpty() || vehicleId.isEmpty() || dateString.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required to add a trip.");
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            date = dateFormat.parse(dateString);

            Trip newTrip = new Trip(tripId, category, 0, from, to, vehicleId, date, null);
            Trip.trips.add(newTrip);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Trip added successfully: ID = " + tripId);
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use dd/MM/yyyy.");
        }
    });

    // Action for "Edit Trip"
    editTripBtn.setOnAction(e -> {
        String tripId = tripIdField.getText();
        String newCategory = tripCategoryField.getText();

        if (tripId.isEmpty() || newCategory.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Trip ID and new category are required to edit a trip.");
            return;
        }

        for (Trip trip : Trip.trips) {
            if (trip.getId().equalsIgnoreCase(tripId)) {
                trip.setCategory(newCategory);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Trip updated successfully: ID = " + tripId);
                return;
            }
        }

        showAlert(Alert.AlertType.ERROR, "Error", "Trip with ID " + tripId + " not found.");
    });

    // Action for "Remove Trip"
    removeTripBtn.setOnAction(e -> {
        String tripId = tripIdField.getText();

        if (tripId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Trip ID is required to remove a trip.");
            return;
        }

        Trip toRemove = null;
        for (Trip trip : Trip.trips) {
            if (trip.getId().equalsIgnoreCase(tripId)) {
                toRemove = trip;
                break;
            }
        }

        if (toRemove != null) {
            Trip.trips.remove(toRemove);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Trip removed successfully: ID = " + tripId);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Trip with ID " + tripId + " not found.");
        }
    });

    // Action for "Search Trip"
    searchTripBtn.setOnAction(e -> {
        String tripId = tripIdField.getText();

        if (tripId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Trip ID is required to search for a trip.");
            return;
        }

        for (Trip trip : Trip.trips) {
            if (trip.getId().equalsIgnoreCase(tripId)) {
                showAlert(Alert.AlertType.INFORMATION, "Trip Found",
                        "Trip Details:\nID: " + trip.getId() +
                                "\nCategory: " + trip.getCategory() +
                                "\nFrom: " + trip.getFrom() +
                                "\nTo: " + trip.getTo() +
                                "\nVehicle ID: " + trip.getVehicleId() +
                                "\nDate: " + trip.getDate());
                return;
            }
        }

        showAlert(Alert.AlertType.ERROR, "Error", "Trip with ID " + tripId + " not found.");
    });

    // Add components to the pane
    tripPane.getChildren().addAll(
        tripIdLabel, tripIdField,
        tripCategoryLabel, tripCategoryField,
        tripFromLabel, tripFromField,
        tripToLabel, tripToField,
        vehicleIdLabel, vehicleIdField,
        tripDateLabel, tripDateField,
        addTripBtn, editTripBtn, removeTripBtn, searchTripBtn,previousBtn
    );

    Scene tripScene = new Scene(tripPane, 600, 800);
    primaryStage.setScene(tripScene);
    primaryStage.setTitle("Manage Trips");
    previousBtn.setOnAction(e->{
    primaryStage.setScene(adminScene);
    primaryStage.setTitle("Admin menu");
    });
//    tripStage.setScene(tripScene);
//    tripStage.show();
}

private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

    private void openManageUsersWindow(Admin admin,Stage primaryStage,Scene adminScene) {

    VBox userPane = new VBox();
    userPane.setAlignment(Pos.CENTER);
    userPane.setSpacing(20);

    Button addUserBtn = new Button("Add User");
    Button editUserBtn = new Button("Edit User");
    Button removeUserBtn = new Button("Remove User");
    Button searchUserBtn = new Button("Search User");
    Button previousBtn = new Button("Previous");

    addUserBtn.setOnAction(e -> openAddUserWindow(admin));
    editUserBtn.setOnAction(e -> openEditUserWindow(admin));
    removeUserBtn.setOnAction(e -> openRemoveUserWindow(admin));
    searchUserBtn.setOnAction(e -> openSearchUserWindow(admin));
    

    userPane.getChildren().addAll(addUserBtn, editUserBtn, removeUserBtn, searchUserBtn,previousBtn);

    Scene userScene = new Scene(userPane, 400, 400);
    primaryStage.setScene(userScene);
    primaryStage.setTitle("Manage Users");
    
    previousBtn.setOnAction(e->{
    primaryStage.setScene(adminScene);
    primaryStage.setTitle("Admin menu");
    });
}
    private void openAddUserWindow(Admin admin) {
    Stage addUserStage = new Stage();
    addUserStage.setTitle("Add User");

    VBox addUserPane = new VBox();
    addUserPane.setAlignment(Pos.CENTER);
    addUserPane.setSpacing(20);

    // Labels and input fields
    Label userTypeLabel = new Label("Select User Type:");
    ComboBox<String> userTypeComboBox = new ComboBox<>();
    userTypeComboBox.getItems().addAll("Admin", "Receptionist", "Guest");
    userTypeComboBox.setValue("Admin"); // Default value

    Label idLabel = new Label("User ID:");
    TextField idField = new TextField();

    Label nameLabel = new Label("User Name:");
    TextField nameField = new TextField();

    Button submitBtn = new Button("Add User");
    submitBtn.setOnAction(e -> {
        String userType = userTypeComboBox.getValue();
        String userId = idField.getText();
        String userName = nameField.getText();

        if (userId.isEmpty() || userName.isEmpty()) {
            System.out.println("User ID and Name are required.");
            return;
        }

        switch (userType) {
            case "Admin":
                Admin.admins.add(new Admin(userId, userName)); // Add to admins
                System.out.println("Admin added: ID = " + userId + ", Name = " + userName);
                break;
            case "Receptionist":
                admin.addUser(Receptionist.receptionists,new Receptionist(userId, userName)); // Add to receptionists
                System.out.println("Receptionist added: ID = " + userId + ", Name = " + userName);
                break;
            case "Guest":
                admin.addUser(Guest.guests,new Guest(userId, userName)); // Add to receptionists
                System.out.println("Receptionist added: ID = " + userId + ", Name = " + userName);
                break;
            default:
                System.err.println("Invalid user type selected.");
        }

        addUserStage.close();
    });

    addUserPane.getChildren().addAll(userTypeLabel, userTypeComboBox, idLabel, idField, nameLabel, nameField, submitBtn);

    Scene scene = new Scene(addUserPane, 300, 300);
    addUserStage.setScene(scene);
    addUserStage.show();
}
    private void openEditUserWindow(Admin admin) {
    Stage editUserStage = new Stage();
    editUserStage.setTitle("Edit User");

    VBox editUserPane = new VBox();
    editUserPane.setAlignment(Pos.CENTER);
    editUserPane.setSpacing(20);

    // Labels and TextFields
    Label userTypeLabel = new Label("Select User Type:");
    ComboBox<String> userTypeComboBox = new ComboBox<>();
    userTypeComboBox.getItems().addAll("Receptionist", "Guest");
    userTypeComboBox.setValue("User"); // Default value

    Label idLabel = new Label("User ID:");
    TextField idField = new TextField();

    Label nameLabel = new Label("New User Name:");
    TextField nameField = new TextField();

    Button submitBtn = new Button("Edit User");
    submitBtn.setOnAction(e -> {
        String userType = userTypeComboBox.getValue();
        String userId = idField.getText();
        String newName = nameField.getText();

        if (userId.isEmpty() || newName.isEmpty()) {
            System.out.println("Both ID and New Name are required.");
            return;
        }

        switch (userType) {
//            case "User":
//                editUser(Admin.users, userId, newName); // Edit regular users
//                break;
            case "Receptionist":
                admin.editUser(Receptionist.receptionists, userId, newName); // Edit receptionists
                break;
            case "Guest":
                admin.editUser(Guest.guests, userId, newName); // Edit guests
                break;
            default:
                System.err.println("Invalid user type selected.");
        }

        editUserStage.close();
    });

    editUserPane.getChildren().addAll(userTypeLabel, userTypeComboBox, idLabel, idField, nameLabel, nameField, submitBtn);

    Scene scene = new Scene(editUserPane, 300, 300);
    editUserStage.setScene(scene);
    editUserStage.show();
}


    private void openRemoveUserWindow(Admin admin) {
    Stage removeUserStage = new Stage();
    removeUserStage.setTitle("Remove User");

    VBox removeUserPane = new VBox();
    removeUserPane.setAlignment(Pos.CENTER);
    removeUserPane.setSpacing(20);

    // Labels and input fields
    Label userTypeLabel = new Label("Select User Type:");
    ComboBox<String> userTypeComboBox = new ComboBox<>();
    userTypeComboBox.getItems().addAll("User", "Receptionist", "Guest");
    userTypeComboBox.setValue("User"); // Default value

    Label idLabel = new Label("User ID:");
    TextField idField = new TextField();

    Button submitBtn = new Button("Remove User");
    submitBtn.setOnAction(e -> {
        String userType = userTypeComboBox.getValue();
        String userId = idField.getText();

        if (userId.isEmpty()) {
            System.out.println("User ID is required.");
            return;
        }

        // Perform removal based on user type
        switch (userType) {
//            case "User":
//                removeUser(Admin.users, userId);
//                break;
            case "Receptionist":
                admin.removeUser(Receptionist.receptionists, userId);
                break;
            case "Guest":
                admin.removeUser(Guest.guests, userId);
                break;
            default:
                System.err.println("Invalid user type selected.");
        }

        removeUserStage.close();
    });

    removeUserPane.getChildren().addAll(userTypeLabel, userTypeComboBox, idLabel, idField, submitBtn);

    Scene scene = new Scene(removeUserPane, 300, 250);
    removeUserStage.setScene(scene);
    removeUserStage.show();
}

    private void openSearchUserWindow(Admin admin) {
    Stage searchUserStage = new Stage();
    searchUserStage.setTitle("Search User");

    VBox searchUserPane = new VBox();
    searchUserPane.setAlignment(Pos.CENTER);
    searchUserPane.setSpacing(20);

    Label queryLabel = new Label("Search Query (ID or Name):");
    TextField queryField = new TextField();

    Button searchBtn = new Button("Search");
    searchBtn.setOnAction(e -> {
        String query = queryField.getText();
        String r = "";
        List<User> us = admin.searchUsers(query);
        for(User u : us){
        r += u.toString();
        }
        showAlert(Alert.AlertType.INFORMATION, "Result",r);
        searchUserStage.close();

    });

    searchUserPane.getChildren().addAll(queryLabel, queryField, searchBtn);

    Scene scene = new Scene(searchUserPane, 300, 200);
    searchUserStage.setScene(scene);
    searchUserStage.show();
}
    

    // Report Actions with Statistics
    private void openViewVehicleReportsWindow(Admin admin,Stage primaryStage,Scene adminScene) {
//    Stage vehicleReportStage = new Stage();
//    vehicleReportStage.setTitle("View Vehicle Reports");

    VBox vehicleReportPane = new VBox();
    vehicleReportPane.setAlignment(Pos.CENTER);
    vehicleReportPane.setSpacing(20);

    // Labels and TextFields for date range input
    Label startDateLabel = new Label("Start Date (dd/MM/yyyy):");
    TextField startDateField = new TextField();

    Label endDateLabel = new Label("End Date (dd/MM/yyyy):");
    TextField endDateField = new TextField();

    // Buttons for vehicle reports
    Button vehicleByCategoryBtn = new Button("Vehicles Category");
    Button totalBookingsPerVehicleBtn = new Button("Total Bookings Per Vehicle");
    Button mostBookedVehicleBtn = new Button("Most Booked Vehicle");
    Button totalTripsOverPeriodBtn = new Button("Number of Trips Over a Period");
    Button highestRevenueVehicleBtn = new Button("Highest Revenue Vehicle");
    Button previousBtn = new Button("Previous");
    
    
    // Action for "Number of Vehicles by Category"
    vehicleByCategoryBtn.setOnAction(e -> {
//        String start = startDateField.getText();
//        String end = endDateField.getText();

        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date startDate = dateFormat.parse(start);
//            Date endDate = dateFormat.parse(end);
                
            if (Vehicle.vehicles.isEmpty() || Trip.trips.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "No Vehicles or Trips Found", "There are no vehicles or trips to report.");
                return;
            }
            String result = admin.vehiclesCategory();

            StringBuilder report = new StringBuilder(result);

            showAlert(Alert.AlertType.INFORMATION, "Vehicles Category", report.toString());
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use dd/MM/yyyy.");
        }
    });

    // Action for "Total Bookings Per Vehicle"
    totalBookingsPerVehicleBtn.setOnAction(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            StringBuilder report = new StringBuilder("Total Bookings Per Vehicle (Filtered by Time):\n");
            for (Vehicle vehicle : Vehicle.vehicles) {
                int bookings = 0;
                for (Trip trip : Trip.trips) {
                    if (trip.getVehicleId().equals(vehicle.getId()) &&
                        !trip.getDate().before(startDate) && !trip.getDate().after(endDate)) {
                        bookings += trip.getBookings();
                    }
                }
                report.append("Vehicle ID: ").append(vehicle.getId())
                      .append(" - Total Bookings: ").append(bookings).append("\n");
            }

            showAlert(Alert.AlertType.INFORMATION, "Total Bookings Per Vehicle", report.toString());
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use dd/MM/yyyy.");
        }
    });

    // Action for "Most Booked Vehicle"
    mostBookedVehicleBtn.setOnAction(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            Vehicle mostBookedVehicle = admin.mostBookedVehicle(startDate, endDate);
            

            if (mostBookedVehicle != null) {
                showAlert(Alert.AlertType.INFORMATION, "Most Booked Vehicle",
                    "Vehicle ID: " + mostBookedVehicle.getId() +
                    "\nCategory: " + mostBookedVehicle.getCategory());
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Most Booked Vehicle", "No bookings found in the specified period.");
            }
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use dd/MM/yyyy.");
        }
    });

    // Action for "Number of Trips Over a Period"
    totalTripsOverPeriodBtn.setOnAction(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            int totalTrips = 0;
            for (Trip trip : Trip.trips) {
                if (!trip.getDate().before(startDate) && !trip.getDate().after(endDate)) {
                    totalTrips++;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Number of Trips Over a Period",
                "Total Trips from " + start + " to " + end + ": " + totalTrips);
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use dd/MM/yyyy.");
        }
    });

    // Action for "Highest Revenue Vehicle"
    highestRevenueVehicleBtn.setOnAction(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            Vehicle highestRevenueVehicle = null;
            double maxRevenue = 0;

            for (Vehicle vehicle : Vehicle.vehicles) {
                double revenue = 0;
                for (Trip trip : Trip.trips) {
                    if (trip.getVehicleId().equals(vehicle.getId()) &&
                        !trip.getDate().before(startDate) && !trip.getDate().after(endDate)) {
                        revenue += trip.getRevenue();
                    }
                }

                if (revenue > maxRevenue) {
                    maxRevenue = revenue;
                    highestRevenueVehicle = vehicle;
                }
            }

            if (highestRevenueVehicle != null) {
                showAlert(Alert.AlertType.INFORMATION, "Highest Revenue Vehicle",
                    "Vehicle ID: " + highestRevenueVehicle.getId() +
                    "\nCategory: " + highestRevenueVehicle.getCategory() +
                    "\nTotal Revenue: $" + maxRevenue);
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Highest Revenue Vehicle", "No revenue data found in the specified period.");
            }
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use dd/MM/yyyy.");
        }
    });

    // Add components to the pane
    vehicleReportPane.getChildren().addAll(
        startDateLabel, startDateField,
        endDateLabel, endDateField,
        vehicleByCategoryBtn, totalBookingsPerVehicleBtn, mostBookedVehicleBtn, totalTripsOverPeriodBtn, highestRevenueVehicleBtn,previousBtn
    );

    Scene vehicleReportScene = new Scene(vehicleReportPane, 400, 500);
    primaryStage.setScene(vehicleReportScene);
    primaryStage.setTitle("View Vehicle Reports");
    previousBtn.setOnAction(e->{
    primaryStage.setScene(adminScene);
    primaryStage.setTitle("Admin menu");
    });
//    vehicleReportStage.setScene(vehicleReportScene);
//    vehicleReportStage.show();
}

    private void openViewTripReportsWindow(Admin admin,Stage primaryStage,Scene adminScene) {
//    Stage tripReportStage = new Stage();
//    tripReportStage.setTitle("View Trip Reports");

    VBox tripReportPane = new VBox();
    tripReportPane.setAlignment(Pos.CENTER);
    tripReportPane.setSpacing(20);

    // Labels and TextFields for date range input
    Label startDateLabel = new Label("Start Date (dd/MM/yyyy):");
    TextField startDateField = new TextField();

    Label endDateLabel = new Label("End Date (dd/MM/yyyy):");
    TextField endDateField = new TextField();

    // Buttons for trip reports
    Button bookingsOverPeriodBtn = new Button("No. of Bookings Over a Period");
    Button mostBookedTripBtn = new Button("Most Booked Trip in a Period");
    Button highestRevenueTripBtn = new Button("Highest Revenue Trip in a Period");
    Button previousBtn = new Button("Previous");
    
    // Action for "No. of Bookings Over a Period"
    bookingsOverPeriodBtn.setOnAction(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            int totalBookings = 0;
            for (Trip trip : Trip.trips) {
                if (trip.getDate() != null && 
                    !trip.getDate().before(startDate) && 
                    !trip.getDate().after(endDate)) {
                    totalBookings += trip.getBookings();
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Bookings Over Period",
                "Total Bookings from " + start + " to " + end + ": " + totalBookings);
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format or no trips found. Please use dd/MM/yyyy.");
        }
    });

    // Action for "Most Booked Trip in a Period"
    mostBookedTripBtn.setOnAction(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            Trip mostBookedTrip = null;
            int maxBookings = 0;

            for (Trip trip : Trip.trips) {
                if (trip.getDate() != null && 
                    !trip.getDate().before(startDate) && 
                    !trip.getDate().after(endDate)) {
                    if (trip.getBookings() > maxBookings) {
                        maxBookings = trip.getBookings();
                        mostBookedTrip = trip;
                    }
                }
            }

            if (mostBookedTrip != null) {
                showAlert(Alert.AlertType.INFORMATION, "Most Booked Trip",
                    "Trip Details:\nID: " + mostBookedTrip.getId() +
                    "\nCategory: " + mostBookedTrip.getCategory() +
                    "\nFrom: " + mostBookedTrip.getFrom() +
                    "\nTo: " + mostBookedTrip.getTo() +
                    "\nBookings: " + maxBookings);
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Most Booked Trip",
                    "No trips found in the specified period.");
            }
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format or no trips found. Please use dd/MM/yyyy.");
        }
    });

    // Action for "Highest Revenue Trip in a Period"
    highestRevenueTripBtn.setOnAction(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);

            Trip highestRevenueTrip = null;
            double maxRevenue = 0;

            for (Trip trip : Trip.trips) {
                if (trip.getDate() != null && 
                    !trip.getDate().before(startDate) && 
                    !trip.getDate().after(endDate)) {
                    if (trip.getRevenue() > maxRevenue) {
                        maxRevenue = trip.getRevenue();
                        highestRevenueTrip = trip;
                    }
                }
            }

            if (highestRevenueTrip != null) {
                showAlert(Alert.AlertType.INFORMATION, "Highest Revenue Trip",
                    "Trip Details:\nID: " + highestRevenueTrip.getId() +
                    "\nCategory: " + highestRevenueTrip.getCategory() +
                    "\nFrom: " + highestRevenueTrip.getFrom() +
                    "\nTo: " + highestRevenueTrip.getTo() +
                    "\nRevenue: $" + highestRevenueTrip.getRevenue());
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Highest Revenue Trip",
                    "No trips found in the specified period.");
            }
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format or no trips found. Please use dd/MM/yyyy.");
        }
    });

    // Add components to the pane
    tripReportPane.getChildren().addAll(
        startDateLabel, startDateField,
        endDateLabel, endDateField,
        bookingsOverPeriodBtn, mostBookedTripBtn, highestRevenueTripBtn,previousBtn
    );

    Scene tripReportScene = new Scene(tripReportPane, 400, 400);
    primaryStage.setScene(tripReportScene);
    primaryStage.setTitle("View Trip Reports");
    previousBtn.setOnAction(e->{
    primaryStage.setScene(adminScene);
    primaryStage.setTitle("Admin menu");
    });
//    tripReportStage.setScene(tripReportScene);
//    tripReportStage.show();
}

    private void openViewUserReportsWindow(Admin admin,Stage primaryStage,Scene adminScene) {
//    Stage userReportStage = new Stage();
//    userReportStage.setTitle("View User Reports");

    VBox userReportPane = new VBox();
    userReportPane.setAlignment(Pos.CENTER);
    userReportPane.setSpacing(20);

    // Buttons for user reports
    Button maxBookingsReceptionistBtn = new Button("Receptionist with Maximum Bookings");
    Button maxBookingsGuestBtn = new Button("Guest with Maximum Bookings");
    Button topReceptionistRevenueBtn = new Button("Top Receptionist by Revenue");
    Button topGuestRevenueBtn = new Button("Top Guest by Revenue");
    Button receptionistBookingsBtn = new Button("Booking Details per Receptionist");
    Button guestBookingsBtn = new Button("Booking Details per Guest");
    Button previousBtn = new Button("Previous");

    // Action for each button
    maxBookingsReceptionistBtn.setOnAction(e -> showMaxBookingsReceptionist(admin));
    maxBookingsGuestBtn.setOnAction(e -> showMaxBookingsGuest(admin));
    topReceptionistRevenueBtn.setOnAction(e -> maxRevenueR(admin));
    topGuestRevenueBtn.setOnAction(e -> maxRevenueG(admin));
    receptionistBookingsBtn.setOnAction(e -> showReceptionistBookings(admin));
    guestBookingsBtn.setOnAction(e -> showGuestBookings(admin));

    userReportPane.getChildren().addAll(
        maxBookingsReceptionistBtn,
        maxBookingsGuestBtn,
        topReceptionistRevenueBtn,
        topGuestRevenueBtn,
        receptionistBookingsBtn,
        guestBookingsBtn,previousBtn
    );

    Scene userReportScene = new Scene(userReportPane, 400, 400);
    primaryStage.setScene(userReportScene);
    primaryStage.setTitle("View User Reports");
    previousBtn.setOnAction(e->{
    primaryStage.setScene(adminScene);
    primaryStage.setTitle("Admin menu");
    });
//    userReportStage.setScene(userReportScene);
//    userReportStage.show();
}
    private void showMaxBookingsReceptionist(Admin admin) {
      Stage resultStage = new Stage();
    resultStage.setTitle("Receptionist with Maximum Bookings");

    if (Receptionist.receptionists.isEmpty()) {
        Label noDataLabel = new Label("No Receptionists found.");
        VBox noDataPane = new VBox(noDataLabel);
        noDataPane.setAlignment(Pos.CENTER);
        Scene noDataScene = new Scene(noDataPane, 300, 200);
        resultStage.setScene(noDataScene);
        resultStage.show();
        return;
    }

    Receptionist maxReceptionist = null;
    int maxBookings = 0;

    for (Receptionist receptionist : Receptionist.receptionists) {
        if (receptionist.getTotalBookings() > maxBookings) {
            maxBookings = receptionist.getTotalBookings();
            maxReceptionist = receptionist;
        }
    }

    if (maxReceptionist != null) {
        Label resultLabel = new Label(
            "Receptionist with Maximum Bookings:\n" +
            "ID: " + maxReceptionist.getId() + "\n" +
            "Name: " + maxReceptionist.getName() + "\n" +
            "Total Bookings: " + maxBookings
        );
        VBox resultPane = new VBox(resultLabel);
        resultPane.setAlignment(Pos.CENTER);
        Scene resultScene = new Scene(resultPane, 300, 200);
        resultStage.setScene(resultScene);
        resultStage.show();
    }
}

    private void showMaxBookingsGuest(Admin admin) {
    Stage resultStage = new Stage();
    resultStage.setTitle("Guest with Maximum Bookings");

    if (Guest.guests.isEmpty()) {
        Label noDataLabel = new Label("No Guests found.");
        Scene scene = new Scene(new VBox(noDataLabel), 300, 200);
        resultStage.setScene(scene);
        resultStage.show();
        return;
    }

    Guest maxGuest = null;
    int maxBookings = 0;

    for (Guest guest : Guest.guests) {
        if (guest.getTotalBookings() > maxBookings) {
            maxBookings = guest.getTotalBookings();
            maxGuest = guest;
        }
    }

    if (maxGuest != null) {
        Label label = new Label(
            "Guest with Maximum Bookings:\n" +
            "ID: " + maxGuest.getId() + "\n" +
            "Name: " + maxGuest.getName() + "\n" +
            "Total Bookings: " + maxBookings
        );
        Scene scene = new Scene(new VBox(label), 300, 200);
        resultStage.setScene(scene);
        resultStage.show();
    }
}
    private void maxRevenueR(Admin admin) {
    Receptionist receptionist = admin.maxRevenueR(); 
    if (receptionist != null) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Top Receptionist by Revenue");

        Label label = new Label("Receptionist with Maximum Revenue:\n" +
                "ID: " + receptionist.getId() + "\n" +
                "Name: " + receptionist.getName() + "\n" +
                "Total Revenue: " + receptionist.getTotalRevenue());
        VBox layout = new VBox(label);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Scene scene = new Scene(layout, 300, 200);
        resultStage.setScene(scene);
        resultStage.show();
    }
}
    private void maxRevenueG(Admin admin) {
    Guest guest = admin.maxRevenueG();
    if (guest != null) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Top Guest by Revenue");

        Label label = new Label("Guest with Maximum Revenue:\n" +
                "ID: " + guest.getId() + "\n" +
                "Name: " + guest.getName() + "\n" +
                "Total Revenue: " + guest.getTotalRevenue());
        VBox layout = new VBox(label);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Scene scene = new Scene(layout, 300, 200);
        resultStage.setScene(scene);
        resultStage.show();
    }
}
    private void showReceptionistBookings(Admin admin) {
    Stage resultStage = new Stage();
    resultStage.setTitle("Receptionist Bookings");

    VBox layout = new VBox();
    layout.setAlignment(Pos.CENTER);
    layout.setSpacing(10);

    for (Receptionist receptionist : Receptionist.receptionists) {
        Label header = new Label("Receptionist: " + receptionist.getName() + 
                " (ID: " + receptionist.getId() + ")");
        layout.getChildren().add(header);

        for (Booking booking : Booking.bookings) {
            if (booking.getGuestId().equalsIgnoreCase(receptionist.getId())) {
                Label bookingLabel = new Label("  Booking ID: " + booking.getId() +
                        ", Date: " + booking.getDate() +
                        ", Payment: " + booking.getPayment());
                layout.getChildren().add(bookingLabel);
            }
        }
    }

    Scene scene = new Scene(layout, 400, 400);
    resultStage.setScene(scene);
    resultStage.show();
}
    private void showGuestBookings(Admin admin) {
    Stage resultStage = new Stage();
    resultStage.setTitle("Guest Bookings");

    VBox layout = new VBox();
    layout.setAlignment(Pos.CENTER);
    layout.setSpacing(10);

    for (Guest guest : Guest.guests) {
        Label header = new Label("Guest: " + guest.getName() + " (ID: " + guest.getId() + ")");
        layout.getChildren().add(header);

        for (Booking booking : Booking.bookings) {
            if (booking.getGuestId().equalsIgnoreCase(guest.getId())) {
                Label bookingLabel = new Label("  Booking ID: " + booking.getId() +
                        ", Date: " + booking.getDate() +
                        ", Payment: " + booking.getPayment());
                layout.getChildren().add(bookingLabel);
            }
        }
    }

    Scene scene = new Scene(layout, 400, 400);
    resultStage.setScene(scene);
    resultStage.show();
}
    
    

    private void openViewBookingReportsWindow(Admin admin, Stage primaryStage, Scene adminScene) {
    VBox bookingReportPane = new VBox();
    bookingReportPane.setAlignment(Pos.CENTER);
    bookingReportPane.setSpacing(20);

    // Title
    Label titleLabel = new Label("Booking Reports");
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    // Labels and TextFields for date range input
    Label startDateLabel = new Label("Start Date (dd/MM/yyyy):");
    TextField startDateField = new TextField();
    startDateField.setPromptText("Enter start date");

    Label endDateLabel = new Label("End Date (dd/MM/yyyy):");
    TextField endDateField = new TextField();
    endDateField.setPromptText("Enter end date");

    // Buttons for booking reports
    Button totalBookingsOverPeriodBtn = new Button("Number of Bookings Over a Period");
    totalBookingsOverPeriodBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

    Button avgRevenueOverPeriodBtn = new Button("Average Revenue Over a Period");
    avgRevenueOverPeriodBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");

    Button totalRevenueOverPeriodBtn = new Button("Total Revenue Over a Period");
    totalRevenueOverPeriodBtn.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black;");

    Button previousBtn = new Button("Previous");
    previousBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");

    // Result Label
    Label resultLabel = new Label();
    resultLabel.setWrapText(true);

    // Date parsing helper method
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Function<String, Date> parseDate = dateString -> {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }
    };

    // Action for "Number of Bookings Over a Period"
    totalBookingsOverPeriodBtn.setOnAction(e -> {
        Date startDate = parseDate.apply(startDateField.getText().trim());
        Date endDate = parseDate.apply(endDateField.getText().trim());

        if (startDate == null || endDate == null) return;

        long totalBookings = Booking.bookings.stream()
                .filter(booking -> booking.getDate() != null &&
                        !booking.getDate().before(startDate) &&
                        !booking.getDate().after(endDate))
                .count();

        resultLabel.setText("Total Bookings from " + startDateField.getText() + " to " + endDateField.getText() + ": " + totalBookings);
        showAlert(Alert.AlertType.INFORMATION, "Bookings Over Period", resultLabel.getText());
    });

    // Action for "Average Revenue Over a Period"
    avgRevenueOverPeriodBtn.setOnAction(e -> {
        Date startDate = parseDate.apply(startDateField.getText().trim());
        Date endDate = parseDate.apply(endDateField.getText().trim());

        if (startDate == null || endDate == null) return;

        List<Booking> filteredBookings = Booking.bookings.stream()
                .filter(booking -> booking.getDate() != null &&
                        !booking.getDate().before(startDate) &&
                        !booking.getDate().after(endDate))
                .toList();

        double totalRevenue = filteredBookings.stream().mapToDouble(Booking::getPayment).sum();
        double avgRevenue = filteredBookings.isEmpty() ? 0 : totalRevenue / filteredBookings.size();

        resultLabel.setText("Average Revenue from " + startDateField.getText() + " to " + endDateField.getText() + ": $" + String.format("%.2f", avgRevenue));
        showAlert(Alert.AlertType.INFORMATION, "Average Revenue Over Period", resultLabel.getText());
    });

    // Action for "Total Revenue Over a Period"
    totalRevenueOverPeriodBtn.setOnAction(e -> {
        Date startDate = parseDate.apply(startDateField.getText().trim());
        Date endDate = parseDate.apply(endDateField.getText().trim());

        if (startDate == null || endDate == null) return;

        double totalRevenue = Booking.bookings.stream()
                .filter(booking -> booking.getDate() != null &&
                        !booking.getDate().before(startDate) &&
                        !booking.getDate().after(endDate))
                .mapToDouble(Booking::getPayment)
                .sum();

        resultLabel.setText("Total Revenue from " + startDateField.getText() + " to " + endDateField.getText() + ": $" + String.format("%.2f", totalRevenue));
        showAlert(Alert.AlertType.INFORMATION, "Total Revenue Over Period", resultLabel.getText());
    });

    // Previous button action
    previousBtn.setOnAction(e -> {
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Admin Menu");
    });

    // Add components to the pane
    bookingReportPane.getChildren().addAll(
            titleLabel,
            startDateLabel, startDateField,
            endDateLabel, endDateField,
            totalBookingsOverPeriodBtn, avgRevenueOverPeriodBtn, totalRevenueOverPeriodBtn,
            resultLabel,
            previousBtn
    );

    Scene bookingReportScene = new Scene(bookingReportPane, 500, 600);
    primaryStage.setScene(bookingReportScene);
    primaryStage.setTitle("View Booking Reports");
}


    // Receptionist Actions
    private void openCreateBookingWindow(Receptionist receptionist, Stage primaryStage, Scene receptionistScene) {
    VBox createBookingPane = new VBox();
    createBookingPane.setAlignment(Pos.CENTER);
    createBookingPane.setSpacing(20);

    // Title
    Label titleLabel = new Label("Create Booking");
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    // Guest Selection
    Label guestLabel = new Label("Select Guest:");
    ComboBox<String> guestComboBox = new ComboBox<>();
    Guest.guests.forEach(guest -> guestComboBox.getItems().add(guest.getId() + " - " + guest.getName()));
    guestComboBox.setPromptText("Choose a Guest");

    // Trip Selection
    Label tripLabel = new Label("Select Trip:");
    ComboBox<String> tripComboBox = new ComboBox<>();
    Trip.trips.forEach(trip -> tripComboBox.getItems().add(trip.getId() + " - " + trip.getFrom() + " to " + trip.getTo()));
    tripComboBox.setPromptText("Choose a Trip");

    // Payment and Status Fields
    Label paymentLabel = new Label("Payment Amount:");
    TextField paymentField = new TextField();
    paymentField.setPromptText("Enter payment amount");

    Label statusLabel = new Label("Status:");
    ComboBox<String> statusComboBox = new ComboBox<>();
    statusComboBox.getItems().addAll("Confirmed", "Pending", "Canceled");
    statusComboBox.setPromptText("Choose Status");

    // Buttons
    Button createBookingBtn = new Button("Create Booking");
    createBookingBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
    Button previousBtn = new Button("Previous");
    previousBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");

    // Feedback Label
    Label resultLabel = new Label();

    // Action for "Create Booking"
    createBookingBtn.setOnAction(e -> {
        String selectedGuest = guestComboBox.getValue();
        String selectedTrip = tripComboBox.getValue();
        String paymentStr = paymentField.getText().trim();
        String status = statusComboBox.getValue();

        if (selectedGuest == null || selectedTrip == null || paymentStr.isEmpty() || status == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required to create a booking.");
            resultLabel.setText("Error: All fields are required.");
            return;
        }

        try {
            double payment = Double.parseDouble(paymentStr);
            if (payment <= 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Payment must be a positive value.");
                resultLabel.setText("Error: Payment must be positive.");
                return;
            }

            String guestId = selectedGuest.split(" - ")[0];
            String tripId = selectedTrip.split(" - ")[0];

            // Validate Guest and Trip existence
            Guest guest = Guest.guests.stream()
                    .filter(g -> g.getId().equals(guestId))
                    .findFirst()
                    .orElse(null);

            Trip trip = Trip.trips.stream()
                    .filter(t -> t.getId().equals(tripId))
                    .findFirst()
                    .orElse(null);

            if (guest == null || trip == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Selected guest or trip not found.");
                resultLabel.setText("Error: Guest or Trip not found.");
                return;
            }

            Booking newBooking = new Booking("B" + (Booking.bookings.size() + 1), guestId, tripId, payment, status);
            newBooking.setDate(new Date()); // Set the current date as the booking date
            receptionist.createBooking(newBooking);

            // Update guest and trip statistics
            guest.setTotalBookings(guest.getTotalBookings() + 1);
            guest.setTotalRevenue(guest.getTotalRevenue() + payment);
            trip.addBooking(payment);

            resultLabel.setText("Success: Booking created.");
            showAlert(Alert.AlertType.INFORMATION, "Success", "Booking created successfully!");

            // Clear fields after success
            guestComboBox.getSelectionModel().clearSelection();
            tripComboBox.getSelectionModel().clearSelection();
            paymentField.clear();
            statusComboBox.getSelectionModel().clearSelection();

        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Payment must be a valid number.");
            resultLabel.setText("Error: Payment must be a valid number.");
        }
    });

    // Action for "Previous" button
    previousBtn.setOnAction(e -> {
        primaryStage.setScene(receptionistScene);
        primaryStage.setTitle("Receptionist Menu");
    });

    // Add components to the pane
    createBookingPane.getChildren().addAll(
            titleLabel,
            guestLabel, guestComboBox,
            tripLabel, tripComboBox,
            paymentLabel, paymentField,
            statusLabel, statusComboBox,
            createBookingBtn, resultLabel, previousBtn
    );

    Scene createBookingScene = new Scene(createBookingPane, 600, 600);
    primaryStage.setScene(createBookingScene);
    primaryStage.setTitle("Create Booking");
}



   private void openSelectTripDetailsWindow(Receptionist receptionist, Stage primaryStage, Scene receptionistScene) {
    VBox selectTripPane = new VBox();
    selectTripPane.setAlignment(Pos.CENTER);
    selectTripPane.setSpacing(20);

    // Title
    Label titleLabel = new Label("Select Trip Details");
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    // Filters
    Label categoryLabel = new Label("Category:");
    ComboBox<String> categoryComboBox = new ComboBox<>();
    categoryComboBox.getItems().addAll("Local", "Interstate", "International");

    Label fromLabel = new Label("From Location:");
    TextField fromField = new TextField();

    Label toLabel = new Label("To Location:");
    TextField toField = new TextField();

    Label guestIdLabel = new Label("Guest ID:");
    ComboBox<String> guestComboBox = new ComboBox<>();
    Guest.guests.forEach(guest -> guestComboBox.getItems().add(guest.getId() + " - " + guest.getName()));

    // Trip Selection Area
    TextArea tripSelectionArea = new TextArea();
    tripSelectionArea.setEditable(false);
    tripSelectionArea.setWrapText(true);
    tripSelectionArea.setPrefHeight(200);

    Button selectTripBtn = new Button("Select Trip");
    selectTripBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");

    Button previousBtn = new Button("Previous");
    previousBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");

    // Action for "Select Trip"
    selectTripBtn.setOnAction(e -> {
        String category = categoryComboBox.getValue();
        String from = fromField.getText().trim();
        String to = toField.getText().trim();
        String guestId = guestComboBox.getValue();

        if (category == null || from.isEmpty() || to.isEmpty() || guestId == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required to select a trip.");
            return;
        }

        String selectedGuestId = guestId.split(" - ")[0];

        // Find trips matching the criteria
        List<Trip> matchingTrips = Trip.trips.stream()
                .filter(trip -> trip.getCategory().equalsIgnoreCase(category) &&
                        trip.getFrom().equalsIgnoreCase(from) &&
                        trip.getTo().equalsIgnoreCase(to))
                .toList();

        if (matchingTrips.isEmpty()) {
            tripSelectionArea.setText("No matching trips found.");
        } else {
            Trip selectedTrip = matchingTrips.get(0); // Select the first match
            Booking newBooking = new Booking(
                    "B" + (Booking.bookings.size() + 1),
                    selectedGuestId,
                    selectedTrip.getId(),
                    0.0,
                    "Pending"
            );
            receptionist.selectTripDetails(newBooking, category, from, to);
            Booking.bookings.add(newBooking);

            tripSelectionArea.setText(String.format(
                    "Selected Trip:\nTrip ID: %s\nFrom: %s\nTo: %s\nCategory: %s\n",
                    selectedTrip.getId(), selectedTrip.getFrom(), selectedTrip.getTo(), selectedTrip.getCategory()
            ));
            showAlert(Alert.AlertType.INFORMATION, "Success", "Trip successfully selected for the guest.");
        }
    });

    // Previous button action
    previousBtn.setOnAction(e -> {
        primaryStage.setScene(receptionistScene);
        primaryStage.setTitle("Receptionist Menu");
    });

    // Add components
    selectTripPane.getChildren().addAll(
            titleLabel,
            categoryLabel, categoryComboBox,
            fromLabel, fromField,
            toLabel, toField,
            guestIdLabel, guestComboBox,
            selectTripBtn,
            tripSelectionArea,
            previousBtn
    );

    Scene selectTripScene = new Scene(selectTripPane, 500, 600);
    primaryStage.setScene(selectTripScene);
    primaryStage.setTitle("Select Trip Details");
}

    private void openCancelBookingWindow(Receptionist receptionist, Stage primaryStage, Scene receptionistScene) {
    VBox cancelBookingPane = new VBox();
    cancelBookingPane.setAlignment(Pos.CENTER);
    cancelBookingPane.setSpacing(20);

    // Title
    Label titleLabel = new Label("Cancel Booking");
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    // Booking Selection
    Label bookingLabel = new Label("Select Booking to Cancel:");
    ComboBox<String> bookingComboBox = new ComboBox<>();
    Booking.bookings.stream()
            .filter(booking -> !"Canceled".equalsIgnoreCase(booking.getStatus()))
            .forEach(booking -> bookingComboBox.getItems().add(booking.getId() + " - " + booking.getTripId()));

    Button cancelBookingBtn = new Button("Cancel Booking");
    cancelBookingBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
    Label resultLabel = new Label();

    Button previousBtn = new Button("Previous");
    previousBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

    // Cancel action
    cancelBookingBtn.setOnAction(e -> {
        String selectedBooking = bookingComboBox.getValue();

        if (selectedBooking == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a booking to cancel.");
            return;
        }

        String bookingId = selectedBooking.split(" - ")[0];
        Booking bookingToCancel = Booking.bookings.stream()
                .filter(booking -> booking.getId().equalsIgnoreCase(bookingId))
                .findFirst()
                .orElse(null);

        if (bookingToCancel != null) {
            bookingToCancel.setStatus("Canceled");
            showAlert(Alert.AlertType.INFORMATION, "Success", "Booking canceled successfully.");
            resultLabel.setText("Booking Canceled: " + bookingId);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Booking not found.");
            resultLabel.setText("Booking not found: " + bookingId);
        }
    });

    previousBtn.setOnAction(e -> {
        primaryStage.setScene(receptionistScene);
        primaryStage.setTitle("Receptionist Menu");
    });

    cancelBookingPane.getChildren().addAll(
            titleLabel,
            bookingLabel, bookingComboBox,
            cancelBookingBtn,
            resultLabel,
            previousBtn
    );

    Scene cancelBookingScene = new Scene(cancelBookingPane, 500, 400);
    primaryStage.setScene(cancelBookingScene);
    primaryStage.setTitle("Cancel Booking");
}

    private void openCalculatePaymentWindow(Receptionist receptionist, Stage primaryStage, Scene receptionistScene) {
    VBox calculatePaymentPane = new VBox();
    calculatePaymentPane.setAlignment(Pos.CENTER);
    calculatePaymentPane.setSpacing(20);

    // Title
    Label titleLabel = new Label("Calculate Payment");
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    // Booking Selection
    Label bookingLabel = new Label("Select Booking:");
    ComboBox<String> bookingComboBox = new ComboBox<>();
    Booking.bookings.forEach(booking -> bookingComboBox.getItems().add(booking.getId() + " - " + booking.getTripId()));

    Button calculatePaymentBtn = new Button("Calculate Payment");
    calculatePaymentBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");

    Label resultLabel = new Label();
    resultLabel.setWrapText(true);

    Button previousBtn = new Button("Previous");
    previousBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

    // Calculate action
    calculatePaymentBtn.setOnAction(e -> {
        String selectedBooking = bookingComboBox.getValue();

        if (selectedBooking == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a booking to calculate payment.");
            return;
        }

        String bookingId = selectedBooking.split(" - ")[0];
        Booking bookingToCalculate = Booking.bookings.stream()
                .filter(booking -> booking.getId().equalsIgnoreCase(bookingId))
                .findFirst()
                .orElse(null);

        if (bookingToCalculate != null) {
            double basePayment = bookingToCalculate.getPayment();
            double tax = 0.14 * basePayment; // Example: 14% tax
            double totalPayment = basePayment + tax;

            String paymentDetails = String.format(
                    "Booking ID: %s\nBase Payment: $%.2f\nTax (14%%): $%.2f\nTotal Payment: $%.2f",
                    bookingId, basePayment, tax, totalPayment
            );

            showAlert(Alert.AlertType.INFORMATION, "Payment Calculated", paymentDetails);
            resultLabel.setText(paymentDetails);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Booking not found.");
            resultLabel.setText("Booking not found: " + bookingId);
        }
    });

    previousBtn.setOnAction(e -> {
        primaryStage.setScene(receptionistScene);
        primaryStage.setTitle("Receptionist Menu");
    });

    calculatePaymentPane.getChildren().addAll(
            titleLabel,
            bookingLabel, bookingComboBox,
            calculatePaymentBtn,
            resultLabel,
            previousBtn
    );

    Scene calculatePaymentScene = new Scene(calculatePaymentPane, 500, 400);
    primaryStage.setScene(calculatePaymentScene);
    primaryStage.setTitle("Calculate Payment");
}

    // Guest Actions
    private void openViewBookingDetailsWindow(Guest guest, Stage primaryStage, Scene guestScene) {
    VBox viewBookingDetailsPane = new VBox();
    viewBookingDetailsPane.setAlignment(Pos.CENTER);
    viewBookingDetailsPane.setSpacing(20);

    // Title and Instructions
    Label titleLabel = new Label("Booking Details");
    titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

    // Display Booking History
    TextArea bookingHistoryArea = new TextArea();
    bookingHistoryArea.setEditable(false);
    bookingHistoryArea.setWrapText(true);
    bookingHistoryArea.setPrefHeight(300);

    // Initially populate with booking details
    if (guest != null) {
        bookingHistoryArea.setText(guest.viewBookingHistory(guest.getId()));
    } else {
        bookingHistoryArea.setText("No guest details available.");
    }

    // Action Buttons
    Button refreshBtn = new Button("Refresh");
    refreshBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
    refreshBtn.setOnAction(e -> {
        if (guest != null) {
            bookingHistoryArea.setText(guest.viewBookingHistory(guest.getId()));
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Guest with ID not found.");
        }
    });

    Button previousBtn = new Button("Previous");
    previousBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
    previousBtn.setOnAction(e -> {
        primaryStage.setScene(guestScene);
        primaryStage.setTitle("Guest Menu");
    });

    // Add components to the pane
    viewBookingDetailsPane.getChildren().addAll(
        titleLabel,
        bookingHistoryArea,
        refreshBtn,
        previousBtn
    );

    Scene viewBookingDetailsScene = new Scene(viewBookingDetailsPane, 600, 500);
    primaryStage.setScene(viewBookingDetailsScene);
    primaryStage.setTitle("Booking Details");
}

    private void openRateBookingWindow(Guest guest, Stage primaryStage, Scene guestScene) {
    VBox rateBookingPane = new VBox();
    rateBookingPane.setAlignment(Pos.CENTER);
    rateBookingPane.setSpacing(20);

    // Title and Instructions
    Label titleLabel = new Label("Rate Your Booking");
    titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

    // Display Booking History
    TextArea bookingHistoryArea = new TextArea();
    bookingHistoryArea.setEditable(false);
    bookingHistoryArea.setPrefHeight(200);
    bookingHistoryArea.setWrapText(true);
    bookingHistoryArea.setText(guest.viewBookingHistory(guest.getId())); // Display booking history for the guest

    Label bookingIdLabel = new Label("Select Booking ID:");
    TextField bookingIdField = new TextField();
    bookingIdField.setPromptText("Enter Booking ID");

    Label ratingLabel = new Label("Enter Rating (1-5):");
    TextField ratingField = new TextField();
    ratingField.setPromptText("Enter Rating");

    Button rateBookingBtn = new Button("Rate Booking");
    rateBookingBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
    Label resultLabel = new Label();

    Button previousBtn = new Button("Previous");
    previousBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");

    // Action for "Rate Booking"
    rateBookingBtn.setOnAction(e -> {
        String bookingId = bookingIdField.getText().trim();
        String ratingStr = ratingField.getText().trim();

        // Validate inputs
        if (bookingId.isEmpty() || ratingStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Both Booking ID and Rating are required.");
            return;
        }

        try {
            int rating = Integer.parseInt(ratingStr);
            if (rating < 1 || rating > 5) {
                showAlert(Alert.AlertType.ERROR, "Error", "Rating must be between 1 and 5.");
                return;
            }

            // Rate the booking
            boolean bookingFound = false;
            for (Booking b : Booking.bookings) {
                if (b.getId().equalsIgnoreCase(bookingId)) {
                    guest.rateBookingHistory(bookingId, String.valueOf(rating));
                    bookingFound = true;
                    showAlert(Alert.AlertType.INFORMATION, "Success", 
                        String.format("Booking with ID %s has been successfully rated: %d", bookingId, rating));
                    resultLabel.setText(String.format("Rated: ID = %s, Rating = %d", bookingId, rating));
                    bookingHistoryArea.setText(guest.viewBookingHistory(guest.getId())); // Refresh booking history
                    break;
                }
            }

            if (!bookingFound) {
                showAlert(Alert.AlertType.ERROR, "Error", "Booking with ID " + bookingId + " not found.");
                resultLabel.setText("No booking found with ID: " + bookingId);
            }
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Rating must be a valid integer between 1 and 5.");
        }
    });

    // Action for "Previous" button
    previousBtn.setOnAction(e -> {
        primaryStage.setScene(guestScene);
        primaryStage.setTitle("Guest Menu");
    });

    // Add components to the pane
    rateBookingPane.getChildren().addAll(
        titleLabel,
        bookingHistoryArea,
        bookingIdLabel, bookingIdField,
        ratingLabel, ratingField,
        rateBookingBtn,
        resultLabel,
        previousBtn
    );

    Scene rateBookingScene = new Scene(rateBookingPane, 600, 550);
    primaryStage.setScene(rateBookingScene);
    primaryStage.setTitle("Rate Booking");
}






    public static void main(String[] args) {
  
        launch(args);
        
 
    }
    
        private <T> void saveToFile(String fileName, List<T> list) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
        System.out.println("Saving data to " + fileName + ": " + list);
        oos.writeObject(list);
        System.out.println("Data saved successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private <T> List<T> loadFromFile(String FILE_NAME) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {            
            return new ArrayList<>();
        }
    }
}

