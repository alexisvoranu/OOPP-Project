# Online Store Management System

A robust Java-based desktop application designed for efficient e-commerce management, providing a complete solution for handling product inventories, customer orders, and sales data analytics.

## Technical Overview

### Core Architecture and Environment
* **Language and Runtime:** Developed using Java 21 (JDK 21).
* **GUI Framework:** Built with Java Swing, utilizing an event-driven programming model and custom layouts for an intuitive user experience.
* **Persistence Layer:** Implements a local file-based database system using text files. It features custom parsing logic for handling both CSV (comma-separated) and tab-delimited data formats.
* **Design Patterns:** Follows Object-Oriented Programming (OOP) principles, emphasizing modularity, encapsulation, and the use of interfaces for service decoupling.

### Advanced Implementation Details
* **Data Processing:** Extensive use of the Java Streams API for efficient data filtering and aggregation, such as calculating totals or generating customer activity reports.
* **Error Handling:** Features a specialized exception handling layer with custom classes like `InvalidNumberOfDataInFileException` and `InvalidQuantityException` to ensure data integrity and prevent illegal operations.
* **File Synchronization:** Utilizes optimized I/O streams (`BufferedReader`, `BufferedWriter`) to synchronize the memory-resident data structures with local storage in real-time.
* **Security and Validation:** Includes automated validation for input formats, ensuring all quantities are non-negative and all required data fields are present before processing.

## Functionalities

### Product Catalog Management
* **Inventory Control:** Provides a complete management interface for products, allowing users to add, modify, delete, and view detailed information for every item in the catalog.
* **Detailed Attributes:** Tracks essential product data including unique IDs, pricing, and warranty periods.

### Order Processing System
* **Dynamic Shopping Cart:** Enables customers to select multiple products, adjust quantities, and manage their virtual cart before finalizing a purchase.
* **Automated Calculations:** Automatically calculates the total order value and supports the application of promotional discount codes (e.g., "EXTRA20").
* **Transaction Finalization:** Generates unique order IDs and records delivery addresses and payment methods directly into the system history.

### Customer Analytics and History
* **Order Tracking:** Allows users to view the complete order history for any specific client, presenting all past transactions in a structured format.
* **Activity Statistics:** Generates comprehensive summaries for each customer, including the total number of orders placed, the total volume of items purchased, and the cumulative amount spent.

## Project Structure
* **ro.ase.PPOO.Clase:** Contains the domain models and core logic controller.
* **ro.ase.PPOO.Interfete:** Manages the graphical components and window navigation.
* **ro.ase.PPOO.Date:** Stores the persistent data files for the system.
