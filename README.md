# 🚌 Bus Ticket Booking System

A Java-based bus ticket booking system with JavaFX GUI that allows management of vehicles, bookings, trips, and users.

## 🎯 Features

### 👥 User Roles
- **Admin**: Manage vehicles and view reports
- **Receptionist**: Handle bookings and customer service
- **Guest**: Book tickets and rate services

### 🔑 Core Functionalities

#### Vehicle Management
```java
public class Vehicle implements Serializable {
    private String id;
    private String category;
    private int totalTrips;
    private int totalBookings;
    private double totalRevenue;
}
```
- Add/Edit/Remove vehicles
- Track vehicle statistics
- Generate vehicle reports

#### Booking System
```java
public class Booking implements Serializable {
    private String id;
    private String guestId;
    private String tripId;
    private double payment;
    private String rate;
    private String status;
    private Date date;
}
```
- Create and manage bookings
- Process payments
- Track booking status
- Rating system

#### Trip Management
```java
public class Trip implements Serializable {
    // Trip details and scheduling
    private String id;
    private String vehicleId;
    private String source;
    private String destination;
    private Date date;
    private double price;
}
```
- Schedule trips
- Set pricing
- Manage routes

## 🛠️ Technical Details

### Data Storage
- Serialized data files:
  - `admins.dat`
  - `receptionists.dat`
  - `guests.dat`
  - `vehicles.dat`
  - `trips.dat`
  - `bookings.dat`

### Input Validation
```java
// Example of input validation in Vehicle class
public void setId(String id) {
    if (id == null || id.trim().isEmpty()) {
        throw new IllegalArgumentException("Vehicle ID cannot be empty");
    }
    this.id = id.trim();
}
```

### Error Handling
- Comprehensive exception handling for:
  - File operations
  - Invalid input data
  - Business logic violations

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE (recommended)
- JavaFX

### Installation

1. Clone the repository
```bash
git clone https://github.com/abdulkader-adnan/Bus-Ticket-Booking-System.git
```

2. Open the project in NetBeans
- File -> Open Project -> Select the cloned directory

3. Run the application
- Locate `BusGUI.java`
- Right-click and select "Run File"

## 🏗️ Project Structure

```
BusSystem/
├── src/
│   ├── Admin.java
│   ├── Booking.java
│   ├── BusGUI.java
│   ├── Guest.java
│   ├── Receptionist.java
│   ├── Trip.java
│   ├── User.java
│   └── Vehicle.java
├── build/
├── nbproject/
└── test/
```

## 💡 Key Features Implementation

### User Authentication
```java
private void openSignInWindow(User user, Stage primaryStage, Scene mainScene) {
    // Authentication logic
    // Role-based access control
}
```

### Data Persistence
```java
private <T> void saveToFile(String fileName, List<T> list) {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(fileName))) {
        oos.writeObject(list);
    } catch (IOException e) {
        // Error handling
    }
}
```

## 🔒 Security Features

- Input validation and sanitization
- Error handling and logging
- Data integrity checks
- Session management

## 🔄 Recent Updates

- Added comprehensive input validation
- Improved error handling
- Enhanced data integrity checks
- Added constructor validation
- Implemented proper string trimming

## 👥 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contact

Abdul Kader - [GitHub Profile](https://github.com/abdulkader-adnan)

Project Link: [https://github.com/abdulkader-adnan/Bus-Ticket-Booking-System](https://github.com/abdulkader-adnan/Bus-Ticket-Booking-System)