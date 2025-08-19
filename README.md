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

## ✨ Purpose
To practice **object-oriented design and coding**.
