# 🗄️ DAO Package – CineVault

The **dao** package handles all database operations using JDBC. It directly interacts with the MySQL database to perform CRUD operations.

## 📁 Classes

* **DBConnection.java**
  Manages the database connection using JDBC.

* **UserDAO.java**
  Handles user-related queries such as login and registration.

* **MovieDAO.java**
  Performs movie operations like insert, update, delete, and fetch.

* **RatingDAO.java**
  Manages storing and retrieving movie ratings.

## 🎯 Purpose

* Executes SQL queries
* Connects application with the database
* Performs all CRUD operations
* Returns data to the controller layer

## 🔁 Flow

```text id="p4xk19"
Controller → DAO → Database
```

## ⚠️ Notes

* Contains only database-related logic (JDBC code)
* No UI or business logic should be written here
* Each class directly implements its database operations (no interfaces used)