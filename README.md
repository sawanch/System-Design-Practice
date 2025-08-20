# System Design Practice

This repository contains implementations of **Low-Level System Design (LLD)** problems in Java.

---

## 📌 Projects

### 1. ATM System Design (`ATM-Dispenser/`)

**Overview:**  
The ATM System Design simulates the working of an Automated Teller Machine with a focus on **Object-Oriented Design principles**.  

**Key Features:**  
- **Authentication:** Card + PIN verification before transactions.  
- **Transactions:** Cash withdrawal, balance inquiry (extensible to deposits, mini-statements).  
- **Error Handling:** Custom exceptions for invalid PINs, insufficient balance, and transaction errors.  
- **OOP Concepts:**  
  - Encapsulation (separating ATM, Account, and Bank logic).  
  - Abstraction (hiding low-level details behind clear interfaces).  
  - Extensibility (easy to add new transaction types).  

**Design Components:**  
- **ATM Class** → Handles user interaction and transaction flow.  
- **Account Class** → Stores user details and balance.  
- **Bank Class** → Acts as a controller for accounts and validation.  
- **Custom Exceptions** → Manage error scenarios gracefully.  

---

### 2. Parking Lot System Design (`Parking-Lot/`)

**Overview:**  
The Parking Lot System manages the allocation and release of parking spots, calculates parking fees, and ensures smooth handling of vehicles with different types and constraints.  

**Key Features:**  
- **Spot Management:** Assigns available spots based on vehicle type and releases them when customers exit.  
- **Ticket & Fee Calculation:** Uses entry and exit times to calculate fees (default $10/hour).  
- **Custom Exceptions:**  
  - `InvalidLicenseException` → Raised when license plate is null/empty.  
  - `InvalidVehicleTypeException` → Raised when vehicle type does not match the spot.  
- **Persistence:** Integrated with **SQLite database** via DAO to store and retrieve parking spot data.  
- **Testability:**  
  - JUnit + Mockito for unit testing.  
  - Jacoco integrated for code coverage reports.  

**Design Components:**  
- **Customer Class** → Holds vehicle details, license, entry/exit time.  
- **ParkingSpot Class** → Represents individual parking spots with occupancy status.  
- **ParkingLotSystemImpl Class** → Core system handling allocation, release, and fee calculation.  
- **TicketFeesSystemImpl Class** → Responsible for computing parking charges.  
- **ParkingSpotDAO Class** → Manages persistence using SQLite DB.  
- **Custom Exceptions** → Handle invalid input gracefully.  

---

## ✨ Purpose
To practice **object-oriented design and coding** with a focus on:  
- Encapsulation, Abstraction, and Extensibility.  
- Error handling through **custom exceptions**.  
- Integrating **databases (SQLite)** for persistence.  
- Writing **unit tests with JUnit + Mockito** for robust verification.  
