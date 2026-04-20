# 🎮 Controller Package – CineVault

The **controller** package contains the business logic of the application. It acts as a bridge between the **view (UI)** and the **DAO (database layer)**.

## 📁 Classes

* **AuthController.java**
  Handles user authentication such as login and validation.

* **MovieController.java**
  Manages movie-related operations like adding, updating, deleting, and fetching movies.

* **RatingController.java**
  Handles movie ratings and generates basic recommendations.

## 🎯 Purpose

* Processes user input from the view
* Applies business logic and validations
* Calls DAO methods to interact with the database
* Returns processed data back to the view

## 🔁 Flow

```text
View → Controller → DAO → Database
```

## ⚠️ Notes

* Should not contain UI code (Swing components)
* Should not directly write SQL queries
* Keeps the application logic organized and maintainable
