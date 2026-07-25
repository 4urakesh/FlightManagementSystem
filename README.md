# ✈️ Flight Management System

A backend-based **Flight Management System** developed using **Java and Spring Boot** to manage flights, passengers, bookings, and payment operations through RESTful APIs.

The application provides a structured and efficient platform for managing flight booking operations, including flight search, passenger management, ticket booking, booking cancellation, and payment processing.

---

## 🚀 Features

### 👤 User Management

* User registration and login
* Secure authentication using Spring Security and JWT
* Role-based access control

### ✈️ Flight Management

* Add new flights
* View all available flights
* Search flights by source and destination
* Search flights based on travel date
* Update flight details
* Delete flight records

### 🎫 Booking Management

* Book flight tickets
* Manage passenger details
* View booking information
* Update booking details
* Cancel bookings
* View booking history

### 💳 Payment Management

* Process payment for bookings
* Maintain payment records
* Confirm booking after successful payment

### 🛡️ Security & Validation

* JWT-based authentication
* Role-based authorization
* Input validation
* Global exception handling
* Secure REST APIs

---

## 🛠️ Technologies Used

| Technology          | Usage                          |
| ------------------- | ------------------------------ |
| ☕ Java              | Backend Programming            |
| 🌱 Spring Boot      | Application Development        |
| 🌐 Spring MVC       | REST API Development           |
| 🗃️ Spring Data JPA | Database Operations            |
| 🔄 Hibernate        | ORM Framework                  |
| 🐘 PostgreSQL       | Database                       |
| 🔐 Spring Security  | Authentication & Authorization |
| 🎟️ JWT             | Token-Based Authentication     |
| 📦 Maven            | Dependency Management          |
| 🧪 Postman          | API Testing                    |

---

## 🏗️ Project Architecture

The application follows a layered architecture:

```text
src/main/java
│
└── com.example.flightmanagement
    │
    ├── controller
    │   └── Handles REST API requests
    │
    ├── service
    │   └── Contains business logic
    │
    ├── repository
    │   └── Handles database operations
    │
    ├── entity
    │   └── Contains JPA entities
    │
    ├── dto
    │   └── Data Transfer Objects
    │
    ├── exception
    │   └── Custom exception handling
    │
    ├── config
    │   └── Application configuration
    │
    └── security
        └── JWT and Spring Security configuration
```

---

## 🗃️ Main Modules

### ✈️ Flight

Manages flight-related information such as:

* Flight number
* Source
* Destination
* Travel date
* Departure time
* Arrival time
* Available seats

### 👤 Passenger

Stores passenger information required for flight booking.

### 🎫 Booking

Acts as the central module connecting passengers and flights.

It manages:

* Booking details
* Passenger information
* Flight information
* Booking status
* Ticket details

### 💳 Payment

Manages payment information associated with a booking.

A booking is confirmed only after successful payment processing.

---

## 🔗 Entity Relationships

```text
Flight
   │
   ├── One-to-Many
   │
   ├──────────────► Passenger
   │
   └──────────────► Booking
                       │
                       ├── Many-to-One ──► Passenger
                       │
                       ├── Many-to-One ──► Flight
                       │
                       └── One-to-One ──► Payment
```

### Relationship Summary

* One `Flight` can have multiple `Passengers`
* One `Flight` can have multiple `Bookings`
* One `Passenger` can have multiple `Bookings`
* One `Booking` belongs to one `Passenger`
* One `Booking` belongs to one `Flight`
* One `Booking` has one `Payment`

---

## 🔌 REST API Modules

### ✈️ Flight APIs

```text
GET     /api/flights
GET     /api/flights/{id}
POST    /api/flights
PUT     /api/flights/{id}
DELETE  /api/flights/{id}
```

### 🎫 Booking APIs

```text
POST    /api/bookings
GET     /api/bookings/{id}
PUT     /api/bookings/{id}
DELETE  /api/bookings/{id}
```

### 💳 Payment APIs

```text
POST    /api/payments
GET     /api/payments/{id}
```

### 🔐 Authentication APIs

```text
POST    /api/auth/register
POST    /api/auth/login
```

---

## 🔄 Booking Flow

```text
User Login
    ↓
Search Available Flights
    ↓
Select Flight
    ↓
Enter Passenger Details
    ↓
Create Booking
    ↓
Process Payment
    ↓
Payment Successful
    ↓
Booking Confirmed
```

---

## ⚙️ Application Setup

### 1. Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
```

### 2. Navigate to the Project Directory

```bash
cd FlightManagementSystem
```

### 3. Configure PostgreSQL

Create a PostgreSQL database and update the database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flight_management
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

## 🧪 API Testing

The APIs can be tested using:

* Postman
* Swagger/OpenAPI

The application supports testing of:

* Authentication APIs
* Flight APIs
* Passenger APIs
* Booking APIs
* Payment APIs

---

## 🧠 Key Concepts Implemented

* RESTful Web Services
* Layered Architecture
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate ORM
* Entity Relationships
* PostgreSQL Database
* JWT Authentication
* Spring Security
* Role-Based Authorization
* DTO Pattern
* Input Validation
* Exception Handling
* CRUD Operations
* API Testing using Postman

---

## 📌 Future Enhancements

* ✉️ Email confirmation after successful booking
* 📄 Generate downloadable e-tickets
* 💳 Integration with a real payment gateway
* 📱 Frontend application using React
* 🔔 Booking notifications
* 📊 Admin dashboard
* 🔍 Advanced flight filtering and sorting

---

## 👨‍💻 Author

### Rakesh Kumar

B.Tech Computer Science Engineering Graduate

---

## ⭐ Support

If you find this project useful, consider giving the repository a ⭐ on GitHub.
